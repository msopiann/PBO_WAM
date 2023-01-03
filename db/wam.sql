-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 30, 2022 at 08:51 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wam`
--

-- --------------------------------------------------------

--
-- Table structure for table `scores`
--

CREATE TABLE `scores` (
  `id` varchar(40) NOT NULL,
  `name` varchar(20) NOT NULL,
  `hasil` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `scores`
--

INSERT INTO `scores` (`id`, `name`, `hasil`) VALUES
('1', 'test', 7),
('81648ae2-6ede-4c0d-a126-7de4d1d13bf2', 'test', 6),
('4e8ee492-093d-4591-ac76-2927a3782ffa', 'coba', 4),
('992d3ef4-881d-41b6-b638-b1fa2678a2b5', 'hey', 8),
('5636530a-fcce-4eca-b864-4b553b296c54', 'yoow', 3),
('2f0d4272-3544-4826-b8ed-3b915871fba5', 'yoow', 7),
('491cbb0f-484f-4aca-a36c-e1bb6c25228c', 'DD', 13),
('7844ff03-84d5-47fb-91bd-e32905160624', 'DD', 8),
('e27cb0b0-bf0b-44cc-adee-22620a466ce0', 'puttt', 13),
('207a4e7b-5b50-4b1a-93da-3699fac4bf9f', 'hhhhh', 1),
('d913cf02-53b3-4693-93b1-be12e7599a75', 'aku', 2),
('b5079463-1318-4b1d-8c46-b40a9d29b1d2', 'ii', 5),
('bb92923d-3711-4baf-a0c2-309613ca34b2', 'nama', 0),
('b74ab158-8a7c-45b5-b7b7-de87988dde02', 'trial', 3),
('4ee6bad0-3a1f-4e63-9f70-f43dca0cd706', 'test', 3),
('9e8fd09b-a01a-447a-a0db-db68cdc00009', 'cek', 14),
('ca8fdee7-a39d-43df-9d53-dd552488744a', 'put', 12),
('ec01a4b2-b60c-40de-9472-d0fa8ff705e9', 'tew', 12),
('8f6affe9-de0c-44d3-945d-7afdc6048767', 'izdore', 6),
('f0b13afe-723a-407b-9341-a6fe28957485', 'issa', 3),
('f0de0900-9ee4-4fcb-84b8-ba3e54fe3007', 'izzz', 1),
('727a76fa-23f0-414f-bc65-4b66069a72f9', 'izzz', -5),
('8804f1f4-863c-4fc6-aa8b-6f3f630f0325', 'izzz', 0),
('6d15a564-2cd4-4dab-b9ae-2c27e85a8cfe', 'izzz', 7),
('f75a5303-885c-4e7a-97aa-ff6ec4761143', 'hacu', 8),
('fd6b64ba-18ea-4582-9e9d-87f8ace929fd', 'heyow', 10),
('ee373038-9c65-46ec-ae28-f42d9e2cd24b', 'nama', 7),
('6d6d5e78-1bb0-4490-b58c-269d9c13fbb4', 'nama', 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
