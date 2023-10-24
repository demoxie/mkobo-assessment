--liquibase formatted sql
--changeset nvoxland:1
create table test_table (
    id int primary key,
    name varchar(255)
);