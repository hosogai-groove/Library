CREATE DATABASE `hosogai` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `bookmst` (
  `bookNumber` varchar(45) NOT NULL,
  `bookBranchNumber` varchar(45) NOT NULL,
  `bookName` varchar(45) NOT NULL,
  `genreCord` varchar(45) NOT NULL,
  PRIMARY KEY (`bookNumber`,`bookBranchNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `genremst` (
  `genreCord` varchar(45) NOT NULL,
  `genreName` varchar(45) NOT NULL,
  PRIMARY KEY (`genreCord`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lenddelay_table` (
  `deadLineDate` date NOT NULL,
  `cardNumber` varchar(45) NOT NULL,
  `bookNumber` varchar(45) NOT NULL,
  `bookBranchNumber` int NOT NULL,
  `mailFlag` int NOT NULL,
  PRIMARY KEY (`deadLineDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lendingstatus_table` (
  `cardNumber` varchar(45) NOT NULL,
  `bookNumber` varchar(45) NOT NULL,
  `bookBranchNumber` varchar(45) NOT NULL,
  `returnDate` date NOT NULL,
  PRIMARY KEY (`bookNumber`,`bookBranchNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `settingdatabase` (
  `settingItem` varchar(45) NOT NULL,
  `settingValue` varchar(45) NOT NULL,
  PRIMARY KEY (`settingItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `totaldelay_table` (
  `totalDelayDays` int NOT NULL,
  `cardNumber` varchar(45) NOT NULL,
  `notDelayDays` int NOT NULL,
  `processExecutionDate` datetime NOT NULL,
  `penaltyFlag` tinyint(1) NOT NULL,
  PRIMARY KEY (`cardNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usermst` (
  `cardNumber` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `mailAdress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cardNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
