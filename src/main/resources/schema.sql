CREATE TABLE pet (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         pet_name VARCHAR(30),
                         owner_last_name VARCHAR(30),
                         pet_kind VARCHAR(30),
                         pet_gender VARCHAR(30),
                         vaccinated BOOLEAN
);