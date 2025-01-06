package org.example.Repositories;

import org.example.Characters.PlayerCharacter.PlayerCharacter;

import java.util.List;

public interface PlayerCharacterRepository {

    List<PlayerCharacter> fetchAllCharacters();

    PlayerCharacter fetchCharacterByIndex(int index);

    void saveOrUpdate(PlayerCharacter playerCharacter);

}






