--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements

INSERT INTO `financiarte`.`roles` (`id`, `descripcion`, `rol`) VALUES ('1', 'ADMIN', 'ADMIN');
INSERT INTO `financiarte`.`roles` (`id`, `descripcion`, `rol`) VALUES ('2', 'PROVEEDOR', 'PROVEEDOR');
INSERT INTO `financiarte`.`roles` (`id`, `descripcion`, `rol`) VALUES ('3', 'CLIENTE', 'CLIENTE');
INSERT INTO `financiarte`.`usuarios` (`id`, `correo`, `nombre`, `password`, `telefono`, `usuario`, `rol`) VALUES ('1', 'admin@financiarte.com.uy', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', '123', 'admin', '1');
INSERT INTO estados VALUES (1,'activo','activo'),(2,'inactivo', 'inactivo'),(3, 'creada', 'creada'),(4, 'moroso', 'moroso'),(5 ,'entregada', 'entregada');
INSERT INTO `paises` VALUES (1,'Uruguay');
INSERT INTO `departamentos` VALUES (1,'Montevideo',1),(2,'Canelones',1),
(3,'Maldonado',1),(4,'Rocha',1),(5,'Treinta y Tres',1),(6,'Cerro Largo',1),
(7,'Rivera',1),(8,'Artigas',1),(9,'Salto',1),(10,'Paysandu',1),(11,'Rio Negro',1),
(12,'Soriano',1),(13,'Colonia',1),(14,'San Jose',1),(15,'Flores',1),(16,'Florida',1),
(17,'Lavalleja',1),(18,'Durazno',1),(19,'Tacuarembo',1);
INSERT INTO `ciudades` VALUES (1,'Montevideo',1),(221,'Las Piedras',2),(320,'Canelones',2),
(321,'La Paz',2),(322,'Pando',2),(323,'Santa Lucía',2),(324,'Piedras de Afilar',2),
(325,'Cumbres de Carrasco',2),(326,'Haras del Lago',2),(327,'Quinta Los Horneros',2),
(328,'Las Higueritas',2),(329,'Sofía Santos',2),(421,'Progreso',2),(422,'San Ramón',2),
(521,'Barros Blancos',2),(522,'Fracc. Cno. Maldonado',2),(523,'Colonia Nicolich',2),
(524,'Joaquín Suárez',2),(525,'Paso Carrasco',2),(526,'Santa Rosa',2),(527,'Sauce',2),(528,'Tala',2),
(529,'Villa Crespo y San Andrés',2),(530,'Fracc. Cno.del Andaluz y R.84',2),(621,'Atlántida',2),
(622,'Estación Atlántida',2),(624,'Cerrillos',2),(625,'Empalme Olmos',2),(626,'Migues',2),
(627,'Parque del Plata',2),(628,'San Bautista',2),(629,'San Jacinto',2),(630,'Dr. Francisco Soca',2),
(631,'Toledo',2),(632,'Montes',2),(633,'San José de Carrasco',2),(634,'Fracc. sobre R.74',2),
(721,'Aguas Corrientes',2),(722,'Barra de Carrasco',2),(723,'Juanicó',2),(724,'La Floresta',2),
(725,'Estación La Floresta',2),(726,'Las Toscas',2),(727,'Parque Carrasco',2),(729,'Salinas',2),
(730,'San Antonio',2),(731,'Aerop. Internacional de Carrasco',2),(732,'Solymar',2),
(733,'Villa Aeroparque',2),(822,'Camino a la Cadena',2),(823,'Castellanos',2),(824,'Colonia Berro',2),
(825,'Barrio Cópola',2),(826,'Costa Azul',2),(827,'Costa y Guillamón',2),(828,'El Pinar',2),
(829,'Estación Migues',2),(830,'Pinamar - Pinepark',2),(831,'Lagomar',2),(832,'Olmos',2),
(833,'Parada Cabrera',2),(834,'San Luis',2),(835,'Shangrilá',2),(836,'Totoral del Sauce',2),
(837,'Villa Felicidad',2),(838,'Villa Paz S.A.',2),(839,'Villa San José',2),(840,'Estación Tapia',2),
(880,'Villa San Felipe',2),(881,'Villa Hadita',2),(882,'Paso de Pache',2),(900,'Rural',2),
(910,'City Golf',2),(911,'Viejo Molino San Bernardo',2),(912,'Estanque de Pando',2),
(913,'Jardines de Pando',2),(914,'Paso Espinosa',2),(921,'Araminda',2),(922,'Argentino',2),
(923,'Barra de la Pedrera',2),(924,'Barrancas Coloradas',2),(925,'Bello Horizonte',2),
(926,'Biarritz',2),(927,'Bolívar',2),(928,'Campo Militar',2),(929,'Capilla de Cella',2),
(930,'Cañada de Cardozo',2),(931,'Cerrillos al Sur',2),(932,'Costa de Pando',2),(933,'Costa de Tala',2),
(934,'Cruz de los Caminos',2),(935,'Cuchilla Alta',2),(936,'Cuchilla Verde',2),(937,'Cueva del Tigre',2),
(938,'Echevarría',2),(939,'El Bosque',2),(940,'Empalme Sauce',2),(941,'Estación Margat',2),
(942,'Estación Pedrera',2),(943,'Fortín de Santa Rosa',2),(944,'Fracc. Progreso',2),
(945,'Instituto Adventista',2),(946,'Jaureguiberry',2),(947,'La Capilla',2),(948,'La Lucha',2),
(949,'La Montañesa',2),(950,'La Palmita',2),(951,'La Paloma',2),(952,'La Querencia',2),
(953,'Lomas de Solymar',2),(954,'Las Barreras',2),(955,'Los Cerrillos',2),(956,'Los Titanes',2),
(957,'Marindia',2),(959,'Neptunia',2),(961,'Parador Tajes',2),(962,'Paso de la Cadena',2),
(963,'Paso de la Paloma',2),(964,'Paso de las Toscas',2),(965,'Paso del Bote',2),
(966,'Paso Palomeque',2),(967,'Paso Villar',2),(968,'Piedra del Toro',2),
(969,'Estación Piedras de Afilar',2),(970,'El Galeón',2),(972,'Puntas de Pantanoso',2),
(973,'San Pedro',2),(974,'Santa Ana',2),(975,'Santa Lucía del Este',2),(976,'Santos Lugares',2),
(977,'Sauce de Solís',2),(978,'Seis Hermanos',2),(979,'Villa Arejo',2),(980,'Villa Argentina',2),
(981,'Villa Encantada',2),(982,'Villa Gabi',2),(983,'Villa Nueva',2),(984,'Villa Porvenir',2),
(985,'La Tuna',2),(986,'Guazú - Virá',2),(987,'Colinas de Solymar',2),(988,'Barrio Remanso',2),
(989,'Villa El Tato',2),(990,'Villa San Cono',2),(991,'Villa Juana',2),(992,'Colinas de Carrasco',2),
(993,'Lomas de Carrasco',2),(994,'Carmel',2),(995,'La Asunción',2),(996,'Quintas del Bosque',2),
(997,'Altos de la Tahona',2),(998,'Asentamiento R.6 Km 24.500',2),(999,'LAS VEGAS',2),
(1000,'CIUDAD DE LA COSTA',2),(1001,'JUAN LACAZE',13),(1002,'SALTO',9),(1005,'FLORIDA',16),
(1006,'FLORIDA',16),(1007,'COLONIA DEL SACRAMENTO',13),(1008,'COLONIA DEL SACRAMENTO',13),
(1009,'COLONIA DEL SACRAMENTO',13),(1010,'JUAN ANTONIO ARTIGAS',2),(1011,'PINAR NORTE',2),
(1012,'DURAZNO',18),(1013,'SAN JOSE DE CARRASCO',14),(1014,'TRINIDAD',15),(1015,'MINAS',17);



