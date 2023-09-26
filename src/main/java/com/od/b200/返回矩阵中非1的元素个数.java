package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/131365762
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.LinkedList;
import java.util.Scanner;

public class 返回矩阵中非1的元素个数 {
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

        matrix[0][0] = 1;

        System.out.println(getResult(m, n, matrix));
    }

    public static int getResult(int m, int n, int[][] matrix) {
        // 上、下、左、右偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 广搜队列
        LinkedList<int[]> queue = new LinkedList<>();

        // 初始时只有矩阵[0,0]位置元素为1
        queue.add(new int[] {0, 0});

        // count记录矩阵中值为1的元素的个数
        int count = 1;

        // 广搜
        while (queue.size() > 0) {
            int[] pos = queue.removeFirst();

            int x = pos[0];
            int y = pos[1];

            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == 0) {
                    matrix[newX][newY] = 1;
                    count++;
                    queue.add(new int[] {newX, newY});
                }
            }
        }

        return m * n - count;
    }
}