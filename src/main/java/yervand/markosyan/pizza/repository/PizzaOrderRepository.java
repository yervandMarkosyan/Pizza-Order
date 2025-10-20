package yervand.markosyan.pizza.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yervand.markosyan.pizza.model.Pizza;
import yervand.markosyan.pizza.model.PizzaIngredientRef;
import yervand.markosyan.pizza.model.PizzaOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PizzaOrderRepository {
    private JdbcClient jdbcClient;
    private JdbcTemplate jdbcTemplate;
//
    @Autowired
    public PizzaOrderRepository(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate) {
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public PizzaOrder save(PizzaOrder order) {
        order.setPlacedAt(new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Pizza_Order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, order.getDeliveryName());
            ps.setString(2, order.getDeliveryStreet());
            ps.setString(3, order.getDeliveryCity());
            ps.setString(4, order.getDeliveryState());
            ps.setString(5, order.getDeliveryZip());
            ps.setString(6, order.getCcNumber());
            ps.setString(7, order.getCcExpiration());
            ps.setString(8, order.getCcCVV());
            ps.setTimestamp(9, new Timestamp(order.getPlacedAt().getTime()));
            return ps;
        }, keyHolder);

        Long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        int i = 0;
        for (Pizza pizza : order.getPizzas()) {
            savePizza(orderId, i++, pizza);
        }

        return order;
    }

    private long savePizza(Long orderId, int orderKey, Pizza pizza) {
        pizza.setCreatedAt(new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Pizza (name, created_at, pizza_order, pizza_order_key) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, pizza.getName());
            ps.setTimestamp(2, new Timestamp(pizza.getCreatedAt().getTime()));
            ps.setLong(3, orderId);
            ps.setInt(4, orderKey);
            return ps;
        }, keyHolder);
//
        Long pizzaId = keyHolder.getKey().longValue();
        pizza.setId(pizzaId);

        saveIngredientRefs(pizzaId, pizza.getIngredients());
        return pizzaId;
    }


    private void saveIngredientRefs(long pizzaId, List<PizzaIngredientRef> ingredientRefs) {
        int key = 0;
        for (PizzaIngredientRef ingredientRef : ingredientRefs) {
            jdbcClient.sql("insert into Pizza_Ingredient_Ref (ingredient, pizza, pizza_key) " +
                            "values (?, ?, ?)")
                    .params(ingredientRef.getIngredient(), pizzaId, key++)
                    .update();
        }
    }

    public Optional<PizzaOrder> findById(Long id) {
        Optional<PizzaOrder> orderOpt = jdbcClient.sql("SELECT ... FROM Pizza_Order WHERE id=?")
                .param(id)
                .query(PizzaOrder.class)
                .optional();

        orderOpt.ifPresent(order -> {
            List<Pizza> pizzas = findPizzasByOrderId(order.getId());
            pizzas.forEach(pizza -> {
                List<PizzaIngredientRef> ingredients = findIngredientsByPizzaId(pizza.getId());
                pizza.setIngredients(ingredients);
            });
            order.setPizzas(pizzas);
        });

        return orderOpt;
    }


    private List<Pizza> findPizzasByOrderId(long orderId) {
        return jdbcClient.sql("select id, name, created_at from Pizza " +
                        "where pizza_order=? order by pizza_order_key")
                .param(orderId)
                .query(Pizza.class)
                .list();
    }

    private List<PizzaIngredientRef> findIngredientsByPizzaId(long pizzaId) {
        return jdbcClient.sql("select ingredient from Pizza_Ingredient_Ref " +
                        "where pizza = ? order by pizza_key")
                .param(pizzaId)
                .query(PizzaIngredientRef.class)
                .list();
    }
}