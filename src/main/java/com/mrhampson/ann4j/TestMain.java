package com.mrhampson.ann4j;

import com.mrhampson.ann4j.util.MutableSupplier;

import java.util.Arrays;

public class TestMain {
  
  public static void main(String[] args) {
    MutableSupplier<Double> input1 = new MutableSupplier<>(1d);
    MutableSupplier<Double> input2 = new MutableSupplier<>(1d);
    
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
    
    ANN network = new ANN(Arrays.asList(input1, input2), Arrays.asList(hidden1, hidden2, hidden3), output);
    
    double[][] testingInputs = {
      {10, 10},
      {9, 9},
      {8, 8},
      {7, 7},
      {6, 6},
      {5, 5},
      {4, 4},
      {3, 3},
      {2, 2},
      {1, 1}
    };
    double[] testingOutputs = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    network.train(testingInputs, testingOutputs, 1_000_000);
    double prediction = network.predict(Arrays.asList(1d, 1d));
    System.out.println("Prediction for 5,5 " + prediction);
  }
}
