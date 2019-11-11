package de.jonas_thelemann.maze_generator_game;

import greenfoot.Greenfoot;

/**
 * An object of this class is a special buttons which is connected to a counter object.
 * A counter button may be either a "plus" button (increasing the counter) or a "minus" button (tdecreasing the counter).
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */

public class CounterButton extends Button {

  private Counter counter;

  public CounterButton(int type, Counter counter) {

    super(type, null);
    this.counter = counter;
  }

  public void act() {
    if (Greenfoot.mouseClicked(this)) {
      if (this.type == Button.PLUS) {
        this.counter.incr(1);
      } else if (this.type == Button.MINUS) {
        this.counter.incr(-1);
      }
    }
  }
}
