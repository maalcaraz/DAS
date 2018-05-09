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
import ar.edu.ubp.das.src.beans.PlanBean;

public class MSClientesDao extends DaoImpl {

	@Override
	public Bean make(ResultSet result) throws SQLException {
        
    	return null;
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
		ClienteBean f = (ClienteBean) form;
		System.out.println(f.getDniCliente());
		/*Procesamiento para notificar los nuevos ganadores. Actualiza datos del cliente en la base de datos. */
		this.setProcedure("dbo.cancelar_ganador(?,?)");
		this.setParameter(1, f.getDniCliente());
		this.setParameter(2, f.getFechaSorteo());
		this.executeUpdate();
		this.disconnect();

	}
	
	public void update(Bean b1, Bean b2) throws SQLException {
		this.connect();
		
		ClienteBean cliente = (ClienteBean) b1;
		AdquiridoBean adquirido = (AdquiridoBean) b2;
		
	/*-------------- Procesamiento para notificar los nuevos ganadores. Actualiza datos del cliente en la base de datos. -------------*/
		this.setProcedure("dbo.cancelar_ganador(?,?,?)");
		this.setParameter(1, cliente.getDniCliente());
		this.setParameter(2, adquirido.getFechaSorteado());
		this.setParameter(3, adquirido.getIdPlan());
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
		//List<Bean> 	clientes = new LinkedList<Bean>();
		List<List<Bean>> listOfBeans = new ArrayList<List<Bean>>();
		List<Bean> 	clientes = new LinkedList<Bean>();
		List<Bean> 	adquiridos = new LinkedList<Bean>();
		List<Bean> 	planes = new LinkedList<Bean>();
		ClienteBean clienteRecuperado;
		AdquiridoBean adquiridoRecuperado;
		PlanBean planRecuperado;
		
		listOfBeans.add(clientes);
		listOfBeans.add(adquiridos);
		listOfBeans.add(planes);
		
		// Operaciones en BD
		this.connect();
		
		this.setProcedure("dbo.get_estados_cuentas()", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
        ResultSet result = this.getStatement().executeQuery();

        /*------- Almacenamiento en la estructura a retornar en el servicio  -------*/
        while (result.next()){
        	clienteRecuperado = new ClienteBean();
        	adquiridoRecuperado = new AdquiridoBean();
        	planRecuperado = new PlanBean();
        	clienteRecuperado.setDniCliente(result.getString("dni_cliente"));
        	clienteRecuperado.setNomCliente(result.getString("apellido_nombre"));
        	clienteRecuperado.setEdad(result.getString("edad"));
        	clienteRecuperado.setDomicilio(result.getString("domicilio"));
        	clienteRecuperado.setEmailCliente(result.getString("email"));
        	clienteRecuperado.setTelefono(result.getString("telefono"));
        	clienteRecuperado.setIdLocalidad(result.getString("id_localidad"));
        	clienteRecuperado.setCodProvincia(result.getString("cod_provincia"));
        	
        	listOfBeans.get(0).add(clienteRecuperado);
        	
        	adquiridoRecuperado.setDniCliente(result.getString("dni_cliente"));
        	adquiridoRecuperado.setIdPlan(result.getString("id_plan"));
        	adquiridoRecuperado.setCancelado(result.getString("cancelado"));
        	adquiridoRecuperado.setGanadorSorteo(result.getString("ganador_sorteo"));
        	adquiridoRecuperado.setFechaEntrega(result.getString("fecha_entrega"));
        	adquiridoRecuperado.setFechaSorteado(result.getString("fecha_sorteado"));
        	adquiridoRecuperado.setSucursalSuscripcion(result.getString("sucursal_suscripcion"));
        	adquiridoRecuperado.setNroChasis(result.getString("nro_chasis"));
        	
        	listOfBeans.get(1).add(adquiridoRecuperado);
        	
        	planRecuperado.setIdPlan(result.getString("id_plan"));
        	planRecuperado.setDescripcion(result.getString("descripcion"));
        	planRecuperado.setNom_plan(result.getString("nom_plan"));
        	planRecuperado.setCant_cuotas(result.getString("cant_cuotas"));
        	planRecuperado.setEntrega_pactada(result.getString("entrega_pactada"));
        	planRecuperado.setEntrega_pactada(result.getString("financion"));
        	planRecuperado.setEntrega_pactada(result.getString("dueño_plan"));

        	listOfBeans.get(2).add(planRecuperado);
        	
        	result.next();
        }
		this.disconnect();
		return listOfBeans;
	}

	@Override
	public boolean valid(Bean form) throws SQLException {
		
		/*
		this.connect();		
		ClienteBean f = (ClienteBean) form;
		this.setProcedure("dbo.verificar_cancelado(?, ?)"); // falta agregar al PA el nro plan
		
		this.setParameter(1, f.getDniCliente());
		this.setParameter(2, f.getIdPlan());
		 
		
		boolean res = this.executeValidateQuery("cancelado"); 
		
		
		this.disconnect();
		
		return res;
		*/
		return false;
	}
	
	@Override
	public boolean valid2Beans(Bean form1, Bean form2) throws SQLException {
		this.connect();		
		ClienteBean f1 = (ClienteBean) form1;
		PlanBean f2 = (PlanBean) form2;
		this.setProcedure("dbo.verificar_cancelado(?, ?)"); // falta agregar al PA el nro plan
		
		this.setParameter(1, f1.getDniCliente());
		this.setParameter(2, f2.getIdPlan());
		 
		
		boolean res = this.executeValidateQuery("cancelado"); 
		
		
		this.disconnect();
		
		return res;
	}

}
