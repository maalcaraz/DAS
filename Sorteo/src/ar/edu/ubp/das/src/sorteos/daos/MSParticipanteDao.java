package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;

public class MSParticipanteDao extends DaoImpl{

	@Override
	public Bean make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		ParticipanteBean p = (ParticipanteBean)bean;
		
		this.connect();
		this.setProcedure("dbo.insertarParticipantes()");
		//parametros
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
		this.setProcedure("dbo.getParticipantes()");
		List<Bean> participantes = new LinkedList<Bean>();
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				ParticipanteBean p = new ParticipanteBean();
				p.setIdSorteo(result.getString("id_sorteo"));				
				p.setDniCliente(result.getString("dni_cliente"));			
				p.setIdConcesionaria(result.getString("id_concesionaria"));		
				p.setFechaSorteo(result.getString("fecha_sorteo"));		
				p.setEmail(result.getString("email"));	
				participantes.add(p);
			}
			catch(Exception ex){
				System.out.println("[ConcDAO]Error en la consulta de participantes registradas. Mensaje: "+ex.getMessage());
			}
			result.next();
		
		}
		this.disconnect();
		return participantes;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
