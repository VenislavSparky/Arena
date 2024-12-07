package org.example.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("arena");


    private EntityManagerFactoryUtil() {}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}