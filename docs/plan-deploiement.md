# Plan de déploiement (CD)

## 1. Environnements
| Environnement | But | Règle |
|---|---|---|
| Développement | Développer et intégrer les fonctionnalités | Données fictives, changements fréquents |
| Staging | Valider une version proche de la production | Déploiement après CI réussie, tests de recette |
| Production | Service pour les utilisateurs | Approbation humaine, sauvegarde, retour arrière |

## 2. Stratégie de livraison
1. Un développeur ouvre une Pull Request vers `develop`.
2. GitHub Actions compile et exécute les tests (`./mvnw clean verify`).
3. Si les tests réussissent, l'image Docker est construite.
4. L'image est taguée avec le SHA du commit (exemple : `gestion-etudiants:3f2a9c1`) pour retrouver la version exacte.
5. L'image est publiée dans un registre (GitHub Container Registry) — étape documentée, non obligatoire pour ce TP.
6. Déploiement en staging, puis un test de fumée : `GET /api/students` doit répondre 200.
7. Mise en production seulement après validation humaine (un membre de l'équipe approuve).

## 3. Retour arrière
Si la nouvelle version a un problème, on relance l'image précédente grâce à son tag (SHA ou numéro de version).

## 4. Secrets et sécurité
- Identifiants de déploiement, jetons et mots de passe : uniquement dans les **GitHub Secrets**, avec des permissions minimales.
- Jamais de secret dans le code, le Dockerfile ou l'image.
- Pas de données personnelles réelles dans un environnement de démonstration.
- Le conteneur s'exécute avec un utilisateur non-root.

## 5. État dans ce TP
Le déploiement automatique complet dépend d'un serveur cible. Pour ce TP : pipeline CI, image Docker construite, conteneur lancé en local, et procédure ci-dessus documentée.
