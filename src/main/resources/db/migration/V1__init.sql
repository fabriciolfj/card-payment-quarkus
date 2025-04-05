create table customer (
    id serial primary key,
    code varchar(255) not null,
    latitude DOUBLE PRECISION not null,
    longitude DOUBLE PRECISION not null
);

-- Criação da tabela de compra
CREATE TABLE purchase (
    id SERIAL PRIMARY KEY,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    customer_id bigint,
    constraint fk_customer foreign key (customer_id) references customer (id)
);

-- Criação da tabela de cartão
CREATE TABLE card (
    id SERIAL PRIMARY KEY,
    crypt VARCHAR(255) NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    balance DECIMAL(19, 2) NOT NULL
);

-- Criação da tabela de relacionamento entre cartão e compra (many-to-many)
CREATE TABLE card_purchase (
    card_id BIGINT NOT NULL,
    purchase_id BIGINT NOT NULL,
    PRIMARY KEY (card_id, purchase_id),
    CONSTRAINT fk_card FOREIGN KEY (card_id) REFERENCES card (id),
    CONSTRAINT fk_purchase FOREIGN KEY (purchase_id) REFERENCES purchase (id)
);

-- Adicionando índices para melhorar performance de buscas
CREATE INDEX idx_card_expiration ON card (expiration_date);
CREATE INDEX idx_purchase_location ON purchase (latitude, longitude);
create INDEX idx_custome_code on customer(code);