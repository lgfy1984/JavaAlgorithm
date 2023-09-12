package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130768798
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Scanner;

public class ¾­µäÆÁ±£ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getResult(sc.nextInt(), sc.nextInt(), sc.nextInt()));
    }

    public static String getResult(int x, int y, int t) {
        x += t;
        y += t;

        while (y + 25 > 600 || y < 0 || x + 50 > 800 || x < 0) {
            if (y + 25 > 600) {
                //        y = 600 - (y + 25 - 600) - 25;
                y = 1150 - y;
            }

            if (x + 50 > 800) {
                //        x = 800 - (x + 50 - 800) - 50;
                x = 1500 - x;
            }

            if (y < 0) {
                y = -y;
            }

            if (x < 0) {
                x = -x;
            }
        }

        return x + " " + y;
    }
}