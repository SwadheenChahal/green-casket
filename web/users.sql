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
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_number` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_email`, `user_number`, `user_address`, `user_password`) VALUES
(16, 'Devesh Chahal', 'deveshchahalme@gmail.com', '9494947293', 'Sambhal, Singhpur Sani, Bus Stand', 'testandroid'),
(17, 'Swadheen Chahal', 'swadheenchahal@gmail.com', '9027379383', 'singhpur sani', 'ironfist16'),
(18, 'Udit Rai', 'smashingudit4@gmail.com', '6388168918', '139/1 Janakinagar', 'uditRAI'),
(19, 'tushar', 'tushargarg0110@gmail.com', '7754076354', 'hdudhbdb', 'tushar'),
(20, 'jessy pinkman', 'harshitjatt81@gmail.com', '9773876594', 'sambhal', '16051999'),
(21, 'Swarnali Sinha', 'swarnali@mnnit.ac.in', '7595988549', 'Kolkata', '123456'),
(22, 'Vaishnavi ', 'tyagivaish123@gmail.com', '7017733228', 'Sambhal ', 'bestrong'),
(23, 'navneet', 'navneetprakash2000@gmail.com', '9773513186', 'Gurgaon', 'Navneet@123'),
(24, 'Akash Tiwana', 'akash212x@gmail.com', '8279868504', 'SAMBHAL', 'Ironfist16'),
(25, 'Abhinav Prakash', 'abhinavprakash616@gmail.com', '9457548199', 'sambhal', 'swadheenkapwd'),
(26, 'Suraj Kesarwani', 'surajkes296.0@gmail.com', '1950635948', 'Allahabad', 'swadheenmc');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
