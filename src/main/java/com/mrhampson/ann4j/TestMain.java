/*
 * TestMain.java
 * Created on Oct 05, 2018, 10:56 AM
 */
package main.java.com.mrhampson.ann4j;

import main.java.com.mrhampson.ann4j.utils.Pair;

import java.util.Arrays;
import java.util.function.Supplier;

public class TestMain {
  
  public static void main(String[] args) {
    Supplier<Double> input1 = () -> 1d;
    Supplier<Double> input2 = () -> 1d;
    
    Neuron hidden1 = new Neuron(
      ActivationFunctions::sigmoid, 
      Arrays.asList(Pair.of(input1, 0.8), Pair.of(input2, 0.2))
    );
    Neuron hidden2 = new Neuron(
      ActivationFunctions::sigmoid,
      Arrays.asList(Pair.of(input1, 0.4), Pair.of(input2, 0.9))
    );
    Neuron hidden3 = new Neuron(
      ActivationFunctions::sigmoid,
      Arrays.asList(Pair.of(input1, 0.3), Pair.of(input2, 0.5))
    );
    
    Neuron output = new Neuron(
      ActivationFunctions::sigmoid,
      Arrays.asList(Pair.of(hidden1::getOutput, 0.3), Pair.of(hidden2::getOutput, 0.5), Pair.of(hidden3::getOutput, 0.9))
    );
    
    System.out.println("Final output: " + output.getOutput());
  }
}
