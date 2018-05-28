use AUTOHAUS

drop table novedades
drop table adquiridos
drop table planes_modelos
drop table cuotas
drop table clientes
drop table planes
drop table vehiculos
drop table sucursales
drop table modelos_versiones
drop table versiones
drop table modelos
drop table marcas
drop table localidades
drop table provincias
drop table colores
drop table tipos_vehiculos
drop table nacionalidades
go

create table nacionalidades
(
	cod_nacionalidad			char(3)			not null,
	nom_nacionalidad			varchar(30)		not null,
	CONSTRAINT PK__nacionalidades__END primary key (cod_nacionalidad)
)
go

insert into nacionalidades(cod_nacionalidad, nom_nacionalidad)
values  ('GER', 'Alemana'),
		('ITA', 'Italiana'),
		('FRA', 'Francesa'),
		('USA', 'Americana'),
		('JAP', 'Japonesa')
go

create table provincias
(
	cod_provincia		char(2)			not null,
	nombre_provincia	varchar(30)		not null,
	CONSTRAINT PK__provincias__END primary key(cod_provincia),
	CONSTRAINT UK__provincias__END unique(nombre_provincia)
)
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

create table localidades
(
	cod_provincia		char(2)		not null,
	id_localidad		tinyint		not null,
	nombre_localidad	varchar(30)	not null,
	CONSTRAINT PK__localidades__END primary key(cod_provincia, id_localidad),
	CONSTRAINT UK__localidades__END unique(cod_provincia, nombre_localidad),
	CONSTRAINT FK__loc_prov__END foreign key(cod_provincia) references provincias
)
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')
go

create table marcas
(
	id_marca				smallint		not null,
	nombre_marca			varchar(30)		not null,
	cod_nacionalidad		char(3)			not null,
	CONSTRAINT PK__marcas__END primary key(id_marca),
	CONSTRAINT FK__nacionalidades_marcas__END foreign key(cod_nacionalidad) references nacionalidades
)
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

create table modelos
(
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	nombre_modelo			varchar(30)		not null,
	CONSTRAINT PK__modelos__END	primary key (id_marca, id_modelo),
	CONSTRAINT UK__modelos__END unique (id_marca, nombre_modelo),
	CONSTRAINT FK__modelos_marcas__END foreign key (id_marca) references marcas
)
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

create table versiones
(
	id_version				smallint		not null,
	descripcion				varchar(50)		not null,
	CONSTRAINT PK__versiones__END primary key(id_version)
)
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

create table modelos_versiones
(
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	id_version				smallint		not null,
	CONSTRAINT PK__modelos_versiones__END primary key(id_marca, id_modelo, id_version),
	CONSTRAINT FK__mod_ver_modelos__END foreign key (id_marca, id_modelo) references modelos,
	CONSTRAINT FK__mod_ver_versiones__END foreign key(id_version) references versiones
)
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

create table colores
(
	id_color		tinyint			not null,
	nom_color	varchar(15)		not null,
	CONSTRAINT PK__colores__END primary key(id_color),
	CONSTRAINT UK__colores__END unique(nom_color)
)
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

create table tipos_vehiculos
(
	id_tipo_vehiculo		tinyint			not null,
	nombre_tipo_vehiculo	varchar(30)		not null,
	CONSTRAINT PK__tipos_vehiculos__END primary key(id_tipo_vehiculo)		
)
go

insert into tipos_vehiculos(id_tipo_vehiculo, nombre_tipo_vehiculo)
values(1, 'PARTICULAR'),
	  (2, 'UTILITARIO'),
	  (3, 'COMERCIAL'),
	  (4, 'TRANSPORTE DE PASAJEROS'),
	  (5, 'TRANSPORTE ESCOLAR'),
	  (6, 'CAMION')
go

