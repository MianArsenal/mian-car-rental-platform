--delete
delete
from User;
delete
from Car;

--insert User
insert into User (id, name, password, phone, created_at)
values ('1', 'mian', 'pass', '13888888888', CURRENT_TIMESTAMP());

--insert Car_Model
insert into Car_Model (id, name, price_per_hour, in_stock, created_at)
values ('1', 'BMW 650', 5000, 2, CURRENT_TIMESTAMP());
insert into Car_Model (id, name, price_per_hour, in_stock, created_at)
values ('2', 'Toyota Camry', 1000, 2, CURRENT_TIMESTAMP());

--insert Car
insert into Car (id, license_plate, car_model_id, created_at)
values ('1', '粤B11111', '1', CURRENT_TIMESTAMP());
insert into Car (id, license_plate, car_model_id, created_at)
values ('2', '粤B22222', '1', CURRENT_TIMESTAMP());
insert into Car (id, license_plate, car_model_id, created_at)
values ('3', '粤B33333', '2', CURRENT_TIMESTAMP());
insert into Car (id, license_plate, car_model_id, created_at)
values ('4', '粤B44444', '2', CURRENT_TIMESTAMP());
