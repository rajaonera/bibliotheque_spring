# 📚 Projet : Gestion de Bibliothèque

## 🎯 Objectif

Développer une application web permettant la **gestion complète des livres, des emprunts, des adhérents, des pénalités et des réservations** dans une bibliothèque, avec des règles spécifiques selon les profils utilisateurs.

---

## 👨‍💻 Équipe de développement

- Développeur 1 : [Nom] – Backend, sécurité, logique métier
- Développeur 2 : [Nom] – Interface, tests, services métiers

---

## 🛠️ Technologies utilisées

| Côté | Technologies |
|------|--------------|
| Backend | Java 17, Spring Boot 3, Spring Data JPA |
| Sécurité | Spring Security (JWT ou session-based) |
| Base de données | PostgreSQL |
| Interface | HTML / CSS / JS (ou Thymeleaf) |
| Tests | Postman, JUnit |
| Outils | Maven, Git, Lombok |

---

## 🧩 Modules principaux

- **Gestion des livres** : Ajout, modification, suppression, recherche
- **Gestion des utilisateurs** : Profils avec rôles (`Étudiant`, `Professeur`, `Professionnel`, `Anonyme`)
- **Emprunt & retour** : Contrôle des durées et des quantités selon le profile
- **Pénalités** : Application automatique des jours de blocage en cas de retard
- **Réservations** : Système de réservation avec notification console/email
- **Jours fériés** : Calculs ajustés pour les retours
- **Import CSV** : Importation des livres et adhérents (optionnel)
- **Statistiques** : Livres les plus empruntés, utilisateurs actifs (optionnel)

---

## 🧱 Structure des entités (JPA)

- `User` : id, nom, email, mot de passe, profile, liste des prêts
- `Role` : id, nom (`ADMIN`, `USER`)
- `Book` : id, titre, auteur, ISBN, langue, etc.
- `BookCopy` : id, statut (`DISPONIBLE`, `EMPRUNTÉ`, `RÉSERVÉ`), référence `Book`
- `Loan` : utilisateur, exemplaire, date de prêt, date retour, prolongation
- `Reservation` : utilisateur, exemplaire, date de réservation
- `Penalty` : utilisateur, jours de pénalité, date début/fin
- `HolidayCalendar` : date, nom du jour férié

---

## ⚖️ Règles de gestion

### 🔐 Profils utilisateurs
- Étudiant : 3 livres / 7 jours / 1 prolongation
- Professeur : 5 livres / 14 jours / 2 prolongations
- Professionnel : 2 livres / 7 jours / pas de prolongation
- Anonyme : Consultation sur place uniquement

### 📖 Emprunt & retour
- Vérification du nombre de livres en cours
- Refus si utilisateur pénalisé ou si livre déjà emprunté
- Retour en retard = pénalité automatique (1 jour de retard = X jours bloqués)
- Prise en compte des jours fériés pour ajuster les dates

### 📅 Réservations
- Possible uniquement si tous les exemplaires sont empruntés
- Notification console ou email à la disponibilité
- Expiration après 48h si non récupéré

---

## 🖥️ Fonctionnalités livrées

- [x] Authentification simple avec rôles
- [x] CRUD Livres
- [x] Prêt / Retour
- [x] Réservation avec expiration
- [x] Pénalités automatiques
- [x] Interface simple (HTML/Postman)
- [x] Import CSV (si le temps le permet)
- [ ] Statistiques (optionnel)

---

## 🗃️ Organisation du code

src/
├── controller/
├── entity/
├── repository/
├── service/
├── security/ (facultatif)
└── resources/
├── templates/
├── static/

yaml
Copier le code

---

## 🧪 Tests réalisés

| Fonction | Testé via |
|----------|------------|
| Authentification | Postman |
| Emprunt | API REST |
| Retour & pénalité | API REST |
| Réservation | API REST |
| Interface | HTML ou Swagger UI |

---

## 📸 Captures d'écran (optionnel)

*(Inclure ici des captures d’écrans de la base de données, de l’interface ou de Postman)*

---

## 📄 Rapport technique (résumé)

- Projet réalisé en **3 jours** par une équipe de **2 développeurs**
- Respect du cahier des charges minimum
- Application prête pour démo locale
- Améliorations possibles : emails réels, interface complète, déploiement

---

## 🚀 Lancer le projet

```bash
# 1. Cloner le dépôt
git clone https://github.com/ton-nom/projet-bibliotheque.git

# 2. Lancer PostgreSQL et créer la DB
CREATE DATABASE bibliotheque;

# 3. Configurer application.properties

# 4. Lancer l'application
./mvnw spring-boot:run

``` 
👀 Auteurs
[Nom 1]

[Nom 2]