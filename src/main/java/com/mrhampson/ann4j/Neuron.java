package com.mrhampson.ann4j;

import com.mrhampson.ann4j.util.MutableSupplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Marshall Hampson
 */
public class Neuron {
  private final List<WeightInputPair> inputs;
  private final ActivationFunction activationFunction;
  private double mostRecentOutput;
  
  private Neuron(Builder builder) {
    this.activationFunction = builder.activationFunction;
    this.inputs = Collections.unmodifiableList(builder.inputs);
  }
  
  public void ramdomizeWeights() {
    for (WeightInputPair input : inputs) {
      input.setWeight(0.5 * Random.SHARED_INSTANCE.nextGaussian() + 0.5);
    }
  }

  public double getMostRecentOutput() {
    return mostRecentOutput;
  }

  public List<WeightInputPair> getInputs() {
    return this.inputs;
  }

  public double calculateOutput() {
    double sum = inputs.stream()
      .mapToDouble(inputPair -> inputPair.getInputSupplier().get() * inputPair.getWeight())
      .sum();
    mostRecentOutput =  activationFunction.apply(sum);
    return mostRecentOutput;
  }
  
  public static final class Builder {
    private ActivationFunction activationFunction;
    private final List<WeightInputPair> inputs = new ArrayList<>();
    
    public Builder takesInput(Neuron neuron, double weight) {
      Objects.requireNonNull(neuron);
      this.inputs.add(new WeightInputPair(neuron::calculateOutput, weight, neuron));
      return this;
    }
    
    public Builder takesInput(MutableSupplier<Double> input, double weight) {
      Objects.requireNonNull(input);
      this.inputs.add(new WeightInputPair(input, weight, null));
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
