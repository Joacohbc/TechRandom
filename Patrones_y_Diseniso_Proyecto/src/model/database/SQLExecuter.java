package model.database;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SQLExecuter {
    
    private String sql;
    private List<Object> params;

    public static SQLExecuter make(String sql) {
        return new SQLExecuter(sql);
    }

    private SQLExecuter(String sql) {
        this.sql = sql;
        this.params = new LinkedList<Object>();
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
}
