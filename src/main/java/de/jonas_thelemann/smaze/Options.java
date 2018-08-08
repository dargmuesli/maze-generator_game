// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.smaze;

import greenfoot.World;

/**
 * An object of this class represents the options dialog. Three options are included, more are possible, but currently not wanted.
 * Once an object of this class is created it is reused throughout the game whenever the options dialog is chosen.
 *
 * @author Nur Çobankara, Jonas Thelemann
 * @version 17.12.2014
 */

public class Options extends World {
  private int textHeight = 75;
  private int plusHeight = 125;
  private int minusHeight = 225;
  private int numberHeight = 175;
  private int leftWidth = 100;
  private int middleWidth = 300;
  private int rightWidth = 500;

  private Game game;

  public Options(Game game) {
    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    super(600, 400, 1);

    this.setBackground("PlainBackground.png");

    this.game = game;
    this.game.setOptions(this);

    Counter sheepCounter = this.game.getSheepCounter();
    Counter dogCounter = this.game.getDogCounter();
    Counter viewFieldSizer = this.game.getViewFieldSizer();

    // Add text and control buttons.

    this.addObject(new Text("Sheep", 30), leftWidth, textHeight);
    this.addObject(sheepCounter, leftWidth, numberHeight);
    this.addObject(new CounterButton(Button.PLUS, sheepCounter), leftWidth, plusHeight);
    this.addObject(new CounterButton(Button.MINUS, sheepCounter), leftWidth, minusHeight);

    this.addObject(new Text("Viewfield", 30), middleWidth, textHeight);
    this.addObject(viewFieldSizer, middleWidth, numberHeight);
    this.addObject(new CounterButton(Button.PLUS, viewFieldSizer), middleWidth, plusHeight);
    this.addObject(new CounterButton(Button.MINUS, viewFieldSizer), middleWidth, minusHeight);

    this.addObject(new Text("Dogs", 30), rightWidth, textHeight);
    this.addObject(dogCounter, rightWidth, numberHeight);
    this.addObject(new CounterButton(Button.PLUS, dogCounter), rightWidth, plusHeight);
    this.addObject(new CounterButton(Button.MINUS, dogCounter), rightWidth, minusHeight);

    this.addObject(new Button(Button.BACK, this.game), middleWidth, 375);
  }

  public Game getGame() {
    return this.game;
  }
}