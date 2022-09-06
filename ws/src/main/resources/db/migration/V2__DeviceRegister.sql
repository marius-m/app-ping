SET NAMES utf8mb4;

CREATE TABLE `device_register` (
  `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_agent` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `device_name` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `token` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
