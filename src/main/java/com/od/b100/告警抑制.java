package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130768150
 *
 * @author l84309057
 * @since 2023/9/6
 */

import java.util.*;

public class 告警抑制 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        HashMap<String, HashSet<String>> fa = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tmp = sc.nextLine().split(" ");
            // id1抑制id2
            String id1 = tmp[0], id2 = tmp[1];
            fa.putIfAbsent(id2, new HashSet<>());
            // fa用于记录抑制id2的所有id1的集合
            fa.get(id2).add(id1);
        }

        String[] alertList = sc.nextLine().split(" ");

        System.out.println(getResult(fa, alertList));
    }

    public static String getResult(HashMap<String, HashSet<String>> fa, String[] alertList) {
        HashSet<String> alertSet = new HashSet<>(Arrays.asList(alertList));

        StringJoiner sj = new StringJoiner(" ");

        for (String id2 : alertList) {
            // 如果没有抑制id2的更高级的告警，或者有抑制id2的更高级的告警，但是此高级告警没有出现在alertList列表中
            if (!fa.containsKey(id2) || Collections.disjoint(fa.get(id2), alertSet)) {
                // 此时id2就可以正常告警
                sj.add(id2);
            }
        }

        return sj.toString();
    }

}
