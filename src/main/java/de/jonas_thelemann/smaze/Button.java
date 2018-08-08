// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.smaze;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.HashMap;
import java.util.Map;

/**
 * Objects of this class are buttons to be placed on the screens. If not a counter button then a specific action is set in the game object
 * and the game's act method is called to handle the action.
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */

public class Button extends Actor {
  public static final int OPTIONS = 0;
  public static final int BACK = 1;
  public static final int PLAY = 2;
  public static final int PLUS = 3;
  public static final int MINUS = 4;
  public static Map<Integer, GreenfootImage> imageMap;

  static {
    imageMap = new HashMap<Integer, GreenfootImage>();

    imageMap.put(OPTIONS, createImage("Options"));
    imageMap.put(BACK, createImage("Back"));
    imageMap.put(PLAY, createImage("Play"));
    imageMap.put(PLUS, new GreenfootImage("PlusButton.png"));
    imageMap.put(MINUS, new GreenfootImage("MinusButton.png"));
  }

  protected int type;
  protected Game game;

  public Button(int type, Game game) {

    this.type = type;
    this.game = game;

    setImage(imageMap.get(type));
  }

  /**
   * Buttons with text need to be larger than the text image itself.
   *
   * @param text
   * @return image of size 100x26 with centered text
   */
  private static GreenfootImage createImage(String text) {

    GreenfootImage img = new GreenfootImage(100, 26);
    img.setColor(Color.WHITE);
    img.fill();
    GreenfootImage textImg = new GreenfootImage(text, 20, Color.BLACK, Color.WHITE);
    img.drawImage(textImg, (img.getWidth() - textImg.getWidth()) / 2, (img.getHeight() - textImg.getHeight()) / 2);
    return img;
  }

  public void act() {
    if (Greenfoot.mouseClicked(this)) {
      if (type == OPTIONS) {
        this.game.setAction(Game.ACTION_OPTIONS);
      } else if (type == PLAY) {
        this.game.setAction(Game.ACTION_PLAY);
      } else if (type == BACK) {
        this.game.setAction(Game.ACTION_BACK);
      }

      this.game.act();
    }
  }
}