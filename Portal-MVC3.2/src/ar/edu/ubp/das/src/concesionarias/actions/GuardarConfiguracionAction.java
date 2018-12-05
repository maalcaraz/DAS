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
			nuevaConfiguracion.setIdConcesionaria(request.getParameter("idConcesionaria"));
			nuevaConfiguracion.setCodTecnologia(request.getParameter("tipoServicio"));
			nuevaConfiguracion.getWebService().setUrl(request.getParameter("url"));
			nuevaConfiguracion.setCuit(request.getParameter("cuit"));
			nuevaConfiguracion.setEmail(request.getParameter("emailConcesionaria"));
			nuevaConfiguracion.setDireccion(request.getParameter("dir"));
			nuevaConfiguracion.setTelefono(request.getParameter("tel"));
			System.out.println("[GuardarConfigAction]dias: "+ request.getParameter("diasCaducidad"));
			nuevaConfiguracion.setCantDiasCaducidad(request.getParameter("diasCaducidad"));
			Concesionaria.update(nuevaConfiguracion);
		}
		catch (Exception ex){
			System.out.println("[GuardarConf]Error guardando nueva configuracion: "+ex.getMessage());
		}
		return mapping.getForwardByName("success");
	}

}
