package ar.edu.ubp.das.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

    public Bean make(ResultSet result) throws SQLException;
    public void insert(Bean bean) throws SQLException;
    public void insert(String textoNovedad) throws SQLException;
    public void update(Bean bean) throws SQLException;
    public void delete(Bean bean) throws SQLException;
    public List<List<Bean>> selectListBeans() throws SQLException;
    public List<Bean> select() throws SQLException;
    public boolean valid(Bean bean) throws SQLException;
	public boolean valid(Bean form1, Bean form2) throws SQLException;

}
