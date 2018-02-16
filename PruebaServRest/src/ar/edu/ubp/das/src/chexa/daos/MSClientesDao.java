package ar.edu.ubp.das.src.chexa.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSClientesDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
        
		DynaActionForm form = new DynaActionForm();
        
    	form.setItem("dniCliente", result.getString("dni_cliente"));
    	form.setItem("nomCliente", result.getString("nom_cliente"));

        
        return form;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
    	//LinkedList<DynaActionForm> clientes = new LinkedList<DynaActionForm>();
    	
		this.connect();
		
		this.setProcedure("dbo.get_clientes()", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		//this.setParameter(1, form.getItem("solicitante"));
		
        List<DynaActionForm> clientes = this.executeQuery();
        
		this.disconnect();
		
		return clientes;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
