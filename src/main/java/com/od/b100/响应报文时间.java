package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130769162
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Scanner;

public class 响应报文时间 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();

        int[][] messages = new int[c][2];
        for (int i = 0; i < c; i++) {
            messages[i][0] = sc.nextInt();
            messages[i][1] = sc.nextInt();
        }

        System.out.println(getResult(messages));
    }

    public static int getResult(int[][] messages) {
        int ans = Integer.MAX_VALUE;

        for (int[] message : messages) {
            int respTime = message[0] + getMaxResponseTime(message[1]);
            ans = Math.min(ans, respTime);
        }

        return ans;
    }

    public static int getMaxResponseTime(int m) {
        if (m >= 128) {
            StringBuilder bStr = new StringBuilder(Integer.toBinaryString(m));

            while (bStr.length() < 8) {
                bStr.insert(0, '0');
            }

            int exp = Integer.parseInt(bStr.substring(1, 4), 2);
            int mant = Integer.parseInt(bStr.substring(4), 2);

            return (mant | 16) << (exp + 3);
        } else {
            return m;
        }
    }
}