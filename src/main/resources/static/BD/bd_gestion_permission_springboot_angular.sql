-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 29 déc. 2023 à 01:57
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_gestion_permission_springboot_angular`
--

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

CREATE TABLE `demandes` (
  `id_permission` bigint(20) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_demande` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `duree_permission` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `type_permission` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `demandes`
--

INSERT INTO `demandes` (`id_permission`, `date_debut`, `date_demande`, `date_fin`, `duree_permission`, `libelle`, `type_permission`) VALUES
(1, '2023-12-30', '2023-12-29', '2023-12-30', '24H', 'Demande de permission de 24h', 'T1'),
(2, '2023-12-30', '2023-12-29', '2023-12-31', '48H', 'Demande de permission de 24h', 'T1'),
(3, '2027-12-29', '2027-12-28', '2027-12-31', '72H mm', 'Demande de permission de 72h mm', 'T1 mm'),
(4, '2024-01-02', '2023-12-29', '2024-01-07', '6 jours', 'Demande de permission de 6 jours', 'T2');

-- --------------------------------------------------------

--
-- Structure de la table `permissions`
--

CREATE TABLE `permissions` (
  `id_permission` bigint(20) NOT NULL,
  `date_permission` date DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `personnels`
--

CREATE TABLE `personnels` (
  `id_personnel` bigint(20) NOT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `service_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id_role` bigint(20) NOT NULL,
  `desc_role` varchar(100) DEFAULT NULL,
  `nom_role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id_role`, `desc_role`, `nom_role`) VALUES
(1, 'Admin', 'Administrateur'),
(2, 'Secretariat Particulier du CSA', 'SP CSA'),
(3, 'Chef de poste', 'Chef de Poste'),
(4, 'Role 01', 'ROLE 1'),
(5, 'Role 2 mm', 'ROLE 2 mmm'),
(6, 'Role 03', 'ROLE 3'),
(8, 'Role 8', 'ROLE 8'),
(9, 'user MM', 'User mm');

-- --------------------------------------------------------

--
-- Structure de la table `services`
--

CREATE TABLE `services` (
  `id_service` bigint(20) NOT NULL,
  `contact_service` varchar(30) DEFAULT NULL,
  `desc_service` varchar(100) DEFAULT NULL,
  `nom_service` varchar(50) NOT NULL,
  `sigle_service` varchar(20) DEFAULT NULL,
  `service_parent_id` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `services`
--

INSERT INTO `services` (`id_service`, `contact_service`, `desc_service`, `nom_service`, `sigle_service`, `service_parent_id`) VALUES
(1, '77 529 00 37', 'Secretariat particulier du commissaire special de l\'AIBD', 'SP CSA', 'SP CSA', NULL),
(2, '77 529 02 77', 'Premiere brigade d\'arraisonnement du commissaire special de l\'AIBD', 'Brigade 1', 'B1', NULL),
(3, '77 529 02 78', 'Deuxieme brigade d\'arraisonnement du commissariat special de l\'AIBD', 'Brigade 2', 'B2', NULL),
(4, '77 529 02 79', 'Troisieme brigade d\'arraisonnement du commissariat special de l\'AIBD', 'Brigade 3', 'B3', NULL),
(5, '77 529 15 54', 'Brigade Special du commissariat special de l\'AIBD', 'Brigade Special', 'BS', NULL),
(6, 'NON RENSEIGNE', 'Centre de Monitoring et de Profiling du commissariat special de l\'AIBD', 'CMP/AIBD', 'CMP/AIBD', NULL),
(7, 'NON RENSEIGNE', 'Service 7', 'SERVICE 7', 'S7', NULL),
(8, 'NON RENSEIGNE mm', 'Service 8 mm', 'SERVICE 8 mm', 'S8 mm', NULL),
(9, 'NON RENSEIGNE', 'Service 9', 'SERVICE 9', 'S9', NULL),
(10, 'NON RENSEIGNE', 'Service 10', 'SERVICE 10', 'S10', NULL),
(11, 'NON RENSEIGNE', 'Service 11', 'SERVICE 11', 'S11', NULL),
(12, 'NON RENSEIGNE', 'Service 12', 'SERVICE 12', 'S12', NULL),
(14, 'NON RENSEIGNE', 'Service 14', 'SERVICE 14', 'S14', NULL),
(15, '77 000 00 15', 'service 15', 'SERVICE 15', 'S 15', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `type_permissions`
--

CREATE TABLE `type_permissions` (
  `id_type_permission` bigint(20) NOT NULL,
  `nom_type_permission` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id_user` bigint(20) NOT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id_user`, `contact`, `nom`, `password`, `prenom`, `username`, `role_id`, `service_id`) VALUES
(1, '77 973 73 33', 'DIALLO', 'password', 'Abdoulaye', 'admin', 1, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD PRIMARY KEY (`id_permission`);

--
-- Index pour la table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id_permission`),
  ADD KEY `FK2vnmjh5vw8m96emb2x1web77p` (`user_id`);

--
-- Index pour la table `personnels`
--
ALTER TABLE `personnels`
  ADD PRIMARY KEY (`id_personnel`),
  ADD KEY `FK22b1arskt8jydoy3640qmfbdn` (`service_id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_role`);

--
-- Index pour la table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id_service`);

--
-- Index pour la table `type_permissions`
--
ALTER TABLE `type_permissions`
  ADD PRIMARY KEY (`id_type_permission`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  ADD KEY `FKg28emhyfqgy7bu8nv5ol805wt` (`service_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `demandes`
--
ALTER TABLE `demandes`
  MODIFY `id_permission` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id_permission` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `personnels`
--
ALTER TABLE `personnels`
  MODIFY `id_personnel` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id_role` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `services`
--
ALTER TABLE `services`
  MODIFY `id_service` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `type_permissions`
--
ALTER TABLE `type_permissions`
  MODIFY `id_type_permission` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `permissions`
--
ALTER TABLE `permissions`
  ADD CONSTRAINT `FK2vnmjh5vw8m96emb2x1web77p` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`);

--
-- Contraintes pour la table `personnels`
--
ALTER TABLE `personnels`
  ADD CONSTRAINT `FK22b1arskt8jydoy3640qmfbdn` FOREIGN KEY (`service_id`) REFERENCES `services` (`id_service`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKg28emhyfqgy7bu8nv5ol805wt` FOREIGN KEY (`service_id`) REFERENCES `services` (`id_service`),
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
