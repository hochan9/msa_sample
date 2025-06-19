-- auto-generated definition
CREATE TABLE IF NOT EXISTS wallet
(
    id      bigint auto_increment
        primary key,
    user_id uuid           null,
    point   decimal(38, 2) null,
    created_at  datetime(6)    null,
    modified_at datetime(6)    null,
    constraint UKhgee4p1hiwadqinr0avxlq4eb
        unique (user_id)
);

