/*
 Navicat Premium Data Transfer

 Source Server         : ENIGMA
 Source Server Type    : MySQL
 Source Server Version : 100425 (10.4.25-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : apollo_db

 Target Server Type    : MySQL
 Target Server Version : 100425 (10.4.25-MariaDB)
 File Encoding         : 65001

 Date: 16/06/2024 09:13:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mst_credit
-- ----------------------------
DROP TABLE IF EXISTS `mst_credit`;
CREATE TABLE `mst_credit`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modified_date` datetime(6) NULL DEFAULT NULL,
  `alasan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `asal_rek` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keterangan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nama_asal_rek` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nama_rek_tujuan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rek_tujuan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tgl_efektif` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_by` int NOT NULL,
  `is_delete` tinyint NOT NULL,
  `jumlah` int NULL DEFAULT NULL,
  `modified_by` int NULL DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mst_credit
-- ----------------------------
INSERT INTO `mst_credit` VALUES (1, 1, 27835000, NULL, '2024-06-16 09:02:00.000000', 1, NULL, '2.35E+11', '7160210006', 'INCENTIVE SIF', 'SHINHAN INDO FINANCE', 'PT. SURYABUMI TUNGGAL', '1.40E+12', 'Berhasil', '20230202');
INSERT INTO `mst_credit` VALUES (1, 1, 27835000, NULL, '2024-06-16 09:02:00.000000', 2, NULL, '2.35E+11', '7160210006', 'INCENTIVE SIF', 'SHINHAN INDO FINANCE', 'PT. SURYABUMI TUNGGAL', '1.42E+12', 'Berhasil', '20230202');
INSERT INTO `mst_credit` VALUES (1, 1, 215589000, NULL, '2024-06-16 09:02:00.000000', 3, NULL, '2.35E+11', '7160210006', 'INCENTIVE SIF', 'SHINHAN INDO FINANCE', 'PT UNITEDA ARKATO', '1.42E+12', 'Berhasil', '20230202');

SET FOREIGN_KEY_CHECKS = 1;
