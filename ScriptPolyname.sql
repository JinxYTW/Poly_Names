/*
Partie (id_partie, score, unique_code)
Joueur (id_joueur, pseudo, role, #id_partie)
Tour (id_tour, indice, word_to_find_nb, #id_partie)
Carte (id_carte, mot,position, etat,id_partie, #id_couleur, #id_mot)
Couleur (id_couleur, texte)
Dictionnaire (id_mot, texte)
*/
DROP DATABASE IF EXISTS Poly_Names;

CREATE DATABASE Poly_Names;

USE Poly_Names;

CREATE TABLE Partie (
    id_partie INT PRIMARY KEY AUTO_INCREMENT,
    score INT,
    unique_code VARCHAR(255)
);

CREATE TABLE Joueur (
    id_joueur INT PRIMARY KEY AUTO_INCREMENT,
    pseudo VARCHAR(255),
    role VARCHAR(255),
    id_partie INT
);

CREATE TABLE Tour (
    id_tour INT PRIMARY KEY AUTO_INCREMENT,
    indice VARCHAR(255),
    word_to_find_nb INT,
    id_partie INT
);

CREATE TABLE Couleur (
    id_couleur INT PRIMARY KEY AUTO_INCREMENT,
    texte VARCHAR(255)
);

CREATE TABLE Dictionnaire (
    id_mot INT PRIMARY KEY AUTO_INCREMENT,
    texte VARCHAR(255)
);

CREATE TABLE Carte (
    id_carte INT PRIMARY KEY AUTO_INCREMENT,
    id_partie INT,
    mot VARCHAR(255),
    etat BOOLEAN,
    position INT,
    id_couleur INT,
    id_mot INT
);

ALTER TABLE Joueur
ADD CONSTRAINT fk_joueur_partie
FOREIGN KEY (id_partie) REFERENCES Partie(id_partie);

ALTER TABLE Tour
ADD CONSTRAINT fk_tour_partie
FOREIGN KEY (id_partie) REFERENCES Partie(id_partie);

ALTER TABLE Carte
ADD CONSTRAINT fk_carte_couleur
FOREIGN KEY (id_couleur) REFERENCES Couleur(id_couleur);

ALTER TABLE Carte
ADD CONSTRAINT fk_carte_dictionnaire
FOREIGN KEY (id_mot) REFERENCES Dictionnaire(id_mot);

INSERT INTO Dictionnaire (texte) VALUES 
('✨Starfield✨'),
('Minecraft'),
('Tetris'),
('Pac-Man'),
('Outer Wilds'),
('Smash bros'),
('Zelda'),
('Mario Bros'),
('Mario Kart'),
('Tunic'),
('Hue'),
('Cyberpunk'),
('Overwatch'),
('Css go'),
('Battlefield'),
('Fortnite'),
('Pubg'),
('Call of duty'),
('Limbo'),
('Inside'),
('Ori'),
('Rival of aether'),
('Tekken'),
('Street fighter'),
('Gang beast'),
('Baldur''s gate'),
('Wii sports'),
('Red Dead redemption'),
('Phasmophobia'),
('Outlast'),
('GeoGuesser'),
('CodeName'),
('Celeste'),
('Fire Emblem'),
('Pokemon'),
('Persona'),
('Earthbound'),
('StarFox'),
('BorderLand'),
('MetalGearSolid'),
('Falout'),
('Dying Light'),
('Skyrim'),
('Elder Scroll'),
('Lethal Company'),
('Darks Souls'),
('Rust'),
('Sea of thieves'),
('Warhammer'),
('Wordl of Warcraft'),
('Gta'),
('Fifa'),
('League of Legends'),
('Halo'),
('Geometry Dash'),
('Final Fantasy'),
('TrackMania'),
('Rainbow Siege'),
('Genshin Impact'),
('Diablo'),
('Hades'),
('Monster Hunter'),
('Civilisation'),
('The last of us'),
('God of War'),
('The Witcher'),
('OSU'),
('Among us'),
('The Crew'),
('Assasin''s Creed'),
('Les Sims'),
('Fall guys'),
('Dofus'),
('Subnautica'),
('Mortal kombat'),
('Hogwarts Legacy'),
('No man''s Sky'),
('It takes Two'),
('Shift happens'),
('Hollow Knight'),
('BioShock'),
('Untitled Goose Game'),
('For Honor'),
('Uncharted'),
('Doom'),
('Raft'),
('Cuphead'),
('Getting over it'),
('Resident evil'),
('The exit 8'),
('Stanley parable'),
('Garry''s mod'),
('Goat Simulator'),
('World of tanks'),
('Dishonored'),
('Devil may cry'),
('Klonoa'),
('Adibou'),
('Trials Rising'),
('Parking Fury'),
('Rocket league'),
('Dead by daylight'),
('Hitman'),
('Portal'),
('Apex Legends'),
('The long dark'),
('Dishonor'),
('Elden ring'),
('7 days to die'),
('Pong'),
('Gartic phone'),
('Paper please'),
('Skribbl.io'),
('s0urce.io'),
('Galaxiga'),
('The Forest'),
('Animal Crossing'),
('Contraband Police'),
('Escape Together'),
('Star Citizen'),
('Stick fight'),
('FlakBoy'),
('Human fall flat'),
('Medal of honor'),
('❤️Antoine Daniel❤️');

INSERT INTO Couleur (texte) VALUES 
('Bleu'),
('Gris'),
('Noir');
