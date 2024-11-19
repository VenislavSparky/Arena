package org.example;

import org.example.Arenas.Arena;
import org.example.Arenas.Arena1v1;
import org.example.Characters.Heros.Hero;
import org.example.Characters.Heros.HeroFactory;
import org.example.Shops.Market;
import org.example.Shops.Shop;
import org.example.Trainers.ClassTrainer;
import org.example.Trainers.Trainer;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Select existing Hero!");
        //TODO Select from DB
        System.out.println("2.Create new Hero!");
        //TODO Select new Hero Class
        Hero hero = HeroFactory.createHero("Warrior");


        boolean exit = false;

        while (!exit) {
            System.out.println("Select option:");
            System.out.println("1.Arena");
            System.out.println("2.Shop");
            System.out.println("3.Class Trainer");
            System.out.println("4.Exit");

            int selectedOption = Integer.parseInt(scanner.nextLine());

            switch (selectedOption) {
                case 1:
                    Arena arena = new Arena1v1();
                    arena.startBattle(hero);
                    break;
                case 2:
                    Shop shop = new Market();
                    break;
                case 3:
                    Trainer trainer = new ClassTrainer();
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