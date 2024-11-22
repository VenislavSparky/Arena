package org.example;

import org.example.Abilities.AbilityRegistry;
import org.example.Arenas.Arena;
import org.example.Arenas.Arena1v1;
import org.example.Characters.ClassType;
import org.example.Characters.Heros.Hero;
import org.example.Characters.Heros.HeroFactory;
import org.example.Shops.Market;
import org.example.Shops.Shop;
import org.example.Trainers.ClassTrainer;
import org.example.Trainers.Trainer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = null;
        boolean exit = false;
        int selectedOption;
        AbilityRegistry.initialize();

        while (!exit) {

            if (hero == null) {
                System.out.println("1.Select existing Hero!\n2.Create new Hero!\n3.Exit Game");
                selectedOption = Integer.parseInt(scanner.nextLine());
                switch (selectedOption) {
                    case 1:
                        //TODO SELECT FROM DB
                        break;
                    case 2:
                        System.out.println("Select hero class!");
                        HeroFactory.listAvailableClasses();
                        String selectedHero = scanner.nextLine();

                        Optional<ClassType> first = Arrays.stream(ClassType.values())
                                .filter(c -> c.name().equals(selectedHero.toUpperCase()))
                                .findFirst();
                        //TODO CHECK HERO SELECTION FOR INVALID CLASSES
                        hero = HeroFactory.createHero(first.orElse(ClassType.WARRIOR));
                        System.out.println("Your hero's class is " + hero.getClass().getSimpleName());
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid Option!");
                }
            }


            if (hero!= null){
                System.out.println("Enter:\n1.Arena\n2.Shop\n3.Trainer\n4.Exit Game\n");

                selectedOption = Integer.parseInt(scanner.nextLine());

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
                        trainer.listAvailableAbilities(hero);
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

}