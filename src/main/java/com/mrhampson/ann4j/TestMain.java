/*
 * TestMain.java
 * Created on Oct 05, 2018, 10:56 AM
 */
package com.mrhampson.ann4j;

import com.mrhampson.ann4j.utils.Pair;

import java.util.Arrays;
import java.util.function.Supplier;

public class TestMain {
  
  public static void main(String[] args) {
    Supplier<Double> input1 = () -> 1d;
    Supplier<Double> input2 = () -> 1d;
    
    Neuron hidden1 = new Neuron.Builder()
      .withActivationFunction(ActivationFunctions::sigmoid)
      .takesInput(input1, 0.8)
      .takesInput(input2, 0.2)
      .build();
    
    Neuron hidden2 = new Neuron.Builder()
      .withActivationFunction(ActivationFunctions::sigmoid)
      .takesInput(input1, 0.4)
      .takesInput(input2, 0.9)
      .build();
    
    Neuron hidden3 = new Neuron.Builder()
      .withActivationFunction(ActivationFunctions::sigmoid)
      .takesInput(input1, 0.3)
      .takesInput(input2, 0.5)
      .build();
    
    Neuron output = new Neuron.Builder()
      .withActivationFunction(ActivationFunctions::sigmoid)
      .takesInput(hidden1, 0.3)
      .takesInput(hidden2, 0.5)
      .takesInput(hidden3, 0.9)
      .build();
    
    System.out.println("Final output: " + output.getOutput());
  }
}
