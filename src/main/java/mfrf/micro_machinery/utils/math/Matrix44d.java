package mfrf.micro_machinery.utils.math;

import org.joml.Vector4d;

public class Matrix44d {
    private double[][] data;
    private int rows;
    private int columns;

    public Matrix44d(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    public Matrix44d(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.columns = data[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getValue(int i, int j) {
        return data[i][j];
    }

    public void setValue(int i, int j, double value) {
        data[i][j] = value;
    }

    public void swapRows(int row1, int row2) {
        if (row1 < 0 || row1 >= rows || row2 < 0 || row2 >= rows) {
            throw new IllegalArgumentException("行索引超出范围");
        }

        double[] temp = data[row1];
        data[row1] = data[row2];
        data[row2] = temp;
    }

    public Matrix44d multiply(Matrix44d vector) {
        Matrix44d result = new Matrix44d(rows, 1);

        result.setValue(0, 0,
                data[0][0] * vector.getValue(0, 0) +
                        data[0][1] * vector.getValue(1, 0) +
                        data[0][2] * vector.getValue(2, 0) +
                        data[0][3] * vector.getValue(3, 0));

        result.setValue(1, 0,
                data[1][0] * vector.getValue(0, 0) +
                        data[1][1] * vector.getValue(1, 0) +
                        data[1][2] * vector.getValue(2, 0) +
                        data[1][3] * vector.getValue(3, 0));

        result.setValue(2, 0,
                data[2][0] * vector.getValue(0, 0) +
                        data[2][1] * vector.getValue(1, 0) +
                        data[2][2] * vector.getValue(2, 0) +
                        data[2][3] * vector.getValue(3, 0));

        result.setValue(3, 0,
                data[3][0] * vector.getValue(0, 0) +
                        data[3][1] * vector.getValue(1, 0) +
                        data[3][2] * vector.getValue(2, 0) +
                        data[3][3] * vector.getValue(3, 0));

        return result;
    }

    public Matrix44d add(Matrix44d matrix44d) {
        Matrix44d result = new Matrix44d(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.setValue(i, j, data[i][j] + matrix44d.getValue(i, j));
            }
        }
        return result;
    }

    public Matrix44d minus(Matrix44d matrix44d) {
        Matrix44d result = new Matrix44d(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.setValue(i, j, data[i][j] - matrix44d.getValue(i, j));
            }
        }
        return result;
    }

    public Vector4d multiplyL(Vector4d vector4d) {
        Vector4d result = new Vector4d();
        result.x = data[0][0] * vector4d.x + data[0][1] * vector4d.y + data[0][2] * vector4d.z + data[0][3] * vector4d.w;
        result.y = data[1][0] * vector4d.x + data[1][1] * vector4d.y + data[1][2] * vector4d.z + data[1][3] * vector4d.w;
        result.z = data[2][0] * vector4d.x + data[2][1] * vector4d.y + data[2][2] * vector4d.z + data[2][3] * vector4d.w;
//        result.w = data[3][0] * vector4d.x + data[3][1] * vector4d.y + data[3][2] * vector4d.z + data[3][3] * vector4d.w;
        result.w = vector4d.w;
        return result;
    }

    public Matrix44d inverse() {
        if (rows != columns) {
            throw new IllegalArgumentException("矩阵必须是方阵");
        }

        // 创建一个新的矩阵作为逆矩阵
        Matrix44d inverseMatrix44d = new Matrix44d(rows, columns);

        // 将当前矩阵和单位矩阵拼接成增广矩阵
        Matrix44d augmentedMatrix44d = new Matrix44d(rows, 2 * columns);

        // 初始化增广矩阵，左边部分为当前矩阵，右边部分为单位矩阵
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                augmentedMatrix44d.setValue(i, j, data[i][j]);
            }
            augmentedMatrix44d.setValue(i, i + columns, 1);
        }

        // 利用高斯消元法将增广矩阵转化为行简化阶梯型
        for (int i = 0; i < rows; i++) {
            // 检查对角线元素是否为0，若是则进行行交换
            if (augmentedMatrix44d.getValue(i, i) == 0) {
                int swapRow = -1;
                for (int k = i + 1; k < rows; k++) {
                    if (augmentedMatrix44d.getValue(k, i) != 0) {
                        swapRow = k;
                        break;
                    }
                }
                if (swapRow == -1) {
                    throw new IllegalArgumentException("该矩阵不可逆");
                }
                augmentedMatrix44d.swapRows(i, swapRow);
            }

            // 将对角线元素设为1
            double divisor = augmentedMatrix44d.getValue(i, i);
            for (int j = 0; j < 2 * columns; j++) {
                augmentedMatrix44d.setValue(i, j, (int) (augmentedMatrix44d.getValue(i, j) / divisor));
            }

            // 利用行消元，将其他行的对应列元素设为0
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    double factor = augmentedMatrix44d.getValue(k, i);
                    for (int j = 0; j < 2 * columns; j++) {
                        double newValue = augmentedMatrix44d.getValue(k, j) - factor * augmentedMatrix44d.getValue(i, j);
                        augmentedMatrix44d.setValue(k, j, (int) newValue);
                    }
                }
            }
        }

        // 提取逆矩阵，即增广矩阵右边部分
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                inverseMatrix44d.setValue(i, j, augmentedMatrix44d.getValue(i, j + columns));
            }
        }

        return inverseMatrix44d;
    }

}
