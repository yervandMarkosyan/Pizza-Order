package yervand.markosyan.pizza.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yervand.markosyan.pizza.model.PizzaIngredient;
import yervand.markosyan.pizza.repository.PizzaIngredientRepository;

import java.util.Map;
import java.util.HashMap;

@Component
public class PizzaIngredientByIdConverter implements Converter<String, PizzaIngredient> {

    private PizzaIngredientRepository pizzaIngredientRepository;

    @Autowired
    public PizzaIngredientByIdConverter(PizzaIngredientRepository pizzaIngredientRepository) {
        this.pizzaIngredientRepository = pizzaIngredientRepository;
    }

    @Override
    public PizzaIngredient convert(String id) {
        return pizzaIngredientRepository.findById(id).orElse(null);
    }

}