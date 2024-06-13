package models;


public record Partie(
    int id_partie,
    String unique_code,
    int score,
    int nb_joueur
){}