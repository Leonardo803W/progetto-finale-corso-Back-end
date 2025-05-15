TRUNCATE TABLE app_user_roles, users, viaggi;

ALTER TABLE users
ADD COLUMN avatar VARCHAR;

ALTER TABLE viaggi ALTER COLUMN check_in TYPE date USING check_in::date;
ALTER TABLE viaggi ALTER COLUMN check_out TYPE date USING check_out::date;