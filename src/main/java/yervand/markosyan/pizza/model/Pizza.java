package yervand.markosyan.pizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Table
@Data
public class Pizza {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 6, message = "Name should be at least 5-char-long")
    private String name;

    @NotNull
    @Size(min = 3, message = "You should choose at least 3 ingredients")
    private List<PizzaIngredientRef> ingredients;


}
