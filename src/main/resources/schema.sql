CREATE TABLE IF NOT EXISTS Pizza_Ingredient (
    id VARCHAR(4) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Pizza_Order (
    id IDENTITY PRIMARY KEY,
    delivery_Name VARCHAR(50) NOT NULL,
    delivery_Street VARCHAR(50) NOT NULL,
    delivery_City VARCHAR(50) NOT NULL,
    delivery_State VARCHAR(2) NOT NULL,
    delivery_Zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Pizza (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    pizza_order BIGINT NOT NULL,
    pizza_order_key BIGINT NOT NULL,
    FOREIGN KEY (pizza_order) REFERENCES Pizza_Order(id)
);

CREATE TABLE IF NOT EXISTS Pizza_Ingredient_Ref (
    ingredient VARCHAR(4) NOT NULL,
    pizza BIGINT NOT NULL,
    pizza_key BIGINT NOT NULL,
    FOREIGN KEY (ingredient) REFERENCES Pizza_Ingredient(id),
    FOREIGN KEY (pizza) REFERENCES Pizza(id)
);