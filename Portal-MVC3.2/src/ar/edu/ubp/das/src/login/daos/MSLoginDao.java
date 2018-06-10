package ar.edu.ubp.das.src.login.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSLoginDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		
		DynaActionForm usuario = new DynaActionForm();
		usuario.setItem("tipo_usuario", result.getString("existe"));
		return usuario;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		
	//	form.getItem("");
		
		// POR LO PRONTO ESTA PARTE QUEDA EN PAUSA
		
		
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
		
		List<DynaActionForm> res;
		this.connect();
		this.setProcedure("dbo.validar_usuarios(?,?)");
		this.setParameter(1, form.getItem("user"));
		this.setParameter(2, form.getItem("pass"));
		//int res = this.executeValidateQuery("existe"); 
		// Por ahora esto funciona si se pudo loggear
		res = this.executeQuery();
		this.disconnect();
		return res;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		/*this.connect();
		this.setProcedure("dbo.validar_usuarios(?,?)");
		this.setParameter(1, form.getItem("user"));
		this.setParameter(2, form.getItem("pass"));
		//int res = this.executeValidateQuery("existe"); 
		// Por ahora esto funciona si se pudo loggear
		boolean res = ((this.executeQuery() != null) ? true : false);
		this.disconnect();
		return res;*/
		return true;
	}
}
