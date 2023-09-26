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
public class �ַ�������Ϊ�� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    public static String getResult(String s) {
        // �����ַ�����
        StringBuilder sb = new StringBuilder();
        // ��Ч�ַ������ļ���
        LinkedList<TreeSet<Character>> eqs = new LinkedList<>();

        //  isOpen��־����ʾ��û������ '(' �ַ�
        boolean isOpen = false;

        // �����߼����ڴ������ַ����н����������ַ����͵�Ч�ַ�
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                isOpen = true;
                eqs.add(new TreeSet<>());
            } else if (c == ')') {
                isOpen = false;
                if (eqs.getLast().size() == 0) eqs.removeLast(); // �����Ч�ַ�����Ϊ�գ���ɾ��
            } else {
                if (!isOpen) sb.append(c);
                else eqs.getLast().add(c);
            }
        }

        // �����ĶԵ�Ч�ַ��������кϲ�
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

        // �滻�����ַ������е��ַ�
        for (TreeSet<Character> eq : eqs) {
            Character t = eq.first();
            for (int i = 0; i < cArr.length; i++) {
                if (eq.contains(cArr[i])) cArr[i] = t;
            }
        }

        String ans = new String(cArr);

        // ����򻯺���ַ���Ϊ�գ������Ϊ"0"��
        return ans.length() == 0 ? "0" : ans;
    }

    public static boolean canCombine(TreeSet<Character> set1, TreeSet<Character> set2) {
        // c ��Сд�ַ�
        for (char c = 'a'; c <= 'z'; c++) {
            char uc = (char) (c - 32); // uc�Ǵ�д�ַ�
            if ((set1.contains(c) || set1.contains(uc)) && (set2.contains(c) || set2.contains(uc))) {
                return true;
            }
        }
        return false;
    }
}