package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130765683
 *
 * @author l84309057
 * @since 2023/9/19
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;
public class 字符串化繁为简 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    public static String getResult(String s) {
        // 主体字符容器
        StringBuilder sb = new StringBuilder();
        // 等效字符容器的集合
        LinkedList<TreeSet<Character>> eqs = new LinkedList<>();

        //  isOpen标志，表示有没有遇到 '(' 字符
        boolean isOpen = false;

        // 下面逻辑用于从输入字符串中解析处主体字符，和等效字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                isOpen = true;
                eqs.add(new TreeSet<>());
            } else if (c == ')') {
                isOpen = false;
                if (eqs.getLast().size() == 0) eqs.removeLast(); // 如果等效字符容器为空，则删除
            } else {
                if (!isOpen) sb.append(c);
                else eqs.getLast().add(c);
            }
        }

        // 暴力的对等效字符容器进行合并
        outer:
        while (true) {
            for (int i = 0; i < eqs.size(); i++) {
                for (int j = i + 1; j < eqs.size(); j++) {
                    if (canCombine(eqs.get(i), eqs.get(j))) {
                        eqs.get(i).addAll(eqs.get(j));
                        eqs.remove(j);
                        continue outer;
                    }
                }
            }
            break;
        }

        char[] cArr = sb.toString().toCharArray();

        // 替换主体字符容器中的字符
        for (TreeSet<Character> eq : eqs) {
            Character t = eq.first();
            for (int i = 0; i < cArr.length; i++) {
                if (eq.contains(cArr[i])) cArr[i] = t;
            }
        }

        String ans = new String(cArr);

        // 如果简化后的字符串为空，请输出为"0"。
        return ans.length() == 0 ? "0" : ans;
    }

    public static boolean canCombine(TreeSet<Character> set1, TreeSet<Character> set2) {
        // c 是小写字符
        for (char c = 'a'; c <= 'z'; c++) {
            char uc = (char) (c - 32); // uc是大写字符
            if ((set1.contains(c) || set1.contains(uc)) && (set2.contains(c) || set2.contains(uc))) {
                return true;
            }
        }
        return false;
    }
}