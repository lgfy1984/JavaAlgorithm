package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130764650
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.*;

public class �ҳ���������������ͬʱ���ֵ����� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr1 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        getResult(arr1, arr2);
    }

    public static void getResult(int[] arr1, int[] arr2) {
        // ͳ��arr1�и����ֳ��ִ���
        HashMap<Integer, Integer> countMap1 = new HashMap<>();
        for (int num : arr1) {
            countMap1.put(num, countMap1.getOrDefault(num, 0) + 1);
        }

        // ͳ��arr2�и����ֳ��ִ���
        HashMap<Integer, Integer> countMap2 = new HashMap<>();
        for (int num : arr2) {
            countMap2.put(num, countMap2.getOrDefault(num, 0) + 1);
        }

        // arr1��arr2�Ƿ������ͬ�����֣����費���ڣ���noSameNum = true
        boolean noSameNum = true;

        // ͳ��arr1��arr2����ͬ�����֣�����¼�������ֳ��ֵĴ�����ע��sameCountKeys�д����Ǽ�����ͬ������ֵ
        HashMap<Integer, TreeSet<Integer>> sameCountKeys = new HashMap<>();
        for (Integer num : countMap1.keySet()) {
            if (countMap2.containsKey(num)) {
                // �������key��arr1��arr2�ж��У���˵��������ͬ���֣���ʱnoSameNum = false
                noSameNum = false;
                // ���������������ж����ֲ�Ŀ���ִ������ٵ��Ǹ�
                int count = Math.min(countMap1.get(num), countMap2.get(num));
                // sameCountKeys�д����Ǽ�����ͬ������ֵ
                sameCountKeys.putIfAbsent(count, new TreeSet<>());
                sameCountKeys.get(count).add(num);
            }
        }

        // �����������֮��û����ͬ���֣������"NULL"
        if (noSameNum) {
            System.out.println("NULL");
            return;
        }

        // ���򣬰�����ͬ���ֵĳ��ִ�����������ӡ��Ӧ�����µ���ͬ�����б�ע����ͬ�����б�������TreeSet�������Ȼ����
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