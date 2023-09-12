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

public class �����Ĺ��Ǽ��� {
    // �˶�Ա��
    static class Sport {
        int id; // �˶�Ա��id
        long strength; // �˶�Ա��ʵ��

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
        // ansֻ��¼�����飬�ھ��飬�Ǿ��飬������
        LinkedList<ArrayList<Sport>> ans = new LinkedList<>();

        // �������ʵ��ֵ��ת��Ϊ�˶�Ա����
        ArrayList<Sport> sports = new ArrayList<>();
        for (int i = 0; i < strength.length; i++) sports.add(new Sport(i, strength[i]));

        // ������
        promote(sports, ans);

        // �ھ����������һ���ˣ���ô����Ҫȡ���ھ���������н�����
        while (ans.getFirst().size() > 1) {
            promote(ans.removeFirst(), ans);
        }

        // �ھ�
        int first = ans.get(0).get(0).id;

        // �Ǿ�
        int second = ans.get(1).get(0).id;

        // ����
        ans.get(2)
                .sort(
                        (a, b) ->
                                a.strength != b.strength ? b.strength - a.strength > 0 ? 1 : -1 : a.id - b.id);
        int third = ans.get(2).get(0).id;

        return first + " " + second + " " + third;
    }

    public static void promote(ArrayList<Sport> sports, LinkedList<ArrayList<Sport>> ans) {
        // ��¼��ʤ��
        ArrayList<Sport> win = new ArrayList<>();
        // ��¼ʧ����
        ArrayList<Sport> fail = new ArrayList<>();

        for (int i = 1; i < sports.size(); i += 2) {
            // ��Ŵ���˶�Ա
            Sport major = sports.get(i);
            // ���С���˶�Ա
            Sport minor = sports.get(i - 1);

            if (major.strength > minor.strength) {
                win.add(major);
                fail.add(minor);
            } else {
                // �����Ŵ���˶�Ա��ʵ�� <= ���С���˶�Ա�������С���˶�Ա��ʤ
                win.add(minor);
                fail.add(major);
            }
        }

        // ������������˶�Ա����������������ô���һ���˶�Աֱ�ӽ���
        if (sports.size() % 2 != 0) {
            win.add(sports.get(sports.size() - 1));
        }

        // ����ͷ��ѹ��ʧ���飬��ʤ�飬��֤ͷ���ǻ�ʤ��
        ans.addFirst(fail);
        ans.addFirst(win);

        // ����������������3������ô��Ҫ���������ֵ���ȥ������Ϊ�ⲿ�����Ѿ���Ե����
        while (ans.size() > 3) ans.removeLast();
    }
}