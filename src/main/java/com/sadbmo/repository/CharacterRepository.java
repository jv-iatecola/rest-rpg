package com.sadbmo.repository;

import com.sadbmo.adapters.SqlAdapter;
import com.sadbmo.dtos.NewCharacterDto;

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
}
