-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2025 at 10:39 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medical_task_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `first_name`, `last_name`, `timezone`) VALUES
(1, 'Alice', 'Smith', 'America/New_York'),
(2, 'Bob', 'Johnson', 'Europe/London'),
(3, 'Charlie', 'Brown', 'Asia/Tokyo'),
(4, 'John', 'Doe', 'America/New_York'),
(5, 'Jack', 'Smith', 'Europe/London'),
(6, 'Robert', 'Brown', 'Asia/Tokyo'),
(7, 'Emily', 'Davis', 'America/Los_Angeles'),
(8, 'Michael', 'Wilson', 'Europe/Berlin'),
(9, 'Sophia', 'Miller', 'Asia/Singapore'),
(10, 'Daniel', 'Anderson', 'America/Chicago'),
(11, 'Emma', 'Martinez', 'Europe/Madrid'),
(12, 'William', 'Taylor', 'America/Toronto'),
(13, 'Olivia', 'Harris', 'Europe/Paris');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `first_name`, `last_name`) VALUES
(1, 'David', 'Wilson'),
(2, 'Emma', 'Taylor'),
(3, 'Frank', 'Anderson'),
(4, 'Grace', 'Thomas'),
(5, 'Ethan', 'Johnson'),
(6, 'Ava', 'Williams'),
(7, 'Mason', 'Jones'),
(8, 'Isabella', 'Garcia'),
(9, 'Logan', 'Martinez'),
(10, 'Liam', 'Lopez'),
(11, 'Sophia', 'Gonzalez'),
(12, 'James', 'Rodriguez'),
(13, 'Charlotte', 'Lee'),
(14, 'Benjamin', 'Walker'),
(15, 'Amelia', 'Hall'),
(16, 'Elijah', 'Allen'),
(17, 'Harper', 'Young'),
(18, 'Henry', 'King'),
(19, 'Alexander', 'Wright'),
(20, 'Ella', 'Scott'),
(21, 'Lucas', 'Green'),
(22, 'Mila', 'Adams'),
(23, 'Noah', 'Baker'),
(24, 'Zoe', 'Nelson');

-- --------------------------------------------------------

--
-- Table structure for table `visit`
--

CREATE TABLE `visit` (
  `id` bigint(20) NOT NULL,
  `end_date_time` datetime(6) DEFAULT NULL,
  `start_date_time` datetime(6) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `visit`
--

INSERT INTO `visit` (`id`, `end_date_time`, `start_date_time`, `doctor_id`, `patient_id`) VALUES
(1, '2025-02-16 10:00:00.000000', '2025-02-16 09:00:00.000000', 1, 1),
(2, '2025-02-16 11:30:00.000000', '2025-02-16 10:30:00.000000', 1, 2),
(3, '2025-02-16 15:00:00.000000', '2025-02-16 14:00:00.000000', 2, 3),
(4, '2025-02-16 17:00:00.000000', '2025-02-16 16:00:00.000000', 2, 4),
(5, '2025-02-16 09:30:00.000000', '2025-02-16 09:00:00.000000', 3, 1),
(6, '2025-02-16 11:00:00.000000', '2025-02-16 10:00:00.000000', 3, 2),
(7, '2024-02-01 09:00:00.000000', '2024-02-01 08:00:00.000000', 1, 1),
(8, '2024-02-02 11:00:00.000000', '2024-02-02 10:00:00.000000', 2, 2),
(9, '2024-02-03 13:00:00.000000', '2024-02-03 12:00:00.000000', 3, 3),
(10, '2024-02-04 15:00:00.000000', '2024-02-04 14:00:00.000000', 4, 4),
(11, '2024-02-05 17:00:00.000000', '2024-02-05 16:00:00.000000', 5, 5),
(12, '2024-02-06 09:30:00.000000', '2024-02-06 08:30:00.000000', 6, 6),
(13, '2024-02-07 11:30:00.000000', '2024-02-07 10:30:00.000000', 7, 7),
(14, '2024-02-08 13:30:00.000000', '2024-02-08 12:30:00.000000', 8, 8),
(15, '2024-02-09 15:30:00.000000', '2024-02-09 14:30:00.000000', 9, 9),
(16, '2024-02-10 17:30:00.000000', '2024-02-10 16:30:00.000000', 10, 10),
(17, '2024-02-11 10:00:00.000000', '2024-02-11 09:00:00.000000', 1, 11),
(18, '2024-02-12 12:00:00.000000', '2024-02-12 11:00:00.000000', 2, 12),
(19, '2024-02-13 14:00:00.000000', '2024-02-13 13:00:00.000000', 3, 13),
(20, '2024-02-14 16:00:00.000000', '2024-02-14 15:00:00.000000', 4, 14),
(21, '2024-02-15 18:00:00.000000', '2024-02-15 17:00:00.000000', 5, 15),
(22, '2024-02-16 10:30:00.000000', '2024-02-16 09:30:00.000000', 6, 16),
(23, '2024-02-17 12:30:00.000000', '2024-02-17 11:30:00.000000', 7, 17),
(24, '2024-02-18 14:30:00.000000', '2024-02-18 13:30:00.000000', 8, 18),
(25, '2024-02-19 16:30:00.000000', '2024-02-19 15:30:00.000000', 9, 19),
(26, '2024-02-20 18:30:00.000000', '2024-02-20 17:30:00.000000', 10, 20),
(27, '2024-02-01 07:00:00.000000', '2024-02-01 07:00:00.000000', 13, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `visit`
--
ALTER TABLE `visit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc63541y8ppkvsovm00gumv90t` (`doctor_id`),
  ADD KEY `FKrban5yeabnx30seqm69jw44e` (`patient_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `visit`
--
ALTER TABLE `visit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `FKc63541y8ppkvsovm00gumv90t` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  ADD CONSTRAINT `FKrban5yeabnx30seqm69jw44e` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
