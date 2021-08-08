-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 20, 2021 at 03:17 PM
-- Server version: 10.4.19-MariaDB-cll-lve
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u496468587_green_casket`
--

-- --------------------------------------------------------

--
-- Table structure for table `pubs`
--

CREATE TABLE `pubs` (
  `pub_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pub_email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pub_number` varchar(18) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pub_password` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pub_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `pubs`
--

INSERT INTO `pubs` (`pub_name`, `pub_email`, `pub_number`, `pub_password`, `pub_id`) VALUES
('Swadheen ', 'swadheenchahal@gmail.com', '9027379383', 'ironfist16', 0),
('Devesh Chahal', 'deveshchahalme@gmail.com', '9997435209', '1234', 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pubs`
--
ALTER TABLE `pubs`
  ADD PRIMARY KEY (`pub_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pubs`
--
ALTER TABLE `pubs`
  MODIFY `pub_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
