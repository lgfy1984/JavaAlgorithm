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

public class ս��������� {
        static int n; // ��ͼ����
        static int m; // ��ͼ����
        static int k; // ����о���������ֵ
        static char[][] matrix; // ��ͼ����
        static boolean[][] visited; // ���ʾ���

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
                    // ���(i,j)λ��δ���ʹ����Ҳ���ǽ����������ѣ����ѽ�������������ڵĵо��������������С��k������������Ҫ��
                    ans += dfs(i, j) < k ? 1 : 0;
                }
            }

            return ans;
        }

        // �ϡ��¡�����ƫ����
        static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public static int dfs(int i, int j) {
            // ������о�����
            int count = 0;

            // ��Ǹ�λ�÷��ʹ�
            visited[i][j] = true;

            // �����Ӧλ����E����о�����+1
            if (matrix[i][j] == 'E') count += 1;

            // ����������ջ�ṹ������ȳ�
            LinkedList<int[]> stack = new LinkedList<>();
            stack.add(new int[]{i, j});

            while (stack.size() > 0) {
                int[] pos = stack.removeLast();
                int x = pos[0], y = pos[1];

                // ������λ�õ���������
                for (int[] offset : offsets) {
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // �����λ�ò�Խ�磬��δ���ʹ����Ҳ���ǽ�����������
                    if (newX >= 0
                            && newX < n
                            && newY >= 0
                            && newY < m
                            && !visited[newX][newY]
                            && matrix[newX][newY] != '#') {
                        // ��Ǹ�λ�÷��ʹ�
                        visited[newX][newY] = true;

                        // �����Ӧλ����E����о�����+1
                        if (matrix[newX][newY] == 'E') count += 1;

                        stack.add(new int[]{newX, newY});
                    }
                }
            }

            return count;
        }
}