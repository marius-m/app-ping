SET NAMES utf8mb4;

ALTER TABLE `ping_entry`
ADD COLUMN `extras` TEXT NOT NULL AFTER `dt_current`;