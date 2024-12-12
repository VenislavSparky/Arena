package org.example.Utils;

import org.example.Characters.CharacterClass;
import org.example.Characters.Inventory.Equipments.ItemRarity;

public class TextUtil {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String WHITE = "\u001B[38;5;15m";

    private TextUtil() {
    }

    public static String toRed(String text) {
        return RED + text + RESET;
    }

    public static String toGreen(String text) {
        return GREEN + text + RESET;
    }

    public static String toClassColor(CharacterClass characterClass, String text) {
        switch (characterClass) {
            case  WARRIOR -> {
                return "\u001B[38;5;160m" + text + RESET;
            }
            case MAGE -> {
                return "\u001B[34m" + text + RESET;
            }
            case PALADIN -> {
                return "\u001B[38;5;11m" + text + RESET;
            }
        }
        return "\u001B[0m" + text + RESET;
    }

    public static String toRarityColor(ItemRarity itemRarity, String text) {
        switch (itemRarity) {
            case COMMON -> {
                return "\u001B[38;5;189m" + text + RESET;
            }
            case UNCOMMON -> {
                return "\u001B[38;5;40m" + text + RESET;
            }
            case RARE -> {
                return "\u001B[38;5;51m" + text + RESET;
            }
            case EPIC -> {
                return "\u001B[38;5;93m" + text + RESET;
            }
            case LEGENDARY -> {
                return "\u001B[38;5;214m" + text + RESET;
            }
        }
        return "\u001B[0m" + text + RESET;
    }

    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    public static void printSeparator() {
        String text = "~".repeat(30);
        System.out.println("\u001B[48;5;7m" + text + RESET);
    }

}