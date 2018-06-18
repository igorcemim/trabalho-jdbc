CREATE TABLE `placa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `letras` varchar(3) DEFAULT NULL,
  `numeros` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `carro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ano` int(11) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `montadora` varchar(20) DEFAULT NULL,
  `placa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_placa_id` (`placa_id`),
  CONSTRAINT `fk_placa_id` FOREIGN KEY (`placa_id`) REFERENCES `placa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