create table sucursales
(
	id_sucursal				smallint		not null,
	cod_provincia			char(2)			not null,
	id_localidad			tinyint			not null,
	nom_sucursal			varchar(20)		null,
	CONSTRAINT PK__sucursales__END primary key(id_sucursal),
	CONSTRAINT FK__loc_suc__END foreign key (cod_provincia, id_localidad) references localidades
)
go

insert into sucursales(id_sucursal, cod_provincia, id_localidad, nom_sucursal)
values (1, 'CB', 1, 'Sucursal Rio IV'),
	   (2, 'CB', 2, 'Centro'),
	   (3, 'CB', 5, 'Oncativo'),
	   (4, 'CB', 4, 'Jesus Maria')
go

create table clientes
(
	dni_cliente				char(8)			not null,
	apellido_nombre			char(30)		not null,
	edad					smallint		not null,
	domicilio				char(20)		null,
	email					varchar(50)		not null,
	cod_provincia			char(2)			not null,
	id_localidad			tinyint			not null,
	telefono				char(20)		null,
	CONSTRAINT PK__clientes__END primary key(dni_cliente),
	CONSTRAINT FK__clientes_localidades__END foreign key(cod_provincia, id_localidad) references localidades,
	check (edad > 18)
)
go

insert into clientes(dni_cliente, apellido_nombre, edad, domicilio, email, cod_provincia, id_localidad, telefono)
values(25555555, 'Juan Perez', 43, 'Av. Siempre Viva 123', 'juanperez@gmail.com', 'CB', 2, '351-7777777'),
	  (26666666, 'Marcos Juarez', 40, 'Belgrano 450', 'marcosjuarez@gmail.com', 'CB', 2, '351-6666666'),
	  (27777777, 'Pedro Ramirez', 35, 'Rivadavia 59', 'pedroramirez@gmail.com', 'CB', 2, '351-5777777'),
	  (23432255, 'Pablo Alcaraz', 20, 'Potel 6883', 'pabloalcaraz@gmail.com', 'CB', 2, '3517473350')
go

create table vehiculos	
(
	nro_chasis				varchar(15)		not null,
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	id_version				smallint		not null,
	id_color				tinyint			not null,
	id_tipo_vehiculo		tinyint			not null,
	precio					decimal(10, 2)	null,
	año_fabricacion			smallint		null,
	id_sucursal				smallint		not null,	
	nro_patente				char(9)			null,
	CONSTRAINT PK__vehiculos__END primary key(nro_chasis),
	CONSTRAINT FK__veh_modelos_versiones__END foreign key(id_marca, id_modelo, id_version) references modelos_versiones,
	CONSTRAINT FK__veh_colores__END foreign key(id_color) references colores,
	CONSTRAINT FK__veh_tipos_vehiculos__END foreign key(id_tipo_vehiculo) references tipos_vehiculos,
	CONSTRAINT FK__veh_sucursales__END foreign key(id_sucursal) references sucursales
)
go

insert into vehiculos (nro_chasis, id_marca, id_modelo, id_version, id_color, id_tipo_vehiculo, precio, año_fabricacion, id_sucursal, nro_patente)
values(1234, 1, 1, 1, 1, 1, 200.000, 2017, 1, NULL),
	  (1235, 1, 1, 1, 2, 1, 200.000, 2017, 1, NULL),
	  (1236, 1, 1, 1, 3, 1, 200.000, 2017, 1, NULL)
go

create table planes
(
	id_plan					integer			not null, 
	nom_plan				varchar(40)		not null,
	descripcion				varchar(100)	not null,
	cant_cuotas				tinyint			not null,
	entrega_pactada			varchar(50)		not null,
	financiacion			varchar(50)		not null,
	dueño_plan				char(3)			not null check(dueño_plan in ('GOB','CON')),
	CONSTRAINT PK__planes__END primary key(id_plan)
)
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
	--  (303459, 120, '', 2, 2, 50)
go

