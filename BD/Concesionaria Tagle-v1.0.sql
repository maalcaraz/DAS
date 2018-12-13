use TAGLE
go

/*******************************

	DROPS

********************************/

drop table dbo.novedades
drop table dbo.adquiridos
drop table dbo.planes_modelos
drop table dbo.cuotas
drop table dbo.clientes
drop table dbo.planes
drop table dbo.vehiculos
drop table dbo.sucursales
drop table dbo.modelos_versiones
drop table dbo.versiones
drop table dbo.modelos
drop table dbo.marcas
drop table dbo.localidades
drop table dbo.provincias
drop table dbo.colores
drop table dbo.tipos_vehiculos
drop table dbo.nacionalidades
drop procedure dbo.cancelar_ganador
drop procedure dbo.get_estados_cuentas
-- drop trigger dbo.tu_ri_cuotas_adquiridos
--drop trigger dbo.tu_ri_patentes
go

/*******************************

	TABLES

********************************/

create table nacionalidades
(
	cod_nacionalidad			char(3)			not null,
	nom_nacionalidad			varchar(30)		not null,
	CONSTRAINT PK__nacionalidades__END primary key (cod_nacionalidad)
)
go

create table provincias
(
	cod_provincia		char(2)			not null,
	nombre_provincia	varchar(30)		not null,
	CONSTRAINT PK__provincias__END primary key(cod_provincia),
	CONSTRAINT UK__provincias__END unique(nombre_provincia)
)
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

create table marcas
(
	id_marca				smallint		not null,
	nombre_marca			varchar(30)		not null,
	cod_nacionalidad		char(3)			not null,
	CONSTRAINT PK__marcas__END primary key(id_marca),
	CONSTRAINT FK__nacionalidades_marcas__END foreign key(cod_nacionalidad) references nacionalidades
)
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

create table versiones
(
	id_version				smallint		not null,
	descripcion				varchar(50)		not null,
	CONSTRAINT PK__versiones__END primary key(id_version)
)
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


create table colores
(
	id_color		tinyint			not null,
	nom_color	varchar(15)		not null,
	CONSTRAINT PK__colores__END primary key(id_color),
	CONSTRAINT UK__colores__END unique(nom_color)
)
go

create table tipos_vehiculos
(
	id_tipo_vehiculo		tinyint			not null,
	nombre_tipo_vehiculo	varchar(30)		not null,
	CONSTRAINT PK__tipos_vehiculos__END primary key(id_tipo_vehiculo)		
)
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
	fecha_compra_plan		date			not null default getDate(), 
	CONSTRAINT PK__adquiridos__END primary key (id_plan, dni_cliente),
	CONSTRAINT FK__adquiridos_planes__END foreign key(id_plan) references planes,
	CONSTRAINT FK__adquiridos_vehiculos__END foreign key(nro_chasis) references vehiculos,
	CONSTRAINT FK__adquiridos_sucursales__END foreign key(sucursal_suscripcion) references sucursales
)
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

/*******************************

	PROCEDURES

********************************/


create procedure dbo.get_estados_cuentas
(
	@id_duenio_plan		char(3)
)
as
begin
   select c.dni_cliente, c.apellido_nombre, c.edad, c.domicilio, c.email, c.telefono, c.id_localidad, c.cod_provincia,
		 ad.cancelado, ad.ganador_sorteo, ad.fecha_entrega, ad.fecha_sorteado, ad.sucursal_suscripcion, ad.nro_chasis,
		 ad.fecha_compra_plan, pl.id_plan, pl.descripcion, pl.nom_plan, pl.cant_cuotas, pl.entrega_pactada, pl.financiacion, pl.dueño_plan,
		 cuo.id_cuota, cuo.importe, cuo.fecha_vencimiento, cuo.pagó
	from clientes c (nolock)
	left join adquiridos ad
	on c.dni_cliente = ad.dni_cliente
	right join planes pl
	on ad.id_plan = pl.id_plan
	full join cuotas cuo
	on cuo.dni_cliente = ad.dni_cliente
	and cuo.id_plan = ad.id_plan
	where pl.dueño_plan = @id_duenio_plan
    order by c.dni_cliente, pl.id_plan
end;
go

--execute dbo.get_estados_cuentas 'GOB'

select * 
	from clientes c
		join adquiridos ad
		on c.dni_cliente = ad.dni_cliente
		join planes pl
		on ad.id_plan = pl.id_plan
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
	UPDATE a
		SET a.fecha_sorteado = convert(date, @fecha_sorteo, 105), 
			a.ganador_sorteo = 'S', -- Cambiamos su estado a ganador
			a.cancelado = 'S'		-- Especificamos que ya estan canceladas sus cuotas
		FROM adquiridos a		
		where a.dni_cliente = @dni_cliente
		and   a.id_plan = @id_plan
END
go


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

Select * 
	from clientes c
		join adquiridos ad
		on c.dni_cliente = ad.dni_cliente
		where ad.cancelado = 'S' 
go

/*	execute dbo.cancelar_ganador '25555555', '02-02-18' ,'303456'
	lo probamos con  */

/*

select * from adquiridos a
where a.ganador_sorteo = 'S'

select * from cuotas cuo
where cuo.dni_cliente = 25555555
and cuo.id_plan = 303456
go

*/

-- Trigger para la cancelacion de cuotas: Para que cada vez que un adquirido se declare como ganador, automaticamente se le cancelen las 
-- cuotas restantes
/*
	- Tabla: CUOTAS
	- Operaciones: Update

*/

/*******************************

	TRIGGERS

********************************/

CREATE TRIGGER tu_ri_cuotas_adquiridos
on adquiridos
FOR update
AS
	BEGIN
		IF exists (
					select * from inserted i
					where i.cancelado = 'S'
					)
		BEGIN	
			UPDATE cuo
				SET cuo.pagó = 'S'
				FROM cuotas cuo join inserted i 
				on cuo.dni_cliente = i.dni_cliente
				and cuo.id_plan = i.id_plan
				where cuo.dni_cliente = i.dni_cliente
				and	  cuo.id_plan = i.id_plan
		END
	END
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

/*****************************/
/*		INSERTS		 		 */
/*****************************/


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
		(2, 'BMW', 'GER'),
		(3, 'Chevrolet', 'USA'),
		(4, 'Mazda', 'JAP'),
		(5, 'Mercedes Benz', 'GER' ),
		(6, 'Mitsubishi', 'JAP'),
		(7, 'Nissan', 'JAP'),
		(8, 'Peugeot', 'FRA'),
		(9, 'Chrysler/Jeep/Dodge', 'USA'),
		(10, 'Citroen', 'FRA'),
		(11, 'Fiat', 'ITA'),
		(12, 'Ford', 'USA'),
		(13, 'Volkswagen', 'GER')
go

