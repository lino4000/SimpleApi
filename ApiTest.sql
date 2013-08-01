-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 31/07/2013 às 21h24min
-- Versão do Servidor: 5.5.32
-- Versão do PHP: 5.3.10-1ubuntu3.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Banco de Dados: `simpleapi`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `user` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `acl` tinyint(4) DEFAULT '2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `test`
--

INSERT INTO `test` (`id`, `user`, `pass`, `name`, `email`, `acl`) VALUES
(1, 'lino4000', MD5(`api123`), 'Henrique Lino Pacheco', 'lino4000@yahoo.com.br', 1),
(2, 'second', MD5(`apitest`), 'The Second User', 'second@test.com', 2);