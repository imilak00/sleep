DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (id, username, email, password)
VALUES (1, 'John123', 'alice@example.com', '$2a$12$jAzbWu7okyBEwMH7JuMRKuHNMSPLNDyuhUkyN4pC/JA2vI9Lgpka.');