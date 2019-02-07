package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class MSSorteosDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		
		SorteosForm sorteo = (SorteosForm) form;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        this.connect();
        try {
        	
        	java.util.Date fechaAux = parser.parse(sorteo.getFechaDefinida());
        	java.sql.Date fecha = new java.sql.Date(fechaAux.getTime());
        	
        	
    		String idSorteo = sorteo.getIdSorteo();
    		
    		
    		this.setProcedure("dbo.insertar_sorteo(?, ?, ?, ?)");
    		this.setParameter(1, idSorteo);
    		this.setParameter(2, fecha);
    		this.setNull(3, java.sql.Types.CHAR);
    		this.setParameter(4, "Una descripcion");
    		
        }
        catch(ParseException ex){
        	
        }
        this.executeUpdate();
		this.disconnect();
		
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		this.connect();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fechaAux;
		try {
			fechaAux = parser.parse(form.getItem("fechaSorteo"));
			java.sql.Date fecha = new java.sql.Date(fechaAux.getTime());
			this.setProcedure("dbo.editar_sorteo(?, ?, ?)");
			this.setParameter(1, form.getItem("idSorteo"));
			this.setParameter(2, fecha);
			this.setParameter(3, form.getItem("descripcion"));
		} 
		catch (ParseException e) {
			System.out.println("[SorteosDao]Error en update de sorteo: "+e.getMessage());
		}
		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		this.connect();
		this.setProcedure("dbo.eliminar_sorteo(?)");
		this.setParameter(1, form.getItem("idSorteo"));
		this.executeUpdate();
		this.disconnect();
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		List<DynaActionForm> ret = new LinkedList<DynaActionForm>();
		this.connect();
		this.setProcedure("dbo.get_sorteos", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				SorteosForm f = new SorteosForm();
				f.setIdSorteo(result.getString("id_sorteo"));
				f.setFechaDefinida(result.getString("fecha_sorteo"));
				f.setfechaEjecucion((result.getString("fecha_ejecucion") == null) ? "-" : (result.getString("fecha_ejecucion")));
				f.setFechaNotificacion((result.getString("fecha_notificacion") == null) ? "-" : (result.getString("fecha_notificacion")));
				f.setPendiente((result.getString("pendiente") == null) ? "-" : (result.getString("pendiente")));
				ret.add(f);
			}
			catch(Exception ex){
				System.out.println(ex);
			}
			result.next();
		}
		this.disconnect();
		return ret;
	}
	
	public List<DynaActionForm> obtenerResultadoUltimoSorteo() throws SQLException {
		List<DynaActionForm> ret = new LinkedList<DynaActionForm>();
		this.connect();
		this.setProcedure("dbo.get_ultimo_sorteo_ganador", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = this.getStatement().executeQuery();
		result.next();
		while(result.getRow() > 0) {
			try{
				SorteosForm f = new SorteosForm();
				f.setFechaDefinida(result.getString("fecha_sorteado"));
				f.setItem("apellidoNombre", result.getString("apellido_nombre"));
				f.setItem("nombreConcesionaria", result.getString("nombre_concesionaria"));
				ret.add(f);
			}
			catch(Exception ex){
				System.out.println(ex);
			}
			result.next();
		}
		this.disconnect();
		return ret;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		return false;
	}
}
