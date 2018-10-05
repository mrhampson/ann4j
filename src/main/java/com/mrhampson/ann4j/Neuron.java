package com.mrhampson.ann4j;

import com.mrhampson.ann4j.utils.Pair;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Marshall Hampson
 */
public class Neuron {
  private final List<Pair<Supplier<Double>, Double>> inputs;
  private final ActivationFunction activationFunction;
  
  public Neuron(
    ActivationFunction activationFunction,
    List<Pair<Supplier<Double>, Double>> inputs) {
    this.activationFunction = activationFunction;
    this.inputs = inputs;
  }
  
  public double getOutput() {
    double sum = inputs.stream()
      .mapToDouble(input -> input.getFirst().get() * input.getSecond())
      .sum();
    return activationFunction.apply(sum);
  }
}