insert into modelos (id_marca, id_modelo, nombre_modelo)
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
		(12, 1, 'Fiesta'),
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
	  (12, 7, 1),
	  (11, 1, 1), -- 11=Fiat
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
	  (11, 7, 6)
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
values(30245785, 'juan page', 55, 'Luis Reinaudi 1100', 'email@testing.com', 'CB', 1, '3518749568'),
	  (29485621, 'juan hendrix', 55, 'Dean Funes 975', 'email@testing.com', 'CB', 2, '3514875125'),
	  (28451987, 'mirko marley', 55, 'Buenos Aires 785', 'email@testing.com', 'CB', 2, '3517849568'),
	  (26451256, 'Carlos velez', 55, 'Av Uruguay 875', 'email@testing.com', 'CB', 4, '3514785965'),
	  (35214523, 'Gustavo Alberti', 55, 'San Martin 3332', 'email@testing.com', 'CB', 2, '3514785496'),
	  (33415265, 'Luis beilinson', 55, 'Buenos aires 2313', 'email@testing.com', 'CB', 4, '3514785245'),
	  (31256485, 'Juan Pastorius', 55, 'Av Brasil 4424', 'email@testing.com', 'CB', 2, '3514785496'),
	  (28456987, 'Carlos Tirao', 55, 'Arturo M bas 112', 'email@testing.com', 'CB', 2, '3514785496'),
	  (26555222, 'Martin Dimaria', 55, 'Buenos aires 746', 'email@testing.com', 'CB', 1, '3512457895'),
	  (28451965, 'Sergio Davalle', 55, '9 de julio 990', 'email@testing.com', 'CB', 4, '3516524895'),
	  (32528978, 'Roberto Lujan', 55, 'Rivadavia 1100', 'email@testing.com', 'CB', 4, '3516487598')
go

insert into vehiculos (nro_chasis, id_marca, id_modelo, id_version, id_color, id_tipo_vehiculo, precio, año_fabricacion, id_sucursal, nro_patente)
values(1234, 1, 4, 1, 1, 1, 250000.000, 2018, 1, NULL),
	  (1235, 3, 5, 1, 2, 1, 276000.000, 2017, 2, NULL),
	  (1236, 3, 20, 1, 3, 1, 500000.000, 2018, 3, NULL),
	  (1237, 12, 2, 1, 3, 1, 550000.000, 2018, 3, NULL)
go

insert into planes (id_plan, nom_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, dueño_plan)
values(303455, 'Plan Ahorro', 'Plan Ahorro', 36, '5ta cuota', '36cuotas s/interes', 'CON'), 
	  (303456, 'Plan Nacional Chevrolet','Plan Nacional Chevrolet', 60, '5ta cuota', '84 cuotas 0% interes', 'GOB'),
	  (303457, 'Plan 100% financiado', 'Plan 100% financiado', 80, '10ma cuota','84 cuotas 0% interes', 'GOB'),
	  (303458, 'Plan 70/30 cuota reducida', 'Plan 70/30 cuota reducida', 90, '3ra cuota', '84 cuotas 0% interes', 'GOB')
go

