-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 18, 2022 at 03:43 PM
-- Server version: 8.0.13
-- PHP Version: 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobile_shopping_site`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `state` varchar(50) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `zip` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `address_line_1`, `address_line_2`, `city`, `country`, `full_name`, `phone`, `state`, `user_id`, `zip`) VALUES
(1, 'D-23', 'Near Lohia Park', 'Ghaziabad', 'India', 'Sorabh86', '9483838842', 'Uttar Pradesh', 1, '201005'),
(2, '123 streets', 'Near Tower House', 'Banglore', 'India', 'Sorabh Sharma', '9483960030', 'Tamilnadu', 2, '284995'),
(3, '212 Street', 'Near Metro Station', 'Dault', 'United State', 'Sorabh', '948493822', 'LakeWill', 2, '38843'),
(8, 'djafslj', 'dalkjfl', 'lkjsalk', 'lkjaslkdj', 'happy paul', '349404394', 'lkjasdkl', 22, '49034403'),
(9, 'kllas fkj', ' kjads lj', 'lk jaslkd jlk', 'j asjflk', 'kajf f', 'k aslkdsj ', 'j lkasfdj lk', 1, '23090932'),
(10, 'kladsj', 'sksdfl', 'saksdj', 'asddkfj', 'happy paul', '389398493', 'asdkf', 22, '39032');

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `phone_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cart_items`
--

INSERT INTO `cart_items` (`id`, `quantity`, `phone_id`, `user_id`) VALUES
(32, 1, 3, 22);

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `receiver_username` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `is_visited` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`id`, `date`, `message`, `receiver_username`, `status`, `username`, `is_visited`) VALUES
(85, '2022-06-16 17:59:47.463000', 'hello man', NULL, 'MESSAGE', 'sorabh', b'0'),
(86, '2022-06-16 18:00:10.052000', 'what do you want', 'sorabh', 'MESSAGE', 'admin', b'0'),
(152, '2022-06-16 18:40:52.721000', 'babby ii love it', NULL, 'MESSAGE', 'admin', b'0'),
(153, '2022-06-16 18:41:09.747000', 'what are you saying? are you mad?', NULL, 'MESSAGE', 'sorabh', b'0'),
(154, '2022-06-16 18:41:17.275000', 'baby i love you', NULL, 'MESSAGE', 'admin', b'0'),
(155, '2022-06-16 18:41:26.084000', 'so what?', NULL, 'MESSAGE', 'sorabh', b'0'),
(156, '2022-06-16 18:41:30.305000', 'nothing', NULL, 'MESSAGE', 'admin', b'0'),
(161, '2022-06-16 18:41:42.289000', 'keep going', NULL, 'MESSAGE', 'admin', b'0'),
(162, '2022-06-16 18:41:48.145000', 'where do go', NULL, 'MESSAGE', 'sorabh', b'0'),
(163, '2022-06-16 18:42:05.227000', 'everywhere i go', NULL, 'MESSAGE', 'sorabh', b'0'),
(164, '2022-06-16 18:42:15.356000', 'shut up man', NULL, 'MESSAGE', 'admin', b'0'),
(187, '2022-06-16 19:26:59.028000', 'hello', NULL, 'MESSAGE', 'sorabh', b'0'),
(207, '2022-06-16 19:39:46.728000', 'soarabh', NULL, 'MESSAGE', 'admin', b'0'),
(211, '2022-06-16 19:44:01.762000', 'my name is ', NULL, 'MESSAGE', 'admin', b'0'),
(216, '2022-06-16 19:45:32.983000', 'asfkadsf', NULL, 'MESSAGE', 'admin', b'0'),
(221, '2022-06-16 19:45:46.996000', 'works', NULL, 'MESSAGE', 'admin', b'0'),
(225, '2022-06-16 19:46:08.805000', 'rock', NULL, 'MESSAGE', 'admin', b'0'),
(228, '2022-06-16 19:47:05.726000', 'asfadfl', NULL, 'MESSAGE', 'admin', b'0'),
(232, '2022-06-16 20:15:12.163000', 'Working', NULL, 'MESSAGE', 'admin', b'0'),
(235, '2022-06-16 20:15:45.361000', 'working', 'admin', 'MESSAGE', 'sorabh', b'0'),
(240, '2022-06-16 20:16:42.438000', 'good day sir', 'sorabh', 'MESSAGE', 'admin', b'0'),
(242, '2022-06-16 21:25:39.905000', 'i got to go', 'sorabh', 'MESSAGE', 'admin', b'0'),
(338, '2022-06-16 23:16:39.691000', 'What is your problem', NULL, 'MESSAGE', 'sorabh', b'0'),
(525, '2022-06-17 18:31:06.287000', 'skdfa', 'sorabh', 'MESSAGE', 'admin', b'0'),
(528, '2022-06-17 18:37:02.746000', 'good morning', 'happy', 'MESSAGE', 'admin', b'0'),
(546, '2022-06-17 18:52:05.454000', 'kajdskfa', 'sorabh', 'MESSAGE', 'admin', b'0'),
(548, '2022-06-17 19:00:18.035000', 'how are you? happy', 'happy', 'MESSAGE', 'admin', b'0'),
(551, '2022-06-17 20:05:02.361000', 'hello', 'happy', 'MESSAGE', 'admin', b'0'),
(558, '2022-06-17 20:12:06.386000', 'sorabh', 'happy', 'MESSAGE', 'admin', b'0'),
(564, '2022-06-17 20:13:01.436000', 'i am going to sleep now', 'happy', 'MESSAGE', 'admin', b'0'),
(569, '2022-06-17 20:13:49.774000', 'good day', 'happy', 'MESSAGE', 'admin', b'0'),
(570, '2022-06-17 20:14:05.985000', 'good morning', 'admin', 'MESSAGE', 'happy', b'0'),
(571, '2022-06-17 20:15:04.803000', 'wola', 'admin', 'MESSAGE', 'happy', b'0'),
(575, '2022-06-17 20:15:49.950000', 'doma', 'admin', 'MESSAGE', 'happy', b'0'),
(579, '2022-06-17 20:18:22.559000', 'Hi, Sir how are you?', 'admin', 'MESSAGE', 'happy', b'0'),
(580, '2022-06-17 20:19:14.776000', 'i don\'t understand', 'happy', 'MESSAGE', 'admin', b'0'),
(583, '2022-06-17 20:21:30.609000', 'What are you saying?', 'happy', 'MESSAGE', 'admin', b'0'),
(611, '2022-06-17 22:40:45.763000', 'working', 'happy', 'MESSAGE', 'admin', b'0'),
(613, '2022-06-17 22:42:24.958000', 'I wonder if we can talk', 'happy', 'MESSAGE', 'admin', b'0'),
(614, '2022-06-17 22:43:13.125000', 'can we talk', 'happy', 'MESSAGE', 'admin', b'0'),
(621, '2022-06-17 22:48:00.021000', 'Yes, i want to talk with you', 'happy', 'MESSAGE', 'admin', b'0'),
(623, '2022-06-17 22:51:08.832000', 'Ok, fine. i will talk to you tomorrow', 'admin', 'MESSAGE', 'happy', b'0'),
(628, '2022-06-17 22:52:22.480000', 'I want to talk, today.', 'happy', 'MESSAGE', 'admin', b'0'),
(629, '2022-06-17 22:53:01.187000', 'no man, i am busy', 'admin', 'MESSAGE', 'happy', b'0'),
(650, '2022-06-17 23:03:55.238000', 'are you sure don\'t want to talk?', 'happy', 'MESSAGE', 'admin', b'0'),
(652, '2022-06-17 23:06:09.438000', 'Think again.', 'happy', 'MESSAGE', 'admin', b'0'),
(655, '2022-06-17 23:06:36.594000', 'many why are you messing with me?', 'admin', 'MESSAGE', 'happy', b'0'),
(661, '2022-06-17 23:08:39.870000', 'Can you please think it again?', 'happy', 'MESSAGE', 'admin', b'0'),
(662, '2022-06-17 23:09:39.638000', 'kick', 'happy', 'MESSAGE', 'admin', b'0'),
(663, '2022-06-17 23:11:42.949000', 'I don\'t understand what is wrong here', 'happy', 'MESSAGE', 'admin', b'0'),
(666, '2022-06-17 23:15:45.112000', 'leave it, man', 'admin', 'MESSAGE', 'happy', b'0'),
(667, '2022-06-17 23:16:34.574000', 'please, send me message, whenever online', 'sorabh', 'MESSAGE', 'admin', b'0'),
(675, '2022-06-17 23:26:08.501000', 'hello admin.', 'sorabhadmin', 'MESSAGE', 'sorabh', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `feedback_rating`
--

CREATE TABLE `feedback_rating` (
  `id` int(11) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  `user_feedback_msg` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `phone_id` int(11) DEFAULT NULL,
  `is_visted` bit(1) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `feedback_rating`
--

INSERT INTO `feedback_rating` (`id`, `rate`, `user_feedback_msg`, `user_id`, `order_id`, `phone_id`, `is_visted`, `date`) VALUES
(2, 3, 'Good product, I really love this phone. We love it', 2, 3, 1, b'0', '2022-06-11 23:32:45.000000'),
(3, 4, 'This phone rocks', 2, 3, 2, b'0', '2022-06-10 14:33:27.000000'),
(4, 4, 'I purchase at 30% discount. i loved this product', 2, 3, 3, b'0', '2022-06-10 13:33:41.000000'),
(5, 3, 'I purchase this phone for my friend, customer service was awesome.', 2, 4, 2, b'0', '2022-06-11 13:33:50.000000'),
(6, 2, 'Broken item received', 2, 4, 3, b'0', '2022-06-12 13:32:22.657000');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `expected_delivery_days` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `payment_method` varchar(60) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `delivery_date` datetime(6) DEFAULT NULL,
  `is_visited` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `created_date`, `expected_delivery_days`, `message`, `payment_method`, `status`, `address_id`, `user_id`, `delivery_date`, `is_visited`) VALUES
(3, '2022-06-01 23:33:59.647000', 7, 'something like it', 'COD', 'DELIVERED', 3, 2, '2022-06-05 19:18:10.000000', b'1'),
(4, '2022-06-01 23:34:49.274000', 30, 'Go for it instantly', 'COD', 'DELIVERED', 2, 2, NULL, b'1'),
(5, '2022-06-01 23:35:01.295000', 1, '', 'OP', 'REJECTED', 3, 2, NULL, b'1'),
(6, '2022-06-01 23:37:20.808000', 5, '', 'OP', 'APPROVED', 3, 2, '2022-06-13 15:46:23.293000', b'1'),
(7, '2022-06-01 23:41:54.070000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'1'),
(8, '2022-06-01 23:45:18.831000', 30, '', 'OP', 'APPROVED', 3, 2, NULL, b'1'),
(9, '2022-06-01 23:47:08.042000', 5, '', 'OP', 'SHIPPED', 3, 2, NULL, b'1'),
(10, '2022-06-01 23:51:19.501000', 5, '', 'OP', 'SHIPPED', 2, 2, NULL, b'1'),
(11, '2022-06-01 23:52:44.212000', 30, '', 'OP', 'APPROVED', 2, 2, NULL, b'1'),
(12, '2022-06-01 23:54:08.493000', 5, '', 'OP', 'SHIPPED', 3, 2, NULL, b'1'),
(13, '2022-06-01 23:58:27.121000', 5, '', 'OP', 'SHIPPED', 2, 2, NULL, b'1'),
(14, '2022-06-02 00:17:54.845000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(15, '2022-06-02 00:40:50.411000', 7, '', 'OP', 'DELIVERED', 3, 2, '2022-06-18 14:31:26.057000', b'1'),
(16, '2022-06-02 00:41:12.687000', NULL, 'going to pay you.', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(17, '2022-06-02 00:43:42.750000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(18, '2022-06-02 18:51:09.949000', NULL, 'asdfklajsdfljad akf', 'COD', 'REQUESTED', 3, 2, NULL, b'0'),
(19, '2022-06-02 18:54:01.900000', NULL, 'asdfklajsdfljad akf', 'OP', 'REQUESTED', 3, 2, NULL, b'1'),
(20, '2022-06-02 19:01:49.056000', 1, '', 'OP', 'SHIPPED', 2, 2, NULL, b'1'),
(21, '2022-06-02 19:04:57.634000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(22, '2022-06-02 19:26:14.144000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(23, '2022-06-02 19:29:42.779000', 5, '', 'OP', 'DELIVERED', 2, 2, '2022-06-15 00:54:43.440000', b'1'),
(24, '2022-06-02 20:22:43.194000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(25, '2022-06-03 09:50:24.270000', NULL, '', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(26, '2022-06-03 09:50:34.582000', 1, '', 'OP', 'APPROVED', 2, 2, NULL, b'1'),
(27, '2022-06-03 10:02:22.286000', 7, '', 'OP', 'REJECTED', 3, 2, NULL, b'1'),
(28, '2022-06-03 10:02:46.886000', 5, '', 'OP', 'APPROVED', 3, 2, NULL, b'1'),
(29, '2022-06-13 00:31:04.398000', 5, 'Please send fast.', 'COD', 'APPROVED', 8, NULL, NULL, b'1'),
(31, '2022-06-14 19:04:15.726000', NULL, 'Last item worked well.', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(32, '2022-06-14 19:19:44.550000', NULL, 'adfkasd fsaskdfa', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(33, '2022-06-14 19:21:29.338000', NULL, '', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(34, '2022-06-14 19:28:25.972000', NULL, 'Send me the product.', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(35, '2022-06-14 19:31:01.810000', NULL, 'Good product', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(36, '2022-06-14 19:37:10.128000', NULL, 'asjlfkajslkd akldf', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(37, '2022-06-14 19:38:40.447000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(38, '2022-06-14 19:41:36.271000', NULL, 'Get me my item', 'OP', 'REQUESTED', 3, 2, NULL, b'0'),
(39, '2022-06-14 19:48:14.646000', NULL, '', 'OP', 'REQUESTED', 2, 2, NULL, b'0'),
(40, '2022-06-14 19:49:54.459000', 2, '', 'OP', 'DELIVERED', 2, 2, '2022-06-14 23:01:51.070000', b'1'),
(41, '2022-06-18 20:23:36.771000', NULL, '', 'OP', 'REQUESTED', 3, 2, NULL, b'1'),
(42, '2022-06-18 20:24:54.713000', NULL, '', 'COD', 'REQUESTED', 3, 2, NULL, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL,
  `amount` float NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `phone_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id`, `amount`, `order_id`, `quantity`, `phone_id`) VALUES
(3, 17104, 3, 1, 1),
(4, 19018, 3, 1, 2),
(5, 16483, 3, 1, 3),
(6, 19018, 4, 2, 2),
(7, 16483, 4, 1, 3),
(8, 19018, 5, 2, 2),
(9, 16483, 5, 1, 3),
(10, 19018, 6, 2, 2),
(11, 16483, 6, 1, 3),
(12, 19018, 7, 2, 2),
(13, 16483, 7, 3, 3),
(14, 19018, 8, 2, 2),
(15, 16483, 8, 3, 3),
(16, 17104, 9, 1, 1),
(17, 17104, 10, 1, 1),
(18, 17104, 11, 1, 1),
(19, 17104, 12, 1, 1),
(20, 17104, 13, 1, 1),
(21, 17104, 14, 1, 1),
(22, 17104, 15, 1, 1),
(23, 17104, 16, 1, 1),
(24, 17104, 17, 1, 1),
(25, 19018, 17, 1, 2),
(26, 21762, 17, 1, 4),
(27, 19018, 18, 1, 2),
(28, 21762, 18, 1, 4),
(29, 19018, 19, 1, 2),
(30, 21762, 19, 1, 4),
(31, 19018, 20, 1, 2),
(32, 17104, 21, 1, 1),
(33, 19018, 22, 1, 2),
(34, 16483, 23, 1, 3),
(35, 16483, 24, 1, 3),
(36, 19018, 25, 1, 2),
(37, 19018, 26, 1, 2),
(38, 19018, 27, 1, 2),
(39, 16483, 28, 1, 3),
(40, 19018, 29, 1, 2),
(41, 16483, 29, 1, 3),
(43, 19018, 31, 1, 2),
(44, 19018, 32, 1, 2),
(45, 19018, 33, 1, 2),
(46, 16483, 34, 1, 3),
(47, 19018, 35, 1, 2),
(48, 21762, 36, 1, 4),
(49, 21762, 37, 1, 4),
(50, 17104, 38, 1, 1),
(51, 17104, 39, 1, 1),
(52, 19018, 40, 1, 2),
(53, 19018, 41, 1, 2),
(54, 19018, 42, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modify_date` datetime(6) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `razor_order_id` varchar(255) DEFAULT NULL,
  `razor_payment_id` varchar(255) DEFAULT NULL,
  `amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `order_id`, `created_date`, `modify_date`, `status`, `razor_order_id`, `razor_payment_id`, `amount`) VALUES
(3, 14, '2022-06-02 00:17:55.871000', '2022-06-14 17:55:14.258000', 'PAID', 'order_JcKochbEL0Es7U', NULL, 17104),
(4, 15, '2022-06-02 00:40:51.456000', '2022-06-18 14:31:14.412000', 'PAID', 'order_JcLCqKYSpfOrou', 'pay_JittrRRltFtOjn', 17104),
(5, 16, '2022-06-02 00:41:13.413000', '2022-06-14 17:55:14.280000', 'PENDING', 'order_JcLDE9txiedX02', NULL, 17104),
(6, 17, '2022-06-02 00:43:43.405000', '2022-06-14 17:55:14.281000', 'PAID', 'order_JcLFrvEau20pU4', 'pay_JcLG6tbMB3YWKQ', 57884),
(7, 18, '2022-06-02 18:51:11.818000', '2022-06-14 17:55:14.283000', 'PENDING', 'order_JcdmcvVuX0UIBx', NULL, 40780),
(8, 19, '2022-06-02 18:54:02.693000', '2022-06-14 17:55:14.286000', 'PAID', 'order_JcdpdYP8yfwavi', 'pay_JcdqGU7RQdb2XQ', 40780),
(9, 20, '2022-06-02 19:01:50.256000', '2022-06-14 17:55:14.287000', 'PAID', 'order_JcdxrmcwNTHqOY', 'pay_Jcdy3wkoptMxRi', 19018),
(10, 21, '2022-06-02 19:04:58.359000', '2022-06-14 17:55:14.289000', 'PAID', 'order_Jce1BH2HXBEb17', 'pay_Jce1P4XwNzAgbP', 17104),
(11, 22, '2022-06-02 19:26:15.387000', '2022-06-14 17:55:14.290000', 'PAID', 'order_JceNf5L4lkCUah', 'pay_JceNp7BhBNHTqA', 19018),
(12, 23, '2022-06-02 19:29:43.883000', '2022-06-14 17:55:14.293000', 'PAID', 'order_JceRKfSTBrCi24', 'pay_JceRWTLMXFCHUy', 16483),
(13, 24, '2022-06-02 20:22:46.148000', '2022-06-14 17:55:14.295000', 'PAID', 'order_JcfLMG29ZnEPuP', 'pay_JcfLTnCqcDZdbw', 16483),
(15, 32, '2022-06-14 19:19:45.949000', '2022-06-14 22:36:58.954000', 'PAID', 'order_JhOgHNxKgBuBrS', 'pay_JhRvtbhueQfIKD', 19018),
(16, 33, '2022-06-14 19:21:30.616000', '2022-06-14 22:36:58.985000', 'PENDING', 'order_JhOi7tT7AUi9v6', NULL, 19018),
(17, 34, '2022-06-14 19:28:27.297000', '2022-06-14 22:36:58.988000', 'PENDING', 'order_JhOpSTMC79JTuH', NULL, 16483),
(18, 35, '2022-06-14 19:31:02.832000', '2022-06-14 22:36:58.991000', 'PENDING', 'order_JhOsCJ9Z7PiKIy', NULL, 19018),
(19, 36, '2022-06-14 19:37:11.801000', '2022-06-14 19:37:11.801000', 'PENDING', 'order_JhOyhFwqBGg9UZ', NULL, 21762),
(20, 37, '2022-06-14 19:38:41.260000', '2022-06-14 19:38:41.260000', 'PENDING', 'order_JhP0Gkf05uqaMb', NULL, 21762),
(21, 38, '2022-06-14 19:41:37.014000', '2022-06-14 19:41:50.715000', 'PAID', 'order_JhP3MgLhGZTAEa', 'pay_JhP3WFAsUGRilA', 17104),
(22, 39, '2022-06-14 19:48:15.718000', '2022-06-14 19:48:26.494000', 'PAID', 'order_JhPANwcCDdZgnD', 'pay_JhPAUAAoKKW6IK', 17104),
(23, 40, '2022-06-14 19:49:55.301000', '2022-06-14 19:51:39.681000', 'PAID', 'order_JhPC8OsGLJUoiL', 'pay_JhPDt9PpfNFgsP', 19018),
(24, 7, '2022-06-14 22:52:59.300000', '2022-06-14 22:52:59.300000', 'PENDING', 'order_JhSJWBEMyVsjkM', NULL, 35501),
(25, 8, '2022-06-14 22:54:22.247000', '2022-06-14 22:54:22.247000', 'PENDING', 'order_JhSKylTlLkmCA2', NULL, 35501),
(26, 9, '2022-06-14 22:58:35.616000', '2022-06-14 22:59:01.886000', 'PAID', 'order_JhSPRIz9glNvlp', 'pay_JhSPotyy3cJFAG', 17104),
(27, 41, '2022-06-18 20:23:38.581000', '2022-06-18 20:23:38.581000', 'PENDING', 'order_JizuCdyya07inT', NULL, 19018),
(28, 6, '2022-06-18 20:41:27.119000', '2022-06-18 20:41:27.119000', 'PENDING', 'order_Jj0D0fgwPa2wQ7', NULL, 35501);

-- --------------------------------------------------------

--
-- Table structure for table `phone`
--

CREATE TABLE `phone` (
  `id` int(11) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `publish_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phone`
--

INSERT INTO `phone` (`id`, `description`, `image`, `price`, `publish_date`, `title`) VALUES
(1, 'Superb Smartphone with superb design.\r\n\r\nFeatures:\r\n4GB RAM, 64GB ROM, 4G Lite supported, 6.6 inch display, 5000 Mah Battery.', 'phone-1.jpg', 17104, '2022-05-30 18:09:32.752000', 'UIGO Nexa'),
(2, 'Processor: snapdragon 405,\r\nMemory: 4GB,\r\nStorage: 32GB,\r\nBattery: 40Wh,\r\nGorilla Glass 4\r\n', 'UIGO1Ultra.jpg', 19018, '2022-05-30 18:09:32.770000', 'UIGO 1 Ultra'),
(3, '<p>6.1-inch (15.5 cm diagonal) Super tRetina XDR display\r\nCeramic Shield, tougher than any smartphone glass\r\nA14 Bionic chip, the fastest chip ever in a smartphone\r\nAdvanced dual-camera system with 12MP Ultra Wide and Wide cameras; Night mode, Deep Fusion, Smart HDR 3, 4K Dolby Vision HDR recording</p>\r\n<p>12MP TrueDepth front camera with Night mode, 4K Dolby Vision HDR recording\r\nIndustry-leading IP68 water resistance\r\nSupports MagSafe accessories for easy attach and faster wireless charging\r\niOS with redesigned widgets on the Home screen, all-new App Library, App Clips and more</p>', 'UIGOALPHA1.jpg', 16483, '2022-05-30 18:09:32.772000', 'UIGO ALPHA 1'),
(4, 'Processor: G90\r\nMemory: 3GB\r\nROM: 16GB\r\nBattery: 4080Mah', 'UIGODelta2s.jpg', 21762, '2022-05-30 18:09:32.775000', 'UIGO Delta 2s');

-- --------------------------------------------------------

--
-- Table structure for table `return_order`
--

CREATE TABLE `return_order` (
  `id` int(11) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `return_reason` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `modify_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `return_order`
