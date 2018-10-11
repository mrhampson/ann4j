package com.mrhampson.ann4j;

public class ActivationFunctions {
  
  private ActivationFunctions() {}
  
  public static double sigmoid(double x) {
    return 1 / (1 + Math.pow(Math.E, -x));
  }
  
  public static double sigmoidDerivative(double x) {
    double sigmoidResult = sigmoid(x);
    return sigmoidResult * (1 - sigmoidResult);
  }
  
  public static double tanh(double x) {
    return (Math.pow(Math.E, 2 * x) - 1) / (Math.pow(Math.E, 2 * x) + 1);
  }
  
  public static double relu(double x) {
    return x <= 0 ? 0 : x;
  }
}
