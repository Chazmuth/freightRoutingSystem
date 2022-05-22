package com.company.objects.neuralNetwork;

class Matrix {
    double[][] data;
    int rows, cols;


    public Matrix(int rows, int cols) {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    //initilises a matrix (mathematic 2d number array) - which is essential for machine learning
    // with random values between -1 and 1
    
    public void add(double scaler){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j]+=scaler;
            }
        }
    }

    //adds a double to every number in the matrix

    public void add(Matrix matrix){
        if(cols!=matrix.cols || rows!=matrix.rows) {
            System.out.println("Shape Mismatch");
            return;
        }
        // checks the two matrices are the same size,
        // as one cannot add 2 arrays of
        // different dimensions
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]+=matrix.data[i][j];
            }
        }
    }
}

