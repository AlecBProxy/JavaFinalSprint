DROP TABLE IF EXISTS public.workout_class CASCADE;
DROP TABLE IF EXISTS public.users CASCADE;
DROP TABLE IF EXISTS public.membership CASCADE;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS public.workout_class
(
    workout_class_id SERIAL PRIMARY KEY,
    workout_class_type VARCHAR(50) NOT NULL,
    workout_class_description VARCHAR(250),
    trainer_id INTEGER NOT NULL,
    CONSTRAINT workout_class_trainer_fkey FOREIGN KEY (trainer_id)
        REFERENCES public.users (id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS public.membership (
    membership_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    membership_type VARCHAR(50) NOT NULL,
    start_date DATE NOT NULL,
    CONSTRAINT membership_user_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id)
        ON DELETE CASCADE
);