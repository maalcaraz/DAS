use das

drop table ganadores
drop table plan_comprador
drop table compradores
drop table cuotas
drop table planes
drop table vehiculos
drop table disponibles_sucursal
drop table sucursales
drop table modelos_versiones
drop table versiones
drop table modelos
drop table marcas
drop table localidades
drop table provincias
drop table colores
drop table tipos_vehiculos
go
go

create table provincias
(
	cod_provincia		char(2)		not null,
	nombre_provincia	varchar(30) not null,
	CONSTRAINT PK__provincias__END primary key(cod_provincia)
)
go

create table localidades
(
	cod_provincia		char(2)		not null,
	id_localidad		tinyint		not null,
	nombre_localidad	varchar(30)	not null,
	CONSTRAINT PK__localidades__END primary key(cod_provincia, id_localidad),
	CONSTRAINT FK__loc_prov__END foreign key(cod_provincia) references provincias
)
go

create table marcas
(
	id_marca				smallint		not null,
	nombre_marca			varchar(30)		not null,
	nacionalidad			varchar(20)		not null,
	CONSTRAINT PK__marcas__END primary key(id_marca)
)
go

create table modelos
(
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	nombre_modelo			varchar(30)		not null,
	CONSTRAINT PK__modelos__END	primary key (id_marca, id_modelo),
	CONSTRAINT FK__modelos_marcas__END foreign key (id_marca) references marcas
)
go

create table versiones
(
	id_version				smallint		not null,
	descripcion				varchar(30)		not null,
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
	nombre_color	varchar(15)		not null,
	CONSTRAINT PK__colores__END primary key(id_color)
)
go

create table sucursales
(
	id_sucursal				smallint		not null,
	cod_provincia			char(2)			not null,
	id_localidad			tinyint			not null,
	nombre_sucursal			varchar(20)		null,
	CONSTRAINT PK__sucursales__END primary key(id_sucursal),
	CONSTRAINT FK__loc_suc__END foreign key (cod_provincia, id_localidad) references localidades
)
go

create table tipos_vehiculos
(
	id_tipo_vehiculo		tinyint			not null,
	nombre_tipo_vehiculo	varchar(20)		not null,
	CONSTRAINT PK__tipos_vehiculos__END primary key(id_tipo_vehiculo)		
)
go

create table disponibles_sucursal
(
	id_sucursal				smallint		not null,
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	id_version				smallint		not null,
	cantidad				tinyint			null,
	CONSTRAINT PK__disponibles_sucursal__END primary key(id_sucursal, id_marca, id_modelo, id_version),
	CONSTRAINT FK__dis_suc_sucursales__END foreign key (id_sucursal) references sucursales,
	CONSTRAINT FK__dis_suc_modelos_versiones__END foreign key (id_marca, id_modelo, id_version) references modelos_versiones
)
go

create table compradores
(
	dni_comprador			char(8)			not null,
	apellido_nombre			char(30)		not null,
	edad					smallint		not null,
	domicilio				char(20)		null,
	cod_provincia			char(2)			not null,
	id_localidad			tinyint			not null,
	telefono				char(20)		null,
	ganador					char(1)			check(ganador in ('S', 'N')) default 'N'
	CONSTRAINT PK__compradores__END primary key(dni_comprador),
	CONSTRAINT FK__compradores_localidades__END foreign key(cod_provincia, id_localidad) references localidades,
	check (edad > 18) -- los compradores deben ser mayores de edad
)
go

create table vehiculos	
(
	nro_serie				varchar(15)			not null,
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	id_version				smallint		not null,
	id_color				tinyint			not null,
	id_tipo_vehiculo		tinyint			not null,
	precio					decimal(10, 2)	null,
	año_fabricacion			smallint		null,
	CONSTRAINT PK__vehiculos__END primary key(nro_serie),
	CONSTRAINT FK__veh_modelos_versiones__END foreign key(id_marca, id_modelo, id_version) references modelos_versiones,
	CONSTRAINT FK__veh_colores__END foreign key(id_color) references colores,
	CONSTRAINT FK__veh_tipos_vehiculos__END foreign key(id_tipo_vehiculo) references tipos_vehiculos
)
go

create table planes
(
	id_plan					integer			not null, 
	id_sucursal				smallint		not null,
	id_marca				smallint		not null,
	id_modelo				smallint		not null,
	id_version				smallint		not null,
	cant_cuotas				tinyint			null,
	CONSTRAINT PK__planes__END primary key(id_plan),
	CONSTRAINT FK__planes_disponibles_sucursal__END foreign key(id_sucursal,id_marca, id_modelo, id_version) references disponibles_sucursal
)
go

create table cuotas
(
	id_cuota				smallint		not null,
	id_plan					integer			not null,
	monto					decimal(10,2)	null, -- por defecto
	fecha_vencimiento		date			null,
	pagó					char(1)			check (pagó in ('N', 'S'))	DEFAULT 'S',
	CONSTRAINT PK__cuotas__END primary key (id_cuota, id_plan),
	CONSTRAINT FK__cuotas_planes__END foreign key(id_plan) references planes
)
go

