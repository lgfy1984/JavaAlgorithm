package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130771502
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

public class ∞¢¿Ô∞Õ∞Õ’“ª∆Ω±¶œ‰4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(arr));
    }

    public static String getResult(int[] arr) {
        LinkedList<int[]> stack = new LinkedList<>();

        int[] res = new int[arr.length];
        Arrays.fill(res, -1);

        findNextBig(arr, stack, res);

        if (stack.size() != 1) findNextBig(arr, stack, res);

        StringJoiner sj = new StringJoiner(",");
        for (int v : res) {
            sj.add(v + "");
        }
        return sj.toString();
    }

    public static void findNextBig(int[] arr, LinkedList<int[]> stack, int[] res) {
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            while (true) {
                if (stack.size() == 0) {
                    stack.add(new int[] {ele, i});
                    break;
                } else {
                    int[] peek = stack.get(stack.size() - 1);
                    int peekEle = peek[0];
                    int peekIdx = peek[1];

                    if (ele > peekEle) {
                        res[peekIdx] = ele;
                        stack.removeLast();
                    } else {
                        stack.add(new int[] {ele, i});
                        break;
                    }
                }
            }
        }
    }
}