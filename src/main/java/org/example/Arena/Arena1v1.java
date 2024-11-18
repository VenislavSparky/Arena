package org.example.Arena;

import org.example.Characters.Character;
import org.example.Characters.Heros.Hero;
import org.example.Characters.Monsters.Monster;
import org.example.Characters.Monsters.MonsterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arena1v1 implements Arena{

    List<Character> characters;
    List<Character> heroes;
    List<Character> monsters;
    int currentCharacterTurn;

    public Arena1v1() {
        characters = new ArrayList<>();
        heroes = new ArrayList<>();
        monsters = new ArrayList<>();
        currentCharacterTurn = 0;
    }

    public void startBattle(Hero hero) {
        Scanner scanner = new Scanner(System.in);

        loadBattle(hero);

        while (!isBattleOver()) {
            Character character = characters.get(currentCharacterTurn);

            boolean endTurn = false;
            while (character.getCurrentActionPoints() == 0 || endTurn) {

                System.out.println("1.Chose ability:");
                System.out.println("2.End turn:");
                int selectedOption = Integer.parseInt(scanner.nextLine());

                switch (selectedOption) {
                    case 1:
                        if (character instanceof Hero) {
                            character.selectAbility(character, heroes, monsters);
                        } else if (character instanceof Monster) {
                            character.selectAbility(character, monsters, heroes);
                        }
                        break;
                    case 2:
                        endTurn = true;
                        break;
                    default:
                        System.out.println("Invalid Option!");
                }

            }

            removeDead();

            nextTurn();

        }

    }

    private boolean isBattleOver() {
        if (monsters.isEmpty()) {
            System.out.println("Heroes won!");
            return true;
        } else if (heroes.isEmpty()) {
            System.out.println("Monsters won!");
            return true;
        }
        return false;
    }


    private void nextTurn() {
        currentCharacterTurn = (currentCharacterTurn + 1) % characters.size(); // Loop back to the first character
    }


    public void loadBattle(Hero hero) {
        characters.add(hero);
        heroes.add(hero);

        Monster monster = MonsterFactory.createMonster();
        characters.add(monster);
        monsters.add(monster);
    }

    private void removeDead() {
        characters.removeIf(Character::isDead);
        monsters.removeIf(Character::isDead);
        heroes.removeIf(Character::isDead);
    }


}
