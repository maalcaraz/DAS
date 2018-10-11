use DAS

insert into provincias(cod_provincia, nom_provincia)
values('CB', 'Cordoba'),
	  ('SF', 'Santa Fe'),
	  ('TU', 'Tucuman'), 
	  ('CR', 'Corrientes')


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

insert into vehiculos (nro_serie, id_marca, id_modelo, id_version, id_color, id_tipo_vehiculo, precio, año_fabricacion)
values()
go

insert into compradores(dni_comprador, nom_comprador, edad, domicilio, cod_provincia, id_localidad, telefono)
values()
go

insert into planes (id_plan, id_marca, id_modelo, id_version, cant_cuotas)
values()
go

insert into cuotas(id_cuota, id_plan, monto, fecha_vencimiento, pagó)
values()
go

insert into plan_comprador (id_plan, dni_comprador)
values()
go

insert into ganadores (id_plan, dni_comprador,fecha_sorteo)
values()
go
