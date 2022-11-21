-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 05, 2022 at 09:48 AM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `car_rental`
--

-- --------------------------------------------------------

--
-- Table structure for table `car_mang`
--

DROP TABLE IF EXISTS `car_mang`;
CREATE TABLE IF NOT EXISTS `car_mang` (
  `id` varchar(20) NOT NULL,
  `car_model` varchar(20) NOT NULL,
  `color` varchar(20) NOT NULL,
  `price_rent` varchar(20) NOT NULL,
  `comment` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `car_mang`
--

INSERT INTO `car_mang` (`id`, `car_model`, `color`, `price_rent`, `comment`) VALUES
('1', 'golf', 'sivi', '100', 'odlican'),
('2', 'fiat', 'crni', '150', 'fino auto'),
('5', 'seat', 'crvena', '50', 'fino auto'),
('4', 'ferari', 'bijela', '120', 'odlican'),
('3', 'audi', 'zelena', '100', 'good'),
('6', 'rabbit', 'roza', '120', 'bijeli'),
('10', 'golf 8', 'bijela', '10', 'good');

-- --------------------------------------------------------

--
-- Table structure for table `customer_mang`
--

DROP TABLE IF EXISTS `customer_mang`;
CREATE TABLE IF NOT EXISTS `customer_mang` (
  `id` varchar(20) NOT NULL,
  `customer_name` varchar(20) NOT NULL,
  `customer_phone` varchar(20) NOT NULL,
  `customer_address` varchar(20) NOT NULL,
  `comment` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_mang`
--

INSERT INTO `customer_mang` (`id`, `customer_name`, `customer_phone`, `customer_address`, `comment`) VALUES
('1', 'edinaz', '232376876', 'grbavica', 'hfhfh');

-- --------------------------------------------------------

--
-- Table structure for table `rent_mang`
--

DROP TABLE IF EXISTS `rent_mang`;
CREATE TABLE IF NOT EXISTS `rent_mang` (
  `id` int(10) NOT NULL,
  `car_id` int(10) NOT NULL,
  `customer_name` varchar(20) NOT NULL,
  `date` varchar(20) NOT NULL,
  `cost` varchar(10) DEFAULT NULL,
  `paid_cost` varchar(10) NOT NULL,
  `total` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rent_mang`
--

INSERT INTO `rent_mang` (`id`, `car_id`, `customer_name`, `date`, `cost`, `paid_cost`, `total`) VALUES
(1, 1, 'edinaz', '1', '100.0', '1', '99.0'),
(2, 4, 'edinaz', '2', '240.0', '30', '210.0'),
(3, 3, 'edinaz', '1', '100.0', '10', '90.0');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
