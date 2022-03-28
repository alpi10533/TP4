package com.isep.rpg;

import com.isep.utils.InputParser;
import java.util.ArrayList;

/**
 * Class Game
 */
public class Game {

  //
  // Fields
  //
  private ArrayList<Hero> heroes;
  private ArrayList<Enemy> enemies;
  private Hero fightingHero;
  private Enemy fightingEnemy;
  private int entityTurn;
  private InputParser inputParser;

  //
  // Constructors
  //
  public Game () {
    heroes = new ArrayList<>();
    enemies = new ArrayList<>();
    entityTurn = 0;
    inputParser = new InputParser();
  }
  
  //
  // Methods
  //
  public void playGame() {
    System.out.println("\nMini RPG lite 3000");
    createHeroes();
    generateEnemies();
    int i = 1;
    while(true) {
      check();
      if (heroes.size()>0 && enemies.size()>0) {
        System.out.print("\nCOMBAT N°" + i + " - ");
        entityTurn = (int) (Math.random() * 2);
        generateCombat();
        System.out.print(fightingHero.getName());
        System.out.print(" CONTRE ");
        System.out.println(fightingEnemy.getName());
        if (entityTurn == 0) { //le héros commence
          System.out.println("\n" + fightingHero.getName() + " commence ... ");
          while (fightingHero.getLifePoints() > 0 && fightingEnemy.getLifePoints() > 0) {
            askActionHero();
            if (fightingHero.getLifePoints() <= 0) {
              System.out.println("\nVous avez perdu le combat N°" + i + " !");
              break;
            } else if (fightingEnemy.getLifePoints() <= 0) {
              System.out.println("\nVous avez gagné le combat N°" + i + " !");
              askOptionHero();
              break;
            } else {
              fightingHero.deleteLifePoints(fightingEnemy.getWeaponDamages());
              System.out.println("\n" + fightingEnemy.getName() + " attaque ... [ " + fightingEnemy.getLifePoints() + " PV ]");
              if (fightingHero.getLifePoints() <= 0) {
                System.out.println("\nVous avez perdu le combat N°" + i + " !");
                break;
              } else if (fightingEnemy.getLifePoints() <= 0) {
                System.out.println("\nVous avez gagné le combat N°" + i + " !");
                askOptionHero();
                break;
              }
              check();
            }
          }
          check();
          if (heroes.size() <= 0){
            System.out.print("\nVous avez perdu la partie !");
            break;
          } else if(enemies.size() <= 0){
            System.out.print("\nVous avez gagné la partie !");
            break;
          }
        } else if (entityTurn == 1) { //l'énemi commence
          System.out.println("\n" + fightingEnemy.getName() + " commence ... ");
          while (fightingHero.getLifePoints() > 0 && fightingEnemy.getLifePoints() > 0) {
            fightingHero.deleteLifePoints(fightingEnemy.getWeaponDamages());
            System.out.println("\n" + fightingEnemy.getName() + " attaque ... [ " + fightingEnemy.getLifePoints() + " PV ]");
            if (fightingHero.getLifePoints() <= 0) {
              System.out.println("\nVous avez perdu le combat N°" + i + " !");
              break;
            } else if (fightingEnemy.getLifePoints() <= 0) {
              System.out.println("\nVous avez gagné le combat N°" + i + " !");
              if (enemies.size() > 0){
                askOptionHero();
              }
              break;
            } else {
              askActionHero();
              if (fightingHero.getLifePoints() <= 0) {
                System.out.println("\nVous avez perdu le combat N°" + i + " !");
                break;
              } else if (fightingEnemy.getLifePoints() <= 0) {
                System.out.println("\nVous avez gagné le combat N°" + i + " !");
                if (enemies.size() > 0){
                  askOptionHero();
                }
                break;
              }
              check();
            }
          }
          check();
          if (heroes.size() <= 0){
            System.out.print("\nVous avez perdu la partie !");
            break;
          } else if(enemies.size() <= 0){
            System.out.print("\nVous avez gagné la partie !");
            break;
          }
        }
        i++;
        System.out.print("\nPress enter to continue");
        try {
          System.in.read();
        } catch (Exception e) {
        }
      } else {
        if (enemies.size() <= 0){
          System.out.print("\nVous avez gagné la partie !");
          break;
        } else {
          System.out.print("\nVous avez perdu la partie !");
          break;
        }
      }
    }
  }

