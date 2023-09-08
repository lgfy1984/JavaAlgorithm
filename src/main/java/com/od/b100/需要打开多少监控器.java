package com.od.b100;


import java.util.Scanner;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130632804
 *
 * @author l84309057
 * @since 2023/9/6
 */

public class 需要打开多少监控器 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(m, n, matrix));
    }

    public static int getResult(int m, int n, int[][] matrix) {
        int count = 0;
        int[][] offsets = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (matrix[x][y] == 1) {
                    count++;
                    continue;
                }

                for (int[] offset : offsets) {
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == 1) {
                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }

}
