CREATE DATABASE txt;
USE txt;

CREATE TABLE Usuario (
id_usuario BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_usuario VARCHAR (25) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
login VARCHAR(20) NOT NULL UNIQUE,
senha VARCHAR(345) NOT NULL,
data_nascimento VARCHAR(10),
url_foto VARCHAR(2083),
bio VARCHAR(255),
genero VARCHAR(20),
telefone VARCHAR(20) UNIQUE,
url_site VARCHAR(2083)
);

CREATE TABLE Postagem (
id_postagem BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
nome_postagem VARCHAR(25) NOT NULL,
categoria VARCHAR(20),
texto_postagem LONGTEXT NOT NULL,
id_usuario BIGINT NOT NULL,
data_postagem VARCHAR(10),
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE Diario (
id_diario BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
texto_diario VARCHAR(10000) NOT NULL,
data_diario VARCHAR(10),
id_usuario BIGINT NOT NULL,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE Curtida (
id_curtida BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_usuario BIGINT NOT NULL,
id_postagem BIGINT NOT NULL,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario),
FOREIGN KEY(id_postagem) REFERENCES Postagem (id_postagem)
);



CREATE TABLE Seguidores (
id_usuario_seguidor BIGINT NOT NULL,
id_usuario_seguido BIGINT NOT NULL,
id BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
status_seguidores BOOLEAN,
FOREIGN KEY(id_usuario_seguidor) REFERENCES Usuario (id_usuario),
FOREIGN KEY(id_usuario_seguido) REFERENCES Usuario (id_usuario)
);

CREATE TABLE Comentario (
id_comentario BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
data_comentario VARCHAR(10),
texto_comentario VARCHAR(255),
id_usuario BIGINT NOT NULL,
id_postagem BIGINT NOT NULL,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario),
FOREIGN KEY(id_postagem) REFERENCES Postagem (id_postagem)
);

CREATE TABLE Avaliacao (
id_avaliacao BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_usuario BIGINT NOT NULL,
id_postagem BIGINT NOT NULL,
nota DOUBLE,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario),
FOREIGN KEY(id_postagem) REFERENCES Postagem (id_postagem)
);