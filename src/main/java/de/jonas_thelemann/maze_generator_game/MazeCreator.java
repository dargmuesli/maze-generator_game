// Import: World, Actor, GreenfootImage, Greenfoot and MouseInfo.
package de.jonas_thelemann.maze_generator_game;

import greenfoot.Greenfoot;

/**
 * An object of this class generates the maze.
 *
 * @author Jonas Thelemann
 * @version 17.12.2014
 */

public class MazeCreator extends Player {

  // values for step array cells
  private static final int IMPOSSIBLE = 0;
  private static final int POSSIBLE = 1;
  private static final int REQUESTED = 2;

  // indexes into step array
  private static final int RIGHT = 0;
  private static final int DOWN = 1;
  private static final int LEFT = 2;
  private static final int UP = 3;

  private int startPositionX;
  private int startPositionY;

  private int endPositionX;
  private int endPositionY;

  private int[] step = {POSSIBLE, POSSIBLE, POSSIBLE, POSSIBLE};
  private int possibleStepsCount;
  private int backDirection;

  private int pathLength = 0;
  private int longestPathLength = 0;

  private Maze maze;

  public MazeCreator(Maze maze) {
    super();
    this.setImage(Icon.getSquare("yellow"));
    this.maze = maze;
  }

  public void act() {
    Greenfoot.setSpeed(Game.MAZE_CREATOR_SPEED);

    // Generate the maze.
    this.resetMaze();
    this.randomStart();
    this.buildMaze();
    this.makeWallsAndInvisiblePaths();
    this.buildStartAndEnd();

    this.maze.addObject(maze.getGame().getPlayerA(), this.startPositionX, this.startPositionY);
    this.maze.addObject(maze.getGame().getPlayerB(), this.endPositionX, this.endPositionY);
    this.randomPlaceSheep();

    this.maze.removeObject(this);

    Greenfoot.setSpeed(Game.SPEED);
  }

  // Remove all classes except this one.
  private void resetMaze() {
    this.longestPathLength = 0;

    this.maze.removeObjects(this.maze.getObjects(MazeElement.class));
  }

  // Randomly place the point of starting.
  private void randomStart() {
    this.startPositionX = this.maze.getxMin();
    this.startPositionY = Greenfoot.getRandomNumber(this.maze.getyMax() / 10) * 10 + 5;
    this.setLocation(this.startPositionX, this.startPositionY);
    this.maze.addObject(new MazeElement(MazeElement.REQUESTED), this.startPositionX, this.startPositionY);
  }

  private void buildMaze() {

    boolean done = checkNextStep();

    while (!done) {

      setDirection();

      if (this.possibleStepsCount > 0) {
        this.stepForward();
      } else {
        this.stepBackward();
      }

      Greenfoot.delay(Game.DELAY_TIME);

      done = checkNextStep();
    }

    // switch starting point to Path
    this.maze.getMazeElementAt(this.startPositionX, this.startPositionY, MazeElement.REQUESTED).setPath();
  }

  private boolean checkNextStep() {

    MazeElement me;
    MazeElement me2;

    // first check of steps in all directions: next element empty or requested, still within maze?

    this.step[RIGHT] = POSSIBLE;

    if (this.getX() + 10 > this.maze.getxMax()) {
      this.step[RIGHT] = IMPOSSIBLE;
    } else {
      me = (MazeElement) this.getOneObjectAtOffset(10, 0, MazeElement.class);
      me2 = (MazeElement) this.getOneObjectAtOffset(20, 0, MazeElement.class);
      if (me != null && me.isRequested()) {
        this.step[RIGHT] = REQUESTED;
      } else if ((me != null && me.isPath()) || me2 != null) {
        this.step[RIGHT] = IMPOSSIBLE;
      }
    }

    this.step[DOWN] = POSSIBLE;

    if (this.getY() + 10 > this.maze.getyMax()) {
      this.step[DOWN] = IMPOSSIBLE;
    } else {
      me = (MazeElement) this.getOneObjectAtOffset(0, 10, MazeElement.class);
      me2 = (MazeElement) this.getOneObjectAtOffset(0, 20, MazeElement.class);
      if (me != null && me.isRequested()) {
        this.step[DOWN] = REQUESTED;
      } else if ((me != null && me.isPath()) || me2 != null) {
        this.step[DOWN] = IMPOSSIBLE;
      }
    }

    this.step[LEFT] = POSSIBLE;

    if (this.getX() - 10 < this.maze.getxMin()) {
      this.step[LEFT] = IMPOSSIBLE;
    } else {
      me = (MazeElement) this.getOneObjectAtOffset(-10, 0, MazeElement.class);
      me2 = (MazeElement) this.getOneObjectAtOffset(-20, 0, MazeElement.class);
      if (me != null && me.isRequested()) {
        this.step[LEFT] = REQUESTED;
      } else if ((me != null && me.isPath()) || me2 != null) {
        this.step[LEFT] = IMPOSSIBLE;
      }
    }

    this.step[UP] = POSSIBLE;

    if (this.getY() - 10 < this.maze.getyMin()) {
      this.step[UP] = IMPOSSIBLE;
    } else {
      me = (MazeElement) this.getOneObjectAtOffset(0, -10, MazeElement.class);
      me2 = (MazeElement) this.getOneObjectAtOffset(0, -20, MazeElement.class);
      if (me != null && me.isRequested()) {
        this.step[UP] = REQUESTED;
      } else if ((me != null && me.isPath()) || me2 != null) {
        this.step[UP] = IMPOSSIBLE;
      }
    }

    // second check of steps in all directions: are neigoring maze elements empty?

    if (this.step[RIGHT] == POSSIBLE || this.step[UP] == POSSIBLE) {
      me = (MazeElement) this.getOneObjectAtOffset(10, -10, MazeElement.class);
      if (me != null) {
        if (this.step[RIGHT] != REQUESTED) this.step[RIGHT] = IMPOSSIBLE;
        if (this.step[UP] != REQUESTED) this.step[UP] = IMPOSSIBLE;
      }
    }

    if (this.step[RIGHT] == POSSIBLE || this.step[DOWN] == POSSIBLE) {
      me = (MazeElement) this.getOneObjectAtOffset(10, 10, MazeElement.class);
      if (me != null) {
        if (this.step[RIGHT] != REQUESTED) this.step[RIGHT] = IMPOSSIBLE;
        if (this.step[DOWN] != REQUESTED) this.step[DOWN] = IMPOSSIBLE;
      }
    }

    if (this.step[LEFT] == POSSIBLE || this.step[UP] == POSSIBLE) {
      me = (MazeElement) this.getOneObjectAtOffset(-10, -10, MazeElement.class);
      if (me != null) {
        if (this.step[LEFT] != REQUESTED) this.step[LEFT] = IMPOSSIBLE;
        if (this.step[UP] != REQUESTED) this.step[UP] = IMPOSSIBLE;
      }
    }

    if (this.step[LEFT] == POSSIBLE || this.step[DOWN] == POSSIBLE) {
      me = (MazeElement) this.getOneObjectAtOffset(-10, 10, MazeElement.class);
      if (me != null) {
        if (this.step[LEFT] != REQUESTED) this.step[LEFT] = IMPOSSIBLE;
        if (this.step[DOWN] != REQUESTED) this.step[DOWN] = IMPOSSIBLE;
      }
    }

    // check if done

    this.possibleStepsCount = 0;
    this.backDirection = -1;

    for (int i = 0; i < 4; i++) {
      if (this.step[i] == REQUESTED) {
        this.backDirection = i;
      } else if (this.step[i] == POSSIBLE) {
        this.possibleStepsCount++;
      }
    }

    if (this.backDirection == -1 && this.possibleStepsCount == 0) {
      return true;  // done
    } else {
      return false; // not yet done
    }
  }


