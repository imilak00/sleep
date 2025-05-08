DROP TABLE IF EXISTS sleep_logs;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE public.sleep_logs (
   id BIGINT AUTO_INCREMENT  PRIMARY KEY,
   log_date date NULL,
   start_time timestamp,
   end_time timestamp,
   duration_minutes int4,
   mood varchar(25),
   user_id int4 NOT NULL,
   timezone varchar(50),
   CONSTRAINT sleep_logs_pkey PRIMARY KEY (id),
   CONSTRAINT fk_sleep_logs_users FOREIGN KEY (user_id) REFERENCES users(id)
);