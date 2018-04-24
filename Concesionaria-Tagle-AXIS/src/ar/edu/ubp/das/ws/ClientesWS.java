package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ar.edu.ubp.das.daos.MSClientesDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.ClienteBean;

public class ClientesWS {

	public List<ClienteBean> getCuentasClientes() throws Exception {
		
		List<Bean> cts = new LinkedList<Bean>();
		List<ClienteBean> cuentas = new LinkedList<ClienteBean>(); 
		try {
			MSClientesDao dao = (MSClientesDao)DaoFactory.getDao( "Clientes", "ar.edu.ubp.das" );
				
			cts = dao.select();
			
			for (Bean b : cts){
				cuentas.add((ClienteBean) b);
			}
			return cuentas;
		} 
		catch ( SQLException error ) {
			throw new Exception(error.getMessage());
		}
	}
}