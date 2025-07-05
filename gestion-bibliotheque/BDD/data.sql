-- 📌 1. Insertion des rôles

INSERT INTO roles (name) VALUES
                             ('ADMIN'),
                             ('UTILISATEUR');

-- 👤 2. Insertion des utilisateurs

INSERT INTO users (name, email, password, profile, role_id, active, role, username)
VALUES
    ('Admin Bibliothèque', 'admin@bib.mg', 'admin123', 'BIBLIOTHECAIRE', 1, true, 'BIBLIOTHECAIRE', 'admin'),
    ('Jean Etudiant', 'jean@etud.mg', 'pass123', 'ETUDIANT', 2, true, 'ETUDIANT', 'jeanE'),
    ('Marie Pro', 'marie@pro.mg', 'pass456', 'PROFESSIONNEL', 2, true, 'PROFESSIONNEL', 'marieP');

-- 📚 3. Catégories de livres

INSERT INTO book_categories (name) VALUES
                                       ('Roman'),
                                       ('Informatique'),
                                       ('Sciences'),
                                       ('Histoire');

-- 🌍 4. Langues

INSERT INTO languages (name) VALUES
                                 ('Français'),
                                 ('Anglais'),
                                 ('Malagasy');

-- 📘 5. Livres

INSERT INTO books (title, author, isbn, category_id, language_id)
VALUES
    ('Le Petit Prince', 'Antoine de Saint-Exupéry', '9782070612758', 1, 1),
    ('Spring Boot en Action', 'Craig Walls', '9781617292545', 2, 2),
    ('Introduction à la physique', 'Isaac Newton', '9782212545687', 3, 1);

-- 🔖 6. Copies de livres

INSERT INTO book_copies (book_id, status, code)
VALUES
    (1, 'DISPONIBLE', 'LP-001'),
    (1, 'DISPONIBLE', 'LP-002'),
    (2, 'DISPONIBLE', 'SB-001'),
    (3, 'EMPRUNTE', 'PHY-001');

-- 📋 7. Politiques de prêt

INSERT INTO loan_policies (user_role, loan_type, max_loans, loan_duration_days, max_prolongations, allow_reservation, allow_prolongation, penalty_days_per_late_day)
VALUES
    ('ETUDIANT', 'DOMICILE', 3, 14, 1, true, true, 2),
    ('PROFESSIONNEL', 'DOMICILE', 5, 21, 2, true, true, 1),
    ('BIBLIOTHECAIRE', 'DOMICILE', 10, 30, 3, true, true, 1);

-- 📅 8. Jours fériés

INSERT INTO holidays (holiday_date, name)
VALUES
    ('2025-01-01', 'Nouvel An'),
    ('2025-03-29', 'Commémoration'),
    ('2025-12-25', 'Noël');

-- 📝 9. Prêts

INSERT INTO loans (user_id, book_copy_id, start_date, due_date, loan_type, returned)
VALUES
    (2, 4, '2025-07-01', '2025-07-15', 'DOMICILE', false);

-- 📌 10. Réservations

INSERT INTO reservations (user_id, book_id, status, reservation_date, notified, active)
VALUES
    (3, 1, 'EN_ATTENTE', '2025-07-05', false, true);

-- ⚠️ 11. Pénalités

INSERT INTO penalties (user_id, start_date, end_date, days, reason, active)
VALUES
    (2, '2025-07-03', '2025-07-07', 4, 'Retard de retour', true);

-- 📚 12. Journaux d'activité

INSERT INTO activity_logs (user_id, action_type, description, timestamp)
VALUES
(2, 'EMPRUNT', 'Emprunt du livre Le Petit Prince', now()),
(3, 'RESERVATION', 'Réservation du livre Le Petit Prince', now());