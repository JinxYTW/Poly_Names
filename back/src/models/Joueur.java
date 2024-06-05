package models;


public record Joueur(
    int id_joueur,
    String pseudo,
    String role,
    int id_partie
){}