package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoImpl;
import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.CuotaBean;
import ar.edu.ubp.das.src.beans.PlanBean;

public class MSClientesDao extends DaoImpl {

	@Override
	public Bean make(ResultSet result) throws SQLException {
		
		AdquiridoBean adquirido = new AdquiridoBean();
		
		adquirido.setCancelado(result.getString("cancelado"));
        
    	return adquirido;
	}

	@Override
	public void insert(Bean form) throws SQLException {

	}
	
	public void insert(String textoNovedad) throws SQLException {
		this.connect();
		System.out.println(textoNovedad);
		this.setProcedure("dbo.insertar_novedad(?)");
	
		this.setParameter(1, textoNovedad);
		this.executeUpdate();
		this.disconnect();

	}
	@Override
	public void update(Bean form) throws SQLException {
		this.connect();
		ConcesionariaBean concesionaria = (ConcesionariaBean) form;
		
		/*-------------- Procesamiento para notificar los nuevos ganadores. Actualiza datos del cliente en la base de datos. -------------*/
		this.setProcedure("dbo.cancelar_ganador(?,?,?)");
		this.setParameter(1, concesionaria.getClientes().get(0).getDniCliente());
		this.setParameter(2, concesionaria.getAdquiridos().get(0).getFechaSorteado());
		this.setParameter(3, concesionaria.getAdquiridos().get(0).getIdPlan());
		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public void delete(Bean form) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public List<Bean> select() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Bean>> selectListBeans() throws SQLException {
		
		// Estructuras iniciales
		
		List<List<Bean>> listOfBeans = new ArrayList<List<Bean>>();
		List<Bean> clientes = new LinkedList<Bean>();
		List<Bean> adquiridos = new LinkedList<Bean>();
		List<Bean> 	planes = new LinkedList<Bean>();
		List<Bean> cuotas = new LinkedList<Bean>();
		ClienteBean clienteRecuperado;
		AdquiridoBean adquiridoRecuperado;
		PlanBean planRecuperado;
		CuotaBean cuotaRecuperada;
		
		/* Operaciones en BD*/
		this.connect();
		
		this.setProcedure("dbo.get_estados_cuentas()", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
        ResultSet result = this.getStatement().executeQuery();

        /*------- Almacenamiento en la estructura a retornar en el servicio  -------*/
        
        result.next();
       
        
        while (result.getRow() > 0){
        	clienteRecuperado = new ClienteBean();
        	clienteRecuperado.setDniCliente(result.getString("dni_cliente"));
        	clienteRecuperado.setNomCliente(result.getString("apellido_nombre"));
        	clienteRecuperado.setEdad(result.getString("edad"));
        	clienteRecuperado.setDomicilio(result.getString("domicilio"));
        	clienteRecuperado.setEmailCliente(result.getString("email"));
        	clienteRecuperado.setTelefono(result.getString("telefono"));
        	clienteRecuperado.setIdLocalidad(result.getString("id_localidad"));
        	clienteRecuperado.setCodProvincia(result.getString("cod_provincia"));
        	if (!clientes.contains(clienteRecuperado)){
        		clientes.add(clienteRecuperado);
        	}
        		
        	adquiridoRecuperado = new AdquiridoBean();
    		adquiridoRecuperado.setDniCliente(result.getString("dni_cliente"));
        	adquiridoRecuperado.setIdPlan(result.getString("id_plan"));
        	adquiridoRecuperado.setCancelado(result.getString("cancelado"));
        	adquiridoRecuperado.setGanadorSorteo(result.getString("ganador_sorteo"));
        	adquiridoRecuperado.setFechaEntrega(result.getString("fecha_entrega"));
        	adquiridoRecuperado.setFechaSorteado(result.getString("fecha_sorteado"));
        	adquiridoRecuperado.setSucursalSuscripcion(result.getString("sucursal_suscripcion"));
        	adquiridoRecuperado.setNroChasis(result.getString("nro_chasis"));
        	
        	if (!adquiridos.contains(adquiridoRecuperado)){
        		adquiridos.add(adquiridoRecuperado);
        	}
        
        	planRecuperado = new PlanBean();
        	planRecuperado.setIdPlan(result.getString("id_plan"));
        	planRecuperado.setDescripcion(result.getString("descripcion"));
        	planRecuperado.setNom_plan(result.getString("nom_plan"));
        	planRecuperado.setCant_cuotas(result.getString("cant_cuotas"));
        	planRecuperado.setEntrega_pactada(result.getString("entrega_pactada"));
        	planRecuperado.setFinancion(result.getString("financiacion"));
        	planRecuperado.setDuenoPlan(result.getString("due�o_plan"));
        	if (!planes.contains(planRecuperado)){
        		planes.add(planRecuperado);
        	}
        	
        	cuotaRecuperada = new CuotaBean();
        	cuotaRecuperada.setDniCliente(result.getString("dni_cliente"));
        	cuotaRecuperada.setIdPlan(result.getString("id_plan"));
        	cuotaRecuperada.setIdCuota(result.getString("id_cuota"));
        	cuotaRecuperada.setPagada(result.getString("pag�"));
        	cuotaRecuperada.setImporte(result.getString("importe"));
        	cuotaRecuperada.setFechaVencimiento(result.getString("fecha_vencimiento"));;
        	if (!cuotas.contains(cuotaRecuperada)){
        		cuotas.add(cuotaRecuperada);
        	}
        	
        	result.next();
        }
        listOfBeans.add(clientes);
		listOfBeans.add(adquiridos);
		listOfBeans.add(planes);
		listOfBeans.add(cuotas);
		
		this.disconnect();
		return listOfBeans;
	}

	@Override
	public boolean valid(Bean form) throws SQLException {
		
		this.connect();
		List<Bean> adquiridos;
		AdquiridoBean adquirido = (AdquiridoBean) form;
		this.setProcedure("dbo.verificar_cancelado(?, ?)"); // falta agregar al PA el nro plan
		
		this.setParameter(1, adquirido.getDniCliente());
		this.setParameter(1, adquirido.getIdPlan());
		 
		adquiridos = this.executeQuery();
		adquirido = (AdquiridoBean)adquiridos.get(0);
		
		this.disconnect();
		
		System.out.println(Boolean.valueOf(adquirido.getCancelado()));
		
		return Boolean.valueOf(adquirido.getCancelado());
		
	}
	
}
