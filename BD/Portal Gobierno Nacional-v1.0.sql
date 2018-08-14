use portalgob

drop view dbo.ult_transaccion
drop procedure dbo.validar_usuarios
drop procedure dbo.insertar_cliente
drop procedure dbo.insertar_adquirido
drop procedure dbo.insertar_cuota
drop procedure dbo.insertar_novedad
drop procedure dbo.insertar_plan
drop procedure dbo.insertar_transaccion
drop procedure dbo.insertar_concesionaria
drop procedure dbo.loginUsuario
drop procedure dbo.get_concesionarias
drop procedure dbo.insertar_sorteo
drop procedure dbo.get_sorteos
drop procedure dbo.get_ultimo_ganador
drop procedure dbo.update_concesionaria
drop procedure dbo.insertar_usuario
drop procedure dbo.eliminar_concesionaria
drop procedure dbo.get_cliente_info
go

drop table logs
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
drop table novedades

go


create table concesionarias
(
	id_concesionaria			char(8)			not null,
	nombre_concesionaria		varchar(30)		not null,
	cuit						char(9)			not null,
	email						varchar(50)		null,	
	direccion					varchar(100)	null,
	telefono					char(11)		null,
	ultima_actualizacion		date			null,
	cant_dias_caducidad			tinyint			default '15',
	url_servicio				varchar(100)	not null,
	cod_tecnologia				varchar(10)		check (cod_tecnologia in ('Rest', 'CXF', 'Axis2'))		not null,
	aprobada					char(1)			check (aprobada in ('S', 'N')) default 'N'
	CONSTRAINT PK__concesionarias__END primary key(id_concesionaria)
)
go

create table planes
(
	id_plan					integer			not null, 
	descripcion				varchar(100)	not null,
	cant_cuotas				tinyint			not null,
	entrega_pactada			varchar(50)		not null,
	financiacion			varchar(50)		 null,
	due�o_plan				char(3)			not null check(due�o_plan in ('GOB','CON')),
	id_concesionaria		char(8)			not null,	
	CONSTRAINT PK__planes__END primary key(id_plan, id_concesionaria),
	CONSTRAINT FK__planes_concesionarias__END foreign key (id_concesionaria) references concesionarias
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
	CONSTRAINT FK__adquiridos_planes__END foreign key(id_plan, id_concesionaria) references planes,
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
	pag�					char(1)			check (pag� in ('N', 'S'))	DEFAULT 'S',
	CONSTRAINT PK__cuotas__END primary key (id_cuota, dni_cliente, id_plan, id_concesionaria),
	CONSTRAINT FK__cuotas_planes__END foreign key(id_plan, id_concesionaria) references planes,
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
	mensaje_respuesta		varchar(255)			null,
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

insert into sorteos (id_sorteo, fecha_sorteo, fecha_proximo)
values ('s1', '3-03-2003', '3-03-2008'),
	   ('s2','4-04-2004','4-04-2005'),
	   ('s3','5-05-2005','5-06-2005'),
	   ('s4','6-06-2006','6-07-2006'),
	   ('s5','7-07-2007','7-08-2007')
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
	tipo_usuario	varchar(10)		not null,
	pass			varchar(30)		not null,
	CONSTRAINT PK__usuarios__END primary key(id_usuario),
	check (tipo_usuario in ('admin', 'cliente', 'sistema'))
)
go

insert into usuarios(id_usuario, pass, tipo_usuario)
values ('admin', 'intel123', 'admin'),
	   ('pepe', 'pepepass', 'cliente'),
	   ('23432255', 'pablopass', 'cliente'),
	   ('25555555', 'juanpass', 'cliente')
go

create table novedades
(
	id_novedad				integer			not null identity(1,1),
	textoNovedad			varchar(max)	not null,
	CONSTRAINT PK__novedades__END primary key(id_novedad)
)
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

create table logs -- agregar a la BD del portal
(
	tipoLog		char(5) not null,
	horaLog		datetime,
	usuario		varchar(100),
	check (tipoLog in ('LOGIN','ERROR')),
	CONSTRAINT PK__logs__END primary key(tipoLog, horaLog)
)
go

