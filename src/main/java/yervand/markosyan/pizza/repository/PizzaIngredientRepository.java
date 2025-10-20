package yervand.markosyan.pizza.repository;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import yervand.markosyan.pizza.model.PizzaIngredient;

import java.util.Optional;

@Repository
public class PizzaIngredientRepository {
    private final JdbcClient jdbcClient;
//
    public PizzaIngredientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<PizzaIngredient> findById(String id) {
        return jdbcClient.sql("SELECT id, name, type FROM Pizza_Ingredient WHERE id = ?")
                .param(id)
                .query(PizzaIngredient.class)
                .optional();
    }

    public Iterable<PizzaIngredient> findAll() {
        return jdbcClient.sql("SELECT id, name, type FROM Pizza_Ingredient")
                .query(PizzaIngredient.class)
                .list();
    }

    public PizzaIngredient save(PizzaIngredient ingredient) {
        jdbcClient.sql("INSERT INTO Pizza_Ingredient (id, name, type) VALUES (?, ?, ?)")
                .params(ingredient.getId(), ingredient.getName(), ingredient.getType().toString())
                .update();
        return ingredient;
    }
}
