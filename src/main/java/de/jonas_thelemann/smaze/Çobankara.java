// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.smaze;

import greenfoot.Greenfoot;

/**
 * An object of this class is a specific character, the shepherd, who can send dogs.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class Çobankara extends Character {
  public Çobankara() {
    super("Çobankara", "purple");
  }

  public void act() {
    super.act();

    if (Greenfoot.isKeyDown("w")) {
      this.moveUp();
    } else if (Greenfoot.isKeyDown("a")) {
      this.moveLeft();
    } else if (Greenfoot.isKeyDown("s")) {
      this.moveDown();
    } else if (Greenfoot.isKeyDown("d")) {
      this.moveRight();
    } else if (Greenfoot.isKeyDown("space")) {
      this.useSpecialPower();
    }

    if (this.game.getViewFieldSize() > 0) {
      this.showView();
    }

    this.pickUpSheep();
  }

  // Spawn some dogs.
  public void useSpecialPower() {
    if (isCooledDown()) {
      initCoolDown();

      this.getWorld().addObject(new Dog(this, Game.DOG_RANGE), this.getX(), this.getY());
      Greenfoot.playSound("dogBarks.wav");
    }
  }
}