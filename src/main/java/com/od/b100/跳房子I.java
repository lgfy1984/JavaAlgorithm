package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130766192
 *
 * @author l84309057
 * @since 2023/9/12
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class Ìø·¿×ÓI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String tmp = sc.nextLine();
        int[] steps =
                Arrays.stream(tmp.substring(1, tmp.length() - 1).split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        int count = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(steps, count));
    }

    public static String getResult(int[] steps, int count) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int minIdxSum = Integer.MAX_VALUE;
        String ans = "";

        for (int idx1 = 0; idx1 < steps.length; idx1++) {
            int step1 = steps[idx1];
            int step2 = count - step1;

            if (map.containsKey(step2)) {
                int idx2 = map.get(step2);
                int idxSum = idx1 + idx2;
                if (idxSum < minIdxSum) {
                    minIdxSum = idxSum;
                    ans = idx1 < idx2 ? "[" + step1 + ", " + step2 + "]" : "[" + step2 + ", " + step1 + "]";
                }

            } else {
                map.putIfAbsent(step1, idx1);
            }
        }

        return ans;
    }
}