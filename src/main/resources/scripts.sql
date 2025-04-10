CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(20)
);

Create the Workout Class table
CREATE TABLE IF NOT EXISTS public.workout_class
(
    workout_class_id SERIAL PRIMARY KEY,
    workout_class_type VARCHAR(50) NOT NULL,
    workout_class_description VARCHAR(250),
    trainer_id INTEGER NOT NULL,
    CONSTRAINT workout_class_trainer_fkey FOREIGN KEY (trainer_id)
        REFERENCES public.users (user_id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);