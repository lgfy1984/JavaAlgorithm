package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130770084
 *
 * @author l84309057
 * @since 2023/9/6
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class AI面板识别 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Light[] lights = new Light[n];
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            lights[i] = new Light(id, (x1 + x2) / 2, (y1 + y2) / 2, (x2 - x1) / 2);
        }

        System.out.println(getResult(lights));
    }

    public static String getResult(Light[] lights) {
        // 按照圆心y坐标升序
        Arrays.sort(lights, (a, b) -> a.y - b.y);

        // ans记录题解
        StringJoiner ans = new StringJoiner(" ");

        // sameRowLights记录同一行的灯
        ArrayList<Light> sameRowLights = new ArrayList<>();
        Light base = lights[0];
        sameRowLights.add(base);

        for (int i = 1; i < lights.length; i++) {
            Light light = lights[i];

            // 如果lights[i]的纵坐标和base的纵坐标相差不超过半径，则视为同一行
            if (light.y - base.y <= base.r) {
                sameRowLights.add(light);
            } else {
                // 否则，不是同一行
                // 针对同一行的灯，再按照横坐标升序
                sameRowLights.sort((a, b) -> a.x - b.x);
                sameRowLights.forEach(a -> ans.add(a.id + ""));
                sameRowLights.clear();

                // 开始新的一行记录
                base = light;
                sameRowLights.add(base);
            }
        }

        // 注意不要漏了最后一行
        if (sameRowLights.size() > 0) {
            sameRowLights.sort((a, b) -> a.x - b.x);
            sameRowLights.forEach(a -> ans.add(a.id + ""));
        }

        return ans.toString();
    }
}

class Light {
    int id; // 编号
    int x; // 圆心横坐标
    int y; // 圆心纵坐标
    int r; // 圆半径

    public Light(int id, int x, int y, int r) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
    }

}
