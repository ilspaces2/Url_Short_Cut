create table site
(
    id serial primary key,
    site text NOT NULL unique,
    login text NOT NULL unique,
    password text NOT NULL
);

create table url
(
    id serial primary key,
    url text NOT NULL unique,
    code text NOT NULL unique,
    total int default 0,
    site_id int references site(id)
);
