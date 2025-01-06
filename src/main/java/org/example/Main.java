package org.example;


import org.example.Characters.PlayerCharacter.PlayerCharacter;

import org.example.Utils.EntityManagerFactoryUtil;

public class Main {
    public static void main(String[] args) {
        PlayerCharacter playerCharacter = GameMenu.initializeCharacter();
        GameMenu.getMainMenu(playerCharacter);

        EntityManagerFactoryUtil.closeFactory();

    }
}