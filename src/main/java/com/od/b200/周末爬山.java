package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130774056
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.HashMap;
import java.util.Scanner;

public class 周末爬山 {
    static int m;
    static int n;
    static int k;
    static int[][] matrix;
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 标记某个山峰已经访问过了, 由于山峰高度范围0~9,而k范围1~4, 则最大山峰高度为9+4 = 13, 因此访问过的山峰被重置为15的话，则该山峰必然不可能被再次访问
    static final int VISITED = 15;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();

        matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult());
    }

    public static String getResult() {
        // key表示山峰高度，value表示到达此山峰高度的最短步数
        HashMap<Integer, Integer> minStepToHeight = new HashMap<>();
        // 到达matrix[0][0]高度的山峰，最短步数是0
        minStepToHeight.put(matrix[0][0], 0);

        // 深搜
        dfs(0, 0, 0, minStepToHeight);

        // 取得最大高度
        int maxHeight = minStepToHeight.keySet().stream().max((a, b) -> a - b).orElse(0);
        // 取得最大高度对应的最短步数
        int minStep = minStepToHeight.get(maxHeight);

        return maxHeight + " " + minStep;
    }

    public static void dfs(int x, int y, int step, HashMap<Integer, Integer> minStepToHeight) {
        // 上一个山峰的高度
        int lastHeight = matrix[x][y];

        // 四个方向运动
        for (int[] offset : offsets) {
            // 下一个山峰的位置
            int newX = x + offset[0];
            int newY = y + offset[1];

            if (newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

            // 下一个山峰的高度
            int curHeight = matrix[newX][newY];

            // 如果两个山峰高度相差k以内
            if (Math.abs(curHeight - lastHeight) <= k) {
                // 则下一个山峰可以去，即步数+1
                step++;

                // 更新到达curHeight高度山峰的最短步数
                if (!minStepToHeight.containsKey(curHeight) || minStepToHeight.get(curHeight) > step) {
                    minStepToHeight.put(curHeight, step);
                }

                // 标记x,y位置的山峰已经爬过, 防止被重复爬
                matrix[x][y] = VISITED;

                // 深搜
                dfs(newX, newY, step, minStepToHeight);

                // 深搜完对应分支，则还原上一个山峰的高度
                matrix[x][y] = lastHeight;

                // 回退步数
                step--;
            }
        }
    }
}