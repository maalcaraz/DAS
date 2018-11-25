use AUTOHAUS

insert into nacionalidades(cod_nacionalidad, nom_nacionalidad)
values  ('GER', 'Alemana'),
		('ITA', 'Italiana'),
		('FRA', 'Francesa'),
		('USA', 'Americana'),
		('JAP', 'Japonesa')
go

insert into provincias(cod_provincia, nombre_provincia)
values('BA', 'Buenos Aires'),
	  ('CA', 'Catamarca'),
	  ('CH', 'Chaco'),
	  ('CT', 'Chubut'),
	  ('CB', 'Cordoba'),
	  ('CR', 'Corrientes'),
	  ('ER', 'Entre Rios'),
	  ('FO', 'Formosa'),
	  ('JY', 'Jujuy'),
	  ('LP', 'La Pampa'),
	  ('LR', 'La Rioja'),
	  ('MZ', 'Mendoza'),
	  ('MI', 'Misiones'),
	  ('NQ', 'Neuquen'),
	  ('RN', 'Rio Negro'),
	  ('SA', 'Salta '),
	  ('SJ', 'San Juan'),
	  ('SL', 'San Luis'),
	  ('SC', 'Santa Cruz'),
	  ('SF', 'Santa Fe'),
	  ('SE', 'Santiago del Estero'),
	  ('TF', 'Tierra del Fuego'),
	  ('TU', 'Tucuman')
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')
go

insert into marcas (id_marca, nombre_marca, cod_nacionalidad)
values  (1, 'Audi', 'GER'),
	--	(2, 'BMW', 'GER'),
		(3, 'Chevrolet', 'USA'),
	--	(4, 'Mazda', 'JAP'),
	--	(5, 'Mercedes Benz', 'GER' ),
	--	(6, 'Mitsubishi', 'JAP'),
	--	(7, 'Nissan', 'JAP'),
	--	(8, 'Peugeot', 'FRA'),
	--	(9, 'Chrysler/Jeep/Dodge', 'USA'),
	--	(10, 'Citroen', 'FRA'),
	--	(11, 'Fiat', 'ITA'),
		(12, 'Ford', 'USA'),
		(13, 'Volkswagen', 'GER')
go

insert into modelos (id_marca, id_modelo, nombre_modelo)
values  (1, 1, 'A1'),
		(1, 2, 'A3'),
		(1, 3, 'A4'),
		(1, 4, 'Q5'),
	/*	(2, 1, '118i'),
		(2, 2, '320D'),
		(2, 3, '325i'),
		(2, 4, '335i'),
		(2, 5, 'X1D'),
	*/	(3, 1, 'Agile'),
		(3, 2, 'Astra'),
		(3, 3, 'Aveo'),
		(3, 4, 'Captiva'),
		(3, 5, 'Celta'),
		(3, 6, 'Cobalt'),
		(3, 7, 'Corsa'),
		(3 ,8, 'Corsa II'),
		(3, 9, 'Corsa Wagon'),
		(3, 10, 'Cruze'),
		(3, 11, 'Meriva'),
		(3, 12, 'Montana'),
		(3, 13, 'Onix'),
		(3, 14, 'Prisma'),
		(3, 15, 'S10'),
		(3, 16, 'Sonic'),
		(3, 17, 'Spin'),
		(3, 18, 'Tracker'),
		(3, 19, 'Vectra'),
		(3, 20, 'Zafira'),
	/*	(4, 1, 'B2500'),
		(4, 2, 'C200'),
		(5, 1, 'Sprinter'),
		(6, 1, 'L200'),
		(7, 1, 'Frontier'),
		(7, 2, 'March'),
		(7, 3, 'Murano'),
		(7, 4, 'Note'),
		(7, 5, 'NP300'),
		(7, 6, 'Sentra'),
		(7, 7, 'Tiida'),
		(7, 8, 'Versa'),
		(7, 9, 'X-Terra'),
		(7, 10, 'X-Trail'),
		(8, 1 ,'2008'),
		(8, 2, '206'),
		(8, 3, '207'),
		(8, 4, '208'),
		(8, 5, '3008'),
		(8, 6, '307'),
		(8, 7, '308'),
		(8, 8, '408'),
		(8, 9, '508'),
		(9, 1, 'Caravan'),
		(9, 2, 'Compass'),
		(9, 3, 'Gran Cherokee'),
		(9, 4, 'Grand Cherokee Overland'),
		(9, 5, 'Journey'),
		(9, 6, 'Limited'),
		(9, 7, 'Ram'),
		(9, 8, 'Renegade'),
		(10, 1, 'Berlingo'),
		(10, 2, 'C3'),
		(10, 3, 'C3 Aircross'),
		(10, 4, 'C3 Picasso'),
		(10, 5, 'C4'),
		(10, 6, 'DS3'),
		(10, 7, 'S4'),
		(10, 8, 'Xara Picasso'),
		(11, 1, 'Idea'),
		(11, 2, 'Toro'),
		(11, 3, 'Punto'),
		(11, 4, 'Ducato'),
		(11, 5, 'Fiorino'),
		(11, 6, 'Palio'),
		(11, 7, 'Grand Siena'),
		(11, 8, 'Strada'),
	*/	(12, 1, 'Fiesta'),
		(12, 2, 'Focus'),
		(12, 3, 'Ecosport'),
		(12, 4, 'Ka'),
		(12, 5, 'Ranger'),
		(12, 6, 'Mondeo'),
		(12, 7, 'Transit')
go

