# gestion-etudiants-devops

API REST Spring Boot de gestion des étudiants. Tout le code est dans `backend/`.

## Lancer les tests
```bash
cd backend
.\mvnw test        # Windows
./mvnw test        # Linux / macOS
```

## Endpoints

| Méthode | URL | User story | Réponses |
|---|---|---|---|
| GET | `/api/students` | US-01 Consulter les étudiants | 200 |
| POST | `/api/students` | US-02 Ajouter un étudiant | 201, 400 |
| GET | `/api/students/{id}` | US-03 Consulter un étudiant par son ID | 200, 404 |
| POST (validation) | `/api/students` | US-04 Valider les données | 400 + détails |

## Validation des données (US-04)

Lors de l'ajout d'un étudiant (`POST /api/students`), les règles suivantes sont contrôlées :

| Champ | Règle | Message |
|-------|-------|---------|
| `nom` | obligatoire (non vide) | Le nom est obligatoire |
| `prenom` | obligatoire (non vide) | Le prénom est obligatoire |
| `email` | obligatoire | L'e-mail est obligatoire |
| `email` | format valide | L'e-mail est invalide |

Si une règle n'est pas respectée, l'API répond **400 Bad Request** :
```json
{
  "status": 400,
  "erreur": "Données invalides",
  "details": { "nom": "Le nom est obligatoire" }
}
```
Les tests sont dans `StudentValidationTest` et `StudentServiceTest`.
