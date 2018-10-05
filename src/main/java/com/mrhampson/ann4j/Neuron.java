package main.java.com.mrhampson.ann4j;

import main.java.com.mrhampson.ann4j.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Marshall Hampson
 */
public class Neuron {
  private final List<Pair<Neuron, Double>> inputs = new ArrayList<>();
  private final ActivationFunction activationFunction;
  
  public Neuron(ActivationFunction activationFunction) {
    Objects.requireNonNull(activationFunction);
    this.activationFunction = activationFunction;
  }
  
  public double calculateOutput() {
    double sum = inputs.stream()
      .mapToDouble(input -> input.getFirst().calculateOutput() * input.getSecond())
      .sum();
    return activationFunction.apply(sum);
  }
}
