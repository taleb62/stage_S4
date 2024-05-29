-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 29 mai 2024 à 15:25
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.1.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_passation`
--

-- --------------------------------------------------------

--
-- Structure de la table `approle`
--

CREATE TABLE `approle` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `approle`
--

INSERT INTO `approle` (`id`, `description`, `role_name`) VALUES
(1, 'saisie des mouvements', 'SAISIE'),
(2, 'Gestion des utilisateurs', 'ADMINISTRATEUR'),
(3, 'Valide la saisie des utilisateurs', 'VALIDATION');

-- --------------------------------------------------------

--
-- Structure de la table `appuser`
--

CREATE TABLE `appuser` (
  `id` int(11) NOT NULL,
  `actived` bit(1) NOT NULL,
  `confirmed_password` varchar(255) DEFAULT NULL,
  `fonction` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appuser`
--

INSERT INTO `appuser` (`id`, `actived`, `confirmed_password`, `fonction`, `nom`, `password`, `prenom`, `username`) VALUES
(4, b'1', NULL, 'Administrateur', 'admin1', '$2a$10$Mkr5mo8ASjYg3QsSbRPageUkq44GTv6uoev7cNuXgEnVjYcgYfEkO', 'admine2', 'admin'),
(5, b'0', NULL, 'admin', 'saa', '$2a$10$YBqr88XkkbzrUOtsm/.qJOY70XczOp2FJeQ/gVjcxedIA0nPJDxEa', 'tourad', 'saadna'),
(6, b'1', NULL, 'admin', 'nour', '$2a$10$Sth5sNLSDPwhHD9en2SYQuv1GSPFyjr5pBntn8gNA2PyJMi4WfO4O', 'sidi', 'nour');

-- --------------------------------------------------------

--
-- Structure de la table `appuser_roles`
--

