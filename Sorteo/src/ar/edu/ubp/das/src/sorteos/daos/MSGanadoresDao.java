package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;

public class MSGanadoresDao  extends DaoImpl {

	@Override
	public Bean make(ResultSet result) throws SQLException {
		 return null;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		ParticipanteBean g = (ParticipanteBean) bean;
		
		this.connect();
		this.setProcedure("dbo.registrar_ganador(?, ?, ?)");
		this.setParameter(1, g.getIdSorteo());
		this.setParameter(2, g.getDniCliente());
		this.setParameter(3, g.getIdConcesionaria());
		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public void update(Bean bean) throws SQLException {
		ParticipanteBean g = (ParticipanteBean) bean;
		SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaActualizacionAux = null;
		try {
			fechaActualizacionAux = parser.parse(g.getFechaSorteo());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("[MSGanadoresDAO] " + e.getMessage());
		}
		java.sql.Date fechaActualizacion = new java.sql.Date(fechaActualizacionAux.getTime());
		
		this.connect();
		
		this.setProcedure("dbo.cancelar_localmente(?, ?, ?, ?)");
		this.setParameter(1, g.getDniCliente());
		this.setParameter(2, g.getIdConcesionaria());
		this.setParameter(3, g.getIdPlan());
		this.setParameter(4, fechaActualizacion);
		this.executeUpdate();
		this.disconnect();
		
	}

	@Override
	public void delete(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		
		List<Bean> ganadores = null;
		this.connect();
		
		if(bean != null)
		{
			/*
			 * El codigo dentro de este IF espera un adquiridoBean
			 * ya que necesita id concesionaria
			 */
			AdquiridoBean ganador = (AdquiridoBean) bean;
			ganadores = new LinkedList<Bean>();
			this.setProcedure("dbo.get_cliente_info(?, ?)");
			this.setParameter(1, ganador.getDniCliente());
			this.setParameter(2, ganador.getIdConcesionaria());
			ResultSet  result = this.getStatement().executeQuery();
	        while(result.next()) {
	        	ClienteBean g = new ClienteBean();
	        	g.setDniCliente(result.getString("dni_cliente"));
	        	g.setEmailCliente(result.getString("email"));
	        	g.setNomCliente(result.getString("apellido_nombre"));
	        	ganadores.add(g);
	        }
			this.disconnect();			
		}
		else{
			ganadores = new LinkedList<Bean>();
			
			this.setProcedure("dbo.get_ganadores()");
			
			ResultSet result = this.getStatement().executeQuery();
			
			while (result.next()){
				ParticipanteBean participante = new ParticipanteBean();
				participante.setIdPlan(result.getString("id_plan"));
				participante.setDniCliente(result.getString("dni_cliente"));
				participante.setIdConcesionaria(result.getString("id_concesionaria"));
				participante.setIdSorteo(result.getString("id_sorteo"));
				participante.setApellidoNombre(result.getString("apellido_nombre"));
				participante.setIdPlan(result.getString("id_plan"));
				participante.setFechaSorteo(result.getString("fecha_sorteo"));
				participante.setEmail(result.getString("email"));
				ganadores.add(participante);
			}
			
			this.disconnect();
		}
		
		return ganadores;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
