package ar.edu.ubp.das.src.ganadores.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.portal.forms.AdquiridoForm;

public class MSGanadoresDao  extends DaoImpl {

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
		List<DynaActionForm> ganadores = new LinkedList<DynaActionForm>();
		this.setProcedure("dbo.get_ganadores", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			DynaActionForm ad = new DynaActionForm();
			ad.setItem("fechaSorteado",result.getString("fecha_sorteado"));
			ad.setItem("nombre", result.getString("apellido_nombre"));
			ad.setItem("nombreConcesionaria", result.getString("nombre_concesionaria"));
			ad.setItem("idConcesionaria", result.getString("id_concesionaria"));
			ganadores.add(ad);
			result.next();
		}
		this.disconnect();
		return ganadores;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