create table plan_comprador
(
	id_plan					integer			not null, 
	dni_comprador			char(8)			not null,		
	CONSTRAINT PK__plan_comprador__END primary key (id_plan, dni_comprador),
	CONSTRAINT FK__plan_plan_comprador__END foreign key(id_plan) references planes,
	CONSTRAINT FK__plan_comprador_comprador__END foreign key(dni_comprador) references compradores
)
go

create table ganadores
(
	id_plan					integer			not null, 
	dni_comprador			char(8)			not null,
	fecha_sorteo			date			not null,
	CONSTRAINT PK__ganadores__END primary key(id_plan, dni_comprador, fecha_sorteo),
	CONSTRAINT FK__ganadores_plan_comps__END foreign key(id_plan, dni_comprador) references plan_comprador
)
go

insert into provincias(cod_provincia, nombre_provincia)
values('CB', 'Cordoba'),
	  ('SF', 'Santa Fe'),
	  ('TU', 'Tucuman'), 
	  ('CR', 'Corrientes'),
	  ('NQ', 'Neuquen'),
	  ('MN', 'Misiones')
go

insert into localidades(id_localidad, nombre_localidad, cod_provincia)
values(1, 'Rio Cuarto', 'CB'),
	  (2, 'Cordoba Capital', 'CB'),
	  (3, 'San Miguel', 'TU'),
	  (4, 'Jesus Maria', 'CB'),
	  (5, 'Oncativo', 'CB')

insert into marcas (id_marca, nombre_marca, nacionalidad)
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
		(11, 8, 'Strada')
go

insert into sucursales(id_sucursal, cod_provincia, id_localidad, nombre_sucursal)
values (1, 'CB', 1, 'Sucursal Rio IV'),
	   (2, 'CB', 2, 'Centro'),
	   (3, 'CB', 5, 'Oncativo')
go

insert into colores(id_color, nombre_color)
values(1, 'NEGRO'),
	  (2, 'BLANCO'),
	  (3, 'ROJO'),
	  (4, 'AZUL'),
	  (5, 'PLATA')
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
	   (11, 'Combinato 2.3')
	   -- (12, 'Furgon Maxicargo 2.3 Techo Elevado'),
	   -- (13, '1.4'),
	   -- (14, 'Adventure 1.6 Cabina Extendida'),
	   -- (15, 'Trekking 1.3 Multijet Cabina Doble'),
	   -- (16, 'Working 1.4 Cabina Simple'),
	   -- (17, 'Working 1.4 Cabina Doble')
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
--	  (11, 4, 12),
	  --(11, 5, 13),
	  (11, 6, 1),
	  (11, 6, 6),
	  (11, 7, 1),
	  (11, 7, 6)
go

insert into tipos_vehiculos(id_tipo_vehiculo, nombre_tipo_vehiculo)
values(1, 'PARTICULAR'),
	  (2, 'UTILITARIO'),
	  (3, 'COMERCIAL'),
	  (4, 'TAXI'),
	  (5, 'CAMION')
go

insert into vehiculos (nro_serie, id_marca, id_modelo, id_version, id_color, id_tipo_vehiculo, precio, año_fabricacion)
values(1234, 11, 1, 1, 1, 1, 200.000, 2017),
	  (1235, 11, 1, 1, 2, 1, 200.000, 2017),
	  (1236, 11, 1, 1, 3, 1, 200.000, 2017)
go

insert into compradores(dni_comprador, apellido_nombre, edad, domicilio, cod_provincia, id_localidad, telefono)
values(25555555, 'Juan Perez', 43, 'Av. Siempre Viva 123', 'CB', 2, '351-7777777'),
	  (26666666, 'Marcos Juarez', 40, 'Belgrano 450', 'CB', 2, '351-6666666'),
	  (27777777, 'Pedro Ramirez', 35, 'Rivadavia 59', 'CB', 2, '351-5777777')
go

insert into disponibles_sucursal(id_sucursal, id_marca, id_modelo, id_version, cantidad)
values (1, 11, 1, 1, 20),
	   (1, 11, 1, 2, 20)
go

insert into planes (id_plan, id_sucursal, id_marca, id_modelo, id_version, cant_cuotas)
values(303456, 1, 11, 1, 1, 50),
	  (303457, 1, 11, 1, 2, 50)
	  --(303458, 1, 11, 2, 2, 50)
go

insert into cuotas(id_cuota, id_plan, monto, fecha_vencimiento, pagó)
values(111, 303456, 5000.000, '02-02-2018', 'S'),
	  (112, 303456, 5000.000, '02-03-2018', 'S'),
	  (113, 303456, 5000.000, '02-04-2018', 'S'),
	  (114, 303456, 5000.000, '02-05-2018', 'N'),
	  (115, 303456, 5000.000, '02-06-2018', 'N'),
	  (111, 303457, 5000.000, '02-02-2018', 'N')
	 -- (112, 303458, 5000.000, '02-03-2018', 'N')
go

insert into plan_comprador (id_plan, dni_comprador)
values(0303456, 25555555),
	  (0303457, 26666666)
	 -- (0303458, 27777777)
go

insert into ganadores (id_plan, dni_comprador,fecha_sorteo)
values(0303457, 26666666, '05-05-2018')
go


drop procedure dbo.get_estados_cuentas
go

create procedure dbo.get_estados_cuentas
as
begin
  select c.dni_comprador,
         c.apellido_nombre
	from compradores c (nolock)
   order by c.apellido_nombre
end;
go

