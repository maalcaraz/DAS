package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.sorteos.forms.ParticipanteForm;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class MSParticipantesDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {

		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		SorteosForm p = (SorteosForm)form;
		
		this.connect();
		this.setProcedure("dbo.insertar_participantes(?, ?, ?)");
		/* Procedimiento para setear los datos en la tabla de participantes */
		this.setParameter(1, 16);
		this.setParameter(2, 70);
		this.setParameter(3, p.getIdSorteo());

		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public void update(DynaActionForm bean) throws SQLException {
		
		
	}

	@Override
	public void delete(DynaActionForm bean) throws SQLException {
		
		
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		
		SorteosForm sorteo = (SorteosForm) form;
		this.connect();
		this.setProcedure("dbo.get_participantes(?)");
		this.setParameter(1, sorteo.getIdSorteo());
		
		List<DynaActionForm> participantes = new LinkedList<DynaActionForm>();
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				ParticipanteForm p = new ParticipanteForm();
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
	public boolean valid(DynaActionForm form) throws SQLException {
	
		return false;
	}
}
