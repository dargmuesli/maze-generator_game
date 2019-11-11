// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.maze_generator_game;

import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.List;

/**
 * An object of this class is a specific player, the Ninja, who can teleport.
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */

public class Thelemann extends Character {
  private int teleportRange = Game.TELEPORT_RANGE;

  public Thelemann() {
    super("Thelemann", "darkBlue");
  }

  // Always check the movement, but only do showView on initialization.
  public void act() {
    super.act();

    if (Greenfoot.isKeyDown("up")) {
      this.moveUp();
    } else if (Greenfoot.isKeyDown("left")) {
      this.moveLeft();
    } else if (Greenfoot.isKeyDown("down")) {
      this.moveDown();
    } else if (Greenfoot.isKeyDown("right")) {
      this.moveRight();
    } else if (Greenfoot.isKeyDown("enter")) {
      this.useSpecialPower();
    }

    if (this.game.getViewFieldSize() > 0) {
      this.showView();
    }

    this.pickUpSheep();
  }

  // Teleport to a random branch's part in range.
  public void useSpecialPower() {
    if (isCooledDown()) {
      initCoolDown();

      Greenfoot.playSound("teleport.wav");

      List<MazeElement> meList = this.getObjectsInRange((this.teleportRange), MazeElement.class);
      List<MazeElement> pathList = new ArrayList<MazeElement>();
      for (MazeElement me : meList) {
        if (me.isPath()) {
          pathList.add(me);
        }
      }

      int i = Greenfoot.getRandomNumber(pathList.size());
      MazeElement path = pathList.get(i);
      this.setLocation(path.getX(), path.getY());
    }
  }
}