CREATE TABLE `appuser_roles` (
  `app_user_id` int(11) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appuser_roles`
--

INSERT INTO `appuser_roles` (`app_user_id`, `roles_id`) VALUES
(4, 2),
(4, 1),
(4, 3),
(5, 2),
(6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

CREATE TABLE `dossier` (
  `id` int(11) NOT NULL,
  `fk_iduser` int(11) NOT NULL,
  `id_paa` int(11) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `lettre_cree` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `dossier`
--

INSERT INTO `dossier` (`id`, `fk_iduser`, `id_paa`, `reference`, `lettre_cree`) VALUES
(19, 4, 1, '1/2024', b'1');

-- --------------------------------------------------------

--
-- Structure de la table `files`
--

CREATE TABLE `files` (
  `id` int(11) NOT NULL,
  `dt_upload` datetime DEFAULT NULL,
  `file_name_on_disc` varchar(255) DEFAULT NULL,
  `file_sub_folder` varchar(255) DEFAULT NULL,
  `fk_iduser` int(11) DEFAULT NULL,
  `id_elm` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fk_id_tbl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `files`
--

INSERT INTO `files` (`id`, `dt_upload`, `file_name_on_disc`, `file_sub_folder`, `fk_iduser`, `id_elm`, `name`, `fk_id_tbl`) VALUES
(41, '2024-05-27 13:38:29', 'doc_2024_05_27_13_38_29_90449.pdf', '2024\\05\\', 4, 1, 'cv minetou.pdf', 1),
(42, '2024-05-27 13:42:54', 'doc_2024_05_27_13_42_54_23369.pdf', '2024\\05\\', 4, 19, 'doc_2024_05_27_13_38_29_90449.pdf', 2);

-- --------------------------------------------------------

--
-- Structure de la table `ged_storage`
--

CREATE TABLE `ged_storage` (
  `id` int(11) NOT NULL,
  `root_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ged_table`
--

CREATE TABLE `ged_table` (
  `id_tbl` int(11) NOT NULL,
  `code_tbl` varchar(145) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ged_table`
--

INSERT INTO `ged_table` (`id_tbl`, `code_tbl`) VALUES
(1, 'EXPRESSION_BESOIN'),
(2, 'INITIATION_PROCEDURE');

-- --------------------------------------------------------

--
-- Structure de la table `lettre`
--

CREATE TABLE `lettre` (
  `id` int(11) NOT NULL,
  `id_dossier` int(255) DEFAULT NULL,
  `lieu_overture_plis` varchar(255) DEFAULT NULL,
  `date_anterieur_depot` datetime DEFAULT NULL,
  `date_limite_depot` datetime DEFAULT NULL,
  `date_overture_plis` datetime DEFAULT NULL,
  `fk_iduser` int(11) NOT NULL,
  `nom_fournissuer` varchar(255) DEFAULT NULL,
  `non_autorite_contractante` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lettre`
--

INSERT INTO `lettre` (`id`, `id_dossier`, `lieu_overture_plis`, `date_anterieur_depot`, `date_limite_depot`, `date_overture_plis`, `fk_iduser`, `nom_fournissuer`, `non_autorite_contractante`) VALUES
(48, 19, 'CABINET', '2024-06-12 13:45:00', '2024-05-31 13:44:00', '2024-06-01 13:44:00', 4, 'MOHAMED', 'MF'),
(49, 19, 'CABINET', '2024-06-12 13:45:00', '2024-05-31 13:44:00', '2024-06-01 13:44:00', 4, 'SIDI', 'MF'),
(50, 19, 'CABINET', '2024-06-12 13:45:00', '2024-05-31 13:44:00', '2024-06-01 13:44:00', 4, 'ABDOULAHI', 'MF');

-- --------------------------------------------------------

--
-- Structure de la table `mod_passation`
--

CREATE TABLE `mod_passation` (
  `id` int(11) NOT NULL,
  `mode_passation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mod_passation`
--

INSERT INTO `mod_passation` (`id`, `mode_passation`) VALUES
(1, 'CONSULTATION CANDIDATS'),
(2, 'ENTENTE DIRECT');

-- --------------------------------------------------------

--
-- Structure de la table `plan_anuell_achat`
--

CREATE TABLE `plan_anuell_achat` (
  `id` int(11) NOT NULL,
  `date_cration_procedure` datetime DEFAULT NULL,
  `date_previ_attribution` date DEFAULT NULL,
  `date_previ_lancement` date DEFAULT NULL,
  `destinataire` varchar(255) DEFAULT NULL,
  `dosssier_cree` bit(1) NOT NULL,
  `enprocedure` bit(1) NOT NULL,
  `fkid_mod_passation` int(11) DEFAULT NULL,
  `fkid_type_marche` int(11) DEFAULT NULL,
  `inpu_budgetaire` varchar(255) DEFAULT NULL,
  `mnt_estimatif` double DEFAULT NULL,
  `objet_depense` varchar(255) DEFAULT NULL,
  `origine` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `plan_anuell_achat`
--

INSERT INTO `plan_anuell_achat` (`id`, `date_cration_procedure`, `date_previ_attribution`, `date_previ_lancement`, `destinataire`, `dosssier_cree`, `enprocedure`, `fkid_mod_passation`, `fkid_type_marche`, `inpu_budgetaire`, `mnt_estimatif`, `objet_depense`, `origine`) VALUES
(1, '2023-01-27 00:00:00', '2023-01-29', '2023-01-31', 'MF', b'1', b'1', 1, 2, '67-01-41-2-1-6-06', 100000, 'acquisition des titre sécurise', 'MFHHHH'),
(2, '2023-02-09 02:09:00', '2023-03-01', '2023-02-11', '', b'0', b'0', 2, 2, '2022-1-67-09-11-21-1-05', 200000, 'Fourniture habillement et accessoire au profit du personnel de la douane', '');

-- --------------------------------------------------------

--
-- Structure de la table `plis`
--

CREATE TABLE `plis` (
  `idplis` int(11) NOT NULL,
  `date_reception` datetime DEFAULT NULL,
  `id_dossier` int(11) DEFAULT NULL,
  `id_lettre` int(11) DEFAULT NULL,
  `observation` varchar(255) DEFAULT NULL,
  `porteur` varchar(255) DEFAULT NULL,
  `poste_ou_courrier_express` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `plis`
--

INSERT INTO `plis` (`idplis`, `date_reception`, `id_dossier`, `id_lettre`, `observation`, `porteur`, `poste_ou_courrier_express`) VALUES
(13, '2024-05-27 13:48:00', 19, 48, 'OOJHHGFFF', 'NNNNNN', 2);

-- --------------------------------------------------------

--
-- Structure de la table `type_marche`
--

CREATE TABLE `type_marche` (
  `id` int(11) NOT NULL,
  `type_marche` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_marche`
--

INSERT INTO `type_marche` (`id`, `type_marche`) VALUES
(1, 'CONTRAT TRAVAUX'),
(2, 'CONTRAT FOURNITURES'),
(5, 'PRESTATION INTELLECTUELLE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `approle`
--
ALTER TABLE `approle`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `appuser`
--
ALTER TABLE `appuser`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `appuser_roles`
--
ALTER TABLE `appuser_roles`
  ADD KEY `FKhvk0uspnceqb18rujefrjrtcu` (`roles_id`),
  ADD KEY `FK79hgfqoueuj3fkq3ll77tpkgf` (`app_user_id`);

--
-- Index pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ged_storage`
--
ALTER TABLE `ged_storage`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ged_table`
--
ALTER TABLE `ged_table`
  ADD PRIMARY KEY (`id_tbl`);

--
-- Index pour la table `lettre`
--
ALTER TABLE `lettre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `mod_passation`
--
ALTER TABLE `mod_passation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `plan_anuell_achat`
--
ALTER TABLE `plan_anuell_achat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhkre5u27mqbomu4bbbo9dde5i` (`fkid_mod_passation`),
  ADD KEY `FKh2vmwv6c36kbx2mhe7ngdhnnd` (`fkid_type_marche`);

--
-- Index pour la table `plis`
--
ALTER TABLE `plis`
  ADD PRIMARY KEY (`idplis`);

--
-- Index pour la table `type_marche`
--
ALTER TABLE `type_marche`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `approle`
--
ALTER TABLE `approle`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `appuser`
--
ALTER TABLE `appuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `dossier`
--
ALTER TABLE `dossier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `files`
--
ALTER TABLE `files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `ged_storage`
--
ALTER TABLE `ged_storage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ged_table`
--
ALTER TABLE `ged_table`
  MODIFY `id_tbl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `lettre`
--
ALTER TABLE `lettre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `mod_passation`
--
ALTER TABLE `mod_passation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `plan_anuell_achat`
--
ALTER TABLE `plan_anuell_achat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `plis`
--
ALTER TABLE `plis`
  MODIFY `idplis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `type_marche`
--
ALTER TABLE `type_marche`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appuser_roles`
--
ALTER TABLE `appuser_roles`
  ADD CONSTRAINT `FK79hgfqoueuj3fkq3ll77tpkgf` FOREIGN KEY (`app_user_id`) REFERENCES `appuser` (`id`),
  ADD CONSTRAINT `FKhvk0uspnceqb18rujefrjrtcu` FOREIGN KEY (`roles_id`) REFERENCES `approle` (`id`);

--
-- Contraintes pour la table `plan_anuell_achat`
--
ALTER TABLE `plan_anuell_achat`
  ADD CONSTRAINT `FKh2vmwv6c36kbx2mhe7ngdhnnd` FOREIGN KEY (`fkid_type_marche`) REFERENCES `type_marche` (`id`),
  ADD CONSTRAINT `FKhkre5u27mqbomu4bbbo9dde5i` FOREIGN KEY (`fkid_mod_passation`) REFERENCES `mod_passation` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
