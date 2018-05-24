package ar.edu.ubp.das.mvc.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.portal.forms.ClienteForm;

public interface Dao {

    public DynaActionForm make(ResultSet result) throws SQLException;
    public void insert(DynaActionForm form) throws SQLException;
    public void insertClientes(List<DynaActionForm> form) throws SQLException;
    public void insertCuotas(List<DynaActionForm> form) throws SQLException;
    public void insertAdquiridos(List<DynaActionForm> form) throws SQLException;
    public void insertPlanes(List<DynaActionForm> form) throws SQLException;
    public void insertTransacciones(List<DynaActionForm> form) throws SQLException;
    public void update(DynaActionForm form) throws SQLException;
    public void delete(DynaActionForm form) throws SQLException;
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException;
    public boolean valid(DynaActionForm form) throws SQLException;
	void insertClientes(LinkedList<ClienteForm> clientes, String idConcesionaria) throws SQLException;

}
