package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130770597
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.Scanner;

public class 生日礼物 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 这里replaceAll的原因是：有考友反馈实际考试存在20%的用例输入类似于 [10,20,5] 这种
        int[] cakes =
                Arrays.stream(sc.nextLine().replaceAll("[\\[\\]]", "").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        int[] gifts =
                Arrays.stream(sc.nextLine().replaceAll("[\\[\\]]", "").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        int x = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(cakes, gifts, x));
    }

    public static long getResult(int[] cakes, int[] gifts, int x) {
        Arrays.sort(cakes);

        long ans = 0;
        for (int gift : gifts) {
            if (x <= gift) continue;

            int maxCake = x - gift;
            int i = searchLast(cakes, maxCake);

            if (i >= 0) {
                ans += i + 1;
            } else {
                i = -i - 1;
                ans += i;
            }
        }

        return ans;
    }

    public static int searchLast(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            int midVal = arr[mid];

            if (midVal > target) {
                high = mid - 1;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                // 向右延伸判断，mid是否为target数域的右边界，即最后一次出现的位置
                if (mid == arr.length - 1 || arr[mid] != arr[mid + 1]) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -low - 1; // 找不到则返回插入位置
    }
}