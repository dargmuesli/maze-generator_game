package de.jonas_thelemann.smaze;

import greenfoot.Greenfoot;

import java.util.List;

/**
 * Objects of this class are players which can move, collect sheep and perform special actions.
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */

public class Character extends Player {

  protected Game game;
  private String name;
  private Counter sheepCounter;
  private int coolDownCounter;
  private Icon coolDownIcon;
  private String color;
  private List<MazeElement> meList;

  public Character(String name, String color) {

    this.name = name;
    this.color = color;
    this.setImage(Icon.getSquare(color));
    this.sheepCounter = new Counter(0);
    this.coolDownCounter = 0;

    this.coolDownIcon = new Icon("CoolDownGreen.png");
  }

  public void act() {

    if (this.coolDownCounter > 0) {
      this.coolDownCounter--;
      if (this.coolDownCounter == 0) {
        this.coolDownIcon.setImage("CoolDownGreen.png");
      }
    }

    if (this.getGame().gameOver()) {
      Greenfoot.setWorld(new Finish(this.game));
    } else {
      if (this.game.getViewFieldSize() > 0) {
        this.hideView();
      }
    }
  }

  public String getName() {
    return name;
  }

  public Counter getSheepCounter() {
    return sheepCounter;
  }

  public Icon getCoolDownIcon() {
    return coolDownIcon;
  }

  public String getColor() {
    return color;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  protected void initCoolDown() {
    this.coolDownCounter = Game.COOLDOWN_LENGTH;
    this.coolDownIcon.setImage("CoolDownRed.png");
  }

  protected boolean isCooledDown() {
    return (this.coolDownCounter == 0);
  }

  // Make maze elements within view field range visible.
  public void showView() {
    this.meList = this.getObjectsInRange(this.game.getViewFieldSize() * 50, MazeElement.class);
    for (MazeElement me : this.meList) {
      me.makeVisible();
    }
  }

  // Make visible maze elements unvisible.
  public void hideView() {
    if (meList != null) {
      for (MazeElement me : this.meList) {
        me.makeInvisible();
      }
    }
  }

  // Remove sheep and add a number to the counter.
  public void pickUpSheep() {
    List<Sheep> sheepList = this.getWorld().getObjectsAt(this.getX(), this.getY(), Sheep.class);

    if (sheepList.size() > 0) {
      this.getWorld().removeObjects(sheepList);
      Greenfoot.playSound("singleSheep.wav");
      this.sheepCounter.incr(sheepList.size());
    }
  }
}



