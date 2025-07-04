
-- 📘 Table des rôles (ADMIN / USER)
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- 👤 Table des utilisateurs
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       profile VARCHAR(50) NOT NULL CHECK (profile IN ('ETUDIANT', 'PROFESSEUR', 'PROFESSIONNEL', 'ANONYME','BIBLIOTHECAIRE')),
                       role_id INT NOT NULL REFERENCES roles(id),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 📚 Table des livres
CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255),
                       isbn VARCHAR(50) UNIQUE,
                       category VARCHAR(100),
                       language VARCHAR(50),
                       description TEXT
);

-- 🔖 Table des exemplaires (copies physiques de livres)
CREATE TABLE book_copies (
                             id SERIAL PRIMARY KEY,
                             book_id INT NOT NULL REFERENCES books(id) ON DELETE CASCADE,
                             status VARCHAR(20) NOT NULL CHECK (status IN ('DISPONIBLE', 'EMPRUNTE', 'RESERVE'))
);

-- 📖 Table des emprunts
CREATE TABLE loans (
                       id SERIAL PRIMARY KEY,
                       user_id INT NOT NULL REFERENCES users(id),
                       book_copy_id INT NOT NULL REFERENCES book_copies(id),
                       start_date DATE NOT NULL,
                       due_date DATE NOT NULL,
                       return_date DATE,
                       extended BOOLEAN DEFAULT FALSE,
                       CONSTRAINT unique_loan UNIQUE (book_copy_id, return_date) -- un exemplaire ne peut être prêté qu'une fois à la fois
);

-- 📆 Table des réservations
CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              user_id INT NOT NULL REFERENCES users(id),
                              book_id INT NOT NULL REFERENCES books(id),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              status VARCHAR(20) NOT NULL CHECK (status IN ('EN_ATTENTE', 'DISPONIBLE', 'EXPIREE')) DEFAULT 'EN_ATTENTE'
);

-- ⚠️ Table des pénalités
CREATE TABLE penalties (
                           id SERIAL PRIMARY KEY,
                           user_id INT NOT NULL REFERENCES users(id),
                           start_date DATE NOT NULL,
                           end_date DATE NOT NULL,
                           days INT NOT NULL,
                           reason VARCHAR(255)
);

-- 📅 Table des jours fériés
CREATE TABLE holidays (
                          id SERIAL PRIMARY KEY,
                          holiday_date DATE UNIQUE NOT NULL,
                          name VARCHAR(100) NOT NULL
);

CREATE TABLE activity_logs (
                               id SERIAL PRIMARY KEY,
                               user_id INT REFERENCES users(id),
                               action_type VARCHAR(50) NOT NULL CHECK (action_type IN (
                                                                                       'EMPRUNT', 'RETOUR', 'RESERVATION', 'PROLONGATION', 'PENALITE', 'CREATION_LIVRE', 'SUPPRESSION_LIVRE', 'CONNEXION', 'MODIFICATION_UTILISATEUR'
                                   )),
                               description TEXT,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
