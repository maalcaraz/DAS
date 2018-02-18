package ar.edu.ubp.das.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ar.edu.ubp.das.daos.MSCuentasDao;
import ar.edu.ubp.das.db.Bean;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.src.beans.CuentaBean;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "CuentasWSPort", serviceName = "CuentasWSService")
public class CuentasWS {

	@WebMethod(operationName = "getCuentasClientes", action = "urn:GetCuentasClientes")
	public List<CuentaBean> getCuentasClientes() throws Exception {
		
		List<Bean> cts = new LinkedList<Bean>();
		List<CuentaBean> cuentas = new LinkedList<CuentaBean>(); 
		try {
			MSCuentasDao dao = (MSCuentasDao)DaoFactory.getDao( "Cuentas", "ar.edu.ubp.das" );
				
			cts = dao.select();
			
			for (Bean b : cts){
				cuentas.add((CuentaBean) b);
			}
			return cuentas;
		} 
		catch ( SQLException error ) {
			throw new Exception(error.getMessage());
		}
	}
}