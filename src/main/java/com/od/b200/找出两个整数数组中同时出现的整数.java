package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130764650
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.*;

public class 找出两个整数数组中同时出现的整数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr1 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        getResult(arr1, arr2);
    }

    public static void getResult(int[] arr1, int[] arr2) {
        // 统计arr1中各数字出现次数
        HashMap<Integer, Integer> countMap1 = new HashMap<>();
        for (int num : arr1) {
            countMap1.put(num, countMap1.getOrDefault(num, 0) + 1);
        }

        // 统计arr2中各数字出现次数
        HashMap<Integer, Integer> countMap2 = new HashMap<>();
        for (int num : arr2) {
            countMap2.put(num, countMap2.getOrDefault(num, 0) + 1);
        }

        // arr1和arr2是否存在相同的数字，假设不存在，即noSameNum = true
        boolean noSameNum = true;

        // 统计arr1和arr2的相同的数字，并记录对于数字出现的次数，注意sameCountKeys中次数是键，相同数字是值
        HashMap<Integer, TreeSet<Integer>> sameCountKeys = new HashMap<>();
        for (Integer num : countMap1.keySet()) {
            if (countMap2.containsKey(num)) {
                // 如果数字key在arr1和arr2中都有，则说明存在相同数字，此时noSameNum = false
                noSameNum = false;
                // 整数在两个数组中都出现并目出现次数较少的那个
                int count = Math.min(countMap1.get(num), countMap2.get(num));
                // sameCountKeys中次数是键，相同数字是值
                sameCountKeys.putIfAbsent(count, new TreeSet<>());
                sameCountKeys.get(count).add(num);
            }
        }

        // 如果两个数组之间没有相同数字，则输出"NULL"
        if (noSameNum) {
            System.out.println("NULL");
            return;
        }

        // 否则，按照相同数字的出现次数升序，来打印对应次数下的相同数字列表，注意相同数字列表存入的是TreeSet，因此自然升序
        sameCountKeys.keySet().stream()
                .sorted()
                .forEach(
                        count -> {
                            StringJoiner sj = new StringJoiner(",", count + ":", "");
                            for (Integer num : sameCountKeys.get(count)) {
                                sj.add(num + "");
                            }
                            System.out.println(sj.toString());
                        });
    }
}