  private void setDirection() {
    int nextRotation = 0;

    if (this.possibleStepsCount > 0) {
      int d = Greenfoot.getRandomNumber(this.possibleStepsCount);
      int j = 0;
      for (int i = 0; i < 4; i++) {
        if (this.step[i] == POSSIBLE) {
          if (j == d) {
            nextRotation = i;
            break;
          } else {
            j++;
          }
        }
      }

    } else {
      nextRotation = this.backDirection;
    }

    this.setRotation(nextRotation * 90);
  }

  // Go 10px.
  private void stepForward() {
    this.move(10);

    this.maze.addObject(new MazeElement(MazeElement.REQUESTED), this.getX(), this.getY());

    this.pathLength++;

    if (this.getX() == this.maze.getxMax() && this.pathLength > this.longestPathLength) {
      this.endPositionX = this.getX();
      this.endPositionY = this.getY();
      this.longestPathLength = this.pathLength;
    }
  }

  // Go to last position.
  private void stepBackward() {
    MazeElement me = this.maze.getMazeElementAt(this.getX(), this.getY(), MazeElement.REQUESTED);
    if (me != null) {
      me.setPath();
    }

    this.move(10);
  }

  // Make an entrance and exit.
  private void buildStartAndEnd() {
    int borderSize = this.maze.getBorderSize();

    for (int offset = 10; offset <= borderSize; offset = offset + 10) {
      this.maze.removeObjects(this.maze.getObjectsAt(this.startPositionX - offset, this.startPositionY, MazeElement.class));
      this.maze.addObject(new MazeElement(MazeElement.PATH), this.startPositionX - offset, this.startPositionY);

      this.maze.removeObjects(this.maze.getObjectsAt((this.endPositionX + offset), this.endPositionY, MazeElement.class));
      this.maze.addObject(new MazeElement(MazeElement.PATH), (this.endPositionX + offset), this.endPositionY);
    }
  }


  // Create missing walls and make paths invisible
  private void makeWallsAndInvisiblePaths() {
    int borderSize = this.maze.getBorderSize();

    Greenfoot.delay(0);

    for (int y = this.maze.getyMin(); y <= this.maze.getyMax(); y = y + 10) {
      for (int x = this.maze.getxMin() - borderSize; x <= this.maze.getxMax() + borderSize; x = x + 10) {
        this.setLocation(x, y);

        MazeElement me = (MazeElement) this.getOneObjectAtOffset(0, 0, MazeElement.class);

        if (me == null) {
          this.maze.addObject(new MazeElement(MazeElement.WALL), x, y);
        } else if (this.maze.getGame().getViewFieldSize() > 0) {
          me.makeInvisible();
        }

      }
    }

    Greenfoot.delay(Game.DELAY_TIME);
  }

  // Randomly place sheep in world.

  private void randomPlaceSheep() {
    int sheepCounter = this.maze.getGame().getSheepCount();
    int counter = 0;

    while (counter < sheepCounter) {
      int randomXPosition = (Greenfoot.getRandomNumber(this.maze.getWidth() / 10) * 10) + 5;
      int randomYPosition = (Greenfoot.getRandomNumber(this.maze.getHeight() / 10) * 10) + 5;

      if (this.maze.getMazeElementAt(randomXPosition, randomYPosition, MazeElement.PATH) != null) {
        this.maze.addObject(new Sheep(), randomXPosition, randomYPosition);
        counter++;
      }
    }
  }

}