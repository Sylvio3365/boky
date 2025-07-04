# Gestion d'une Bibliothèque

---

## Fonctionnalité 1 : Prêter un livre

- **Acteur principal** : Bibliothécaire
- **Entrées** :
  - ID de l’adhérent
  - ID de l’exemplaire
  - Type de prêt (sur place ou à domicile)

### Scénario nominal

Le bibliothécaire sélectionne un adhérent et un exemplaire disponible pour effectuer le prêt.

### Règles de gestion

1. L’adhérent existe dans la base.
2. L’adhérent est abonné.
3. L’adhérent est actif.
4. L’adhérent n’est pas sanctionné.
5. L’exemplaire est disponible.
6. Le type de prêt est autorisé pour ce livre.
7. Le livre est adapté à l’âge de l’adhérent (ex. : un livre +18 est interdit aux mineurs).
8. Le nombre de prêts à domicile en cours ne dépasse pas le quota autorisé selon le profil de l’adhérent.

### Scénarios alternatifs

- Adhérent inexistant
- Adhérent non abonné
- Adhérent inactif
- Adhérent sanctionné
- Exemplaire indisponible
- Type de prêt non autorisé
- Âge non conforme
- Quota dépassé

### Résultat attendu

- Le prêt est enregistré avec succès.
- Un message d’erreur est affiché si une règle n’est pas respectée.

---

## Fonctionnalité 2 : Prolonger un prêt

- **Acteurs** :
  - Adhérent (demande)
  - Bibliothécaire (valide ou rejette)
- **Entrée** : ID du prêt à prolonger

### Scénario nominal

L’adhérent demande une prolongation. Le bibliothécaire décide de la valider ou non.

### Règles de gestion

1. L’adhérent n’est pas sanctionné.
2. Le nombre de prolongations en cours n’excède pas le maximum autorisé selon le profil.

### Scénarios alternatifs

- Adhérent sanctionné
- Nombre de prolongations dépassé

### Résultat attendu

- Prolongation enregistrée
- Prolongation refusée si une règle est violée

---

## Fonctionnalité 3 : Rendre un livre

- **Acteur** : Bibliothécaire

### Scénario nominal

L’adhérent restitue le livre et le bibliothécaire enregistre le retour.

### Règles de gestion

1. Si la date de retour est postérieure à la date de fin du prêt :
   - L’adhérent est sanctionné pour une durée définie selon son profil.
2. Si la date de fin du prêt tombe un jour férié ou un week-end :
   - Un paramètre du système détermine si le retour est attendu avant ou après ce jour.
   - Si le livre est rendu après le jour ouvrable suivant, l’adhérent est sanctionné.

### Résultat attendu

- Le retour est enregistré.
- Une sanction est appliquée en cas de retard injustifié.

---

## Fonctionnalité 4 : Réserver un livre

- **Acteurs** :
  - Adhérent (demande)
  - Bibliothécaire (valide ou rejette, puis transforme en prêt)

### Scénario nominal

1. L’adhérent consulte les exemplaires disponibles.
2. Il choisit un exemplaire et une date de réservation.
3. Le bibliothécaire valide ou rejette la réservation.
4. À la date prévue, la réservation est transformée en prêt par le bibliothécaire.

### Règles de gestion

- Les mêmes règles qu’un prêt classique doivent être respectées (profil valide, quota, non sanctionné, etc.).

### Résultat attendu

- Réservation validée et transformée automatiquement en prêt si conforme.
- Rejetée si une règle n’est pas respectée.

---
