create table books
(
id bigint generated by default as identity,
title varchar(255) not null,
author varchar(25) not null,
description text not null,
price double precision,
img varchar(255),
quantity int,
primary key(id)
)