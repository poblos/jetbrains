package pl.edu.mimuw.matrix;

import java.util.Arrays;

public abstract class DoubleMatrix implements IDoubleMatrix {
    protected Shape shape;

    public IDoubleMatrix times(IDoubleMatrix other) {
        double[][] matrix3 = new double[this.shape().rows][other.shape().columns];
        for (int i = 0; i < this.shape().rows; i++) {
            for (int j = 0; j < other.shape().columns; j++) {
                double sum = 0;
                for (int k = 0; k < this.shape().columns; k++) {
                    sum += this.data()[i][k] * other.data()[k][j];
                }
                matrix3[i][j] = sum;
            }
        }
        return (new Full(matrix3));
    }

    public IDoubleMatrix times(double scalar) {
        double[][] matrix1 = this.data();
        double[][] matrix2 = new double[this.shape().rows][this.shape().columns];
        for (int i = 0; i < this.shape().rows; i++) {
            for (int j = 0; j < this.shape().columns; j++) {
                matrix2[i][j] = matrix1[i][j] * scalar;
            }
        }
        return (new Full(matrix2));
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        double[][] matrix3 = new double[this.shape().rows][this.shape().columns];
        for (int i = 0; i < this.shape().rows; i++) {
            for (int j = 0; j < this.shape().columns; j++) {
                matrix3[i][j] = this.data()[i][j] + other.data()[i][j];
            }
        }
        return (new Full(matrix3));
    }

    public IDoubleMatrix plus(double scalar) {
        double[][] matrix1 = this.data();
        double[][] matrix2 = new double[this.shape().rows][this.shape().columns];
        for (int i = 0; i < this.shape().rows; i++) {
            for (int j = 0; j < this.shape().columns; j++) {
                matrix2[i][j] = matrix1[i][j] + scalar;
            }
        }
        return (new Full(matrix2));
    }

    public IDoubleMatrix minus(IDoubleMatrix other) {
        return (plus(other.times(-1.0)));
    }

    public IDoubleMatrix minus(double scalar) {
        return (plus(-scalar));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] s : this.data()) {
            sb.append(Arrays.toString(s).replaceAll("[\\[\\],]", "")).append('\n');
        }
        return ("Rozmiar: " + this.shape().toString() + "\n" + sb);
    }

    public Shape shape() {
        return shape;
    }
}
