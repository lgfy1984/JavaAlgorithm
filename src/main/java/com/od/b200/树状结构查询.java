package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130773940
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.*;

public class Ê÷×´½á¹¹²éÑ¯ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        HashMap<String, HashSet<String>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String ch = sc.next();
            String fa = sc.next();

            tree.putIfAbsent(fa, new HashSet<>());
            tree.get(fa).add(ch);
        }

        String target = sc.next();

        getResult(tree, target);
    }

    public static void getResult(HashMap<String, HashSet<String>> tree, String target) {
        if (!tree.containsKey(target)) {
            System.out.println("");
            return;
        }

        LinkedList<String> queue = new LinkedList<>(tree.get(target));

        ArrayList<String> ans = new ArrayList<>();

        while (queue.size() > 0) {
            String node = queue.removeFirst();
            ans.add(node);

            if (tree.containsKey(node)) queue.addAll(tree.get(node));
        }

        ans.sort(String::compareTo);

        ans.forEach(System.out::println);
    }
}