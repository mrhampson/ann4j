package com.mrhampson.ann4j.utils;

/**
 * @author Marshall Hampson
 */
public class PairImpl<T,V> implements Pair<T, V> {
  
  private final T first;
  private final V second;
  
  public PairImpl(T first, V second) {
    this.first = first;
    this.second = second;
  }
  
  @Override 
  public T getFirst() {
    return first;
  }

  @Override 
  public V getSecond() {
    return second;
  }
}
