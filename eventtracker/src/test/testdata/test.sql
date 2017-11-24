DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT primary key,
     `type` varchar(30),
    message_id varchar(40),
    ts TIMESTAMP,
    context text,
    anonymous_id varchar(40),
    user_id VARCHAR(40),
    integrations text
);