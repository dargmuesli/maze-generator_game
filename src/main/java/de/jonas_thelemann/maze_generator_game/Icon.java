package de.jonas_thelemann.maze_generator_game;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

import java.util.HashMap;
import java.util.Map;

/**
 * An object of this class is an icon to be placed on a screen.
 * It can be created from an image file or a Greenfoot image.
 * <p>
 * This class also serves as a provider of square images of different colors.
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */
public class Icon extends Actor {

  public static Map<String, GreenfootImage> imageMap;

  static {
    imageMap = new HashMap<String, GreenfootImage>();

    imageMap.put("black", createSquare(Color.BLACK));
    imageMap.put("brightBlue", createSquare(new Color(0, 128, 255)));
    imageMap.put("brightGrey", createSquare(new Color(192, 192, 192)));
    imageMap.put("brown", createSquare(new Color(128, 64, 0)));
    imageMap.put("darkBlue", createSquare(Color.BLUE));
    imageMap.put("darkGrey", createSquare(new Color(64, 64, 64)));
    imageMap.put("green", createSquare(Color.GREEN));
    imageMap.put("grey", createSquare(new Color(128, 128, 128)));
    imageMap.put("orange", createSquare(Color.ORANGE));
    imageMap.put("purple", createSquare(new Color(255, 0, 254)));
    imageMap.put("red", createSquare(Color.RED));
    imageMap.put("white", createSquare(Color.WHITE));
    imageMap.put("yellow", createSquare(Color.YELLOW));
  }

  public Icon(String iconFile) {

    GreenfootImage img = new GreenfootImage(iconFile);
    setImage(img);
  }

  public Icon(GreenfootImage img) {

    setImage(img);
  }

  private static GreenfootImage createSquare(Color color) {
    GreenfootImage img = new GreenfootImage(10, 10);
    img.setColor(color);
    img.fill();
    return img;
  }

  public static GreenfootImage getSquare(String color) {
    return imageMap.get(color);
  }

  public static Icon getSquareIcon(String color) {
    return new Icon(imageMap.get(color));
  }

  /**
   * Act - do whatever the Text wants to do. This method is called whenever
   * the 'Act' or 'Run' button gets pressed in the environment.
   */
  public void act() {
    // Add your action code here.
  }

}
