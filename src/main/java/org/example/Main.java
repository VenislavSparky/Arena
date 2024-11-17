package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Arena.ArenaManager;
import org.example.Characters.Character;
import org.example.Characters.Heros.Hero;
import org.example.Characters.Heros.HeroFactory;
import org.example.Characters.Monsters.Monster;
import org.example.Characters.Monsters.MonsterFactory;
import org.example.Characters.Monsters.Skeleton;
import org.example.Characters.Monsters.Troll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println("1.Select existing Hero!");
        //TODO Select from DB
        System.out.println("2.Create new Hero!");
        //TODO Select new Hero Class
        Hero hero = HeroFactory.createHero("Warrior");


        boolean exit = false;

        while (exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select option:");
            System.out.println("1.Arena");
            System.out.println("2.Shop");
            System.out.println("3.Class Trainer");
            System.out.println("4.Exit");

            int selectedOption = Integer.parseInt(scanner.nextLine());

            switch (selectedOption) {
                case 1:
                    ArenaManager arena = new ArenaManager();
                    arena.startBattle(hero);
                break;
                case 2:
                    //TODO Shop
                    break;
                case 3:
                    //TODO Class Trainer
                    break;
                case 4:
                    exit = true;
                break;
                default:
                    System.out.println("Invalid Option!");
            }


        }

    }
}