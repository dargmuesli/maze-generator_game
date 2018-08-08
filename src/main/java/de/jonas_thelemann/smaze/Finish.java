// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.smaze;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * An object of this class shows the final screen with statistics about the just finished game.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class Finish extends World {
  private Game game;
  private int leftSide = 150;
  private int middleSide = 300;
  private int rightSide = 450;
  private int numbersLeftSide = 250;
  private int numbersRightSide = 350;

  public Finish(Game game) {
    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    super(600, 400, 1);

    this.setBackground("PlainBackground.png");

    this.game = game;

    Character playerA = this.game.getPlayerA();
    Character playerB = this.game.getPlayerB();

    // Add text/icons and a button which leads back to the menu.
    this.addObject(new Icon("Finish.png"), this.middleSide, 50);

    this.addObject(new Text(playerA.getName(), 40, Color.WHITE, Game.BG_COLOR, Color.BLACK), this.leftSide, 200);
    GreenfootImage img = new GreenfootImage(Icon.getSquare(playerA.getColor()));
    img.scale(20, 20);
    this.addObject(new Icon(img), this.leftSide, 240);

    this.addObject(new Text(playerB.getName(), 40, Color.WHITE, Game.BG_COLOR, Color.BLACK), this.rightSide, 200);
    img = new GreenfootImage(Icon.getSquare(playerB.getColor()));
    img.scale(20, 20);
    this.addObject(new Icon(img), this.rightSide, 240);

    this.addObject(new Button(Button.BACK, this.game), this.middleSide, 350);

    int sheepCounterA = playerA.getSheepCounter().get();
    int sheepCounterB = playerB.getSheepCounter().get();

    // Decide how the game ended.
    if (sheepCounterA == sheepCounterB) {
      this.addObject(new Text("Draw", 30), this.middleSide, 100);
    } else {

      this.addObject(new Text(sheepCounterA > sheepCounterB ? "Winner" : "Loser", 30), this.leftSide, 150);
      this.addObject(new Text(sheepCounterB > sheepCounterA ? "Winner" : "Loser", 30), this.rightSide, 150);
    }

    // Display sheepCounts.

    this.addObject(new Counter(sheepCounterA), this.numbersLeftSide, this.leftSide);
    this.addObject(new Text(":", 50), this.middleSide, this.leftSide);
    this.addObject(new Counter(sheepCounterB), this.numbersRightSide, this.leftSide);
  }
}