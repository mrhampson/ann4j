package com.mrhampson.ann4j;

import com.mrhampson.ann4j.util.MutableSupplier;

import java.util.function.Supplier;

/**
 * @author Marshall Hampson
 */
public class WeightInputPair {
  private Neuron neuron;
  private Supplier<Double> input;
  private double weight;

  public WeightInputPair(Supplier<Double> input, double weight, Neuron neuron) {
    this.input = input;
    this.weight = weight;
    this.neuron = neuron;
  }

  public Neuron getNeuron() {
    return neuron;
  }

  public Supplier<Double> getInputSupplier() {
    return input;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}
