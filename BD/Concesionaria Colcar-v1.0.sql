use COLCAR
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
--drop trigger dbo.tu_ri_cuotas_adquiridos
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

--execute dbo.verificar_cancelado '25555555', 303456
--execute dbo.verificar_cancelado '27777777', 303458

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

/*
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

insert into sucursales(id_sucursal, cod_provincia, id_localidad, nom_sucursal)
values (1, 'CB', 1, 'Sucursal Rio IV'),
	   (2, 'CB', 2, 'Centro'),
	   (3, 'CB', 5, 'Oncativo'),
	   (4, 'CB', 4, 'Jesus Maria')
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

/************************************
IMPORTANTE! Cambiaste DATEFORMAT PARA QUE NUESTROS
INSERTS QUE SE ESCRIBIERON COMO d-m-y se inserten bien
en nuestra base de datos que tiene por default m-d-y
Abajo de estos inserts el DATEFORMAT se vuelve a default
SOLO INSERTAR FECHAS Dentro de estos cambios
************************************/

		SET DATEFORMAT dmy

		insert into adquiridos(id_plan, dni_cliente, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis, sucursal_suscripcion, fecha_compra_plan)
		values(303455, 25174634, 'N', 'N', '07-07-2007', null, null, 1, '08-10-2015'),
			  (303457, 24365613, 'N', 'N', null, null, null, 1, '02-04-2016'),
			  (303458, 22872919, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303455, 33576314, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303456, 23405390, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303457, 34970322, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303457, 33870475, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303458, 30895845, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303456, 21005836, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303458, 29930123, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303457, 32998142, 'N', 'N', null, null, null, 1, '08-10-2015'),
			  (303455, 28421744, 'N', 'N', null, null, null, 1, '08-10-2015')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 25174634, 303455, 5000.00, '08-11-2015', 'S'),
			  (111, 25174634, 303455, 5000.00, '02-12-2015', 'S'),
			  (112, 25174634, 303455, 5000.00, '02-01-2016', 'S'),
			  (113, 25174634, 303455, 5000.00, '02-02-2016', 'S'),
			  (114, 25174634, 303455, 5000.00, '02-03-2016', 'S'),
			  (115, 25174634, 303455, 5000.00, '02-04-2016', 'S'),
			  (116, 25174634, 303455, 5000.00, '02-05-2016', 'S'),
			  (117, 25174634, 303455, 5000.00, '02-06-2016', 'S'),
			  (118, 25174634, 303455, 5000.00, '02-07-2016', 'S'),
			  (119, 25174634, 303455, 5000.00, '02-08-2016', 'S'),
			  (120, 25174634, 303455, 5000.00, '02-09-2016', 'S'),
			  (121, 25174634, 303455, 5000.00, '02-10-2016', 'S'),
			  (122, 25174634, 303455, 5000.00, '02-11-2016', 'S'),
			  (123, 25174634, 303455, 5000.00, '02-12-2016', 'S'),
			  (124, 25174634, 303455, 5000.00, '02-01-2017', 'S'),
			  (125, 25174634, 303455, 5000.00, '02-02-2017', 'S'),
			  (126, 25174634, 303455, 5000.00, '02-03-2017', 'S'),
			  (127, 25174634, 303455, 5000.00, '02-04-2017', 'S'),
			  (128, 25174634, 303455, 5000.00, '02-05-2017', 'S'),
			  (129, 25174634, 303455, 5000.00, '02-06-2017', 'S'),
			  (130, 25174634, 303455, 5000.00, '02-07-2017', 'S'),
			  (131, 25174634, 303455, 5000.00, '02-08-2017', 'S'),
			  (132, 25174634, 303455, 5000.00, '02-09-2017', 'S'),
			  (133, 25174634, 303455, 5000.00, '02-10-2017', 'S'),
			  (134, 25174634, 303455, 5000.00, '02-11-2017', 'S'),
			  (135, 25174634, 303455, 5000.00, '02-12-2017', 'S'),
			  (136, 25174634, 303455, 5000.00, '02-01-2018', 'S'),
			  (137, 25174634, 303455, 5000.00, '02-02-2018', 'S'),
			  (138, 25174634, 303455, 5000.00, '02-03-2018', 'S'),
			  (139, 25174634, 303455, 5000.00, '02-04-2018', 'S'),
			  (140, 25174634, 303455, 5000.00, '02-05-2018', 'S'),
			  (141, 25174634, 303455, 5000.00, '02-06-2018', 'S'),
			  (142, 25174634, 303455, 5000.00, '02-07-2018', 'S'),
			  (143, 25174634, 303455, 5000.00, '02-08-2018', 'S'),
			  (144, 25174634, 303455, 5000.00, '02-09-2018', 'S'),
			  (145, 25174634, 303455, 5000.00, '02-10-2018', 'S'),
			  (146, 25174634, 303455, 5000.00, '02-11-2018', 'N'),
			  (147, 25174634, 303455, 5000.00, '02-12-2018', 'N'),
			  (148, 25174634, 303455, 5000.00, '02-01-2019', 'N'),
			  (149, 25174634, 303455, 5000.00, '02-02-2019', 'N'),
			  (150, 25174634, 303455, 5000.00, '02-03-2019', 'N'),
			  (151, 25174634, 303455, 5000.00, '02-04-2019', 'N'),
			  (152, 25174634, 303455, 5000.00, '02-05-2019', 'N'),
			  (153, 25174634, 303455, 5000.00, '02-06-2019', 'N'),
			  (154, 25174634, 303455, 5000.00, '02-07-2019', 'N'),
			  (155, 25174634, 303455, 5000.00, '02-08-2019', 'N'),
			  (156, 25174634, 303455, 5000.00, '02-09-2019', 'N'),
			  (157, 25174634, 303455, 5000.00, '02-10-2019', 'N'),
			  (158, 25174634, 303455, 5000.00, '02-11-2019', 'N'),
			  (159, 25174634, 303455, 5000.00, '02-12-2019', 'N'),
			  (160, 25174634, 303455, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 24365613, 303457, 5000.00, '02-05-2016', 'S'),
			  (111, 24365613, 303457, 5000.00, '02-06-2016', 'S'),
			  (112, 24365613, 303457, 5000.00, '02-07-2016', 'S'),
			  (113, 24365613, 303457, 5000.00, '02-08-2016', 'S'),
			  (114, 24365613, 303457, 5000.00, '02-09-2016', 'S'),
			  (115, 24365613, 303457, 5000.00, '02-10-2016', 'S'),
			  (116, 24365613, 303457, 5000.00, '02-11-2016', 'S'),
			  (117, 24365613, 303457, 5000.00, '02-12-2016', 'S'),
			  (118, 24365613, 303457, 5000.00, '02-01-2017', 'S'),
			  (119, 24365613, 303457, 5000.00, '02-02-2017', 'S'),
			  (120, 24365613, 303457, 5000.00, '02-03-2017', 'S'),
			  (121, 24365613, 303457, 5000.00, '02-04-2017', 'S'),
			  (122, 24365613, 303457, 5000.00, '02-05-2017', 'S'),
			  (123, 24365613, 303457, 5000.00, '02-06-2017', 'S'),
			  (124, 24365613, 303457, 5000.00, '02-07-2017', 'S'),
			  (125, 24365613, 303457, 5000.00, '02-08-2017', 'S'),
			  (126, 24365613, 303457, 5000.00, '02-09-2017', 'S'),
			  (127, 24365613, 303457, 5000.00, '02-10-2017', 'S'),
			  (128, 24365613, 303457, 5000.00, '02-11-2017', 'S'),
			  (129, 24365613, 303457, 5000.00, '02-12-2017', 'S'),
			  (130, 24365613, 303457, 5000.00, '02-01-2018', 'S'),
			  (131, 24365613, 303457, 5000.00, '02-02-2018', 'S'),
			  (132, 24365613, 303457, 5000.00, '02-03-2018', 'S'),
			  (133, 24365613, 303457, 5000.00, '02-04-2018', 'S'),
			  (134, 24365613, 303457, 5000.00, '02-05-2018', 'S'),
			  (135, 24365613, 303457, 5000.00, '02-06-2018', 'S'),
			  (136, 24365613, 303457, 5000.00, '02-07-2018', 'S'),
			  (137, 24365613, 303457, 5000.00, '02-08-2018', 'S'),
			  (138, 24365613, 303457, 5000.00, '02-09-2018', 'S'),
			  (139, 24365613, 303457, 5000.00, '02-10-2018', 'S'),
			  (140, 24365613, 303457, 5000.00, '02-11-2018', 'S'),
			  (141, 24365613, 303457, 5000.00, '02-12-2018', 'S'),
			  (142, 24365613, 303457, 5000.00, '02-01-2019', 'S'),
			  (143, 24365613, 303457, 5000.00, '02-02-2019', 'S'),
			  (144, 24365613, 303457, 5000.00, '02-03-2019', 'S'),
			  (145, 24365613, 303457, 5000.00, '02-04-2019', 'S'),
			  (146, 24365613, 303457, 5000.00, '02-05-2019', 'S'),
			  (147, 24365613, 303457, 5000.00, '02-06-2019', 'N'),
			  (148, 24365613, 303457, 5000.00, '02-07-2019', 'N'),
			  (149, 24365613, 303457, 5000.00, '02-08-2019', 'N'),
			  (150, 24365613, 303457, 5000.00, '02-09-2019', 'N'),
			  (151, 24365613, 303457, 5000.00, '02-10-2019', 'N'),
			  (152, 24365613, 303457, 5000.00, '02-11-2019', 'N'),
			  (153, 24365613, 303457, 5000.00, '02-12-2019', 'N'),
			  (154, 24365613, 303457, 5000.00, '02-01-2020', 'N'),
			  (155, 24365613, 303457, 5000.00, '02-02-2020', 'N'),
			  (156, 24365613, 303457, 5000.00, '02-03-2020', 'N'),
			  (157, 24365613, 303457, 5000.00, '02-04-2020', 'N'),
			  (158, 24365613, 303457, 5000.00, '02-05-2020', 'N'),
			  (159, 24365613, 303457, 5000.00, '02-06-2020', 'N'),
			  (160, 24365613, 303457, 5000.00, '02-07-2020', 'N')	  
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 22872919, 303458, 5000.00, '08-11-2015', 'S'),
			  (111, 22872919, 303458, 5000.00, '02-12-2015', 'S'),
			  (112, 22872919, 303458, 5000.00, '02-01-2016', 'S'),
			  (113, 22872919, 303458, 5000.00, '02-02-2016', 'S'),
			  (114, 22872919, 303458, 5000.00, '02-03-2016', 'S'),
			  (115, 22872919, 303458, 5000.00, '02-04-2016', 'S'),
			  (116, 22872919, 303458, 5000.00, '02-05-2016', 'S'),
			  (117, 22872919, 303458, 5000.00, '02-06-2016', 'S'),
			  (118, 22872919, 303458, 5000.00, '02-07-2016', 'S'),
			  (119, 22872919, 303458, 5000.00, '02-08-2016', 'S'),
			  (120, 22872919, 303458, 5000.00, '02-09-2016', 'S'),
			  (121, 22872919, 303458, 5000.00, '02-10-2016', 'S'),
			  (122, 22872919, 303458, 5000.00, '02-11-2016', 'S'),
			  (123, 22872919, 303458, 5000.00, '02-12-2016', 'S'),
			  (124, 22872919, 303458, 5000.00, '02-01-2017', 'S'),
			  (125, 22872919, 303458, 5000.00, '02-02-2017', 'S'),
			  (126, 22872919, 303458, 5000.00, '02-03-2017', 'S'),
			  (127, 22872919, 303458, 5000.00, '02-04-2017', 'S'),
			  (128, 22872919, 303458, 5000.00, '02-05-2017', 'S'),
			  (129, 22872919, 303458, 5000.00, '02-06-2017', 'S'),
			  (130, 22872919, 303458, 5000.00, '02-07-2017', 'S'),
			  (131, 22872919, 303458, 5000.00, '02-08-2017', 'S'),
			  (132, 22872919, 303458, 5000.00, '02-09-2017', 'S'),
			  (133, 22872919, 303458, 5000.00, '02-10-2017', 'S'),
			  (134, 22872919, 303458, 5000.00, '02-11-2017', 'S'),
			  (135, 22872919, 303458, 5000.00, '02-12-2017', 'S'),
			  (136, 22872919, 303458, 5000.00, '02-01-2018', 'S'),
			  (137, 22872919, 303458, 5000.00, '02-02-2018', 'S'),
			  (138, 22872919, 303458, 5000.00, '02-03-2018', 'S'),
			  (139, 22872919, 303458, 5000.00, '02-04-2018', 'S'),
			  (140, 22872919, 303458, 5000.00, '02-05-2018', 'S'),
			  (141, 22872919, 303458, 5000.00, '02-06-2018', 'S'),
			  (142, 22872919, 303458, 5000.00, '02-07-2018', 'S'),
			  (143, 22872919, 303458, 5000.00, '02-08-2018', 'S'),
			  (144, 22872919, 303458, 5000.00, '02-09-2018', 'S'),
			  (145, 22872919, 303458, 5000.00, '02-10-2018', 'S'),
			  (146, 22872919, 303458, 5000.00, '02-11-2018', 'N'),
			  (147, 22872919, 303458, 5000.00, '02-12-2018', 'N'),
			  (148, 22872919, 303458, 5000.00, '02-01-2019', 'N'),
			  (149, 22872919, 303458, 5000.00, '02-02-2019', 'N'),
			  (150, 22872919, 303458, 5000.00, '02-03-2019', 'N'),
			  (151, 22872919, 303458, 5000.00, '02-04-2019', 'N'),
			  (152, 22872919, 303458, 5000.00, '02-05-2019', 'N'),
			  (153, 22872919, 303458, 5000.00, '02-06-2019', 'N'),
			  (154, 22872919, 303458, 5000.00, '02-07-2019', 'N'),
			  (155, 22872919, 303458, 5000.00, '02-08-2019', 'N'),
			  (156, 22872919, 303458, 5000.00, '02-09-2019', 'N'),
			  (157, 22872919, 303458, 5000.00, '02-10-2019', 'N'),
			  (158, 22872919, 303458, 5000.00, '02-11-2019', 'N'),
			  (159, 22872919, 303458, 5000.00, '02-12-2019', 'N'),
			  (160, 22872919, 303458, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 33576314, 303455, 5000.00, '08-11-2015', 'S'),
			  (111, 33576314, 303455, 5000.00, '02-12-2015', 'S'),
			  (112, 33576314, 303455, 5000.00, '02-01-2016', 'S'),
			  (113, 33576314, 303455, 5000.00, '02-02-2016', 'S'),
			  (114, 33576314, 303455, 5000.00, '02-03-2016', 'S'),
			  (115, 33576314, 303455, 5000.00, '02-04-2016', 'S'),
			  (116, 33576314, 303455, 5000.00, '02-05-2016', 'S'),
			  (117, 33576314, 303455, 5000.00, '02-06-2016', 'S'),
			  (118, 33576314, 303455, 5000.00, '02-07-2016', 'S'),
			  (119, 33576314, 303455, 5000.00, '02-08-2016', 'S'),
			  (120, 33576314, 303455, 5000.00, '02-09-2016', 'S'),
			  (121, 33576314, 303455, 5000.00, '02-10-2016', 'S'),
			  (122, 33576314, 303455, 5000.00, '02-11-2016', 'S'),
			  (123, 33576314, 303455, 5000.00, '02-12-2016', 'S'),
			  (124, 33576314, 303455, 5000.00, '02-01-2017', 'S'),
			  (125, 33576314, 303455, 5000.00, '02-02-2017', 'S'),
			  (126, 33576314, 303455, 5000.00, '02-03-2017', 'S'),
			  (127, 33576314, 303455, 5000.00, '02-04-2017', 'S'),
			  (128, 33576314, 303455, 5000.00, '02-05-2017', 'S'),
			  (129, 33576314, 303455, 5000.00, '02-06-2017', 'S'),
			  (130, 33576314, 303455, 5000.00, '02-07-2017', 'S'),
			  (131, 33576314, 303455, 5000.00, '02-08-2017', 'S'),
			  (132, 33576314, 303455, 5000.00, '02-09-2017', 'S'),
			  (133, 33576314, 303455, 5000.00, '02-10-2017', 'S'),
			  (134, 33576314, 303455, 5000.00, '02-11-2017', 'S'),
			  (135, 33576314, 303455, 5000.00, '02-12-2017', 'S'),
			  (136, 33576314, 303455, 5000.00, '02-01-2018', 'S'),
			  (137, 33576314, 303455, 5000.00, '02-02-2018', 'S'),
			  (138, 33576314, 303455, 5000.00, '02-03-2018', 'S'),
			  (139, 33576314, 303455, 5000.00, '02-04-2018', 'S'),
			  (140, 33576314, 303455, 5000.00, '02-05-2018', 'S'),
			  (141, 33576314, 303455, 5000.00, '02-06-2018', 'S'),
			  (142, 33576314, 303455, 5000.00, '02-07-2018', 'S'),
			  (143, 33576314, 303455, 5000.00, '02-08-2018', 'S'),
			  (144, 33576314, 303455, 5000.00, '02-09-2018', 'S'),
			  (145, 33576314, 303455, 5000.00, '02-10-2018', 'S'),
			  (146, 33576314, 303455, 5000.00, '02-11-2018', 'N'),
			  (147, 33576314, 303455, 5000.00, '02-12-2018', 'N'),
			  (148, 33576314, 303455, 5000.00, '02-01-2019', 'N'),
			  (149, 33576314, 303455, 5000.00, '02-02-2019', 'N'),
			  (150, 33576314, 303455, 5000.00, '02-03-2019', 'N'),
			  (151, 33576314, 303455, 5000.00, '02-04-2019', 'N'),
			  (152, 33576314, 303455, 5000.00, '02-05-2019', 'N'),
			  (153, 33576314, 303455, 5000.00, '02-06-2019', 'N'),
			  (154, 33576314, 303455, 5000.00, '02-07-2019', 'N'),
			  (155, 33576314, 303455, 5000.00, '02-08-2019', 'N'),
			  (156, 33576314, 303455, 5000.00, '02-09-2019', 'N'),
			  (157, 33576314, 303455, 5000.00, '02-10-2019', 'N'),
			  (158, 33576314, 303455, 5000.00, '02-11-2019', 'N'),
			  (159, 33576314, 303455, 5000.00, '02-12-2019', 'N'),
			  (160, 33576314, 303455, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 23405390, 303456, 5000.00, '08-11-2015', 'S'),
			  (111, 23405390, 303456, 5000.00, '02-12-2015', 'S'),
			  (112, 23405390, 303456, 5000.00, '02-01-2016', 'S'),
			  (113, 23405390, 303456, 5000.00, '02-02-2016', 'S'),
			  (114, 23405390, 303456, 5000.00, '02-03-2016', 'S'),
			  (115, 23405390, 303456, 5000.00, '02-04-2016', 'S'),
			  (116, 23405390, 303456, 5000.00, '02-05-2016', 'S'),
			  (117, 23405390, 303456, 5000.00, '02-06-2016', 'S'),
			  (118, 23405390, 303456, 5000.00, '02-07-2016', 'S'),
			  (119, 23405390, 303456, 5000.00, '02-08-2016', 'S'),
			  (120, 23405390, 303456, 5000.00, '02-09-2016', 'S'),
			  (121, 23405390, 303456, 5000.00, '02-10-2016', 'S'),
			  (122, 23405390, 303456, 5000.00, '02-11-2016', 'S'),
			  (123, 23405390, 303456, 5000.00, '02-12-2016', 'S'),
			  (124, 23405390, 303456, 5000.00, '02-01-2017', 'S'),
			  (125, 23405390, 303456, 5000.00, '02-02-2017', 'S'),
			  (126, 23405390, 303456, 5000.00, '02-03-2017', 'S'),
			  (127, 23405390, 303456, 5000.00, '02-04-2017', 'S'),
			  (128, 23405390, 303456, 5000.00, '02-05-2017', 'S'),
			  (129, 23405390, 303456, 5000.00, '02-06-2017', 'S'),
			  (130, 23405390, 303456, 5000.00, '02-07-2017', 'S'),
			  (131, 23405390, 303456, 5000.00, '02-08-2016', 'S'),
			  (132, 23405390, 303456, 5000.00, '02-09-2017', 'S'),
			  (133, 23405390, 303456, 5000.00, '02-10-2017', 'S'),
			  (134, 23405390, 303456, 5000.00, '02-11-2017', 'S'),
			  (135, 23405390, 303456, 5000.00, '02-12-2017', 'S'),
			  (136, 23405390, 303456, 5000.00, '02-01-2018', 'S'),
			  (137, 23405390, 303456, 5000.00, '02-02-2018', 'S'),
			  (138, 23405390, 303456, 5000.00, '02-03-2018', 'S'),
			  (139, 23405390, 303456, 5000.00, '02-04-2018', 'S'),
			  (140, 23405390, 303456, 5000.00, '02-05-2018', 'S'),
			  (141, 23405390, 303456, 5000.00, '02-06-2018', 'S'),
			  (142, 23405390, 303456, 5000.00, '02-07-2018', 'S'),
			  (143, 23405390, 303456, 5000.00, '02-08-2018', 'S'),
			  (144, 23405390, 303456, 5000.00, '02-09-2018', 'S'),
			  (145, 23405390, 303456, 5000.00, '02-10-2018', 'S'),
			  (146, 23405390, 303456, 5000.00, '02-11-2018', 'N'),
			  (147, 23405390, 303456, 5000.00, '02-12-2018', 'N'),
			  (148, 23405390, 303456, 5000.00, '02-01-2019', 'N'),
			  (149, 23405390, 303456, 5000.00, '02-02-2019', 'N'),
			  (150, 23405390, 303456, 5000.00, '02-03-2019', 'N'),
			  (151, 23405390, 303456, 5000.00, '02-04-2019', 'N'),
			  (152, 23405390, 303456, 5000.00, '02-05-2019', 'N'),
			  (153, 23405390, 303456, 5000.00, '02-06-2019', 'N'),
			  (154, 23405390, 303456, 5000.00, '02-07-2019', 'N'),
			  (155, 23405390, 303456, 5000.00, '02-08-2019', 'N'),
			  (156, 23405390, 303456, 5000.00, '02-09-2019', 'N'),
			  (157, 23405390, 303456, 5000.00, '02-10-2019', 'N'),
			  (158, 23405390, 303456, 5000.00, '02-11-2019', 'N'),
			  (159, 23405390, 303456, 5000.00, '02-12-2019', 'N'),
			  (160, 23405390, 303456, 5000.00, '02-02-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 34970322, 303457, 5000.00, '08-11-2015', 'S'),
			  (111, 34970322, 303457, 5000.00, '02-12-2015', 'S'),
			  (112, 34970322, 303457, 5000.00, '02-01-2016', 'S'),
			  (113, 34970322, 303457, 5000.00, '02-02-2016', 'S'),
			  (114, 34970322, 303457, 5000.00, '02-03-2016', 'S'),
			  (115, 34970322, 303457, 5000.00, '02-04-2016', 'S'),
			  (116, 34970322, 303457, 5000.00, '02-05-2016', 'S'),
			  (117, 34970322, 303457, 5000.00, '02-06-2016', 'S'),
			  (118, 34970322, 303457, 5000.00, '02-07-2016', 'S'),
			  (119, 34970322, 303457, 5000.00, '02-08-2016', 'S'),
			  (120, 34970322, 303457, 5000.00, '02-09-2016', 'S'),
			  (121, 34970322, 303457, 5000.00, '02-10-2016', 'S'),
			  (122, 34970322, 303457, 5000.00, '02-11-2016', 'S'),
			  (123, 34970322, 303457, 5000.00, '02-12-2016', 'S'),
			  (124, 34970322, 303457, 5000.00, '02-01-2017', 'S'),
			  (125, 34970322, 303457, 5000.00, '02-02-2017', 'S'),
			  (126, 34970322, 303457, 5000.00, '02-03-2017', 'S'),
			  (127, 34970322, 303457, 5000.00, '02-04-2017', 'S'),
			  (128, 34970322, 303457, 5000.00, '02-05-2017', 'S'),
			  (129, 34970322, 303457, 5000.00, '02-06-2017', 'S'),
			  (130, 34970322, 303457, 5000.00, '02-07-2017', 'S'),
			  (131, 34970322, 303457, 5000.00, '02-08-2017', 'S'),
			  (132, 34970322, 303457, 5000.00, '02-09-2017', 'S'),
			  (133, 34970322, 303457, 5000.00, '02-10-2017', 'S'),
			  (134, 34970322, 303457, 5000.00, '02-11-2017', 'S'),
			  (135, 34970322, 303457, 5000.00, '02-12-2017', 'S'),
			  (136, 34970322, 303457, 5000.00, '02-01-2018', 'S'),
			  (137, 34970322, 303457, 5000.00, '02-02-2018', 'S'),
			  (138, 34970322, 303457, 5000.00, '02-03-2018', 'S'),
			  (139, 34970322, 303457, 5000.00, '02-04-2018', 'S'),
			  (140, 34970322, 303457, 5000.00, '02-05-2018', 'S'),
			  (141, 34970322, 303457, 5000.00, '02-06-2018', 'S'),
			  (142, 34970322, 303457, 5000.00, '02-07-2018', 'S'),
			  (143, 34970322, 303457, 5000.00, '02-08-2018', 'S'),
			  (144, 34970322, 303457, 5000.00, '02-09-2018', 'S'),
			  (145, 34970322, 303457, 5000.00, '02-10-2018', 'S'),
			  (146, 34970322, 303457, 5000.00, '02-11-2018', 'N'),
			  (147, 34970322, 303457, 5000.00, '02-12-2018', 'N'),
			  (148, 34970322, 303457, 5000.00, '02-01-2019', 'N'),
			  (149, 34970322, 303457, 5000.00, '02-02-2019', 'N'),
			  (150, 34970322, 303457, 5000.00, '02-03-2019', 'N'),
			  (151, 34970322, 303457, 5000.00, '02-04-2019', 'N'),
			  (152, 34970322, 303457, 5000.00, '02-05-2019', 'N'),
			  (153, 34970322, 303457, 5000.00, '02-06-2019', 'N'),
			  (154, 34970322, 303457, 5000.00, '02-07-2019', 'N'),
			  (155, 34970322, 303457, 5000.00, '02-08-2019', 'N'),
			  (156, 34970322, 303457, 5000.00, '02-09-2019', 'N'),
			  (157, 34970322, 303457, 5000.00, '02-10-2019', 'N'),
			  (158, 34970322, 303457, 5000.00, '02-11-2019', 'N'),
			  (159, 34970322, 303457, 5000.00, '02-12-2019', 'N'),
			  (160, 34970322, 303457, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 33870475, 303457, 5000.00, '08-11-2015', 'S'),
			  (111, 33870475, 303457, 5000.00, '02-12-2015', 'S'),
			  (112, 33870475, 303457, 5000.00, '02-01-2016', 'S'),
			  (113, 33870475, 303457, 5000.00, '02-02-2016', 'S'),
			  (114, 33870475, 303457, 5000.00, '02-03-2016', 'S'),
			  (115, 33870475, 303457, 5000.00, '02-04-2016', 'S'),
			  (116, 33870475, 303457, 5000.00, '02-05-2016', 'S'),
			  (117, 33870475, 303457, 5000.00, '02-06-2016', 'S'),
			  (118, 33870475, 303457, 5000.00, '02-07-2016', 'S'),
			  (119, 33870475, 303457, 5000.00, '02-08-2016', 'S'),
			  (120, 33870475, 303457, 5000.00, '02-09-2016', 'S'),
			  (121, 33870475, 303457, 5000.00, '02-10-2016', 'S'),
			  (122, 33870475, 303457, 5000.00, '02-11-2016', 'S'),
			  (123, 33870475, 303457, 5000.00, '02-12-2016', 'S'),
			  (124, 33870475, 303457, 5000.00, '02-01-2017', 'S'),
			  (125, 33870475, 303457, 5000.00, '02-02-2017', 'S'),
			  (126, 33870475, 303457, 5000.00, '02-03-2017', 'S'),
			  (127, 33870475, 303457, 5000.00, '02-04-2017', 'S'),
			  (128, 33870475, 303457, 5000.00, '02-05-2017', 'S'),
			  (129, 33870475, 303457, 5000.00, '02-06-2017', 'S'),
			  (130, 33870475, 303457, 5000.00, '02-07-2017', 'S'),
			  (131, 33870475, 303457, 5000.00, '02-08-2017', 'S'),
			  (132, 33870475, 303457, 5000.00, '02-09-2017', 'S'),
			  (133, 33870475, 303457, 5000.00, '02-10-2017', 'S'),
			  (134, 33870475, 303457, 5000.00, '02-11-2017', 'S'),
			  (135, 33870475, 303457, 5000.00, '02-12-2017', 'S'),
			  (136, 33870475, 303457, 5000.00, '02-01-2018', 'S'),
			  (137, 33870475, 303457, 5000.00, '02-02-2018', 'S'),
			  (138, 33870475, 303457, 5000.00, '02-03-2018', 'S'),
			  (139, 33870475, 303457, 5000.00, '02-04-2018', 'S'),
			  (140, 33870475, 303457, 5000.00, '02-05-2018', 'S'),
			  (141, 33870475, 303457, 5000.00, '02-06-2018', 'S'),
			  (142, 33870475, 303457, 5000.00, '02-07-2018', 'S'),
			  (143, 33870475, 303457, 5000.00, '02-08-2018', 'S'),
			  (144, 33870475, 303457, 5000.00, '02-09-2018', 'S'),
			  (145, 33870475, 303457, 5000.00, '02-10-2018', 'S'),
			  (146, 33870475, 303457, 5000.00, '02-11-2018', 'N'),
			  (147, 33870475, 303457, 5000.00, '02-12-2018', 'N'),
			  (148, 33870475, 303457, 5000.00, '02-01-2019', 'N'),
			  (149, 33870475, 303457, 5000.00, '02-02-2019', 'N'),
			  (150, 33870475, 303457, 5000.00, '02-03-2019', 'N'),
			  (151, 33870475, 303457, 5000.00, '02-04-2019', 'N'),
			  (152, 33870475, 303457, 5000.00, '02-05-2019', 'N'),
			  (153, 33870475, 303457, 5000.00, '02-06-2019', 'N'),
			  (154, 33870475, 303457, 5000.00, '02-07-2019', 'N'),
			  (155, 33870475, 303457, 5000.00, '02-08-2019', 'N'),
			  (156, 33870475, 303457, 5000.00, '02-09-2019', 'N'),
			  (157, 33870475, 303457, 5000.00, '02-10-2019', 'N'),
			  (158, 33870475, 303457, 5000.00, '02-11-2019', 'N'),
			  (159, 33870475, 303457, 5000.00, '02-12-2019', 'N'),
			  (160, 33870475, 303457, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 30895845, 303458, 5000.00, '08-11-2015', 'S'),
			  (111, 30895845, 303458, 5000.00, '02-12-2015', 'S'),
			  (112, 30895845, 303458, 5000.00, '02-01-2016', 'S'),
			  (113, 30895845, 303458, 5000.00, '02-02-2016', 'S'),
			  (114, 30895845, 303458, 5000.00, '02-03-2016', 'S'),
			  (115, 30895845, 303458, 5000.00, '02-04-2016', 'S'),
			  (116, 30895845, 303458, 5000.00, '02-05-2016', 'S'),
			  (117, 30895845, 303458, 5000.00, '02-06-2016', 'S'),
			  (118, 30895845, 303458, 5000.00, '02-07-2016', 'S'),
			  (119, 30895845, 303458, 5000.00, '02-08-2016', 'S'),
			  (120, 30895845, 303458, 5000.00, '02-09-2016', 'S'),
			  (121, 30895845, 303458, 5000.00, '02-10-2016', 'S'),
			  (122, 30895845, 303458, 5000.00, '02-11-2016', 'S'),
			  (123, 30895845, 303458, 5000.00, '02-12-2016', 'S'),
			  (124, 30895845, 303458, 5000.00, '02-01-2017', 'S'),
			  (125, 30895845, 303458, 5000.00, '02-02-2017', 'S'),
			  (126, 30895845, 303458, 5000.00, '02-03-2017', 'S'),
			  (127, 30895845, 303458, 5000.00, '02-04-2017', 'S'),
			  (128, 30895845, 303458, 5000.00, '02-05-2017', 'S'),
			  (129, 30895845, 303458, 5000.00, '02-06-2017', 'S'),
			  (130, 30895845, 303458, 5000.00, '02-07-2017', 'S'),
			  (131, 30895845, 303458, 5000.00, '02-08-2017', 'S'),
			  (132, 30895845, 303458, 5000.00, '02-09-2017', 'S'),
			  (133, 30895845, 303458, 5000.00, '02-10-2017', 'S'),
			  (134, 30895845, 303458, 5000.00, '02-11-2017', 'S'),
			  (135, 30895845, 303458, 5000.00, '02-12-2017', 'S'),
			  (136, 30895845, 303458, 5000.00, '02-01-2018', 'S'),
			  (137, 30895845, 303458, 5000.00, '02-02-2018', 'S'),
			  (138, 30895845, 303458, 5000.00, '02-03-2018', 'S'),
			  (139, 30895845, 303458, 5000.00, '02-04-2018', 'S'),
			  (140, 30895845, 303458, 5000.00, '02-05-2018', 'S'),
			  (141, 30895845, 303458, 5000.00, '02-06-2018', 'S'),
			  (142, 30895845, 303458, 5000.00, '02-07-2018', 'S'),
			  (143, 30895845, 303458, 5000.00, '02-08-2018', 'S'),
			  (144, 30895845, 303458, 5000.00, '02-09-2018', 'S'),
			  (145, 30895845, 303458, 5000.00, '02-10-2018', 'S'),
			  (146, 30895845, 303458, 5000.00, '02-11-2018', 'N'),
			  (147, 30895845, 303458, 5000.00, '02-12-2018', 'N'),
			  (148, 30895845, 303458, 5000.00, '02-01-2019', 'N'),
			  (149, 30895845, 303458, 5000.00, '02-02-2019', 'N'),
			  (150, 30895845, 303458, 5000.00, '02-03-2019', 'N'),
			  (151, 30895845, 303458, 5000.00, '02-04-2019', 'N'),
			  (152, 30895845, 303458, 5000.00, '02-05-2019', 'N'),
			  (153, 30895845, 303458, 5000.00, '02-06-2019', 'N'),
			  (154, 30895845, 303458, 5000.00, '02-07-2019', 'N'),
			  (155, 30895845, 303458, 5000.00, '02-08-2019', 'N'),
			  (156, 30895845, 303458, 5000.00, '02-09-2019', 'N'),
			  (157, 30895845, 303458, 5000.00, '02-10-2019', 'N'),
			  (158, 30895845, 303458, 5000.00, '02-11-2019', 'N'),
			  (159, 30895845, 303458, 5000.00, '02-12-2019', 'N'),
			  (160, 30895845, 303458, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 21005836, 303456, 5000.00, '08-11-2015', 'S'),
			  (111, 21005836, 303456, 5000.00, '02-12-2015', 'S'),
			  (112, 21005836, 303456, 5000.00, '02-01-2016', 'S'),
			  (113, 21005836, 303456, 5000.00, '02-02-2016', 'S'),
			  (114, 21005836, 303456, 5000.00, '02-03-2016', 'S'),
			  (115, 21005836, 303456, 5000.00, '02-04-2016', 'S'),
			  (116, 21005836, 303456, 5000.00, '02-05-2016', 'S'),
			  (117, 21005836, 303456, 5000.00, '02-06-2016', 'S'),
			  (118, 21005836, 303456, 5000.00, '02-07-2016', 'S'),
			  (119, 21005836, 303456, 5000.00, '02-08-2016', 'S'),
			  (120, 21005836, 303456, 5000.00, '02-09-2016', 'S'),
			  (121, 21005836, 303456, 5000.00, '02-10-2016', 'S'),
			  (122, 21005836, 303456, 5000.00, '02-11-2016', 'S'),
			  (123, 21005836, 303456, 5000.00, '02-12-2016', 'S'),
			  (124, 21005836, 303456, 5000.00, '02-01-2017', 'S'),
			  (125, 21005836, 303456, 5000.00, '02-02-2017', 'S'),
			  (126, 21005836, 303456, 5000.00, '02-03-2017', 'S'),
			  (127, 21005836, 303456, 5000.00, '02-04-2017', 'S'),
			  (128, 21005836, 303456, 5000.00, '02-05-2017', 'S'),
			  (129, 21005836, 303456, 5000.00, '02-06-2017', 'S'),
			  (130, 21005836, 303456, 5000.00, '02-07-2017', 'S'),
			  (131, 21005836, 303456, 5000.00, '02-08-2017', 'S'),
			  (132, 21005836, 303456, 5000.00, '02-09-2017', 'S'),
			  (133, 21005836, 303456, 5000.00, '02-10-2017', 'S'),
			  (134, 21005836, 303456, 5000.00, '02-11-2017', 'S'),
			  (135, 21005836, 303456, 5000.00, '02-12-2017', 'S'),
			  (136, 21005836, 303456, 5000.00, '02-01-2018', 'S'),
			  (137, 21005836, 303456, 5000.00, '02-02-2018', 'S'),
			  (138, 21005836, 303456, 5000.00, '02-03-2018', 'S'),
			  (139, 21005836, 303456, 5000.00, '02-04-2018', 'S'),
			  (140, 21005836, 303456, 5000.00, '02-05-2018', 'S'),
			  (141, 21005836, 303456, 5000.00, '02-06-2018', 'S'),
			  (142, 21005836, 303456, 5000.00, '02-07-2018', 'S'),
			  (143, 21005836, 303456, 5000.00, '02-08-2018', 'S'),
			  (144, 21005836, 303456, 5000.00, '02-09-2018', 'S'),
			  (145, 21005836, 303456, 5000.00, '02-10-2018', 'S'),
			  (146, 21005836, 303456, 5000.00, '02-11-2018', 'N'),
			  (147, 21005836, 303456, 5000.00, '02-12-2018', 'N'),
			  (148, 21005836, 303456, 5000.00, '02-01-2019', 'N'),
			  (149, 21005836, 303456, 5000.00, '02-02-2019', 'N'),
			  (150, 21005836, 303456, 5000.00, '02-03-2019', 'N'),
			  (151, 21005836, 303456, 5000.00, '02-04-2019', 'N'),
			  (152, 21005836, 303456, 5000.00, '02-05-2019', 'N'),
			  (153, 21005836, 303456, 5000.00, '02-06-2019', 'N'),
			  (154, 21005836, 303456, 5000.00, '02-07-2019', 'N'),
			  (155, 21005836, 303456, 5000.00, '02-08-2019', 'N'),
			  (156, 21005836, 303456, 5000.00, '02-09-2019', 'N'),
			  (157, 21005836, 303456, 5000.00, '02-10-2019', 'N'),
			  (158, 21005836, 303456, 5000.00, '02-11-2019', 'N'),
			  (159, 21005836, 303456, 5000.00, '02-12-2019', 'N'),
			  (160, 21005836, 303456, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 29930123, 303458, 5000.00, '08-11-2015', 'S'),
			  (111, 29930123, 303458, 5000.00, '02-12-2015', 'S'),
			  (112, 29930123, 303458, 5000.00, '02-01-2016', 'S'),
			  (113, 29930123, 303458, 5000.00, '02-02-2016', 'S'),
			  (114, 29930123, 303458, 5000.00, '02-03-2016', 'S'),
			  (115, 29930123, 303458, 5000.00, '02-04-2016', 'S'),
			  (116, 29930123, 303458, 5000.00, '02-05-2016', 'S'),
			  (117, 29930123, 303458, 5000.00, '02-06-2016', 'S'),
			  (118, 29930123, 303458, 5000.00, '02-07-2016', 'S'),
			  (119, 29930123, 303458, 5000.00, '02-08-2016', 'S'),
			  (120, 29930123, 303458, 5000.00, '02-09-2016', 'S'),
			  (121, 29930123, 303458, 5000.00, '02-10-2016', 'S'),
			  (122, 29930123, 303458, 5000.00, '02-11-2016', 'S'),
			  (123, 29930123, 303458, 5000.00, '02-12-2016', 'S'),
			  (124, 29930123, 303458, 5000.00, '02-01-2017', 'S'),
			  (125, 29930123, 303458, 5000.00, '02-02-2017', 'S'),
			  (126, 29930123, 303458, 5000.00, '02-03-2017', 'S'),
			  (127, 29930123, 303458, 5000.00, '02-04-2017', 'S'),
			  (128, 29930123, 303458, 5000.00, '02-05-2017', 'S'),
			  (129, 29930123, 303458, 5000.00, '02-06-2017', 'S'),
			  (130, 29930123, 303458, 5000.00, '02-07-2017', 'S'),
			  (131, 29930123, 303458, 5000.00, '02-08-2017', 'S'),
			  (132, 29930123, 303458, 5000.00, '02-09-2017', 'S'),
			  (133, 29930123, 303458, 5000.00, '02-10-2017', 'S'),
			  (134, 29930123, 303458, 5000.00, '02-11-2017', 'S'),
			  (135, 29930123, 303458, 5000.00, '02-12-2017', 'S'),
			  (136, 29930123, 303458, 5000.00, '02-01-2018', 'S'),
			  (137, 29930123, 303458, 5000.00, '02-02-2018', 'S'),
			  (138, 29930123, 303458, 5000.00, '02-03-2018', 'S'),
			  (139, 29930123, 303458, 5000.00, '02-04-2018', 'S'),
			  (140, 29930123, 303458, 5000.00, '02-05-2018', 'S'),
			  (141, 29930123, 303458, 5000.00, '02-06-2018', 'S'),
			  (142, 29930123, 303458, 5000.00, '02-07-2018', 'S'),
			  (143, 29930123, 303458, 5000.00, '02-08-2018', 'S'),
			  (144, 29930123, 303458, 5000.00, '02-09-2018', 'S'),
			  (145, 29930123, 303458, 5000.00, '02-10-2018', 'S'),
			  (146, 29930123, 303458, 5000.00, '02-11-2018', 'N'),
			  (147, 29930123, 303458, 5000.00, '02-12-2018', 'N'),
			  (148, 29930123, 303458, 5000.00, '02-01-2019', 'N'),
			  (149, 29930123, 303458, 5000.00, '02-02-2019', 'N'),
			  (150, 29930123, 303458, 5000.00, '02-03-2019', 'N'),
			  (151, 29930123, 303458, 5000.00, '02-04-2019', 'N'),
			  (152, 29930123, 303458, 5000.00, '02-05-2019', 'N'),
			  (153, 29930123, 303458, 5000.00, '02-06-2019', 'N'),
			  (154, 29930123, 303458, 5000.00, '02-07-2019', 'N'),
			  (155, 29930123, 303458, 5000.00, '02-08-2019', 'N'),
			  (156, 29930123, 303458, 5000.00, '02-09-2019', 'N'),
			  (157, 29930123, 303458, 5000.00, '02-10-2019', 'N'),
			  (158, 29930123, 303458, 5000.00, '02-11-2019', 'N'),
			  (159, 29930123, 303458, 5000.00, '02-12-2019', 'N'),
			  (160, 29930123, 303458, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 32998142, 303457, 5000.00, '08-11-2015', 'S'),
			  (111, 32998142, 303457, 5000.00, '02-12-2015', 'S'),
			  (112, 32998142, 303457, 5000.00, '02-01-2016', 'S'),
			  (113, 32998142, 303457, 5000.00, '02-02-2016', 'S'),
			  (114, 32998142, 303457, 5000.00, '02-03-2016', 'S'),
			  (115, 32998142, 303457, 5000.00, '02-04-2016', 'S'),
			  (116, 32998142, 303457, 5000.00, '02-05-2016', 'S'),
			  (117, 32998142, 303457, 5000.00, '02-06-2016', 'S'),
			  (118, 32998142, 303457, 5000.00, '02-07-2016', 'S'),
			  (119, 32998142, 303457, 5000.00, '02-08-2016', 'S'),
			  (120, 32998142, 303457, 5000.00, '02-09-2016', 'S'),
			  (121, 32998142, 303457, 5000.00, '02-10-2016', 'S'),
			  (122, 32998142, 303457, 5000.00, '02-11-2016', 'S'),
			  (123, 32998142, 303457, 5000.00, '02-12-2016', 'S'),
			  (124, 32998142, 303457, 5000.00, '02-01-2017', 'S'),
			  (125, 32998142, 303457, 5000.00, '02-02-2017', 'S'),
			  (126, 32998142, 303457, 5000.00, '02-03-2017', 'S'),
			  (127, 32998142, 303457, 5000.00, '02-04-2017', 'S'),
			  (128, 32998142, 303457, 5000.00, '02-05-2017', 'S'),
			  (129, 32998142, 303457, 5000.00, '02-06-2017', 'S'),
			  (130, 32998142, 303457, 5000.00, '02-07-2017', 'S'),
			  (131, 32998142, 303457, 5000.00, '02-08-2017', 'S'),
			  (132, 32998142, 303457, 5000.00, '02-09-2017', 'S'),
			  (133, 32998142, 303457, 5000.00, '02-10-2017', 'S'),
			  (134, 32998142, 303457, 5000.00, '02-11-2017', 'S'),
			  (135, 32998142, 303457, 5000.00, '02-12-2017', 'S'),
			  (136, 32998142, 303457, 5000.00, '02-01-2018', 'S'),
			  (137, 32998142, 303457, 5000.00, '02-02-2018', 'S'),
			  (138, 32998142, 303457, 5000.00, '02-03-2018', 'S'),
			  (139, 32998142, 303457, 5000.00, '02-04-2018', 'S'),
			  (140, 32998142, 303457, 5000.00, '02-05-2018', 'S'),
			  (141, 32998142, 303457, 5000.00, '02-06-2018', 'S'),
			  (142, 32998142, 303457, 5000.00, '02-07-2018', 'S'),
			  (143, 32998142, 303457, 5000.00, '02-08-2018', 'S'),
			  (144, 32998142, 303457, 5000.00, '02-09-2018', 'S'),
			  (145, 32998142, 303457, 5000.00, '02-10-2018', 'S'),
			  (146, 32998142, 303457, 5000.00, '02-11-2018', 'N'),
			  (147, 32998142, 303457, 5000.00, '02-12-2018', 'N'),
			  (148, 32998142, 303457, 5000.00, '02-01-2019', 'N'),
			  (149, 32998142, 303457, 5000.00, '02-02-2019', 'N'),
			  (150, 32998142, 303457, 5000.00, '02-03-2019', 'N'),
			  (151, 32998142, 303457, 5000.00, '02-04-2019', 'N'),
			  (152, 32998142, 303457, 5000.00, '02-05-2019', 'N'),
			  (153, 32998142, 303457, 5000.00, '02-06-2019', 'N'),
			  (154, 32998142, 303457, 5000.00, '02-07-2019', 'N'),
			  (155, 32998142, 303457, 5000.00, '02-08-2019', 'N'),
			  (156, 32998142, 303457, 5000.00, '02-09-2019', 'N'),
			  (157, 32998142, 303457, 5000.00, '02-10-2019', 'N'),
			  (158, 32998142, 303457, 5000.00, '02-11-2019', 'N'),
			  (159, 32998142, 303457, 5000.00, '02-12-2019', 'N'),
			  (160, 32998142, 303457, 5000.00, '02-01-2020', 'N')
		go

		insert into cuotas(id_cuota, dni_cliente, id_plan, importe, fecha_vencimiento, pagó)
		values(110, 28421744, 303455, 5000.00, '08-11-2015', 'S'),
			  (111, 28421744, 303455, 5000.00, '02-12-2015', 'S'),
			  (112, 28421744, 303455, 5000.00, '02-01-2016', 'S'),
			  (113, 28421744, 303455, 5000.00, '02-02-2016', 'S'),
			  (114, 28421744, 303455, 5000.00, '02-03-2016', 'S'),
			  (115, 28421744, 303455, 5000.00, '02-04-2016', 'S'),
			  (116, 28421744, 303455, 5000.00, '02-05-2016', 'S'),
			  (117, 28421744, 303455, 5000.00, '02-06-2016', 'S'),
			  (118, 28421744, 303455, 5000.00, '02-07-2016', 'S'),
			  (119, 28421744, 303455, 5000.00, '02-08-2016', 'S'),
			  (120, 28421744, 303455, 5000.00, '02-09-2016', 'S'),
			  (121, 28421744, 303455, 5000.00, '02-10-2016', 'S'),
			  (122, 28421744, 303455, 5000.00, '02-11-2016', 'S'),
			  (123, 28421744, 303455, 5000.00, '02-12-2016', 'S'),
			  (124, 28421744, 303455, 5000.00, '02-01-2017', 'S'),
			  (125, 28421744, 303455, 5000.00, '02-02-2017', 'S'),
			  (126, 28421744, 303455, 5000.00, '02-03-2017', 'S'),
			  (127, 28421744, 303455, 5000.00, '02-04-2017', 'S'),
			  (128, 28421744, 303455, 5000.00, '02-05-2017', 'S'),
			  (129, 28421744, 303455, 5000.00, '02-06-2017', 'S'),
			  (130, 28421744, 303455, 5000.00, '02-07-2017', 'S'),
			  (131, 28421744, 303455, 5000.00, '02-08-2017', 'S'),
			  (132, 28421744, 303455, 5000.00, '02-09-2017', 'S'),
			  (133, 28421744, 303455, 5000.00, '02-10-2017', 'S'),
			  (134, 28421744, 303455, 5000.00, '02-11-2017', 'S'),
			  (135, 28421744, 303455, 5000.00, '02-12-2017', 'S'),
			  (136, 28421744, 303455, 5000.00, '02-01-2018', 'S'),
			  (137, 28421744, 303455, 5000.00, '02-02-2018', 'S'),
			  (138, 28421744, 303455, 5000.00, '02-03-2018', 'S'),
			  (139, 28421744, 303455, 5000.00, '02-04-2018', 'S'),
			  (140, 28421744, 303455, 5000.00, '02-05-2018', 'S'),
			  (141, 28421744, 303455, 5000.00, '02-06-2018', 'S'),
			  (142, 28421744, 303455, 5000.00, '02-07-2018', 'S'),
			  (143, 28421744, 303455, 5000.00, '02-08-2018', 'S'),
			  (144, 28421744, 303455, 5000.00, '02-09-2018', 'S'),
			  (145, 28421744, 303455, 5000.00, '02-10-2018', 'S'),
			  (146, 28421744, 303455, 5000.00, '02-11-2018', 'N'),
			  (147, 28421744, 303455, 5000.00, '02-12-2018', 'N'),
			  (148, 28421744, 303455, 5000.00, '02-01-2019', 'N'),
			  (149, 28421744, 303455, 5000.00, '02-02-2019', 'N'),
			  (150, 28421744, 303455, 5000.00, '02-03-2019', 'N'),
			  (151, 28421744, 303455, 5000.00, '02-04-2019', 'N'),
			  (152, 28421744, 303455, 5000.00, '02-05-2019', 'N'),
			  (153, 28421744, 303455, 5000.00, '02-06-2019', 'N'),
			  (154, 28421744, 303455, 5000.00, '02-07-2019', 'N'),
			  (155, 28421744, 303455, 5000.00, '02-08-2019', 'N'),
			  (156, 28421744, 303455, 5000.00, '02-09-2019', 'N'),
			  (157, 28421744, 303455, 5000.00, '02-10-2019', 'N'),
			  (158, 28421744, 303455, 5000.00, '02-11-2019', 'N'),
			  (159, 28421744, 303455, 5000.00, '02-12-2019', 'N'),
			  (160, 28421744, 303455, 5000.00, '02-01-2020', 'N')
		go

		SET DATEFORMAT mdy

/****
Vuelta DATEFORMAT a default
***/

*/