create table cuotas
(
	id_cuota				smallint		not null,
	dni_cliente				char(8)			not null,
	id_plan					integer			not null,
	importe					decimal(10,2)	null, -- por defecto
	fecha_vencimiento		date			null,
	pagó					char(1)			check (pagó in ('N', 'S'))	DEFAULT 'S',
	CONSTRAINT PK__cuotas__END primary key (id_cuota, dni_cliente, id_plan),
	CONSTRAINT FK__cuotas_planes__END foreign key(id_plan) references planes,
	CONSTRAINT FK__cuotas_clientes__END foreign key(dni_cliente) references clientes
)
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(111, 23432255, 303455, 5000.000, '02-02-2018', 'N'),
	  (112, 23432255, 303455, 5000.000, '02-03-2018', 'N'),
	  (113, 23432255, 303455, 5000.000, '02-04-2018', 'N'),
	  (114, 23432255, 303455, 5000.000, '02-05-2018', 'N'),
	  (115, 23432255, 303455, 5000.000, '02-06-2018', 'N'),
	  (111, 25555555, 303456, 5000.000, '02-02-2018', 'S'),
	  (112, 25555555, 303456, 5000.000, '02-03-2018', 'S'),
	  (113, 25555555, 303456, 5000.000, '02-04-2018', 'S'),
	  (114, 25555555, 303456, 5000.000, '02-05-2018', 'N'),
	  (115, 25555555, 303456, 5000.000, '02-06-2018', 'N'),
	  (111, 26666666, 303457, 5000.000, '02-02-2018', 'N'),
	  (112, 27777777, 303458, 5000.000, '02-03-2018', 'N')
go

create table adquiridos
(
	id_plan					integer			not null, 
	dni_cliente			char(8)			not null,		
	cancelado				char(1)			not null	check (cancelado in ('S', 'N')),
	ganador_sorteo			char(1)			not null	check (ganador_sorteo in ('S', 'N')),
	fecha_sorteado			date			null,
	fecha_entrega			date			null,
	nro_chasis				varchar(15)		null,
	sucursal_suscripcion	smallint		not null,
	CONSTRAINT PK__adquiridos__END primary key (id_plan, dni_cliente),
	CONSTRAINT FK__adquiridos_planes__END foreign key(id_plan) references planes,
	CONSTRAINT FK__adquiridos_vehiculos__END foreign key(nro_chasis) references vehiculos,
	CONSTRAINT FK__adquiridos_sucursales__END foreign key(sucursal_suscripcion) references sucursales
)
go

insert into adquiridos(id_plan, dni_cliente, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis, sucursal_suscripcion)
values(303456, 25555555, 'N', 'N', null, null, null, 1),
	  (303457, 26666666, 'N', 'N', null, null, null, 1),
	  (303458, 27777777, 'N', 'N', null, null, null, 1),
	  (303455, 23432255, 'N', 'N', null, null, null, 1)
go

create table planes_modelos
(
	id_plan					integer			not null, 
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	id_version				smallint		not null,
	CONSTRAINT PK__planes_modelos__END primary key(id_plan, id_marca, id_modelo),
	CONSTRAINT FK__planes_modelos_planes__END foreign key(id_plan) references planes
)
go

create table novedades
(
	id_novedad				integer			not null identity(1,1),
	textoNovedad			varchar(max)	not null,
	CONSTRAINT PK__novedades__END primary key(id_novedad)
)
go

drop procedure dbo.get_estados_cuentas
go

create procedure dbo.get_estados_cuentas
as
begin
   select c.dni_cliente, c.apellido_nombre, c.edad, c.domicilio, c.email, c.telefono, c.id_localidad, c.cod_provincia,
		 ad.cancelado, ad.ganador_sorteo, ad.fecha_entrega, ad.fecha_sorteado, ad.sucursal_suscripcion, ad.nro_chasis,
		 pl.id_plan, pl.descripcion, pl.nom_plan, pl.cant_cuotas, pl.entrega_pactada, pl.financiacion, pl.dueño_plan,
		 cuo.id_cuota, cuo.importe, cuo.fecha_vencimiento, cuo.pagó
	from clientes c (nolock)
	left join adquiridos ad
	on c.dni_cliente = ad.dni_cliente
	right join planes pl
	on ad.id_plan = pl.id_plan
	full join cuotas cuo
	on cuo.dni_cliente = ad.dni_cliente
	and cuo.id_plan = ad.id_plan
    order by c.dni_cliente, pl.id_plan
