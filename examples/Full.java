package pl.edu.mimuw.matrix;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class Full extends DoubleMatrix {
    double[][] data;

    Full(double[][] values) {
        int length = values[0].length;
        this.shape = matrix(values.length, length);
        this.data = values;
    }

    public double get(int row, int column) {
        return (data[row][column]);
    }

    public double[][] data() {
        return (data);
    }

    public double normOne() {
        double max = 0;
        for (int column = 0; column < this.shape().columns; column++) {
            double sum = 0;
            for (int row = 0; row < this.shape().rows; row++) {
                sum += Math.abs(data[row][column]);
            }
            max = Math.max(sum, max);
        }
        return (max);
    }

    public double normInfinity() {
        double max = 0;
        for (double[] row : this.data) {
            double sum = 0;
            for (double value : row) {
                sum += Math.abs(value);
            }
            max = Math.max(sum, max);
        }
        return (max);
    }

    public double frobeniusNorm() {
        double k = 0;
        for (double[] row : this.data) {
            for (double value : row) {
                k += Math.pow(value, 2.0);
            }
        }
        return (Math.sqrt(k));
    }
}
