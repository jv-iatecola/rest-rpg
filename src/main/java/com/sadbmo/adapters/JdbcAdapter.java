package com.sadbmo.adapters;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;


public class JdbcAdapter extends SqlAdapter{
    private final Connection connection;

    public JdbcAdapter() throws Exception{
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bs", "postgres", "");
    }

    @Override
    public void callProcedure(String sql, List<Object> params) throws Exception {
        CallableStatement callableStatement = this.connection.prepareCall(sql);
        for (int index = 0; index < params.size(); index++) {
            callableStatement.setObject(index + 1, params.get(index));
        }
        callableStatement.execute();
    }
}