end;
go

execute dbo.get_estados_cuentas
go

select * 
	from clientes c
		join adquiridos ad
		on c.dni_cliente = ad.dni_cliente
		join planes pl
		on ad.id_plan = pl.id_plan
go

--Trigger 

--1. Actualizacion en tabla VEHICULOS (agregar nro_patente) cuando es adquirido (en la tabla adquiridos)

CREATE TRIGGER tu_ri_patentes
ON adquiridos
FOR update
AS
	BEGIN
		IF EXISTS (
					select * from 
					adquiridos a join vehiculos v
					on a.nro_chasis = v.nro_chasis
					where a.nro_chasis <> null
					and a.fecha_entrega > getDate()
					)
		BEGIN
				UPDATE v
				SET v.nro_patente = 'AA-aaa-aa' -- actualizar. Agregamos una tabla 'patentes'?
				FROM vehiculos v
				where exists (
								select * from 
								adquiridos a join vehiculos v
								on a.nro_chasis = v.nro_chasis
								where a.nro_chasis <> null
								and a.fecha_entrega > getDate()
							)
		END
	END
go

drop procedure dbo.cancelar_ganador
go 

CREATE PROCEDURE dbo.cancelar_ganador
(
	@dni_cliente		char(8),
	@fecha_sorteo		varchar(10),
	@id_plan			integer
)
AS
BEGIN

	if exists (
				SELECT * from adquiridos ad
				where ad.dni_cliente = @dni_cliente
				and ad.id_plan = @id_plan 
			  )
	UPDATE cuo
		SET cuo.pagó = 'S'
		FROM cuotas cuo
		where cuo.dni_cliente = @dni_cliente
		and	  cuo.id_plan = @id_plan

	UPDATE a
		SET a.fecha_sorteado = convert(varchar(8), @fecha_sorteo, 108), 
			a.ganador_sorteo = 'S', -- Cambiamos su estado a ganador
			a.cancelado = 'S'		-- Especificamos que ya estan canceladas sus cuotas
		FROM adquiridos a		
		where a.dni_cliente = @dni_cliente
		and		  a.id_plan = @id_plan
	
END
go

-- execute dbo.cancelar_ganador '25555555', '02-02-18' 

-- lo probamos con 

select * from adquiridos a
where a.ganador_sorteo = 'S'

drop procedure dbo.insertar_novedad
go

create procedure dbo.insertar_novedad
(
	@texto_novedad			varchar(max)
)
AS
	BEGIN
			insert into novedades(textoNovedad)
			values(@texto_novedad)
	END
go

--execute dbo.insertar_novedad 'Hola mundo'

select * from novedades


drop procedure dbo.verificar_cancelado
go

create procedure dbo.verificar_cancelado
(
	@dni_cliente			char(12),
	@id_plan				char(12)
)
AS
	BEGIN
			if exists (
				Select * from clientes c
				join adquiridos ad
				on c.dni_cliente = ad.dni_cliente
				where c.dni_cliente = @dni_cliente
				and ad.cancelado = 'S'
				and ad.id_plan = @id_plan
				)
				begin
					select cancelado = 1
				end
			else
				begin
					select cancelado = 0
				end
			
	END
go

--execute dbo.verificar_cancelado '27777777', 303458

Select * 
	from clientes c
		join adquiridos ad
		on c.dni_cliente = ad.dni_cliente
		where c.dni_cliente = '27777777'
		and ad.cancelado = 'N' 
go



