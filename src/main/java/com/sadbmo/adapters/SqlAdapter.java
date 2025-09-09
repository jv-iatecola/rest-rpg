package com.sadbmo.adapters;

import java.util.List;

public abstract class SqlAdapter {
    public abstract void callProcedure(String sql, List<Object> params) throws Exception;
}
