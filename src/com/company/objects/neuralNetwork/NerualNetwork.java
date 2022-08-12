package com.company.objects.neuralNetwork;

import java.util.ArrayList;

public class NerualNetwork {
    int inputSize;
    ArrayList<Matrix> weights = new ArrayList<>();
    ArrayList<Matrix> biases = new ArrayList<>();
    ArrayList<Matrix> hiddenLayerStore = new ArrayList<>();
    //for each loop of the training process the result of each
    //hidden layer needs to be used to calculate the amounts each
    //weight matrix needs to change by

    public NerualNetwork(int inputSize, int hiddenAmount, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.weights.add(new Matrix(inputSize, hiddenSize, "r"));
        this.biases.add(new Matrix(1, hiddenSize, "r"));
        this.weights.add(new Matrix(hiddenSize, outputSize, "r"));
        this.biases.add(new Matrix(1, outputSize, "r"));
        //add iteration here to define the layers
    }

    public Matrix feedForward(Matrix inputMatrix) {
        if (inputMatrix.rows == 1 && inputMatrix.cols == this.inputSize) {
            for (int i = 0; i < weights.size(); i++) {
                inputMatrix = Matrix.multiply(inputMatrix, weights.get(i));
                inputMatrix.add(biases.get(i));
                inputMatrix.sigmoid();
                this.hiddenLayerStore.add(inputMatrix);
            }
        }
        return inputMatrix;
    }

    public double error(Matrix output, Matrix expectedOutput){
        Matrix outputMinusExpected = Matrix.subtract(output, expectedOutput);
        outputMinusExpected.power(2);
        return 0.5 * outputMinusExpected.sum();
    }

    public void backProp(Matrix result, Matrix expectedResult){

    }

}

//add a save function that saves the weights biases, layers, all thats needed
//to load in a trained neural network (and a constructor to train it and a
//function thats basically just feedforward to give an output from a trained network)

//expand the neural network so that one can control the size of each hidden layer

//the maths for this neural network (the most basic feedforward neural
//network) is from https://medium.com/swlh/mathematics-behind-basic-feed-forward-neural-network-3-layers-python-from-scratch-df88085c8049

