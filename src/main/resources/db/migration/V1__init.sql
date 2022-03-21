CREATE TABLE IF NOT EXISTS user_account
(
    id           SERIAL PRIMARY KEY,
    user_name    VARCHAR(20),
    password     VARCHAR(20),
    age          INTEGER,
    email        VARCHAR(40),
    gender       VARCHAR(10),
    phone_number VARCHAR(10),
    city         VARCHAR(20),
    country      VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS interest
(
    id          SERIAL PRIMARY KEY,
    likes       VARCHAR(100),
    dislikes    VARCHAR(100),
    hobbies     VARCHAR(100),
    profile_url VARCHAR(60),
    about       VARCHAR(200),
    user_id     INTEGER REFERENCES user_account (id)
);
