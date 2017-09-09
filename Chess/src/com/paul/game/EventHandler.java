package com.paul.game;

import java.util.ArrayList;

public abstract class EventHandler {

  private static ArrayList<EventHandler> eventHandlers = new ArrayList<EventHandler>();
  
  private Game game;
  
  public EventHandler(Game game) {
    this.game = game;
    
    eventHandlers.add(this);
  }
}
