drop table if exists rental_location;
create table rental_location(id INT primary key not null, name text, address text, latitude float, longitude float);
insert into rental_location(id, name, address, latitude, longitude) values (1, 'San Jose Airport', '123 Main St', 37.363949, -121.928940);
select * from rental_location;