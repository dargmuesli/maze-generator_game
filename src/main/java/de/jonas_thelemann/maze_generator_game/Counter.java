package de.jonas_thelemann.maze_generator_game;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

import java.util.HashMap;
import java.util.Map;

/**
 * Objects of this class are counters which can be placed on the screens.
 * The range of the counters is 0..9.
 *
 * @author Jonas Thelemann, Nur Çobankara
 * @version 17.12.2014
 */
public class Counter extends Actor {

  public static Map<Integer, GreenfootImage> imageCache;

  static {
    imageCache = new HashMap<Integer, GreenfootImage>();

    for (int i = 0; i <= 9; i++) {
      imageCache.put(i, new GreenfootImage(i + "", 60, Color.WHITE, null));
    }
  }

  private Integer from;
  private Integer to;
  private int i;

  public Counter(int start) {
    this.from = null;
    this.to = null;
    this.i = start;
    this.setImage(getCounterImage());
  }

  public Counter(int from, int to, int start) {
    this.from = from;
    this.to = to;
    this.i = start;
    this.setImage(getCounterImage());
  }

  public int get() {
    return this.i;
  }

  public void incr(int incr) {

    if ((this.from == null || (this.from != null && this.i + incr >= this.from))
            && (this.to == null || (this.to != null && this.i + incr <= this.to))) {

      this.i = this.i + incr;
      this.setImage(this.getCounterImage());
    }
  }

  private GreenfootImage getCounterImage() {
    GreenfootImage img = imageCache.get(this.i);
    if (img == null) {
      img = new GreenfootImage(this.i + "", 60, Color.WHITE, null);
      imageCache.put(this.i, img);
    }
    return img;
  }
}
