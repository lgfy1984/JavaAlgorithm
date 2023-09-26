package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130633638
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Arrays;
import java.util.Scanner;

public class ×î¼ÑÖ²Ê÷¾àÀë {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        System.out.println(getResult(n, positions, m));
    }

    public static int getResult(int n, int[] positions, int m) {
        Arrays.sort(positions);

        int min = 1, max = positions[n - 1] - positions[0];
        int ans = 0;

        while (min <= max) {
            int mid = (min + max) >> 1;

            if (check(positions, m, mid)) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return ans;
    }

    public static boolean check(int[] positions, int m, int minDis) {
        int count = 1;

        int curPos = positions[0];
        for (int i = 1; i < positions.length; i++) {
            if (positions[i] - curPos >= minDis) {
                count++;
                curPos = positions[i];
            }
        }

        return count >= m;
    }
}