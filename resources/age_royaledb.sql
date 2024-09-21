DROP TABLE IF EXISTS Usuari;
DROP TABLE IF EXISTS Partida;
DROP TABLE IF EXISTS Moviment;

CREATE TABLE Usuari (
nom VARCHAR(255),
correu VARCHAR (255) UNIQUE,
password VARCHAR (255),
total_partides INT,
guanyades INT,

PRIMARY KEY(nom)
);

CREATE TABLE Partida (
nom_partida VARCHAR(255),
usuari VARCHAR (255),
data DATE,
user_guanyador BOOLEAN,

PRIMARY KEY(nom_partida, usuari),
FOREIGN KEY(usuari) REFERENCES Usuari(nom)
);

CREATE TABLE Moviment (
id INT AUTO_INCREMENT,
nom_partida VARCHAR(255),
temps DOUBLE,
casella_x INT,
casella_y INT,
fitxa VARCHAR (255),
usuari BOOLEAN,

PRIMARY KEY(id, nom_partida),
FOREIGN KEY(nom_partida) REFERENCES Partida(nom_partida)
);
