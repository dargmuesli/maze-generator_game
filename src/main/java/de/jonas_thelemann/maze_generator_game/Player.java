// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.maze_generator_game;

import greenfoot.Actor;
import greenfoot.Greenfoot;

/**
 * This class provides all movement methods for players.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class Player extends Actor {
  public void act() {
    // Nothing to do.
  }

  // Move only on branches in the given direction (without turning).
  public void moveRight() {
    MazeElement me = (MazeElement) this.getOneObjectAtOffset(10, 0, MazeElement.class);

    if (me != null && me.isPath()) {
      this.setLocation(getX() + 10, this.getY());
    }
  }

  public void moveDown() {
    MazeElement me = (MazeElement) this.getOneObjectAtOffset(0, 10, MazeElement.class);

    if (me != null && me.isPath()) {
      this.setLocation(getX(), this.getY() + 10);
    }
  }

  public void moveLeft() {
    MazeElement me = (MazeElement) this.getOneObjectAtOffset(-10, 0, MazeElement.class);

    if (me != null && me.isPath()) {
      this.setLocation(getX() - 10, this.getY());
    }
  }

  public void moveUp() {
    MazeElement me = (MazeElement) this.getOneObjectAtOffset(0, -10, MazeElement.class);

    if (me != null && me.isPath()) {
      this.setLocation(getX(), this.getY() - 10);
    }
  }


  // Turn randomly 90° left or right.
  public void randomTurn() {
    if (Greenfoot.getRandomNumber(2) == 1) {
      this.turn(90);
    } else {
      this.turn(-90);
    }
  }
}