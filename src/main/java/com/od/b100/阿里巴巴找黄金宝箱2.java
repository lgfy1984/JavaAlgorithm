package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130766629
 *
 * @author l84309057
 * @since 2023/9/6
 */

import java.util.*;
import java.util.stream.Collectors;

public class ����Ͱ��һƽ���2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] nums) {
        // �������������ֿ��Կ��� ���
        // ͳ��ÿһ�������ֵĴ�����key�����value�������ִ���
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // half������������һ�룬ע������ȡ������Ϊ����������7����������һ�뼰���ϵ���������������4����������3��
        int half = (int) Math.ceil(nums.length / 2.0);

        // �������ִ������� ���
        List<Map.Entry<Integer, Integer>> collect =
                count.entrySet().stream()
                        .sorted((a, b) -> b.getValue() - a.getValue())
                        .collect(Collectors.toList());

        // remove��¼���ٵ���������
        int remove = 0;
        // numCount��¼���ٵ��������
        int numCount = 0;
        // ̰��˼ά,��Ҫ������������������Ҫ���ٵ����ӵ���������٣���Ӧ�þ��������ٳ��ִ������������ǰ��԰��մ������������
        for (Map.Entry<Integer, Integer> entry : collect) {
            remove += entry.getValue();
            numCount++;
            // һ����꣬�򷵻����ٵ��������
            if (remove >= half) {
                return numCount;
            }
        }

        return -1; // �߲������У������ڴ��뽡׳��
    }

}
