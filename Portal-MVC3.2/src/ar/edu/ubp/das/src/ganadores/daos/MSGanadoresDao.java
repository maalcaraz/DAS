package ar.edu.ubp.das.src.ganadores.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

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
		//this.setProcedure("dbo.get_ganadores", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setProcedure("dbo.get_ganadores");
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			DynaActionForm ad = new DynaActionForm();
			//ad.setItem("idPlan",result.getString("id_plan"));
			ad.setItem("apellidoNombre", result.getString("apellido_nombre"));
			ad.setItem("nombreConcesionaria", result.getString("nombre_concesionaria"));
			ad.setItem("fechaSorteo", result.getString("fecha_sorteo"));
			ad.setItem("idSorteo", result.getString("id_sorteo"));
			ad.setItem("dni", result.getString("dni_cliente"));
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
