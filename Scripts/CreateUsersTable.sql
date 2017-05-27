drop table if exists Users;
create table Users(
user_id int unsigned auto_increment,
first_name varchar(128) not null,
last_name varchar(128) not null,
login_id varchar(128) not null,
login_passwd varchar(128) not null,
email varchar(100),
primary key (user_id)
);