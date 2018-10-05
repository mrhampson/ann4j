/*
 * Network.java
 * Created on Oct 05, 2018, 11:52 AM
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

import java.util.List;

/**
 * @author Marshall Hampson
 */
public class Network {
  private final Neuron output;
  private final List<Neuron> hiddenLayer;
  private final List<WeightInputPair> inputLayer;
  
  public Network(List<WeightInputPair> inputLayer, List<Neuron> hiddenLayer, Neuron output) {
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
    double deltaOutput = ActivationFunctions.sigmoidDerivative(currentOutput) * marginOfError;
    for (WeightInputPair input : output.getInputs()) {
      Neuron neuron = input.getNeuron();
      if (neuron != null) {
        double lastOutput = neuron.getMostRecentOutput();
        double newWeight = deltaOutput / lastOutput;
        // TODO finish
      }
    }
  }
}
