package com.isep.rpg;

import java.util.ArrayList;

/**
 * Class Warrior
 */
public class Warrior extends SpellCaster {

  //
  // Fields
  //

  //
  // Constructors
  //
  public Warrior (String name) {
    super(4, name, 70, 10, 20, new ArrayList<Potion>(), new ArrayList<Food>());
  }
  
  //
  // Methods
  //

}
