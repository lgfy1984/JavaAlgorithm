package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130767673
 *
 * @author l84309057
 * @since 2023/9/12
 */

import java.util.Scanner;

public class 求最小步数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(getResult(n));
    }

    public static int getResult(int n) {
        if (n == 1) return 2; // -2 + 3
        if (n == 2) return 1; // 2
        if (n == 3) return 1; // 3

        int base = 2;
        return (n - 4) / 3 + base;
    }
}