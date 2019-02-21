use portalgob
go

/*******************************

	DROPS

********************************/

drop view	   dbo.ult_transaccion
drop view dbo.posibles_participantes
drop procedure dbo.registrar_ganador
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
drop procedure dbo.get_datos_clientes
drop procedure dbo.get_participantes
drop procedure dbo.get_sorteos
drop procedure dbo.get_sorteos_pendientes
drop procedure dbo.get_ultimo_ganador
drop procedure dbo.get_ganadores
drop procedure dbo.get_ultimo_sorteo_ganador
drop procedure dbo.aprobar_concesionaria
drop procedure dbo.insertar_usuario
drop procedure dbo.rechazar_concesionaria
drop procedure dbo.get_cliente_info
drop procedure dbo.hoy_es_fecha_de_sorteo
drop procedure dbo.actualizar_sorteo
drop procedure dbo.eliminar_sorteo
drop procedure dbo.editar_sorteo
drop procedure dbo.reconfigurar_concesionaria
drop procedure dbo.actualizar_ultima_fecha_actualizacion
drop procedure dbo.cancelar_localmente
drop procedure dbo.insertar_participantes
drop procedure dbo.update_consumos_pendientes
go

drop table ganadores
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


/*******************************

	TABLES

********************************/


create table concesionarias
(
	id_concesionaria			varchar(20)		not null,
	nombre_concesionaria		varchar(30)		not null,
	cuit						char(9)			not null,
	email						varchar(50)		null,	
	direccion					varchar(100)	null,
	telefono					char(11)		null,
	ultima_actualizacion		date			not null default getDate(),
	cant_dias_caducidad			tinyint			not null default 15,
	url_servicio				varchar(100)	not null,
	cod_tecnologia				varchar(10)		check (cod_tecnologia in ('Rest', 'CXF', 'Axis2'))		not null,
	aprobada					char(1)			check (aprobada in ('S', 'N', 'P')) default 'N',
	consulta_pendiente			char(1)			check (consulta_pendiente in ('S', 'N'))  default 'N',
	notificacion_pendiente		char(1)			check (notificacion_pendiente in ('S', 'N'))  default 'N',
	CONSTRAINT PK__concesionarias__END primary key(id_concesionaria)
)
go

create table planes
(
	id_plan					integer			not null, 
	descripcion				varchar(100)	not null,
	cant_cuotas				tinyint			not null,
	entrega_pactada			varchar(50)		not null,
	financiacion			varchar(50)		null,
	dueño_plan				char(3)			not null check(dueño_plan in ('GOB','CON')),
	id_concesionaria		varchar(20)		not null,	
	CONSTRAINT PK__planes__END primary key(id_plan, id_concesionaria),
	CONSTRAINT FK__planes_concesionarias__END foreign key (id_concesionaria) references concesionarias
)
go

create table clientes
(
	dni_cliente				char(8)			not null,	
	id_concesionaria		varchar(20)		not null,	
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
	id_concesionaria		varchar(20)		not null,	
	cancelado				char(1)			not null	check (cancelado in ('S', 'N')),
	ganador_sorteo			char(1)			not null	check (ganador_sorteo in ('S', 'N')),
	fecha_sorteado			date			null,
	fecha_entrega			date			null,
	nro_chasis				varchar(15)		null,
	fecha_compra_plan		date			not null default getDate(), 
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
	id_concesionaria		varchar(20)		not null,
	importe					decimal(10,2)	not null,
	fecha_vencimiento		date			null,
	pagó					char(1)			check (pagó in ('N', 'S'))	DEFAULT 'S',
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
	id_concesionaria		varchar(20)			not null,
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
	fecha_ejecucion		date			null,
	fecha_notificacion	date			null,
	pendiente			char(1)			default null	check (pendiente in ('S','N', null)),
	descripcion			varchar(200)	not null,
	CONSTRAINT PK__sorteos__END primary key(id_sorteo)
)
go


