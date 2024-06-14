# PolyNames
Voici le projet projet de développement Web proposé par l'enseignant Charles Meunier de Polytech Dijon.
Le but de celui-ci était de recréer le célèbre jeu "Codenames" avec quelques règles différentes afin de simplifier la mise en place.


# Architecture des dossiers

Ce projet utilise l'architecture MVC pour le frontend. Pour ce qui est du backend, API et SSE ont été utilisées.

## BDD et Modèle relationnel

Notre bdd a été dessiné à l'aide du site drawio (cf poly_names.drawio).
Pour ce qui est du modèle relationel, il se retrouve écrit en commentaire dans le script, ainsi que dans ce qui suit :

>Partie (id_partie, score,nb_joueur, unique_code)

>Joueur (id_joueur, pseudo, role, #id_partie)

>Tour (id_tour,tour, indice, word_to_find_nb,word_to_guess, #id_partie)

>Carte (id_carte, mot,position, etat,id_partie, #id_couleur, #id_mot)

>Couleur (id_couleur, texte)

>Dictionnaire (id_mot, texte)

## Règle du jeu 

A chaque tour, on comptabilise les points obtenus pour chaque carte bleue découverte. La première carte vaut 1 point, la seconde 2 points, la troisième 3 points, ... 
Si le Maître des intuitions découvre une carte bleue en N+1, cette carte vaut N² points.
 Lorsqu'une carte grise est découverte, le tour s'arrête et les points obtenus sont ajoutés au score. Les cartes grises n'apportent aucun point. Si une carte noire est découverte, la partie est perdue et le score de l'équipe tombe à 0

## Travail non réalisé

Malheureusement, le LocalStorage n'a pas été implémenté, **évitez donc de recharger la page**.


