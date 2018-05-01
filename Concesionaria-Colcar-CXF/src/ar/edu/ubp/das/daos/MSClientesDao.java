package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoImpl;
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
		//Procesamiento para notificar los nuevos ganadores
		this.setProcedure("dbo.cancelar_ganador(?,?)");
		this.setParameter(1, f.getDniCliente());
		this.setParameter(2, f.getFechaSorteo());
		
		this.executeUpdate();
		
		this.disconnect();

	}

	@Override
	public void delete(Bean form) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Bean> select() throws SQLException {
		
		// Estructuras iniciales
		List<Bean> 	clientes = new LinkedList<Bean>();
		ClienteBean clienteRecuperado;
		
		// Operaciones en BD
		this.connect();
		
		this.setProcedure("dbo.get_estados_cuentas()", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
        
        ResultSet result = this.getStatement().executeQuery();

        // Almacenamiento en la estructura a retornar en el servicio
        while (result.next()){
        	clienteRecuperado = new ClienteBean();
        	clienteRecuperado.setDniCliente(result.getString("dni_cliente"));
        	clienteRecuperado.setNomCliente(result.getString("apellido_nombre"));
        	clienteRecuperado.setEmailCliente(result.getString("email"));
        	clientes.add(clienteRecuperado);
        	result.next();
        }
    	
		this.disconnect();
		
		return clientes;
	}

	@Override
	public boolean valid(Bean form) throws SQLException {
		/*
		this.connect();		
		ClienteBean f = (ClienteBean) form;
		this.setProcedure("dbo.verificar_cancelado(?)"); // falta agregar al PA el nro plan
		
		this.setParameter(1, f.getDniCliente());
		 
		
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
