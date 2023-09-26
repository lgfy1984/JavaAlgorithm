package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130775122
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Scanner;

public class ²é×Öµä {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String prefix = sc.next();

        boolean find = false;

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String word = sc.next();

            if (word.startsWith(prefix)) {
                find = true;
                System.out.println(word);
            }
        }

        if (!find) System.out.println(-1);
    }
}