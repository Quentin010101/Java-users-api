-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mar. 11 avr. 2023 à 20:13
-- Version du serveur : 5.7.33
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java-users-api`
--

-- --------------------------------------------------------

--
-- Structure de la table `api_user`
--

CREATE TABLE `api_user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `date_of_birth` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `api_user`
--

INSERT INTO `api_user` (`id`, `name`, `surname`, `email`, `date_of_birth`) VALUES
(1, 'quentin', 'coz', 'quentin_c@hotmail.fr', 12345656),
(2, 'Rachel', 'Scott', 'rachel.scott73@example.com', 14634),
(3, 'Alex', 'Jones', 'alex.jones12@example.com', 4564656),
(10, 'Samantha', 'Lee', 'samantha.lee89@example.com', 14564566),
(11, 'Tyler', 'Perry', 'tyler.perry45@example.com', 15646),
(12, 'Mia', 'Rodriguez', ' mia.rodriguez27@example.com', 4542452),
(13, 'Tyler', 'Smith', 'Tyler.S@outlook.com', 12378954);

-- --------------------------------------------------------

--
-- Structure de la table `request`
--

CREATE TABLE `request` (
  `request_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `request`
--

INSERT INTO `request` (`request_id`, `user_id`, `date`) VALUES
(1, 3, '2023-04-10'),
(2, 3, '2023-04-04'),
(3, 3, '2023-04-20'),
(4, 3, '2023-04-25'),
(5, 3, '2023-04-11'),
(6, 3, '2023-04-11');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `apikey` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`, `apikey`) VALUES
(3, 'c@c.fr', '$2a$10$3oQanKZ0RHab/f9V4SUVdukV7d8OYlvEb6qgeOFYNf3SDtFcngWcK', 'Clara', '7d7eaa0a-a3b1-4ad6-b91d-f089dac6fa17'),
(4, 'j@h.fr', '$2a$10$WALp1MGt8.DL3YkEFyb3B.QWH6f6ZS63Lw9hInQc.o6fCBV4SK3Mi', 'Jhon', 'd769a0b3-d2e5-47cb-ac9f-cae7889ccc02'),
(6, 'q@gmail.com', '$2a$10$Io1fNuBA54V1oLU.JRcaw.kfQG1yhi5ilRQ4xBlW7L3F8jQQykb/u', 'Quentin', '1abd6b4b-fce0-42b0-b165-2abff227f26e'),
(7, 's@h.fr', '$2a$10$0hd.NaE8oj5kCUnHXwWUsO.j4xIJClPX9bbypKi6dCh9pjPCA7/la', 'Serge', '08f474d4-f25a-4bf7-af3f-f5ef8d3c01ee'),
(8, 'f@f.fr', '$2a$10$CmgP3PYhXrLuYTFkIyZae.Sksf4pJVwSDmq92uQpcCFRUQGRrJT.S', 'Sergu', '5f4b7cbc-333c-4e75-9d6c-bfc259c4b3d1'),
(9, 'j@g.fr', '$2a$10$Uc62.dWeG0h1pF7S.UBX6eS9kX/NP0p0uUoeQlzvTgzM5xG.J/ON6', 'James', '787332e0-ae22-48bc-982b-5d65df99cbce');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `api_user`
--
ALTER TABLE `api_user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `api_user`
--
ALTER TABLE `api_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `request`
--
ALTER TABLE `request`
  MODIFY `request_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
