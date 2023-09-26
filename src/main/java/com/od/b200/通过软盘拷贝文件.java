package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130765207
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Scanner;

public class 通过软盘拷贝文件 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        System.out.println(getResult(n, arr));
    }

    public static int getResult(int n, int[] arr) {
        int bag = 1474560 / 512; // 背包承重（块）

        int[] dp = new int[bag + 1];

        for (int i = 0; i < n; i++) {
            int weight = (int) Math.ceil(arr[i] / 512.0); // 物品的重量（块）
            int worth = arr[i]; // 物品的价值（字节）
            for (int j = bag; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + worth);
            }
        }

        return dp[bag];
    }
}