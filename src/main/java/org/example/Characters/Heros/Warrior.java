package org.example.Characters.Heros;

import org.example.Abilities.Ability;
import org.example.Characters.Character;

import java.util.List;

public class Warrior extends Hero {


    @Override
    public boolean selectAbility(Character user, List<Character> allies, List<Character> enemies) {
        for (Ability ability : getAbilities()) {
            ability.use(user, allies,enemies);
        }
        return true;
    }
}
