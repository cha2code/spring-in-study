drop table if exists users;
drop table if exists authorities;
drop index if exists ix_auth_username;

create table if not exists users(
    username varchar2(50) not null primary key,
    password varchar2(150) not null);

create table if not exists authorities (
    username varchar2(50) not null,
    authority varchar2(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username));