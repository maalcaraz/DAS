package ar.edu.ubp.das.src.main;

import java.util.LinkedList;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ClienteBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.CuotaBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.beans.PlanBean;
import ar.edu.ubp.das.src.beans.TransaccionBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSConcesionariaDao;

public class Main {

	public static void main (String[] args){
		try {
			MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "sorteos");
			List<Bean> listadoConcesionarias = Concesionaria.select(null);
			
			Gson gson = new Gson();
			
			for (Bean c : listadoConcesionarias ){
				ConcesionariaBean concesionaria = (ConcesionariaBean) c;
				int ultimaActualizacion = Integer.parseInt(concesionaria.getUltimaActualizacion());
				
				LinkedList<ClienteBean> clientes;
				LinkedList<PlanBean> planes;
				LinkedList<AdquiridoBean> adquiridos;
				LinkedList<CuotaBean> cuotas;
				
				if ( ultimaActualizacion > 15){
					try {
						java.util.Date utilDate = new java.util.Date(); //fecha actual
						long lnMilisegundos = utilDate.getTime();
						java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
						concesionaria.setUltimaActualizacion(sqlTimestamp.toString());
						// llamar a consultaQuincenal
						String restResp = concesionaria.getWebService().Consumir("getClientes", null);
					
						
						TransaccionBean transaccion = gson.fromJson(restResp, new TypeToken<TransaccionBean>(){}.getType());
						
						String listaRetorno[] = transaccion.getMensajeRespuesta().split("],");
						/*Listado de Clientes*/
						String strClientes = listaRetorno[0] + "]";
						clientes = gson.fromJson(strClientes, new TypeToken<LinkedList<ClienteBean>>(){}.getType() );
						/*Listado de Planes*/
						String strPlanes = listaRetorno[1] + "]";
						planes = gson.fromJson(strPlanes, new TypeToken<LinkedList<PlanBean>>(){}.getType() );
						/*Listado de Aquiridos*/
						String strAdquiridos = listaRetorno[2] + "]";
						adquiridos = gson.fromJson(strAdquiridos, new TypeToken<LinkedList<AdquiridoBean>>(){}.getType() );
						/*Listado de Cuotas*/
						String strCuotas = listaRetorno[3];
						cuotas = gson.fromJson(strCuotas, new TypeToken<LinkedList<CuotaBean>>(){}.getType() );
						// Concesionaria.update(); //ingresar los datos traidos del servicio a la bd local 
					 }
		 			catch (Exception ex)
		 			{
		 			 	//Guardar sorteo como pendiente (ex.getMessage());
		 			   //return;
		 				System.out.println("El presente sorteo se guarda como pendiente");
		 			}
					
		 			
				}
				else{
					/*No hace falta actualizar los datos*/
					Concesionaria.select(c);
				}
				/*Por ahora, los participantes son todos*/
			//	List<ParticipanteBean> participantes = verificarParticipantes(clientes, planes, adquiridos, cuotas);
				
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
				
		System.out.println("Hola mundo");
	}
	void registrarPendiente (String idSorteo, String razon){
		// MSSorteosDao Sorteos = new MSSorteosDao();
		// Sorteos
	}
	
	static List<ParticipanteBean> verificarParticipantes (LinkedList<ClienteBean> clientes, LinkedList<PlanBean> planes, LinkedList<AdquiridoBean> adquiridos, LinkedList<CuotaBean> cuotas){
		/*En esta funcion se deberian calcular los clientes que pueden participar del sorteo*/
		return null;
	}
	
}
