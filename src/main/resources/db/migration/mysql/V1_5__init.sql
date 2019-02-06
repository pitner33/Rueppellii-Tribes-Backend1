CREATE TABLE `progression` (
  `id`         bigint(20) AUTO_INCREMENT,
  `model_id`       bigint(20),
  `is_create`   boolean,
  `finished_at` timestamp(6),
  `progression_type` varchar (20),

  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_progression` (
  `kingdom_id`          bigint(20) not null,
  `progression_id` bigint(20) NOT NULL,
  PRIMARY KEY (`progression_id`),
  KEY progression_id (`progression_id`),
  CONSTRAINT kingdom_progression_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT kingdom_progression_progression_id FOREIGN KEY (`progression_id`) REFERENCES `progression` (`id`)
);