insert into versiones(id_version, descripcion)
values (1, 'Attractive 1.4'),
	   (2, 'Adventure 1.6'),
	   (3, 'Freedom 4x2 MT'),
	   (4, 'Freedom 4WD MT'),
	   (5, 'Volcano 4WD AT'),
	   (6, 'Essence 1.6'),
	   (7, 'Blackmotion 1.6'),
	   (8, 'Furgón corto 2.3 Techo Normal'),
	   (9, 'Furgón corto 2.3 Techo Normal'),
	   (10, 'Furgón medio 2.3 Techo Normal'),
	   (11, 'Combinato 2.3'),
	   (12, 'Furgon Maxicargo 2.3 Techo Elevado'),
	   (13, '1.4'),
	   (14, 'Adventure 1.6 Cabina Extendida'),
	   (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   (16, 'Working 1.4 Cabina Simple'),
	   (17, 'Working 1.4 Cabina Doble')
go

insert into modelos_versiones (id_marca, id_modelo, id_version)
values(1, 1, 1), -- = Audi
	  (1, 2, 1), -- Verificar versiones (puse todo 1).
	  (1, 3, 1),
	  (1, 4, 1),
	  (3, 1, 1), -- = Chevrolet
	  (3, 5, 1),
	  (3, 10, 1),
	  (3, 20, 1),
	  (12, 1, 1),
	  (12, 2, 1),
	  (12, 5, 1),
	  (12, 7, 1)
	  /*(11, 1, 1), -- 11=Fiat
	  (11, 1, 2),
	  (11, 2, 3),
	  (11, 2, 4),
	  (11, 2, 5),
	  (11, 3, 1),
	  (11, 3, 6),
	  (11, 3, 7),
	  (11, 4, 8),
	  (11, 4, 9),
	  (11, 4, 10),
	  (11, 4, 11),
	  (11, 4, 12),
	  (11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6)*/
go

insert into colores(id_color, nom_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'GRIS PLATA'),
	  (6, 'VERDE'),
	  (7, 'GRIS ACERO'),
	  (8, 'GRIS PETROLEO'),
	  (9, 'AMARILLO')
go

insert into tipos_vehiculos(id_tipo_vehiculo, nombre_tipo_vehiculo)
values(1, 'PARTICULAR'),
	  (2, 'UTILITARIO'),
	  (3, 'COMERCIAL'),
	  (4, 'TRANSPORTE DE PASAJEROS'),
	  (5, 'TRANSPORTE ESCOLAR'),
	  (6, 'CAMION')
go

insert into sucursales(id_sucursal, cod_provincia, id_localidad, nom_sucursal)
values (1, 'CB', 1, 'Sucursal Rio IV'),
	   (2, 'CB', 2, 'Centro'),
	   (3, 'CB', 5, 'Oncativo'),
	   (4, 'CB', 4, 'Jesus Maria')
go

insert into clientes(dni_cliente, apellido_nombre, edad, domicilio, email, cod_provincia, id_localidad, telefono)
values(24444444, 'Maria Gonzales', 37, 'Sabattini 780', 'mariagonzales@gmail.com', 'CB', 2, '351-4444444'),
	  (24560856, 'Juan Perez', 36, 'Av. Siempre Viva 123', 'juanperez@gmail.com', 'CB', 2, '351-7777777'),
	  (26666666, 'Marcos Juarez', 35, 'Belgrano 450', 'marcosjuarez@gmail.com', 'CB', 2, '351-6666666'),
	  (28888888, 'Pablo Alcaraz', 33, 'Potel 6883', 'pabloalcaraz@gmail.com', 'CB', 2, '3518888888'),
	  (29067894, 'Nombre Testing', 32, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (24229583, 'Nombre Testing', 37, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (37563768, 'Nombre Testing', 24, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (31076213, 'Nombre Testing', 30, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (30012228, 'Nombre Testing', 31, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (33967932, 'Nombre Testing', 28, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (27777777, 'Pedro Ramirez', 35, 'Rivadavia 59', 'pedroramirez@gmail.com', 'CB', 2, '351-5777777'),
	  (21111111, 'Tamara Garzon', 40, 'Rondeau 399', 'tamaragarzon@gmail.com', 'CB', 2, '351-1111111')
go

insert into vehiculos (nro_chasis, id_marca, id_modelo, id_version, id_color, id_tipo_vehiculo, precio, año_fabricacion, id_sucursal, nro_patente)
values(1234, 1, 1, 1, 1, 1, 200.000, 2017, 1, NULL),
	  (1235, 1, 1, 1, 2, 1, 200.000, 2017, 1, NULL),
	  (1236, 1, 1, 1, 3, 1, 200.000, 2017, 1, NULL)
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
	--  (303459, 120, '', 2, 2, 50)
go

insert into adquiridos(id_plan, dni_cliente, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis, sucursal_suscripcion)
values(303455, 24444444, 'N', 'N', '07-07-2007', null, null, 1),
	  (303457, 24560856, 'N', 'N', null, null, null, 1),
	  (303458, 26666666, 'N', 'N', null, null, null, 1),
	  (303455, 28888888, 'N', 'N', null, null, null, 1),
	  (303456, 29067894, 'N', 'N', null, null, null, 1),
	  (303457, 24229583, 'N', 'N', null, null, null, 1),
	  (303457, 37563768, 'N', 'N', null, null, null, 1),
	  (303458, 31076213, 'N', 'N', null, null, null, 1),
	  (303456, 30012228, 'N', 'N', null, null, null, 1),
	  (303458, 33967932, 'N', 'N', null, null, null, 1),
	  (303457, 27777777, 'N', 'N', null, null, null, 1),
	  (303455, 21111111, 'N', 'N', null, null, null, 1)
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 24444444, 303455, 5000.000, '02-06-2018', 'N'),
	  (111, 24444444, 303455, 5000.000, '02-02-2018', 'N'),
	  (112, 24444444, 303455, 5000.000, '02-03-2018', 'N'),
	  (113, 24444444, 303455, 5000.000, '02-04-2018', 'N'),
	  (114, 24444444, 303455, 5000.000, '02-05-2018', 'N'),
	  (115, 24444444, 303455, 5000.000, '02-06-2018', 'N'),
	  (116, 24444444, 303455, 5000.000, '02-06-2018', 'N'),
	  (117, 24444444, 303455, 5000.000, '02-06-2018', 'N'),
	  (118, 24444444, 303455, 5000.000, '02-06-2018', 'N'),
	  (119, 24444444, 303455, 5000.000, '02-06-2018', 'N'),
	  (120, 24444444, 303455, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 24560856, 303457, 8000.000, '02-01-2018', 'N'),
	  (111, 24560856, 303457, 8000.000, '02-02-2018', 'N'),
	  (112, 24560856, 303457, 8000.000, '02-03-2018', 'N'),
	  (113, 24560856, 303457, 8000.000, '02-04-2018', 'N'),
	  (114, 24560856, 303457, 8000.000, '02-05-2018', 'N'),
	  (115, 24560856, 303457, 8000.000, '02-06-2018', 'N'),
	  (116, 24560856, 303457, 8000.000, '02-07-2018', 'N'),
	  (117, 24560856, 303457, 8000.000, '02-08-2018', 'N'),
	  (118, 24560856, 303457, 8000.000, '02-09-2018', 'N'),
	  (119, 24560856, 303457, 8000.000, '02-10-2018', 'N'),
	  (120, 24560856, 303457, 8000.000, '02-11-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 26666666, 303458, 7000.000, '02-06-2018', 'N'),
	  (111, 26666666, 303458, 7000.000, '02-02-2018', 'N'),
	  (112, 26666666, 303458, 7000.000, '02-03-2018', 'N'),
	  (113, 26666666, 303458, 7000.000, '02-04-2018', 'N'),
	  (114, 26666666, 303458, 7000.000, '02-05-2018', 'N'),
	  (115, 26666666, 303458, 7000.000, '02-06-2018', 'N'),
	  (116, 26666666, 303458, 7000.000, '02-06-2018', 'N'),
	  (117, 26666666, 303458, 7000.000, '02-06-2018', 'N'),
	  (118, 26666666, 303458, 7000.000, '02-06-2018', 'N'),
	  (119, 26666666, 303458, 7000.000, '02-06-2018', 'N'),
	  (120, 26666666, 303458, 7000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 28888888, 303455, 5000.000, '02-06-2018', 'N'),
	  (111, 28888888, 303455, 5000.000, '02-02-2018', 'N'),
	  (112, 28888888, 303455, 5000.000, '02-03-2018', 'N'),
	  (113, 28888888, 303455, 5000.000, '02-04-2018', 'N'),
	  (114, 28888888, 303455, 5000.000, '02-05-2018', 'N'),
	  (115, 28888888, 303455, 5000.000, '02-06-2018', 'N'),
	  (116, 28888888, 303455, 5000.000, '02-06-2018', 'N'),
	  (117, 28888888, 303455, 5000.000, '02-06-2018', 'N'),
	  (118, 28888888, 303455, 5000.000, '02-06-2018', 'N'),
	  (119, 28888888, 303455, 5000.000, '02-06-2018', 'N'),
	  (120, 28888888, 303455, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 29067894, 303456, 5000.000, '02-06-2018', 'N'),
	  (111, 29067894, 303456, 5000.000, '02-02-2018', 'N'),
	  (112, 29067894, 303456, 5000.000, '02-03-2018', 'N'),
	  (113, 29067894, 303456, 5000.000, '02-04-2018', 'N'),
	  (114, 29067894, 303456, 5000.000, '02-05-2018', 'N'),
	  (115, 29067894, 303456, 5000.000, '02-06-2018', 'N'),
	  (116, 29067894, 303456, 5000.000, '02-06-2018', 'N'),
	  (117, 29067894, 303456, 5000.000, '02-06-2018', 'N'),
	  (118, 29067894, 303456, 5000.000, '02-06-2018', 'N'),
	  (119, 29067894, 303456, 5000.000, '02-06-2018', 'N'),
	  (120, 29067894, 303456, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 24229583, 303457, 5000.000, '02-06-2018', 'N'),
	  (111, 24229583, 303457, 5000.000, '02-02-2018', 'N'),
	  (112, 24229583, 303457, 5000.000, '02-03-2018', 'N'),
	  (113, 24229583, 303457, 5000.000, '02-04-2018', 'N'),
	  (114, 24229583, 303457, 5000.000, '02-05-2018', 'N'),
	  (115, 24229583, 303457, 5000.000, '02-06-2018', 'N'),
	  (116, 24229583, 303457, 5000.000, '02-06-2018', 'N'),
	  (117, 24229583, 303457, 5000.000, '02-06-2018', 'N'),
	  (118, 24229583, 303457, 5000.000, '02-06-2018', 'N'),
	  (119, 24229583, 303457, 5000.000, '02-06-2018', 'N'),
	  (120, 24229583, 303457, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 37563768, 303457, 8000.000, '02-06-2018', 'N'),
	  (111, 37563768, 303457, 8000.000, '02-02-2018', 'N'),
	  (112, 37563768, 303457, 8000.000, '02-03-2018', 'N'),
	  (113, 37563768, 303457, 8000.000, '02-04-2018', 'N'),
	  (114, 37563768, 303457, 8000.000, '02-05-2018', 'N'),
	  (115, 37563768, 303457, 8000.000, '02-06-2018', 'N'),
	  (116, 37563768, 303457, 8000.000, '02-06-2018', 'N'),
	  (117, 37563768, 303457, 8000.000, '02-06-2018', 'N'),
	  (118, 37563768, 303457, 8000.000, '02-06-2018', 'N'),
	  (119, 37563768, 303457, 8000.000, '02-06-2018', 'N'),
	  (120, 37563768, 303457, 8000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 31076213, 303458, 5000.000, '02-06-2018', 'N'),
	  (111, 31076213, 303458, 5000.000, '02-02-2018', 'N'),
	  (112, 31076213, 303458, 5000.000, '02-03-2018', 'N'),
	  (113, 31076213, 303458, 5000.000, '02-04-2018', 'N'),
	  (114, 31076213, 303458, 5000.000, '02-05-2018', 'N'),
	  (115, 31076213, 303458, 5000.000, '02-06-2018', 'N'),
	  (116, 31076213, 303458, 5000.000, '02-06-2018', 'N'),
	  (117, 31076213, 303458, 5000.000, '02-06-2018', 'N'),
	  (118, 31076213, 303458, 5000.000, '02-06-2018', 'N'),
	  (119, 31076213, 303458, 5000.000, '02-06-2018', 'N'),
	  (120, 31076213, 303458, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 30012228, 303456, 5000.000, '02-06-2018', 'N'),
	  (111, 30012228, 303456, 5000.000, '02-02-2018', 'N'),
	  (112, 30012228, 303456, 5000.000, '02-03-2018', 'N'),
	  (113, 30012228, 303456, 5000.000, '02-04-2018', 'N'),
	  (114, 30012228, 303456, 5000.000, '02-05-2018', 'N'),
	  (115, 30012228, 303456, 5000.000, '02-06-2018', 'N'),
	  (116, 30012228, 303456, 5000.000, '02-06-2018', 'N'),
	  (117, 30012228, 303456, 5000.000, '02-06-2018', 'N'),
	  (118, 30012228, 303456, 5000.000, '02-06-2018', 'N'),
	  (119, 30012228, 303456, 5000.000, '02-06-2018', 'N'),
	  (120, 30012228, 303456, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 33967932, 303458, 5000.000, '02-06-2018', 'N'),
	  (111, 33967932, 303458, 5000.000, '02-02-2018', 'N'),
	  (112, 33967932, 303458, 5000.000, '02-03-2018', 'N'),
	  (113, 33967932, 303458, 5000.000, '02-04-2018', 'N'),
	  (114, 33967932, 303458, 5000.000, '02-05-2018', 'N'),
	  (115, 33967932, 303458, 5000.000, '02-06-2018', 'N'),
	  (116, 33967932, 303458, 5000.000, '02-06-2018', 'N'),
	  (117, 33967932, 303458, 5000.000, '02-06-2018', 'N'),
	  (118, 33967932, 303458, 5000.000, '02-06-2018', 'N'),
	  (119, 33967932, 303458, 5000.000, '02-06-2018', 'N'),
	  (120, 33967932, 303458, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 27777777, 303457, 7000.000, '02-06-2018', 'N'),
	  (111, 27777777, 303457, 7000.000, '02-02-2018', 'N'),
	  (112, 27777777, 303457, 7000.000, '02-03-2018', 'N'),
	  (113, 27777777, 303457, 7000.000, '02-04-2018', 'N'),
	  (114, 27777777, 303457, 7000.000, '02-05-2018', 'N'),
	  (115, 27777777, 303457, 7000.000, '02-06-2018', 'N'),
	  (116, 27777777, 303457, 7000.000, '02-06-2018', 'N'),
	  (117, 27777777, 303457, 7000.000, '02-06-2018', 'N'),
	  (118, 27777777, 303457, 7000.000, '02-06-2018', 'N'),
	  (119, 27777777, 303457, 7000.000, '02-06-2018', 'N'),
	  (120, 27777777, 303457, 7000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 21111111, 303455, 5000.000, '02-06-2018', 'N'),
	  (111, 21111111, 303455, 5000.000, '02-02-2018', 'N'),
	  (112, 21111111, 303455, 5000.000, '02-03-2018', 'N'),
	  (113, 21111111, 303455, 5000.000, '02-04-2018', 'N'),
	  (114, 21111111, 303455, 5000.000, '02-05-2018', 'N'),
	  (115, 21111111, 303455, 5000.000, '02-06-2018', 'N'),
	  (116, 21111111, 303455, 5000.000, '02-06-2018', 'N'),
	  (117, 21111111, 303455, 5000.000, '02-06-2018', 'N'),
	  (118, 21111111, 303455, 5000.000, '02-06-2018', 'N'),
	  (119, 21111111, 303455, 5000.000, '02-06-2018', 'N'),
	  (120, 21111111, 303455, 5000.000, '02-06-2018', 'N')
go
;
go

USE MONTIRONI

insert into nacionalidades(cod_nacionalidad, nom_nacionalidad)
values  ('GER', 'Alemana'),
		('ITA', 'Italiana'),
		('FRA', 'Francesa'),
		('USA', 'Americana'),
		('JAP', 'Japonesa')
go

insert into provincias(cod_provincia, nombre_provincia)
values('BA', 'Buenos Aires'),
	  ('CA', 'Catamarca'),
	  ('CH', 'Chaco'),
	  ('CT', 'Chubut'),
	  ('CB', 'Cordoba'),
	  ('CR', 'Corrientes'),
	  ('ER', 'Entre Rios'),
	  ('FO', 'Formosa'),
	  ('JY', 'Jujuy'),
	  ('LP', 'La Pampa'),
	  ('LR', 'La Rioja'),
	  ('MZ', 'Mendoza'),
	  ('MI', 'Misiones'),
	  ('NQ', 'Neuquen'),
	  ('RN', 'Rio Negro'),
	  ('SA', 'Salta '),
	  ('SJ', 'San Juan'),
	  ('SL', 'San Luis'),
	  ('SC', 'Santa Cruz'),
	  ('SF', 'Santa Fe'),
	  ('SE', 'Santiago del Estero'),
	  ('TF', 'Tierra del Fuego'),
	  ('TU', 'Tucuman')
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')
go

insert into marcas (id_marca, nombre_marca, cod_nacionalidad)
values  (1, 'Audi', 'GER'),
	--	(2, 'BMW', 'GER'),
		(3, 'Chevrolet', 'USA'),
	--	(4, 'Mazda', 'JAP'),
	--	(5, 'Mercedes Benz', 'GER' ),
	--	(6, 'Mitsubishi', 'JAP'),
	--	(7, 'Nissan', 'JAP'),
	--	(8, 'Peugeot', 'FRA'),
	--	(9, 'Chrysler/Jeep/Dodge', 'USA'),
	--	(10, 'Citroen', 'FRA'),
	--	(11, 'Fiat', 'ITA'),
		(12, 'Ford', 'USA'),
		(13, 'Volkswagen', 'GER')
go

insert into modelos (id_marca, id_modelo, nombre_modelo)
values  (1, 1, 'A1'),
		(1, 2, 'A3'),
		(1, 3, 'A4'),
		(1, 4, 'Q5'),
	/*	(2, 1, '118i'),
		(2, 2, '320D'),
		(2, 3, '325i'),
		(2, 4, '335i'),
		(2, 5, 'X1D'),
	*/	(3, 1, 'Agile'),
		(3, 2, 'Astra'),
		(3, 3, 'Aveo'),
		(3, 4, 'Captiva'),
		(3, 5, 'Celta'),
		(3, 6, 'Cobalt'),
		(3, 7, 'Corsa'),
		(3 ,8, 'Corsa II'),
		(3, 9, 'Corsa Wagon'),
		(3, 10, 'Cruze'),
		(3, 11, 'Meriva'),
		(3, 12, 'Montana'),
		(3, 13, 'Onix'),
		(3, 14, 'Prisma'),
		(3, 15, 'S10'),
		(3, 16, 'Sonic'),
		(3, 17, 'Spin'),
		(3, 18, 'Tracker'),
		(3, 19, 'Vectra'),
		(3, 20, 'Zafira'),
	/*	(4, 1, 'B2500'),
		(4, 2, 'C200'),
		(5, 1, 'Sprinter'),
		(6, 1, 'L200'),
		(7, 1, 'Frontier'),
		(7, 2, 'March'),
		(7, 3, 'Murano'),
		(7, 4, 'Note'),
		(7, 5, 'NP300'),
		(7, 6, 'Sentra'),
		(7, 7, 'Tiida'),
		(7, 8, 'Versa'),
		(7, 9, 'X-Terra'),
		(7, 10, 'X-Trail'),
		(8, 1 ,'2008'),
		(8, 2, '206'),
		(8, 3, '207'),
		(8, 4, '208'),
		(8, 5, '3008'),
		(8, 6, '307'),
		(8, 7, '308'),
		(8, 8, '408'),
		(8, 9, '508'),
		(9, 1, 'Caravan'),
		(9, 2, 'Compass'),
		(9, 3, 'Gran Cherokee'),
		(9, 4, 'Grand Cherokee Overland'),
		(9, 5, 'Journey'),
		(9, 6, 'Limited'),
		(9, 7, 'Ram'),
		(9, 8, 'Renegade'),
		(10, 1, 'Berlingo'),
		(10, 2, 'C3'),
		(10, 3, 'C3 Aircross'),
		(10, 4, 'C3 Picasso'),
		(10, 5, 'C4'),
		(10, 6, 'DS3'),
		(10, 7, 'S4'),
		(10, 8, 'Xara Picasso'),
		(11, 1, 'Idea'),
		(11, 2, 'Toro'),
		(11, 3, 'Punto'),
		(11, 4, 'Ducato'),
		(11, 5, 'Fiorino'),
		(11, 6, 'Palio'),
		(11, 7, 'Grand Siena'),
		(11, 8, 'Strada'),
	*/	(12, 1, 'Fiesta'),
		(12, 2, 'Focus'),
		(12, 3, 'Ecosport'),
		(12, 4, 'Ka'),
		(12, 5, 'Ranger'),
		(12, 6, 'Mondeo'),
		(12, 7, 'Transit')
go

insert into versiones(id_version, descripcion)
values (1, 'Attractive 1.4'),
	   (2, 'Adventure 1.6'),
	   (3, 'Freedom 4x2 MT'),
	   (4, 'Freedom 4WD MT'),
	   (5, 'Volcano 4WD AT'),
	   (6, 'Essence 1.6'),
	   (7, 'Blackmotion 1.6'),
	   (8, 'Furgón corto 2.3 Techo Normal'),
	   (9, 'Furgón corto 2.3 Techo Normal'),
	   (10, 'Furgón medio 2.3 Techo Normal'),
	   (11, 'Combinato 2.3'),
	   (12, 'Furgon Maxicargo 2.3 Techo Elevado'),
	   (13, '1.4'),
	   (14, 'Adventure 1.6 Cabina Extendida'),
	   (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   (16, 'Working 1.4 Cabina Simple'),
	   (17, 'Working 1.4 Cabina Doble')
go

insert into modelos_versiones (id_marca, id_modelo, id_version)
values(1, 1, 1), -- = Audi
	  (1, 2, 1), -- Verificar versiones (puse todo 1).
	  (1, 3, 1),
	  (1, 4, 1),
	  (3, 1, 1), -- = Chevrolet
	  (3, 5, 1),
	  (3, 10, 1),
	  (3, 20, 1),
	  (12, 1, 1),
	  (12, 2, 1),
	  (12, 5, 1),
	  (12, 7, 1)
	  /*(11, 1, 1), -- 11=Fiat
	  (11, 1, 2),
	  (11, 2, 3),
	  (11, 2, 4),
	  (11, 2, 5),
	  (11, 3, 1),
	  (11, 3, 6),
	  (11, 3, 7),
	  (11, 4, 8),
	  (11, 4, 9),
	  (11, 4, 10),
	  (11, 4, 11),
	  (11, 4, 12),
	  (11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6)*/
go

insert into colores(id_color, nom_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'GRIS PLATA'),
	  (6, 'VERDE'),
	  (7, 'GRIS ACERO'),
	  (8, 'GRIS PETROLEO'),
	  (9, 'AMARILLO')
go

insert into tipos_vehiculos(id_tipo_vehiculo, nombre_tipo_vehiculo)
values(1, 'PARTICULAR'),
	  (2, 'UTILITARIO'),
	  (3, 'COMERCIAL'),
	  (4, 'TRANSPORTE DE PASAJEROS'),
	  (5, 'TRANSPORTE ESCOLAR'),
	  (6, 'CAMION')
go

insert into sucursales(id_sucursal, cod_provincia, id_localidad, nom_sucursal)
values (1, 'CB', 1, 'Sucursal Rio IV'),
	   (2, 'CB', 2, 'Centro'),
	   (3, 'CB', 5, 'Oncativo'),
	   (4, 'CB', 4, 'Jesus Maria')
go

insert into clientes(dni_cliente, apellido_nombre, edad, domicilio, email, cod_provincia, id_localidad, telefono)
values(23432255, 'Pablo Alcaraz', 20, 'Potel 6883', 'pabloalcaraz@gmail.com', 'CB', 2, '3517473350'),
	  (24560856, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (25852618, 'Pedro Ramirez', 35, 'Rivadavia 59', 'pedroramirez@gmail.com', 'CB', 2, '351-5777777'),
	  (31103532, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (28248271, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (33162769, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (22867351, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (33432133, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (25226013, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (39721315, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (35107075, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono')
go

insert into vehiculos (nro_chasis, id_marca, id_modelo, id_version, id_color, id_tipo_vehiculo, precio, año_fabricacion, id_sucursal, nro_patente)
values(1234, 1, 1, 1, 1, 1, 200.000, 2017, 1, NULL),
	  (1235, 1, 1, 1, 2, 1, 200.000, 2017, 1, NULL),
	  (1236, 1, 1, 1, 3, 1, 200.000, 2017, 1, NULL)
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
	--  (303459, 120, '', 2, 2, 50)
go

insert into adquiridos(id_plan, dni_cliente, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis, sucursal_suscripcion)
values(303455, 23432255, 'N', 'N', '07-07-2007', null, null, 1),
	  (303457, 24560856, 'N', 'N', null, null, null, 1),
	  (303458, 25852618, 'N', 'N', null, null, null, 1),
	  (303455, 31103532, 'N', 'N', null, null, null, 1),
	  (303456, 28248271, 'N', 'N', null, null, null, 1),
	  (303457, 33162769, 'N', 'N', null, null, null, 1),
	  (303457, 22867351, 'N', 'N', null, null, null, 1),
	  (303458, 33432133, 'N', 'N', null, null, null, 1),
	  (303456, 25226013, 'N', 'N', null, null, null, 1),
	  (303458, 39721315, 'N', 'N', null, null, null, 1),
	  (303457, 35107075, 'N', 'N', null, null, null, 1)
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (111, 23432255, 303455, 5000.000, '02-02-2018', 'N'),
	  (112, 23432255, 303455, 5000.000, '02-03-2018', 'N'),
	  (113, 23432255, 303455, 5000.000, '02-04-2018', 'N'),
	  (114, 23432255, 303455, 5000.000, '02-05-2018', 'N'),
	  (115, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (116, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (117, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (118, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (119, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (120, 23432255, 303455, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 24560856, 303457, 8000.000, '02-01-2018', 'N'),
	  (111, 24560856, 303457, 8000.000, '02-02-2018', 'N'),
	  (112, 24560856, 303457, 8000.000, '02-03-2018', 'N'),
	  (113, 24560856, 303457, 8000.000, '02-04-2018', 'N'),
	  (114, 24560856, 303457, 8000.000, '02-05-2018', 'N'),
	  (115, 24560856, 303457, 8000.000, '02-06-2018', 'N'),
	  (116, 24560856, 303457, 8000.000, '02-07-2018', 'N'),
	  (117, 24560856, 303457, 8000.000, '02-08-2018', 'N'),
	  (118, 24560856, 303457, 8000.000, '02-09-2018', 'N'),
	  (119, 24560856, 303457, 8000.000, '02-10-2018', 'N'),
	  (120, 24560856, 303457, 8000.000, '02-11-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 25852618, 303458, 7000.000, '02-06-2018', 'N'),
	  (111, 25852618, 303458, 7000.000, '02-02-2018', 'N'),
	  (112, 25852618, 303458, 7000.000, '02-03-2018', 'N'),
	  (113, 25852618, 303458, 7000.000, '02-04-2018', 'N'),
	  (114, 25852618, 303458, 7000.000, '02-05-2018', 'N'),
	  (115, 25852618, 303458, 7000.000, '02-06-2018', 'N'),
	  (116, 25852618, 303458, 7000.000, '02-06-2018', 'N'),
	  (117, 25852618, 303458, 7000.000, '02-06-2018', 'N'),
	  (118, 25852618, 303458, 7000.000, '02-06-2018', 'N'),
	  (119, 25852618, 303458, 7000.000, '02-06-2018', 'N'),
	  (120, 25852618, 303458, 7000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 31103532, 303455, 5000.000, '02-06-2018', 'N'),
	  (111, 31103532, 303455, 5000.000, '02-02-2018', 'N'),
	  (112, 31103532, 303455, 5000.000, '02-03-2018', 'N'),
	  (113, 31103532, 303455, 5000.000, '02-04-2018', 'N'),
	  (114, 31103532, 303455, 5000.000, '02-05-2018', 'N'),
	  (115, 31103532, 303455, 5000.000, '02-06-2018', 'N'),
	  (116, 31103532, 303455, 5000.000, '02-06-2018', 'N'),
	  (117, 31103532, 303455, 5000.000, '02-06-2018', 'N'),
	  (118, 31103532, 303455, 5000.000, '02-06-2018', 'N'),
	  (119, 31103532, 303455, 5000.000, '02-06-2018', 'N'),
	  (120, 31103532, 303455, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 28248271, 303456, 5000.000, '02-06-2018', 'N'),
	  (111, 28248271, 303456, 5000.000, '02-02-2018', 'N'),
	  (112, 28248271, 303456, 5000.000, '02-03-2018', 'N'),
	  (113, 28248271, 303456, 5000.000, '02-04-2018', 'N'),
	  (114, 28248271, 303456, 5000.000, '02-05-2018', 'N'),
	  (115, 28248271, 303456, 5000.000, '02-06-2018', 'N'),
	  (116, 28248271, 303456, 5000.000, '02-06-2018', 'N'),
	  (117, 28248271, 303456, 5000.000, '02-06-2018', 'N'),
	  (118, 28248271, 303456, 5000.000, '02-06-2018', 'N'),
	  (119, 28248271, 303456, 5000.000, '02-06-2018', 'N'),
	  (120, 28248271, 303456, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 33162769, 303457, 5000.000, '02-06-2018', 'N'),
	  (111, 33162769, 303457, 5000.000, '02-02-2018', 'N'),
	  (112, 33162769, 303457, 5000.000, '02-03-2018', 'N'),
	  (113, 33162769, 303457, 5000.000, '02-04-2018', 'N'),
	  (114, 33162769, 303457, 5000.000, '02-05-2018', 'N'),
	  (115, 33162769, 303457, 5000.000, '02-06-2018', 'N'),
	  (116, 33162769, 303457, 5000.000, '02-06-2018', 'N'),
	  (117, 33162769, 303457, 5000.000, '02-06-2018', 'N'),
	  (118, 33162769, 303457, 5000.000, '02-06-2018', 'N'),
	  (119, 33162769, 303457, 5000.000, '02-06-2018', 'N'),
	  (120, 33162769, 303457, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 22867351, 303457, 8000.000, '02-06-2018', 'N'),
	  (111, 22867351, 303457, 8000.000, '02-02-2018', 'N'),
	  (112, 22867351, 303457, 8000.000, '02-03-2018', 'N'),
	  (113, 22867351, 303457, 8000.000, '02-04-2018', 'N'),
	  (114, 22867351, 303457, 8000.000, '02-05-2018', 'N'),
	  (115, 22867351, 303457, 8000.000, '02-06-2018', 'N'),
	  (116, 22867351, 303457, 8000.000, '02-06-2018', 'N'),
	  (117, 22867351, 303457, 8000.000, '02-06-2018', 'N'),
	  (118, 22867351, 303457, 8000.000, '02-06-2018', 'N'),
	  (119, 22867351, 303457, 8000.000, '02-06-2018', 'N'),
	  (120, 22867351, 303457, 8000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 33432133, 303458, 5000.000, '02-06-2018', 'N'),
	  (111, 33432133, 303458, 5000.000, '02-02-2018', 'N'),
	  (112, 33432133, 303458, 5000.000, '02-03-2018', 'N'),
	  (113, 33432133, 303458, 5000.000, '02-04-2018', 'N'),
	  (114, 33432133, 303458, 5000.000, '02-05-2018', 'N'),
	  (115, 33432133, 303458, 5000.000, '02-06-2018', 'N'),
	  (116, 33432133, 303458, 5000.000, '02-06-2018', 'N'),
	  (117, 33432133, 303458, 5000.000, '02-06-2018', 'N'),
	  (118, 33432133, 303458, 5000.000, '02-06-2018', 'N'),
	  (119, 33432133, 303458, 5000.000, '02-06-2018', 'N'),
	  (120, 33432133, 303458, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 25226013, 303456, 5000.000, '02-06-2018', 'N'),
	  (111, 25226013, 303456, 5000.000, '02-02-2018', 'N'),
	  (112, 25226013, 303456, 5000.000, '02-03-2018', 'N'),
	  (113, 25226013, 303456, 5000.000, '02-04-2018', 'N'),
	  (114, 25226013, 303456, 5000.000, '02-05-2018', 'N'),
	  (115, 25226013, 303456, 5000.000, '02-06-2018', 'N'),
	  (116, 25226013, 303456, 5000.000, '02-06-2018', 'N'),
	  (117, 25226013, 303456, 5000.000, '02-06-2018', 'N'),
	  (118, 25226013, 303456, 5000.000, '02-06-2018', 'N'),
	  (119, 25226013, 303456, 5000.000, '02-06-2018', 'N'),
	  (120, 25226013, 303456, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 39721315, 303458, 5000.000, '02-06-2018', 'N'),
	  (111, 39721315, 303458, 5000.000, '02-02-2018', 'N'),
	  (112, 39721315, 303458, 5000.000, '02-03-2018', 'N'),
	  (113, 39721315, 303458, 5000.000, '02-04-2018', 'N'),
	  (114, 39721315, 303458, 5000.000, '02-05-2018', 'N'),
	  (115, 39721315, 303458, 5000.000, '02-06-2018', 'N'),
	  (116, 39721315, 303458, 5000.000, '02-06-2018', 'N'),
	  (117, 39721315, 303458, 5000.000, '02-06-2018', 'N'),
	  (118, 39721315, 303458, 5000.000, '02-06-2018', 'N'),
	  (119, 39721315, 303458, 5000.000, '02-06-2018', 'N'),
	  (120, 39721315, 303458, 5000.000, '02-06-2018', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 35107075, 303457, 7000.000, '02-06-2018', 'N'),
	  (111, 35107075, 303457, 7000.000, '02-02-2018', 'N'),
	  (112, 35107075, 303457, 7000.000, '02-03-2018', 'N'),
	  (113, 35107075, 303457, 7000.000, '02-04-2018', 'N'),
	  (114, 35107075, 303457, 7000.000, '02-05-2018', 'N'),
	  (115, 35107075, 303457, 7000.000, '02-06-2018', 'N'),
	  (116, 35107075, 303457, 7000.000, '02-06-2018', 'N'),
	  (117, 35107075, 303457, 7000.000, '02-06-2018', 'N'),
	  (118, 35107075, 303457, 7000.000, '02-06-2018', 'N'),
	  (119, 35107075, 303457, 7000.000, '02-06-2018', 'N'),
	  (120, 35107075, 303457, 7000.000, '02-06-2018', 'N')
go


;
GO

use COLCAR

insert into nacionalidades(cod_nacionalidad, nom_nacionalidad)
values  ('GER', 'Alemana'),
		('ITA', 'Italiana'),
		('FRA', 'Francesa'),
		('USA', 'Americana'),
		('JAP', 'Japonesa')
go

insert into provincias(cod_provincia, nombre_provincia)
values('BA', 'Buenos Aires'),
	  ('CA', 'Catamarca'),
	  ('CH', 'Chaco'),
	  ('CT', 'Chubut'),
	  ('CB', 'Cordoba'),
	  ('CR', 'Corrientes'),
	  ('ER', 'Entre Rios'),
	  ('FO', 'Formosa'),
	  ('JY', 'Jujuy'),
	  ('LP', 'La Pampa'),
	  ('LR', 'La Rioja'),
	  ('MZ', 'Mendoza'),
	  ('MI', 'Misiones'),
	  ('NQ', 'Neuquen'),
	  ('RN', 'Rio Negro'),
	  ('SA', 'Salta '),
	  ('SJ', 'San Juan'),
	  ('SL', 'San Luis'),
	  ('SC', 'Santa Cruz'),
	  ('SF', 'Santa Fe'),
	  ('SE', 'Santiago del Estero'),
	  ('TF', 'Tierra del Fuego'),
	  ('TU', 'Tucuman')
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')
go

insert into marcas (id_marca, nombre_marca, cod_nacionalidad)
values  (1, 'Audi', 'GER'),
	--	(2, 'BMW', 'GER'),
		(3, 'Chevrolet', 'USA'),
	--	(4, 'Mazda', 'JAP'),
	--	(5, 'Mercedes Benz', 'GER' ),
	--	(6, 'Mitsubishi', 'JAP'),
	--	(7, 'Nissan', 'JAP'),
	--	(8, 'Peugeot', 'FRA'),
	--	(9, 'Chrysler/Jeep/Dodge', 'USA'),
	--	(10, 'Citroen', 'FRA'),
	--	(11, 'Fiat', 'ITA'),
		(12, 'Ford', 'USA'),
		(13, 'Volkswagen', 'GER')
go

insert into modelos (id_marca, id_modelo, nombre_modelo)
values  (1, 1, 'A1'),
		(1, 2, 'A3'),
		(1, 3, 'A4'),
		(1, 4, 'Q5'),
	/*	(2, 1, '118i'),
		(2, 2, '320D'),
		(2, 3, '325i'),
		(2, 4, '335i'),
		(2, 5, 'X1D'),
	*/	(3, 1, 'Agile'),
		(3, 2, 'Astra'),
		(3, 3, 'Aveo'),
		(3, 4, 'Captiva'),
		(3, 5, 'Celta'),
		(3, 6, 'Cobalt'),
		(3, 7, 'Corsa'),
		(3 ,8, 'Corsa II'),
		(3, 9, 'Corsa Wagon'),
		(3, 10, 'Cruze'),
		(3, 11, 'Meriva'),
		(3, 12, 'Montana'),
		(3, 13, 'Onix'),
		(3, 14, 'Prisma'),
		(3, 15, 'S10'),
		(3, 16, 'Sonic'),
		(3, 17, 'Spin'),
		(3, 18, 'Tracker'),
		(3, 19, 'Vectra'),
		(3, 20, 'Zafira'),
	/*	(4, 1, 'B2500'),
		(4, 2, 'C200'),
		(5, 1, 'Sprinter'),
		(6, 1, 'L200'),
		(7, 1, 'Frontier'),
		(7, 2, 'March'),
		(7, 3, 'Murano'),
		(7, 4, 'Note'),
		(7, 5, 'NP300'),
		(7, 6, 'Sentra'),
		(7, 7, 'Tiida'),
		(7, 8, 'Versa'),
		(7, 9, 'X-Terra'),
		(7, 10, 'X-Trail'),
		(8, 1 ,'2008'),
		(8, 2, '206'),
		(8, 3, '207'),
		(8, 4, '208'),
		(8, 5, '3008'),
		(8, 6, '307'),
		(8, 7, '308'),
		(8, 8, '408'),
		(8, 9, '508'),
		(9, 1, 'Caravan'),
		(9, 2, 'Compass'),
		(9, 3, 'Gran Cherokee'),
		(9, 4, 'Grand Cherokee Overland'),
		(9, 5, 'Journey'),
		(9, 6, 'Limited'),
		(9, 7, 'Ram'),
		(9, 8, 'Renegade'),
		(10, 1, 'Berlingo'),
		(10, 2, 'C3'),
		(10, 3, 'C3 Aircross'),
		(10, 4, 'C3 Picasso'),
		(10, 5, 'C4'),
		(10, 6, 'DS3'),
		(10, 7, 'S4'),
		(10, 8, 'Xara Picasso'),
		(11, 1, 'Idea'),
		(11, 2, 'Toro'),
		(11, 3, 'Punto'),
		(11, 4, 'Ducato'),
		(11, 5, 'Fiorino'),
		(11, 6, 'Palio'),
		(11, 7, 'Grand Siena'),
		(11, 8, 'Strada'),
	*/	(12, 1, 'Fiesta'),
		(12, 2, 'Focus'),
		(12, 3, 'Ecosport'),
		(12, 4, 'Ka'),
		(12, 5, 'Ranger'),
		(12, 6, 'Mondeo'),
		(12, 7, 'Transit')
go

insert into versiones(id_version, descripcion)
values (1, 'Attractive 1.4'),
	   (2, 'Adventure 1.6'),
	   (3, 'Freedom 4x2 MT'),
	   (4, 'Freedom 4WD MT'),
	   (5, 'Volcano 4WD AT'),
	   (6, 'Essence 1.6'),
	   (7, 'Blackmotion 1.6'),
	   (8, 'Furgón corto 2.3 Techo Normal'),
	   (9, 'Furgón corto 2.3 Techo Normal'),
	   (10, 'Furgón medio 2.3 Techo Normal'),
	   (11, 'Combinato 2.3'),
	   (12, 'Furgon Maxicargo 2.3 Techo Elevado'),
	   (13, '1.4'),
	   (14, 'Adventure 1.6 Cabina Extendida'),
	   (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   (16, 'Working 1.4 Cabina Simple'),
	   (17, 'Working 1.4 Cabina Doble')
go

insert into modelos_versiones (id_marca, id_modelo, id_version)
values(1, 1, 1), -- = Audi
	  (1, 2, 1), -- Verificar versiones (puse todo 1).
	  (1, 3, 1),
	  (1, 4, 1),
	  (3, 1, 1), -- = Chevrolet
	  (3, 5, 1),
	  (3, 10, 1),
	  (3, 20, 1),
	  (12, 1, 1),
	  (12, 2, 1),
	  (12, 5, 1),
	  (12, 7, 1)
	  /*(11, 1, 1), -- 11=Fiat
	  (11, 1, 2),
	  (11, 2, 3),
	  (11, 2, 4),
	  (11, 2, 5),
	  (11, 3, 1),
	  (11, 3, 6),
	  (11, 3, 7),
	  (11, 4, 8),
	  (11, 4, 9),
	  (11, 4, 10),
	  (11, 4, 11),
	  (11, 4, 12),
	  (11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6)*/
go

insert into colores(id_color, nom_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'GRIS PLATA'),
	  (6, 'VERDE'),
	  (7, 'GRIS ACERO'),
	  (8, 'GRIS PETROLEO'),
	  (9, 'AMARILLO')
go

insert into clientes(dni_cliente, apellido_nombre, edad, domicilio, email, cod_provincia, id_localidad, telefono)
values(25174634, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (24365613, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (22872919, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (33576314, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (23405390, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (34970322, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (33870475, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (30895845, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (21005836, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (29930123, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (32998142, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (28421744, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono')
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
go
;
GO


use ROSSO

insert into provincias(cod_provincia, nombre_provincia)
values('BA', 'Buenos Aires'),
	  ('CA', 'Catamarca'),
	  ('CH', 'Chaco'),
	  ('CT', 'Chubut'),
	  ('CB', 'Cordoba'),
	  ('CR', 'Corrientes'),
	  ('ER', 'Entre Rios'),
	  ('FO', 'Formosa'),
	  ('JY', 'Jujuy'),
	  ('LP', 'La Pampa'),
	  ('LR', 'La Rioja'),
	  ('MZ', 'Mendoza'),
	  ('MI', 'Misiones'),
	  ('NQ', 'Neuquen'),
	  ('RN', 'Rio Negro'),
	  ('SA', 'Salta '),
	  ('SJ', 'San Juan'),
	  ('SL', 'San Luis'),
	  ('SC', 'Santa Cruz'),
	  ('SF', 'Santa Fe'),
	  ('SE', 'Santiago del Estero'),
	  ('TF', 'Tierra del Fuego'),
	  ('TU', 'Tucuman')
go

insert into nacionalidades(cod_nacionalidad, nom_nacionalidad)
values  ('GER', 'Alemana'),
		('ITA', 'Italiana'),
		('FRA', 'Francesa'),
		('USA', 'Americana'),
		('JAP', 'Japonesa')
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')
go

insert into marcas (id_marca, nombre_marca, cod_nacionalidad)
values  (1, 'Audi', 'GER'),
	--	(2, 'BMW', 'GER'),
		(3, 'Chevrolet', 'USA'),
	--	(4, 'Mazda', 'JAP'),
	--	(5, 'Mercedes Benz', 'GER' ),
	--	(6, 'Mitsubishi', 'JAP'),
	--	(7, 'Nissan', 'JAP'),
	--	(8, 'Peugeot', 'FRA'),
	--	(9, 'Chrysler/Jeep/Dodge', 'USA'),
	--	(10, 'Citroen', 'FRA'),
	--	(11, 'Fiat', 'ITA'),
		(12, 'Ford', 'USA'),
		(13, 'Volkswagen', 'GER')
go

insert into modelos (id_marca, id_modelo, nombre_modelo)
values  (1, 1, 'A1'),
		(1, 2, 'A3'),
		(1, 3, 'A4'),
		(1, 4, 'Q5'),
	/*	(2, 1, '118i'),
		(2, 2, '320D'),
		(2, 3, '325i'),
		(2, 4, '335i'),
		(2, 5, 'X1D'),
	*/	(3, 1, 'Agile'),
		(3, 2, 'Astra'),
		(3, 3, 'Aveo'),
		(3, 4, 'Captiva'),
		(3, 5, 'Celta'),
		(3, 6, 'Cobalt'),
		(3, 7, 'Corsa'),
		(3 ,8, 'Corsa II'),
		(3, 9, 'Corsa Wagon'),
		(3, 10, 'Cruze'),
		(3, 11, 'Meriva'),
		(3, 12, 'Montana'),
		(3, 13, 'Onix'),
		(3, 14, 'Prisma'),
		(3, 15, 'S10'),
		(3, 16, 'Sonic'),
		(3, 17, 'Spin'),
		(3, 18, 'Tracker'),
		(3, 19, 'Vectra'),
		(3, 20, 'Zafira'),
	/*	(4, 1, 'B2500'),
		(4, 2, 'C200'),
		(5, 1, 'Sprinter'),
		(6, 1, 'L200'),
		(7, 1, 'Frontier'),
		(7, 2, 'March'),
		(7, 3, 'Murano'),
		(7, 4, 'Note'),
		(7, 5, 'NP300'),
		(7, 6, 'Sentra'),
		(7, 7, 'Tiida'),
		(7, 8, 'Versa'),
		(7, 9, 'X-Terra'),
		(7, 10, 'X-Trail'),
		(8, 1 ,'2008'),
		(8, 2, '206'),
		(8, 3, '207'),
		(8, 4, '208'),
		(8, 5, '3008'),
		(8, 6, '307'),
		(8, 7, '308'),
		(8, 8, '408'),
		(8, 9, '508'),
		(9, 1, 'Caravan'),
		(9, 2, 'Compass'),
		(9, 3, 'Gran Cherokee'),
		(9, 4, 'Grand Cherokee Overland'),
		(9, 5, 'Journey'),
		(9, 6, 'Limited'),
		(9, 7, 'Ram'),
		(9, 8, 'Renegade'),
		(10, 1, 'Berlingo'),
		(10, 2, 'C3'),
		(10, 3, 'C3 Aircross'),
		(10, 4, 'C3 Picasso'),
		(10, 5, 'C4'),
		(10, 6, 'DS3'),
		(10, 7, 'S4'),
		(10, 8, 'Xara Picasso'),
		(11, 1, 'Idea'),
		(11, 2, 'Toro'),
		(11, 3, 'Punto'),
		(11, 4, 'Ducato'),
		(11, 5, 'Fiorino'),
		(11, 6, 'Palio'),
		(11, 7, 'Grand Siena'),
		(11, 8, 'Strada'),
	*/	(12, 1, 'Fiesta'),
		(12, 2, 'Focus'),
		(12, 3, 'Ecosport'),
		(12, 4, 'Ka'),
		(12, 5, 'Ranger'),
		(12, 6, 'Mondeo'),
		(12, 7, 'Transit')
go

insert into versiones(id_version, descripcion)
values (1, 'Attractive 1.4'),
	   (2, 'Adventure 1.6'),
	   (3, 'Freedom 4x2 MT'),
	   (4, 'Freedom 4WD MT'),
	   (5, 'Volcano 4WD AT'),
	   (6, 'Essence 1.6'),
	   (7, 'Blackmotion 1.6'),
	   (8, 'Furgón corto 2.3 Techo Normal'),
	   (9, 'Furgón corto 2.3 Techo Normal'),
	   (10, 'Furgón medio 2.3 Techo Normal'),
	   (11, 'Combinato 2.3'),
	   (12, 'Furgon Maxicargo 2.3 Techo Elevado'),
	   (13, '1.4'),
	   (14, 'Adventure 1.6 Cabina Extendida'),
	   (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   (16, 'Working 1.4 Cabina Simple'),
	   (17, 'Working 1.4 Cabina Doble')
go

insert into modelos_versiones (id_marca, id_modelo, id_version)
values(1, 1, 1), -- = Audi
	  (1, 2, 1), -- Verificar versiones (puse todo 1).
	  (1, 3, 1),
	  (1, 4, 1),
	  (3, 1, 1), -- = Chevrolet
	  (3, 5, 1),
	  (3, 10, 1),
	  (3, 20, 1),
	  (12, 1, 1),
	  (12, 2, 1),
	  (12, 5, 1),
	  (12, 7, 1)
	  /*(11, 1, 1), -- 11=Fiat
	  (11, 1, 2),
	  (11, 2, 3),
	  (11, 2, 4),
	  (11, 2, 5),
	  (11, 3, 1),
	  (11, 3, 6),
	  (11, 3, 7),
	  (11, 4, 8),
	  (11, 4, 9),
	  (11, 4, 10),
	  (11, 4, 11),
	  (11, 4, 12),
	  (11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6)*/
go

insert into colores(id_color, nom_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'GRIS PLATA'),
	  (6, 'VERDE'),
	  (7, 'GRIS ACERO'),
	  (8, 'GRIS PETROLEO'),
	  (9, 'AMARILLO')
go

insert into clientes(dni_cliente, apellido_nombre, edad, domicilio, email, cod_provincia, id_localidad, telefono)
values(34623085, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (32480301, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (27802751, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (30558609, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (29287534, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (35757422, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (34354917, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (35337920, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (30726830, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (25059766, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (39704529, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono')
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
go
;
GO



use TAGLE

insert into nacionalidades(cod_nacionalidad, nom_nacionalidad)
values  ('GER', 'Alemana'),
		('ITA', 'Italiana'),
		('FRA', 'Francesa'),
		('USA', 'Americana'),
		('JAP', 'Japonesa')
go

insert into provincias(cod_provincia, nombre_provincia)
values('BA', 'Buenos Aires'),
	  ('CA', 'Catamarca'),
	  ('CH', 'Chaco'),
	  ('CT', 'Chubut'),
	  ('CB', 'Cordoba'),
	  ('CR', 'Corrientes'),
	  ('ER', 'Entre Rios'),
	  ('FO', 'Formosa'),
	  ('JY', 'Jujuy'),
	  ('LP', 'La Pampa'),
	  ('LR', 'La Rioja'),
	  ('MZ', 'Mendoza'),
	  ('MI', 'Misiones'),
	  ('NQ', 'Neuquen'),
	  ('RN', 'Rio Negro'),
	  ('SA', 'Salta '),
	  ('SJ', 'San Juan'),
	  ('SL', 'San Luis'),
	  ('SC', 'Santa Cruz'),
	  ('SF', 'Santa Fe'),
	  ('SE', 'Santiago del Estero'),
	  ('TF', 'Tierra del Fuego'),
	  ('TU', 'Tucuman')
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')
go

insert into marcas (id_marca, nombre_marca, cod_nacionalidad)
values  (1, 'Audi', 'GER'),
	--	(2, 'BMW', 'GER'),
		(3, 'Chevrolet', 'USA'),
	--	(4, 'Mazda', 'JAP'),
	--	(5, 'Mercedes Benz', 'GER' ),
	--	(6, 'Mitsubishi', 'JAP'),
	--	(7, 'Nissan', 'JAP'),
	--	(8, 'Peugeot', 'FRA'),
	--	(9, 'Chrysler/Jeep/Dodge', 'USA'),
	--	(10, 'Citroen', 'FRA'),
	--	(11, 'Fiat', 'ITA'),
		(12, 'Ford', 'USA'),
		(13, 'Volkswagen', 'GER')
go

insert into modelos (id_marca, id_modelo, nombre_modelo)
values  (1, 1, 'A1'),
		(1, 2, 'A3'),
		(1, 3, 'A4'),
		(1, 4, 'Q5'),
	/*	(2, 1, '118i'),
		(2, 2, '320D'),
		(2, 3, '325i'),
		(2, 4, '335i'),
		(2, 5, 'X1D'),
	*/	(3, 1, 'Agile'),
		(3, 2, 'Astra'),
		(3, 3, 'Aveo'),
		(3, 4, 'Captiva'),
		(3, 5, 'Celta'),
		(3, 6, 'Cobalt'),
		(3, 7, 'Corsa'),
		(3 ,8, 'Corsa II'),
		(3, 9, 'Corsa Wagon'),
		(3, 10, 'Cruze'),
		(3, 11, 'Meriva'),
		(3, 12, 'Montana'),
		(3, 13, 'Onix'),
		(3, 14, 'Prisma'),
		(3, 15, 'S10'),
		(3, 16, 'Sonic'),
		(3, 17, 'Spin'),
		(3, 18, 'Tracker'),
		(3, 19, 'Vectra'),
		(3, 20, 'Zafira'),
	/*	(4, 1, 'B2500'),
		(4, 2, 'C200'),
		(5, 1, 'Sprinter'),
		(6, 1, 'L200'),
		(7, 1, 'Frontier'),
		(7, 2, 'March'),
		(7, 3, 'Murano'),
		(7, 4, 'Note'),
		(7, 5, 'NP300'),
		(7, 6, 'Sentra'),
		(7, 7, 'Tiida'),
		(7, 8, 'Versa'),
		(7, 9, 'X-Terra'),
		(7, 10, 'X-Trail'),
		(8, 1 ,'2008'),
		(8, 2, '206'),
		(8, 3, '207'),
		(8, 4, '208'),
		(8, 5, '3008'),
		(8, 6, '307'),
		(8, 7, '308'),
		(8, 8, '408'),
		(8, 9, '508'),
		(9, 1, 'Caravan'),
		(9, 2, 'Compass'),
		(9, 3, 'Gran Cherokee'),
		(9, 4, 'Grand Cherokee Overland'),
		(9, 5, 'Journey'),
		(9, 6, 'Limited'),
		(9, 7, 'Ram'),
		(9, 8, 'Renegade'),
		(10, 1, 'Berlingo'),
		(10, 2, 'C3'),
		(10, 3, 'C3 Aircross'),
		(10, 4, 'C3 Picasso'),
		(10, 5, 'C4'),
		(10, 6, 'DS3'),
		(10, 7, 'S4'),
		(10, 8, 'Xara Picasso'),
		(11, 1, 'Idea'),
		(11, 2, 'Toro'),
		(11, 3, 'Punto'),
		(11, 4, 'Ducato'),
		(11, 5, 'Fiorino'),
		(11, 6, 'Palio'),
		(11, 7, 'Grand Siena'),
		(11, 8, 'Strada'),
	*/	(12, 1, 'Fiesta'),
		(12, 2, 'Focus'),
		(12, 3, 'Ecosport'),
		(12, 4, 'Ka'),
		(12, 5, 'Ranger'),
		(12, 6, 'Mondeo'),
		(12, 7, 'Transit')
go

insert into versiones(id_version, descripcion)
values (1, 'Attractive 1.4'),
	   (2, 'Adventure 1.6'),
	   (3, 'Freedom 4x2 MT'),
	   (4, 'Freedom 4WD MT'),
	   (5, 'Volcano 4WD AT'),
	   (6, 'Essence 1.6'),
	   (7, 'Blackmotion 1.6'),
	   (8, 'Furgón corto 2.3 Techo Normal'),
	   (9, 'Furgón corto 2.3 Techo Normal'),
	   (10, 'Furgón medio 2.3 Techo Normal'),
	   (11, 'Combinato 2.3'),
	   (12, 'Furgon Maxicargo 2.3 Techo Elevado'),
	   (13, '1.4'),
	   (14, 'Adventure 1.6 Cabina Extendida'),
	   (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   (16, 'Working 1.4 Cabina Simple'),
	   (17, 'Working 1.4 Cabina Doble')
go

insert into modelos_versiones (id_marca, id_modelo, id_version)
values(1, 1, 1), -- = Audi
	  (1, 2, 1), -- Verificar versiones (puse todo 1).
	  (1, 3, 1),
	  (1, 4, 1),
	  (3, 1, 1), -- = Chevrolet
	  (3, 5, 1),
	  (3, 10, 1),
	  (3, 20, 1),
	  (12, 1, 1),
	  (12, 2, 1),
	  (12, 5, 1),
	  (12, 7, 1)
	  /*(11, 1, 1), -- 11=Fiat
	  (11, 1, 2),
	  (11, 2, 3),
	  (11, 2, 4),
	  (11, 2, 5),
	  (11, 3, 1),
	  (11, 3, 6),
	  (11, 3, 7),
	  (11, 4, 8),
	  (11, 4, 9),
	  (11, 4, 10),
	  (11, 4, 11),
	  (11, 4, 12),
	  (11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6)*/
go

insert into colores(id_color, nom_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'GRIS PLATA'),
	  (6, 'VERDE'),
	  (7, 'GRIS ACERO'),
	  (8, 'GRIS PETROLEO'),
	  (9, 'AMARILLO')
go

insert into clientes(dni_cliente, apellido_nombre, edad, domicilio, email, cod_provincia, id_localidad, telefono)
values(24280103, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (26374653, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (24834927, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (24215278, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (27835283, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (32039643, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (22458775, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (34628607, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (36149183, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (35372003, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono'),
	  (25220710, 'Nombre Testing', 55, 'Dir testing', 'email@testing.com', 'CB', 2, 'telefono')
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
go

;
GO
---------------------------------------------------------




/*
insert into localidades(id_localidad, nom_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')

insert into concesionarias (id_concesionaria, 
							nom_concesionaria, 
							cuit,
							cod_provincia,
							id_localidad,
							direccion,
							email,
							telefono)
values (1, 'Montironi', '1-1111-1', 'CB', 2, 'Direccion Montironi 600', 'montironi@gmail.com', '351-111111'),
	   (2, 'Tablada', '2-2222-2', 'CB', 1, 'Direccion Tablada 1500', 'tablada@gmail.com', '351-2222222'),
	   (3, 'Auto Haus', '3-3333-3', 'TU', 3, 'Direccion Auto Haus 450', 'autohaus@gmail.com', '351-3333333'),
	   (4, 'Maipú', '4-4444-4', 'CB', 2, 'Direccion Maipu 300', 'maipu@gmail.com', '351-4444444'),
	   (5, 'Colcar', '5-5555-5', 'TU', 3, 'Direccion Colcar 781', 'colcar@gmail.com', '351-555555')


insert into marcas (id_marca, nom_marca, nacionalidad)
values  (1, 'Audi', 'Alemania'),
		(2, 'BMW', 'Alemania'),
		(3, 'Chevrolet', 'America'),
		(4, 'Mazda', 'Japonesa'),
		(5, 'Mercedes Benz', 'Alemania' ),
		(6, 'Mitsubishi', 'Japonesa'),
		(7, 'Nissan', 'Japonesa'),
		(8, 'Peugeot', 'Francia'),
		(9, 'Chrysler/Jeep/Dodge', 'America'),
		(10, 'Citroen', 'Francia'),
		(11, 'Fiat', 'Italiana'),
		(12, 'Ford', 'Americana')

insert into modelos (id_marca, id_modelo, nom_modelo)
values  (1, 1, 'A1'),
		(1, 2, 'A3'),
		(1, 3, 'A4'),
		(1, 4, 'Q5'),
		(2, 1, '118i'),
		(2, 2, '320D'),
		(2, 3, '325i'),
		(2, 4, '335i'),
		(2, 5, 'X1D'),
		(3, 1, 'Agile'),
		(3, 2, 'Astra'),
		(3, 3, 'Aveo'),
		(3, 4, 'Captiva'),
		(3, 5, 'Celta'),
		(3, 6, 'Cobalt'),
		(3, 7, 'Corsa'),
		(3 ,8, 'Corsa II'),
		(3, 9, 'Corsa Wagon'),
		(3, 10, 'Cruze'),
		(3, 11, 'Meriva'),
		(3, 12, 'Montana'),
		(3, 13, 'Onix'),
		(3, 14, 'Prisma'),
		(3, 15, 'S10'),
		(3, 16, 'Sonic'),
		(3, 17, 'Spin'),
		(3, 18, 'Tracker'),
		(3, 19, 'Vectra'),
		(3, 20, 'Zafira'),
		(4, 1, 'B2500'),
		(4, 2, 'C200'),
		(5, 1, 'Sprinter'),
		(6, 1, 'L200'),
		(7, 1, 'Frontier'),
		(7, 2, 'March'),
		(7, 3, 'Murano'),
		(7, 4, 'Note'),
		(7, 5, 'NP300'),
		(7, 6, 'Sentra'),
		(7, 7, 'Tiida'),
		(7, 8, 'Versa'),
		(7, 9, 'X-Terra'),
		(7, 10, 'X-Trail'),
		(8, 1 ,'2008'),
		(8, 2, '206'),
		(8, 3, '207'),
		(8, 4, '208'),
		(8, 5, '3008'),
		(8, 6, '307'),
		(8, 7, '308'),
		(8, 8, '408'),
		(8, 9, '508'),
		(9, 1, 'Caravan'),
		(9, 2, 'Compass'),
		(9, 3, 'Gran Cherokee'),
		(9, 4, 'Grand Cherokee Overland'),
		(9, 5, 'Journey'),
		(9, 6, 'Limited'),
		(9, 7, 'Ram'),
		(9, 8, 'Renegade'),
		(10, 1, 'Berlingo'),
		(10, 2, 'C3'),
		(10, 3, 'C3 Aircross'),
		(10, 4, 'C3 Picasso'),
		(10, 5, 'C4'),
		(10, 6, 'DS3'),
		(10, 7, 'S4'),
		(10, 8, 'Xara Picasso'),
		(11, 1, 'Idea'),
		(11, 2, 'Toro'),
		(11, 3, 'Punto'),
		(11, 4, 'Ducato'),
		(11, 5, 'Fiorino'),
		(11, 6, 'Palio'),
		(11, 7, 'Grand Siena'),
		(11, 8, 'Strada'),
		-- Faltan los Ford


insert into sucursales(id_concesionaria, id_sucursal, cod_provincia, id_localidad, nombre_sucursal)
values(1, 1, 'CB', 2),
	  (1, 2, 'CB', 4),
	  (1, 3, 'CB', 5) -- Hasta aca son las sucursales de Montironi
go

insert into colores(id_color, nombre_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'PLATA')
go

insert into versiones(id_version, nom_version)
values (1, 'Attractive 1.4'),
	   (2, 'Adventure 1.6'),
	   (3, 'Freedom 4x2 MT'),
	   (4, 'Freedom 4WD MT'),
	   (5, 'Volcano 4WD AT'),
	   (6, 'Essence 1.6'),
	   (7, 'Blackmotion 1.6'),
	   (8, 'Furgón corto 2.3 Techo Normal'),
	   (9, 'Furgón corto 2.3 Techo Normal'),
	   (10, 'Furgón medio 2.3 Techo Normal'),
	   (11, 'Combinato 2.3'),
	   (12, 'Furgon Maxicargo 2.3 Techo Elevado')
	   (13, '1.4'),
	   (14, 'Adventure 1.6 Cabina Extendida'),
	   (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   (16, 'Working 1.4 Cabina Simple'),
	   (17, 'Working 1.4 Cabina Doble'),
	   (18, '')
go

insert into modelos_versiones (id_marca, id_modelo, id_version)
values(11, 1, 1), -- 11=Fiat
	  (11, 1, 2),
	  (11, 2, 3),
	  (11, 2, 4),
	  (11, 2, 5),
	  (11, 3, 1),
	  (11, 3, 6),
	  (11, 3, 7),
	  (11, 4, 8),
	  (11, 4, 9),
	  (11, 4, 10),
	  (11, 4, 11),
	  (11, 4, 12),
	  (11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6),
	  ()
go

insert into tipos_vehiculos(id_tipo_vehiculo, nombre_tipo_vehiculo)
values(1, 'PARTICULAR'),
	  (2, 'UTILITARIO'),
	  (3, 'COMERCIAL'),
	  (4, 'TAXI'),
	  (5, 'CAMION')
go
*/
