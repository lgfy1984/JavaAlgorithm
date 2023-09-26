package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130161763
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class ��ͬ�������ͼ�ε��ܳ� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Integer[][] lines = new Integer[n][];
        for (int i = 0; i < n; i++) {
            lines[i] =
                    Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        System.out.println(getResult(lines));
    }

    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static String getResult(Integer[][] lines) {
        // ��¼ÿ��ͼ�εı߳�
        ArrayList<Integer> ans = new ArrayList<>();

        // ����һ��64*64�ľ���
        int[][] matrix = new int[64][64];

        // ��64*64�ľ������������
        for (Integer[] line : lines) {
            // ÿ�е�һ�����Ǿ���Ԫ����������
            int num = line[0];

            // ����ÿ����һ�飬��ʾ�������ֵĵ�Ԫ������
            for (int i = 1; i < line.length; i += 2) {
                int x = line[i];
                int y = line[i + 1];
                matrix[x][y] = num; // �������
            }
        }

        // ����ÿ��ͼ�ε�����
        for (Integer[] line : lines) {
            int num = line[0];
            int p = 0; // �ܳ�

            for (int i = 1; i < line.length; i += 2) {
                int x = line[i];
                int y = line[i + 1];

                // ����ͼ����Ԫ�ص���������
                for (int[] offset : offsets) {
                    int offsetX = offset[0];
                    int offsetY = offset[1];

                    int newX = x + offsetX;
                    int newY = y + offsetY;

                    // ���ͼ��Ԫ�ص��ϻ��»������û��Խ��
                    if (newX >= 0 && newX < 64 && newY >= 0 && newY < 64) {
                        // �����ͼ��Ԫ�ص��ϻ��»�����Ҳ���ͼ��ֵ����ô��ǰͼ�εı߳�+1
                        if (matrix[newX][newY] != num) p++;
                    } else {
                        // ���ͼ��Ԫ�ص��ϻ��»������Խ���ˣ���ǰͼ�α߳�Ҳ+1
                        p++;
                    }
                }
            }

            ans.add(p);
        }

        StringJoiner sj = new StringJoiner(" ");
        for (Integer an : ans) {
            sj.add(an + "");
        }

        return sj.toString();
    }
}