insert into adquiridos(id_plan, dni_cliente, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis, sucursal_suscripcion, fecha_compra_plan)
values(303455, 30245785, 'N', 'N', null, null, null, 1, '05-02-2016'),
	  (303456, 29485621, 'N', 'N', null, null, null, 3, '05-05-2015'),
	  (303458, 28451987, 'N', 'N', null, null, null, 2, '05-02-2017'),
	  (303455, 26451256, 'N', 'N', null, null, null, 1, '02-13-2015'),
	  (303456, 35214523, 'N', 'N', null, null, null, 1, '03-28-2015'),
	  (303457, 33415265, 'N', 'N', null, null, null, 1, '03-28-2016'),
	  (303457, 31256485, 'N', 'N', null, null, null, 1, '05-15-2017'),
	  (303458, 28456987, 'N', 'N', null, null, null, 1, '07-20-2018'),
	  (303456, 26555222, 'N', 'N', null, null, null, 1, '03-12-2016'),
	  (303458, 28451965, 'N', 'N', null, null, null, 1, '05-05-2017'),
	  (303457, 32528978, 'N', 'N', null, null, null, 1, '05-05-2018')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 32528978,  303457, 5000.00, '08-11-2015', 'S'),
	  (111, 32528978,  303457, 5000.00, '02-12-2015', 'S'),
	  (112, 32528978,  303457, 5000.00, '02-01-2016', 'S'),
	  (113, 32528978,  303457, 5000.00, '02-02-2016', 'S'),
	  (114, 32528978,  303457, 5000.00, '02-03-2016', 'S'),
	  (115, 32528978,  303457, 5000.00, '02-04-2016', 'S'),
	  (116, 32528978,  303457, 5000.00, '02-05-2016', 'S'),
	  (117, 32528978,  303457, 5000.00, '02-06-2016', 'S'),
	  (118, 32528978,  303457, 5000.00, '02-07-2016', 'S'),
	  (119, 32528978,  303457, 5000.00, '02-08-2016', 'S'),
	  (120, 32528978,  303457, 5000.00, '02-09-2016', 'S'),
	  (121, 32528978,  303457, 5000.00, '02-12-2016', 'S'),
	  (122, 32528978,  303457, 5000.00, '02-01-2017', 'S'),
	  (123, 32528978,  303457, 5000.00, '02-02-2017', 'S'),
	  (124, 32528978,  303457, 5000.00, '02-03-2017', 'S'),
	  (125, 32528978,  303457, 5000.00, '02-04-2017', 'S'),
	  (126, 32528978,  303457, 5000.00, '02-05-2017', 'S'),
	  (127, 32528978,  303457, 5000.00, '02-06-2017', 'S'),
	  (128, 32528978,  303457, 5000.00, '02-07-2017', 'S'),
	  (129, 32528978,  303457, 5000.00, '02-08-2017', 'S'),
	  (130, 32528978,  303457, 5000.00, '02-09-2017', 'S'),
	  (131, 32528978,  303457, 5000.00, '02-12-2016', 'S'),
	  (132, 32528978,  303457, 5000.00, '02-01-2017', 'S'),
	  (133, 32528978,  303457, 5000.00, '02-02-2017', 'S'),
	  (134, 32528978,  303457, 5000.00, '02-03-2017', 'S'),
	  (135, 32528978,  303457, 5000.00, '02-04-2017', 'S'),
	  (136, 32528978,  303457, 5000.00, '02-05-2017', 'S'),
	  (137, 32528978,  303457, 5000.00, '02-06-2017', 'S'),
	  (138, 32528978,  303457, 5000.00, '02-07-2017', 'S'),
	  (139, 32528978,  303457, 5000.00, '02-08-2017', 'S'),
	  (140, 32528978,  303457, 5000.00, '02-09-2017', 'S'),
	  (141, 32528978,  303457, 5000.00, '02-10-2017', 'S'),
	  (142, 32528978,  303457, 5000.00, '02-11-2017', 'S'),
	  (143, 32528978,  303457, 5000.00, '02-12-2017', 'S'),
	  (144, 32528978,  303457, 5000.00, '02-01-2018', 'S'),
	  (145, 32528978,  303457, 5000.00, '02-02-2018', 'S'),
	  (146, 32528978,  303457, 5000.00, '02-03-2018', 'N'),
	  (147, 32528978,  303457, 5000.00, '02-04-2018', 'N'),
	  (148, 32528978,  303457, 5000.00, '02-05-2018', 'N'),
	  (149, 32528978,  303457, 5000.00, '02-06-2018', 'N'),
	  (150, 32528978,  303457, 5000.00, '02-07-2018', 'N'),
	  (151, 32528978,  303457, 5000.00, '02-08-2018', 'N'),
	  (152, 32528978,  303457, 5000.00, '02-09-2018', 'N'),
	  (153, 32528978,  303457, 5000.00, '02-10-2018', 'N'),
	  (154, 32528978,  303457, 5000.00, '02-11-2018', 'N'),
	  (155, 32528978,  303457, 5000.00, '02-12-2018', 'N'),
	  (156, 32528978,  303457, 5000.00, '02-01-2019', 'N'),
	  (157, 32528978,  303457, 5000.00, '02-02-2019', 'N'),
	  (158, 32528978,  303457, 5000.00, '02-03-2019', 'N'),
	  (159, 32528978,  303457, 5000.00, '02-04-2019', 'N'),
	  (160, 32528978,  303457, 5000.00, '02-05-2019', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 28451965,  303458, 5000.00, '08-11-2015', 'S'),
	  (111, 28451965,  303458, 5000.00, '02-12-2015', 'S'),
	  (112, 28451965,  303458, 5000.00, '02-01-2016', 'S'),
	  (113, 28451965,  303458, 5000.00, '02-02-2016', 'S'),
	  (114, 28451965,  303458, 5000.00, '02-03-2016', 'S'),
	  (115, 28451965,  303458, 5000.00, '02-04-2016', 'S'),
	  (116, 28451965,  303458, 5000.00, '02-05-2016', 'S'),
	  (117, 28451965,  303458, 5000.00, '02-06-2016', 'S'),
	  (118, 28451965,  303458, 5000.00, '02-07-2016', 'S'),
	  (119, 28451965,  303458, 5000.00, '02-08-2016', 'S'),
	  (120, 28451965,  303458, 5000.00, '02-09-2016', 'S'),
	  (121, 28451965,  303458, 5000.00, '02-12-2016', 'S'),
	  (122, 28451965,  303458, 5000.00, '02-01-2017', 'S'),
	  (123, 28451965,  303458, 5000.00, '02-02-2017', 'S'),
	  (124, 28451965,  303458, 5000.00, '02-03-2017', 'S'),
	  (125, 28451965,  303458, 5000.00, '02-04-2017', 'S'),
	  (126, 28451965,  303458, 5000.00, '02-05-2017', 'S'),
	  (127, 28451965,  303458, 5000.00, '02-06-2017', 'S'),
	  (128, 28451965,  303458, 5000.00, '02-07-2017', 'S'),
	  (129, 28451965,  303458, 5000.00, '02-08-2017', 'S'),
	  (130, 28451965,  303458, 5000.00, '02-09-2017', 'S'),
	  (131, 28451965,  303458, 5000.00, '02-12-2016', 'S'),
	  (132, 28451965,  303458, 5000.00, '02-01-2017', 'S'),
	  (133, 28451965,  303458, 5000.00, '02-02-2017', 'S'),
	  (134, 28451965,  303458, 5000.00, '02-03-2017', 'S'),
	  (135, 28451965,  303458, 5000.00, '02-04-2017', 'S'),
	  (136, 28451965,  303458, 5000.00, '02-05-2017', 'S'),
	  (137, 28451965,  303458, 5000.00, '02-06-2017', 'S'),
	  (138, 28451965,  303458, 5000.00, '02-07-2017', 'S'),
	  (139, 28451965,  303458, 5000.00, '02-08-2017', 'S'),
	  (140, 28451965,  303458, 5000.00, '02-09-2017', 'S'),
	  (141, 28451965,  303458, 5000.00, '02-10-2017', 'S'),
	  (142, 28451965,  303458, 5000.00, '02-11-2017', 'S'),
	  (143, 28451965,  303458, 5000.00, '02-12-2017', 'S'),
	  (144, 28451965,  303458, 5000.00, '02-01-2018', 'S'),
	  (145, 28451965,  303458, 5000.00, '02-02-2018', 'S'),
	  (146, 28451965,  303458, 5000.00, '02-03-2018', 'N'),
	  (147, 28451965,  303458, 5000.00, '02-04-2018', 'N'),
	  (148, 28451965,  303458, 5000.00, '02-05-2018', 'N'),
	  (149, 28451965,  303458, 5000.00, '02-06-2018', 'N'),
	  (150, 28451965,  303458, 5000.00, '02-07-2018', 'N'),
	  (151, 28451965,  303458, 5000.00, '02-08-2018', 'N'),
	  (152, 28451965,  303458, 5000.00, '02-09-2018', 'N'),
	  (153, 28451965,  303458, 5000.00, '02-10-2018', 'N'),
	  (154, 28451965,  303458, 5000.00, '02-11-2018', 'N'),
	  (155, 28451965,  303458, 5000.00, '02-12-2018', 'N'),
	  (156, 28451965,  303458, 5000.00, '02-01-2019', 'N'),
	  (157, 28451965,  303458, 5000.00, '02-02-2019', 'N'),
	  (158, 28451965,  303458, 5000.00, '02-03-2019', 'N'),
	  (159, 28451965,  303458, 5000.00, '02-04-2019', 'N'),
	  (160, 28451965,  303458, 5000.00, '02-05-2019', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 26451256,  303455, 5000.00, '08-11-2015', 'S'),
	  (111, 26451256,  303455, 5000.00, '02-12-2015', 'S'),
	  (112, 26451256,  303455, 5000.00, '02-01-2016', 'S'),
	  (113, 26451256,  303455, 5000.00, '02-02-2016', 'S'),
	  (114, 26451256,  303455, 5000.00, '02-03-2016', 'S'),
	  (115, 26451256,  303455, 5000.00, '02-04-2016', 'S'),
	  (116, 26451256,  303455, 5000.00, '02-05-2016', 'S'),
	  (117, 26451256,  303455, 5000.00, '02-06-2016', 'S'),
	  (118, 26451256,  303455, 5000.00, '02-07-2016', 'S'),
	  (119, 26451256,  303455, 5000.00, '02-08-2016', 'S'),
	  (120, 26451256,  303455, 5000.00, '02-09-2016', 'S'),
	  (121, 26451256,  303455, 5000.00, '02-12-2016', 'S'),
	  (122, 26451256,  303455, 5000.00, '02-01-2017', 'S'),
	  (123, 26451256,  303455, 5000.00, '02-02-2017', 'S'),
	  (124, 26451256,  303455, 5000.00, '02-03-2017', 'S'),
	  (125, 26451256,  303455, 5000.00, '02-04-2017', 'S'),
	  (126, 26451256,  303455, 5000.00, '02-05-2017', 'S'),
	  (127, 26451256,  303455, 5000.00, '02-06-2017', 'S'),
	  (128, 26451256,  303455, 5000.00, '02-07-2017', 'S'),
	  (129, 26451256,  303455, 5000.00, '02-08-2017', 'S'),
	  (130, 26451256,  303455, 5000.00, '02-09-2017', 'S'),
	  (131, 26451256,  303455, 5000.00, '02-12-2016', 'S'),
	  (132, 26451256,  303455, 5000.00, '02-01-2017', 'S'),
	  (133, 26451256,  303455, 5000.00, '02-02-2017', 'S'),
	  (134, 26451256,  303455, 5000.00, '02-03-2017', 'S'),
	  (135, 26451256,  303455, 5000.00, '02-04-2017', 'S'),
	  (136, 26451256,  303455, 5000.00, '02-05-2017', 'S'),
	  (137, 26451256,  303455, 5000.00, '02-06-2017', 'S'),
	  (138, 26451256,  303455, 5000.00, '02-07-2017', 'S'),
	  (139, 26451256,  303455, 5000.00, '02-08-2017', 'S'),
	  (140, 26451256,  303455, 5000.00, '02-09-2017', 'S'),
	  (141, 26451256,  303455, 5000.00, '02-10-2017', 'S'),
	  (142, 26451256,  303455, 5000.00, '02-11-2017', 'S'),
	  (143, 26451256,  303455, 5000.00, '02-12-2017', 'S'),
	  (144, 26451256,  303455, 5000.00, '02-01-2018', 'S'),
	  (145, 26451256,  303455, 5000.00, '02-02-2018', 'S'),
	  (146, 26451256,  303455, 5000.00, '02-03-2018', 'N'),
	  (147, 26451256,  303455, 5000.00, '02-04-2018', 'N'),
	  (148, 26451256,  303455, 5000.00, '02-05-2018', 'N'),
	  (149, 26451256,  303455, 5000.00, '02-06-2018', 'N'),
	  (150, 26451256,  303455, 5000.00, '02-07-2018', 'N'),
	  (151, 26451256,  303455, 5000.00, '02-08-2018', 'N'),
	  (152, 26451256,  303455, 5000.00, '02-09-2018', 'N'),
	  (153, 26451256,  303455, 5000.00, '02-10-2018', 'N'),
	  (154, 26451256,  303455, 5000.00, '02-11-2018', 'N'),
	  (155, 26451256,  303455, 5000.00, '02-12-2018', 'N'),
	  (156, 26451256,  303455, 5000.00, '02-01-2019', 'N'),
	  (157, 26451256,  303455, 5000.00, '02-02-2019', 'N'),
	  (158, 26451256,  303455, 5000.00, '02-03-2019', 'N'),
	  (159, 26451256,  303455, 5000.00, '02-04-2019', 'N'),
	  (160, 26451256,  303455, 5000.00, '02-05-2019', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 30245785,  303455, 5000.00, '08-11-2015', 'S'),
	  (111, 30245785,  303455, 5000.00, '02-12-2015', 'S'),
	  (112, 30245785,  303455, 5000.00, '02-01-2016', 'S'),
	  (113, 30245785,  303455, 5000.00, '02-02-2016', 'S'),
	  (114, 30245785,  303455, 5000.00, '02-03-2016', 'S'),
	  (115, 30245785,  303455, 5000.00, '02-04-2016', 'S'),
	  (116, 30245785,  303455, 5000.00, '02-05-2016', 'S'),
	  (117, 30245785,  303455, 5000.00, '02-06-2016', 'S'),
	  (118, 30245785,  303455, 5000.00, '02-07-2016', 'S'),
	  (119, 30245785,  303455, 5000.00, '02-08-2016', 'S'),
	  (120, 30245785,  303455, 5000.00, '02-09-2016', 'S'),
	  (121, 30245785,  303455, 5000.00, '02-12-2016', 'S'),
	  (122, 30245785,  303455, 5000.00, '02-01-2017', 'S'),
	  (123, 30245785,  303455, 5000.00, '02-02-2017', 'S'),
	  (124, 30245785,  303455, 5000.00, '02-03-2017', 'S'),
	  (125, 30245785,  303455, 5000.00, '02-04-2017', 'S'),
	  (126, 30245785,  303455, 5000.00, '02-05-2017', 'S'),
	  (127, 30245785,  303455, 5000.00, '02-06-2017', 'S'),
	  (128, 30245785,  303455, 5000.00, '02-07-2017', 'S'),
	  (129, 30245785,  303455, 5000.00, '02-08-2017', 'S'),
	  (130, 30245785,  303455, 5000.00, '02-09-2017', 'S'),
	  (131, 30245785,  303455, 5000.00, '02-12-2016', 'S'),
	  (132, 30245785,  303455, 5000.00, '02-01-2017', 'S'),
	  (133, 30245785,  303455, 5000.00, '02-02-2017', 'S'),
	  (134, 30245785,  303455, 5000.00, '02-03-2017', 'S'),
	  (135, 30245785,  303455, 5000.00, '02-04-2017', 'S'),
	  (136, 30245785,  303455, 5000.00, '02-05-2017', 'S'),
	  (137, 30245785,  303455, 5000.00, '02-06-2017', 'S'),
	  (138, 30245785,  303455, 5000.00, '02-07-2017', 'S'),
	  (139, 30245785,  303455, 5000.00, '02-08-2017', 'S'),
	  (140, 30245785,  303455, 5000.00, '02-09-2017', 'S'),
	  (141, 30245785,  303455, 5000.00, '02-10-2017', 'S'),
	  (142, 30245785,  303455, 5000.00, '02-11-2017', 'S'),
	  (143, 30245785,  303455, 5000.00, '02-12-2017', 'S'),
	  (144, 30245785,  303455, 5000.00, '02-01-2018', 'S'),
	  (145, 30245785,  303455, 5000.00, '02-02-2018', 'S'),
	  (146, 30245785,  303455, 5000.00, '02-03-2018', 'N'),
	  (147, 30245785,  303455, 5000.00, '02-04-2018', 'N'),
	  (148, 30245785,  303455, 5000.00, '02-05-2018', 'N'),
	  (149, 30245785,  303455, 5000.00, '02-06-2018', 'N'),
	  (150, 30245785,  303455, 5000.00, '02-07-2018', 'N'),
	  (151, 30245785,  303455, 5000.00, '02-08-2018', 'N'),
	  (152, 30245785,  303455, 5000.00, '02-09-2018', 'N'),
	  (153, 30245785,  303455, 5000.00, '02-10-2018', 'N'),
	  (154, 30245785,  303455, 5000.00, '02-11-2018', 'N'),
	  (155, 30245785,  303455, 5000.00, '02-12-2018', 'N'),
	  (156, 30245785,  303455, 5000.00, '02-01-2019', 'N'),
	  (157, 30245785,  303455, 5000.00, '02-02-2019', 'N'),
	  (158, 30245785,  303455, 5000.00, '02-03-2019', 'N'),
	  (159, 30245785,  303455, 5000.00, '02-04-2019', 'N'),
	  (160, 30245785,  303455, 5000.00, '02-05-2019', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 26555222,  303456, 5000.00, '08-11-2015', 'S'),
	  (111, 26555222,  303456, 5000.00, '02-12-2015', 'S'),
	  (112, 26555222,  303456, 5000.00, '02-01-2016', 'S'),
	  (113, 26555222,  303456, 5000.00, '02-02-2016', 'S'),
	  (114, 26555222,  303456, 5000.00, '02-03-2016', 'S'),
	  (115, 26555222,  303456, 5000.00, '02-04-2016', 'S'),
	  (116, 26555222,  303456, 5000.00, '02-05-2016', 'S'),
	  (117, 26555222,  303456, 5000.00, '02-06-2016', 'S'),
	  (118, 26555222,  303456, 5000.00, '02-07-2016', 'S'),
	  (119, 26555222,  303456, 5000.00, '02-08-2016', 'S'),
	  (120, 26555222,  303456, 5000.00, '02-09-2016', 'S'),
	  (121, 26555222,  303456, 5000.00, '02-12-2016', 'S'),
	  (122, 26555222,  303456, 5000.00, '02-01-2017', 'S'),
	  (123, 26555222,  303456, 5000.00, '02-02-2017', 'S'),
	  (124, 26555222,  303456, 5000.00, '02-03-2017', 'S'),
	  (125, 26555222,  303456, 5000.00, '02-04-2017', 'S'),
	  (126, 26555222,  303456, 5000.00, '02-05-2017', 'S'),
	  (127, 26555222,  303456, 5000.00, '02-06-2017', 'S'),
	  (128, 26555222,  303456, 5000.00, '02-07-2017', 'S'),
	  (129, 26555222,  303456, 5000.00, '02-08-2017', 'S'),
	  (130, 26555222,  303456, 5000.00, '02-09-2017', 'S'),
	  (131, 26555222,  303456, 5000.00, '02-12-2016', 'S'),
	  (132, 26555222,  303456, 5000.00, '02-01-2017', 'S'),
	  (133, 26555222,  303456, 5000.00, '02-02-2017', 'S'),
	  (134, 26555222,  303456, 5000.00, '02-03-2017', 'S'),
	  (135, 26555222,  303456, 5000.00, '02-04-2017', 'S'),
	  (136, 26555222,  303456, 5000.00, '02-05-2017', 'S'),
	  (137, 26555222,  303456, 5000.00, '02-06-2017', 'S'),
	  (138, 26555222,  303456, 5000.00, '02-07-2017', 'S'),
	  (139, 26555222,  303456, 5000.00, '02-08-2017', 'S'),
	  (140, 26555222,  303456, 5000.00, '02-09-2017', 'S'),
	  (141, 26555222,  303456, 5000.00, '02-10-2017', 'S'),
	  (142, 26555222,  303456, 5000.00, '02-11-2017', 'S'),
	  (143, 26555222,  303456, 5000.00, '02-12-2017', 'S'),
	  (144, 26555222,  303456, 5000.00, '02-01-2018', 'S'),
	  (145, 26555222,  303456, 5000.00, '02-02-2018', 'S'),
	  (146, 26555222,  303456, 5000.00, '02-03-2018', 'N'),
	  (147, 26555222,  303456, 5000.00, '02-04-2018', 'N'),
	  (148, 26555222,  303456, 5000.00, '02-05-2018', 'N'),
	  (149, 26555222,  303456, 5000.00, '02-06-2018', 'N'),
	  (150, 26555222,  303456, 5000.00, '02-07-2018', 'N'),
	  (151, 26555222,  303456, 5000.00, '02-08-2018', 'N'),
	  (152, 26555222,  303456, 5000.00, '02-09-2018', 'N'),
	  (153, 26555222,  303456, 5000.00, '02-10-2018', 'N'),
	  (154, 26555222,  303456, 5000.00, '02-11-2018', 'N'),
	  (155, 26555222,  303456, 5000.00, '02-12-2018', 'N'),
	  (156, 26555222,  303456, 5000.00, '02-01-2019', 'N'),
	  (157, 26555222,  303456, 5000.00, '02-02-2019', 'N'),
	  (158, 26555222,  303456, 5000.00, '02-03-2019', 'N'),
	  (159, 26555222,  303456, 5000.00, '02-04-2019', 'N'),
	  (160, 26555222,  303456, 5000.00, '02-05-2019', 'N')
