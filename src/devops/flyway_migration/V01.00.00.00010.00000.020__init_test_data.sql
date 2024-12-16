INSERT INTO address (country, city, street)
VALUES
    ('Russia', 'Kazan', 'Rossiaskaya'),
    ('Russia', 'Kazan', 'Sovetskaya'),
    ('Russia', 'Moscow', 'Sovetskaya'),
    ('Russia', 'Moscow', 'Ivovskaya'),
    ('Turkey', 'Istanbul', 'Hortica');

INSERT INTO supplier (name, address_id, phone_number)
VALUES
    ('first', (SELECT id FROM address WHERE city = 'Kazan' AND street = 'Sovetskaya'), '9159139393'),
    ('second', (SELECT id FROM address WHERE city = 'Moscow' AND street = 'Sovetskaya'), '9159139393'),
    ('third', (SELECT id FROM address WHERE city = 'Istanbul' AND street = 'Hortica'), '9159139393');

INSERT INTO client (client_name, client_surname, birthday, gender, registration_date, address_id)
VALUES
    ('Ivan', 'Ivanov', '12.12.2006', 'MAN', '2024-09-23',
    (SELECT id FROM address WHERE city = 'Kazan' AND street = 'Sovetskaya')),
    ('Petr', 'Petrov', '12.12.2006', 'MAN', '2024-09-23',
    (SELECT id FROM address WHERE city = 'Moscow' AND street = 'Sovetskaya')),
    ('Sidor', 'Sidorov', '12.12.2006', 'MAN', '2024-09-23',
    (SELECT id FROM address WHERE city = 'Istanbul' AND street = 'Hortica'));

INSERT INTO images (image)
VALUES
    ('0x89504E470D0A1A0A'),
    ('0x89504E470D0A1A0B'),
    ('0x89504E470D0A1A0C');


INSERT INTO product (name, category, price, available_stock, last_update_date, supplier_id, image_id)
VALUES
    ('first_product','first_aid', 100, 100, '2024-09-23',
    (SELECT id FROM supplier WHERE address_id = (SELECT id FROM address WHERE city = 'Kazan' AND street = 'Sovetskaya')),
    (SELECT id FROM images WHERE image = '0x89504E470D0A1A0A')),
    ('second_product','second_aid', 100, 100, '2024-09-23',
    (SELECT id FROM supplier WHERE address_id = (SELECT id FROM address WHERE city = 'Moscow' AND street = 'Sovetskaya')),
    (SELECT id FROM images WHERE image = '0x89504E470D0A1A0B')),
    ('third_product','third_aid', 100, 100, '2024-09-23',
    (SELECT id FROM supplier WHERE address_id = (SELECT id FROM address WHERE city = 'Istanbul' AND street = 'Hortica')),
    (SELECT id FROM images WHERE image = '0x89504E470D0A1A0C'));


