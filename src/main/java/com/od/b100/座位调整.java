package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130764903
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.Arrays;
import java.util.Scanner;

public class ×ùÎ»µ÷Õû {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] desk = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(desk));
    }

    public static int getResult(int[] desk) {
        int ans = 0;

        for (int i = 0; i < desk.length; i++) {
            if (desk[i] == 0) {
                boolean isLeftEmpty = i == 0 || desk[i - 1] == 0;
                boolean isRightEmpty = i == desk.length - 1 || desk[i + 1] == 0;
                if (isLeftEmpty && isRightEmpty) {
                    ans++;
                    desk[i] = 1;
                    i++;
                }
            }
        }

        return ans;
    }

}
