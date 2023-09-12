package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130771177
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.Scanner;


public class 符合要求的元组的个数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());
        int target = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(nums, k, target));
    }

    public static int getResult(int[] nums, int k, int target) {
        if (k > nums.length) return 0;
        Arrays.sort(nums);
        return kSum(nums, k, target, 0, 0, 0);
    }

    // k数之和
    public static int kSum(int[] nums, int k, int target, int start, int count, long sum) {
        if (k < 2) return count;

        if (k == 2) {
            return twoSum(nums, target, start, count, sum);
        }

        for (int i = start; i <= nums.length - k; i++) {
            // 剪枝
            if (nums[i] > 0 && sum + nums[i] > target) break;

            // 去重
            if (i > start && nums[i] == nums[i - 1]) continue;
            count = kSum(nums, k - 1, target, i + 1, count, sum + nums[i]);
        }

        return count;
    }

    // 两数之和
    public static int twoSum(int[] nums, int target, int start, int count, long preSum) {
        int l = start;
        int r = nums.length - 1;

        while (l < r) {
            long sum = preSum + nums[l] + nums[r];

            if (target < sum) {
                r--;
            } else if (target > sum) {
                l++;
            } else {
                count++;
                // 去重
                while (l + 1 < r && nums[l] == nums[l + 1]) l++;
                // 去重
                while (r - 1 > l && nums[r] == nums[r - 1]) r--;
                l++;
                r--;
            }
        }

        return count;
    }
}