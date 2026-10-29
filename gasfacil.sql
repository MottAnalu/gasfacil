CREATE DATABASE IF NOT EXISTS gasfacil CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gasfacil;

CREATE TABLE IF NOT EXISTS beneficiario (
 id_beneficiario INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(120) NOT NULL,
 cpf VARCHAR(14) NOT NULL UNIQUE,
 telefone VARCHAR(20),
 endereco VARCHAR(180),
 renda_familiar DECIMAL(10,2) DEFAULT 0,
 status VARCHAR(20) NOT NULL DEFAULT 'ATIVO'
);

CREATE TABLE IF NOT EXISTS botijao (
 id_botijao INT AUTO_INCREMENT PRIMARY KEY,
 codigo VARCHAR(30) NOT NULL UNIQUE,
 tipo VARCHAR(50) NOT NULL,
 valor DECIMAL(10,2) DEFAULT 0,
 estoque INT NOT NULL DEFAULT 0,
 status VARCHAR(20) NOT NULL DEFAULT 'DISPONIVEL'
);

CREATE TABLE IF NOT EXISTS retirada (
 id_retirada INT AUTO_INCREMENT PRIMARY KEY,
 id_beneficiario INT NOT NULL,
 id_botijao INT NOT NULL,
 data_retirada DATE NOT NULL,
 quantidade INT NOT NULL,
 status VARCHAR(20) NOT NULL DEFAULT 'CONCLUIDA',
 FOREIGN KEY (id_beneficiario) REFERENCES beneficiario(id_beneficiario),
 FOREIGN KEY (id_botijao) REFERENCES botijao(id_botijao)
);
