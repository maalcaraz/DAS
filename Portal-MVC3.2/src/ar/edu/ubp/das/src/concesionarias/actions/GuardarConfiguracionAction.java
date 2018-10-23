package ar.edu.ubp.das.src.concesionarias.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class GuardarConfiguracionAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		/* Datos del form */
		MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "concesionarias");
		try{
			ConcesionariaForm nuevaConfiguracion = new ConcesionariaForm(request.getParameter("tipoServicio"));
			
			nuevaConfiguracion.setItem("operacion", "configurar");
			nuevaConfiguracion.setIdConcesionaria((String)request.getAttribute("idConcesionaria"));
			nuevaConfiguracion.setCodTecnologia(request.getParameter("tipoServicio"));
			nuevaConfiguracion.getWebService().setUrl(request.getParameter("url"));
			nuevaConfiguracion.setCuit(request.getParameter("cuit"));
			nuevaConfiguracion.setEmail(request.getParameter("email"));
			nuevaConfiguracion.setDireccion(request.getParameter("direccion"));
			nuevaConfiguracion.setTelefono(request.getParameter("telefono"));
			
			Concesionaria.update(nuevaConfiguracion);
		}
		catch (Exception ex){
			System.out.println("[GuardarConf]Error guardando nueva configuracion: "+ex.getMessage());
		}
		return mapping.getForwardByName("success");
	}

}
