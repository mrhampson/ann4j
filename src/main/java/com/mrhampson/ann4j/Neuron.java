package com.mrhampson.ann4j;

import com.mrhampson.ann4j.utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Marshall Hampson
 */
public class Neuron {
  private final List<Pair<Supplier<Double>, Double>> inputs;
  private final ActivationFunction activationFunction;
  
  private Neuron(Builder builder) {
    this.activationFunction = builder.activationFunction;
    this.inputs = Collections.unmodifiableList(builder.inputs);
  }
  
  public double getOutput() {
    double sum = inputs.stream()
      .mapToDouble(input -> input.getFirst().get() * input.getSecond())
      .sum();
    return activationFunction.apply(sum);
  }
  
  public static final class Builder {
    private ActivationFunction activationFunction;
    private final List<Pair<Supplier<Double>, Double>> inputs = new ArrayList<>();
    
    public Builder takesInput(Neuron neuron, double weight) {
      Objects.requireNonNull(neuron);
      this.inputs.add(Pair.of(neuron::getOutput, weight));
      return this;
    }
    
    public Builder takesInput(Supplier<Double> input, double weight) {
      Objects.requireNonNull(input);
      this.inputs.add(Pair.of(input, weight));
      return this;
    }
    
    public Builder withActivationFunction(ActivationFunction activationFunction) {
      Objects.requireNonNull(activationFunction);
      this.activationFunction = activationFunction;
      return this;
    }
    
    public Neuron build() {
      return new Neuron(this);
    }
  }
}
