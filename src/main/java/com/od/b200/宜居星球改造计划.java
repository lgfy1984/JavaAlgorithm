package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130774524
 *
 * @author l84309057
 * @since 2023/9/19
 */

import java.util.ArrayList;
import java.util.Scanner;
public class �˾��������ƻ� {
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

        // ��¼�˾�ȡ����λ��
        ArrayList<int[]> queue = new ArrayList<>();

        // ��¼�ɸ���������
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

        // ���û���˾��������޷����죬ֱ�ӷ���-1
        if (queue.size() == 0) return -1;
        // ���ȫ���˾�������������죬ֱ�ӷ���0
        if (queue.size() == row * col) return 0;

        // ��¼���ѵ�����
        int day = 0;

        // �ϣ��£������ĸ������ƫ����
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // ͼ�Ķ�ԴBFSģ��
        while (queue.size() > 0 && need > 0) {
            ArrayList<int[]> newQueue = new ArrayList<>();

            for (int[] pos : queue) {
                int x = pos[0], y = pos[1];
                for (int[] offset : offsets) {
                    // �ϣ��£������ĸ�������ɢ
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // �����λ��û��Խ�磬��ΪNO������Ա�����
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