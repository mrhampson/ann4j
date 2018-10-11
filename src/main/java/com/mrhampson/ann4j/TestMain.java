/*
 * TestMain.java
 * Created on Oct 05, 2018, 10:56 AM
 */
package com.mrhampson.ann4j;

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
    
    Network network = new Network(Arrays.asList(input1, input2), Arrays.asList(hidden1, hidden2, hidden3), output);
    
    System.out.println("Initial output: " + output.calculateOutput());
    
    network.backpropogate(0);
    System.out.println("After backprop 1 output: " + output.calculateOutput());
  }
}
