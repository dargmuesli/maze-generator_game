// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.smaze;

import greenfoot.World;

import java.util.List;

/**
 * An object of this class represents the screen where the game is actually played.
 * Besides 2 "cool down" icons and 2 sheep counter to the left and to the right, it consists
 * of a matrix of square maze elements (either paths or walls) and players (playerA, playerB, sheep and dogs) in the maze.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class Maze extends World {
  private Game game;
  private int borderSize = 30;

  private int xMin;
  private int xMax;
  private int yMin;
  private int yMax;

  public Maze(Game game) {
    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    super(600, 400, 1);

    this.setBackground("brick.jpg");

    this.game = game;
    this.game.setMaze(this);

    // Add a generator to build the maze.
    this.addObject(new MazeCreator(this), 5, 5);

    // Add visual elements.

    this.addObject(this.game.getPlayerA().getCoolDownIcon(), 10, this.getHeight() / 2);
    this.addObject(this.game.getPlayerB().getCoolDownIcon(), this.getWidth() - 10, this.getHeight() / 2);

    this.addObject(game.getPlayerA().getSheepCounter(), 20, 40);
    this.addObject(game.getPlayerB().getSheepCounter(), this.getWidth() - 20, 40);

    // set border coordinates of actual maze

    int offsetX = 50;

    this.xMin = offsetX + 5;
    this.xMax = this.getWidth() - (offsetX + 5);
    this.yMin = 5;
    this.yMax = this.getHeight() - 5;

    // Fix wrong display of overlaying classes.
    this.setPaintOrder(MazeCreator.class, Thelemann.class, Çobankara.class, Dog.class, Sheep.class, Counter.class, Icon.class, MazeElement.class);
  }

  public int getBorderSize() {
    return borderSize;
  }

  public int getxMin() {
    return xMin;
  }

  public int getxMax() {
    return xMax;
  }

  public int getyMin() {
    return yMin;
  }

  public int getyMax() {
    return yMax;
  }

  public Game getGame() {
    return game;
  }

  public MazeElement getMazeElementAt(int x, int y, Integer type) {

    List<MazeElement> meList = this.getObjectsAt(x, y, MazeElement.class);

    if (meList.size() > 0) {
      MazeElement me = meList.get(0);
      if (type == null || me.getType() == type.intValue()) {
        return me;
      } else {
        return null;
      }
    } else {
      return null;
    }

  }
}