--

INSERT INTO `return_order` (`id`, `created_date`, `return_reason`, `status`, `order_id`, `modify_date`) VALUES
(1, '2022-06-14 15:24:05.293000', 'Damaged Product Received', 'RESOLVED', 15, '2022-06-18 16:17:08.199000'),
(2, '2022-06-18 14:20:56.506000', 'Damaged Item Received : I have received a damaged product', 'REQUESTED', 3, '2022-06-18 14:20:56.506000');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `activation_key` varchar(255) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `full_name` varchar(60) DEFAULT NULL,
  `password` varchar(120) NOT NULL,
  `phone_no` varchar(16) DEFAULT NULL,
  `role` varchar(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `activation_key`, `email`, `full_name`, `password`, `phone_no`, `role`, `status`, `username`) VALUES
(1, NULL, 'admin@admin.in', 'admin', '$2a$10$Fo9V6p2SUiRMWHuD785ZTeHBavAmMH1IBS76/Jz/ZAgf9SR2klwVS', '0000000000', 'ROLE_ADMIN', 1, 'admin'),
(2, NULL, 'sorabh@customer.in', 'Sorabh', '$2a$10$IKfsisH/xpGe9D0HbULtAOL0wxqplGIQ.QRWrd5qEtbEFVJZrvufi', '0000000000', 'ROLE_CUSTOMER', 1, 'sorabh'),
(3, '', 'neeraj@subscriber.in', 'Neeraj', '$2a$10$FwIb7CGqoCFjSZOBZI8Vju4v8XnJ9hX9f0VpauFtmMlVo/VJ.Jnoi', '9594827837', 'ROLE_SUBSCRIBER', 1, 'neeraj'),
(22, 'SOS778486', 'sorabh.vasistha@gmail.com', 'happy paul', '$2a$10$Z2c2mqcaFiX8qj./MMlK/ewz8SvilxCyYn81ySOsqP4R04aW/htIS', '9393939393', 'ROLE_CUSTOMER', 1, 'happy'),
(24, 'SOS746386', '', '', '$2a$10$8.Pl4sHxjVkkLIbe2NxEle0L92kj9I23vdP31EMeHD9Ih26VPLdW.', '', 'ROLE_ADMIN', 0, 'sorabhadmin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKng28lgn25vvh6kwf3obtng6yx` (`phone_id`),
  ADD KEY `FKkjv4yjjdlt4hd9ayey6mti09m` (`user_id`);

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback_rating`
--
ALTER TABLE `feedback_rating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK40ujr2k539qaa8venvi92sunn` (`user_id`),
  ADD KEY `FKqbny93kowux5kfi5dqo9xp4s4` (`order_id`),
  ADD KEY `FKsmhibga1gq29pui42sco5vka8` (`phone_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf5464gxwc32ongdvka2rtvw96` (`address_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs7k485wawg3s5htgqj5fgalex` (`phone_id`),
  ADD KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jja7bpik8tl2prft0n9nknt5g` (`razor_order_id`),
  ADD UNIQUE KEY `UK_8mpqi5o0sg8x1xtvu2gvs65xe` (`razor_payment_id`),
  ADD KEY `FKlouu98csyullos9k25tbpk4va` (`order_id`);

--
-- Indexes for table `phone`
--
ALTER TABLE `phone`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `return_order`
--
ALTER TABLE `return_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd2m1bv0p2swr9vqsmicjyou4o` (`order_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=686;

--
-- AUTO_INCREMENT for table `feedback_rating`
--
ALTER TABLE `feedback_rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `phone`
--
ALTER TABLE `phone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `return_order`
--
ALTER TABLE `return_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `FKkjv4yjjdlt4hd9ayey6mti09m` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKng28lgn25vvh6kwf3obtng6yx` FOREIGN KEY (`phone_id`) REFERENCES `phone` (`id`);

--
-- Constraints for table `feedback_rating`
--
ALTER TABLE `feedback_rating`
  ADD CONSTRAINT `FK40ujr2k539qaa8venvi92sunn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqbny93kowux5kfi5dqo9xp4s4` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKsmhibga1gq29pui42sco5vka8` FOREIGN KEY (`phone_id`) REFERENCES `phone` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKf5464gxwc32ongdvka2rtvw96` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FKs7k485wawg3s5htgqj5fgalex` FOREIGN KEY (`phone_id`) REFERENCES `phone` (`id`),
  ADD CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FKlouu98csyullos9k25tbpk4va` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `return_order`
--
ALTER TABLE `return_order`
  ADD CONSTRAINT `FKd2m1bv0p2swr9vqsmicjyou4o` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
