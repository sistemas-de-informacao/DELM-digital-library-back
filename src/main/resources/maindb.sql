drop database delm_digital_db;
create database delm_digital_db;
use delm_digital_db;
CREATE TABLE `tb_usuario` (
   `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
   `NICK_USUARIO` varchar(50) NOT NULL,
   `NOME_USUARIO` varchar(120) NOT NULL,
   `EMAIL_USUARIO` varchar(150) NOT NULL,
   `SENHA_USUARIO` varchar(70) NOT NULL,
   `SALDO_USUARIO` double DEFAULT NULL,
   `DATA_CRIACAO_USUARIO` varchar(20) NOT NULL,
   `TIPO_CONTA_USUARIO` BIT(1) not null,
   `ENABLE_USUARIO` BIT(1) NOT NULL,
   PRIMARY KEY (`ID_USUARIO`)
 );
CREATE TABLE `tb_categoria` (
   `ID_CATEGORIA` int(11) NOT NULL AUTO_INCREMENT,
   `NOME_CATEGORIA` varchar(120) NOT NULL,
   PRIMARY KEY (`ID_CATEGORIA`)
 );
 CREATE TABLE `tb_jogo` (
   `ID_JOGO` int(11) NOT NULL AUTO_INCREMENT,
   `NOME_JOGO` varchar(120) NOT NULL,
   `PRECO_JOGO` double NOT NULL,
   `DATA_LANCAMENTO_JOGO` VARCHAR(20) NOT NULL,
   `DESENVOLVEDOR_JOGO` varchar(45) DEFAULT NULL,
   `DESCRICAO_JOGO` varchar(500) DEFAULT NULL,
   `ID_TB_CATEGORIA` int(11) NOT NULL,
   `FULL_PATH` varchar(300),   
   `ENABLE` BIT(1) NOT NULL,
   PRIMARY KEY (`ID_JOGO`),
   KEY `ID_TB_CATEGORIA` (`ID_TB_CATEGORIA`),
   CONSTRAINT `ID_TB_CATEGORIA` FOREIGN KEY (`ID_TB_CATEGORIA`) REFERENCES `tb_categoria` (`ID_CATEGORIA`)
 );
CREATE TABLE `tb_compra` (
  `ID_TB_COMPRA` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO_COMPRA` int(11) NOT NULL,
  `DATA_COMPRA` varchar(20) NOT NULL,
  `TOTAL_COMPRA` double NOT NULL,
  `ID_TB_USUARIO` int(11) NOT NULL,
  PRIMARY KEY (`ID_TB_COMPRA`),
  KEY `ID_TB_USUARIO` (`ID_TB_USUARIO`)
);
CREATE TABLE `tb_jogo_compra` (
  `ID_TB_JOGO` int(11) not null,
  `ID_TB_COMPRA` int(11) not null,
  `PRECO_JOGO` double NOT NULL,
  FOREIGN KEY (`ID_TB_JOGO`) REFERENCES `tb_jogo` (`ID_JOGO`),
  FOREIGN KEY (`ID_TB_COMPRA`) REFERENCES `TB_COMPRA` (`id_tb_compra`)
);
CREATE TABLE `tb_biblioteca` (
  `ID_TB_JOGO` int(11) not null,
  `ID_TB_USUARIO` int(11) NOT NULL,
  FOREIGN KEY (`ID_TB_JOGO`) REFERENCES `tb_jogo` (`ID_JOGO`),
  FOREIGN KEY (`ID_TB_USUARIO`) REFERENCES `tb_usuario` (`ID_USUARIO`)
);


