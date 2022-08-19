package com.company.objects.neuralNetwork;

import com.company.databaseFiles.SQLFunctions;
import com.company.objects.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Matrix {
    double[][] data;
    int rows, cols;

    public Matrix(int rows, int cols, String type) {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        if (type.equals("r")/*inits w/ random number*/) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = Math.random() * 2 - 1;
                }
            }
        }
        if(type.equals("z")/*inits with 0s*/){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = 0;
                }
            }
        }
    }

    //initialises a matrix (mathematic 2d number array) - which is essential for machine learning
    // with random values between -1 and 1 if the type is "R" (random), otherwise it is
    //initialised empty

    //NOTE FOR FUCNTION:
    //empty matrix should only be used when the contents is predetermied, weights
    //hidden layers, and biases should be random

    public Matrix() {

    }

    public void add(double scaler) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] += scaler;
            }
        }
    }

    //adds a double to every number in this matrix

    public void add(Matrix matrix) {
        if (cols != matrix.cols || rows != matrix.rows) {
            System.out.println("Shape Mismatch");
            return;
        }
        // checks the two matrices are the same size,
        // as one cannot add 2 arrays of
        // different dimensions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] += matrix.data[i][j];
            }
        }
    }

    //adds a matrix to this one

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix temp = new Matrix(a.rows, a.cols, "n");
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                temp.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return temp;
    }

    //takes one matrix from another and returns the result

    public void multiply(Matrix a) {
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                this.data[i][j] *= a.data[i][j];
            }
        }
    }

    //multiplies this matrix by another

    public void multiply(double a) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] *= a;
            }
        }

    }

    //multiplies every value in the matrix by a number a

    public void power(double power){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                data[i][j] = Math.pow(data[i][j], power);
            }
        }
    }

    //raises to the power of then double power

    public double sum(){
        double x = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                x += data[i][j];
            }
        }
        return x;
    }

    //makes a sum of the matrix

    public static Matrix transpose(Matrix matrix) {
        Matrix temp = new Matrix(matrix.cols, matrix.rows, "n");
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                temp.data[j][i] = matrix.data[i][j];
            }
        }
        return temp;
    }

    //see https://byjus.com/maths/transpose-of-a-matrix/ for theroy on a
    //matrix transposition, in lay terms it reverses the matrix, and then returns
    //the result

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp = new Matrix(a.rows, b.cols, "n");
        for (int i = 0; i < temp.rows; i++) {
            for (int j = 0; j < temp.cols; j++) {
                double sum = 0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                temp.data[i][j] = sum;
            }
        }
        return temp;
    }

    public static Matrix flatMutliply(Matrix a, Matrix b){
        Matrix temp = new Matrix(a.rows, b.rows, "n");
        for (int i = 0; i < b.rows; i++) {
            for (int j = 0; j < a.rows; j++) {
                temp.data[i][j] = b.data[0][j]*a.data[0][i];
            }
        }
        return temp;
    }

    //multiplies one matrix by another and returns the result

    public static Matrix fromArray(double[] x) {
        Matrix temp = new Matrix(x.length, 1, "n");
        for (int i = 0; i < x.length; i++)
            temp.data[i][0] = x[i];
        return temp;
    }

    //makes an matrix from a given array

    public List<Double> toArray() {
        List<Double> temp = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp.add(data[i][j]);
            }
        }
        return temp;
    }

    //makes a matrix into an array

    //activation functions:

    public void sigmoid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));
        }

    }

    //the sigmoid function takes in data and spreads it from 1 to -1 over
    //a given range, if you put in 100, it would given an output of about 1

    public Matrix dsigmoid() {
        Matrix temp = new Matrix(rows, cols, "n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                temp.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);
        }
        return temp;
    }

    //reverse sigmoid function

    @Override
    public String toString() {
        return Arrays.deepToString(this.data).replace("], ", "]\n").replace("[[", "[").replace("]]", "]");
    }
}

