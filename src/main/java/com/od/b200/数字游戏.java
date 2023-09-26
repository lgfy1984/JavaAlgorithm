package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130775047
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Êý×ÖÓÎÏ· {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                int[] tmp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                System.out.println(isExist(nums, tmp[1]));
            } catch (Exception e) {
                break;
            }
        }
    }

    public static int isExist(int[] nums, int m) {
        HashSet<Integer> remain = new HashSet<>();
        remain.add(0);

        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (remain.contains(sum % m)) {
                return 1;
            } else {
                remain.add(sum % m);
            }
        }

        return 0;
    }
}