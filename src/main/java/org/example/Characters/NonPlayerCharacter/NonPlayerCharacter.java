package org.example.Characters.NonPlayerCharacter;

import org.example.Abilities.Ability;
import org.example.Characters.GameCharacter;
import org.example.Utils.TextColorUtil;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public abstract class NonPlayerCharacter extends GameCharacter {

    @Override
    public boolean performActions(GameCharacter gameCharacter, List<GameCharacter> heroes, List<GameCharacter> monsters) {
        chooseAbility(gameCharacter, monsters, heroes);
        gameCharacter.endTurn();
        return true;

    }

    @Override
    public void chooseAbility(GameCharacter user, List<GameCharacter> heroes, List<GameCharacter> monsters) {
        //TODO SELECT RANDOM VALID ABILITY

        List<GameCharacter> enemies = getEnemies(heroes, monsters);
        List<GameCharacter> allies = getAllies(heroes, monsters);


        Ability ability = getAbilities().get(ThreadLocalRandom.current().nextInt(0, getAbilities().size()));
        int energyCost = ability.getEnergyCost();
        if (hasEnoughEnergy(energyCost)) {
            System.out.println(user.getClass().getSimpleName() + " used " + ability.getClass().getSimpleName());
            ability.use(user, allies, enemies);
            consumeEnergy(energyCost);
        } else {
            System.out.println(TextColorUtil.toRed("Not enough Action Points"));
        }
    }

    abstract List<GameCharacter> getEnemies(List<GameCharacter> heroes, List<GameCharacter> monsters);

    abstract List<GameCharacter> getAllies(List<GameCharacter> heroes, List<GameCharacter> monsters);


}
