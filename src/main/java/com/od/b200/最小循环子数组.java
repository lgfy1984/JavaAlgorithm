package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130773781
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class 最小循环子数组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(n, nums));
    }

    public static String getResult(int n, int[] nums) {
        // KMP算法 前缀表求解
        int[] next = getNext(n, nums);

        // 最长相同前后缀长度
        int m = next[n - 1];

        // 最小重复子串的长度
        int len = n % (n - m) == 0 ? n - m : n;

        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < len; i++) sj.add(nums[i] + "");
        return sj.toString();
    }

    public static int[] getNext(int n, int[] nums) {
        int[] next = new int[n];

        int j = 1;
        int k = 0;

        while (j < n) {
            if (nums[j] == nums[k]) {
                next[j] = k + 1;
                j++;
                k++;
            } else {
                if (k > 0) {
                    k = next[k - 1];
                } else {
                    j++;
                }
            }
        }

        return next;
    }
}