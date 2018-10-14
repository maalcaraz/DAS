package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        this.connect();
        try {
        	
        	java.util.Date fechaAux = parser.parse(sorteo.getFechaSorteado());
        	java.sql.Date fecha = new java.sql.Date(fechaAux.getTime());
        	
        	
    		String idSorteo = sorteo.getIdSorteo();
    		
    		String fechaProximo = sorteo.getFechaProximo();
    		
    		this.setProcedure("dbo.insertar_sorteo(?, ?, ?, ?, ?)");
    		this.setParameter(1, idSorteo);
    		this.setParameter(2, fecha);
    		this.setParameter(3, fechaProximo);
    		this.setNull(4, java.sql.Types.CHAR);
    		this.setParameter(5, "Una descripcion");
    		
        }
        catch(ParseException ex){
        	
        }
        this.executeUpdate();
		this.disconnect();
		
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		
		
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		
		
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
				f.setFechaSorteado(result.getString("fecha_sorteo"));
				f.setFechaProximo(result.getString("fecha_proximo"));
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
