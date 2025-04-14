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
    start_date DATE NOT NULL,
    CONSTRAINT membership_user_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (id)
        ON DELETE CASCADE
);


-- Insert data into users table
INSERT INTO public.users (username, password, email, phone, address, role)
VALUES 
    ('Alex', '1234', 'asd@asdad.com', 1232344, '1 Road drive', 'member'),
    ('Balex', '1234', 'bsd@asdad.com', 1232344, '2 Road drive', 'member'),
    ('Calex', '1234', 'csd@asdad.com', 1232344, '3 Road drive', 'member'),
    ('Dalex', '1234', 'dsd@asdad.com', 1232344, '4 Road drive', 'trainer'),
    ('member', '$2a$10$ENgAPkDNlrsE/SOQhgsyAuYW4IOOIa50WFbH8N9zsnsbLuB98B422', 'wow@email.com', 1231232, '4 Privet drive', 'member'),
    ('trainer', '$2a$10$yu8kvon/K5x3iMr11IaZjeMTP7qazZW5TToSsqexQocIlM/1bh8Q.', 'email@gwiz.com', 2345678, '3 Privet drive', 'trainer'),
    ('admin', '$2a$10$8GWum31J8EwMvkkmbF5dIedAARmbCO8M/PFnmnTkd8omVeQlTp8JC', 'email@example.com', 1231322, '2 Privet drive', 'admin');


-- Insert data into membership table
INSERT INTO public.membership (user_id, start_date)
VALUES
    (5, '2023-01-01'),
    (6, '2023-02-15'),
    (7, '2023-03-10'),
    (4, '2023-04-20');

-- Insert data into workout_class table
INSERT INTO public.workout_class (workout_class_type, workout_class_description, trainer_id)
VALUES
    ('Yoga', 'A relaxing yoga class for all levels', 1),
    ('Pilates', 'A challenging pilates class for core strength', 2),
    ('Zumba', 'A fun dance workout to upbeat music', 3),
    ('HIIT', 'High-intensity interval training for maximum burn', 4);