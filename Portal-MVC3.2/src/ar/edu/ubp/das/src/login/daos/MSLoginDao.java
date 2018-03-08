package ar.edu.ubp.das.src.login.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSLoginDao extends DaoImpl{

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
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		
		this.connect();
		
		this.setProcedure("dbo.validar_usuarios(?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		this.setParameter(1, form.getItem("user"));
		this.setParameter(2, form.getItem("pass"));
		
		
		this.executeValidateQuery(); 
		
		
		this.disconnect();
		return null;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		
		
		return false;
	}

}
