package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130774170
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ����ת����� {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String[] comments = sc.nextLine().split(",");
            getResult(comments);
        }

        public static void getResult(String[] comments) {
            // ���ṹ
            ArrayList<ArrayList<String>> tree = new ArrayList<>();

            // �������������Ϣ��ת��Ϊ���нṹ
            LinkedList<String> queue = new LinkedList<>(Arrays.asList(comments));

            // �����۲㼶Ϊ1
            int level = 1;

            // ��ѭ������ȡ��������
            while (queue.size() > 0) {
                // ������
                String comment = queue.removeFirst();

                // �������û�ж�Ӧ�㼶�����ʼ����Ӧ�㼶
                if (tree.size() < level) {
                    tree.add(new ArrayList<>());
                }

                // �������ۼ������ṹ�ĵ�һ��
                tree.get(0).add(comment);

                // �ø������м���ֱ��������
                int childCount = Integer.parseInt(queue.removeFirst());
                // �������߼����ݹ鴦�������ۣ���������������Ϊlevel+1
                recursive(queue, level + 1, childCount, tree);
            }

            // ���ṹ�ĸ߶ȣ���������Ƕ�׵�������
            System.out.println(tree.size());
            // ���ṹ��ÿһ�㣬��¼��ӦǶ�׼��������
            for (ArrayList<String> levelNodes : tree) {
                System.out.println(String.join(" ", levelNodes));
            }
        }

        public static void recursive(
                LinkedList<String> queue, int level, int childCount, ArrayList<ArrayList<String>> tree) {
            for (int i = 0; i < childCount; i++) {
                String comment = queue.removeFirst();

                if (tree.size() < level) {
                    tree.add(new ArrayList<>());
                }

                tree.get(level - 1).add(comment);

                int count = Integer.parseInt(queue.removeFirst());
                if (count > 0) {
                    recursive(queue, level + 1, count, tree);
                }
            }
        }
    }
