-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-01-2026 a las 11:52:29
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `elorserv`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciclos`
--

CREATE TABLE `ciclos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciclos`
--

INSERT INTO `ciclos` (`id`, `nombre`) VALUES
(1, 'DAM'),
(2, 'DAW'),
(3, 'OTROS'),
(4, 'ASIR'),
(5, 'SMR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

CREATE TABLE `horarios` (
  `id` int(11) NOT NULL,
  `dia` enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES') NOT NULL,
  `hora` tinyint(4) NOT NULL,
  `profe_id` int(11) NOT NULL,
  `modulo_id` int(11) NOT NULL,
  `aula` varchar(50) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`id`, `dia`, `hora`, `profe_id`, `modulo_id`, `aula`, `observaciones`, `created_at`, `updated_at`) VALUES
(1, 'LUNES', 1, 4, 1, '', NULL, '2025-12-12 16:51:59', '2025-12-14 21:30:07'),
(2, 'LUNES', 1, 11, 15, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:16:49'),
(3, 'LUNES', 2, 11, 15, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:16:58'),
(4, 'LUNES', 3, 4, 8, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(5, 'LUNES', 4, 4, 8, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:17:40'),
(6, 'LUNES', 5, 11, 9, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:18:03'),
(7, 'LUNES', 6, 11, 9, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:18:14'),
(8, 'MARTES', 1, 11, 9, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(9, 'MARTES', 2, 11, 9, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(10, 'MARTES', 3, 11, 9, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(11, 'MARTES', 4, 13, 10, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:18:40'),
(12, 'MARTES', 5, 13, 10, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:18:53'),
(13, 'MARTES', 6, 13, 10, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:19:12'),
(14, 'MIERCOLES', 1, 14, 3, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 18:56:01'),
(15, 'MIERCOLES', 2, 14, 3, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 18:56:17'),
(16, 'MIERCOLES', 3, 12, 13, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 18:56:49'),
(17, 'MIERCOLES', 4, 4, 11, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(18, 'MIERCOLES', 5, 4, 11, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(19, 'MIERCOLES', 6, 4, 11, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(20, 'JUEVES', 1, 11, 12, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(21, 'JUEVES', 2, 11, 12, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(22, 'JUEVES', 3, 4, 8, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:20:46'),
(23, 'JUEVES', 4, 4, 8, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(24, 'JUEVES', 5, 13, 14, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:21:11'),
(25, 'JUEVES', 6, 13, 14, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:21:28'),
(26, 'VIERNES', 1, 14, 3, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(27, 'VIERNES', 2, 14, 3, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(28, 'VIERNES', 3, 14, 3, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(29, 'VIERNES', 4, 12, 13, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:22:22'),
(30, 'VIERNES', 5, 12, 13, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-14 14:22:40'),
(31, 'VIERNES', 6, 11, 12, 'Aula B101', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(32, 'LUNES', 1, 14, 9, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 14:23:34'),
(33, 'LUNES', 2, 14, 9, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 14:23:42'),
(34, 'LUNES', 3, 3, 8, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(35, 'LUNES', 4, 3, 8, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(36, 'LUNES', 5, 3, 14, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(37, 'LUNES', 6, 3, 14, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(38, 'MARTES', 1, 12, 13, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:04:16'),
(39, 'MARTES', 2, 12, 13, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:06:12'),
(40, 'MARTES', 3, 14, 15, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:06:25'),
(41, 'MARTES', 4, 14, 15, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:05:31'),
(42, 'MARTES', 5, 3, 8, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(43, 'MARTES', 6, 3, 8, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(44, 'MIERCOLES', 1, 6, 3, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:08:20'),
(45, 'MIERCOLES', 2, 6, 3, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:08:43'),
(46, 'MIERCOLES', 3, 12, 13, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:09:28'),
(47, 'MIERCOLES', 4, 14, 9, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:10:09'),
(48, 'MIERCOLES', 5, 14, 9, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:10:16'),
(49, 'MIERCOLES', 6, 14, 9, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:10:40'),
(50, 'JUEVES', 1, 3, 10, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:12:09'),
(51, 'JUEVES', 2, 3, 10, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:12:27'),
(52, 'JUEVES', 3, 3, 10, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:12:34'),
(53, 'JUEVES', 4, 16, 11, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 18:48:19'),
(54, 'JUEVES', 5, 4, 11, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(55, 'JUEVES', 6, 4, 11, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(56, 'VIERNES', 1, 6, 3, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:13:23'),
(57, 'VIERNES', 2, 6, 3, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:13:34'),
(58, 'VIERNES', 3, 6, 3, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:13:48'),
(59, 'VIERNES', 4, 14, 12, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:14:09'),
(60, 'VIERNES', 5, 14, 12, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:14:18'),
(61, 'VIERNES', 6, 14, 12, 'Aula 106', NULL, '2025-12-12 16:51:59', '2025-12-14 17:14:27'),
(62, 'LUNES', 1, 3, 5, '5.005', NULL, '2025-12-14 17:35:47', '2025-12-14 17:35:47'),
(63, 'LUNES', 2, 3, 5, '5.005', NULL, '2025-12-14 17:35:47', '2025-12-14 17:38:49'),
(64, 'LUNES', 3, 17, 4, '5.005', NULL, '2025-12-14 17:35:47', '2025-12-14 17:39:24'),
(65, 'LUNES', 4, 17, 4, '5.005', NULL, '2025-12-14 17:35:47', '2025-12-14 17:39:33'),
(66, 'LUNES', 5, 17, 4, '5.005', NULL, '2025-12-14 17:35:47', '2025-12-14 17:39:47'),
(67, 'LUNES', 6, 12, 16, '5.005', NULL, '2025-12-14 17:35:47', '2025-12-14 17:35:47'),
(68, 'MARTES', 1, 3, 7, '5.005', NULL, '2025-12-14 17:36:33', '2025-12-14 17:36:33'),
(69, 'MARTES', 2, 3, 7, '5.005', NULL, '2025-12-14 17:36:33', '2025-12-14 17:36:33'),
(70, 'MARTES', 3, 3, 5, '5.005', NULL, '2025-12-14 17:36:33', '2025-12-14 17:40:09'),
(71, 'MARTES', 4, 17, 6, '5.005', NULL, '2025-12-14 17:36:33', '2025-12-14 17:41:34'),
(72, 'MARTES', 5, 17, 6, '5.005', NULL, '2025-12-14 17:36:33', '2025-12-14 17:41:45'),
(73, 'MARTES', 6, 16, 19, '5.005', NULL, '2025-12-14 17:36:33', '2025-12-14 17:42:20'),
(74, 'MIERCOLES', 1, 15, 17, '5.005', NULL, '2025-12-14 17:36:54', '2025-12-14 18:57:39'),
(75, 'MIERCOLES', 2, 3, 5, '5.005', NULL, '2025-12-14 17:36:54', '2025-12-14 17:43:11'),
(77, 'MIERCOLES', 4, 16, 4, '5.005', NULL, '2025-12-14 17:36:54', '2025-12-14 18:48:28'),
(78, 'MIERCOLES', 5, 16, 4, '5.005', NULL, '2025-12-14 17:36:54', '2025-12-14 18:48:38'),
(79, 'MIERCOLES', 6, 12, 16, '5.005', NULL, '2025-12-14 17:36:54', '2025-12-14 17:36:54'),
(80, 'JUEVES', 1, 6, 18, '5.005', NULL, '2025-12-14 17:37:05', '2025-12-14 18:21:14'),
(81, 'JUEVES', 2, 6, 18, '5.005', NULL, '2025-12-14 17:37:05', '2025-12-14 18:21:30'),
(82, 'JUEVES', 3, 16, 7, '5.005', NULL, '2025-12-14 17:37:05', '2025-12-14 18:21:52'),
(83, 'JUEVES', 4, 17, 4, '5.005', NULL, '2025-12-14 17:37:05', '2025-12-14 18:22:26'),
(84, 'JUEVES', 5, 17, 4, '5.005', NULL, '2025-12-14 17:37:05', '2025-12-14 17:49:18'),
(85, 'JUEVES', 6, 17, 4, '5.005', NULL, '2025-12-14 17:37:05', '2025-12-14 17:49:01'),
(86, 'VIERNES', 1, 12, 17, '5.005', NULL, '2025-12-14 17:37:13', '2025-12-14 17:48:01'),
(87, 'VIERNES', 2, 3, 5, '5.005', NULL, '2025-12-14 17:37:13', '2025-12-14 17:47:47'),
(88, 'VIERNES', 3, 3, 5, '5.005', NULL, '2025-12-14 17:37:13', '2025-12-14 17:47:30'),
(89, 'VIERNES', 6, 12, 16, '5.005', NULL, '2025-12-14 17:37:13', '2025-12-14 17:46:53'),
(90, 'VIERNES', 5, 12, 16, '5.005', NULL, '2025-12-14 17:37:13', '2025-12-14 17:37:13'),
(91, 'VIERNES', 4, 3, 5, '5.005', NULL, '2025-12-14 17:37:13', '2025-12-14 17:47:06'),
(92, 'MIERCOLES', 3, 3, 5, '5.005', NULL, '2025-12-14 17:36:54', '2025-12-14 17:36:54'),
(93, 'LUNES', 1, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(94, 'LUNES', 2, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(95, 'LUNES', 3, 15, 7, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(96, 'LUNES', 4, 15, 4, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(97, 'LUNES', 5, 15, 4, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(98, 'LUNES', 6, 15, 4, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(99, 'MARTES', 1, 13, 6, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(100, 'MARTES', 2, 13, 6, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(101, 'MARTES', 3, 12, 16, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(102, 'MARTES', 4, 12, 16, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(103, 'MARTES', 5, 15, 18, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(104, 'MARTES', 6, 15, 18, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(105, 'MIERCOLES', 1, 12, 16, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(106, 'MIERCOLES', 2, 12, 16, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(107, 'MIERCOLES', 3, 15, 7, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(108, 'MIERCOLES', 4, 15, 7, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(109, 'MIERCOLES', 5, 13, 6, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(110, 'MIERCOLES', 6, 13, 6, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(111, 'JUEVES', 1, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(112, 'JUEVES', 2, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(113, 'JUEVES', 3, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(114, 'JUEVES', 4, 15, 4, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(115, 'JUEVES', 5, 15, 4, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(116, 'JUEVES', 6, 15, 4, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(117, 'VIERNES', 1, 12, 17, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(118, 'VIERNES', 2, 12, 17, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(119, 'VIERNES', 3, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(120, 'VIERNES', 4, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(121, 'VIERNES', 5, 13, 5, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(122, 'VIERNES', 6, 13, 19, '5.012', NULL, '2025-12-14 18:47:15', '2025-12-14 18:47:15'),
(634, 'JUEVES', 4, 3, 1, NULL, 'Tutoria automática', '2025-12-14 20:58:46', '2025-12-14 21:31:45'),
(635, 'JUEVES', 5, 3, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(636, 'JUEVES', 6, 3, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(637, 'MARTES', 4, 3, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 21:31:21'),
(638, 'JUEVES', 1, 4, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(639, 'JUEVES', 2, 4, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(640, 'LUNES', 1, 4, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(644, 'JUEVES', 3, 6, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(645, 'JUEVES', 4, 6, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(646, 'JUEVES', 5, 6, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(647, 'JUEVES', 3, 11, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(648, 'JUEVES', 4, 11, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(649, 'JUEVES', 5, 11, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(650, 'JUEVES', 1, 12, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(651, 'JUEVES', 2, 12, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(652, 'JUEVES', 3, 12, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(653, 'JUEVES', 4, 13, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(654, 'LUNES', 5, 13, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 21:21:29'),
(655, 'LUNES', 3, 13, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(656, 'JUEVES', 1, 14, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(657, 'JUEVES', 2, 14, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(658, 'JUEVES', 3, 14, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(659, 'JUEVES', 1, 15, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(660, 'JUEVES', 2, 15, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(661, 'JUEVES', 3, 15, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(662, 'JUEVES', 1, 16, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(663, 'JUEVES', 2, 16, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(664, 'JUEVES', 5, 16, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(665, 'JUEVES', 1, 17, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(666, 'JUEVES', 2, 17, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46'),
(667, 'JUEVES', 3, 17, 2, NULL, 'Guardia automática', '2025-12-14 20:58:46', '2025-12-14 20:58:46');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculaciones`
--

