package com.sadbmo.adapters;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public abstract class SqlAdapter {
    public abstract void callProcedure(String sql, List<Object> params) throws Exception;

    public abstract <T> List<T> callFunction(String sql, List<Object> params, Function<ResultSet, T> mapper) throws Exception;
}
