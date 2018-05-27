package ar.edu.ubp.das.src.concesionarias.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.portal.forms.ClienteForm;
import ar.edu.ubp.das.portal.forms.TransaccionForm;

public class MSConcesionariaDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void insertTransacciones(TransaccionForm transaccion) throws SQLException {
		
		this.connect();
		
		this.setProcedure("dbo.insertar_transaccion(?, ?, ?, ?, ?)");
		
		this.setParameter(1, transaccion.getId_transaccion());
		this.setParameter(2, transaccion.getIdConcesionaria());
		this.setParameter(3, transaccion.getEstadoTransaccion());
		this.setParameter(4, transaccion.getMensajeRespuesta());
		this.setParameter(5, transaccion.getHoraFechaTransaccion());
		this.executeUpdate();
		
		this.disconnect();
		
	}
	
	@Override
	public void insertClientes(LinkedList<ClienteForm> clientes, String idConcesionaria) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.insertar_cliente(?, ?, ?, ?, ?, ?)");
		
		for (ClienteForm c : clientes){
			
			this.setParameter(1, c.getDniCliente());
			this.setParameter(2, idConcesionaria);
			this.setParameter(3, c.getName());
			this.setParameter(4, c.getEdad());
			this.setParameter(5, c.getDomicilio());
			this.setParameter(6, c.getEmailCliente());
			this.executeUpdate();
		}
		
		this.disconnect();
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertCuotas(List<DynaActionForm> form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAdquiridos(List<DynaActionForm> form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertPlanes(List<DynaActionForm> form) throws SQLException {
		// TODO Auto-generated method stub
		
	}


}
