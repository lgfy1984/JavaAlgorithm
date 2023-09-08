package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130764579
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class 模拟消息队列 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] pubArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] subArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        getResult(pubArr, subArr);
    }

    public static void getResult(int[] pubArr, int[] subArr) {
        int n = pubArr.length;
        int m = subArr.length;

        // 将第一行输入两个一组，收集到：发布者数组中
        int[][] publisher = new int[n / 2][];
        for (int i = 0, k = 0; i < n; i += 2) {
            publisher[k++] = new int[]{pubArr[i], pubArr[i + 1]}; // [发布时刻， 发布内容]
        }

        // 将第二行输入两个一组，收集到：订阅者数组中
        int[][] subscriber = new int[m / 2][];
        for (int j = 0, k = 0; j < m; j += 2) {
            subscriber[k++] = new int[]{subArr[j], subArr[j + 1]}; // [订阅时刻， 取消订阅时刻]
        }

        // 按照发布时刻升序：发布者数组
        Arrays.sort(publisher, (a, b) -> a[0] - b[0]);

        // 为每一个订阅者构造一个的订阅内容集合
        ArrayList<ArrayList<Integer>> subContent = new ArrayList<>();
        for (int j = 0; j < subscriber.length; j++) subContent.add(new ArrayList<>());

        // 遍历发布者
        for (int[] pub : publisher) {
            int pubTime = pub[0]; // 发布时刻
            int pubContent = pub[1]; // 发布内容

            // 倒序遍历订阅者，因为后面的订阅者优先级更高，因此倒序可以实现高优先级的订阅者先匹配到发布者
            for (int j = subscriber.length - 1; j >= 0; j--) {
                int subTime = subscriber[j][0]; // 订阅时刻
                int unSubTime = subscriber[j][1]; // 取消订阅时刻

                // 如果 订阅时刻 <= 发布时刻 < 取消订阅时刻
                if (pubTime >= subTime && pubTime < unSubTime) {
                    // 那么该订阅者就可以收到发布的内容
                    subContent.get(j).add(pubContent);
                    // 题目说，发布内容只会被最高优先级的订阅者收到
                    break;
                }
            }
        }

        // 打印
        for (ArrayList<Integer> contents : subContent) {
            if (contents.size() == 0) {
                System.out.println("-1");
            } else {
                StringJoiner sj = new StringJoiner(" ");
                for (Integer content : contents) sj.add(content + "");
                System.out.println(sj.toString());
            }
        }
    }

}
