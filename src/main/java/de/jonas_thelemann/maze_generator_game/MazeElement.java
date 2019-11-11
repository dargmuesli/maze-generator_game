// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.maze_generator_game;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.HashMap;
import java.util.Map;

/**
 * Objects of this class are maze elements: paths, walls, and "requested" paths (which is a transient state during maze generation)
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class MazeElement extends Actor {
  public static final int WALL = 0;
  public static final int PATH = 1;
  public static final int REQUESTED = 2;

  public static Map<Integer, GreenfootImage> imageMap;

  static {
    imageMap = new HashMap<Integer, GreenfootImage>();
    imageMap.put(WALL, Icon.getSquare("black"));
    imageMap.put(PATH, Icon.getSquare("grey"));
    imageMap.put(REQUESTED, Icon.getSquare("red"));
  }

  private int type;

  public MazeElement(int type) {

    this.type = type;
    this.setImage(imageMap.get(type));
  }

  public void act() {
    // Nothing to do.
  }

  public int getType() {
    return this.type;
  }

  public boolean isPath() {
    return (this.type == PATH);
  }

  public void setPath() {
    this.type = PATH;
    this.setImage(imageMap.get(PATH));
  }

  public boolean isWall() {
    return (this.type == WALL);
  }

  public void setWall() {
    this.type = WALL;
    this.setImage(imageMap.get(WALL));
  }

  public boolean isRequested() {
    return (this.type == REQUESTED);
  }

  public void setRequested() {
    this.type = REQUESTED;
    this.setImage(imageMap.get(REQUESTED));
  }

  public void makeInvisible() {
    this.setImage(Icon.getSquare("black"));
  }

  public void makeVisible() {
    this.setImage(imageMap.get(this.type));
  }

}