CREATE TABLE `matriculaciones` (
  `id` int(11) NOT NULL,
  `alum_id` int(11) NOT NULL,
  `ciclo_id` int(11) NOT NULL,
  `curso` tinyint(4) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matriculaciones`
--

INSERT INTO `matriculaciones` (`id`, `alum_id`, `ciclo_id`, `curso`, `fecha`) VALUES
(1, 7, 1, 1, '2025-01-10'),
(2, 8, 2, 1, '2025-01-11'),
(3, 9, 3, 2, '2025-01-12'),
(4, 10, 4, 2, '2025-01-13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulos`
--

CREATE TABLE `modulos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `nombre_eus` varchar(200) DEFAULT NULL,
  `horas` int(11) NOT NULL,
  `ciclo_id` int(11) NOT NULL,
  `curso` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modulos`
--

INSERT INTO `modulos` (`id`, `nombre`, `nombre_eus`, `horas`, `ciclo_id`, `curso`) VALUES
(1, 'Tutoria', 'Tutoretza', 1, 1, 0),
(2, 'Guardia', 'Zaintza', 1, 1, 0),
(3, 'Sistemas Informaticos', 'Informatika-sistemak', 165, 1, 1),
(4, 'Bases de datos', 'Datu-baseak', 198, 1, 1),
(5, 'Programación', 'Programazioa', 264, 1, 1),
(6, 'Lenguajes de marcas', 'Markatzeko lengoaiak', 132, 1, 1),
(7, 'Entornos de desarrollo', 'Garapen-inguruneak', 99, 1, 1),
(8, 'Acceso a datos', 'Datu-atzipena', 120, 1, 2),
(9, 'Desarrollo de interfaces', 'Interfazeen garapena', 140, 1, 2),
(10, 'Programación multimedia y dispositivos móviles', 'Multimedia-programazioa eta gailu mugikorrak', 100, 1, 2),
(11, 'Programación de servicios y procesos', 'Zerbitzu eta prozesuen programazioa', 80, 1, 2),
(12, 'Sistemas de gestión empresarial', 'Enpresa-kudeaketako sistemak', 100, 1, 2),
(13, 'Empresa e Iniciativa Emprendedora', 'Enpresa eta ekimen sortzailea', 60, 1, 2),
(14, 'OPT.moviles', 'Opt. Mugikorrak', 60, 1, 2),
(15, 'LINQ', 'LINQ', 60, 1, 2),
(16, 'Itinerarios personales', 'Ibilbide pertsonalak', 60, 1, 1),
(17, 'Inglés técnico', 'Ingeles teknikoa', 60, 1, 1),
(18, 'Digitalización', 'Digitalizazioa', 60, 1, 1),
(19, 'Sostenibilidad', 'Jasangarritasuna', 60, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reuniones`
--

CREATE TABLE `reuniones` (
  `id_reunion` int(11) NOT NULL,
  `estado` enum('pendiente','aceptada','denegada','conflicto') DEFAULT 'pendiente',
  `estado_eus` enum('onartzeke','onartuta','ezeztatuta','gatazka') DEFAULT NULL,
  `profesor_id` int(11) DEFAULT NULL,
  `alumno_id` int(11) DEFAULT NULL,
  `id_centro` varchar(20) DEFAULT '15112',
  `titulo` varchar(150) DEFAULT NULL,
  `asunto` varchar(200) DEFAULT NULL,
  `aula` varchar(20) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reuniones`
--

INSERT INTO `reuniones` (`id_reunion`, `estado`, `estado_eus`, `profesor_id`, `alumno_id`, `id_centro`, `titulo`, `asunto`, `aula`, `fecha`, `created_at`, `updated_at`) VALUES
(1, 'pendiente', NULL, 3, 7, '15112', NULL, NULL, NULL, '2025-01-15 10:00:00', '2025-12-04 18:53:54', '2025-12-04 18:53:54'),
(2, 'pendiente', NULL, 3, 8, '15112', NULL, NULL, NULL, '2025-01-16 11:00:00', '2025-12-04 18:53:54', '2025-12-04 18:53:54'),
(3, 'pendiente', NULL, 4, 9, '15112', NULL, NULL, NULL, '2025-01-17 12:00:00', '2025-12-04 18:53:54', '2025-12-04 18:53:54'),
(4, 'pendiente', NULL, NULL, 10, '15112', NULL, NULL, NULL, '2025-01-18 09:00:00', '2025-12-04 18:53:54', '2025-12-04 18:53:54');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `name_eu` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `name`, `name_eu`) VALUES
(1, 'god', 'jainkoa'),
(2, 'administrador', 'administratzailea'),
(3, 'profesor', 'irakaslea'),
(4, 'alumno', 'ikaslea');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono1` varchar(20) DEFAULT NULL,
  `telefono2` varchar(20) DEFAULT NULL,
  `tipo_id` int(11) NOT NULL,
  `argazkia_url` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `nombre`, `apellidos`, `dni`, `direccion`, `telefono1`, `telefono2`, `tipo_id`, `argazkia_url`, `created_at`, `updated_at`) VALUES
(1, 'god@elorrieta.com', '5d9+ykzisY5j3JJnm3wtgA==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'God', 'Admin', '00000000A', 'Calle 1, Bilbao', '600111111', '600111112', 1, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00'),
(2, 'admin@elorrieta.com', 'j+ssXOzYm3xBO/jO8V15Dg==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Admin', 'User', '11111111B', 'Calle 2, Bilbao', '600222222', '600222223', 2, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00'),
(3, 'prof1@elorrieta.com', 'JMBoHz60T3A+/H7LVFFGig==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Roman', 'Lopez', '22222222C', 'Calle 3, Bilbao', '600333333', '600333334', 3, NULL, '2025-01-01 00:00:00', '2025-12-14 14:24:57'),
(4, 'prof2@elorrieta.com', 'qYz8+SZa+Y6VhyQ8fxTa5g==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Iker', 'Martinez', '33333333D', 'Calle 4, Bilbao', '600444444', '600444445', 3, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00'),
(6, 'prof4@elorrieta.com', 'umLcZeYgbg/SISaUguM4uw==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Oier', 'Gomez', '55555555F', 'Calle 6, Bilbao', '600666666', '600666667', 3, NULL, '2025-01-01 00:00:00', '2025-12-14 17:07:42'),
(7, 'alum1@elorrieta.com', 'VqOFZjJtDamzRN1C3eqm3Q==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Oscar', 'Lopez', '66666666G', 'Calle 7, Bilbao', '600777777', '600777778', 4, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00'),
(8, 'alum2@elorrieta.com', 'ONNqLn/U9wUzCHrHcgVDMQ==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Itziar', 'Martinez', '77777777H', 'Calle 8, Bilbao', '600888888', '600888889', 4, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00'),
(9, 'alum3@elorrieta.com', 'i9nyAMXhLWaDFOedSMBiVQ==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Karlos', 'Garcia', '88888888I', 'Calle 9, Bilbao', '600999999', '600999990', 4, NULL, '2025-01-01 00:00:00', '2025-12-14 14:25:18'),
(10, 'alum4@elorrieta.com', 'ur5mTCVVRqNvH6TIGB1EZQ==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Iker', 'Lopez', '99999999J', 'Calle 10, Bilbao', '601000000', '601000001', 4, NULL, '2025-01-01 00:00:00', '2025-01-01 00:00:00'),
(11, 'jorge.lopez@elorrieta.com', '+pzK0y0jlFma3d2Ry50H/A==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Jorge', 'Lopez', '11111111K', 'Marques del puerto, 5', NULL, NULL, 3, NULL, '2025-12-12 16:51:59', '2025-12-14 18:24:56'),
(12, 'nerea.gomez@elorrieta.com', 'End3yL7OiXaZ9K9UdShvbg==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Nerea', 'Gomez', '22222222L', 'Lehendakari aguirre, 121', NULL, NULL, 3, NULL, '2025-12-12 16:51:59', '2025-12-14 18:25:16'),
(13, 'asier.garcia@elorrieta.com', 'l8WYqVAwaztujRVhaSHOMQ==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Asier', 'Garcia', '33333333M', NULL, NULL, NULL, 3, NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(14, 'mikel.orego@elorrieta.com', 'DEB2UnNsQEgw8QxI0vYrzw==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Mikel', 'Orego', '44444444N', NULL, NULL, NULL, 3, NULL, '2025-12-12 16:51:59', '2025-12-12 16:51:59'),
(15, 'jose.paz@elorrieta.com', 's3SyfSBVb+ntqaT1zIH91A==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Jose', 'Paz', '77777777k', 'Calle iturribide,6', '99999999', '111111111', 3, NULL, '2025-12-14 17:34:52', '2025-12-14 18:26:27'),
(16, 'aitziber.andes@elorrieta.com', 'RFd57tYDTHCSACUVMnoPDQ==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Aitziber', 'Andes', '12345689', 'Orixe Kalea, 23 ', '123456', '123456', 3, NULL, '2025-12-14 17:34:52', '2025-12-14 18:24:21'),
(17, 'nere.barbar@elorrieta.com', 'vi0xhDdty347l4jfmkPNtw==', 'Z0+/YNxYNXFl1fwFnYjCfg==', 'Nerea', 'Barbar', NULL, 'Av. lehenedakari agirre 145', '123456', '123456', 3, NULL, '2025-12-14 17:34:52', '2025-12-14 18:23:30');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciclos`
--
ALTER TABLE `ciclos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_hor_dia_hora_aula` (`dia`,`hora`,`aula`),
  ADD KEY `idx_hor_profe` (`profe_id`),
  ADD KEY `idx_hor_mod` (`modulo_id`);

--
-- Indices de la tabla `matriculaciones`
--
ALTER TABLE `matriculaciones`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_mat_alum_ciclo_curso` (`alum_id`,`ciclo_id`,`curso`),
  ADD KEY `idx_mat_alum` (`alum_id`),
  ADD KEY `idx_mat_ciclo` (`ciclo_id`);

--
-- Indices de la tabla `modulos`
--
ALTER TABLE `modulos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_modulos_ciclo` (`ciclo_id`);

--
-- Indices de la tabla `reuniones`
--
ALTER TABLE `reuniones`
  ADD PRIMARY KEY (`id_reunion`),
  ADD KEY `fk_reun_prof` (`profesor_id`),
  ADD KEY `fk_reun_alum` (`alumno_id`),
  ADD KEY `idx_reun_fecha` (`fecha`);

--
-- Indices de la tabla `tipos`
--
ALTER TABLE `tipos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_tipos_name` (`name`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_users_email` (`email`),
  ADD UNIQUE KEY `ux_users_username` (`username`),
  ADD KEY `idx_users_tipo` (`tipo_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciclos`
--
ALTER TABLE `ciclos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `horarios`
--
ALTER TABLE `horarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=699;

--
-- AUTO_INCREMENT de la tabla `matriculaciones`
--
ALTER TABLE `matriculaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `modulos`
--
ALTER TABLE `modulos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `reuniones`
--
ALTER TABLE `reuniones`
  MODIFY `id_reunion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipos`
--
ALTER TABLE `tipos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD CONSTRAINT `fk_hor_mod` FOREIGN KEY (`modulo_id`) REFERENCES `modulos` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_hor_profe` FOREIGN KEY (`profe_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `matriculaciones`
--
ALTER TABLE `matriculaciones`
  ADD CONSTRAINT `fk_mat_alum` FOREIGN KEY (`alum_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mat_ciclo` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `modulos`
--
ALTER TABLE `modulos`
  ADD CONSTRAINT `fk_modulos_ciclo` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `reuniones`
--
ALTER TABLE `reuniones`
  ADD CONSTRAINT `fk_reun_alum` FOREIGN KEY (`alumno_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reun_prof` FOREIGN KEY (`profesor_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_tipo` FOREIGN KEY (`tipo_id`) REFERENCES `tipos` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
