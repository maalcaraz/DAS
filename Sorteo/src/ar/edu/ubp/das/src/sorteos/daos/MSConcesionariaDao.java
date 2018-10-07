package ar.edu.ubp.das.src.sorteos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.src.beans.ConcesionariaBean;
import ar.edu.ubp.das.src.beans.ParticipanteBean;
import ar.edu.ubp.das.src.db.Bean;
import ar.edu.ubp.das.src.db.DaoImpl;

public class MSConcesionariaDao extends DaoImpl{

	@Override
	public Bean make(ResultSet result) throws SQLException {
		return null;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		// Hacer un if, preguntar por el name e insertar transaccion o concesionaria.
				ConcesionariaBean c = (ConcesionariaBean) bean;
				this.connect();
				this.setProcedure("dbo.insertar_concesionaria(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				this.setParameter(1, c.getIdConcesionaria());
				this.setParameter(2, c.getNomConcesionaria());
				this.setParameter(3, c.getCuit());
				this.setParameter(4, c.getEmail());
				this.setParameter(5, c.getDireccion());
				this.setParameter(6, c.getTelefono());
				this.setParameter(7, c.getCantDiasCaducidad());
				this.setParameter(8, c.getWebService().getUrl());
				this.setParameter(9, c.getCodTecnologia());
				this.setParameter(10, c.getAprobada());
				this.executeUpdate();
				this.disconnect();
	}

	@Override
	public void update(Bean bean) throws SQLException {
		/*Aca en el update, no olvidar que hay que insertar e nconcesionaria la fecha de actualizacion
		 * de datos qu viene en la transaccion*/
		
	}

	@Override
	public void delete(Bean bean) throws SQLException {
		
		
	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		List<Bean> ret = new LinkedList<Bean>();
		this.connect();
		/* En este caso no las vamos a querer separar. 
		 * Hay que poner en cada una de las concesionarias la lista de clientes*/
		
		if (bean == null){
			// Devuelve una lista de concesionarias
			System.out.println("[ConcDAO]Buscando en la base la lista de concesionarias registradas");
			this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = this.getStatement().executeQuery();
			result.next();
			while(result.getRow() > 0) {
				try{
					ConcesionariaBean f = new ConcesionariaBean(result.getString("cod_tecnologia"));
					f.setIdConcesionaria(result.getString("id_concesionaria"));
					f.setNomConcesionaria(result.getString("nombre_concesionaria"));
					f.getWebService().setUrl(result.getString("url_servicio"));
					f.setCodTecnologia(result.getString("cod_tecnologia"));
					f.setUltimaActualizacion(result.getString("ultima_actualizacion"));
					f.setAprobada(result.getString("aprobada"));
					ret.add(f);
					System.out.println("[ConcDAO]Insertando en la lista de concesionarias ->id: "+f.getIdConcesionaria());
				}
				catch(Exception ex){
					System.out.println("[ConcDAO]Error en la consulta de concesionarias registradas. Mensaje: "+ex.getMessage());
				}
				result.next();
			}
		}
		else  {
			
			System.out.println("[ConcDAO]Buscando en la base la lista de participantes del sorteo");
			ConcesionariaBean concesionaria = (ConcesionariaBean) bean;
			this.setProcedure("dbo.get_participantes(?, ?, ?)");
			this.setParameter(1, concesionaria.getIdConcesionaria());
			this.setParameter(2, 26);
			this.setParameter(3, 40);
			ResultSet result = this.getStatement().executeQuery();
			result.next();
			while(result.getRow() > 0) {
				try{
					ParticipanteBean cli = new ParticipanteBean();
					cli.setIdConcesionaria(result.getString("id_concesionaria"));
					cli.setDniCliente(result.getString("dni_cliente"));
					ret.add(cli);
				}
				catch(Exception ex){
					System.out.println(ex);
				}
				result.next();
			}
		}
		this.disconnect();
		return ret;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		
		return false;
	}

}
