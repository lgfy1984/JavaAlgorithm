package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130773708
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class 不开心的小朋友 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] kids = sc.nextLine().split(" ");

        System.out.println(getResult(n, kids));
    }

    public static int getResult(int n, String[] kids) {
        // 不开心的小朋友数量
        int unHappy = 0;

        // 已在摇摇车上的小朋友编号
        HashSet<String> playing = new HashSet<>();
        // 正在排队的小朋友编号
        LinkedList<String> waiting = new LinkedList<>();

        for (String kid : kids) {
            if (playing.contains(kid)) {
                // 如果kid是摇摇车上的小朋友编号, 则代表kid玩好了要离开
                playing.remove(kid);

                // 如果kid离开后，摇摇车有空位了，如果此时有人排队，则让排队的人上车玩
                if (waiting.size() > 0) {
                    playing.add(waiting.removeFirst());
                }

                continue;
            }

            // 如果kid不是摇摇车上的小朋友，则检查kid是不是排队的小朋友
            int index = waiting.indexOf(kid);
            if (index != -1) {
                // 如果是排队的小朋友，则说明kid没有玩到摇摇车，因此会不开心的离开
                unHappy++;
                waiting.remove(index);
                continue;
            }

            // 如果kid既不是摇摇车上的小朋友，也不是排队的小朋友，则是新来的小朋友
            if (playing.size() < n) {
                // 如果摇摇车还有空位，则直接玩
                playing.add(kid);
            } else {
                // 如果摇摇车没有空位了，则需要排队
                waiting.add(kid);
            }
        }

        return unHappy;
    }
}