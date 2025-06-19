CREATE TABLE IF NOT EXISTS `user`
(
    id          uuid         not null
    primary key,
    name        varchar(255) null,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null
);