create table participantes_sorteos
(
	id_sorteo				varchar(30)		not null,
	dni_cliente				char(8)			not null,
	id_concesionaria		varchar(20)		not null,	
	fecha_sorteo			date			null,
	apellido_nombre			char(30)		not null,
	email					varchar(30)		null,
	id_plan					integer			not null, 
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


create table novedades
(
	id_novedad				integer			not null identity(1,1),
	textoNovedad			varchar(max)	not null,
	CONSTRAINT PK__novedades__END primary key(id_novedad)
)
go

create table logs -- agregar a la BD del portal
(
	tipoLog		char(5) not null,
	horaLog		datetime,
	usuario		varchar(100),
	check (tipoLog in ('LOGIN','ERROR')),
	CONSTRAINT PK__logs__END primary key(tipoLog, horaLog)
)
go

create table ganadores
(
	id_sorteo				varchar(30)		not null,
	dni_cliente				char(8)			not null,
	apellido_nombre			char(30)		not null,
	nombre_concesionaria	varchar(30)		not null,
	vehiculo_adq			varchar(20)		null,
	CONSTRAINT PK__ganadores__END primary key (id_sorteo, dni_cliente),
	CONSTRAINT FK__ganadores_sorteos__END foreign key (id_sorteo) references sorteos
)
go

/*******************************

	INSERTS

********************************/


insert into usuarios(id_usuario, pass, tipo_usuario)
values ('admin', 'intel123', 'admin'),
	   ('pepe', 'pepepass', 'cliente'),
	   ('23432255', 'pablopass', 'cliente'),
	   ('25555555', 'juanpass', 'cliente')
go

/*******************************

	VIEWS

********************************/

create view dbo.ult_transaccion as
	select max (trans.hora_fecha) as ult_transaccion_gc
		from transacciones trans
		where trans.id_transaccion LIKE 'GC%'
		group by trans.hora_fecha
go

create view dbo.posibles_participantes as

	SELECT pp.id_concesionaria, pp.dni_cliente, pp.id_plan, pp.cuotas_pagas_al_dia
	FROM adquiridos adq
	INNER JOIN 
	(Select ad.id_concesionaria, ad.dni_cliente, ad.id_plan, SUM(CASE WHEN cuo.pagó = 'S' THEN 1 ELSE 0 END) AS cuotas_pagas_al_dia, MAX(cuo.fecha_vencimiento) as max_cuota_vencimiento
			from adquiridos ad
			join planes pl
			on pl.id_plan = ad.id_plan
			and pl.id_concesionaria = ad.id_concesionaria
			left join cuotas cuo
			on cuo.id_plan = ad.id_plan
			and cuo.dni_cliente = ad.dni_cliente
			and cuo.id_concesionaria = ad.id_concesionaria
			where ad.ganador_sorteo = 'N'
			and pl.dueño_plan = 'GOB'
			and cuo.fecha_vencimiento < getDate()
			--and DATEDIFF ( month ,  (select convert(datetime, cuo.fecha_vencimiento)) , getDate() ) > 0
			group by ad.id_concesionaria, ad.dni_cliente, ad.id_plan) pp
	on pp.dni_cliente = adq.dni_cliente
	and pp.id_concesionaria = adq.id_concesionaria
	and pp.id_plan = adq.id_plan
	where pp.cuotas_pagas_al_dia = DATEDIFF ( MONTH ,  (select convert(datetime, adq.fecha_compra_plan)) , (select convert(datetime, pp.max_cuota_vencimiento)))
	/*
	  Date diff se hace con max_cuota_vencimiento para evitar casos en los cuales ya estamos en el mes de vencimiento pero
	  la cuota vence a mediados de mes. El sistema tomaba como que deberia haber pagado el mes pero la cuota todavia no vencio.
	  Ejemplo: pagaste hasta Enero inclusive, hoy es 2 de febrero pero la cuota vence el 10 de febrero, te toma como que no
	  estas al dia por que ya cuenta febrero pero la cuota de febrero no esta paga. Se soluciono tomando el dia de la cuota maxima
	  que todavia no vencio. La cuota maxima va a ser menor al dia de hoy por el control and cuo.fecha_vencimiento < getDate()
	 */
go

select * from posibles_participantes pp
order by pp.id_concesionaria
go

-- SELECT DATEDIFF(year,        '2005-12-31 23:59:59.9999999', '2006-01-01 00:00:00.0000000');

/*******************************

	PROCEDURES

********************************/


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
	@id_concesionaria			varchar(20),
	@apellido_nombre			char(30),
	@edad						smallint,
	@domicilio					char(20),
	@email						varchar(50)
)
AS
	BEGIN
		if exists (
					Select * from clientes c
					where c.id_concesionaria = @id_concesionaria
					and c.dni_cliente = @dni_cliente
					)
		update c
		set c.apellido_nombre = @apellido_nombre,
			c.edad = @edad,
			c.domicilio = @domicilio,
			c.email =  @email
		from clientes c
		where c.dni_cliente = @dni_cliente
		and c.id_concesionaria = @id_concesionaria
		ELSE
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
	@dueño_plan					char(3),
	@id_concesionaria			varchar(20)		
)
AS
	BEGIN
		if exists (
					Select * from planes p
					where p.id_plan = @id_plan
					and p.id_concesionaria = @id_concesionaria
					)
			update p
			set p.descripcion = @descripcion,
				p.cant_cuotas = @cant_cuotas,
				p.entrega_pactada = @entrega_pactada,
				p.financiacion = @financiacion,
				p.dueño_plan = @dueño_plan
			from planes p
			where p.id_plan = @id_plan;
		ELSE
			insert into planes
			values(@id_plan, @descripcion, @cant_cuotas, @entrega_pactada, @financiacion, @dueño_plan, @id_concesionaria)
	END
