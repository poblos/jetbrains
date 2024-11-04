package pl.edu.mimuw.matrix;

import java.util.ArrayList;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class Diagonal extends SingleList {
    Diagonal(ArrayList<Pair> elems, int size) {
        this.elems = elems;
        this.shape = matrix(size, size);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        if (!other.getClass().equals(this.getClass())) {
            return super.times(other);
        } else {
            ArrayList<Pair> elems1 = this.elems;
            int length1 = elems1.size();
            int i1 = 0;
            ArrayList<Pair> elems2 = ((Diagonal) other).getElems();
            int length2 = elems2.size();
            int i2 = 0;
            ArrayList<Pair> elems3 = new ArrayList<>();
            while (i1 < length1 && i2 < length2) {
                int ix1 = elems1.get(i1).index();
                double v1 = elems1.get(i1).value();
                int ix2 = elems2.get(i2).index();
                double v2 = elems2.get(i2).value();
                if (ix1 == ix2) {
                    Pair newPair = new Pair(ix1, v1 * v2);
                    elems3.add(newPair);
                    i1++;
                    i2++;
                } else if (ix1 < ix2) {
                    i1++;
                } else {
                    i2++;
                }
            }
            return (new Diagonal(elems3, this.shape().rows));
        }
    }

    public IDoubleMatrix times(double scalar) {
        ArrayList<Pair> elems = super.timesList(scalar);
        return (new Diagonal(elems, this.shape().rows));
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        if (other.getClass() != this.getClass()) {
            return super.plus(other);
        } else {
            ArrayList<Pair> elems2 = super.plusToList(other);
            return (new Diagonal(elems2, this.shape().rows));
        }
    }

    public double get(int row, int column) {
        if (row != column) {
            return (0);
        }
        return (super.get(row, column));
    }

    public double[][] data() {
        double[][] resMatrix = new double[this.shape().rows][this.shape().columns];
        for (Pair elem : this.elems) {
            int k = elem.index();
            resMatrix[k][k] = elem.value();
        }
        return (resMatrix);
    }

    public double normOne() {
        return (super.normInfinity());
    }

    public String toString() {
        if (this.shape().rows < 4) {
            return (super.toString());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.shape().rows; i++) {
            double value = this.get(i, i);
            if (value == 0.0) {
                sb.append("0 ... 0\n");
                continue;
            }
            StringBuilder row = new StringBuilder();
            if (i > 2) {
                row.append("0 ... 0 ");
            } else {
                row.append("0 ".repeat(i));
            }
            row.append(value);
            if (i < this.shape().rows - 3) {
                row.append(" 0 ... 0");
            } else {
                row.append(" 0 ".repeat(Math.max(0, this.shape().rows - 1 - i)));
            }
            sb.append(row).append('\n');
        }
        return ("Diagonalna " + this.shape().toString() + "\n" + sb);
    }
}
