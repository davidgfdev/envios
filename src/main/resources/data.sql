drop table envios;

CREATE TABLE `railes`.`envios` (
  `id_envios` INT NOT NULL,
  `id_estacion_inicial` INT NOT NULL,
  `id_estacion_destino` INT NOT NULL,
  `id_tren` INT NOT NULL,
  `peso` DOUBLE NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`id_envios`));

insert into envios values
(1, 3, 1, 1, 40, curdate()),
(2, 4, 5, 5, 780, curdate()),
(3, 5, 4, 2, 700, curdate());
