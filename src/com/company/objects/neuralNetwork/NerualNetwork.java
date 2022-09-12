package com.company.objects.neuralNetwork;

import java.util.ArrayList;

public class NerualNetwork {
    int inputSize;
    Matrix input;
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
        for (int i = 0; i < weights.size(); i++) {
            this.input = inputMatrix;
            inputMatrix = Matrix.dot(inputMatrix, weights.get(i));
            inputMatrix.add(biases.get(i));
            inputMatrix.sigmoid();
            this.hiddenLayerStore.add(inputMatrix);

            //not outputing a 1X1 matrix as it should in the given case in main
        }
        return inputMatrix;
    }


    public double error(Matrix result, Matrix expectedResult) {
        Matrix resultMinusExpected = Matrix.subtract(result, expectedResult);
        resultMinusExpected.power(2);
        return 0.5 * resultMinusExpected.sum();
    }

    public void backwardPropagation(Matrix result, Matrix expectedResult, double learningRate) {
        Matrix derivativeBiases2 = Matrix.subtract(result, expectedResult);
        derivativeBiases2.multiply(result.dsigmoid().dsigmoid());

        Matrix derivativeWeights2 = Matrix.dot(derivativeBiases2, this.hiddenLayerStore.get(0));

        Matrix derivativeBiases1 = Matrix.dot(Matrix.multiply(derivativeBiases2, this.weights.get(1)), this.hiddenLayerStore.get(0).dsigmoid().dsigmoid());

        Matrix derivativeWeights1 = Matrix.dot(derivativeBiases1, this.input);

        //gradient decente
        derivativeBiases1.multiply(-learningRate);
        this.biases.get(0).add(derivativeBiases1);

        derivativeWeights1.multiply(-learningRate);
        this.weights.get(0).add(derivativeWeights1);

        derivativeBiases2.multiply(-learningRate);
        this.biases.get(1).add(derivativeBiases2);

        derivativeWeights2.multiply(-learningRate);
        this.weights.get(1).add(derivativeWeights2);
    }

    public void train(int epochs, double learningRate, ArrayList<Matrix> traingingDataX,
                      ArrayList<Matrix> traingingDataY) {
        for (int i = 0; i < epochs; i++) {
            double error = 0;
            for (int j = 0; j < traingingDataX.size(); j++) {
                Matrix result = feedForward(traingingDataX.get(j));
                System.out.println("RESULT");
                System.out.println(result);
                Matrix expectedResult = traingingDataY.get(j);
                error += error(result, expectedResult);
                backwardPropagation(result, expectedResult, learningRate);
            }
            System.out.println("INFO: EPOCH: " + i + 1 + " ERROR: " + error);
        }
    }

    public static void main(String[] args) {
        NerualNetwork net = new NerualNetwork(2, 0, 4, 1);

        ArrayList<Matrix> traingingDataX = new ArrayList<>();
        Matrix xin = Matrix.fromArray(new double[]{0, 0});
        traingingDataX.add(xin);
        xin = Matrix.fromArray(new double[]{0, 1});
        traingingDataX.add(xin);
        xin = Matrix.fromArray(new double[]{1, 0});
        traingingDataX.add(xin);
        xin = Matrix.fromArray(new double[]{1, 1});
        traingingDataX.add(xin);

        ArrayList<Matrix> traingingDataY = new ArrayList<>();
        Matrix yin = Matrix.fromArray(new double[]{0});
        traingingDataY.add(yin);
        yin = Matrix.fromArray(new double[]{1});
        traingingDataY.add(yin);
        yin = Matrix.fromArray(new double[]{1});
        traingingDataY.add(yin);
        yin = Matrix.fromArray(new double[]{0});
        traingingDataY.add(yin);

        net.train(10000, 0.1, traingingDataX, traingingDataY);
    }

}

//add a save function that saves the weights biases, layers, all thats needed
//to load in a trained neural network (and a constructor to train it and a
//function thats basically just feedforward to give an output from a trained network)

//expand the neural network so that one can control the size of each hidden layer
//and also add iteration that is based on the given size to all the fucntions so
//that the amount of layers isnt static

//the maths for this neural network (the most basic feedforward neural
//network) is from https://medium.com/swlh/mathematics-behind-basic-feed-forward-neural-network-3-layers-python-from-scratch-df88085c8049

