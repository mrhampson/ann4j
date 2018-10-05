/*
 * WeightInputPair.java
 * Created on Oct 05, 2018, 11:55 AM
 *
 * Copyright 2008-2018 LiveAction, Incorporated. All Rights Reserved.
 * 3500 W Bayshore Road, Palo Alto, California 94303, U.S.A.
 *
 * This software is the confidential and proprietary information
 * of LiveAction ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with LiveAction.
 */
package com.mrhampson.ann4j;

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

  public Supplier<Double> getInput() {
    return input;
  }

  public void setInput(Supplier<Double> input) {
    this.input = input;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}
