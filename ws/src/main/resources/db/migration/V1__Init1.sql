SET NAMES utf8mb4;

CREATE TABLE `ping_entry` (
  `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_agent` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `coord_lat` float COLLATE utf8mb4_general_ci NOT NULL,
  `coord_long` float COLLATE utf8mb4_general_ci NOT NULL,
  `device_dt_last_ping` datetime COLLATE utf8mb4_general_ci NOT NULL,
  `device_dt_current` datetime COLLATE utf8mb4_general_ci NOT NULL,
  `dt_current` datetime COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
