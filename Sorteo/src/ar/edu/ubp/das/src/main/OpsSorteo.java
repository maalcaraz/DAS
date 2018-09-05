package ar.edu.ubp.das.src.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import ar.edu.ubp.das.src.beans.AdquiridoBean;
import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoFactory;
import ar.edu.ubp.das.src.sorteos.daos.MSConcesionariaDao;
import ar.edu.ubp.das.src.sorteos.daos.MSGanadoresDao;

public class OpsSorteo {
	
	public OpsSorteo(){
		
	}
	
	public boolean verificarCancelado(){
		
		//Accion de verificar cancelado
				
				String restResp = "";
				boolean response = false;
				//idportal harcodeado por ahora 
				String idPortal = "PORTALGOB";
				
				try {
					MSGanadoresDao Ganadores = (MSGanadoresDao)DaoFactory.getDao("Ganadores", "ar.edu.ubp.das.src.sorteos");
					/*
					 *  Ganadores.select devuelve una lista con solo el primer elemento 
					 */
					LinkedList<Bean> listaUltimoGanador = (LinkedList<Bean>) Ganadores.select(null);
					
					/*
					String dniCliente = listaUltimoGanador.get(0).getItem("dni_cliente");
					String idPlan = listaUltimoGanador.get(0).getItem("id_plan");
					String idConcesionaria = listaUltimoGanador.get(0).getItem("id_concesionaria");
					*/
					
					AdquiridoBean adqBean = (AdquiridoBean) listaUltimoGanador.get(0);
					
					List <NameValuePair> parameters = new ArrayList <NameValuePair>();
					parameters.add(new BasicNameValuePair("id_portal" , idPortal));
					parameters.add(new BasicNameValuePair("dni_cliente" , adqBean.getDniCliente()));
			      	parameters.add(new BasicNameValuePair("id_plan" , adqBean.getIdPlan()));
					
					MSConcesionariaDao Concesionaria = (MSConcesionariaDao)DaoFactory.getDao("Concesionaria", "ar.edu.ubp.das.src.sorteos");
					List<Bean> listadoConcesionarias = Concesionaria.select(null);
					
					String concAdqBean = adqBean.getIdConcesionaria();
					
					for (Bean c : listadoConcesionarias ){
						ConcesionariaBean concesionaria = (ConcesionariaBean) c;
						
						// aca tengo que preguntar por idconcesionaria en el if pero adq bean no lo tiene!!!
						
						if (concesionaria.getIdConcesionaria().equals(concAdqBean)){
							System.out.println("Verificando cancelado en la concesionaria " + concAdqBean);
							restResp = concesionaria.getWebService().Consumir("verificarCancelado", parameters);
						}

					}
					
					if(restResp.equals("{Cancelado: SI}")){
						response = true;
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}

		
		return response;
	}
	
	

}
