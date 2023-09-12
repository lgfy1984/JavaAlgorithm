package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130768307
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.Scanner;

public class ʳ�ù��� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = sc.nextInt();

        System.out.println(getResult(n, m, p));
    }

    public static long getResult(int n, int m, int[] p) {
        int min = 0; // ÿ����λʱ���������Ӻз�����
        int max = Arrays.stream(p).max().orElse(0); // ÿ����λʱ��������Ӻз�����

        // ��¼���
        int ans = 0;

        // ����
        while (min <= max) {
            int mid = (min + max) >> 1;

            // ���ÿ����λʱ������mid�ݺз����Ƿ��������0�ȴ�
            if (check(m, mid, p)) {
                // ��������Ļ�����mid����һ�����ܽ�
                ans = mid;
                // �������Ҹ��Ž�
                max = mid - 1;
            } else {
                // ������Ļ�����˵��midȡС�ˣ���һ��Ӧ��ȡ�����mid
                min = mid + 1;
            }
        }

        return ans;
    }

    public static boolean check(int m, int add, int[] p) {
        m -= p[0]; // P1 �� M �� 1000��������� m - p[0] ��Ȼ���ڵ��� 0

        for (int i = 1; i < p.length; i++) {
            m += add;
            if (m >= p[i]) m -= p[i];
            else return false;
        }

        return true;
    }
}