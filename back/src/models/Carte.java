package models;


public record Carte(
    int id_carte,
    String mot,
    Boolean etat,
    int position,
    int id_couleur,
    int id_mot,
    int id_partie
){}