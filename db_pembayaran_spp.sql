/*
Navicat MySQL Data Transfer

Source Server         : Bintang Database
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : db_pembayaran_spp

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2023-03-07 10:35:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_kelas`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_kelas`;
CREATE TABLE `tbl_kelas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_kelas` varchar(10) NOT NULL,
  `kompetensi_keahlian` varchar(35) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tbl_kelas
-- ----------------------------
INSERT INTO `tbl_kelas` VALUES ('2', 'XII RPL 2 ', 'Rekayasa Prangkat Lunak');
INSERT INTO `tbl_kelas` VALUES ('3', 'XII TKJ 1', 'Teknik Komputer Jaringan');

-- ----------------------------
-- Table structure for `tbl_pembayaran`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pembayaran`;
CREATE TABLE `tbl_pembayaran` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(35) NOT NULL,
  `nis` char(7) NOT NULL,
  `kelas` varchar(35) NOT NULL,
  `bulan_bayar` varchar(50) NOT NULL,
  `nominal` int(11) NOT NULL,
  `tahun` char(4) NOT NULL,
  `tanggal` varchar(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tbl_pembayaran
-- ----------------------------
INSERT INTO `tbl_pembayaran` VALUES ('3', 'Bintang', '202-306', 'XII RPL 2 ', 'Januari', '200000', '2023', '07-03-2023');
INSERT INTO `tbl_pembayaran` VALUES ('4', 'Bintang', '202-305', 'XII RPL 2 ', 'Februari', '200000', '2023', '07-03-2023');
INSERT INTO `tbl_pembayaran` VALUES ('5', 'Bintang', '202-305', 'XII RPL 2 ', '-- Pilih Bulan --', '200000', '2023', '07-03-2023');
INSERT INTO `tbl_pembayaran` VALUES ('6', 'Bintang', '202-305', 'XII RPL 2 ', 'Februari', '200000', '2023', '07-03-2023');

-- ----------------------------
-- Table structure for `tbl_petugas`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_petugas`;
CREATE TABLE `tbl_petugas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(35) NOT NULL,
  `password` varchar(35) NOT NULL,
  `nama_petugas` varchar(35) NOT NULL,
  `level` enum('petugas','admin') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tbl_petugas
-- ----------------------------
INSERT INTO `tbl_petugas` VALUES ('3', 'Bintang', 'adminadmin', 'Muhammad Bintang', 'admin');
INSERT INTO `tbl_petugas` VALUES ('4', 'Petugas', 'petugaspetugas', 'Zhaka', 'petugas');

-- ----------------------------
-- Table structure for `tbl_siswa`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_siswa`;
CREATE TABLE `tbl_siswa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nis` char(8) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `id_spp` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tbl_siswa
-- ----------------------------
INSERT INTO `tbl_siswa` VALUES ('6', '202-305', 'Bintang', '2', '', '7418732', '13');
INSERT INTO `tbl_siswa` VALUES ('8', '202-306', 'Bintang', '2', '', '7418732', '13');

-- ----------------------------
-- Table structure for `tbl_spp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_spp`;
CREATE TABLE `tbl_spp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nominal` int(11) NOT NULL,
  `level` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tbl_spp
-- ----------------------------
INSERT INTO `tbl_spp` VALUES ('13', '200000', 'I');
INSERT INTO `tbl_spp` VALUES ('14', '200000', 'I');
INSERT INTO `tbl_spp` VALUES ('16', '300000', 'II');

-- ----------------------------
-- View structure for `view_bulanan`
-- ----------------------------
DROP VIEW IF EXISTS `view_bulanan`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_bulanan` AS select `tbl_pembayaran`.`bulan_bayar` AS `bulan_bayar`,`tbl_pembayaran`.`id` AS `id` from `tbl_pembayaran` ;

-- ----------------------------
-- View structure for `view_laporan`
-- ----------------------------
DROP VIEW IF EXISTS `view_laporan`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_laporan` AS select `tbl_pembayaran`.`id` AS `id`,`tbl_pembayaran`.`nama` AS `nama`,`tbl_pembayaran`.`nis` AS `nis`,`tbl_pembayaran`.`kelas` AS `kelas`,`tbl_pembayaran`.`bulan_bayar` AS `bulan_bayar`,`tbl_pembayaran`.`nominal` AS `nominal`,`tbl_pembayaran`.`tahun` AS `tahun`,`tbl_pembayaran`.`tanggal` AS `tanggal` from `tbl_pembayaran` ;

-- ----------------------------
-- View structure for `view_siswa`
-- ----------------------------
DROP VIEW IF EXISTS `view_siswa`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_siswa` AS select `tbl_kelas`.`nama_kelas` AS `nama_kelas`,`tbl_spp`.`nominal` AS `nominal`,`tbl_siswa`.`id` AS `id`,`tbl_siswa`.`nis` AS `nis`,`tbl_siswa`.`nama` AS `nama`,`tbl_siswa`.`id_kelas` AS `id_kelas`,`tbl_siswa`.`alamat` AS `alamat`,`tbl_siswa`.`no_telp` AS `no_telp`,`tbl_siswa`.`id_spp` AS `id_spp` from ((`tbl_siswa` join `tbl_kelas` on(`tbl_siswa`.`id_kelas` = `tbl_kelas`.`id`)) join `tbl_spp` on(`tbl_siswa`.`id_spp` = `tbl_spp`.`id`)) ;

-- ----------------------------
-- View structure for `view_transaksi`
-- ----------------------------
DROP VIEW IF EXISTS `view_transaksi`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi` AS select `tbl_siswa`.`id` AS `id`,`tbl_siswa`.`nis` AS `nis`,`tbl_siswa`.`nama` AS `nama`,`tbl_siswa`.`id_kelas` AS `id_kelas`,`tbl_siswa`.`id_spp` AS `id_spp`,`tbl_kelas`.`nama_kelas` AS `nama_kelas`,`tbl_spp`.`nominal` AS `nominal` from ((`tbl_siswa` join `tbl_spp` on(`tbl_siswa`.`id_spp` = `tbl_spp`.`id`)) join `tbl_kelas` on(`tbl_siswa`.`id_kelas` = `tbl_kelas`.`id`)) ;
