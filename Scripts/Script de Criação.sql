/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


CREATE DATABASE IF NOT EXISTS `tcc_engsoft` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tcc_engsoft`;

CREATE TABLE estado (
  EstId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  EstNome VARCHAR(40) NULL,
  EstUf VARCHAR(2) NULL,
  CONSTRAINT pk_estado PRIMARY KEY(EstId)
);

CREATE TABLE cidade (
  CidId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  EstId INTEGER UNSIGNED NOT NULL,
  CidNome VARCHAR(50) NULL,
  CONSTRAINT pk_cidade PRIMARY KEY(CidId),
  CONSTRAINT fk_cidade_estado FOREIGN KEY (EstId) REFERENCES estado (EstId)
);

CREATE TABLE area_conhecimento (
  AreaId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  AreaNome VARCHAR(50) NULL,
  CONSTRAINT pk_area_conhecimento PRIMARY KEY(AreaId)
);

CREATE TABLE conquista_config (
  ConConfId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ConConfTitulo VARCHAR(50) NULL,
  ConConfDesc VARCHAR(255) NULL,
  CONSTRAINT pk_conquista_config PRIMARY KEY(ConConfId)
);

CREATE TABLE curso (
  CurId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CurNome VARCHAR(50) NULL,
  CurDataCad DATETIME NULL,
  CONSTRAINT pk_curso PRIMARY KEY(CurId)
);

CREATE TABLE questao (
  QuesId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  AreaId INTEGER UNSIGNED NOT NULL,
  QuesDesc VARCHAR(1024) NULL,
  QuesAtiva INTEGER UNSIGNED NULL,
  QuesDataCad DATETIME NULL,
  CONSTRAINT pk_questao PRIMARY KEY(QuesId),
  CONSTRAINT fk_questao_area_conhecimento FOREIGN KEY (AreaId) REFERENCES area_conhecimento (AreaId)
);

CREATE TABLE alternativa (
  AltId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  QuesId INTEGER UNSIGNED NOT NULL,
  AltDesc VARCHAR(512) NULL,
  AltCorreta INTEGER UNSIGNED NOT NULL,
  CONSTRAINT pk_alternativa PRIMARY KEY(AltId),
  CONSTRAINT fk_alternativa_questao FOREIGN KEY (QuesId) REFERENCES questao (QuesId)
);

CREATE TABLE universidade (
  UniId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CidId INTEGER UNSIGNED NOT NULL,
  UniNome VARCHAR(100) NULL,
  UniDataCad DATETIME NULL,
  CONSTRAINT pk_universidade PRIMARY KEY(UniId),
  CONSTRAINT fk_universidade_cidade FOREIGN KEY (CidId) REFERENCES cidade (CidId)
);

CREATE TABLE universidade_curso (
  UniCurId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CurId INTEGER UNSIGNED NOT NULL,
  UniId INTEGER UNSIGNED NOT NULL,
  CONSTRAINT pk_universidade_curso PRIMARY KEY(UniCurId),
  CONSTRAINT fk_universidade FOREIGN KEY (UniId) REFERENCES universidade (UniId),
  CONSTRAINT fk_universidade_curso FOREIGN KEY (CurId) REFERENCES curso (CurId)
);

CREATE TABLE usuario (
  UsuId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CurId INTEGER UNSIGNED NULL,
  UniId INTEGER UNSIGNED NULL,
  CidId INTEGER UNSIGNED NULL,
  UsuNome VARCHAR(80) NULL,
  UsuEmail VARCHAR(40) NULL,
  UsuSenha VARCHAR(50) NULL,
  UsuDireito VARCHAR(15) NULL,
  UsuAtivo INTEGER UNSIGNED NULL,
  UsuDataCad DATETIME NULL,
  CONSTRAINT pk_usuario PRIMARY KEY(UsuId),
  CONSTRAINT fk_usuario_cidade FOREIGN KEY (CidId) REFERENCES cidade (CidId),
  CONSTRAINT fk_usuario_universidade FOREIGN KEY (UniId) REFERENCES universidade (UniId),
  CONSTRAINT fk_usuario_curso FOREIGN KEY (CurId) REFERENCES curso (CurId)
);

CREATE TABLE avaliacao (
  AvaId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  UsuId INTEGER UNSIGNED NOT NULL,
  AvaNota DOUBLE NULL,
  AvaMsg VARCHAR(1024) NULL,
  AvaData DATETIME NULL,  
  CONSTRAINT pk_avaliacao PRIMARY KEY(AvaId),
  CONSTRAINT fk_avaliacao_usuario FOREIGN KEY (UsuId) REFERENCES usuario (UsuId)
);

CREATE TABLE conquista (
  ConId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ConConfId INTEGER UNSIGNED NOT NULL,
  UsuId INTEGER UNSIGNED NOT NULL,
  ConData DATETIME NULL,  
  CONSTRAINT pk_conquista PRIMARY KEY(ConId),
  CONSTRAINT fk_conquista_usuario FOREIGN KEY (UsuId) REFERENCES usuario (UsuId),
  CONSTRAINT fk_conquista_conf FOREIGN KEY (ConConfId) REFERENCES conquista_config (ConConfId)
);

CREATE TABLE resposta (
  ResId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  AltId INTEGER UNSIGNED NOT NULL,
  UsuId INTEGER UNSIGNED NOT NULL,
  ResPontos DOUBLE NULL,
  ResData DATETIME NULL,  
  CONSTRAINT pk_resposta PRIMARY KEY(ResId),
  CONSTRAINT fk_resposta_usuario FOREIGN KEY (UsuId) REFERENCES usuario (UsuId),
  CONSTRAINT fk_resposta_alternativa FOREIGN KEY (AltId) REFERENCES alternativa (AltId)
);
