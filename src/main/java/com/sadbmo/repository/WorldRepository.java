package com.sadbmo.repository;

import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.dtos.NewWorldDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorldRepository {
    SqlAdapter dbAdapter;

    public WorldRepository(SqlAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public void addWorld(NewWorldDto worldDto) throws Exception {
        List<Object> params = new ArrayList<>();
        params.add(worldDto.gameMode);
        params.add(worldDto.characterUuid);

        this.dbAdapter.callProcedure("CALL add_world(?, ?)", params);
    }

    public int getWorldId(int characterUuid) throws Exception{
        List<Object> params = new ArrayList<>();
        params.add(characterUuid);

        List<Integer> result = this.dbAdapter.callFunction("SELECT get_world_id(?)", params, resultSet -> {
            try {
                return resultSet.getInt(1);
            } catch (SQLException error) {
                throw new RuntimeException(error);
            }
        });
        return result.getFirst();
    }
}
