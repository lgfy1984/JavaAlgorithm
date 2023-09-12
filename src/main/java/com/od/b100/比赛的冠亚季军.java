package com.od.b100;

/**
 * https://blog.csdn.net/qfc_128220/article/details/130769633
 *
 * @author l84309057
 * @since 2023/9/12
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class 比赛的冠亚季军 {
    // 运动员类
    static class Sport {
        int id; // 运动员的id
        long strength; // 运动员的实力

        public Sport(int id, long strength) {
            this.id = id;
            this.strength = strength;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long[] strengths = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(getResult(strengths));
    }

    public static String getResult(long[] strength) {
        // ans只记录三个组，冠军组，亚军组，季军组
        LinkedList<ArrayList<Sport>> ans = new LinkedList<>();

        // 将输入的实力值，转化为运动员集合
        ArrayList<Sport> sports = new ArrayList<>();
        for (int i = 0; i < strength.length; i++) sports.add(new Sport(i, strength[i]));

        // 晋级赛
        promote(sports, ans);

        // 冠军组如果不是一个人，那么还需要取出冠军组继续进行晋级赛
        while (ans.getFirst().size() > 1) {
            promote(ans.removeFirst(), ans);
        }

        // 冠军
        int first = ans.get(0).get(0).id;

        // 亚军
        int second = ans.get(1).get(0).id;

        // 季军
        ans.get(2)
                .sort(
                        (a, b) ->
                                a.strength != b.strength ? b.strength - a.strength > 0 ? 1 : -1 : a.id - b.id);
        int third = ans.get(2).get(0).id;

        return first + " " + second + " " + third;
    }

    public static void promote(ArrayList<Sport> sports, LinkedList<ArrayList<Sport>> ans) {
        // 记录获胜组
        ArrayList<Sport> win = new ArrayList<>();
        // 记录失败组
        ArrayList<Sport> fail = new ArrayList<>();

        for (int i = 1; i < sports.size(); i += 2) {
            // 序号大的运动员
            Sport major = sports.get(i);
            // 序号小的运动员
            Sport minor = sports.get(i - 1);

            if (major.strength > minor.strength) {
                win.add(major);
                fail.add(minor);
            } else {
                // 如果序号大的运动员的实力 <= 序号小的运动员，则序号小的运动员获胜
                win.add(minor);
                fail.add(major);
            }
        }

        // 如果晋级赛中运动员个数是奇数个，那么最后一个运动员直接晋级
        if (sports.size() % 2 != 0) {
            win.add(sports.get(sports.size() - 1));
        }

        // 依次头部压入失败组，获胜组，保证头部是获胜组
        ans.addFirst(fail);
        ans.addFirst(win);

        // 如果保留组个数超过3个，那么需要将超过部分的组去掉，因为这部分人已经无缘季军
        while (ans.size() > 3) ans.removeLast();
    }
}