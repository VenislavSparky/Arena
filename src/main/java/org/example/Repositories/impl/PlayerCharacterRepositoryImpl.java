package org.example.Repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.Characters.PlayerCharacter.PlayerCharacter;
import org.example.Exceptions.DatabaseOperationException;
import org.example.Repositories.PlayerCharacterRepository;
import org.example.Utils.EntityManagerFactoryUtil;

import java.util.List;

public class PlayerCharacterRepositoryImpl implements PlayerCharacterRepository {

    @Override
    public List<PlayerCharacter> fetchAllCharacters() {
        try (EntityManager em = EntityManagerFactoryUtil.getEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<PlayerCharacter> query = em.createQuery("SELECT c FROM PlayerCharacter c", PlayerCharacter.class);
            List<PlayerCharacter> characters = query.getResultList();
            em.getTransaction().commit();
            return characters;
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to fetch characters from the database.", e);
        }
    }

    @Override
    public PlayerCharacter fetchCharacterByIndex(int index) {
        List<PlayerCharacter> characters = fetchAllCharacters();

        if (characters.isEmpty()) {
            throw new DatabaseOperationException("No characters found in the database.");
        }

        if (index < 0 || index >= characters.size()) {
            throw new DatabaseOperationException("Invalid character index. Please select a valid character.");
        }

        PlayerCharacter playerCharacter = characters.get(index);
        playerCharacter.initCurrentStats();
        return playerCharacter;
    }

    @Override
    public void saveOrUpdate(PlayerCharacter playerCharacter) {
        try (EntityManager em = EntityManagerFactoryUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.merge(playerCharacter);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseOperationException("Failed to save or update the player character.", e);
        }
    }

}
