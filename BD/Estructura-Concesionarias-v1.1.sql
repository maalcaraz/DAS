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
	descripcion				varchar(25)		not null,
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
	CONSTRAINT FK__planes_disponibles_sucursal__END foreign key(id_sucursal,id_marca, id_modelo, id_version) references disponibles_sucursal,
	CONSTRAINT FK__planes_modelos_versiones__END foreign key(id_marca, id_modelo, id_version) references modelos_versiones
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