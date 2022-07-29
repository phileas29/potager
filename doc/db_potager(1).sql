-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 29 juil. 2022 à 07:20
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_potager`
--

-- --------------------------------------------------------

--
-- Structure de la table `carre`
--

DROP TABLE IF EXISTS `carre`;
CREATE TABLE IF NOT EXISTS `carre` (
  `id_carre` int(11) NOT NULL,
  `surface` int(11) NOT NULL,
  `type_expo` int(11) DEFAULT NULL,
  `type_sol` int(11) DEFAULT NULL,
  `potager_id_potager` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_carre`),
  KEY `FK59onqyrgyb74s7co55oe2xxui` (`potager_id_potager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `carre`
--

INSERT INTO `carre` (`id_carre`, `surface`, `type_expo`, `type_sol`, `potager_id_potager`) VALUES
(10, 5838, 1, 4, 3),
(11, 5802, 0, 2, 3),
(12, 2541, 1, 4, 3),
(13, 3372, 0, 0, 3),
(14, 4480, 1, 0, 3),
(15, 4239, 0, 5, 3),
(16, 2865, 0, 5, 3),
(17, 5125, 0, 2, 3),
(18, 5988, 1, 2, 4),
(19, 3924, 0, 1, 4),
(20, 6451, 0, 1, 4),
(21, 9615, 2, 1, 4),
(22, 6371, 2, 2, 4),
(23, 1135, 1, 3, 4),
(24, 4778, 2, 1, 4),
(25, 1483, 2, 1, 4),
(26, 9000, 1, 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(28);

-- --------------------------------------------------------

--
-- Structure de la table `plantation`
--

DROP TABLE IF EXISTS `plantation`;
CREATE TABLE IF NOT EXISTS `plantation` (
  `id_plantation` int(11) NOT NULL,
  `date_plantation` datetime(6) DEFAULT NULL,
  `date_recolte` datetime(6) DEFAULT NULL,
  `qte` int(11) DEFAULT NULL,
  `carre_id_carre` int(11) DEFAULT NULL,
  `plante_id_plante` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_plantation`),
  KEY `FKgrsmpeytlkq2enia6jtu456v2` (`carre_id_carre`),
  KEY `FK96q2uoe083h3qgo4cowd7gygk` (`plante_id_plante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `plantation`
--

INSERT INTO `plantation` (`id_plantation`, `date_plantation`, `date_recolte`, `qte`, `carre_id_carre`, `plante_id_plante`) VALUES
(27, NULL, NULL, 2, 10, 5);

-- --------------------------------------------------------

--
-- Structure de la table `plante`
--

DROP TABLE IF EXISTS `plante`;
CREATE TABLE IF NOT EXISTS `plante` (
  `id_plante` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `surface` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `variete` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_plante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `plante`
--

INSERT INTO `plante` (`id_plante`, `nom`, `surface`, `type`, `variete`) VALUES
(5, 'blette', 50, 1, ''),
(6, 'tomate', 75, 2, 'coeur de boeuf'),
(7, 'radis', 25, 0, 'noir'),
(8, 'ï¿½pinard', 20, 1, 'gï¿½ant d\'hiver'),
(9, 'carotte', 22, 0, 'chantenay');

-- --------------------------------------------------------

--
-- Structure de la table `potager`
--

DROP TABLE IF EXISTS `potager`;
CREATE TABLE IF NOT EXISTS `potager` (
  `id_potager` int(11) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `surface` int(11) NOT NULL,
  `ville` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_potager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `potager`
--

INSERT INTO `potager` (`id_potager`, `location`, `nom`, `surface`, `ville`) VALUES
(1, 'Toul ar C\'Hoat', 'Clément\'s Meadows', 800000, 'Le Faou 2'),
(3, 'Lesquidic', 'Jardins PhilÃ©asiens', 250000, 'Gouesnac\'h'),
(4, 'Toul ar C\'Hoat', 'ClÃ©ment\'s Meadows', 800000, 'Le Faou');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carre`
--
ALTER TABLE `carre`
  ADD CONSTRAINT `FK59onqyrgyb74s7co55oe2xxui` FOREIGN KEY (`potager_id_potager`) REFERENCES `potager` (`id_potager`);

--
-- Contraintes pour la table `plantation`
--
ALTER TABLE `plantation`
  ADD CONSTRAINT `FK96q2uoe083h3qgo4cowd7gygk` FOREIGN KEY (`plante_id_plante`) REFERENCES `plante` (`id_plante`),
  ADD CONSTRAINT `FKgrsmpeytlkq2enia6jtu456v2` FOREIGN KEY (`carre_id_carre`) REFERENCES `carre` (`id_carre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
