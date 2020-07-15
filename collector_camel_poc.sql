-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.13 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for collector_camel_poc
CREATE DATABASE IF NOT EXISTS `collector_camel_poc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `collector_camel_poc`;

-- Dumping structure for table collector_camel_poc.bot
CREATE TABLE IF NOT EXISTS `bot` (
  `btt_bot_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `bothub_bot_id` varchar(255) NOT NULL,
  `status` enum('ACTIVATED','DEACTIVATED','ERROR') DEFAULT NULL,
  PRIMARY KEY (`btt_bot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table collector_camel_poc.bot: ~1 rows (approximately)
/*!40000 ALTER TABLE `bot` DISABLE KEYS */;
INSERT INTO `bot` (`btt_bot_id`, `name`, `bothub_bot_id`, `status`) VALUES
	(4, 'Bot_Test', 'BOTHUB_ID', 'ACTIVATED');
/*!40000 ALTER TABLE `bot` ENABLE KEYS */;

-- Dumping structure for table collector_camel_poc.bot_hub_campaign
CREATE TABLE IF NOT EXISTS `bot_hub_campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `btt_campaign_id` bigint(20) NOT NULL,
  `bothub_campaign_id` varchar(255) DEFAULT NULL,
  `status` enum('RUNNING','FINISHED') NOT NULL DEFAULT 'RUNNING',
  `sent_count` int(20) DEFAULT '0',
  `delivered_count` int(20) DEFAULT '0',
  `read_count` int(20) DEFAULT '0',
  `error_count` int(20) DEFAULT '0',
  `total_count` int(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `campaign_id` (`btt_campaign_id`),
  CONSTRAINT `bot_hub_campaign_ibfk_2` FOREIGN KEY (`btt_campaign_id`) REFERENCES `btt_campaign` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table collector_camel_poc.bot_hub_campaign: ~0 rows (approximately)
/*!40000 ALTER TABLE `bot_hub_campaign` DISABLE KEYS */;
INSERT INTO `bot_hub_campaign` (`id`, `btt_campaign_id`, `bothub_campaign_id`, `status`, `sent_count`, `delivered_count`, `read_count`, `error_count`, `total_count`) VALUES
	(1, 1, 'bothub_campaign_id_111', 'RUNNING', 0, 0, 0, 0, 3),
	(2, 1, 'bothub_campaign_id_222', 'RUNNING', 0, 0, 0, 0, 3);
/*!40000 ALTER TABLE `bot_hub_campaign` ENABLE KEYS */;

-- Dumping structure for table collector_camel_poc.btt_campaign
CREATE TABLE IF NOT EXISTS `btt_campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bot_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('NOT_STARTED','RUNNING','TRIGGERING','FINISHED','STOPPED','BOT_DEACTIVATED','BOT_ERROR') NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `trigger_start_time` time NOT NULL,
  `trigger_end_time` time NOT NULL,
  `total_msisdns` int(11) NOT NULL,
  `total_sent` int(11) NOT NULL,
  `total_delivered` int(11) NOT NULL,
  `total_read` int(11) NOT NULL,
  `total_error` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bot_id` (`bot_id`),
  CONSTRAINT `btt_campaign_ibfk_1` FOREIGN KEY (`bot_id`) REFERENCES `bot` (`btt_bot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table collector_camel_poc.btt_campaign: ~0 rows (approximately)
/*!40000 ALTER TABLE `btt_campaign` DISABLE KEYS */;
INSERT INTO `btt_campaign` (`id`, `bot_id`, `name`, `status`, `start_date`, `end_date`, `trigger_start_time`, `trigger_end_time`, `total_msisdns`, `total_sent`, `total_delivered`, `total_read`, `total_error`) VALUES
	(1, 4, 'Test Campaign', 'TRIGGERING', '2020-07-14', '2020-07-15', '12:00:15', '23:17:17', 3, 0, 0, 0, 0);
/*!40000 ALTER TABLE `btt_campaign` ENABLE KEYS */;

-- Dumping structure for table collector_camel_poc.msisdn
CREATE TABLE IF NOT EXISTS `msisdn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `campaign_id` bigint(20) NOT NULL,
  `number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `campaign_id` (`campaign_id`),
  CONSTRAINT `msisdn_ibfk_1` FOREIGN KEY (`campaign_id`) REFERENCES `btt_campaign` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

-- Dumping data for table collector_camel_poc.msisdn: ~2 rows (approximately)
/*!40000 ALTER TABLE `msisdn` DISABLE KEYS */;
INSERT INTO `msisdn` (`id`, `campaign_id`, `number`) VALUES
	(1, 1, '010'),
	(2, 1, '011'),
	(3, 1, '012'),
	(4, 1, '015'),
	(5, 1, '016'),
	(6, 1, '020');
/*!40000 ALTER TABLE `msisdn` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