go

create procedure dbo.insertar_adquirido
(
	@id_plan				integer, 
	@dni_cliente			char(8),	
	@id_concesionaria		varchar(20),	
	@cancelado				char(1),
	@ganador_sorteo			char(1),
	@fecha_sorteado			date,
	@fecha_entrega			date,
	@nro_chasis				varchar(15),
	@fecha_compra_plan		date
)
AS
	BEGIN
		if exists (
					Select * from adquiridos ad
					where ad.id_plan = @id_plan
					and ad.dni_cliente = @dni_cliente
					and ad.id_concesionaria = @id_concesionaria
					)
			update ad
			set ad.cancelado = @cancelado,
				ad.ganador_sorteo = @ganador_sorteo,
				ad.fecha_sorteado = @fecha_sorteado,
				ad.fecha_entrega = @fecha_entrega,
				ad.nro_chasis = @nro_chasis,
				ad.fecha_compra_plan = @fecha_compra_plan
			from adquiridos ad
			where ad.id_plan = @id_plan
		ELSE
		insert into adquiridos(id_plan, dni_cliente, id_concesionaria, cancelado, ganador_sorteo, fecha_sorteado, fecha_entrega, nro_chasis, fecha_compra_plan)
		values(@id_plan, @dni_cliente,@id_concesionaria, @cancelado, @ganador_sorteo, @fecha_sorteado, @fecha_entrega, @nro_chasis, @fecha_compra_plan)
	END
go

create procedure dbo.insertar_cuota
(
	@id_cuota				smallint,
	@dni_cliente			char(8),
	@id_plan				integer,
	@id_concesionaria		varchar(20),
	@importe				decimal(10,2),
	@fecha_vencimiento		date,
	@pagó					char(1)
)
AS
	BEGIN
		if exists (
					select * from cuotas c
					where c.dni_cliente = @dni_cliente
					and c.id_concesionaria = @id_concesionaria
					and c.id_plan = @id_plan
					and c.id_cuota = @id_cuota
						)
			update c 
			set c.importe = @importe,
				c.fecha_vencimiento = @fecha_vencimiento,
				c.pagó = @pagó
			from cuotas c
			where c.dni_cliente = @dni_cliente
			and c.id_concesionaria = @id_concesionaria
			and c.id_plan = @id_plan
			and c.id_cuota = @id_cuota

		else
			insert into cuotas
			values(@id_cuota, @dni_cliente, @id_plan, @id_concesionaria, @importe, @fecha_vencimiento, @pagó)
	END
