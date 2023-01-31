-- we don't know how to generate root <with-no-name> (class Root) :(
create table IF NOT EXISTS hits
(
    id        serial
        constraint pk_hits
            primary key,
    app       varchar(50)              not null,
    uri       varchar(100)             not null,
    ip        varchar(20)              not null,
    timestamp timestamp with time zone not null
);

alter table hits
    owner to postgres;

TRUNCATE hits RESTART IDENTITY;