create procedure dbo.loginUsuario
(
	@username		varchar(max) = null,
	@tipo			integer	
)
AS
BEGIN

		if exists (
					select * from usuarios u
					where u.id_usuario = @username
				   )
			begin
				if exists (select * from usuarios u
							where u.id_usuario = @username
							and u.tipo_usuario = 'admin')
				begin
					select existe = 0
				end
				else
					if exists (	select * from usuarios u
									where u.id_usuario = @username
									and u.tipo_usuario = 'cliente'
						)
					begin
						select existe = 1
					end
					else
						if exists (
									select * from usuarios u
										where u.id_usuario = @username
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

create procedure dbo.insertar_cliente
(
	@dni_cliente				char(8),	
	@id_concesionaria			char(8),
	@apellido_nombre			char(30),
	@edad						smallint,
	@domicilio					char(20),
	@email						varchar(50)
)
AS
	BEGIN
		insert into clientes(dni_cliente, id_concesionaria, apellido_nombre, edad, domicilio, email)
		values(@dni_cliente, @id_concesionaria, @apellido_nombre, @edad, @domicilio, @email)
	END
go

create procedure dbo.insertar_plan
(
	@id_plan					integer, 
	@descripcion				varchar(100),
	@cant_cuotas				tinyint,
	@entrega_pactada			varchar(50),
	@financiacion				varchar(50),
	@due�o_plan					char(3),
	@id_concesionaria			char(8)		
)
AS
	BEGIN
		insert into planes (id_plan, descripcion, cant_cuotas, entrega_pactada, financiacion, due�o_plan, id_concesionaria)
		values(@id_plan, @descripcion, @cant_cuotas, @entrega_pactada, @financiacion, @due�o_plan, @id_concesionaria)
	END
go

create procedure dbo.insertar_adquirido
(
	@id_plan				integer, 
	@dni_cliente			char(8),	
	@id_concesionaria		char(8),	
	@cancelado				char(1),
	@ganador_sorteo			char(1),
	@fecha_sorteado			date,
	@fecha_entrega			date,
	@nro_chasis				varchar(15)
)
AS
	BEGIN
		insert into adquiridos(id_plan, dni_cliente, id_concesionaria, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis)
		values(@id_plan, @dni_cliente,@id_concesionaria, @cancelado, @ganador_sorteo, @fecha_sorteado, @fecha_entrega, @nro_chasis)
	END
go

create procedure dbo.insertar_cuota
(
	@id_cuota				smallint,
	@dni_cliente			char(8),
	@id_plan				integer,
	@id_concesionaria		char(8),
	@importe				decimal(10,2),
	@fecha_vencimiento		date,
	@pag�					char(1)
)
AS
	BEGIN
		insert into cuotas
		values(@id_cuota, @dni_cliente, @id_plan, @id_concesionaria, @importe, @fecha_vencimiento, @pag�)
	END
go

create procedure dbo.insertar_transaccion
(
	@id_transaccion			varchar(20),
	@id_concesionaria		char(10),
	@estado_transaccion		varchar(10),
	@mensaje_respuesta		varchar(255),
	@hora_fecha				date
)
AS
	BEGIN
		
		insert into transacciones(id_transaccion, id_concesionaria, estado_transaccion, mensaje_respuesta, hora_fecha)
		values(@id_transaccion, @id_concesionaria, @estado_transaccion, @mensaje_respuesta, CONVERT (datetime, @hora_fecha))
	END
go

select *
from transacciones
delete 
from transacciones

--execute dbo.insertar_transaccion 'GC--1588588466', 'AH123456', 'Success', 's33' ,  '2018-07-16'

select CONVERT (datetime, '2018-05-28 23:52:53.413')
go

select CAST('02-21-2012 6:10:00 PM' AS DATETIME2)
go 
 
select getdate()
go


create procedure dbo.insertar_concesionaria
(
	@id_concesionaria				char(8),	
	@nombre_concesionaria			varchar(30),
	@cuit							char(9),
	@email							varchar(50),
	@direccion						varchar(100),
	@telefono						char(11),
	@ultima_actualizacion			date,
	@cant_dias_caducidad			tinyint,
	@url_servicio					varchar(100),
	@cod_tecnologia					varchar(10),
	@aprobada						char(1)
)
AS
	BEGIN
		insert into concesionarias
		values(@id_concesionaria, @nombre_concesionaria, @cuit, @email, @direccion, @telefono, @ultima_actualizacion, @cant_dias_caducidad, @url_servicio, @cod_tecnologia, @aprobada)
	END
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

select *
	from clientes
go

select *
	from planes p
--group by p.id_concesionaria
go

select *
	from adquiridos
go

select * 
	from cuotas
go

/*
delete from planes
delete from adquiridos
delete from clientes
delete from cuotas
*/


select * 
	from transacciones
go

/*
insert into concesionarias(id_concesionaria, nombre_concesionaria, cuit, email, direccion, telefono, ultima_actualizacion, cant_dias_caducidad, url_servicio, cod_tecnologia)
values ('AH123456', 'AutoHaus', '27-1234-5', 'info@autohaus.com', 'Av. Colon 300', '351-1111111', '02-02-18' , 5 , 'http://localhost:8080/Concesionaria-AutoHaus-REST/rest/AutoHaus/', 'R')
go
*/
select *
	from concesionarias
go


create procedure dbo.get_concesionarias
AS
BEGIN
	Select * from concesionarias
END
go

--execute dbo.get_concesionarias

create procedure dbo.get_ultimo_ganador
AS 
BEGIN
	select a.id_plan, a.dni_cliente, a.id_concesionaria, a.fecha_sorteado 
	from adquiridos a
	where a.fecha_sorteado = (
								select MAX(s.fecha_sorteo) as ultima_fecha
								from sorteos s	
					)
	and a.ganador_sorteo = 'S'
END 
go

create procedure dbo.insertar_sorteo
(
	@id_sorteo			varchar(30),
	@fecha_sorteo		date,
	@fecha_proximo		date
)
AS
BEGIN
	insert into sorteos
	values (@id_sorteo, @fecha_sorteo, @fecha_proximo)
END
go

create procedure dbo.get_sorteos
AS
BEGIN
	select * from sorteos
END
go

create procedure dbo.update_concesionaria
(
	@id_concesionaria			varchar(30)
)
AS 
BEGIN
	UPDATE c
	SET aprobada = 'S'
	FROM concesionarias c
	where c.id_concesionaria = @id_concesionaria
END
go

create procedure dbo.insertar_usuario
(
	@id_usuario		varchar(20),
	@tipo_usuario	varchar(10),
	@pass			varchar(30)
)
AS
BEGIN
	insert into usuarios 
	values(@id_usuario, @tipo_usuario, @pass)
END
go

select * from usuarios
go

create procedure dbo.eliminar_concesionaria
(
	@id_concesionaria	char(8)
)
AS
BEGIN
	delete c
		from concesionarias c
		where c.id_concesionaria = @id_concesionaria
END
go

select * from concesionarias
go
                                                                                                 
create view dbo.ult_transaccion as
	select max (trans.hora_fecha) as ult_transaccion_gc
		from transacciones trans
		where trans.id_transaccion LIKE 'GC%'
		group by trans.hora_fecha
go

alter procedure dbo.get_cliente_info
(
	@dni_cliente		char(8),
	@id_concesionaria	char(8)	
)
AS
BEGIN
	Select *
	from clientes cli
	join adquiridos ad
	on cli.dni_cliente = ad.dni_cliente
	join planes pla
	on pla.id_plan = ad.id_plan
	join (Select ad1.dni_cliente, ad1.id_plan, SUM(CASE WHEN cuo.pag� = 'S' THEN 1 ELSE 0 END) AS cuotas_pagas
			from adquiridos ad1
			left join cuotas cuo
			on cuo.id_plan = ad1.id_plan
			where ad1.dni_cliente = 25555555
			group by ad1.dni_cliente, ad1.id_plan
			) cli1_cuo_pagas
	on cli.dni_cliente = cli1_cuo_pagas.dni_cliente
	and ad.id_plan = cli1_cuo_pagas.id_plan,
	ult_transaccion
	where cli.dni_cliente = @dni_cliente
	and cli.id_concesionaria = @id_concesionaria
END
go

--execute dbo.get_cliente_info 25555555, AutoHaus

select *
from ult_transaccion

select * from clientes
select * from concesionarias


create procedure 
select * from 
	concesionarias c
	join clientes cli 
	on c.id_concesionaria = cli.id_concesionaria
	join adquiridos a
	on a.id_concesionaria = c.id_concesionaria
	and cli.dni_cliente = a.dni_cliente
