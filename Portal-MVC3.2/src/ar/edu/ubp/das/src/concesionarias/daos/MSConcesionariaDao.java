package ar.edu.ubp.das.src.concesionarias.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.portal.forms.ClienteForm;

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
	public void insertClientes(List<DynaActionForm> form) throws SQLException {
		this.connect();
		
		for (DynaActionForm f : form){
			ClienteForm c = (ClienteForm) f;
			
			
		}
		
		
		
		this.setProcedure("dbo.registroQuincenal(?)");
	
		//this.setParameter(1);
		this.executeUpdate();
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

	@Override
	public void insertTransacciones(List<DynaActionForm> form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
