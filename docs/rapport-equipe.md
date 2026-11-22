# Rapport d'équipe — Mise en place d'une chaîne DevOps

**Projet :** Gestion des étudiants (gestion-etudiants-devops)
**Équipe :** [Nom 1], [Nom 2], [Nom 3]
**Dépôt GitHub :** [URL]
**Date :** [date]

> Les parties entre crochets [ ] sont à compléter avec votre expérience réelle.

## 1. Introduction
Ce projet a pour but de construire une chaîne DevOps simple pour une application web de gestion des étudiants. Nous avons organisé le travail avec Jira, versionné le code avec Git et GitHub, automatisé les tests avec GitHub Actions, et conteneurisé l'application avec Docker. C'est notre premier projet DevOps.

## 2. Organisation de l'équipe
| Membre | Rôle principal | Contributions |
|---|---|---|
| [Nom 1] | Coordinateur / Développeur | Jira, fonctionnalité "ajouter un étudiant", README, rapport |
| [Nom 2] | Développeur / Tests | Backend Spring Boot, liste et détail, validation, tests JUnit |
| [Nom 3] | CI/CD et Docker | Dépôt GitHub, branches, `ci.yml`, Dockerfile |

Chaque membre a aussi écrit du code et participé aux revues de Pull Requests. [Préciser si vous avez échangé les rôles.]

## 3. Gestion du projet avec Jira
Nous avons créé un projet Scrum "Gestion des étudiants" avec 4 User Stories (US-01 à US-04). Chaque story a été décomposée en tâches (modèle, service, contrôleur, tests, documentation), avec un responsable et une estimation. Nous avons lancé un sprint d'une semaine. [Ajouter la capture du backlog : `docs/images/jira.png`]

## 4. Architecture
```
Développeurs
     |
Git local <----> GitHub (dépôt partagé)
     | Push / Pull Request
     v
GitHub Actions (CI)
     |-- Build et tests (Maven, JUnit)
     |-- Construction de l'image Docker
     v
Image Docker --> Test local / Staging --> Déploiement validé
```
L'application est un backend Spring Boot (Java 21) empaqueté en JAR. Les données sont stockées en mémoire. L'API expose trois routes : `GET /api/students`, `GET /api/students/{id}` et `POST /api/students`.

## 5. Choix des outils
| Outil | Pourquoi |
|---|---|
| Jira | Suivre le backlog, les tâches et les responsabilités |
| Git / GitHub | Versionner le code et collaborer avec des branches et des Pull Requests |
| Maven (wrapper) | Compiler et tester avec la même version sur tous les postes |
| JUnit | Tester automatiquement les règles métier |
| GitHub Actions | Lancer la CI à chaque push et Pull Request |
| Docker | Lancer la même application partout |

## 6. Git et Pull Requests
Nous avons utilisé `main` (stable), `develop` (intégration) et des branches `feature/...`. Aucun push direct sur `main`. Chaque fonctionnalité passe par une Pull Request relue par un autre membre. Nous avons suivi la convention de commits (`feat:`, `fix:`, `test:`, `docs:`). [Ajouter une capture d'une PR relue.]

## 7. Pipeline CI et tests
Le workflow `.github/workflows/ci.yml` se déclenche à chaque push et Pull Request vers `main` et `develop`. Il exécute `./mvnw clean verify`, puis construit l'image Docker si les tests réussissent.

**Échec volontaire :** nous avons ajouté un test qui échoue dans une branche. Le pipeline est devenu rouge. Cause : [décrire]. Après correction, le pipeline est redevenu vert. [Captures : `docs/images/pipeline-rouge.png`, `docs/images/pipeline-vert.png`]

## 8. Docker
Nous avons écrit un `Dockerfile` (image Java 21, utilisateur non-root) et un `.dockerignore`. Commandes : build du JAR, `docker build`, puis `docker run -p 8080:8080`. L'API répond sur http://localhost:8080/api/students. [Capture : `docs/images/docker.png`]

## 9. Sécurité, déploiement et monitoring
- **Sécurité :** aucun secret dans Git ; secrets prévus dans les GitHub Secrets ; conteneur non-root.
- **Déploiement :** stratégie documentée dans `docs/plan-deploiement.md` (dev, staging, production, tag SHA, validation humaine).
- **Monitoring :** trois indicateurs (disponibilité, temps de réponse, taux d'erreurs) et plan Prometheus/Grafana dans `docs/plan-monitoring.md`.

## 10. Difficultés rencontrées
- [Exemple : installation de Java 21 / mauvaise version de Java]
- [Exemple : erreur dans le pipeline, comment nous l'avons trouvée dans les logs]
- [Exemple : conflit Git]
- [Exemple : Docker ne trouvait pas le JAR]

## 11. Améliorations possibles
- Ajouter une base de données (PostgreSQL) à la place du stockage en mémoire.
- Ajouter SonarQube pour la qualité du code.
- Publier l'image dans GitHub Container Registry.
- Automatiser le déploiement en staging.
- Ajouter Prometheus et Grafana.

## 12. Conclusion
[Ce que l'équipe a appris sur le travail collaboratif et l'automatisation.]
