package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130766629
 *
 * @author l84309057
 * @since 2023/9/6
 */

import java.util.*;
import java.util.stream.Collectors;

public class 阿里巴巴找黄金宝箱2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(nums));
    }

    public static int getResult(int[] nums) {
        // 箱子上贴的数字可以看出 类别
        // 统计每一个类别出现的次数，key是类别，value是类别出现次数
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // half是箱子数量的一半，注意向上取整，因为比如箱子有7个，则销毁一半及以上的箱子数量至少是4个，而不是3个
        int half = (int) Math.ceil(nums.length / 2.0);

        // 按类别出现次数降序 类别
        List<Map.Entry<Integer, Integer>> collect =
                count.entrySet().stream()
                        .sorted((a, b) -> b.getValue() - a.getValue())
                        .collect(Collectors.toList());

        // remove记录销毁的箱子数量
        int remove = 0;
        // numCount记录销毁的类别数量
        int numCount = 0;
        // 贪心思维,想要销毁最多的箱子数，又要销毁的箱子的类别数最少，则应该尽可能销毁出现次数多的类别，因此前面对按照次数降序了类别
        for (Map.Entry<Integer, Integer> entry : collect) {
            remove += entry.getValue();
            numCount++;
            // 一旦达标，则返回销毁的类别数量
            if (remove >= half) {
                return numCount;
            }
        }

        return -1; // 走不到此行，仅用于代码健壮性
    }

}
