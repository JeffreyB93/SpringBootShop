insert into demo_role(name) values ('admin');
insert into demo_role(name) values ('user');
insert into demo_role(name) values ('salesman');


insert into demo_user(balance, login, name, password) values (10, 'user_1', 'user_1@', '123');
insert into demo_user(balance, login, name, password) values (20, 'user_2', 'user_2@', '123');
insert into demo_user(balance, login, name, password) values (30, 'user_3', 'user_3@', '123');
insert into demo_user(balance, login, name, password) values (40, 'user_4', 'user_4@', '123');
insert into demo_user(balance, login, name, password) values (50, 'user_5', 'user_5@', '123');
insert into demo_user(balance, login, name, password) values (100, 'user_10', 'user_10@', '123');

insert into user_role(user_id, role_id) values (1, 1);
insert into user_role(user_id, role_id) values (2, 2);
insert into user_role(user_id, role_id) values (3, 2);
insert into user_role(user_id, role_id) values (4, 2);
insert into user_role(user_id, role_id) values (5, 2);
insert into user_role(user_id, role_id) values (6, 3);

insert into demo_order(user_id, description) values (2, 'Привести домой');
insert into demo_order(user_id, description) values (3, 'Привести домой');
insert into demo_order(user_id, description) values (4, 'Заберу сам');
insert into demo_order(user_id, description) values (5, 'Заберу сам');

insert into demo_product(name, price, description, order_id) values ('Молоко', 10, 'Вкусное',3);
insert into demo_product(name, price, description, order_id) values ('Сметана', 20, 'Вкусное', 2);
insert into demo_product(name, price, description, order_id) values ('Хлеб', 30, 'Вкусное', 4);
insert into demo_product(name, price, description, order_id) values ('Кефир', 40, 'Вкусное', 4);
insert into demo_product(name, price, description, order_id) values ('Йогурт', 50, 'Вкусное', 3);
insert into demo_product(name, price, description, order_id) values ('Гречка', 60, 'Вкусное', 2);
insert into demo_product(name, price, description, order_id) values ('Овсянка', 70, 'Вкусное', 2);
insert into demo_product(name, price, description, order_id) values ('Порошок', 80, 'Не вкусное', 3);
insert into demo_product(name, price, description, order_id) values ('Яблоко', 90, 'Вкусное', 4);
insert into demo_product(name, price, description, order_id) values ('Банан', 100, 'Вкусное', 4);



