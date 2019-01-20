package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;

public class MSParticipantesDao extends DaoImpl{

	@Override
	public Bean make(ResultSet result) throws SQLException {

		return null;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		SorteoBean p = (SorteoBean)bean;
		
		this.connect();
		this.setProcedure("dbo.insertar_participantes(?, ?, ?)");
		/* Procedimiento para setear los datos en la tabla de participantes */
		this.setParameter(1, 26);
		this.setParameter(2, 40);
		this.setParameter(3, p.getIdSorteo());

		this.executeUpdate();
		this.disconnect();
		
	}

	@Override
	public void update(Bean bean) throws SQLException {
		
		
	}

	@Override
	public void delete(Bean bean) throws SQLException {
		
		
	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		this.connect();
		this.setProcedure("dbo.get_participantes()");
		List<Bean> participantes = new LinkedList<Bean>();
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				ParticipanteBean p = new ParticipanteBean();
				p.setIdSorteo(result.getString("id_sorteo"));				
				p.setDniCliente(result.getString("dni_cliente"));			
				p.setIdConcesionaria(result.getString("id_concesionaria"));	
				p.setIdPlan(result.getString("id_plan"));
				p.setFechaSorteo(result.getString("fecha_sorteo"));		
				p.setApellidoNombre(result.getString("apellido_nombre"));
				p.setEmail(result.getString("email"));	
				participantes.add(p);
			}
			catch(Exception ex){
				System.out.println("[ConcDAO]Error en la consulta de participantes registrados. Mensaje: "+ex.getMessage());
			}
			result.next();
		
		}
		this.disconnect();
		return participantes;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
	
		return false;
	}

}