  public void createHeroes() {
    int numHeroes = inputParser.askIntUser("\nChoisir le nombre de héros : ");
    System.out.println("\n 1 = Chasseur | 2 = Sorcier | 3 = Mage | 4 = Guerrier");
    for (int i=0; i<numHeroes; ++i){
      int clasHero = inputParser.askIntUser("\nChoisir la classe du héros N°" + (i+1) + " : ");
      while (true) {
        if (clasHero == 1 || clasHero == 2 || clasHero == 3 || clasHero == 4) {
          String nameHero = inputParser.askStringUser("\nChoisir le nom du héros N°" + (i+1) + " : ");
          switch (clasHero) {
            case 1 -> heroes.add(new Hunter(nameHero));
            case 2 -> {
              ArrayList<Potion> potions = new ArrayList<Potion>();
              ArrayList<Food> foods = new ArrayList<Food>();
              heroes.add(new Healer(nameHero));
            }
            case 3 -> heroes.add(new Mage(nameHero));
            case 4 -> heroes.add(new Warrior(nameHero));
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasHero = inputParser.askIntUser("\nChoisir la classe du héros N°" + (i+1) + " : ");
        }
      }
    }
  }

  public void generateEnemies() {
    int max = 10;
    int min = 1;
    int range = max - min + 1;
    for (int i = 0; i < heroes.size(); i++) {
      int rand = (int)(Math.random() * range) + min;
      if (rand == 8 || rand == 9 || rand == 10){
        String nameBoss = "Boss N°" + Integer.toString(i);
        enemies.add(new Boss(nameBoss));
      } else {
        String nameBasic = "Basic N°" + Integer.toString(i);
        enemies.add(new Basic(nameBasic));
      }
    }
  }

  public void generateCombat() {
      int randHeroes = (int)(Math.random() * heroes.size());
      fightingHero = heroes.get(randHeroes);
      int randEnemies = (int)(Math.random() * enemies.size());
      fightingEnemy = enemies.get(randEnemies);
  }

  public void askActionHero() {
    System.out.println("\nPoints de vie : " + fightingHero.getLifePoints());
    System.out.println("Points d'armure : " + fightingHero.getArmor());
    System.out.println("Points de dégâts : " + fightingHero.getWeaponDamages());
    System.out.println("Potions : " + fightingHero.getPotions().size());
    System.out.println("Foods : " + fightingHero.getFoods().size());
    if (fightingHero.getPotions().size() > 0 && fightingHero.getFoods().size() > 0){
      System.out.println("\n 1 = Attaquer | 2 = Boire | 3 = Manger");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1 || clasActionHero == 2 || clasActionHero == 3) {
          switch (clasActionHero) {
            case 1 -> {
              fightingEnemy.deleteLifePoints(fightingHero.getWeaponDamages());
              System.out.println("\n" + fightingHero.getName() + " attaque ... ");
            }
            case 2 -> {
              fightingHero.heal();
              System.out.println("\n" + fightingHero.getName() + " bois ... ");
            }
            case 3 -> {
              fightingHero.eat();
              System.out.println("\n" + fightingHero.getName() + " mange ... ");
            }
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
        }
      }
    } else if (fightingHero.getPotions().size() <= 0 && fightingHero.getFoods().size() > 0){
      System.out.println("\n 1 = Attaquer | 2 = Manger");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1 || clasActionHero == 2) {
          switch (clasActionHero) {
            case 1 -> {
              fightingEnemy.deleteLifePoints(fightingHero.getWeaponDamages());
              System.out.println("\n" + fightingHero.getName() + " attaque ... ");
            }
            case 2 -> {
              fightingHero.eat();
              System.out.println("\n" + fightingHero.getName() + " bois ... ");
            }
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
        }
      }
    } else if (fightingHero.getPotions().size() > 0 && fightingHero.getFoods().size() <= 0){
      System.out.println("\n 1 = Attaquer | 2 = Boire");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1 || clasActionHero == 2) {
          switch (clasActionHero) {
            case 1 -> {
              fightingEnemy.deleteLifePoints(fightingHero.getWeaponDamages());
              System.out.println("\n" + fightingHero.getName() + " attaque ... ");
            }
            case 2 -> {
              fightingHero.heal();
              System.out.println("\n" + fightingHero.getName() + " bois ... ");
            }
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
        }
      }
    } else {
      System.out.println("\n 1 = Attaquer");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1) {
          fightingEnemy.deleteLifePoints(fightingHero.getWeaponDamages());
          System.out.println("\n" + fightingHero.getName() + " attaque ... ");
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + fightingHero.getName() + " : ");
        }
      }
    }
  }

  public void askOptionHero(){
    System.out.println("\nPoints de vie : " + fightingHero.getLifePoints());
    System.out.println("Points d'armure : " + fightingHero.getArmor());
    System.out.println("Points de dégâts : " + fightingHero.getWeaponDamages());
    System.out.println("Potions : " + fightingHero.getPotions().size());
    System.out.println("Foods : " + fightingHero.getFoods().size());
    System.out.print("\n1 = Augmenter son armure");
    System.out.print("\n2 = Augmenter les dégâts de son arme");
    System.out.println("\n3 = Augmenter le nombre de potions ou de nourriture");
    int clasOptionHero = inputParser.askIntUser("\nChoisir l'option de " + fightingHero.getName() + " : ");
    while (true) {
      if (clasOptionHero == 1 || clasOptionHero == 2 || clasOptionHero == 3) {
        switch (clasOptionHero) {
          case 1 -> {
            fightingHero.upgradeArmor();
            System.out.println("\n" + fightingHero.getName() + " augmente son armure ... ");
          }
          case 2 -> {
            fightingHero.upgradeWeapon();
            System.out.println("\n" + fightingHero.getName() + " augmente les dégâts de son arme ... ");
          }
          case 3 -> {
            fightingHero.addRessources();
            System.out.println("\n" + fightingHero.getName() + " augmente le nombre de potions et de nourriture ... ");
          }
        }
        break;
      } else {
        System.out.println("\nERROR !");
        clasOptionHero = inputParser.askIntUser("\nChoisir l'option de " + fightingHero.getName() + " : ");
      }
    }
  }

  public void check(){
    for (int y=0; y<heroes.size(); ++y){
      if (heroes.get(y).getLifePoints() <= 0) {
        heroes.remove(y);
      }
    }
    for (int z=0; z<enemies.size(); ++z){
      if (enemies.get(z).getLifePoints() <= 0) {
        enemies.remove(z);
      }
    }
  }

}
