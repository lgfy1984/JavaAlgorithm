package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130764526
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.Arrays;
import java.util.Scanner;

public class ∞¢¿Ô∞Õ∞Õ’“ª∆Ω±¶œ‰4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(arr, k));
    }

    public static int getResult(int[] arr, int k) {
        int window_sum = 0;

        for (int i = 0; i < k; i++) {
            window_sum += arr[i];
        }

        int ans = window_sum;

        for (int i = 1; i <= arr.length - k; i++) {
            window_sum -= arr[i - 1];
            window_sum += arr[i + k - 1];
            ans = Math.max(ans, window_sum);
        }

        return ans;
    }

}
