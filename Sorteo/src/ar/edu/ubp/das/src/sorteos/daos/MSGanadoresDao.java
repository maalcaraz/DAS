package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;

public class MSGanadoresDao  extends DaoImpl {

	@Override
	public Bean make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
		AdquiridoBean adq = new AdquiridoBean();
		// falta id portal
		adq.setIdPlan(result.getString("id_plan"));
		adq.setDniCliente(result.getString("dni_cliente"));
		adq.setIdConcesionaria(result.getString("id_concesionaria"));
		
		return adq;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		this.connect();
		this.setProcedure("dbo.get_ultimo_ganador");
		List<Bean> ganadores = this.executeQuery();
			
		this.disconnect();
		return ganadores;
		
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
