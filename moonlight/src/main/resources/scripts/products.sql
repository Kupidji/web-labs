CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price VARCHAR(50) NOT NULL,
    image_url VARCHAR(255),
    description TEXT
);

INSERT INTO products (name, price, image_url, description)
VALUES
    ('Кольцо "Луна"', '15 999', 'https://g0.sunlight.net/r/w800/q85/media/products/bde6a979-166e-11ee-b479-005056bc836d.jpg', 'Description for product 1'),
    ('Кольцо "Река"', '22 999', 'https://g4.sunlight.net/r/w800/q85/media/products/3a7e822519d3fb4c76c6e7f71048bcccb4506376.jpg', 'Description for product 2'),
    ('Кольцо "Гранат"', '13 599', 'https://g1.sunlight.net/r/w800/q85/media/products/f4ca8598a0ec303e56019c586dd157ec0ce290db.jpg', 'Description for product 3'),
    ('Кольцо "Изумруд"', '16 899', 'https://g3.sunlight.net/r/w800/q85/media/products/1a2cccf92b698cb57c8de808729a1d6e5b2add82.jpg', 'Description for product 4'),
    ('Кольцо "Coolring1"', '2 223 999', 'https://g5.sunlight.net/r/w800/q85/media/products/fea138cfdb6ffd893780c9f8dd0c97ff86ecda5d.jpg', 'Description for product 5'),
    ('Кольцо "Coolring2', '2 599 499', 'https://g1.sunlight.net/r/w800/q85/media/products/f58c808056cf97d1f37cd13e9661d6cf2cc09be0.jpg', 'Description for product 6'),
    ('Кольцо "Жемчуг"', '239 599', 'https://g2.sunlight.net/r/w800/q85/media/products/4bb548f15f4c18982e0543a5a7db984692ee5048.jpg', 'Description for product 7'),
    ('Кольцо "Роза"', '679 9999', 'https://g7.sunlight.net/r/w800/q85/media/products/2571b398-de75-11ed-b479-005056bc836d.jpg', 'Description for product 8');