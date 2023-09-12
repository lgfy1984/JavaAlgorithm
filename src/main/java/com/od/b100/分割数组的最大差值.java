package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130767443
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.Scanner;

public class 分割数组的最大差值 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        long[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(getResult(nums, n));
    }

    public static long getResult(long[] nums, int n) {
        long leftSum = 0;
        long rightSum = Arrays.stream(nums).sum();

        long maxDiff = 0;

        for (int i = 0; i < n - 1; i++) {
            leftSum += nums[i];
            rightSum -= nums[i];

            long diff = Math.abs(leftSum - rightSum);
            if (diff > maxDiff) maxDiff = diff;
        }

        return maxDiff;
    }
}
