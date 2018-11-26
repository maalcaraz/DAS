package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
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
		this.connect();
		ParticipanteBean g = (ParticipanteBean) bean;
		this.setProcedure("dbo.registrar_ganador(?, ?, ?)");
		this.setParameter(1, g.getIdSorteo());
		this.setParameter(2, g.getDniCliente());
		this.setParameter(3, g.getIdConcesionaria());
		this.executeUpdate();
		this.disconnect();
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
		
		this.connect();
		this.setProcedure("dbo.get_ultimo_ganador()");
		List<Bean> ganadores = this.executeQuery();			
		this.disconnect();
		if (ganadores.isEmpty()){ /*Modificar. Sacar estas dos lineas*/
			ganadores = null;
		}
		return ganadores;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
