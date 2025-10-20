package yervand.markosyan.pizza.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PizzaIngredient {

    private String id;
    private String name;
    private Type type;

    public enum Type {
        CRUST, SAUCE, CHEESE, TOPPING

    }
}



