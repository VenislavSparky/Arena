package org.example.Characters.Inventory.Equipments;

import org.example.Characters.GameCharacter;
import org.example.Characters.Stats;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EquipmentFactory {
    public static Equipment createRandomEquipment(GameCharacter gameCharacter) {

        ItemRarity rarity = getRandomRarity();
        ArmorType armorType = getArmorTypeByCharacterClass(gameCharacter);

        Stats stats = getRandomStats(getRandomBaseStatScaled(gameCharacter.getLevel(), rarity), armorType);

        Slot slot = getRandomSlot();

        String name = String.format("%s %s %s", rarity.getDisplayName(), armorType.getDisplayName(), slot.getDisplayName());


        return new Equipment(name, slot, rarity, armorType, stats);
    }

    private static Stats getRandomStats(float baseStat, ArmorType armorType) {
        return new Stats(Math.round(baseStat * armorType.getStaminaMultiplier()),
                Math.round(baseStat * armorType.getStrengthMultiplier()),
                Math.round(baseStat * armorType.getArmorMultiplier()),
                Math.round(baseStat * armorType.getIntellectMultiplier()));

    }

    private static ItemRarity getRandomRarity() {
        double roll = Math.random();
        if (roll < 0.5) return ItemRarity.COMMON;
        if (roll < 0.75) return ItemRarity.UNCOMMON;
        if (roll < 0.9) return ItemRarity.RARE;
        if (roll < 0.98) return ItemRarity.EPIC;
        return ItemRarity.LEGENDARY;

    }

    private static ArmorType getArmorTypeByCharacterClass(GameCharacter gameCharacter) {
        ArmorType armorType;
        switch (gameCharacter.getCharacterClass()) {
            case WARRIOR, PALADIN -> armorType = ArmorType.PLATE;
            case MAGE -> armorType = ArmorType.CLOTH;
            default ->
                    throw new IllegalArgumentException("Unsupported class type when trying to get armor type: " + gameCharacter.getCharacterClass());
        }
        return armorType;
    }

    public static Slot getRandomSlot() {
        Random random = new Random();
        Slot[] slots = Slot.values();
        return slots[random.nextInt(slots.length)];
    }

    private static float getRandomBaseStatScaled(int level, ItemRarity rarity) {
        float stat = ThreadLocalRandom.current().nextFloat(level + rarity.getMultiplier(), 6 + level);
        return stat * rarity.getMultiplier();
    }

}


