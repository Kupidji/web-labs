CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    total_price real NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(500) NOT NULL,
    size real NOT NULL,
    insertion VARCHAR(255),
    engraving VARCHAR(255),
    quantity INT NOT NULL,
    comment VARCHAR(500),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

ALTER TABLE orders
    DROP CONSTRAINT orders_product_id_fkey,
    ADD CONSTRAINT orders_product_id_fkey
        FOREIGN KEY (product_id)
            REFERENCES products(id)
            ON DELETE CASCADE;