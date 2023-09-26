package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/131185797
 *
 * @author l84309057
 * @since 2023/9/13
 */
import java.util.Arrays;
import java.util.Scanner;
public class 数字序列比大小 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(n, a, b));
    }

    public static int getResult(int n, int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int la = 0; // 指向田忌最慢的马
        int ra = n - 1; // 指向田忌最快的马

        int lb = 0; // 指向齐王最慢的马
        int rb = n - 1; // 指向齐王最快的马

        int ans = 0; // 记录田忌获得银币数

        while (la <= ra) {
            if (a[ra] > b[rb]) {
                // 田忌最快的马 比 齐王最快的马要快, 则直接比
                ans += 1;
                ra--;
                rb--;
            } else if (a[ra] < b[rb]) {
                // 田忌最快的马 比 齐王最快的马要慢, 则结果肯定输, 为了保留田忌最快的马, 我们应该用田忌最慢的马去消耗掉齐王最快的马
                ans -= 1;
                la++;
                rb--;
            } else {
                // 田忌最快的马 和 齐王最快的 速度相同, 此时如果平局的话，则会让田忌损失最快的马，因此我们应该找到田忌最慢的马, 即田忌必输的马来消耗掉齐王最快的马
                if (a[la] > b[lb]) {
                    // 如果田忌最慢的马 比 齐王最慢的马 快, 则此时田忌最慢的马不是必输的马
                    ans += 1;
                    la++;
                    lb++;
                } else {
                    // 如果田忌最慢的马速度 <= 齐王最慢的马速度, 此时应该让田忌最慢的马 去消耗  齐王最快的马

                    // 如果齐王最快的马速度 > 田忌最慢的马速度，则田忌失去银币
                    // 如果齐王最快的马速度 == 田忌最慢的马速度，则田忌不失去银币
                    if (b[rb] > a[la]) ans -= 1;
                    la++;
                    rb--;
                }
            }
        }

        return ans;
    }
}