CREATE TABLE public.sleep_logs (
id BIGSERIAL PRIMARY KEY,
log_date DATE NULL,
start_time TIMESTAMPTZ NULL,
end_time TIMESTAMPTZ NULL,
duration_minutes INT4 NULL,
mood VARCHAR(10) NULL,
user_id INT4 NOT NULL,
timezone VARCHAR(255) NULL,
CONSTRAINT fk_sleep_logs_users FOREIGN KEY (user_id) REFERENCES users(id)
);