CREATE TABLE IF NOT EXISTS credentials (
    credential_id SERIAL PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    uid VARCHAR(255) NOT NULL UNIQUE
);