go

create procedure dbo.insertar_transaccion
(
	@id_transaccion			varchar(20),
	@id_concesionaria		varchar(20),
	@estado_transaccion		varchar(10),
	@mensaje_respuesta		varchar(255),
	@hora_fecha				date
)
AS
	BEGIN
		insert into transacciones(id_transaccion, id_concesionaria, estado_transaccion, mensaje_respuesta, hora_fecha)
		values(@id_transaccion, @id_concesionaria, @estado_transaccion, @mensaje_respuesta, CONVERT(datetime, @hora_fecha))
	END
go

create procedure dbo.insertar_concesionaria
(
	@id_concesionaria				varchar(20),	
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
		values(@id_concesionaria, @nombre_concesionaria, @cuit, @email, @direccion, @telefono, convert(date, @ultima_actualizacion), @cant_dias_caducidad, @url_servicio, @cod_tecnologia, @aprobada, 'S', 'S')
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

create procedure dbo.get_concesionarias
AS
BEGIN
	Select c.id_concesionaria, c.nombre_concesionaria, c.cuit, c.email, c.direccion, c.telefono, FORMAT(c.ultima_actualizacion, 'dd-MM-yyyy') as ultima_actualizacion, c.cant_dias_caducidad, c.url_servicio, c.cod_tecnologia, c.aprobada, c.consulta_pendiente, c.notificacion_pendiente
	 from concesionarias c
END
go

create procedure dbo.get_ultimo_ganador
AS 
BEGIN
	select TOP 1 * 
		from ganadores g
		join sorteos s
		on g.id_sorteo = s.id_sorteo
		join clientes c
		on c.dni_cliente = g.dni_cliente
		order by s.fecha_ejecucion DESC
END
go

create procedure dbo.insertar_sorteo
(
	@id_sorteo			varchar(30),
	@fecha_sorteo		date,
	@pendiente			char(1),
	@descripcion		varchar(50)
)
AS
BEGIN
	if not exists (select * from sorteos s
					where s.fecha_sorteo = @fecha_sorteo)
	begin
		insert into sorteos (id_sorteo, fecha_sorteo, fecha_ejecucion, pendiente, descripcion)
		values (@id_sorteo, @fecha_sorteo, null, @pendiente, @descripcion)
	end
END
go

create procedure dbo.get_sorteos
AS
BEGIN
	select s.id_sorteo, FORMAT(s.fecha_sorteo, 'dd-MM-yyyy') as fecha_sorteo, FORMAT(s.fecha_ejecucion, 'dd-MM-yyyy') as fecha_ejecucion, FORMAT(s.fecha_notificacion, 'dd-MM-yyyy') as fecha_notificacion, pendiente, descripcion
	from sorteos s
END
go

create procedure dbo.aprobar_concesionaria
(
	@id_concesionaria			varchar(20)	
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

create procedure dbo.rechazar_concesionaria
(
	@id_concesionaria			varchar(20)		
)
AS
BEGIN
	UPDATE c
	SET aprobada = 'N'
	FROM concesionarias c
	where c.id_concesionaria = @id_concesionaria
END
go

create procedure dbo.get_cliente_info
(
	@dni_cliente		char(8),
	@id_concesionaria	varchar(20)
)
AS
BEGIN
	Select cli.dni_cliente, cli.apellido_nombre, cli.edad, cli.domicilio, cli.email, ad.id_plan, ad.id_concesionaria, ad.nro_chasis, ad.fecha_entrega, ad.cancelado,
	ad.ganador_sorteo, pla.cant_cuotas, cli1_cuo_pagas.cuotas_pagas, (pla.cant_cuotas - cli1_cuo_pagas.cuotas_pagas) as cuotas_sin_pagar, ult_transaccion.ult_transaccion_gc
	from clientes cli 
	join adquiridos ad
	on cli.dni_cliente = ad.dni_cliente
	and cli.id_concesionaria = ad.id_concesionaria
	join planes pla 
	on pla.id_plan = ad.id_plan
	and pla.id_concesionaria = ad.id_concesionaria
	join (Select ad1.dni_cliente, ad1.id_plan, SUM(CASE WHEN cuo.pagó = 'S' THEN 1 ELSE 0 END) AS cuotas_pagas
			from adquiridos ad1
			left join cuotas cuo
			on cuo.id_plan = ad1.id_plan
			and cuo.dni_cliente = ad1.dni_cliente
			where ad1.dni_cliente = @dni_cliente
			and ad1.id_concesionaria = @id_concesionaria
			and cuo.id_concesionaria = @id_concesionaria
			group by ad1.dni_cliente, ad1.id_plan
			) cli1_cuo_pagas
	on cli1_cuo_pagas.dni_cliente = cli.dni_cliente
	and cli1_cuo_pagas.id_plan = ad.id_plan,
	ult_transaccion
	where cli.dni_cliente = @dni_cliente
	and cli.id_concesionaria = @id_concesionaria
END
go

create procedure dbo.get_datos_clientes
(
	@id_concesionaria			varchar(20)
)
AS 
BEGIN
	Select cli.dni_cliente, cli.apellido_nombre, cli.edad, cli.domicilio, cli.email, ad.id_plan, conc.nombre_concesionaria, ad.nro_chasis, ad.fecha_entrega, ad.cancelado,
	ad.ganador_sorteo, pla.cant_cuotas, cli1_cuo_pagas.cuotas_pagas, (pla.cant_cuotas - cli1_cuo_pagas.cuotas_pagas) as cuotas_sin_pagar, ult_transaccion.ult_transaccion_gc
	from clientes cli 
	join adquiridos ad
	on cli.dni_cliente = ad.dni_cliente
	and cli.id_concesionaria = ad.id_concesionaria
	join concesionarias conc
	on ad.id_concesionaria = conc.id_concesionaria
	join planes pla 
	on pla.id_plan = ad.id_plan
	and pla.id_concesionaria = ad.id_concesionaria
	join (Select ad1.dni_cliente, ad1.id_plan, SUM(CASE WHEN cuo.pagó = 'S' THEN 1 ELSE 0 END) AS cuotas_pagas
			from adquiridos ad1
			left join cuotas cuo
			on cuo.id_plan = ad1.id_plan
			and cuo.dni_cliente = ad1.dni_cliente
			where ad1.id_concesionaria = @id_concesionaria
			and cuo.id_concesionaria = @id_concesionaria
			group by ad1.dni_cliente, ad1.id_plan
			) cli1_cuo_pagas
	on cli1_cuo_pagas.dni_cliente = cli.dni_cliente
	and cli1_cuo_pagas.id_plan = ad.id_plan,
	ult_transaccion
	where cli.id_concesionaria = @id_concesionaria
END
go

create procedure dbo.get_participantes
(
	@id_sorteo			varchar(30)
)
AS
BEGIN
	Select ps.id_sorteo, ps.dni_cliente, ps.id_concesionaria,  format(ps.fecha_sorteo, 'dd-MM-yyyy')as fecha_sorteo, ps.apellido_nombre, ps.email, ps.id_plan, c.nombre_concesionaria
	from participantes_sorteos ps
	join concesionarias c
	on ps.id_concesionaria = c.id_concesionaria
	where ps.id_sorteo = @id_sorteo
END
go 

create procedure dbo.get_sorteos_pendientes
AS
BEGIN
	select s.id_sorteo, format(s.fecha_sorteo, 'dd-MM-yyyy') as fecha_sorteo, format(s.fecha_ejecucion, 'dd-MM-yyyy') as fecha_ejecucion, s.pendiente, s.descripcion
	from sorteos s
	where s.pendiente = 'S'
	ORDER BY s.fecha_sorteo ASC
END
go

create procedure dbo.hoy_es_fecha_de_sorteo
AS
BEGIN
	select s.id_sorteo, format(s.fecha_sorteo, 'dd-MM-yyyy') as fecha_sorteo, format(s.fecha_ejecucion, 'dd-MM-yyyy') as fecha_ejecucion, s.pendiente, s.descripcion
	from sorteos s
	where s.pendiente is null
	and s.fecha_sorteo = convert(date, getDate())
END
go

create procedure dbo.actualizar_sorteo
(
	@id_sorteo			varchar(30),
	@fecha_sorteo		date,
	@pendiente			char(1),
	@fecha_ejecucion	date,
	@fecha_notificacion date,
	@razon				varchar(200)
)
AS
BEGIN
	UPDATE s
	SET s.pendiente	 = @pendiente,
		s.fecha_sorteo = @fecha_sorteo,
		s.fecha_ejecucion = @fecha_ejecucion,
		s.descripcion = @razon,
		s.fecha_notificacion = @fecha_notificacion
	FROM sorteos s
	where s.id_sorteo = @id_sorteo
END
go

create procedure dbo.eliminar_sorteo
(
	@id_sorteo			varchar(30)
)
AS
BEGIN
	delete s
	from sorteos s
	where s.id_sorteo = @id_sorteo
END
go

create procedure dbo.editar_sorteo
(
	@id_sorteo			varchar(30),	
	@fecha_sorteo		date,
	@descripcion		varchar(50)
)
AS
BEGIN
	update s
	set s.descripcion = @descripcion,
	s.fecha_sorteo = @fecha_sorteo
	from sorteos s
	where s.id_sorteo = @id_sorteo
END
go

create procedure dbo.reconfigurar_concesionaria
(
	@id_concesionaria				varchar(20),
	@cod_tecnologia					varchar(10),
	@url_servicio					varchar(100),
	@cuit							char(9),
	@email							varchar(50),
	@direccion						varchar(100),
	@telefono						char(11),
	@cant_dias_caducidad			integer
)
AS
BEGIN
	update c
	set c.url_servicio			= @url_servicio,
		c.cuit					= @cuit,
		c.email					= @email,
		c.direccion				= @direccion,
		c.telefono				= @telefono,
		c.cod_tecnologia		= @cod_tecnologia,
		c.cant_dias_caducidad	= @cant_dias_caducidad 
	from concesionarias c
	where c.id_concesionaria = @id_concesionaria
END
go

create procedure dbo.actualizar_ultima_fecha_actualizacion
(
	@id_concesionaria				varchar(20),
	@ultima_actualizacion			date

)
AS
BEGIN
	update c
	set c.ultima_actualizacion			= @ultima_actualizacion
	from concesionarias c
	where c.id_concesionaria = @id_concesionaria
END
go


create procedure dbo.get_ganadores
AS
BEGIN
	select g.apellido_nombre, g.dni_cliente, g.id_sorteo, g.nombre_concesionaria, g.vehiculo_adq, format(s.fecha_sorteo, 'dd-MM-yyyy')as fecha_sorteo, a.id_plan, a.id_concesionaria, c.email
		from ganadores g
		join sorteos s
		on g.id_sorteo = s.id_sorteo
		join adquiridos a 
		on g.dni_cliente = a.dni_cliente
		join clientes c 
		on g.dni_cliente = c.dni_cliente
		order by s.fecha_ejecucion
END
go

-- execute dbo.get_ganadores

/*-MARI: Creo que el procedimiento que sigue no hace falta */
create procedure dbo.get_ultimo_sorteo_ganador
AS
BEGIN
	select TOP 1 a.fecha_sorteado, c.apellido_nombre, con.nombre_concesionaria
	from adquiridos a
	join clientes c
	on c.dni_cliente = a.dni_cliente
	join concesionarias con
	on c.id_concesionaria = con.id_concesionaria
	where a.ganador_sorteo = 'S'
	order by a.fecha_sorteado desc
END
go

create procedure dbo.registrar_ganador
(
	@id_sorteo				varchar(30),
	@dni_cliente			char(8)	,
	@id_concesionaria		varchar(20)
)
AS
BEGIN
	declare @n_concesionaria varchar(30), 
			@nombre_ganador char(30)
	select @n_concesionaria = (select c.nombre_concesionaria 
								from concesionarias c
								where c.id_concesionaria = @id_concesionaria
							  ), 
		   @nombre_ganador = (select cli.apellido_nombre
							 from clientes cli
							 where cli.dni_cliente = @dni_cliente
							)
	insert into ganadores (id_sorteo, apellido_nombre, dni_cliente, nombre_concesionaria, vehiculo_adq)
	values(@id_sorteo, @nombre_ganador, @dni_cliente, @n_concesionaria, '')
END
go

create TRIGGER ti_ri_ganadores
on ganadores
FOR insert
AS
	BEGIN
		update ad
		set ad.ganador_sorteo = 'S'
		from adquiridos ad
		where exists (
						select * from inserted i
						join concesionarias c
						on c.nombre_concesionaria = i.nombre_concesionaria
						where c.id_concesionaria = ad.id_concesionaria
						and i.dni_cliente = ad.dni_cliente
						)

	END
go

create procedure dbo.cancelar_localmente
(
	@dni_cliente			char(10),
	@id_concesionaria		varchar(20),
	@id_plan				integer,
	@fecha_sorteado			varchar(20)
)
AS
BEGIN
	update ad
		set ad.cancelado = 'S',
			ad.fecha_sorteado = convert(date, @fecha_sorteado )
		from adquiridos ad
		where ad.dni_cliente = @dni_cliente
		and ad.id_concesionaria = @id_concesionaria
		and ad.id_plan = @id_plan

	update cuo
		set cuo.pagó = 'S'
		from cuotas cuo
		where cuo.dni_cliente = @dni_cliente
		and cuo.id_plan = @id_plan
		and cuo.id_concesionaria = @id_concesionaria
		
END
go


create procedure dbo.insertar_participantes
(
	@minimo_cuotas		tinyint,
	@maximo_cuotas		tinyint,
	@id_sorteo			varchar(30)
)
AS
BEGIN
-- Para evitar duplicados
	/*if not exists (select * from participantes_sorteos p
					where p.id_sorteo = @id_sorteo
					and p.dni_cliente = @dni_cliente
					and p.id_concesionaria = @id_concesionaria
					and p.id_plan = @id_plan)*/
	
	--hay que ver la forma de insertar los participantes que cumplan con las condiciones
	insert into participantes_sorteos(id_sorteo, dni_cliente, id_concesionaria, fecha_sorteo, apellido_nombre, email, id_plan )
		select aux.id_sorteo, cli.dni_cliente, cli.id_concesionaria, aux.fecha_sorteo, cli.apellido_nombre, cli.email, pp.id_plan
		from dbo.posibles_participantes pp
		join clientes cli
		on pp.dni_cliente = cli.dni_cliente
		and pp.id_concesionaria = cli.id_concesionaria ,
		(select s.id_sorteo, s.fecha_sorteo 
		from sorteos s
		where s.id_sorteo = @id_sorteo)aux
		where pp.cuotas_pagas_al_dia >  @minimo_cuotas
		and pp.cuotas_pagas_al_dia < @maximo_cuotas
		
END
go

-- select * from participantes_sorteos


create procedure dbo.update_consumos_pendientes
(
	@id_concesionaria		varchar(20), 
	@consulta_quincenal		char(1),
	@notificar_ganador		char(1)
)
AS
BEGIN
	update c
	set c.consulta_pendiente = @consulta_quincenal,
		c.notificacion_pendiente = @notificar_ganador
	from concesionarias c
	where c.id_concesionaria = @id_concesionaria
END
go


/*******************************

	TESTING

********************************/

/*******************************

	INSERTAR CONCESIONARIA

********************************/

/*
execute dbo.insertar_concesionaria 'AutoHaus1503004614', 'AutoHaus', '27-1234-5', 'info@autohaus.com', 'Av. Colon 300', '351-1111111', 5 , 'http://localhost:8080/Concesionaria-AutoHaus-REST/', 'Rest', 'N'
execute dbo.insertar_concesionaria 'Montironi705993369', 'Montironi', '27-1234-6', 'info@montironi.com', 'Av. Castro Barros 300', '351-2222222', 5 , 'http://localhost:8080/Concesionaria-Montironi-REST/', 'Rest', 'N'
execute dbo.insertar_concesionaria 'Colcar2023979636', 'Colcar', '27-1234-7', 'info@colcar.com', 'Av. Rivadavia 600', '351-3333333', 5, 'http://localhost:9090/ConcesionariaColcarWSPort', 'CXF', 'N'
execute dbo.insertar_concesionaria 'Rosso79149714', 'Rosso', '27-1234-9', 'info@rosso.com', 'Av. Libertad 1200', '351-4444444', '5', 'http://localhost:9191/ConcesionariaRossoWSPort', 'CXF', 'N'
execute dbo.insertar_concesionaria 'Tagle80567923', 'Tagle', '27-1234-8', 'info@tagle.com', 'Av. Libertad 1200', '351-4444444', '5', 'http://localhost:8080/Concesionaria-Tagle-Axis/services/ConcesionariaTagleWS', 'Axis2', 'N'
*/

DECLARE @tmp DATETIME
SET @tmp = GETDATE() -16
execute dbo.insertar_concesionaria 'AutoHaus1503004614', 'AutoHaus', '27-1234-5', 'info@autohaus.com', 'Av. Colon 300', '3511111111', @tmp , 5 , 'http://localhost:8080/Concesionaria-AutoHaus-REST/', 'Rest', 'N'
go
DECLARE @tmp DATETIME
SET @tmp = GETDATE() -16
execute dbo.insertar_concesionaria 'Montironi705993369', 'Montironi', '27-1234-6', 'info@montironi.com', 'Av. Castro Barros 300', '3512222222', @tmp, 5 , 'http://localhost:8080/Concesionaria-Montironi-REST/', 'Rest', 'N'
go
DECLARE @tmp DATETIME
SET @tmp = GETDATE() -16
execute dbo.insertar_concesionaria 'Colcar2023979636', 'Colcar', '27-1234-7', 'info@colcar.com', 'Av. Rivadavia 600', '3513333333', @tmp, 5, 'http://localhost:9090/ConcesionariaColcarWSPort', 'CXF', 'N'
go
DECLARE @tmp DATETIME
SET @tmp = GETDATE() -16
execute dbo.insertar_concesionaria 'Rosso79149714', 'Rosso', '27-1234-9', 'info@rosso.com', 'Av. Libertad 1200', '3514444444', @tmp, 5, 'http://localhost:9191/ConcesionariaRossoWSPort', 'CXF', 'N'
go
DECLARE @tmp DATETIME
SET @tmp = GETDATE() -16
execute dbo.insertar_concesionaria 'Tagle80567923', 'Tagle', '27-1234-8', 'info@tagle.com', 'Av. Libertad 1200', '3514444444', @tmp, 5, 'http://localhost:8080/Concesionaria-Tagle-Axis/services/ConcesionariaTagleWS', 'Axis2', 'N'


/*
	select CONVERT (datetime, '2018-05-28 23:52:53.413')
	go

	select convert(date, '1897-05-05')
	go

	select convert(date, getDate())
	go

	select CAST('02-21-2012 6:10:00 PM' AS DATETIME2)
	go 
 
	select getdate()
	go

	select FORMAT(getDate(), 'dd-MM-yyyy')
	go

	select FORMAT(convert(date, '1897-05-05'), 'dd-MM-yyyy')
	go
*/

select * from sorteos
/* Colocar una fecha vieja de sorteo*/

 insert into sorteos(id_sorteo, fecha_sorteo, fecha_ejecucion, fecha_notificacion, descripcion, pendiente)
 values('s148405990', '02-15-2019', null, null, '[{"name":"operacion","value":"ConsultarConcesionarias"},{"name":"intentos","value":"1"}]', 'S')


select * from concesionarias 

select * from ganadores

execute dbo.get_ganadores