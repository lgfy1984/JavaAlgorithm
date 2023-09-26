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

public class 评论转换输出 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String[] comments = sc.nextLine().split(",");
            getResult(comments);
        }

        public static void getResult(String[] comments) {
            // 树结构
            ArrayList<ArrayList<String>> tree = new ArrayList<>();

            // 将输入的评论信息，转化为队列结构
            LinkedList<String> queue = new LinkedList<>(Arrays.asList(comments));

            // 根评论层级为1
            int level = 1;

            // 该循环用于取出根评论
            while (queue.size() > 0) {
                // 根评论
                String comment = queue.removeFirst();

                // 如果树还没有对应层级，则初始化对应层级
                if (tree.size() < level) {
                    tree.add(new ArrayList<>());
                }

                // 将根评论加入树结构的第一层
                tree.get(0).add(comment);

                // 该根评论有几个直接子评论
                int childCount = Integer.parseInt(queue.removeFirst());
                // 按上面逻辑，递归处理子评论，子评论所处级别为level+1
                recursive(queue, level + 1, childCount, tree);
            }

            // 树结构的高度，就是评论嵌套的最大深度
            System.out.println(tree.size());
            // 树结构的每一层，记录对应嵌套级别的评论
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
