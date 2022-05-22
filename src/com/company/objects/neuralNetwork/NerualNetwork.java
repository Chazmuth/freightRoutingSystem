package com.company.objects.neuralNetwork;

public class NerualNetwork {
    Matrix weightsInputToHidden, weightsHiddenToOut,
            biasHidden , biasOut;
    double learnRate=0.01;

    public NerualNetwork(int inputSize, int hiddenSize, int outputSize){
        weightsInputToHidden = new Matrix(hiddenSize, inputSize);
        weightsHiddenToOut = new Matrix(outputSize, hiddenSize);

        biasHidden = new Matrix(hiddenSize, 1);
        biasOut = new Matrix(outputSize, 1);
    }
}
