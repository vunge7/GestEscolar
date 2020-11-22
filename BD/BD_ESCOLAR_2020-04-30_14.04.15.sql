-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema gestescolardb
--

CREATE DATABASE IF NOT EXISTS gestescolardb;
USE gestescolardb;

--
-- Definition of table `gestescolardb`.`ano_lectivo`
--

DROP TABLE IF EXISTS `gestescolardb`.`ano_lectivo`;
CREATE TABLE  `gestescolardb`.`ano_lectivo` (
  `pk_ano_lectivo` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_ano_lectivo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`ano_lectivo`
--

/*!40000 ALTER TABLE `ano_lectivo` DISABLE KEYS */;
LOCK TABLES `ano_lectivo` WRITE;
INSERT INTO `gestescolardb`.`ano_lectivo` VALUES  (1,'2020');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ano_lectivo` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`classe`
--

DROP TABLE IF EXISTS `gestescolardb`.`classe`;
CREATE TABLE  `gestescolardb`.`classe` (
  `pk_classe` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_classe`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`classe`
--

/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
LOCK TABLES `classe` WRITE;
INSERT INTO `gestescolardb`.`classe` VALUES  (1,'10ª CLASSE'),
 (2,'11ª CLASSE'),
 (3,'12ª CLASSE'),
 (4,'13ª CLASSE');
UNLOCK TABLES;
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`confirmacao_matricula`
--

DROP TABLE IF EXISTS `gestescolardb`.`confirmacao_matricula`;
CREATE TABLE  `gestescolardb`.`confirmacao_matricula` (
  `pk_confirmacao_matricula` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `fk_estudante` int(11) NOT NULL,
  `fk_turma` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `fk_preco_confirmacao` int(11) NOT NULL,
  `fk_tipo_confirmacao` int(11) NOT NULL,
  PRIMARY KEY (`pk_confirmacao_matricula`) USING BTREE,
  KEY `fk_confirmacao_matricula_estudante1_idx` (`fk_estudante`),
  KEY `fk_confirmacao_matricula_turma1_idx` (`fk_turma`),
  KEY `fk_confirmacao_matricula_usuario1_idx` (`fk_usuario`),
  KEY `fk_confirmacao_matricula_preco_confirmacao1_idx` (`fk_preco_confirmacao`),
  KEY `fk_confirmacao_matricula_tipo_confirmacao1_idx` (`fk_tipo_confirmacao`),
  CONSTRAINT `fk_confirmacao_matricula_estudante1` FOREIGN KEY (`fk_estudante`) REFERENCES `estudante` (`pk_estudante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_confirmacao_matricula_preco_confirmacao1` FOREIGN KEY (`fk_preco_confirmacao`) REFERENCES `preco_confirmacao` (`pk_preco_confirmacao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_confirmacao_matricula_tipo_confirmacao1` FOREIGN KEY (`fk_tipo_confirmacao`) REFERENCES `tipo_confirmacao` (`pk_tipo_confirmacao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_confirmacao_matricula_turma1` FOREIGN KEY (`fk_turma`) REFERENCES `turma` (`pk_turma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_confirmacao_matricula_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`confirmacao_matricula`
--

/*!40000 ALTER TABLE `confirmacao_matricula` DISABLE KEYS */;
LOCK TABLES `confirmacao_matricula` WRITE;
INSERT INTO `gestescolardb`.`confirmacao_matricula` VALUES  (1,'2020-01-19','11:20:26',1,1,1,1,1),
 (2,'2020-01-19','11:24:05',2,1,1,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `confirmacao_matricula` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`curso`
--

DROP TABLE IF EXISTS `gestescolardb`.`curso`;
CREATE TABLE  `gestescolardb`.`curso` (
  `pk_curso` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`curso`
--

/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
LOCK TABLES `curso` WRITE;
INSERT INTO `gestescolardb`.`curso` VALUES  (1,'CIÊNCIAS FÍSICAS E BIOLÓGICAS'),
 (2,'CIÊNCIA ENCONÓMICAS E JURÍDICAS');
UNLOCK TABLES;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`disciplina`
--

DROP TABLE IF EXISTS `gestescolardb`.`disciplina`;
CREATE TABLE  `gestescolardb`.`disciplina` (
  `pk_disciplina` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`disciplina`
--

/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
LOCK TABLES `disciplina` WRITE;
INSERT INTO `gestescolardb`.`disciplina` VALUES  (1,'Matemática'),
 (2,'Língua Portuguesa');
UNLOCK TABLES;
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`encarregado`
--

DROP TABLE IF EXISTS `gestescolardb`.`encarregado`;
CREATE TABLE  `gestescolardb`.`encarregado` (
  `pk_encarregado` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCompleto` varchar(100) DEFAULT NULL,
  `localTrabalho` varchar(100) DEFAULT NULL,
  `residencia` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `fk_profissao` int(11) NOT NULL,
  PRIMARY KEY (`pk_encarregado`),
  KEY `fk_encarregado_profissao1_idx` (`fk_profissao`),
  CONSTRAINT `fk_encarregado_profissao1` FOREIGN KEY (`fk_profissao`) REFERENCES `profissao` (`pk_profissao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`encarregado`
--

/*!40000 ALTER TABLE `encarregado` DISABLE KEYS */;
LOCK TABLES `encarregado` WRITE;
INSERT INTO `gestescolardb`.`encarregado` VALUES  (1,'Fasutino Hélio','sdgasdg','sadgasdgs','990537124',3),
 (2,'Margarida','sdgasdg','sadgasdgs','83883838',3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `encarregado` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`estudante`
--

DROP TABLE IF EXISTS `gestescolardb`.`estudante`;
CREATE TABLE  `gestescolardb`.`estudante` (
  `pk_estudante` int(11) NOT NULL AUTO_INCREMENT,
  `nome_completo` varchar(250) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `nbi` varchar(100) DEFAULT NULL,
  `foto` blob,
  `nomePai` varchar(100) DEFAULT NULL,
  `nomeMae` varchar(100) DEFAULT NULL,
  `dataValidadeBI` date DEFAULT NULL,
  `naturalidade` varchar(100) DEFAULT NULL,
  `doenca` varchar(100) DEFAULT NULL,
  `numero_matricula` varchar(50) DEFAULT NULL,
  `fk_sexo` int(11) NOT NULL,
  `fk_encarregado` int(11) NOT NULL,
  `fk_municipio` int(11) NOT NULL,
  PRIMARY KEY (`pk_estudante`),
  KEY `fk_sexo_estudande` (`fk_sexo`),
  KEY `fk_estudante_encarregado1_idx` (`fk_encarregado`),
  KEY `fk_estudante_municipio1_idx` (`fk_municipio`),
  CONSTRAINT `fk_estudante_encarregado1` FOREIGN KEY (`fk_encarregado`) REFERENCES `encarregado` (`pk_encarregado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_estudante_municipio1` FOREIGN KEY (`fk_municipio`) REFERENCES `municipio` (`pkmunicipio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sexo_estudande` FOREIGN KEY (`fk_sexo`) REFERENCES `sexo` (`pk_sexo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`estudante`
--

/*!40000 ALTER TABLE `estudante` DISABLE KEYS */;
LOCK TABLES `estudante` WRITE;
INSERT INTO `gestescolardb`.`estudante` VALUES  (1,'Aurélio','2020-01-19','234234231','sdfgdsfgdgs','asdgasdg','asdgsadg',NULL,'Paulo Cassoma','asdfbasdasdb','2020-01-19','sgsadg','Não','567',1,1,1),
 (2,'Paula Ester','2020-01-19','373773','sdfgdsfgdgs','asdgasdg','3663',NULL,'Adelina','asdfbasdasdb','2020-01-19','sgsadg','Sim','76',2,2,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `estudante` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`item_turma_professor_disciplina`
--

DROP TABLE IF EXISTS `gestescolardb`.`item_turma_professor_disciplina`;
CREATE TABLE  `gestescolardb`.`item_turma_professor_disciplina` (
  `pk_professor_disciplina` int(11) NOT NULL AUTO_INCREMENT,
  `fk_turma` int(11) NOT NULL,
  `fk_professor` int(11) NOT NULL,
  `fk_disciplina` int(11) NOT NULL,
  PRIMARY KEY (`pk_professor_disciplina`),
  KEY `fk_item_turma_professor_disciplina_turma1_idx` (`fk_turma`),
  KEY `fk_item_turma_professor_disciplina_professor1_idx` (`fk_professor`),
  KEY `fk_item_turma_professor_disciplina_disciplina1_idx` (`fk_disciplina`),
  CONSTRAINT `fk_item_turma_professor_disciplina_disciplina1` FOREIGN KEY (`fk_disciplina`) REFERENCES `disciplina` (`pk_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_turma_professor_disciplina_professor1` FOREIGN KEY (`fk_professor`) REFERENCES `professor` (`pk_professor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_turma_professor_disciplina_turma1` FOREIGN KEY (`fk_turma`) REFERENCES `turma` (`pk_turma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gestescolardb`.`item_turma_professor_disciplina`
--

/*!40000 ALTER TABLE `item_turma_professor_disciplina` DISABLE KEYS */;
LOCK TABLES `item_turma_professor_disciplina` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `item_turma_professor_disciplina` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`mes`
--

DROP TABLE IF EXISTS `gestescolardb`.`mes`;
CREATE TABLE  `gestescolardb`.`mes` (
  `pk_mes` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_mes`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`mes`
--

/*!40000 ALTER TABLE `mes` DISABLE KEYS */;
LOCK TABLES `mes` WRITE;
INSERT INTO `gestescolardb`.`mes` VALUES  (1,'Fevereiro'),
 (2,'Março'),
 (3,'Abril'),
 (4,'Maio'),
 (5,'Junho'),
 (6,'Julho'),
 (7,'Agosto'),
 (8,'Setembro'),
 (9,'Outrubro'),
 (10,'Novembro'),
 (11,'Dezembro');
UNLOCK TABLES;
/*!40000 ALTER TABLE `mes` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`municipio`
--

DROP TABLE IF EXISTS `gestescolardb`.`municipio`;
CREATE TABLE  `gestescolardb`.`municipio` (
  `pkmunicipio` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(200) DEFAULT NULL,
  `fk_provincia` int(11) NOT NULL,
  PRIMARY KEY (`pkmunicipio`),
  KEY `fk_municipio_provincia1_idx` (`fk_provincia`),
  CONSTRAINT `fk_municipio_provincia1` FOREIGN KEY (`fk_provincia`) REFERENCES `provincia` (`pk_provincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`municipio`
--

/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
LOCK TABLES `municipio` WRITE;
INSERT INTO `gestescolardb`.`municipio` VALUES  (1,'Belas',1),
 (2,'Luanda',1),
 (3,'Kilamba Kiaxi',1),
 (4,'Lobito',2),
 (5,'Benguela',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`nota`
--

DROP TABLE IF EXISTS `gestescolardb`.`nota`;
CREATE TABLE  `gestescolardb`.`nota` (
  `pk_nota` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `fk_trimestre` int(11) NOT NULL,
  `fk_disciplina` int(11) NOT NULL,
  `fk_ano_lectivo` int(11) NOT NULL,
  `fk_estudante` int(11) NOT NULL,
  `fk_tipo_nota` int(11) NOT NULL,
  `fk_professor` int(11) NOT NULL,
  PRIMARY KEY (`pk_nota`),
  KEY `fk_nota_disciplina1_idx` (`fk_disciplina`),
  KEY `fk_nota_ano_lectivo1_idx` (`fk_ano_lectivo`),
  KEY `fk_nota_estudante1_idx` (`fk_estudante`),
  KEY `FK_tipo_nota` (`fk_tipo_nota`),
  KEY `fk_nota_semestre1_idx` (`fk_trimestre`),
  KEY `fk_nota_table11_idx` (`fk_professor`),
  CONSTRAINT `FK_tipo_nota` FOREIGN KEY (`fk_tipo_nota`) REFERENCES `tipo_nota` (`pk_tipo_nota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_ano_lectivo1` FOREIGN KEY (`fk_ano_lectivo`) REFERENCES `ano_lectivo` (`pk_ano_lectivo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_disciplina1` FOREIGN KEY (`fk_disciplina`) REFERENCES `disciplina` (`pk_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_estudante1` FOREIGN KEY (`fk_estudante`) REFERENCES `estudante` (`pk_estudante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_semestre1` FOREIGN KEY (`fk_trimestre`) REFERENCES `trimestre` (`pk_semestre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_table11` FOREIGN KEY (`fk_professor`) REFERENCES `professor` (`pk_professor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`nota`
--

/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
LOCK TABLES `nota` WRITE;
INSERT INTO `gestescolardb`.`nota` VALUES  (1,15,NULL,NULL,1,1,1,1,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`pais`
--

DROP TABLE IF EXISTS `gestescolardb`.`pais`;
CREATE TABLE  `gestescolardb`.`pais` (
  `pk_pais` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`pais`
--

/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
LOCK TABLES `pais` WRITE;
INSERT INTO `gestescolardb`.`pais` VALUES  (1,'Angola');
UNLOCK TABLES;
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`preco_confirmacao`
--

DROP TABLE IF EXISTS `gestescolardb`.`preco_confirmacao`;
CREATE TABLE  `gestescolardb`.`preco_confirmacao` (
  `pk_preco_confirmacao` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `fk_curso` int(11) NOT NULL,
  `fk_tipo_confirmacao` int(11) NOT NULL,
  PRIMARY KEY (`pk_preco_confirmacao`),
  KEY `fk_preco_confirmacao_curso1_idx` (`fk_curso`),
  KEY `fk_preco_confirmacao_tipo_confirmacao1_idx` (`fk_tipo_confirmacao`),
  CONSTRAINT `fk_preco_confirmacao_curso1` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`pk_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preco_confirmacao_tipo_confirmacao1` FOREIGN KEY (`fk_tipo_confirmacao`) REFERENCES `tipo_confirmacao` (`pk_tipo_confirmacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`preco_confirmacao`
--

/*!40000 ALTER TABLE `preco_confirmacao` DISABLE KEYS */;
LOCK TABLES `preco_confirmacao` WRITE;
INSERT INTO `gestescolardb`.`preco_confirmacao` VALUES  (1,2500,'2020-01-19','11:06:35',1,1),
 (2,2000,'2020-01-19','11:06:35',1,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `preco_confirmacao` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`preco_propina`
--

DROP TABLE IF EXISTS `gestescolardb`.`preco_propina`;
CREATE TABLE  `gestescolardb`.`preco_propina` (
  `pk_preco_propina` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `fk_usuario` int(11) NOT NULL,
  `fk_curso` int(11) NOT NULL,
  `fk_classe` int(11) NOT NULL,
  PRIMARY KEY (`pk_preco_propina`),
  KEY `fk_preco_propina_usuario1_idx` (`fk_usuario`),
  KEY `fk_preco_propina_curso1_idx` (`fk_curso`),
  KEY `fk_preco_propina_classe1_idx` (`fk_classe`),
  CONSTRAINT `fk_preco_propina_classe1` FOREIGN KEY (`fk_classe`) REFERENCES `classe` (`pk_classe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preco_propina_curso1` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`pk_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preco_propina_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`preco_propina`
--

/*!40000 ALTER TABLE `preco_propina` DISABLE KEYS */;
LOCK TABLES `preco_propina` WRITE;
INSERT INTO `gestescolardb`.`preco_propina` VALUES  (1,5000,'2020-01-19','11:06:35',1,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `preco_propina` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`professor`
--

DROP TABLE IF EXISTS `gestescolardb`.`professor`;
CREATE TABLE  `gestescolardb`.`professor` (
  `pk_professor` int(11) NOT NULL AUTO_INCREMENT,
  `nome_complero` varchar(100) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_professor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gestescolardb`.`professor`
--

/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
LOCK TABLES `professor` WRITE;
INSERT INTO `gestescolardb`.`professor` VALUES  (1,'Tiago João','923456789',NULL,NULL,'tiago','1234');
UNLOCK TABLES;
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`profissao`
--

DROP TABLE IF EXISTS `gestescolardb`.`profissao`;
CREATE TABLE  `gestescolardb`.`profissao` (
  `pk_profissao` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_profissao`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`profissao`
--

/*!40000 ALTER TABLE `profissao` DISABLE KEYS */;
LOCK TABLES `profissao` WRITE;
INSERT INTO `gestescolardb`.`profissao` VALUES  (2,'Professor'),
 (3,'Engenheiro'),
 (4,'Médico');
UNLOCK TABLES;
/*!40000 ALTER TABLE `profissao` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`propina`
--

DROP TABLE IF EXISTS `gestescolardb`.`propina`;
CREATE TABLE  `gestescolardb`.`propina` (
  `pk_propina` int(11) NOT NULL AUTO_INCREMENT,
  `fk_recibo_propina` int(11) NOT NULL,
  `fk_mes` int(11) NOT NULL,
  `fk_preco_propina` int(11) NOT NULL,
  `desconto` double DEFAULT NULL,
  `multa` double DEFAULT NULL,
  PRIMARY KEY (`pk_propina`),
  KEY `fk_propina_recibo_propina1_idx` (`fk_recibo_propina`),
  KEY `fk_propina_mes1_idx` (`fk_mes`),
  KEY `fk_propina_preco_propina1_idx` (`fk_preco_propina`),
  CONSTRAINT `fk_propina_mes1` FOREIGN KEY (`fk_mes`) REFERENCES `mes` (`pk_mes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_propina_preco_propina1` FOREIGN KEY (`fk_preco_propina`) REFERENCES `preco_propina` (`pk_preco_propina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_propina_recibo_propina1` FOREIGN KEY (`fk_recibo_propina`) REFERENCES `recibo_propina` (`pk_recibo_propina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`propina`
--

/*!40000 ALTER TABLE `propina` DISABLE KEYS */;
LOCK TABLES `propina` WRITE;
INSERT INTO `gestescolardb`.`propina` VALUES  (1,1,1,1,NULL,NULL),
 (2,2,2,1,NULL,NULL),
 (3,3,1,1,NULL,NULL),
 (4,3,2,1,NULL,NULL),
 (5,3,3,1,NULL,NULL),
 (6,4,4,1,NULL,NULL),
 (7,5,4,1,NULL,NULL),
 (8,5,5,1,NULL,NULL),
 (9,5,6,1,NULL,NULL),
 (10,6,3,1,NULL,NULL),
 (11,6,4,1,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `propina` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`provincia`
--

DROP TABLE IF EXISTS `gestescolardb`.`provincia`;
CREATE TABLE  `gestescolardb`.`provincia` (
  `pk_provincia` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(200) DEFAULT NULL,
  `fk_pais` int(11) NOT NULL,
  PRIMARY KEY (`pk_provincia`),
  KEY `fk_provincia_pais1_idx` (`fk_pais`),
  CONSTRAINT `fk_provincia_pais1` FOREIGN KEY (`fk_pais`) REFERENCES `pais` (`pk_pais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`provincia`
--

/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
LOCK TABLES `provincia` WRITE;
INSERT INTO `gestescolardb`.`provincia` VALUES  (1,'Luanda',1),
 (2,'Benguela',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`recibo_propina`
--

DROP TABLE IF EXISTS `gestescolardb`.`recibo_propina`;
CREATE TABLE  `gestescolardb`.`recibo_propina` (
  `pk_recibo_propina` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fk_estudante` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `multa` double DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `troco` double DEFAULT NULL,
  `obs` varchar(200) DEFAULT NULL,
  `num_bolderoux` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pk_recibo_propina`),
  KEY `fk_recibo_propina_estudante1_idx` (`fk_estudante`),
  KEY `fk_recibo_propina_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_recibo_propina_estudante1` FOREIGN KEY (`fk_estudante`) REFERENCES `estudante` (`pk_estudante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recibo_propina_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pk_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`recibo_propina`
--

/*!40000 ALTER TABLE `recibo_propina` DISABLE KEYS */;
LOCK TABLES `recibo_propina` WRITE;
INSERT INTO `gestescolardb`.`recibo_propina` VALUES  (1,'2020-01-19','11:34:36',5000,2,1,0,0,1000,'edsgsdgf','66767'),
 (2,'2020-01-19','12:19:25',5000,2,1,0,0,0,'',''),
 (3,'2020-02-02','16:59:38',15000,1,1,0,0,0,'xxxx',NULL),
 (4,'2020-04-30','13:21:20',5000,1,4,0,0,0,'',NULL),
 (5,'2020-04-30','13:21:36',15000,1,4,0,0,0,'',NULL),
 (6,'2020-04-30','13:23:59',10000,2,4,0,0,0,'Teste.',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `recibo_propina` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`sala`
--

DROP TABLE IF EXISTS `gestescolardb`.`sala`;
CREATE TABLE  `gestescolardb`.`sala` (
  `pk_sala` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`sala`
--

/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
LOCK TABLES `sala` WRITE;
INSERT INTO `gestescolardb`.`sala` VALUES  (1,'1'),
 (2,'2'),
 (3,'3'),
 (4,'4');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`sexo`
--

DROP TABLE IF EXISTS `gestescolardb`.`sexo`;
CREATE TABLE  `gestescolardb`.`sexo` (
  `pk_sexo` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`sexo`
--

/*!40000 ALTER TABLE `sexo` DISABLE KEYS */;
LOCK TABLES `sexo` WRITE;
INSERT INTO `gestescolardb`.`sexo` VALUES  (1,'Masculino'),
 (2,'Feminino');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`tipo_confirmacao`
--

DROP TABLE IF EXISTS `gestescolardb`.`tipo_confirmacao`;
CREATE TABLE  `gestescolardb`.`tipo_confirmacao` (
  `pk_tipo_confirmacao` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_tipo_confirmacao`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`tipo_confirmacao`
--

/*!40000 ALTER TABLE `tipo_confirmacao` DISABLE KEYS */;
LOCK TABLES `tipo_confirmacao` WRITE;
INSERT INTO `gestescolardb`.`tipo_confirmacao` VALUES  (1,'MATRICULA'),
 (2,'CONFIRMAÇÃO');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tipo_confirmacao` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`tipo_nota`
--

DROP TABLE IF EXISTS `gestescolardb`.`tipo_nota`;
CREATE TABLE  `gestescolardb`.`tipo_nota` (
  `pk_tipo_nota` int(11) NOT NULL,
  `designacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_tipo_nota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`tipo_nota`
--

/*!40000 ALTER TABLE `tipo_nota` DISABLE KEYS */;
LOCK TABLES `tipo_nota` WRITE;
INSERT INTO `gestescolardb`.`tipo_nota` VALUES  (1,'MAC'),
 (2,'CPP'),
 (3,'CAP'),
 (4,'CPE/CE');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tipo_nota` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`trimestre`
--

DROP TABLE IF EXISTS `gestescolardb`.`trimestre`;
CREATE TABLE  `gestescolardb`.`trimestre` (
  `pk_semestre` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_semestre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`trimestre`
--

/*!40000 ALTER TABLE `trimestre` DISABLE KEYS */;
LOCK TABLES `trimestre` WRITE;
INSERT INTO `gestescolardb`.`trimestre` VALUES  (1,'Iº Ttrimestre'),
 (2,'IIº Trimestre'),
 (3,'IIIº Trimestre');
UNLOCK TABLES;
/*!40000 ALTER TABLE `trimestre` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`turma`
--

DROP TABLE IF EXISTS `gestescolardb`.`turma`;
CREATE TABLE  `gestescolardb`.`turma` (
  `pk_turma` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  `fk_sala` int(11) NOT NULL,
  `fk_turno` int(11) NOT NULL,
  `fk_classe` int(11) NOT NULL,
  `fk_ano_lectivo` int(11) NOT NULL,
  `fk_curso` int(11) NOT NULL,
  PRIMARY KEY (`pk_turma`),
  KEY `fk_turma_sala_idx` (`fk_sala`),
  KEY `fk_turma_turno1_idx` (`fk_turno`),
  KEY `fk_turma_classe1_idx` (`fk_classe`),
  KEY `fk_turma_ano_lectivo1_idx` (`fk_ano_lectivo`),
  KEY `fk_turma_curso1_idx` (`fk_curso`),
  CONSTRAINT `fk_turma_ano_lectivo1` FOREIGN KEY (`fk_ano_lectivo`) REFERENCES `ano_lectivo` (`pk_ano_lectivo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_classe1` FOREIGN KEY (`fk_classe`) REFERENCES `classe` (`pk_classe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_curso1` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`pk_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_sala` FOREIGN KEY (`fk_sala`) REFERENCES `sala` (`pk_sala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_turno1` FOREIGN KEY (`fk_turno`) REFERENCES `turno` (`pk_turno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`turma`
--

/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
LOCK TABLES `turma` WRITE;
INSERT INTO `gestescolardb`.`turma` VALUES  (1,'A10/2020',1,1,1,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`turno`
--

DROP TABLE IF EXISTS `gestescolardb`.`turno`;
CREATE TABLE  `gestescolardb`.`turno` (
  `pk_turno` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_turno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`turno`
--

/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
LOCK TABLES `turno` WRITE;
INSERT INTO `gestescolardb`.`turno` VALUES  (1,'MANHÃ'),
 (2,'TARDE'),
 (3,'NOITE');
UNLOCK TABLES;
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;


--
-- Definition of table `gestescolardb`.`usuario`
--

DROP TABLE IF EXISTS `gestescolardb`.`usuario`;
CREATE TABLE  `gestescolardb`.`usuario` (
  `pk_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_completo` varchar(100) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  `senha` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gestescolardb`.`usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
LOCK TABLES `usuario` WRITE;
INSERT INTO `gestescolardb`.`usuario` VALUES  (1,'Nécia Norais Caculo','Administrador1','Eng90x?'),
 (4,'Domingos Dala Vunge','dallas','dallas');
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
