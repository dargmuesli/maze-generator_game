// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.maze_generator_game;

import greenfoot.Greenfoot;

import java.util.List;

/**
 * A dog assists a player in catching sheep. It will be active until a spcific number of moves done (dogRange).
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class Dog extends Player {
  private int stepCount = 0;
  private int dogRange;
  private Character player;


  public Dog(Character player, int dogRange) {
    super();
    this.setImage(Icon.getSquare("brown"));
    this.player = player;
    this.dogRange = dogRange;
  }

  // Move randomly and collect sheep for player A (çobankara).
  public void act() {
    if (this.stepCount < this.dogRange) {
      if ((this.getRotation() == 0) && Greenfoot.getRandomNumber(2) == 1) {
        MazeElement me = (MazeElement) this.getOneObjectAtOffset(10, 0, MazeElement.class);

        if (me != null && me.isPath()) {
          this.moveRight();
          this.sheepIt();
        } else {
          this.randomTurn();
        }
      } else if ((this.getRotation() == 90) && Greenfoot.getRandomNumber(2) == 1) {
        MazeElement me = (MazeElement) this.getOneObjectAtOffset(0, 10, MazeElement.class);

        if (me != null && me.isPath()) {
          this.moveDown();
          this.sheepIt();
        } else {
          this.randomTurn();
        }
      } else if ((this.getRotation() == 180) && Greenfoot.getRandomNumber(2) == 1) {
        MazeElement me = (MazeElement) this.getOneObjectAtOffset(-10, 0, MazeElement.class);

        if (me != null && me.isPath()) {
          this.moveLeft();
          this.sheepIt();
        } else {
          this.randomTurn();
        }
      } else if ((this.getRotation() == 270) && Greenfoot.getRandomNumber(2) == 1) {
        MazeElement me = (MazeElement) this.getOneObjectAtOffset(0, -10, MazeElement.class);

        if (me != null && me.isPath()) {
          this.moveUp();
          this.sheepIt();
        } else {
          this.randomTurn();
        }
      }
    } else {
      this.getWorld().removeObject(this);
    }
  }

  public void sheepIt() {
    List<Sheep> sheepList = this.getWorld().getObjectsAt(this.getX(), this.getY(), Sheep.class);

    if (sheepList.size() > 0) {
      this.getWorld().removeObjects(sheepList);
      Greenfoot.playSound("singleSheep.wav");
      this.player.getSheepCounter().incr(sheepList.size());
    }

    Greenfoot.delay(1);
    stepCount++;
  }
}