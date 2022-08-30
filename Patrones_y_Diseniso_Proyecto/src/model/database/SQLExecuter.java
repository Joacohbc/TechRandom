package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SQLExecuter {
    
	// 2 Atributos
    private String sql; // Obligatorio 
    private List<Object> params; // Opcionales
    
    // Constructores para los posibles casos de uso
    
    // Solo una sentencia (opcionalmente agregar mas atributos)
    private SQLExecuter(String sql) {
        this.sql = sql;
        this.params = new LinkedList<Object>();
    }
    
    private SQLExecuter(String sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    private SQLExecuter(String sql, Object... params) {
        this.sql = sql;
        this.params = new LinkedList<>(Arrays.asList(params));
    }

    
    // Retorna un Objeto de la clase
    public static SQLExecuter make(String sql) {
        return new SQLExecuter(sql);
    }

    public static SQLExecuter make(String sql, List<Object> params) {
        return new SQLExecuter(sql, params);
    }
    
    public static SQLExecuter make(String sql, Object... params) {
        return new SQLExecuter(sql, params);
    }
    
    public SQLExecuter add(Object param) {
        params.add(param);
        return this;
    }

    public int executeUpdate() throws SQLException {
        return BDUtils.Excute(sql, params.toArray());
    }

    public List<Map<String, Object>> queryMap() throws SQLException {
        return BDUtils.QueryMap(sql, params.toArray());
    }

    public Map<String, Object> queryOneMap() throws SQLException {
        return BDUtils.QueryOneMap(sql, params.toArray());
    }

    public List<List<Object>> queryList() throws SQLException {
        return BDUtils.QueryList(sql, params.toArray());
    }

    public List<Object> queryOneList() throws SQLException {
        return BDUtils.QueryOneList(sql, params.toArray());
    }

    public boolean queryExists() throws SQLException {
        return BDUtils.QueryExists(sql, params.toArray());
    }

    public ResultSet queryResultSet() throws SQLException {
        return BDUtils.QueryResultSet(sql, params.toArray());
    }
}
