package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130764272
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.Arrays;
import java.util.Scanner;

public class ∞¢¿Ô∞Õ∞Õ’“ª∆Ω±¶œ‰1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(arr));
    }

    public static int getResult(int[] arr) {
        int leftSum = 0;
        int rightSum = Arrays.stream(arr).sum() - arr[0];

        if (leftSum == rightSum) return 0;

        for (int i = 1; i < arr.length; i++) {
            leftSum += arr[i - 1];
            rightSum -= arr[i];
            if (leftSum == rightSum) return i;
        }

        return -1;
    }

}
