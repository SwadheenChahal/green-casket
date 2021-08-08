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
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_brand` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_publisher_id` int(11) NOT NULL,
  `product_ratings` double NOT NULL DEFAULT 0,
  `product_raters` int(11) NOT NULL DEFAULT 0,
  `product_current_price` int(11) NOT NULL,
  `product_real_price` int(11) NOT NULL,
  `product_description` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_title`, `product_brand`, `product_image`, `product_publisher_id`, `product_ratings`, `product_raters`, `product_current_price`, `product_real_price`, `product_description`) VALUES
(1, 'Naturally sweet colorful and delicious fruits.', 'DC Veg', 'images/1.jpg', 0, 49.5, 13, 9999, 12334, 'We believe fruits are good for us because they contain fiber, vitamins, and antioxidants. While there is no evidence that humans require fruit in order to be healthy, of all the plant foods you can eat, fruits are the least likely to cause trouble. That’s because fruits are the only parts of the plant that are specifically designed to be eaten.'),
(3, 'VEGITABLES | Home delivery | Order online', 'Toxic', 'images/2.jpg', 0, 9.5, 3, 1354, 1538, 'Vegetables are parts of plants that are consumed by humans or other animals as food. The original meaning is still commonly used and is applied to plants collectively to refer to all edible plant matter, including the flowers, fruits, stems, leaves, roots, and seeds.'),
(4, 'Karnataka state implements several schemes for the benefit of farmers.', 'Omaxe', 'images/3.jpg', 0, 18, 4, 2546, 2575, 'The southeast Asian plant Durian has been called the King of Fruits but, like Marmite, it sharply divides opinion between those who love the taste of its custard-like pulp and those revolted by its putrid smell.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
