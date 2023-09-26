package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/131019925
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Arrays;
import java.util.Scanner;

public class �������Լ�ı��ݷ��� {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] nums =
                Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(nums));
    }

    public static int getResult(Integer[] nums) {
        // ���ļ���С����
        Arrays.sort(nums, (a, b) -> b - a);

        // ���ٷ���
        int min = 1;
        // �������
        int max = nums.length;

        // ��¼���
        int ans = max;

        // ����
        while (min <= max) {
            int mid = (min + max) >> 1;

            // ��nums�Ƿ���Է�Ϊmid�飬ÿ��֮��С�ڵ���500
            if (check(mid, nums)) {
                // ���Էֳɹ�����mid����һ�����ܽ�
                ans = mid;
                // �������Ը�С��
                max = mid - 1;
            } else {
                // �����Էֳɹ�����midȡ���ˣ����������ˣ��´�Ӧ�ó��Ը��������
                min = mid + 1;
            }
        }

        return ans;
    }

    public static boolean check(int count, Integer[] nums) {
        // nums�����е����Ƿ���Է�Ϊcount�飬ÿ��֮��<=500
        // ����������ʹ�û����㷨����count�������count��Ͱ��ÿ��Ͱ����500��nums����Ԫ�ؾ���С��������Ҫ������С��ŵ�count��Ͱ�У���֤ÿ��Ͱ��������
        int[] buckets = new int[count];
        return partition(buckets, nums, 0);
    }

    public static boolean partition(int[] buckets, Integer[] nums, int index) {
        if (index == nums.length) {
            return true;
        }

        int select = nums[index];
        for (int i = 0; i < buckets.length; i++) {
            if (i > 0 && buckets[i] == buckets[i - 1]) continue; // �˴��жϻἫ���Ż�����

            if (buckets[i] + select <= 500) {
                buckets[i] += select;
                if (partition(buckets, nums, index + 1)) return true;
                buckets[i] -= select;
            }
        }

        return false;
    }
}