package com.mrhampson.ann4j.utils;

/**
 * @author Marshall Hampson
 */
public interface Pair<T, V> {
  T getFirst();
  V getSecond();
  
  static <T, V> Pair<T,V> of(T first, V second) {
    return new PairImpl<>(first, second);
  }
}
