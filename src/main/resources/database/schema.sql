-- select * from User;
-- select * from Car;
-- select * from Car_Model;
-- select * from Rental_Order;
-- select * from Rental_Order_Time_Slice;

create table if not exists User
(
    id         varchar(20) primary key,
    name       varchar(25) unique                    not null,
    password   varchar(25)                           not null,
    phone      varchar(11),
    created_at timestamp default CURRENT_TIMESTAMP() not null
);

create table if not exists Car_Model
(
    id             varchar(20) primary key,
    name           varchar(20) unique                    not null,
    price_per_hour int(5)                                not null,
    in_stock       int(3)                                not null,
    created_at     timestamp default CURRENT_TIMESTAMP() not null
);

create table if not exists Car
(
    id            varchar(20) primary key,
    license_plate varchar(7) unique                     not null,
    car_model_id  varchar(20)                           not null,
    created_at    timestamp default CURRENT_TIMESTAMP() not null
);

alter table Car
    add foreign key (car_model_id) references Car_Model (id);

create table if not exists Rental_Order
(
    id                varchar(20) primary key,
    car_id            varchar(20)                           not null,
    user_id           varchar(20)                           not null,
    start_time_string varchar(16)                           not null,
    end_time_string   varchar(16)                           not null,
    total_cost        int(10)                               not null,
    created_at        timestamp default CURRENT_TIMESTAMP() not null
);

alter table Rental_Order
    add foreign key (car_id) references Car (id);

alter table Rental_Order
    add foreign key (user_id) references User (id);

create table if not exists Rental_Order_Time_Slice
(
    id                varchar(20) primary key,
    rental_order_id   varchar(20)                           not null,
    start_time_string varchar(16)                           not null,
    end_time_string   varchar(16)                           not null,
    car_id            varchar(20)                           not null,
    user_id           varchar(20)                           not null,
    created_at        timestamp default CURRENT_TIMESTAMP() not null
);

create unique index Rental_Order_Time_Slice_Unique_Key on Rental_Order_Time_Slice (car_id, start_time_string, end_time_string);

alter table Rental_Order_Time_Slice
    add foreign key (rental_order_id) references Rental_Order (id);