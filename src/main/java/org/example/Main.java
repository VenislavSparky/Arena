package org.example;

import org.example.Characters.Heros.Hero;
import org.example.Characters.Heros.HeroFactory;
import org.example.Characters.Monsters.Monster;
import org.example.Characters.Monsters.MonsterFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select Class");

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        Hero hero = HeroFactory.createHero("Warrior");

        Monster monster = MonsterFactory.createMonster();

    }
}