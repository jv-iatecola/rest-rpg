package com.sadbmo.repositories;

import com.sadbmo.adapters.SqlAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExploreRepository {
    SqlAdapter dbAdapter;

    public ExploreRepository (SqlAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public String getGameMode(int gameId) throws Exception{
        List<Object> params = new ArrayList<>();
        params.add(gameId);

        List<String> result = this.dbAdapter.callFunction("SELECT get_game_mode(?)", params, resultSet -> {
            try {
                return resultSet.getString(1);
            } catch (SQLException error) {
                throw new RuntimeException(error);
            }
        });
        return result.getFirst();
    }

}
