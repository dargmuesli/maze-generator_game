// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.smaze;

import greenfoot.Color;
import greenfoot.World;

/**
 * An object of the class represents the main menu. From this screen you can either go to the options dialog or start a new game.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class Menu extends World {
  private Game game;

  public Menu(Game game) {

    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    super(600, 400, 1);

    this.setBackground("PlainBackground.png");

    this.game = game;
    this.game.setMenu(this);

    // add buttons

    this.addObject(new Button(Button.PLAY, this.game), 300, 375);
    this.addObject(new Button(Button.OPTIONS, this.game), 525, 25);

    // add texts and icons

    Text text;
    int x0 = 50;
    int x1 = 70;
    int y = 60;
    int delta = 45;

    text = new Text("Players", 40, Color.WHITE, Game.BG_COLOR, Color.BLACK);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta;

    this.addObject(Icon.getSquareIcon("purple"), x0, y);
    text = new Text("Çobankara", 30, Color.WHITE, Game.BG_COLOR, null);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta / 2;

    text = new Text("the shepherd (moves using WASD keys, can spawn dogs by <Space>)", 20);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta;

    this.addObject(Icon.getSquareIcon("darkBlue"), x0, y);
    text = new Text("Thelemann", 30, Color.WHITE, Game.BG_COLOR, null);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta / 2;

    text = new Text("the Ninja (moves using arrow keys, can teleport by <Enter>)", 20);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta;

    this.addObject(Icon.getSquareIcon("white"), x0, y);
    text = new Text("Sheep", 20);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta;

    this.addObject(Icon.getSquareIcon("brown"), x0, y);
    text = new Text("Dog", 20);
    this.addObject(text, x1 + text.getWidth() / 2, y);

    y += delta;

    text = new Text("Find as may sheep as possible!", 40, Color.WHITE, Game.BG_COLOR, Color.BLACK);
    this.addObject(text, this.getWidth() / 2, y);
  }
}