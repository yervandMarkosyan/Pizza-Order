package yervand.markosyan.pizza.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yervand.markosyan.pizza.model.PizzaIngredient;
import yervand.markosyan.pizza.model.PizzaIngredient.Type;
import yervand.markosyan.pizza.model.PizzaOrder;
import yervand.markosyan.pizza.model.Pizza;

import org.springframework.validation.Errors;
import jakarta.validation.Valid;
import yervand.markosyan.pizza.repository.PizzaIngredientRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class PizzaController {
//
    private final PizzaIngredientRepository ingredientRepo;

    @Autowired
    public PizzaController(PizzaIngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<PizzaIngredient> ingredients = ingredientRepo.findAll();
        Type[] types = PizzaIngredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    private Iterable<PizzaIngredient> filterByType(
            Iterable<PizzaIngredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name="pizzaOrder")
    public PizzaOrder pizzaOrder() {
        return new PizzaOrder();
    }

    @ModelAttribute(name="pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showForm() {
        return "design";
    }

    @PostMapping
    public String processPizza(
            @Valid Pizza pizza, Errors errors,
            @ModelAttribute PizzaOrder pizzaOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        pizzaOrder.addPizza(pizza);
        log.info("Processing pizza: {}", pizza);
        return "redirect:/orders/current";
    }

}
