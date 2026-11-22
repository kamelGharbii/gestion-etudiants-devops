# Plan de monitoring

## 1. Trois indicateurs simples
| Indicateur | Question | Exemple d'objectif |
|---|---|---|
| Disponibilité | L'application répond-elle ? | 99 % du temps |
| Temps de réponse | Est-elle rapide ? | moins de 500 ms pour `GET /api/students` |
| Taux d'erreurs | Combien de requêtes échouent ? | moins de 1 % de réponses 5xx |

## 2. Journaux utiles
- Journaux de l'application (`docker logs <conteneur>`) : erreurs et exceptions.
- Journaux du pipeline GitHub Actions : cause des échecs de build.
- Requêtes en erreur (codes 400, 404, 500).

## 3. Outils (présentation)
- **Prometheus** collecte les métriques de l'application à intervalles réguliers.
- **Grafana** affiche ces métriques sous forme de tableaux de bord.
- Avec Spring Boot, on peut exposer les métriques via Actuator et Micrometer.

## 4. Architecture proposée
Application Spring Boot (Actuator) → Prometheus (collecte) → Grafana (tableaux de bord + alertes)

## 5. Alerte proposée
Alerte si l'application ne répond pas pendant plus de 3 vérifications consécutives (environ 3 minutes) : notification à l'équipe par e-mail ou message.

## 6. Réaction à un incident
1. Détecter (alerte ou utilisateur).
2. Consulter les journaux et identifier la cause.
3. Revenir à la version précédente si besoin (retour arrière).
4. Corriger dans une branche, tester, et livrer.
5. Documenter : ce qui s'est passé, la cause, et comment éviter de le refaire.
