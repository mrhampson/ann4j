package com.mrhampson.ann4j;

import com.mrhampson.ann4j.util.MutableSupplier;

import java.util.List;
import java.util.Objects;

/**
 * @author Marshall Hampson
 */
public class ANN {
  private final Neuron output;
  private final List<Neuron> hiddenLayer;
  private final List<MutableSupplier<Double>> inputLayer;
  
  public ANN(List<MutableSupplier<Double>> inputLayer, List<Neuron> hiddenLayer, Neuron output) {
    this.inputLayer = inputLayer;
    this.hiddenLayer = hiddenLayer;
    this.output = output;
  }
  
  public void randomizeWeights() {
    output.ramdomizeWeights();
    for (Neuron neuron : hiddenLayer) {
      neuron.ramdomizeWeights();
    }
  }
  
  public void backpropogate(double targetValue) {
    double currentOutput = output.calculateOutput();
    double marginOfError = targetValue - currentOutput;
    
    // TODO generalize this for other activation functions besides sigmoid
    double currentOutputSum = output.getInputs().stream().mapToDouble(input -> input.getNeuron().getMostRecentOutput() * input.getWeight()).sum();
    double deltaOutput = ActivationFunctions.sigmoidDerivative(currentOutputSum) * marginOfError;
    for (WeightInputPair input : output.getInputs()) {
      Neuron neuron = input.getNeuron();
      if (neuron != null) {
        double lastOutput = neuron.getMostRecentOutput();
        double newWeight = input.getWeight() + deltaOutput / lastOutput;
        double oldWeight = input.getWeight();
        input.setWeight(newWeight);
        double inputSum = neuron.getInputs().stream().mapToDouble(pair -> pair.getInputSupplier().get() * pair.getWeight()).sum();
        for (int i = 0 ; i < neuron.getInputs().size(); i++) {
          WeightInputPair weightInputPair = neuron.getInputs().get(i);
          double deltaHiddenSum = deltaOutput / oldWeight * ActivationFunctions.sigmoidDerivative(inputSum);
          weightInputPair.setWeight(weightInputPair.getWeight() + deltaHiddenSum / weightInputPair.getInputSupplier().get());
        }
      }
    }
  }
  
  public void train(double[][] inputData, double[] expectedOuputData, int iterations) {
    if (inputData.length != expectedOuputData.length) {
      throw new IllegalArgumentException("input output size mismatch");
    }
    this.randomizeWeights();
    for (int itrCount = 0; itrCount < iterations; itrCount++) {
      for (int i = 0; i < inputData.length; i++) {
        final double[] inputs = inputData[i];
        final int numInputs = inputLayer.size();
        for (int j = 0; j < inputs.length; j++) {
          if (inputs.length != numInputs) {
            throw new IllegalArgumentException("Invalid input size mismatch");
          }
          this.inputLayer.get(j).set(inputs[j]);
        }
        this.backpropogate(expectedOuputData[i]);
      }
    }
  }

  public double predict(List<Double> inputVals) {
    Objects.requireNonNull(inputVals);
    if (inputVals.size() != this.inputLayer.size()) {
      throw new IllegalArgumentException("Invalid input size");
    }
    for (int i = 0; i < inputVals.size(); i++) {
      this.inputLayer.get(i).set(inputVals.get(i));
    }
    return this.output.calculateOutput();
  }
}
