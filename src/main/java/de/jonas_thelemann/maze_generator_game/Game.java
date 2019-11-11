// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.maze_generator_game;

import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * An object of this class is the initial world of this game.
 * When created it shows the start screen, but it remains crucial object throughout the game.
 * It hold references to the counters representing the options (sheep & dog counters, view field sizer),
 * references to the players, and references to other worlds representing othe screens of this game (menu, maze, options).
 * The act method is in charge of performing the desired action. It is called for the first time when the "run" Button
 * of the Greenfoot run time environment is pressed, and then switches the world (screen) to the main menu.
 * <p>
 * This class also defines several constants to be used anywhere.
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */

public class Game extends World {
  public static final int SPEED = 30;
  public static final int MAZE_CREATOR_SPEED = 75;
  public static final int DELAY_TIME = 1;

  public static final int MAX_SHEEP_COUNT = 20;
  public static final int DEFAULT_SHEEP_COUNT = 10;
  public static final int MAX_DOG_COUNT = 10;
  public static final int DEFAULT_DOG_COUNT = 5;
  public static final int MAX_VIEW_FIELD_SIZE = 3;
  public static final int DEFAULT_VIEW_FIELD_SIZE = 0;

  public static final int DOG_RANGE = 5;
  public static final int COOLDOWN_LENGTH = 50;
  public static final int TELEPORT_RANGE = 50;

  public static final int NO_ACTION = 0;
  public static final int ACTION_START = 1;
  public static final int ACTION_PLAY = 2;
  public static final int ACTION_OPTIONS = 3;
  public static final int ACTION_BACK = 4;

  public static final Color BG_COLOR = new Color(255, 193, 0); // color from PlainBackground.png

  private Counter sheepCounter;
  private Counter dogCounter;
  private Counter viewFieldSizer;

  private Character playerA;
  private Character playerB;

  private Menu menu = null;
  private Options options = null;
  private Maze maze = null;

  private int action;

  public Game() {
    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    super(600, 400, 1);

    Greenfoot.setSpeed(Game.SPEED);

    setBackground("Start.png");

    this.sheepCounter = new Counter(0, MAX_SHEEP_COUNT, DEFAULT_SHEEP_COUNT);
    this.dogCounter = new Counter(0, MAX_DOG_COUNT, DEFAULT_DOG_COUNT);
    this.viewFieldSizer = new Counter(0, MAX_VIEW_FIELD_SIZE, DEFAULT_VIEW_FIELD_SIZE);

    this.action = ACTION_START;
  }

  public void act() {

    if (this.action != NO_ACTION) {

      if (this.action == ACTION_START) {

        Greenfoot.setWorld(new Menu(this));

      } else if (this.action == ACTION_PLAY) {

        this.setPlayerA(new Çobankara());
        this.setPlayerB(new Thelemann());

        Greenfoot.setWorld(new Maze(this));

      } else if (this.action == ACTION_OPTIONS) {

        if (this.options == null) {

          this.options = new Options(this);
        }

        Greenfoot.setWorld(this.options);

      } else if (this.action == ACTION_BACK) {

        Greenfoot.setWorld(this.menu);
      }

      this.action = NO_ACTION;
    }
  }


  public void setAction(int action) {
    this.action = action;
  }

  public Counter getSheepCounter() {
    return sheepCounter;
  }

  public int getSheepCount() {
    return sheepCounter.get();
  }

  public Counter getDogCounter() {
    return dogCounter;
  }

  public int getDogCount() {
    return dogCounter.get();
  }

  public Counter getViewFieldSizer() {
    return viewFieldSizer;
  }

  public int getViewFieldSize() {
    return viewFieldSizer.get();
  }

  public Character getPlayerA() {
    return playerA;
  }

  public void setPlayerA(Character playerA) {
    this.playerA = playerA;
    this.playerA.setGame(this);
  }

  public Character getPlayerB() {
    return playerB;
  }

  public void setPlayerB(Character playerB) {
    this.playerB = playerB;
    this.playerB.setGame(this);
  }

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Options getOptions() {
    return options;
  }

  public void setOptions(Options options) {
    this.options = options;
  }

  public Maze getMaze() {
    return maze;
  }

  public void setMaze(Maze maze) {
    this.maze = maze;
  }

  public boolean gameOver() {

    return (this.getSheepCount() - playerA.getSheepCounter().get() - playerB.getSheepCounter().get() == 0);

    // only for testing: game over when first sheep is caught
//		return (playerA.getSheepCounter().get() > 0 ||  playerB.getSheepCounter().get() > 0);

    // only for testing: game over immediately
//		return true;
  }

}