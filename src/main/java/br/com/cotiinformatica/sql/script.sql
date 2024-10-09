CREATE DATABASE bd_javaapiclientes;

USE bd_javaapiclientes;

CREATE TABLE cliente(

id 					CHAR(36) 			PRIMARY KEY,
nome 				VARCHAR(150) 		NOT NULL,
email 				VARCHAR(50) 		NOT NULL,
telefone 			VARCHAR(20) 		NOT NULL,
dataHoraCadastro	DATETIME       		NOT NULL

);

DESC cliente;