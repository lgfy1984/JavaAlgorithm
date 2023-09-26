package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130774740
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Arrays;
import java.util.Scanner;


public class Ìø¸ñ×Ó1 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println(getResult(nums));
        }

        public static int getResult(int[] nums) {
            int n = nums.length;

            int[] dp = new int[n];

            if (n >= 1) dp[0] = nums[0];
            if (n >= 2) dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }

            return dp[n - 1];
        }
    }