delete from Pizza_Ingredient_Ref;
delete from Pizza;
delete from Pizza_Order;
delete from Pizza_Ingredient;

-- Crusts
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('THIN', 'Thin Crust', 'CRUST');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('THCK', 'Thick Crust', 'CRUST');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('STFD', 'Stuffed Crust', 'CRUST');

-- Sauces
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('TMTO', 'Tomato Sauce', 'SAUCE');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('ALFR', 'Alfredo Sauce', 'SAUCE');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('PSTO', 'Pesto Sauce', 'SAUCE');

-- Cheeses
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('MOZZ', 'Mozzarella', 'CHEESE');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('GOAT', 'Goat Cheese', 'CHEESE');

-- Toppings
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('PEPR', 'Pepperoni', 'TOPPING');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('OLIV', 'Olives', 'TOPPING');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('MUSH', 'Mushrooms', 'TOPPING');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('ONIO', 'Onions', 'TOPPING');
INSERT INTO Pizza_Ingredient (id, name, type) VALUES ('BELL', 'Bell Peppers', 'TOPPING');
