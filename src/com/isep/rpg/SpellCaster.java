package com.isep.rpg;

import java.util.ArrayList;

/**
 * Abstract Class SpellCaster
 */
abstract public class SpellCaster extends Hero {

    //
    // Fields
    //

    //
    // Constructors
    //
    public SpellCaster (int type, String name, int lifePoints, int armor, int weaponDamages, ArrayList<Potion> potions, ArrayList<Food> foods) {
        super(type, name, lifePoints, armor, weaponDamages, potions, foods);
    }

    //
    // Methods
    //

}