go


insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 28456987, 303458, 5000.00, '08-11-2015', 'S'),
	  (111, 28456987, 303458, 5000.00, '02-12-2015', 'S'),
	  (112, 28456987, 303458, 5000.00, '02-01-2016', 'S'),
	  (113, 28456987, 303458, 5000.00, '02-02-2016', 'S'),
	  (114, 28456987, 303458, 5000.00, '02-03-2016', 'S'),
	  (115, 28456987, 303458, 5000.00, '02-04-2016', 'S'),
	  (116, 28456987, 303458, 5000.00, '02-05-2016', 'S'),
	  (117, 28456987, 303458, 5000.00, '02-06-2016', 'S'),
	  (118, 28456987, 303458, 5000.00, '02-07-2016', 'S'),
	  (119, 28456987, 303458, 5000.00, '02-08-2016', 'S'),
	  (120, 28456987, 303458, 5000.00, '02-09-2016', 'S'),
	  (121, 28456987, 303458, 5000.00, '02-12-2016', 'S'),
	  (122, 28456987, 303458, 5000.00, '02-01-2017', 'S'),
	  (123, 28456987, 303458, 5000.00, '02-02-2017', 'S'),
	  (124, 28456987, 303458, 5000.00, '02-03-2017', 'S'),
	  (125, 28456987, 303458, 5000.00, '02-04-2017', 'S'),
	  (126, 28456987, 303458, 5000.00, '02-05-2017', 'S'),
	  (127, 28456987, 303458, 5000.00, '02-06-2017', 'S'),
	  (128, 28456987, 303458, 5000.00, '02-07-2017', 'S'),
	  (129, 28456987, 303458, 5000.00, '02-08-2017', 'S'),
	  (130, 28456987, 303458, 5000.00, '02-09-2017', 'S'),
	  (131, 28456987, 303458, 5000.00, '02-12-2016', 'S'),
	  (132, 28456987, 303458, 5000.00, '02-01-2017', 'S'),
	  (133, 28456987, 303458, 5000.00, '02-02-2017', 'S'),
	  (134, 28456987, 303458, 5000.00, '02-03-2017', 'S'),
	  (135, 28456987, 303458, 5000.00, '02-04-2017', 'S'),
	  (136, 28456987, 303458, 5000.00, '02-05-2017', 'S'),
	  (137, 28456987, 303458, 5000.00, '02-06-2017', 'S'),
	  (138, 28456987, 303458, 5000.00, '02-07-2017', 'S'),
	  (139, 28456987, 303458, 5000.00, '02-08-2017', 'S'),
	  (140, 28456987, 303458, 5000.00, '02-09-2017', 'S'),
	  (141, 28456987, 303458, 5000.00, '02-10-2017', 'S'),
	  (142, 28456987, 303458, 5000.00, '02-11-2017', 'S'),
	  (143, 28456987, 303458, 5000.00, '02-12-2017', 'S'),
	  (144, 28456987, 303458, 5000.00, '02-01-2018', 'S'),
	  (145, 28456987, 303458, 5000.00, '02-02-2018', 'S'),
	  (146, 28456987, 303458, 5000.00, '02-03-2018', 'N'),
	  (147, 28456987, 303458, 5000.00, '02-04-2018', 'N'),
	  (148, 28456987, 303458, 5000.00, '02-05-2018', 'N'),
	  (149, 28456987, 303458, 5000.00, '02-06-2018', 'N'),
	  (150, 28456987, 303458, 5000.00, '02-07-2018', 'N'),
	  (151, 28456987, 303458, 5000.00, '02-08-2018', 'N'),
	  (152, 28456987, 303458, 5000.00, '02-09-2018', 'N'),
	  (153, 28456987, 303458, 5000.00, '02-10-2018', 'N'),
	  (154, 28456987, 303458, 5000.00, '02-11-2018', 'N'),
	  (155, 28456987, 303458, 5000.00, '02-12-2018', 'N'),
	  (156, 28456987, 303458, 5000.00, '02-01-2019', 'N'),
	  (157, 28456987, 303458, 5000.00, '02-02-2019', 'N'),
	  (158, 28456987, 303458, 5000.00, '02-03-2019', 'N'),
	  (159, 28456987, 303458, 5000.00, '02-04-2019', 'N'),
	  (160, 28456987, 303458, 5000.00, '02-05-2019', 'N')
