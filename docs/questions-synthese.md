# Réponses aux questions de synthèse

1. **Différence entre Git et GitHub ?**
   Git est l'outil qui garde l'historique du code sur l'ordinateur. GitHub est un service en ligne qui héberge les dépôts Git et permet de collaborer (Pull Requests, Actions).

2. **Quand le pipeline CI est-il déclenché ?**
   À chaque push sur `main` ou `develop`, et à chaque Pull Request vers ces branches (voir `on:` dans `ci.yml`).

3. **Différence entre intégration continue et déploiement continu ?**
   L'intégration continue compile et teste automatiquement le code à chaque changement. Le déploiement continu va plus loin : il met automatiquement la version validée en ligne. La livraison continue prépare la version, mais la mise en production reste une décision humaine.

4. **Pourquoi une Pull Request plutôt qu'un push direct sur `main` ?**
   La Pull Request permet une revue de code par un autre membre et l'exécution des tests avant la fusion. `main` reste stable et on garde la trace des décisions.

5. **Que se passe-t-il si les tests échouent ?**
   Le pipeline devient rouge, la Pull Request ne doit pas être fusionnée, et l'image Docker n'est pas construite (`needs: build-and-test`). On lit les logs, on corrige, et on repousse.

6. **Différence entre une image Docker et un conteneur ?**
   L'image est le modèle figé (application + environnement). Le conteneur est une instance en cours d'exécution de cette image. On peut lancer plusieurs conteneurs depuis la même image.

7. **Pourquoi séparer développement, staging et production ?**
   Pour tester sans risque. Le développement sert à essayer, le staging à valider une version proche de la production, et la production reste protégée pour les vrais utilisateurs.

8. **Où stocker les secrets du pipeline ?**
   Dans les GitHub Secrets (Settings > Secrets and variables > Actions), jamais dans le code, le YAML, le Dockerfile ou Git.

9. **Quels contrôles supplémentaires pour sécuriser la chaîne ?**
   Analyse de qualité et de vulnérabilités (SonarQube), analyse des images Docker, protection de la branche `main`, revues obligatoires, permissions minimales du pipeline, version fixe de l'image de base, utilisateur non-root, et mise à jour régulière des dépendances.

10. **Quels indicateurs montrent que l'application fonctionne bien ?**
    La disponibilité, le temps de réponse et le taux d'erreurs. On regarde aussi les journaux et l'état du conteneur (`docker ps`).
