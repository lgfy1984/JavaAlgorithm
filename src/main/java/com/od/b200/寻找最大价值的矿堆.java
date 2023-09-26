package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130774841
 *
 * @author l84309057
 * @since 2023/9/19
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
public class Ѱ������ֵ�Ŀ�� {
    // ��ͼ����
    static ArrayList<ArrayList<Integer>> matrix;

    // ��¼��ͼ���������row������col
    static int row;
    static int col;

    // �������ң��ĸ������ƫ����
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        matrix = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            // ���ڱ���û��˵�������ֹ���������ʹ�ÿ�����Ϊ�����ֹ����
            if ("".equals(line)) {
                System.out.println(getResult());
                break;
            } else {
                matrix.add(
                        new ArrayList<>(
                                Arrays.stream(line.split("")).map(Integer::parseInt).collect(Collectors.toList())));
            }
        }
    }

    public static int getResult() {
        row = matrix.size();
        if (row == 0) return 0;

        col = matrix.get(0).size();

        // ��¼����Ѽ�ֵ
        int ans = 0;

        // ��������Ԫ��
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // �����(i,j)û�б����ʹ����ҵ�(i,j)���п����������
                if (matrix.get(i).get(j) > 0) {
                    ans = Math.max(ans, dfs(i, j));
                }
            }
        }

        return ans;
    }

    public static int dfs(int i, int j) {
        int sum = matrix.get(i).get(j);
        matrix.get(i).set(j, 0);

        LinkedList<int[]> stack = new LinkedList<>();
        stack.add(new int[] {i, j});

        while (stack.size() > 0) {
            int[] pos = stack.removeLast();
            int x = pos[0], y = pos[1];

            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                if (newX >= 0 && newX < row && newY >= 0 && newY < col && matrix.get(newX).get(newY) > 0) {
                    sum += matrix.get(newX).get(newY);
                    matrix.get(newX).set(newY, 0);
                    stack.add(new int[] {newX, newY});
                }
            }
        }

        return sum;
    }
}