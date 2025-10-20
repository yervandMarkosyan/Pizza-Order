package yervand.markosyan.pizza.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Digits;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Data
public class PizzaOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Date placedAt = new Date();

    @NotBlank(message = "Name cannot be empty")
    private String deliveryName;

    @NotBlank(message = "Street cannot be empty")
    private String deliveryStreet;

    @NotBlank(message = "City cannot be empty")
    private String deliveryCity;

    @NotBlank(message = "State cannot be empty")
    private String deliveryState;

    @NotBlank(message = "Zip code cannot be empty")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Date format is MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

}



