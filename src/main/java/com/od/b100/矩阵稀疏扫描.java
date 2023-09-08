package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130764126
 *
 * @author l84309057
 * @since 2023/9/6
 */

import java.util.Arrays;
import java.util.Scanner;

public class æÿ’Ûœ° Ë…®√Ë {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[] rowZeroCount = new int[m];
        int[] colZeroCount = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (sc.nextInt() == 0) {
                    rowZeroCount[i]++;
                    colZeroCount[j]++;
                }
            }
        }

        System.out.println(Arrays.stream(rowZeroCount).filter(val -> val >= n / 2).count());
        System.out.println(Arrays.stream(colZeroCount).filter(val -> val >= m / 2).count());
    }

}
