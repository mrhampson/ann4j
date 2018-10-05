/*
 * Sigmoid.java
 * Created on Oct 05, 2018, 10:44 AM
 */
package main.java.com.mrhampson.ann4j;

public class Sigmoid implements ActivationFunction {
  
  @Override public Double apply(Double x) {
    return 1 / (1 + Math.pow(Math.E, -x));
  }
}
