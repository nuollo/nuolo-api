CREATE TABLE IF NOT EXISTS users (
    id         SERIAL PRIMARY KEY,
    user_id    VARCHAR(60) NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT now()
);
