package com.mrhampson.ann4j.util;

import java.util.function.Supplier;

/**
 * @author Marshall Hampson
 */

public class MutableSupplier<T> implements Supplier<T> {

  private T value;

  public MutableSupplier(T value) {
    this.value = value;
  }

  @Override public T get() {
    return value;
  }

  public void set(T value) {
    this.value = value;
  }
}
