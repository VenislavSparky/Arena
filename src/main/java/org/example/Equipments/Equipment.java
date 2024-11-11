package org.example.Equipments;

public class Equipment {

    private Slot itemType;
    private int attackPowerStat;
    private int armorStat;
    private int intellectStat;

    public Equipment(Slot itemType, int attackPowerStat, int armorStat, int intellectStat) {
        this.itemType = itemType;
        this.attackPowerStat = attackPowerStat;
        this.armorStat = armorStat;
        this.intellectStat = intellectStat;
    }

    public Slot getItemType() {
        return itemType;
    }

    public void setItemType(Slot itemType) {
        this.itemType = itemType;
    }

    public int getAttackPowerStat() {
        return attackPowerStat;
    }

    public void setAttackPowerStat(int attackPowerStat) {
        this.attackPowerStat = attackPowerStat;
    }

    public int getArmorStat() {
        return armorStat;
    }

    public void setArmorStat(int armorStat) {
        this.armorStat = armorStat;
    }

    public int getIntellectStat() {
        return intellectStat;
    }

    public void setIntellectStat(int intellectStat) {
        this.intellectStat = intellectStat;
    }
}
