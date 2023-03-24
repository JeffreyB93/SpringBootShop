create table demo_role
(
    id   bigserial
        primary key,
    name varchar(255)
);

alter table demo_role
    owner to postgres;

create table demo_user
(
    id       bigserial
        primary key,
    balance  numeric(38, 2),
    login    varchar(255),
    name     varchar(255),
    password varchar(255)
);

alter table demo_user
    owner to postgres;

create table demo_order
(
    id               bigserial
        primary key,
    amount           integer,
    create_date_time timestamp(6),
    description      varchar(255),
    user_id          bigint
        constraint fkgiwtouahhovxkfp8f2ygcyp1r
            references demo_user
);

alter table demo_order
    owner to postgres;

create table demo_product
(
    id           bigserial
        primary key,
    description  varchar(255),
    name         varchar(255),
    price        numeric(38, 2),
    total_amount integer,
    order_id     bigint
        constraint fk5fddv8s1c00oyjpb4c1h7bop3
            references demo_order
);

alter table demo_product
    owner to postgres;

create table user_role
(
    user_id bigint not null
        constraint fk8i6xa3ri7lch490gtntvnrdh3
            references demo_user,
    role_id bigint not null
        constraint fk8g87vhg3pcle79fheljrjwpst
            references demo_role,
    primary key (user_id, role_id)
);

alter table user_role
    owner to postgres;

