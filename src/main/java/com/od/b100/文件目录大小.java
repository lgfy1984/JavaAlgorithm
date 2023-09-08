package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130765017
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.*;
import java.util.stream.Collectors;

public class 文件目录大小 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        HashMap<Integer, ArrayList<Integer>> children = new HashMap<>();
        HashMap<Integer, Integer> cap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int fa_id = sc.nextInt();
            int fa_cap = sc.nextInt();

            children.putIfAbsent(fa_id, new ArrayList<>());
            cap.putIfAbsent(fa_id, fa_cap);

            String ch_str = sc.next();
            if (ch_str.length() > 2) {
                children
                        .get(fa_id)
                        .addAll(
                                Arrays.stream(ch_str.substring(1, ch_str.length() - 1).split(","))
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList()));
            }
        }

        System.out.println(getResult(children, cap, n));
    }

    public static int getResult(
            HashMap<Integer, ArrayList<Integer>> children, HashMap<Integer, Integer> cap, int target) {
        int ans = 0;

        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(target);

        while (stack.size() > 0) {
            Integer id = stack.pop();
            if (!cap.containsKey(id)) continue;
            ans += cap.get(id);
            stack.addAll(children.get(id));
        }

        return ans;
    }

}
