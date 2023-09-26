package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130774056
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.HashMap;
import java.util.Scanner;

public class ��ĩ��ɽ {
    static int m;
    static int n;
    static int k;
    static int[][] matrix;
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // ���ĳ��ɽ���Ѿ����ʹ���, ����ɽ��߶ȷ�Χ0~9,��k��Χ1~4, �����ɽ��߶�Ϊ9+4 = 13, ��˷��ʹ���ɽ�屻����Ϊ15�Ļ������ɽ���Ȼ�����ܱ��ٴη���
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
        // key��ʾɽ��߶ȣ�value��ʾ�����ɽ��߶ȵ���̲���
        HashMap<Integer, Integer> minStepToHeight = new HashMap<>();
        // ����matrix[0][0]�߶ȵ�ɽ�壬��̲�����0
        minStepToHeight.put(matrix[0][0], 0);

        // ����
        dfs(0, 0, 0, minStepToHeight);

        // ȡ�����߶�
        int maxHeight = minStepToHeight.keySet().stream().max((a, b) -> a - b).orElse(0);
        // ȡ�����߶ȶ�Ӧ����̲���
        int minStep = minStepToHeight.get(maxHeight);

        return maxHeight + " " + minStep;
    }

    public static void dfs(int x, int y, int step, HashMap<Integer, Integer> minStepToHeight) {
        // ��һ��ɽ��ĸ߶�
        int lastHeight = matrix[x][y];

        // �ĸ������˶�
        for (int[] offset : offsets) {
            // ��һ��ɽ���λ��
            int newX = x + offset[0];
            int newY = y + offset[1];

            if (newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

            // ��һ��ɽ��ĸ߶�
            int curHeight = matrix[newX][newY];

            // �������ɽ��߶����k����
            if (Math.abs(curHeight - lastHeight) <= k) {
                // ����һ��ɽ�����ȥ��������+1
                step++;

                // ���µ���curHeight�߶�ɽ�����̲���
                if (!minStepToHeight.containsKey(curHeight) || minStepToHeight.get(curHeight) > step) {
                    minStepToHeight.put(curHeight, step);
                }

                // ���x,yλ�õ�ɽ���Ѿ�����, ��ֹ���ظ���
                matrix[x][y] = VISITED;

                // ����
                dfs(newX, newY, step, minStepToHeight);

                // �������Ӧ��֧����ԭ��һ��ɽ��ĸ߶�
                matrix[x][y] = lastHeight;

                // ���˲���
                step--;
            }
        }
    }
}