# Gestion d'un bibliotheque

## Fonctionnalite :

1. Titre : Preter un livre 

acteur : bibliothecaire 

input : id adherant , id exemplaire , type pret (lecture sur place ou a la maison)

scenario nominale : on choisit quel adehrent va preter l'exemplaire

regle de gestion : 
-adherent doit exister
-adhrent abonner 
-adherent doi etre actif
-exemplaire doi etre en disponible
-si le livre de lexemplaire correpond au type de pret
-si le livre de lexemplaire correpond au age de l'adehrent (par exemple si le livre est + 18 ans l'adherent qui na pas plus de 18 ans ne peut pas)
-nombre de prete actuel de ladhrent doit etre <= nb qutoa de ladherent 

scenario alternative :
