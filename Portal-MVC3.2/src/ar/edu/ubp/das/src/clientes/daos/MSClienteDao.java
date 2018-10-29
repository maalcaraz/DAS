package ar.edu.ubp.das.src.clientes.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.portal.forms.ClienteForm;

public class MSClienteDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
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
		ClienteForm clienteForm = (ClienteForm) form;
		List<DynaActionForm> ret = new LinkedList<DynaActionForm>();
		
		this.connect();
		
		this.setProcedure("dbo.get_cliente_info(?, ?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.setParameter(1, clienteForm.getDniCliente());
		this.setParameter(2, clienteForm.getIdConcesionaria());
		
		ResultSet result = this.getStatement().executeQuery();
		ClienteForm c = new ClienteForm();
		List<AdquiridoForm> adquiridos = new LinkedList<AdquiridoForm>();
		result.next();
		while(result.getRow() > 0) {
				if (c.getDniCliente() == null){
					c.setDniCliente(result.getString("dni_cliente"));
					//no pongo id concesionaria en cliente por que puede tener muchos planes de distintas concesionarias
					c.setNomCliente(result.getString("apellido_nombre"));
					c.setEdad(result.getString("edad"));
					c.setDomicilio(result.getString("domicilio"));
					c.setEmailCliente(result.getString("email"));
					c.setItem("fecha_ult_transaccion", result.getString("ult_transaccion_gc"));
	        	}
				AdquiridoForm adq = new AdquiridoForm();
				
				adq.setIdPlan(result.getString("id_plan"));
				adq.setIdConcesionaria(result.getString("id_concesionaria"));
				//adq.setNroChasis(result.getString("nro_chasis"));
				//adq.setFechaEntrega(result.getString("fecha_entrega"));
				adq.setCancelado(result.getString("cancelado"));
				adq.setCancelado(result.getString("ganador_sorteo"));
				adq.setItem("cant_cuotas", result.getString("cant_cuotas"));
				adq.setItem("cuotas_pagas", result.getString("cuotas_pagas"));
				adq.setItem("cuotas_sin_pagar", result.getString("cuotas_sin_pagar"));
				if(!adquiridos.contains(adq)){
					adquiridos.add(adq);
				}
				
			result.next();
		}
		c.setAdquiridos(adquiridos);
		ret.add(c);
		
		this.disconnect();
		return ret;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
