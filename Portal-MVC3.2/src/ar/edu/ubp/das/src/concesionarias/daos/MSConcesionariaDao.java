package ar.edu.ubp.das.src.concesionarias.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.portal.forms.AdquiridoForm;
import ar.edu.ubp.das.portal.forms.ClienteForm;
import ar.edu.ubp.das.portal.forms.CuotaForm;
import ar.edu.ubp.das.portal.forms.PlanForm;
import ar.edu.ubp.das.portal.forms.TransaccionForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

public class MSConcesionariaDao extends DaoImpl{

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		
		form.getItem("form1");
		
		
		
	}
	
	public void insert(ConcesionariaForm form) throws SQLException {
		this.connect();
		this.setProcedure("dbo.insertar_concesionaria(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		this.setParameter(1, form.getIdConcesionaria());
		this.setParameter(2, form.getNomConcesionaria());
		this.setParameter(3, form.getCuit());
		this.setParameter(4, form.getEmail());
		this.setParameter(5, form.getDireccion());
		this.setParameter(6, form.getTelefono());
		this.setParameter(7, form.getUltimaActualizacion());
		this.setParameter(8, form.getCantDiasCaducidad());
		this.setParameter(9, form.getUrlServicio());
		this.setParameter(10, form.getCodTecnologia());
		
		this.executeUpdate();
		
		this.disconnect();
	}
	
	public void insertTransacciones(TransaccionForm transaccion) throws SQLException {
		
		this.connect();
		
		this.setProcedure("dbo.insertar_transaccion(?, ?, ?, ?, ?)");
		
		this.setParameter(1, transaccion.getId_transaccion());
		this.setParameter(2, transaccion.getIdConcesionaria());
		this.setParameter(3, transaccion.getEstadoTransaccion());
		this.setParameter(4, transaccion.getMensajeRespuesta());
		this.setParameter(5, transaccion.getHoraFechaTransaccion());
		this.executeUpdate();
		
		this.disconnect();
		
	}
	
	public void insertClientes(LinkedList<ClienteForm> clientes, String idConcesionaria) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.insertar_cliente(?, ?, ?, ?, ?, ?)");
		
		for (ClienteForm c : clientes){
			
			this.setParameter(1, c.getDniCliente());
			this.setParameter(2, idConcesionaria);
			this.setParameter(3, c.getNomCliente());
			this.setParameter(4, c.getEdad());
			this.setParameter(5, c.getDomicilio());
			this.setParameter(6, c.getEmailCliente());
			this.executeUpdate();
		}
		
		this.disconnect();
	}
	
	 public void insertAdquiridos(LinkedList<AdquiridoForm> adquiridos, String idConcesionaria) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.insertar_adquirido(?, ?, ?, ?, ?, ?, ?, ?)");
		
		for (AdquiridoForm a : adquiridos){
			
			this.setParameter(1, a.getIdPlan());
			this.setParameter(2, a.getDniCliente());
			this.setParameter(3, idConcesionaria);
			this.setParameter(4, a.getCancelado());
			this.setParameter(5, a.getGanadorSorteo());
			this.setParameter(6, a.getFechaSorteado());
			this.setParameter(7, a.getFechaEntrega());
			this.setParameter(8, a.getNroChasis());
			this.executeUpdate();
		}
		
		this.disconnect();
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
		return null;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void insertCuotas(List<CuotaForm> cuotas, String idConcesionaria) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.insertar_cuota(?, ?, ?, ?, ?, ?, ?)");
		
		for (CuotaForm cuo : cuotas){
			
			this.setParameter(1, cuo.getIdCuota());
			this.setParameter(2, cuo.getDniCliente());
			this.setParameter(3, cuo.getIdPlan());
			this.setParameter(4, idConcesionaria);
			this.setParameter(5, cuo.getImporte());
			this.setParameter(6, cuo.getFechaVencimiento());
			this.setParameter(7, cuo.getPagada());
			this.executeUpdate();
		}
		
		this.disconnect();
		
	}


	public void insertPlanes(List<PlanForm> planes) throws SQLException {
		
		this.connect();
		
		this.setProcedure("dbo.insertar_plan(?, ?, ?, ?, ?, ?)");
		
		for (PlanForm p : planes){
			
			this.setParameter(1, p.getIdPlan());
			this.setParameter(2, p.getDescripcion());
			this.setParameter(3, p.getCant_cuotas());
			this.setParameter(4, p.getEntrega_pactada());
			this.setParameter(5, p.getFinanciacion());
			this.setParameter(6, p.getDuenoPlan());
			this.executeUpdate();
		}
		
		this.disconnect();
	}


}
