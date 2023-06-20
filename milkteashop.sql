-- --------------------------------------------------------
-- Host:                         192.168.1.200
-- Server version:               8.0.32 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table milkteashop.admin: ~2 rows (approximately)
REPLACE INTO `admin` (`id`, `username`, `password`, `name`, `email`, `phone`, `level`, `token`) VALUES
	(1, 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ha', NULL, NULL, 0, 'GRNgf1kllskvLRG8W5VY1AgETQYYFsbT'),
	(2, 'admin2', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'dam', NULL, NULL, 1, '8VklvCb8o3wiBsdYvqDdPpuHmWqvVF-t'),
	(3, 'admin3', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'trungtinh', 'tinhle2772002@gmail.com', '0986852943', 2, 'ASCtceqhS7bCP3KL7A2FJm10xkw87MnY');

-- Dumping data for table milkteashop.blogs: ~9 rows (approximately)
-- Dumping data for table milkteashop.categories: ~5 rows (approximately)
REPLACE INTO `categories` (`id`, `name`, `status`) VALUES
	(1, 'Trà sữa ', 0),
	(2, 'Trà', 0),
	(3, 'Đá xay', 0),
	(4, 'Nước ép', 0),
	(5, 'Sữa chua', 0);

-- Dumping data for table milkteashop.coupons: ~7 rows (approximately)
REPLACE INTO `coupons` (`id`, `code`, `percent`, `max_price_sale`, `quantity`, `start_date`, `end_date`, `min_price_order`, `date_regis_acc`, `min_num_order`, `content`) VALUES
	(1, 'op', 12, 12000, 50, '2022-12-21', '2023-01-09', 20000, NULL, NULL, NULL),
	(4, 'ABVVC', 25, 50000, 19, '2022-12-21', '2023-01-09', 0, '1970-01-01', 0, ''),
	(5, 'ABVVC', 25, 50000, 20, '2022-12-27', '2023-01-09', 0, '1970-01-01', 0, ''),
	(6, '61857', 212, 500000, 84512, '2022-12-14', '2023-01-09', 0, '2022-01-01', 0, ''),
	(10, 'sad', 12, 123, 123, '2022-12-19', '2023-06-19', 123, '2022-01-01', 0, ''),
	(15, 'hehe', 25, 60000, 100, '2023-01-09', '2023-01-09', 100000, '2022-01-01', 0, ''),
	(16, 'hehe', 42, 30000, 44, '2023-01-02', '2023-02-04', 0, '2022-01-01', 0, '');

-- Dumping data for table milkteashop.forgot_pass: ~0 rows (approximately)
REPLACE INTO `forgot_pass` (`id`, `token`, `user_id`, `send_at`) VALUES
	(1, 'BfMeUEI89Vx7pWd0R6MDUeIaL59XRW1Mb73LEtLwbXNKP', '18', '2023-06-20 03:16:12'),
	(2, 'uTx15m5qQImt4EOzX8C2XvUPxlsR3YuXAXgoeOP1XIPFj', '18', '2023-06-20 03:21:39');

-- Dumping data for table milkteashop.forgot_pass_count: ~0 rows (approximately)
REPLACE INTO `forgot_pass_count` (`id`, `email`, `count`, `date`) VALUES
	(1, 'buithanhdam02@gmail.com', 3, '2023-06-20');

-- Dumping data for table milkteashop.noti_coupon: ~0 rows (approximately)

-- Dumping data for table milkteashop.noti_order: ~0 rows (approximately)

-- Dumping data for table milkteashop.orders: ~13 rows (approximately)
REPLACE INTO `orders` (`id`, `user_id`, `name`, `phone`, `time`, `address`, `note`, `coupon_id`, `total`, `status`) VALUES
	(1, 1, 'HÃ ', '0344558306', '2022-07-04 00:00:00', 'abc', '123', NULL, 45000, '2'),
	(3, 3, 'HÃ ', '0344558306', '2022-12-16 01:01:22', '123', '123', NULL, 69000, '2'),
	(4, 3, 'Nguyễn Vũ Mạnh Hà', '0344558306', '2023-01-04 15:20:05', '123', '123', NULL, 135000, '2'),
	(5, 5, 'H-Bell', '0344558306', '2023-01-06 19:28:26', '123', '', NULL, 165000, '2'),
	(6, 5, 'H-Bell', '0344558306', '2023-01-06 19:35:33', '123', '1', NULL, 7500, '2'),
	(7, 6, 'Nguyễn Vũ Mạnh Hà', '0344558306', '2023-01-07 02:25:50', '1235', '', NULL, 19500, '2'),
	(8, 3, 'Nguyễn Vũ Mạnh Hà', '0344558306', '2023-01-08 00:57:37', 'bcas', '', 4, 21937.5, '2'),
	(9, 9, 'Hà', '0344558306', '2023-01-09 02:06:29', '213', '', NULL, 48000, '2'),
	(10, 16, 'Hà', '0344558306', '2023-01-10 08:36:38', 'đh nông lâm', '', NULL, 25500, '2'),
	(11, 18, 'Dam Thanh', '0377184642', '2023-04-23 11:10:43', '11/22 Hồ Chí Minh Quận 11 Phường 4', '', NULL, 75000, '4'),
	(12, 18, 'Dam Thanh', '0377184642', '2023-04-23 11:24:36', '11/18-Hồ Chí Minh-Quận 12-Phường Thạnh Xuân', '', NULL, 50000, '4'),
	(13, 18, 'Dam Thanh', '0377184642', '2023-04-23 11:28:43', 'khong biet nua-Hồ Chí Minh-Quận Tân Bình-Phường 10', 'oke bro', NULL, 71000, '2'),
	(14, 18, 'Dam Thanh', '0377184642', '2023-05-05 11:27:40', 'oke-Hồ Chí Minh-Huyện Cần Giờ-Xã Lý Nhơn', '', NULL, 51000, '3');

-- Dumping data for table milkteashop.order_detail: ~10 rows (approximately)
REPLACE INTO `order_detail` (`id`, `order_id`, `product_size_id`, `quantity`) VALUES
	(1, 1, 9, 1),
	(2, 3, 7, 1),
	(3, 4, 9, 1),
	(4, 4, 9, 1),
	(5, 5, 9, 1),
	(6, 5, 9, 2),
	(7, 6, 76, 1),
	(8, 7, 158, 1),
	(9, 8, 110, 1),
	(10, 9, 95, 1),
	(11, 10, 110, 1),
	(12, 10, 155, 1),
	(13, 11, 96, 1),
	(14, 11, 157, 1),
	(15, 11, 205, 1),
	(16, 12, 157, 1),
	(17, 12, 96, 1),
	(18, 13, 96, 1),
	(19, 13, 157, 1),
	(20, 14, 96, 1);

-- Dumping data for table milkteashop.order_logistic: ~2 rows (approximately)
REPLACE INTO `order_logistic` (`order_id`, `logistic_id`) VALUES
	(13, '2136ba724e4845cea0f9d75fa8d58de7'),
	(14, '12ff92192e4246ba820560d8b792ee4c');

-- Dumping data for table milkteashop.products: ~120 rows (approximately)
REPLACE INTO `products` (`id`, `name`, `category_id`, `status`) VALUES
	(1, 'Bánh Oreo Xay Cùng Cà Phê Cappuccino', 3, 0),
	(4, 'Cam Ép', 4, 0),
	(6, 'Caramen Chảy', 3, 0),
	(7, 'Dâu Ép', 4, 1),
	(8, 'Dứa Ép', 4, 0),
	(12, 'Kem Trà Xanh', 3, 0),
	(18, 'Sinh Tố Chanh', 4, 0),
	(19, 'Sinh Tố Dâu', 4, 0),
	(20, 'Sinh Tố Xoài', 4, 0),
	(21, 'Sô-Cô-La Cà Phê Đá Xay', 3, 0),
	(22, 'Sô-Cô-La Xay Cùng Hạnh Nhân Và Espresso', 3, 0),
	(23, 'Sữa Chua Phúc Bồn Tử Đác Cam', 5, 0),
	(24, 'Sữa Chua Xoài Đác Thơm', 5, 0),
	(25, 'Táo Ép', 4, 0),
	(26, 'Táo Và Dâu Ép', 4, 0),
	(27, 'Thơm Và Dâu Ép', 4, 0),
	(28, 'Trà Đào Đá Xay', 1, 1),
	(29, 'Trà Đào Phúc Long', 1, 1),
	(30, 'Trà Đào Sữa', 1, 1),
	(31, 'Trà Hoa Hồng', 1, 1),
	(32, 'Trà Lài Đác Thơm', 1, 1),
	(33, 'Trà Nhãn - Lài', 1, 1),
	(34, 'Trà Nhãn - Sen', 1, 1),
	(35, 'Trà Ô Long Dâu', 1, 1),
	(36, 'Trà Ô Long Mãng Cầu', 1, 1),
	(37, 'Trà Ô Long Sữa', 1, 1),
	(38, 'Trà Sen Vàng (Củ Năng)', 1, 1),
	(39, 'Trà Sen Vàng (Sen)', 1, 1),
	(40, 'Trà Sữa Phúc Long', 1, 1),
	(41, 'Trà Thạch Vãi', 1, 1),
	(42, 'Trà Thanh Đào', 1, 1),
	(43, 'Trà Thảo Mộc', 1, 1),
	(44, 'Trà Vãi - Sen', 1, 1),
	(45, 'Trà Vãi Lài', 1, 1),
	(46, 'Trà Xanh Đá Xay', 3, 0),
	(47, 'Choco Ngũ Cốc Kem Cafe', 2, 1),
	(48, 'Creme Brulee Latte', 1, 1),
	(49, 'Creme Brulee Matcha', 1, 1),
	(50, 'Creme Brulee Milk Tea', 1, 1),
	(51, 'Creme Brulee Tea', 1, 1),
	(52, 'Đào Latte', 1, 1),
	(53, 'Dâu Latte', 1, 1),
	(54, 'Dolce Milk Tea Float', 1, 1),
	(55, 'Grass Jelly Milk Coffee', 2, 1),
	(56, 'Hồng Trà', 1, 1),
	(57, ' Hồng Trà Bưởi Mật Ong', 1, 1),
	(58, 'Hồng Trà Dâu', 1, 1),
	(59, 'Hồng Trà Ngũ Cốc Kem Cafe', 1, 1),
	(60, 'Instant Milk Tea – Original ', 1, 1),
	(61, 'Instant Milk Tea – Strawberry ', 1, 1),
	(62, ' Kem Tuyết Socola', 3, 1),
	(63, 'Kem Tuyết Trà Xanh Nhật Bản', 3, 1),
	(64, 'Matcha Đậu Đỏ', 3, 1),
	(65, 'Ô Long Kem Phô Mai', 1, 1),
	(66, ' Ô Long Thái Cực', 1, 1),
	(67, ' Ô Long Xoài Kem Cà Phê ', 1, 1),
	(68, ' Okinawa Latte', 1, 1),
	(69, 'Okinawa Latte Float', 1, 1),
	(70, 'Okinawa Oreo Cream Milk Tea', 1, 1),
	(71, 'Oolong Trân Châu Baby Kem Café', 1, 1),
	(72, 'Probi Bưởi Trân Châu Sương Mai', 1, 1),
	(73, 'Probi Xoài Trân Châu Sương Mai', 1, 1),
	(74, ' Royal Pearl Milk Coffee', 2, 0),
	(75, ' Strawberry Milk Tea', 1, 1),
	(76, 'Sữa Chua Dâu Tằm Hạt Dẻ', 5, 0),
	(77, 'Sữa Chua Dâu Tằm Hoàng Kim', 5, 1),
	(78, 'Sữa Chua Mận Hạt Sen', 5, 0),
	(79, 'Sữa Chua Trắng', 5, 0),
	(80, 'Sữa Tươi Long Nhãn Táo Đỏ', 1, 1),
	(81, ' Sữa Tươi Okinawa', 1, 1),
	(82, ' Sữa Tươi Trân Châu Baby Kem Café', 1, 1),
	(83, 'Tiger Sugar', 1, 1),
	(84, 'Toffee Black Tea Latte', 1, 1),
	(85, 'Toffee Chocolatea', 1, 1),
	(86, ' Trà Alisan Gong Cha', 1, 1),
	(87, ' Trà Bí Đao Gong Cha', 1, 1),
	(88, ' Trà Đào', 1, 1),
	(89, ' Trà Đào Bưởi Hồng Trân Châu Baby', 1, 1),
	(90, 'Trà Dâu Tầm Pha Lê Tuyết', 1, 1),
	(91, 'Trà Đen Gong Cha', 1, 1),
	(92, 'Trà dứa nhiệt đới', 1, 1),
	(93, 'Trà Oolong Gong Cha', 1, 1),
	(94, 'Trà Sữa', 1, 1),
	(95, 'Trà Sữa Ba Anh Em', 1, 1),
	(96, 'Trà Sữa Bạc Hà', 1, 1),
	(97, 'Trà Sữa Chocolate', 1, 1),
	(98, 'Trà Sữa Đào', 1, 1),
	(99, 'Trà Sữa Dâu Tây', 1, 1),
	(100, 'Trà Sữa Dolce Trân Châu Hoàng Kim', 1, 1),
	(101, 'Trà Sữa Gạo Rang', 1, 1),
	(102, 'Trà Sữa Gạo Rang Okinawa', 1, 1),
	(103, 'Trà Sữa Hạnh Phúc', 1, 1),
	(104, 'Trà Sữa Hokkaido', 1, 1),
	(105, ' Trà Sữa Khoai Môn', 1, 1),
	(106, 'Trà Sữa Kim Cương Đen Okinawa', 1, 1),
	(107, 'Trà Sữa Matcha', 1, 1),
	(108, ' Trà Sữa Matcha Đậu Đỏ', 1, 1),
	(109, 'Trà Sữa Ô Long', 1, 1),
	(110, 'Trà Sữa Okinawa', 1, 1),
	(111, 'Trà Sữa Oolong', 1, 1),
	(112, 'Trà sữa Oolong 3J', 1, 1),
	(113, 'Trà Sữa Panda', 1, 1),
	(114, 'Trà Sữa Pudding Đậu Đỏ', 1, 1),
	(115, 'Trà Sữa Socola', 1, 1),
	(116, 'Trà Sữa Sương Sáo', 1, 1),
	(117, 'Trà Sữa Trà Đen', 1, 1),
	(118, 'Trà Sữa Trà Xanh', 1, 1),
	(119, 'Trà Sữa Trân Châu Đen', 1, 1),
	(120, 'Trà Sữa Trân Châu Hoàng Gia', 1, 3),
	(121, ' Trà Sữa Trân Châu Hoàng Kim', 1, 1),
	(122, ' Trà Sữa Truyền Thống', 1, 1),
	(123, 'Trà Sữa Xoài Trân Châu Đen', 1, 1),
	(124, 'Trà Vải', 1, 1),
	(125, 'Trà Xanh', 1, 1),
	(126, 'Trà Xanh Gong Cha', 1, 1),
	(127, 'Trà Xanh Kiwi', 1, 1),
	(128, 'Trà Xanh Sữa Vị Nhài', 1, 1),
	(129, 'Trà Xoài Bưởi Hồng', 1, 1),
	(130, ' Trà Xoài Bưởi Hồng Kem Phô Mai', 1, 1),
	(131, 'Xoài Matcha Latte', 1, 3);

-- Dumping data for table milkteashop.product_image: ~120 rows (approximately)
REPLACE INTO `product_image` (`id`, `name`, `url`, `product_id`, `status`) VALUES
	(1, NULL, 'img/product/products/BanhOreoXayCungCaPheCappuccino.png', 1, 0),
	(2, NULL, 'img/product/products/CamEp.png', 4, 0),
	(3, NULL, 'img/product/products/CaramenChay.png', 6, 0),
	(4, NULL, 'img/product/products/DauEp.png', 7, 0),
	(5, NULL, 'img/product/products/DuaEp.png', 8, 0),
	(6, NULL, 'img/product/products/KemTraXanh.png', 12, 0),
	(7, NULL, 'img/product/products/SinhToChanh.png', 18, 0),
	(8, NULL, 'img/product/products/SinhToDau.png', 19, 0),
	(9, NULL, 'img/product/products/SinhToXoai.png', 20, 0),
	(10, NULL, 'img/product/products/So-Co-LaCaPheDaXay.png', 21, 0),
	(11, NULL, 'img/product/products/So-Co-LaXayCungHanhNhanVaEspresso.png', 22, 0),
	(12, NULL, 'img/product/products/SuaChuaPhucBonTuDacCam.png', 23, 0),
	(13, NULL, 'img/product/products/SuaChuaXoaiDacThom.png', 24, 0),
	(14, NULL, 'img/product/products/TaoEp.png', 25, 0),
	(15, NULL, 'img/product/products/TaoVaDauEp.png', 26, 0),
	(16, NULL, 'img/product/products/ThomVaDauEp.png', 27, 0),
	(17, NULL, 'img/product/products/TraDaoDaXay.png', 28, 0),
	(18, NULL, 'img/product/products/TraDaoPhucLong.png', 29, 0),
	(19, NULL, 'img/product/products/TraDaoSua.png', 30, 0),
	(20, NULL, 'img/product/products/TraHoaHong.png', 31, 0),
	(21, NULL, 'img/product/products/TraLaiDacThom.png', 32, 0),
	(22, NULL, 'img/product/products/TraNhan-Lai.png', 33, 0),
	(23, NULL, 'img/product/products/TraNhan-Sen.png', 34, 0),
	(24, NULL, 'img/product/products/TraOLongDau.png', 35, 0),
	(25, NULL, 'img/product/products/TraOLongMangCau.png', 36, 0),
	(26, NULL, 'img/product/products/TraOLongSua.png', 37, 0),
	(27, NULL, 'img/product/products/TraSenVangCuNang.png', 38, 0),
	(28, NULL, 'img/product/products/TraSenVangSen.png', 39, 0),
	(29, NULL, 'img/product/products/TraSuaPhucLong.png', 40, 0),
	(30, NULL, 'img/product/products/TraThachVai.png', 41, 0),
	(31, NULL, 'img/product/products/TraThanhDao.png', 42, 0),
	(32, NULL, 'img/product/products/TraThaoMoc.png', 43, 0),
	(33, NULL, 'img/product/products/TraVai-Sen.png', 44, 0),
	(34, NULL, 'img/product/products/TraVaiLai.png', 45, 0),
	(35, NULL, 'img/product/products/TraXanhDaXay.png', 46, 0),
	(36, NULL, 'img/product/products/ChocoNguCocKemCafe.png', 47, 0),
	(37, NULL, 'img/product/products/CremeBruleeLatte.png', 48, 0),
	(38, NULL, 'img/product/products/CremeBruleeMatcha.png', 49, 0),
	(39, NULL, 'img/product/products/CremeBruleeMilkTea.png', 50, 0),
	(40, NULL, 'img/product/products/CremeBruleeTea.png', 51, 0),
	(41, NULL, 'img/product/products/DaoLatte.png', 52, 0),
	(42, NULL, 'img/product/products/DauLatte.png', 53, 0),
	(43, NULL, 'img/product/products/DolceMilkTeaFloat.png', 54, 0),
	(44, NULL, 'img/product/products/GrassJellyMilkCoffee.png', 55, 0),
	(45, NULL, 'img/product/products/HongTra.png', 56, 0),
	(46, NULL, 'img/product/products/HongTraBuoiMatOng.png', 57, 0),
	(47, NULL, 'img/product/products/HongTraDau.png', 58, 0),
	(48, NULL, 'img/product/products/HongTraNguCocKemCafe.png', 59, 0),
	(49, NULL, 'img/product/products/InstantMilkTea–Original.png', 60, 0),
	(50, NULL, 'img/product/products/InstantMilkTea–Strawberry.png', 61, 0),
	(51, NULL, 'img/product/products/KemTuyetSocola.png', 62, 0),
	(52, NULL, 'img/product/products/KemTuyetTraXanhNhatBan.png', 63, 0),
	(53, NULL, 'img/product/products/MatchaDauDo.png', 64, 0),
	(54, NULL, 'img/product/products/OLongKemPhoMai.png', 65, 0),
	(55, NULL, 'img/product/products/OLongThaiCuc.png', 66, 0),
	(56, NULL, 'img/product/products/OLongXoaiKemCaPhe.png', 67, 0),
	(57, NULL, 'img/product/products/OkinawaLatte.png', 68, 0),
	(58, NULL, 'img/product/products/OkinawaLatteFloat.png', 69, 0),
	(59, NULL, 'img/product/products/OkinawaOreoCreamMilkTea.png', 70, 0),
	(60, NULL, 'img/product/products/OolongTranChauBabyKemCafe.png', 71, 0),
	(61, NULL, 'img/product/products/ProbiBuoiTranChauSuongMai.png', 72, 0),
	(62, NULL, 'img/product/products/ProbiXoaiTranChauSuongMai.png', 73, 0),
	(63, NULL, 'img/product/products/RoyalPearlMilkCoffee.png', 74, 0),
	(64, NULL, 'img/product/products/StrawberryMilkTea.png', 75, 0),
	(65, NULL, 'img/product/products/SuaChuaDauTamHatDe.png', 76, 0),
	(66, NULL, 'img/product/products/SuaChuaDauTamHoangKim.png', 77, 0),
	(67, NULL, 'img/product/products/SuaChuaManHatSen.png', 78, 0),
	(68, NULL, 'img/product/products/SuaChuaTrang.png', 79, 0),
	(69, NULL, 'img/product/products/SuaTuoiLongNhanTaoDo.png', 80, 0),
	(70, NULL, 'img/product/products/SuaTuoiOkinawa.png', 81, 0),
	(71, NULL, 'img/product/products/SuaTuoiTranChauBabyKemCafe.png', 82, 0),
	(72, NULL, 'img/product/products/TigerSugar.png', 83, 0),
	(73, NULL, 'img/product/products/ToffeeBlackTeaLatte.png', 84, 0),
	(74, NULL, 'img/product/products/ToffeeChocolatea.png', 85, 0),
	(75, NULL, 'img/product/products/TraAlisanGongCha.png', 86, 0),
	(76, NULL, 'img/product/products/TraBiDaoGongCha.png', 87, 0),
	(77, NULL, 'img/product/products/TraDao.png', 88, 0),
	(78, NULL, 'img/product/products/TraDaoBuoiHongTranChauBaby.png', 89, 0),
	(79, NULL, 'img/product/products/TraDauTamPhaLeTuyet.png', 90, 0),
	(80, NULL, 'img/product/products/TraDenGongCha.png', 91, 0),
	(81, NULL, 'img/product/products/Traduanhietdoi.png', 92, 0),
	(82, NULL, 'img/product/products/TraOolongGongCha.png', 93, 0),
	(83, NULL, 'img/product/products/TraSua.png', 94, 0),
	(84, NULL, 'img/product/products/TraSuaBaAnhEm.png', 95, 0),
	(85, NULL, 'img/product/products/TraSuaBacHa.png', 96, 0),
	(86, NULL, 'img/product/products/TraSuaChocolate.png', 97, 0),
	(87, NULL, 'img/product/products/TraSuaDao.png', 98, 0),
	(88, NULL, 'img/product/products/TraSuaDauTay.png', 99, 0),
	(89, NULL, 'img/product/products/TraSuaDolceTranChauHoangKim.png', 100, 0),
	(90, NULL, 'img/product/products/TraSuaGaoRang.png', 101, 0),
	(91, NULL, 'img/product/products/TraSuaGaoRangOkinawa.png', 102, 0),
	(92, NULL, 'img/product/products/TraSuaHanhPhuc.png', 103, 0),
	(93, NULL, 'img/product/products/TraSuaHokkaido.png', 104, 0),
	(94, NULL, 'img/product/products/TraSuaKhoaiMon.png', 105, 0),
	(95, NULL, 'img/product/products/TraSuaKimCuongDenOkinawa.png', 106, 0),
	(96, NULL, 'img/product/products/TraSuaMatcha.png', 107, 0),
	(97, NULL, 'img/product/products/TraSuaMatchaDauDo.png', 108, 0),
	(98, NULL, 'img/product/products/TraSuaOLong.png', 109, 0),
	(99, NULL, 'img/product/products/TraSuaOkinawa.png', 110, 0),
	(100, NULL, 'img/product/products/TraSuaOolong.png', 111, 0),
	(101, NULL, 'img/product/products/TrasuaOolong3J.png', 112, 0),
	(102, NULL, 'img/product/products/TraSuaPanda.png', 113, 0),
	(103, NULL, 'img/product/products/TraSuaPuddingDauDo.png', 114, 0),
	(104, NULL, 'img/product/products/TraSuaSocola.png', 115, 0),
	(105, NULL, 'img/product/products/TraSuaSuongSao.png', 116, 0),
	(106, NULL, 'img/product/products/TraSuaTraDen.png', 117, 0),
	(107, NULL, 'img/product/products/TraSuaTraXanh.png', 118, 0),
	(108, NULL, 'img/product/products/TraSuaTranChauDen.png', 119, 0),
	(109, NULL, 'img/product/products/TraSuaTranChauHoangGia.png', 120, 0),
	(110, NULL, 'img/product/products/TraSuaTranChauHoangKim.png', 121, 0),
	(111, NULL, 'img/product/products/TraSuaTruyenThong.png', 122, 0),
	(112, NULL, 'img/product/products/TraSuaXoaiTranChauDen.png', 123, 0),
	(113, NULL, 'img/product/products/TraVai.png', 124, 0),
	(114, NULL, 'img/product/products/TraXanh.png', 125, 0),
	(115, NULL, 'img/product/products/TraXanhGongCha.png', 126, 0),
	(116, NULL, 'img/product/products/TraXanhKiwi.png', 127, 0),
	(117, NULL, 'img/product/products/TraXanhSuaViNhai.png', 128, 0),
	(118, NULL, 'img/product/products/TraXoaiBuoiHong.png', 129, 0),
	(119, NULL, 'img/product/products/TraXoaiBuoiHongKemPhoMai.png', 130, 0),
	(120, NULL, 'img/product/products/XoaiMatchaLatte.png', 131, 0);

-- Dumping data for table milkteashop.product_size: ~186 rows (approximately)
REPLACE INTO `product_size` (`id`, `product_id`, `name`, `price`, `original_price`) VALUES
	(1, 1, 'M', 60000, 60000),
	(4, 4, 'M', 45000, 45000),
	(7, 6, 'M', 45000, 45000),
	(8, 6, 'L', 50000, 50000),
	(9, 7, 'M', 45000, 55000),
	(10, 8, 'M', 45000, 45000),
	(16, 12, 'M', 55000, 55000),
	(17, 12, 'L', 60000, 60000),
	(28, 18, 'M', 50000, 50000),
	(29, 19, 'M', 55000, 55000),
	(30, 20, 'M', 55000, 55000),
	(31, 21, 'M', 60000, 60000),
	(32, 22, 'M', 60000, 60000),
	(33, 23, 'M', 70000, 70000),
	(34, 24, 'M', 70000, 70000),
	(35, 25, 'M', 45000, 45000),
	(36, 26, 'M', 50000, 50000),
	(37, 27, 'M', 50000, 50000),
	(38, 28, 'M', 65000, 65000),
	(39, 29, 'M', 55000, 50000),
	(40, 29, 'L', 55000, 55000),
	(41, 30, 'M', 40000, 40000),
	(42, 30, 'L', 45000, 45000),
	(43, 31, 'M', 50000, 50000),
	(44, 31, 'L', 55000, 55000),
	(45, 32, 'M', 50000, 50000),
	(46, 32, 'L', 55000, 55000),
	(47, 33, 'M', 50000, 50000),
	(48, 33, 'L', 55000, 55000),
	(49, 34, 'M', 50000, 50000),
	(50, 34, 'L', 55000, 55000),
	(51, 35, 'M', 50000, 50000),
	(52, 35, 'L', 55000, 55000),
	(53, 36, 'L', 54000, 54000),
	(54, 37, 'M', 45000, 45000),
	(55, 37, 'L', 50000, 50000),
	(56, 38, 'M', 9750, 45000),
	(57, 38, 'L', 50000, 50000),
	(58, 39, 'M', 7500, 45000),
	(59, 39, 'L', 50000, 50000),
	(60, 40, 'M', 45000, 45000),
	(61, 40, 'L', 8250, 50000),
	(62, 41, 'M', 6000, 45000),
	(63, 41, 'L', 50000, 50000),
	(64, 42, 'M', 45000, 45000),
	(65, 42, 'L', 6750, 50000),
	(66, 43, 'M', 7500, 50000),
	(67, 43, 'L', 55000, 55000),
	(68, 44, 'M', 50000, 50000),
	(69, 44, 'L', 8250, 55000),
	(70, 45, 'M', 7500, 50000),
	(71, 45, 'L', 55000, 55000),
	(72, 46, 'M', 60000, 60000),
	(73, 48, 'L', 8250, 32000),
	(74, 49, 'L', 38000, 38000),
	(75, 52, 'L', 8250, 40000),
	(76, 55, 'L', 7500, 30000),
	(77, 66, 'L', 45000, 45000),
	(78, 76, 'L', 40000, 40000),
	(79, 77, 'L', 6750, 36000),
	(80, 87, 'L', 6000, 35000),
	(81, 89, 'L', 5700, 40000),
	(82, 93, 'L', 4950, 32000),
	(83, 95, 'L', 4800, 40000),
	(84, 102, 'L', 37000, 37000),
	(85, 103, 'L', 32000, 32000),
	(86, 106, 'L', 40000, 40000),
	(87, 107, 'L', 40000, 40000),
	(88, 108, 'L', 42000, 42000),
	(89, 109, 'L', 38000, 38000),
	(90, 110, 'L', 38000, 38000),
	(91, 112, 'L', 40000, 40000),
	(92, 113, 'L', 38000, 38000),
	(93, 114, 'L', 33000, 33000),
	(94, 120, 'L', 35000, 35000),
	(95, 125, 'L', 32000, 32000),
	(96, 127, 'L', 30000, 30000),
	(98, 130, 'L', 40000, 40000),
	(99, 51, 'M', 7500, 30000),
	(100, 57, 'M', 30000, 30000),
	(101, 58, 'M', 6750, 35000),
	(102, 60, 'M', 6750, 35000),
	(103, 61, 'M', 35000, 35000),
	(104, 65, 'M', 35000, 35000),
	(105, 74, 'M', 35000, 35000),
	(106, 79, 'M', 28000, 28000),
	(107, 83, 'M', 28000, 28000),
	(108, 92, 'M', 30000, 30000),
	(109, 124, 'M', 4200, 35000),
	(110, 129, 'M', 5250, 30000),
	(111, 47, 'M', 7500, 35000),
	(112, 50, 'M', 30000, 30000),
	(113, 53, 'M', 28000, 28000),
	(114, 54, 'M', 6750, 30000),
	(115, 56, 'M', 6750, 32000),
	(116, 59, 'M', 42000, 42000),
	(117, 62, 'M', 6750, 35000),
	(118, 63, 'M', 32000, 32000),
	(119, 64, 'M', 6750, 28000),
	(120, 67, 'M', 49000, 49000),
	(121, 68, 'M', 7500, 32000),
	(122, 69, 'M', 30000, 30000),
	(123, 70, 'M', 7500, 35000),
	(124, 71, 'M', 28000, 28000),
	(125, 72, 'M', 32000, 32000),
	(126, 73, 'M', 32000, 32000),
	(127, 75, 'M', 40000, 40000),
	(128, 78, 'M', 38000, 38000),
	(129, 80, 'M', 35000, 35000),
	(130, 81, 'M', 40000, 40000),
	(131, 82, 'M', 30000, 30000),
	(132, 84, 'M', 28000, 28000),
	(133, 85, 'M', 36000, 36000),
	(134, 86, 'M', 35000, 35000),
	(135, 88, 'M', 38000, 38000),
	(136, 90, 'M', 35000, 35000),
	(137, 91, 'M', 30000, 30000),
	(138, 94, 'M', 30000, 30000),
	(139, 96, 'M', 22000, 22000),
	(140, 97, 'M', 38000, 38000),
	(141, 98, 'M', 35000, 35000),
	(142, 99, 'M', 4500, 35000),
	(143, 100, 'M', 4500, 28000),
	(144, 101, 'M', 5250, 38000),
	(145, 104, 'M', 5250, 32000),
	(146, 105, 'M', 38000, 38000),
	(147, 111, 'M', 36000, 36000),
	(148, 115, 'M', 4800, 22000),
	(149, 116, 'M', 6300, 35000),
	(150, 117, 'M', 38000, 38000),
	(151, 118, 'M', 38000, 38000),
	(152, 119, 'M', 36000, 36000),
	(153, 121, 'M', 4800, 45000),
	(154, 122, 'M', 4500, 29000),
	(155, 123, 'M', 5250, 40000),
	(156, 126, 'M', 4800, 38000),
	(157, 128, 'M', 20000, 20000),
	(158, 131, 'M', 4500, 35000),
	(159, 47, 'L', 40000, 40000),
	(160, 50, 'L', 8250, 35000),
	(161, 53, 'L', 8100, 33000),
	(162, 54, 'L', 35000, 35000),
	(163, 56, 'L', 37000, 37000),
	(164, 59, 'L', 7500, 47000),
	(165, 62, 'L', 40000, 40000),
	(166, 63, 'L', 7500, 37000),
	(167, 64, 'L', 33000, 33000),
	(168, 67, 'L', 8250, 54000),
	(169, 68, 'L', 37000, 37000),
	(170, 69, 'L', 8250, 35000),
	(171, 70, 'L', 40000, 40000),
	(172, 71, 'L', 8250, 33000),
	(173, 72, 'L', 37000, 37000),
	(174, 73, 'L', 4800, 37000),
	(175, 75, 'L', 6000, 45000),
	(176, 78, 'L', 43000, 43000),
	(177, 80, 'L', 5250, 40000),
	(178, 81, 'L', 6000, 45000),
	(179, 82, 'L', 4800, 35000),
	(180, 84, 'L', 5550, 33000),
	(181, 85, 'L', 4800, 41000),
	(182, 86, 'L', 6000, 40000),
	(183, 88, 'L', 6300, 43000),
	(184, 90, 'L', 5700, 40000),
	(185, 91, 'L', 6000, 35000),
	(186, 94, 'L', 5250, 35000),
	(187, 96, 'L', 4500, 27000),
	(188, 97, 'L', 43000, 43000),
	(189, 98, 'L', 6000, 40000),
	(190, 99, 'L', 40000, 40000),
	(191, 100, 'L', 33000, 33000),
	(192, 101, 'L', 43000, 43000),
	(193, 104, 'L', 37000, 37000),
	(194, 105, 'L', 43000, 43000),
	(195, 111, 'L', 41000, 41000),
	(196, 115, 'L', 27000, 27000),
	(197, 116, 'L', 40000, 40000),
	(198, 117, 'L', 43000, 43000),
	(199, 118, 'L', 43000, 43000),
	(200, 119, 'L', 41000, 41000),
	(201, 121, 'L', 50000, 50000),
	(202, 122, 'L', 34000, 34000),
	(203, 123, 'L', 45000, 45000),
	(204, 126, 'L', 43000, 43000),
	(205, 128, 'L', 25000, 25000),
	(206, 131, 'L', 40000, 40000);

-- Dumping data for table milkteashop.sales: ~5 rows (approximately)
REPLACE INTO `sales` (`id`, `name`, `percent`, `start_date`, `end_date`) VALUES
	(8, 'Teets', 15, '2023-01-28', '2023-02-04'),
	(9, 'Teets', 15, '2023-01-28', '2023-02-04'),
	(10, 'Teets', 15, '2023-01-28', '2023-02-04'),
	(11, 'Nguyễn Vũ Mạnh Hà', 15, '2022-12-27', '2022-12-27'),
	(12, 'Năm mới 2023', 15, '2023-01-01', '2023-01-15');

-- Dumping data for table milkteashop.sale_detail: ~14 rows (approximately)
REPLACE INTO `sale_detail` (`id`, `sale_id`, `product_id`, `category_id`) VALUES
	(6, 8, NULL, 1),
	(7, 9, NULL, 1),
	(8, 10, NULL, 1),
	(9, 10, NULL, 2),
	(10, 10, NULL, 3),
	(11, 10, NULL, 4),
	(12, 11, 6, NULL),
	(13, 11, 7, NULL),
	(14, 11, 26, NULL),
	(15, 11, NULL, 2),
	(16, 11, NULL, 3),
	(17, 11, NULL, 4),
	(18, 11, NULL, 5),
	(19, 12, NULL, 1);

-- Dumping data for table milkteashop.toppings: ~16 rows (approximately)
REPLACE INTO `toppings` (`id`, `name`, `price`, `category_id`, `status`) VALUES
	(3, 'Đào', 15000, 1, 0),
	(4, 'Đào', 15000, 4, 0),
	(5, 'Đào', 15000, 5, 0),
	(6, 'Vải', 15000, 1, 0),
	(7, 'Vải', 15000, 4, 0),
	(8, 'Vải', 15000, 5, 0),
	(9, 'Nhãn', 15000, 1, 0),
	(10, 'Nhãn', 15000, 4, 0),
	(11, 'Nhãn', 15000, 5, 0),
	(12, 'Whipped cream ', 15000, 1, 0),
	(13, 'Whipped cream', 15000, 3, 1),
	(14, 'Whipped cream', 15000, 5, 1),
	(15, 'Bánh Flan', 16000, 1, 0),
	(16, 'Espresso Shot', 24000, 1, 0),
	(17, 'Espresso Shot', 24000, 3, 0),
	(18, 'Espresso Shot', 24000, 5, 0);

-- Dumping data for table milkteashop.topping_order: ~0 rows (approximately)
REPLACE INTO `topping_order` (`id`, `topping_id`, `order_detail_id`) VALUES
	(1, 17, 2),
	(2, 4, 3),
	(3, 7, 3),
	(4, 10, 3),
	(5, 4, 5),
	(6, 10, 5),
	(7, 12, 8),
	(8, 16, 9),
	(9, 15, 10),
	(10, 6, 12);

-- Dumping data for table milkteashop.user: ~14 rows (approximately)
REPLACE INTO `user` (`id`, `username`, `password`, `name`, `address`, `phone`, `email`, `level`, `token`) VALUES
	(1, 'manhha5842', 'a4c9e6e813cf99bc40046b06cad1848e4819cab63c18eb58debac9592229683e', 'HÃ ', '', '0344558306', 'manhha584224@gmail.com', 0, NULL),
	(3, 'abc', '804498493bae57df2783c3a70cb22f13859f13dbf141e9201e8712b4ff209d33', 'Nguyễn Vũ Mạnh Hà', '', '0344558306', 'manhha584224@gmail.com', 1, 'qFNAm5gw9R-IY_x8o7uPUlQpu7y5EdJi'),
	(4, 'undefined', 'eb045d78d273107348b0300c01d29b7552d622abbc6faf81b3ec55359aa9950c', 'undefined', '', '', 'undefined', 0, NULL),
	(5, '113088951446987270620', '8f574df2c949477d190503f36f467d465489c3b7c8c1bde0fe4e45ed6f97de70', 'H-Bell', '', '0344558306', 'manhha584224@gmail.com', 0, NULL),
	(6, 'manhha584224', 'b08befce0a28f4799a1db011f81a69b6bf05a358def74e0b4df4c8342949dd9', 'Nguyễn Vũ Mạnh Hà', '', '0344558306', 'manhha584224@gmail.com', 0, NULL),
	(7, '123', '49a68c15c0d6e26c8b4a0743e6b87f074864c2fae5983c88956cb2882d608f5', 'aasds', '', '0366443541', '123@gmail.com', 0, 'EPOxwf4fGqQcAXak4xrKKIiPjR5QaU_o'),
	(8, 'cay', '49a68c15c0d6e26c8b4a0743e6b87f074864c2fae5983c88956cb2882d608f5', 'Trái', '', '0345582306', '123@gmail.com', 0, NULL),
	(9, 'abcd', '49a68c15c0d6e26c8b4a0743e6b87f074864c2fae5983c88956cb2882d608f5', 'Hà', '', '0344558306', '123@gmail.com', 0, 'iyWqbQqy7USO7wkvG2w0ckrrSSJcm2ye'),
	(10, 'asd', '49a68c15c0d6e26c8b4a0743e6b87f074864c2fae5983c88956cb2882d608f5', 'Hà', '', '0344558306', 'abc@gmail.com', 0, NULL),
	(11, '456', '49a68c15c0d6e26c8b4a0743e6b87f074864c2fae5983c88956cb2882d608f5', 'sáa', '', '0344558306', 'manhha584224@gmail.com', 0, 'CkbgTujUgXRGU8yvECZN7I2fltbKueOo'),
	(12, 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'admin', '', '', '', 1, 'sZMBoFW01H1T-MospDIyztad5jkCDZp5'),
	(15, '113922272377385336933', '416252e39f2f986975f2f361536167f03879d8a6e1b438a68dbc37fee857d2a', 'H-N z', '', '', 'manhha300622@gmail.com', 0, NULL),
	(16, 'hahaha', '49a68c15c0d6e26c8b4a0743e6b87f074864c2fae5983c88956cb2882d608f5', 'Hà', '', '0344558306', 'manhhha584224@gmail.com', 0, 'AfiTuKufNspUKdFL5lLwh3iwt0F6PEDb'),
	(18, 'dam', '60166a3b3b252885e0c489b2da291048d8af0349326d23d96140b065f65f31f5', 'Dam Thanh', '', '0377184642', 'buithanhdam02@gmail.com', 0, 'LOymneD0E3xjLJT0gt0V8SgEAtaWAAQ4'),
	(19, 'tinhdeptrai', '3501a09179ac933084b1314bc537bb6d517c0c6a57cffc5e89c93348dafd598f', 'Lê Trọng Tình', '', '0986852943', 'tinhle2772002@gmail.com', 0, NULL),
	(20, 'btdam', '8c65b9f5b3790fe6b4e1b72d6ec5aee1d27f8162c8ef3fe48748b38562bd1bed', 'Dam Thanh', '', '0377184642', 'thanhdamcd@gmail.com', 0, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;