go


insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 31256485, 303457, 5000.00, '06-15-2017', 'S'),
	  (111, 31256485, 303457, 5000.00, '07-15-2017', 'S'),
	  (112, 31256485, 303457, 5000.00, '08-15-2017', 'S'),
	  (113, 31256485, 303457, 5000.00, '09-15-2017', 'S'),
	  (114, 31256485, 303457, 5000.00, '10-15-2017', 'S'),
	  (115, 31256485, 303457, 5000.00, '11-15-2017', 'S'),
	  (116, 31256485, 303457, 5000.00, '12-15-2017', 'S'),
	  (117, 31256485, 303457, 5000.00, '01-15-2017', 'S'),
	  (118, 31256485, 303457, 5000.00, '02-15-2017', 'S'),
	  (119, 31256485, 303457, 5000.00, '03-15-2018', 'S'),
	  (120, 31256485, 303457, 5000.00, '04-15-2018', 'S'),
	  (121, 31256485, 303457, 5000.00, '05-15-2018', 'S'),
	  (122, 31256485, 303457, 5000.00, '06-15-2018', 'S'),
	  (123, 31256485, 303457, 5000.00, '07-15-2018', 'S'),
	  (124, 31256485, 303457, 5000.00, '08-15-2018', 'S'),
	  (125, 31256485, 303457, 5000.00, '09-15-2018', 'S'),
	  (126, 31256485, 303457, 5000.00, '10-15-2018', 'S'),
	  (127, 31256485, 303457, 5000.00, '11-15-2018', 'S'),
	  (128, 31256485, 303457, 5000.00, '12-15-2018', 'S'),
	  (129, 31256485, 303457, 5000.00, '01-15-2018', 'S'),
	  (130, 31256485, 303457, 5000.00, '02-15-2018', 'S'),
	  (131, 31256485, 303457, 5000.00, '03-15-2019', 'S'),
	  (132, 31256485, 303457, 5000.00, '04-15-2019', 'S'),
	  (133, 31256485, 303457, 5000.00, '05-15-2019', 'S'),
	  (134, 31256485, 303457, 5000.00, '06-15-2019', 'S'),
	  (135, 31256485, 303457, 5000.00, '07-15-2019', 'S'),
	  (136, 31256485, 303457, 5000.00, '08-15-2019', 'S'),
	  (137, 31256485, 303457, 5000.00, '09-15-2019', 'S'),
	  (138, 31256485, 303457, 5000.00, '10-15-2019', 'S'),
	  (139, 31256485, 303457, 5000.00, '11-15-2019', 'S'),
	  (140, 31256485, 303457, 5000.00, '12-15-2019', 'S'),
	  (141, 31256485, 303457, 5000.00, '01-15-2019', 'S'),
	  (142, 31256485, 303457, 5000.00, '02-15-2019', 'N'),
	  (143, 31256485, 303457, 5000.00, '03-15-2020', 'N'),
	  (144, 31256485, 303457, 5000.00, '04-15-2020', 'N'),
	  (145, 31256485, 303457, 5000.00, '05-15-2020', 'N'),
	  (146, 31256485, 303457, 5000.00, '06-15-2020', 'N'),
	  (147, 31256485, 303457, 5000.00, '07-15-2020', 'N'),
	  (148, 31256485, 303457, 5000.00, '08-15-2020', 'N'),
	  (149, 31256485, 303457, 5000.00, '09-15-2020', 'N'),
	  (150, 31256485, 303457, 5000.00, '10-15-2020', 'N'),
	  (151, 31256485, 303457, 5000.00, '11-15-2020', 'N'),
	  (152, 31256485, 303457, 5000.00, '12-15-2020', 'N'),
	  (153, 31256485, 303457, 5000.00, '01-15-2021', 'N'),
	  (154, 31256485, 303457, 5000.00, '02-15-2021', 'N'),
	  (155, 31256485, 303457, 5000.00, '03-15-2021', 'N'),
	  (157, 31256485, 303457, 5000.00, '04-15-2021', 'N'),
	  (158, 31256485, 303457, 5000.00, '05-15-2021', 'N'),
	  (159, 31256485, 303457, 5000.00, '06-15-2021', 'N'),
	  (160, 31256485, 303457, 5000.00, '07-15-2021', 'N'),
	  (156, 31256485, 303457, 5000.00, '08-15-2021', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 33415265, 303457, 5000.00, '04-28-2016', 'S'),
	  (111, 33415265, 303457, 5000.00, '05-28-2016', 'S'),
	  (112, 33415265, 303457, 5000.00, '06-28-2016', 'S'),
	  (113, 33415265, 303457, 5000.00, '07-28-2016', 'S'),
	  (114, 33415265, 303457, 5000.00, '08-28-2016', 'S'),
	  (115, 33415265, 303457, 5000.00, '09-28-2016', 'S'),
	  (116, 33415265, 303457, 5000.00, '10-28-2016', 'S'),
	  (117, 33415265, 303457, 5000.00, '11-28-2016', 'S'),
	  (118, 33415265, 303457, 5000.00, '12-28-2016', 'S'),
	  (119, 33415265, 303457, 5000.00, '01-28-2017', 'S'),
	  (120, 33415265, 303457, 5000.00, '02-28-2017', 'S'),
	  (121, 33415265, 303457, 5000.00, '03-28-2017', 'S'),
	  (122, 33415265, 303457, 5000.00, '04-28-2017', 'S'),
	  (123, 33415265, 303457, 5000.00, '05-28-2017', 'S'),
	  (124, 33415265, 303457, 5000.00, '06-28-2017', 'S'),
	  (125, 33415265, 303457, 5000.00, '07-28-2017', 'S'),
	  (126, 33415265, 303457, 5000.00, '08-28-2017', 'S'),
	  (127, 33415265, 303457, 5000.00, '09-28-2017', 'S'),
	  (128, 33415265, 303457, 5000.00, '10-28-2017', 'S'),
	  (129, 33415265, 303457, 5000.00, '11-28-2017', 'S'),
	  (130, 33415265, 303457, 5000.00, '12-28-2017', 'S'),
	  (131, 33415265, 303457, 5000.00, '01-28-2018', 'S'),
	  (132, 33415265, 303457, 5000.00, '02-28-2018', 'S'),
	  (133, 33415265, 303457, 5000.00, '03-28-2018', 'S'),
	  (134, 33415265, 303457, 5000.00, '04-28-2018', 'S'),
	  (135, 33415265, 303457, 5000.00, '05-28-2018', 'S'),
	  (136, 33415265, 303457, 5000.00, '06-28-2018', 'S'),
	  (137, 33415265, 303457, 5000.00, '07-28-2018', 'S'),
	  (138, 33415265, 303457, 5000.00, '08-28-2018', 'S'),
	  (139, 33415265, 303457, 5000.00, '09-28-2018', 'S'),
	  (140, 33415265, 303457, 5000.00, '10-28-2018', 'S'),
	  (141, 33415265, 303457, 5000.00, '11-28-2018', 'S'),
	  (142, 33415265, 303457, 5000.00, '12-28-2018', 'N'),
	  (143, 33415265, 303457, 5000.00, '01-28-2019', 'N'),
	  (144, 33415265, 303457, 5000.00, '02-28-2019', 'N'),
	  (145, 33415265, 303457, 5000.00, '03-28-2019', 'N'),
	  (146, 33415265, 303457, 5000.00, '04-28-2019', 'N'),
	  (147, 33415265, 303457, 5000.00, '05-28-2019', 'N'),
	  (148, 33415265, 303457, 5000.00, '06-28-2019', 'N'),
	  (149, 33415265, 303457, 5000.00, '07-28-2019', 'N'),
	  (150, 33415265, 303457, 5000.00, '08-28-2019', 'N'),
	  (151, 33415265, 303457, 5000.00, '09-28-2019', 'N'),
	  (152, 33415265, 303457, 5000.00, '10-28-2019', 'N'),
	  (153, 33415265, 303457, 5000.00, '11-28-2019', 'N'),
	  (154, 33415265, 303457, 5000.00, '12-28-2019', 'N'),
	  (155, 33415265, 303457, 5000.00, '01-28-2020', 'N'),
	  (157, 33415265, 303457, 5000.00, '02-28-2020', 'N'),
	  (158, 33415265, 303457, 5000.00, '03-28-2020', 'N'),
	  (159, 33415265, 303457, 5000.00, '04-28-2020', 'N'),
	  (160, 33415265, 303457, 5000.00, '05-28-2020', 'N'),
	  (156, 33415265, 303457, 5000.00, '06-28-2020', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 35214523, 303456, 5000.00, '04-28-2015', 'S'),
	  (111, 35214523, 303456, 5000.00, '05-28-2015', 'S'),
	  (112, 35214523, 303456, 5000.00, '06-28-2015', 'S'),
	  (113, 35214523, 303456, 5000.00, '07-28-2015', 'S'),
	  (114, 35214523, 303456, 5000.00, '08-28-2015', 'S'),
	  (115, 35214523, 303456, 5000.00, '09-28-2015', 'S'),
	  (116, 35214523, 303456, 5000.00, '10-28-2015', 'S'),
	  (117, 35214523, 303456, 5000.00, '11-28-2015', 'S'),
	  (118, 35214523, 303456, 5000.00, '12-28-2015', 'S'),
	  (119, 35214523, 303456, 5000.00, '01-28-2016', 'S'),
	  (120, 35214523, 303456, 5000.00, '02-28-2016', 'S'),
	  (121, 35214523, 303456, 5000.00, '03-28-2016', 'S'),
	  (122, 35214523, 303456, 5000.00, '04-28-2016', 'S'),
	  (123, 35214523, 303456, 5000.00, '05-28-2016', 'S'),
	  (124, 35214523, 303456, 5000.00, '06-28-2016', 'S'),
	  (125, 35214523, 303456, 5000.00, '07-28-2016', 'S'),
	  (126, 35214523, 303456, 5000.00, '08-28-2016', 'S'),
	  (127, 35214523, 303456, 5000.00, '09-28-2016', 'S'),
	  (128, 35214523, 303456, 5000.00, '10-28-2016', 'S'),
	  (129, 35214523, 303456, 5000.00, '11-28-2016', 'S'),
	  (130, 35214523, 303456, 5000.00, '12-28-2016', 'S'),
	  (131, 35214523, 303456, 5000.00, '01-28-2017', 'S'),
	  (132, 35214523, 303456, 5000.00, '02-28-2017', 'S'),
	  (133, 35214523, 303456, 5000.00, '03-28-2017', 'S'),
	  (134, 35214523, 303456, 5000.00, '04-28-2017', 'N'),
	  (135, 35214523, 303456, 5000.00, '05-28-2017', 'N'),
	  (136, 35214523, 303456, 5000.00, '06-28-2017', 'N'),
	  (137, 35214523, 303456, 5000.00, '07-28-2017', 'N'),
	  (138, 35214523, 303456, 5000.00, '08-28-2017', 'N'),
	  (139, 35214523, 303456, 5000.00, '09-28-2017', 'N'),
	  (140, 35214523, 303456, 5000.00, '10-28-2017', 'N'),
	  (141, 35214523, 303456, 5000.00, '11-28-2017', 'N'),
	  (142, 35214523, 303456, 5000.00, '12-28-2017', 'N'),
	  (143, 35214523, 303456, 5000.00, '01-28-2018', 'N'),
	  (144, 35214523, 303456, 5000.00, '02-28-2018', 'N'),
	  (145, 35214523, 303456, 5000.00, '03-28-2018', 'N'),
	  (146, 35214523, 303456, 5000.00, '04-28-2018', 'N'),
	  (147, 35214523, 303456, 5000.00, '05-28-2018', 'N'),
	  (148, 35214523, 303456, 5000.00, '06-28-2018', 'N'),
	  (149, 35214523, 303456, 5000.00, '07-28-2018', 'N'),
	  (150, 35214523, 303456, 5000.00, '08-28-2018', 'N'),
	  (151, 35214523, 303456, 5000.00, '09-28-2018', 'N'),
	  (152, 35214523, 303456, 5000.00, '10-28-2018', 'N'),
	  (153, 35214523, 303456, 5000.00, '11-28-2018', 'N'),
	  (154, 35214523, 303456, 5000.00, '12-28-2018', 'N'),
	  (155, 35214523, 303456, 5000.00, '01-28-2019', 'N'),
	  (157, 35214523, 303456, 5000.00, '02-28-2019', 'N'),
	  (158, 35214523, 303456, 5000.00, '03-28-2019', 'N'),
	  (159, 35214523, 303456, 5000.00, '04-28-2019', 'N'),
	  (160, 35214523, 303456, 5000.00, '05-28-2019', 'N'),
	  (156, 35214523, 303456, 5000.00, '06-28-2019', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 28451987, 303458, 5000.00, '06-02-2017', 'S'),
	  (111, 28451987, 303458, 5000.00, '07-02-2017', 'S'),
	  (112, 28451987, 303458, 5000.00, '08-02-2017', 'S'),
	  (113, 28451987, 303458, 5000.00, '09-02-2017', 'S'),
	  (114, 28451987, 303458, 5000.00, '10-02-2017', 'S'),
	  (115, 28451987, 303458, 5000.00, '11-02-2017', 'S'),
	  (116, 28451987, 303458, 5000.00, '12-02-2017', 'S'),
	  (117, 28451987, 303458, 5000.00, '01-02-2018', 'S'),
	  (118, 28451987, 303458, 5000.00, '02-02-2018', 'S'),
	  (119, 28451987, 303458, 5000.00, '03-02-2018', 'S'),
	  (120, 28451987, 303458, 5000.00, '04-02-2018', 'S'),
	  (121, 28451987, 303458, 5000.00, '05-02-2018', 'S'),
	  (122, 28451987, 303458, 5000.00, '06-02-2018', 'S'),
	  (123, 28451987, 303458, 5000.00, '07-02-2018', 'S'),
	  (124, 28451987, 303458, 5000.00, '08-02-2018', 'S'),
	  (125, 28451987, 303458, 5000.00, '09-02-2018', 'S'),
	  (126, 28451987, 303458, 5000.00, '10-02-2018', 'S'),
	  (127, 28451987, 303458, 5000.00, '11-02-2018', 'S'),
	  (128, 28451987, 303458, 5000.00, '12-02-2018', 'S'),
	  (129, 28451987, 303458, 5000.00, '01-02-2019', 'S'),
	  (130, 28451987, 303458, 5000.00, '02-02-2019', 'S'),
	  (131, 28451987, 303458, 5000.00, '03-02-2019', 'S'),
	  (132, 28451987, 303458, 5000.00, '04-02-2019', 'S'),
	  (133, 28451987, 303458, 5000.00, '05-02-2019', 'S'),
	  (134, 28451987, 303458, 5000.00, '06-02-2019', 'N'),
	  (135, 28451987, 303458, 5000.00, '07-02-2019', 'N'),
	  (136, 28451987, 303458, 5000.00, '08-02-2019', 'N'),
	  (137, 28451987, 303458, 5000.00, '09-02-2019', 'N'),
	  (138, 28451987, 303458, 5000.00, '10-02-2019', 'N'),
	  (139, 28451987, 303458, 5000.00, '11-02-2019', 'N'),
	  (140, 28451987, 303458, 5000.00, '12-02-2019', 'N'),
	  (141, 28451987, 303458, 5000.00, '01-02-2020', 'N'),
	  (142, 28451987, 303458, 5000.00, '02-02-2020', 'N'),
	  (143, 28451987, 303458, 5000.00, '03-02-2020', 'N'),
	  (144, 28451987, 303458, 5000.00, '04-02-2020', 'N'),
	  (145, 28451987, 303458, 5000.00, '05-02-2020', 'N'),
	  (146, 28451987, 303458, 5000.00, '06-02-2020', 'N'),
	  (147, 28451987, 303458, 5000.00, '07-02-2020', 'N'),
	  (148, 28451987, 303458, 5000.00, '08-02-2020', 'N'),
	  (149, 28451987, 303458, 5000.00, '09-02-2020', 'N'),
	  (150, 28451987, 303458, 5000.00, '10-02-2020', 'N'),
	  (151, 28451987, 303458, 5000.00, '11-02-2020', 'N'),
	  (152, 28451987, 303458, 5000.00, '12-02-2020', 'N'),
	  (153, 28451987, 303458, 5000.00, '01-02-2021', 'N'),
	  (154, 28451987, 303458, 5000.00, '02-02-2021', 'N'),
	  (155, 28451987, 303458, 5000.00, '03-02-2021', 'N'),
	  (157, 28451987, 303458, 5000.00, '04-02-2021', 'N'),
	  (158, 28451987, 303458, 5000.00, '05-02-2021', 'N'),
	  (159, 28451987, 303458, 5000.00, '06-02-2021', 'N'),
	  (160, 28451987, 303458, 5000.00, '07-02-2021', 'N'),
	  (156, 28451987, 303458, 5000.00, '08-02-2021', 'N')
