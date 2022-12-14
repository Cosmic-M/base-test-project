CREATE TABLE IF NOT EXISTS persons (
    id bigint auto_increment,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL,
    birth_date timestamp NOT NULL,
    CONSTRAINT person_pk PRIMARY KEY (id)
    );

--rollback DROP TABLE persons;
