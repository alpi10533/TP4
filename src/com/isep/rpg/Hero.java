package com.isep.rpg;

import java.util.ArrayList;

/**
 * Abstract Class Hero
 */
abstract public class Hero {

  //
  // Fields
  //
  private final int type;
  private final String name;
  private int lifePoints;
  private int armor;
  private int weaponDamages;
  private ArrayList<Potion> potions;
  private ArrayList<Food> foods;

  //
  // Constructors
  //
  public Hero (int type, String name, int lifePoints, int armor, int weaponDamages, ArrayList<Potion> potions, ArrayList<Food> foods) {
    this.type = type; // 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior
    this.name = name;
    this.lifePoints = lifePoints;
    this.armor = armor;
    this.weaponDamages = weaponDamages;
    this.potions = potions;
    this.foods = foods;
  }
  
  //
  // Methods
  //
  public String getName() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public int getArmor() {
    return armor;
  }

  public int getWeaponDamages() {
    return weaponDamages;
  }

  public ArrayList<Potion> getPotions() {
    return potions;
  }

  public ArrayList<Food> getFoods() {
    return foods;
  }

  public void eat(){
    lifePoints = lifePoints + 5;
    int index = foods.size() - 1;
    foods.remove(index);
  }

  public void heal(){
    lifePoints = lifePoints + 10;
    int index = potions.size() - 1;
    potions.remove(index);
  }

  public void upgradeArmor(){
    armor = armor + 20;
  }

  public void upgradeWeapon(){
    weaponDamages = weaponDamages + 10;
  }

  public void addRessources(){
    potions.add(new Potion());
    foods.add(new Food());
  }

  public void deleteLifePoints(int lifePoints) {
    if (armor != 0 && armor < lifePoints){
      this.lifePoints = this.lifePoints - (lifePoints - armor);
    } else if (this.armor == 0){
      this.lifePoints = this.lifePoints - lifePoints;
    }
  }

}
