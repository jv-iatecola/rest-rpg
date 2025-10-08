package com.sadbmo.repositories;

import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.dtos.NewCharacterDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepository {
    SqlAdapter dbAdapter;

    public CharacterRepository(SqlAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public void addCharacter(NewCharacterDto characterDto) throws Exception {
        List<Object> params = new ArrayList<>();
        params.add(characterDto.characterName);
        params.add(characterDto.characterClass);

        this.dbAdapter.callProcedure("CALL add_character(?, ?)", params);
    }

    public int getCharacterId(String characterName) throws Exception{
        List<Object> params = new ArrayList<>();
        params.add(characterName);

        List<Integer> result = this.dbAdapter.callFunction("SELECT get_character_id(?)", params, resultSet -> {
            try {
                return resultSet.getInt(1);
            } catch (SQLException error) {
                throw new RuntimeException(error);
            }
        });
        return result.getFirst();
    }
}
