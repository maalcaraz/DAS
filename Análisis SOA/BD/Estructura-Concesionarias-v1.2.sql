use AUTOHAUS

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
	a�o_fabricacion			smallint		null,
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
	due�o_plan				char(3)			not null check(due�o_plan in ('GOB','CON')),
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
	pag�					char(1)			check (pag� in ('N', 'S'))	DEFAULT 'S',
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
  select c.dni_cliente,
         c.apellido_nombre,
		 c.email
	from clientes c (nolock)
   order by c.apellido_nombre
end;
go

execute dbo.get_estados_cuentas
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
	@fecha_sorteo	varchar(10)
)
AS
BEGIN

	if exists (
				Select * from clientes c
				where c.dni_cliente = @dni_cliente
				)
	UPDATE a
		SET a.fecha_sorteado = convert(varchar(8), @fecha_sorteo, 108), -- EN ESTE PROCEDIMIENTO HAY QUE AGREGAR LA CANCELACION DE CUOTAS.
			a.ganador_sorteo = 'S'
		FROM adquiridos a
		where a.dni_cliente = @dni_cliente
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