go

insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
values(110, 29485621, 303456, 5000.00, '06-02-2015', 'S'),
	  (111, 29485621, 303456, 5000.00, '07-02-2015', 'S'),
	  (112, 29485621, 303456, 5000.00, '08-02-2015', 'S'),
	  (113, 29485621, 303456, 5000.00, '09-02-2015', 'S'),
	  (114, 29485621, 303456, 5000.00, '10-02-2015', 'S'),
	  (115, 29485621, 303456, 5000.00, '11-02-2015', 'S'),
	  (116, 29485621, 303456, 5000.00, '12-02-2015', 'S'),
	  (117, 29485621, 303456, 5000.00, '01-02-2016', 'S'),
	  (118, 29485621, 303456, 5000.00, '02-02-2016', 'S'),
	  (119, 29485621, 303456, 5000.00, '03-02-2016', 'S'),
	  (120, 29485621, 303456, 5000.00, '04-02-2016', 'S'),
	  (121, 29485621, 303456, 5000.00, '05-02-2016', 'S'),
	  (122, 29485621, 303456, 5000.00, '06-02-2016', 'S'),
	  (123, 29485621, 303456, 5000.00, '07-02-2016', 'S'),
	  (124, 29485621, 303456, 5000.00, '08-02-2016', 'S'),
	  (125, 29485621, 303456, 5000.00, '09-02-2016', 'S'),
	  (126, 29485621, 303456, 5000.00, '10-02-2016', 'S'),
	  (127, 29485621, 303456, 5000.00, '11-02-2016', 'S'),
	  (128, 29485621, 303456, 5000.00, '12-02-2016', 'S'),
	  (129, 29485621, 303456, 5000.00, '01-02-2017', 'S'),
	  (130, 29485621, 303456, 5000.00, '02-02-2017', 'S'),
	  (131, 29485621, 303456, 5000.00, '03-02-2017', 'S'),
	  (132, 29485621, 303456, 5000.00, '04-02-2017', 'S'),
	  (133, 29485621, 303456, 5000.00, '05-02-2017', 'S'),
	  (134, 29485621, 303456, 5000.00, '06-02-2017', 'N'),
	  (135, 29485621, 303456, 5000.00, '07-02-2017', 'N'),
	  (136, 29485621, 303456, 5000.00, '08-02-2017', 'N'),
	  (137, 29485621, 303456, 5000.00, '09-02-2017', 'N'),
	  (138, 29485621, 303456, 5000.00, '10-02-2017', 'N'),
	  (139, 29485621, 303456, 5000.00, '11-02-2017', 'N'),
	  (140, 29485621, 303456, 5000.00, '12-02-2017', 'N'),
	  (141, 29485621, 303456, 5000.00, '01-02-2018', 'N'),
	  (142, 29485621, 303456, 5000.00, '02-02-2018', 'N'),
	  (143, 29485621, 303456, 5000.00, '03-02-2018', 'N'),
	  (144, 29485621, 303456, 5000.00, '04-02-2018', 'N'),
	  (145, 29485621, 303456, 5000.00, '05-02-2018', 'N'),
	  (146, 29485621, 303456, 5000.00, '06-02-2018', 'N'),
	  (147, 29485621, 303456, 5000.00, '07-02-2018', 'N'),
	  (148, 29485621, 303456, 5000.00, '08-02-2018', 'N'),
	  (149, 29485621, 303456, 5000.00, '09-02-2018', 'N'),
	  (150, 29485621, 303456, 5000.00, '10-02-2018', 'N'),
	  (151, 29485621, 303456, 5000.00, '11-02-2018', 'N'),
	  (152, 29485621, 303456, 5000.00, '12-02-2018', 'N'),
	  (153, 29485621, 303456, 5000.00, '01-02-2019', 'N'),
	  (154, 29485621, 303456, 5000.00, '02-02-2019', 'N'),
	  (155, 29485621, 303456, 5000.00, '03-02-2019', 'N'),
	  (157, 29485621, 303456, 5000.00, '04-02-2019', 'N'),
	  (158, 29485621, 303456, 5000.00, '05-02-2019', 'N'),
	  (159, 29485621, 303456, 5000.00, '06-02-2019', 'N'),
	  (160, 29485621, 303456, 5000.00, '07-02-2019', 'N'),
	  (156, 29485621, 303456, 5000.00, '08-02-2019', 'N')
go

;
GO
