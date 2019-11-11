package de.jonas_thelemann.maze_generator_game;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * Objects of this class are texts to be placed on screens.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */
public class Text extends Actor {

  int width;
  int height;

  public Text(String text, int size) {
    this(text, size, Color.BLACK, Game.BG_COLOR, null); // Font.BOLD
  }

  public Text(String text, int size, Color textColor, Color bgColor, Color outlineColor) {

    GreenfootImage img;

    if (outlineColor != null) {
      //img = new GreenfootImage(text, size, textColor, bgColor, outlineColor);
      img = new GreenfootImage(text, size, textColor, bgColor);
    } else {
      img = new GreenfootImage(text, size, textColor, bgColor);
    }
    this.width = img.getWidth();
    this.height = img.getHeight();
    setImage(img);
  }

  /**
   * Act - do whatever the Text wants to do. This method is called whenever
   * the 'Act' or 'Run' button gets pressed in the environment.
   */
  public void act() {
    // Add your action code here.
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
