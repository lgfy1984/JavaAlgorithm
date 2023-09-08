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

public class AI���ʶ�� {

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
        // ����Բ��y��������
        Arrays.sort(lights, (a, b) -> a.y - b.y);

        // ans��¼���
        StringJoiner ans = new StringJoiner(" ");

        // sameRowLights��¼ͬһ�еĵ�
        ArrayList<Light> sameRowLights = new ArrayList<>();
        Light base = lights[0];
        sameRowLights.add(base);

        for (int i = 1; i < lights.length; i++) {
            Light light = lights[i];

            // ���lights[i]���������base���������������뾶������Ϊͬһ��
            if (light.y - base.y <= base.r) {
                sameRowLights.add(light);
            } else {
                // ���򣬲���ͬһ��
                // ���ͬһ�еĵƣ��ٰ��պ���������
                sameRowLights.sort((a, b) -> a.x - b.x);
                sameRowLights.forEach(a -> ans.add(a.id + ""));
                sameRowLights.clear();

                // ��ʼ�µ�һ�м�¼
                base = light;
                sameRowLights.add(base);
            }
        }

        // ע�ⲻҪ©�����һ��
        if (sameRowLights.size() > 0) {
            sameRowLights.sort((a, b) -> a.x - b.x);
            sameRowLights.forEach(a -> ans.add(a.id + ""));
        }

        return ans.toString();
    }
}

class Light {
    int id; // ���
    int x; // Բ�ĺ�����
    int y; // Բ��������
    int r; // Բ�뾶

    public Light(int id, int x, int y, int r) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
    }

}
