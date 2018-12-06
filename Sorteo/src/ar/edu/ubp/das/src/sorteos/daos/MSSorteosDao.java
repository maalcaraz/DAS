package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.src.beans.SorteoBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;

public class MSSorteosDao extends DaoImpl{

	@Override
	public Bean make(ResultSet result) throws SQLException {
		
		return null;
	}

	@Override
	public void insert(Bean form) throws SQLException {
		/*
		String idSorteo = form.getItem("idSorteo");
		String fechaSorteo = form.getItem("fechaSorteo");
		String fechaProximo = form.getItem("fechaProximo");
		
		this.connect();
		this.setProcedure("dbo.insertar_sorteo(?, ?, ?)");
		this.setParameter(1, idSorteo);
		this.setParameter(2, fechaSorteo);
		this.setParameter(3, fechaProximo);
		this.executeUpdate();
		this.disconnect();*/
	}

	@Override
	public void update(Bean form) throws SQLException {
		
		SorteoBean sorteo = (SorteoBean) form;
		this.connect();
		this.setProcedure("dbo.actualizar_sorteo(?, ?, ?, ?)");
		
		
		
		try{
		
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fechaAuxSorteado;
			java.util.Date fechaAuxEjecucion;
			fechaAuxSorteado = parser.parse(sorteo.getFechaSorteado());
			fechaAuxEjecucion = parser.parse(sorteo.getFechaEjecucion());
			
			
			java.sql.Date fechaSorteado = new java.sql.Date(fechaAuxSorteado.getTime());
			java.sql.Date fechaEjecucion = new java.sql.Date(fechaAuxEjecucion.getTime());
			

			System.out.println("[SorteoDAO]Datos a actualizar: idSorteo="+sorteo.getIdSorteo() + " - FechaSorteado="+ sorteo.getFechaSorteado());
			this.setParameter(1, sorteo.getIdSorteo());
			this.setParameter(2, fechaSorteado);
			this.setParameter(3, sorteo.getPendiente());
			this.setParameter(4, fechaEjecucion);
			
		}
		catch(ParseException e){
			System.out.println("[SorteoDAO]Error parseando fecha: "+e.getMessage());
		}
		
		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public void delete(Bean form) throws SQLException {
		
		
	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException { // Para traer los pendientes
		List<Bean> ret = new LinkedList<Bean>();
		
		this.connect();
		this.setProcedure("dbo.get_sorteos_pendientes", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				SorteoBean f = new SorteoBean();
				f.setIdSorteo(result.getString("id_sorteo"));
				f.setFechaSorteado(result.getString("fecha_sorteo"));
				f.setFechaEjecucion(result.getString("fecha_ejecucion"));
				f.setPendiente(result.getString("pendiente"));
				ret.add(f);
			}
			catch(Exception ex){
				System.out.println("[MSSorteosDao]Error en obtencion de sorteos pendientes: "+ex.getMessage());
			}
			result.next();
		}
		this.disconnect();
		return ret;
	}
	
	public LinkedList<Bean> obtenerSorteoActual() throws SQLException {
		LinkedList<Bean> ret = new LinkedList<Bean>();
		
		this.connect();
		this.setProcedure("dbo.hoy_es_fecha_de_sorteo", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				SorteoBean f = new SorteoBean();
				f.setIdSorteo(result.getString("id_sorteo"));
				f.setFechaSorteado(result.getString("fecha_sorteo"));
				f.setFechaEjecucion(result.getString("fecha_ejecucion"));
				ret.add(f);
			}
			catch(Exception ex){
				System.out.println("[MSSorteosDao]Error en hoy_es_fecha_de_sorteo: "+ex.getMessage());
			}
			result.next();
		}
		this.disconnect();
		return ret;
	}
	

	@Override
	public boolean valid(Bean form) throws SQLException {
		
		return false;
	}
}
