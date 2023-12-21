package top.iyuhao.algorithm.factorial;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2023/10/28
 * 杨辉三角
 **/
public class PascalTriangle {
    private final int[][] pascalTriangle;
    private final int[] row;
    private final int n;

    public PascalTriangle(int n) {
        pascalTriangle = new int[n][];
        row = new int[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            pascalTriangle[i] = new int[i + 1];
            Arrays.fill(pascalTriangle[i], -1);
        }
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle1 = new PascalTriangle(10);
        pascalTriangle1.print();
        pascalTriangle1.createPrint();
    }

    private int element(int i, int j) {
        if (pascalTriangle[i][j] != -1) {
            return pascalTriangle[i][j];
        }
        if (i == 0 || j == 0 || i == j) {
            pascalTriangle[i][j] = 1;
            return 1;
        }
        pascalTriangle[i][j] = element(i - 1, j - 1) + element(i - 1, j);
        return pascalTriangle[i][j];
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            printSpace(i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(i, j));
            }
            System.out.println();
        }
    }

    /**
     * 生成一行
     * @param row 一行
     * @param i 行数
     */
    private void createRow(int[] row, int i){
        if (i == 0 || i == row.length) {
            row[i] = 1;
            return;
        }
        for (int j = i; j > 0; j--) {
            row[j] = row[j - 1] + row[j];
        }
    }
    public void createPrint(){
        for (int i = 0; i < n; i++) {
            createRow(row, i);
            printSpace(i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", row[j]);
            }
            System.out.println();
        }
    }

    private void printSpace(int i) {
        for (int k = 0; k < (n - 1 - i) * 2; k++) {
            System.out.print(" ");
        }
    }
}
