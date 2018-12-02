package ar.edu.ubp.das.src.sorteos.actions;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSSorteosDao;
import ar.edu.ubp.das.src.sorteos.forms.SorteosForm;

public class InsertarSorteoAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		String fechaSorteo = request.getParameter("nuevaFecha");
		String fechaEjecucion =  "";
		System.out.println("FechaSorteo entra como parametro: "+fechaSorteo);
		Date hoy = new Date();
		Date nuevaFechaDate = null;
		
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date;
			date = parser.parse(fechaSorteo);
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		    String formattedDate = formatter.format(date);
		    System.out.println("[InsertarSorteoAction]Fecha actualizacion formateada: "+ formattedDate);
		    nuevaFechaDate = formatter.parse(formattedDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
        if (nuevaFechaDate.compareTo(hoy) == 0){ //si la nueva fecha es menor o igual a hoy, no se deberia insertar
        	System.out.println("Nueva Fecha Date: "+nuevaFechaDate.toString());
        	System.out.println("Hoy Date: "+hoy.toString());
        	System.out.println("[InsertarSorteoAction]La fecha que intenta insertar ya existe.");
        }
        else{
        	System.out.println("Nueva Fecha Date: "+nuevaFechaDate.toString());
        	System.out.println("Hoy Date: "+hoy.toString());
        	SorteosForm sorteo = new SorteosForm();
    		sorteo.setIdSorteo("s"+ nuevaFechaDate.hashCode());
    		sorteo.setFechaSorteado(fechaSorteo);
    		sorteo.setfechaEjecucion(fechaEjecucion);
    		
    		try {
    			MSSorteosDao Sorteos = (MSSorteosDao)DaoFactory.getDao("Sorteos", "sorteos");
    			Sorteos.insert(sorteo);
    		}
    		catch (SQLException ex){
    			System.out.println("[InsertarSorteoAction]"+ex.getMessage());
    			return mapping.getForwardByName("failure");
    		}
        }
		return mapping.getForwardByName("success");
	}

}
