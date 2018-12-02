package com.josephbaca.rpggame;


import com.josephbaca.world.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal representation of the context to manage current game context.
 */
public class ContextManager {

  private final List<Context> contextStack = new ArrayList<>();

  public ContextManager() { }

  public void addContextLayer(Context c) {
    contextStack.add(c);
  }

  public void removeContextLayer() {
    contextStack.remove(contextStack.size() - 1);
  }

  public void replaceContextLayer(Context c) {
    removeContextLayer();
    addContextLayer(c);
  }

  public Context getCurrentContext() {
    return contextStack.get(contextStack.size() - 1);
  }
}
