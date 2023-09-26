package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130774524
 *
 * @author l84309057
 * @since 2023/9/19
 */

import java.util.ArrayList;
import java.util.Scanner;
public class 宜居星球改造计划 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String[]> matrix = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if ("".equals(line)) {
                System.out.println(getResult(matrix));
                break;
            } else {
                matrix.add(line.split(" "));
            }
        }
    }

    private static int getResult(ArrayList<String[]> matrix) {
        int row = matrix.size();
        int col = matrix.get(0).length;

        // 记录宜居取坐标位置
        ArrayList<int[]> queue = new ArrayList<>();

        // 记录可改造区数量
        int need = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                String val = matrix.get(i)[j];
                switch (val) {
                    case "YES":
                        queue.add(new int[] {i, j});
                        break;
                    case "NO":
                        need++;
                        break;
                }
            }
        }

        // 如果没有宜居区，则无法改造，直接返回-1
        if (queue.size() == 0) return -1;
        // 如果全是宜居区，则无需改造，直接返回0
        if (queue.size() == row * col) return 0;

        // 记录花费的天数
        int day = 0;

        // 上，下，左，右四个方向的偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 图的多源BFS模板
        while (queue.size() > 0 && need > 0) {
            ArrayList<int[]> newQueue = new ArrayList<>();

            for (int[] pos : queue) {
                int x = pos[0], y = pos[1];
                for (int[] offset : offsets) {
                    // 上，下，左，右四个方向扩散
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // 如果新位置没有越界，且为NO，则可以被改造
                    if (newX >= 0
                            && newX < row
                            && newY >= 0
                            && newY < col
                            && "NO".equals(matrix.get(newX)[newY])) {
                        matrix.get(newX)[newY] = "YES";
                        newQueue.add(new int[] {newX, newY});
                        need--;
                    }
                }
            }

            day++;
            queue = newQueue;
        }

        if (need == 0) return day;
        else return -1;
    }
}