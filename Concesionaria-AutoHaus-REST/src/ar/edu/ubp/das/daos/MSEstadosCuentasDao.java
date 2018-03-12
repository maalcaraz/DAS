package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoImpl;
import ar.edu.ubp.das.src.beans.EstadoCuentaBean;

public class MSEstadosCuentasDao extends DaoImpl {

	@Override
	public Bean make(ResultSet result) throws SQLException {
        
    	return null;
	}

	@Override
	public void insert(Bean form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Bean form) throws SQLException {
		this.connect();
		
		EstadoCuentaBean f = (EstadoCuentaBean) form;
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
		List<Bean> 	estadosCuentas = new LinkedList<Bean>();
		EstadoCuentaBean 	estadoRecuperado;
		
		// Operaciones en BD
		this.connect();
		
		this.setProcedure("dbo.get_estados_cuentas()", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
        
        ResultSet result = this.getStatement().executeQuery();

        // Almacenamiento en la estructura a retornar en el servicio
        while (result.next()){
        	estadoRecuperado = new EstadoCuentaBean();
        	estadoRecuperado.setDniCliente(result.getString("dni_cliente"));
        	estadoRecuperado.setNomCliente(result.getString("apellido_nombre"));
        	estadoRecuperado.setEmailCliente(result.getString("email"));
    		estadosCuentas.add(estadoRecuperado);
        	result.next();
        }
    	
		this.disconnect();
		
		return estadosCuentas;
	}

	@Override
	public boolean valid(Bean form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
