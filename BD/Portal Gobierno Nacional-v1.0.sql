use portalgob


drop procedure dbo.validar_usuarios
go

drop table transacciones
drop table usuarios
drop table participantes_sorteos
drop table sorteos
drop table errores
drop table adquiridos
drop table cuotas
drop table clientes
drop table planes
drop table concesionarias
drop table tecnologias
go


create table tecnologias
(
	cod_tecnologia			char(1)		check (cod_tecnologia in ('R', 'C', 'A'))		not null,
	nombre_tecnologia		varchar(5)	not null,
	CONSTRAINT PK__tecnologias__END primary key (cod_tecnologia),
	CONSTRAINT UK__tecnologias__END unique (nombre_tecnologia)
)

create table concesionarias
(
	id_concesionaria			char(8)			not null,
	nombre_concesionaria		varchar(30)		not null,
	cuit						char(9)			not null,
	email						varchar(50)		null,	
	direccion					varchar(100)	null,
	telefono					char(11)		null,
	ultima_actualizacion		date			not null,
	cant_dias_caducidad			tinyint			not null,
	url_servicio				varchar(100)	not null,
	cod_tecnologia			char(1)		check (cod_tecnologia in ('R', 'C', 'A'))		not null,
	CONSTRAINT PK__concesionarias__END primary key(id_concesionaria),
	CONSTRAINT FK__concesionarias_tecnologias__END foreign key(cod_tecnologia) references tecnologias
)
go

create table planes
(
	id_plan					integer			not null, 
	descripcion				varchar(100)	not null,
	cant_cuotas				tinyint			not null,
	entrega_pactada			varchar(50)		not null,
	financiacion			varchar(50)		not null,
	dueño_plan				char(3)			not null check(dueño_plan in ('GOB','CON')),
	CONSTRAINT PK__planes__END primary key(id_plan)
)
go

create table clientes
(
	dni_cliente				char(8)			not null,	
	id_concesionaria		char(8)			not null,	
	apellido_nombre			char(30)		not null,
	edad					smallint		not null,
	domicilio				char(20)		null,
	email					varchar(50)		not null,
	CONSTRAINT PK__clientes__END primary key(dni_cliente, id_concesionaria),
	CONSTRAINT FK__clientes_concesionarias foreign key (id_concesionaria) references concesionarias
)
go


create table adquiridos
(
	id_plan					integer			not null, 
	dni_cliente				char(8)			not null,	
	id_concesionaria		char(8)			not null,	
	cancelado				char(1)			not null	check (cancelado in ('S', 'N')),
	ganador_sorteo			char(1)			not null	check (ganador_sorteo in ('S', 'N')),
	fecha_sorteado			date			null,
	fecha_entrega			date			null,
	nro_chasis				varchar(15)		null,
	CONSTRAINT PK__adquiridos__END primary key (id_plan, dni_cliente, id_concesionaria),
	CONSTRAINT FK__adquiridos_planes__END foreign key(id_plan) references planes,
	CONSTRAINT FK__adquiridos_clientes__END foreign key (dni_cliente, id_concesionaria) references clientes
)
go


create table cuotas
(
	id_cuota				smallint		not null,
	dni_cliente				char(8)			not null,
	id_plan					integer			not null,
	id_concesionaria		char(8)			not null,
	importe					decimal(10,2)	not null,
	fecha_vencimiento		date			null,
	pagó					char(1)			check (pagó in ('N', 'S'))	DEFAULT 'S',
	CONSTRAINT PK__cuotas__END primary key (id_cuota, dni_cliente, id_plan),
	CONSTRAINT FK__cuotas_planes__END foreign key(id_plan) references planes,
	CONSTRAINT FK__cuotas_clientes__END foreign key(dni_cliente, id_concesionaria) references clientes
)
go

create table errores
(
	id_error			smallint			not null,
	hora_fecha			datetime			not null,
	mensaje_error		varchar(50)			not null,
	CONSTRAINT PK__errores__END primary key(id_error)
)
go

create table transacciones
(
	id_transaccion			varchar(15)			not null,
	id_concesionaria		char(8)				not null,
	estado_transaccion		varchar(10)			not null,
	mensaje_respuesta		varchar				null,
	hora_fecha				date				not null,
	--retorno						
	check (estado_transaccion in ('SUCCESS','FAILED'))
)
go 

create table sorteos
(
	id_sorteo			varchar(30)		not null,-- alfanumerico que adentro tenga incluida la fecha		
	fecha_sorteo		date			not null,
	fecha_proximo		date			not null,
	CONSTRAINT PK__sorteos__END primary key(id_sorteo)
)
go

create table participantes_sorteos
(
	id_sorteo				varchar(30)		not null,
	dni_cliente				char(8)			not null,
	id_concesionaria		char(8)			not null,	
	CONSTRAINT PK__participantes_sorteos__END primary key (id_sorteo, dni_cliente, id_concesionaria)
)
go

create table usuarios
(
	id_usuario		varchar(20)		not null,
	tipo_usuario	varchar(10)			not null,
	pass			varchar(30)		not null,
	CONSTRAINT PK__usuarios__END primary key(id_usuario),
	check (tipo_usuario in ('admin', 'cliente', 'sistema'))
)
go

insert into usuarios(id_usuario, pass, tipo_usuario)
values ('admin', 'intel123', 'admin'),
	   ('pepe', 'pepepass', 'cliente')
go



create procedure dbo.validar_usuarios
(
	@username		varchar(max) = null,
	@password		varchar(max) = null
)
AS
BEGIN

		if exists (
					select * from usuarios u
					where u.id_usuario = @username
					and u.pass = @password
				   )
			begin
				if exists (select * from usuarios u
							where u.id_usuario = @username
							and u.pass = @password
							and u.tipo_usuario = 'admin')
				begin
					select existe = 0
				end
				else
					if exists (	select * from usuarios u
									where u.id_usuario = @username
									and u.pass = @password
									and u.tipo_usuario = 'cliente'
						)
					begin
						select existe = 1
					end
					else
						if exists (
									select * from usuarios u
										where u.id_usuario = @username
										and u.pass = @password
										and u.tipo_usuario ='sistema')
						begin
							select existe = 2
						end
				end
		else
			begin
				select existe = -1
			end
END
go
--execute dbo.validar_usuarios 'pepe', 'pepepass'
--execute dbo.validar_usuarios 'admin', 'intel123'

create procedure dbo.insertarClientes
(
)
AS
BEGIN
END
go