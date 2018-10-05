/*
 * ActivationFunctions.java
 * Created on Oct 05, 2018, 10:44 AM
 */
package main.java.com.mrhampson.ann4j;

public class ActivationFunctions {
  
  private ActivationFunctions() {}
  
  public static double sigmoid(double x) {
    return 1 / (1 + Math.pow(Math.E, -x));
  }
}
