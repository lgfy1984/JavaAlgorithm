package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130768307
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.Scanner;

public class 食堂供餐 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = sc.nextInt();

        System.out.println(getResult(n, m, p));
    }

    public static long getResult(int n, int m, int[] p) {
        int min = 0; // 每个单位时间最少增加盒饭数量
        int max = Arrays.stream(p).max().orElse(0); // 每个单位时间最多增加盒饭数量

        // 记录题解
        int ans = 0;

        // 二分
        while (min <= max) {
            int mid = (min + max) >> 1;

            // 检查每个单位时间增加mid份盒饭，是否可以满足0等待
            if (check(m, mid, p)) {
                // 可以满足的话，则mid就是一个可能解
                ans = mid;
                // 继续查找更优解
                max = mid - 1;
            } else {
                // 不满足的话，则说明mid取小了，下一轮应该取更大的mid
                min = mid + 1;
            }
        }

        return ans;
    }

    public static boolean check(int m, int add, int[] p) {
        m -= p[0]; // P1 ≤ M ≤ 1000，因此这里 m - p[0] 必然大于等于 0

        for (int i = 1; i < p.length; i++) {
            m += add;
            if (m >= p[i]) m -= p[i];
            else return false;
        }

        return true;
    }
}