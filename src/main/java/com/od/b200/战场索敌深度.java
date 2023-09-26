package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130774007
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class 战场索敌深度 {
        static int n; // 地图行数
        static int m; // 地图列数
        static int k; // 区域敌军人数上限值
        static char[][] matrix; // 地图矩阵
        static boolean[][] visited; // 访问矩阵

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int[] tmp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = tmp[0];
            m = tmp[1];
            k = tmp[2];

            visited = new boolean[n][m];

            matrix = new char[n][];
            for (int i = 0; i < n; i++) {
                matrix[i] = sc.nextLine().toCharArray();
            }

            System.out.println(getResult());
        }

        public static int getResult() {
            int ans = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] || matrix[i][j] == '#') continue;
                    // 如果(i,j)位置未访问过，且不是墙，则进入深搜，深搜结果是深搜区域内的敌军数量，如果数量小于k，则该区域符合要求
                    ans += dfs(i, j) < k ? 1 : 0;
                }
            }

            return ans;
        }

        // 上、下、左、右偏移量
        static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public static int dfs(int i, int j) {
            // 该区域敌军数量
            int count = 0;

            // 标记该位置访问过
            visited[i][j] = true;

            // 如果对应位置是E，则敌军数量+1
            if (matrix[i][j] == 'E') count += 1;

            // 深搜依赖于栈结构，后进先出
            LinkedList<int[]> stack = new LinkedList<>();
            stack.add(new int[]{i, j});

            while (stack.size() > 0) {
                int[] pos = stack.removeLast();
                int x = pos[0], y = pos[1];

                // 遍历该位置的上下左右
                for (int[] offset : offsets) {
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // 如果新位置不越界，且未访问过，且不是墙，则继续深搜
                    if (newX >= 0
                            && newX < n
                            && newY >= 0
                            && newY < m
                            && !visited[newX][newY]
                            && matrix[newX][newY] != '#') {
                        // 标记该位置访问过
                        visited[newX][newY] = true;

                        // 如果对应位置是E，则敌军数量+1
                        if (matrix[newX][newY] == 'E') count += 1;

                        stack.add(new int[]{newX, newY});
                    }
                }
            }

            return count;
        }
}