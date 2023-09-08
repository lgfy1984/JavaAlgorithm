package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130764579
 *
 * @author l84309057
 * @since 2023/9/7
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class ģ����Ϣ���� {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] pubArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] subArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        getResult(pubArr, subArr);
    }

    public static void getResult(int[] pubArr, int[] subArr) {
        int n = pubArr.length;
        int m = subArr.length;

        // ����һ����������һ�飬�ռ�����������������
        int[][] publisher = new int[n / 2][];
        for (int i = 0, k = 0; i < n; i += 2) {
            publisher[k++] = new int[]{pubArr[i], pubArr[i + 1]}; // [����ʱ�̣� ��������]
        }

        // ���ڶ�����������һ�飬�ռ�����������������
        int[][] subscriber = new int[m / 2][];
        for (int j = 0, k = 0; j < m; j += 2) {
            subscriber[k++] = new int[]{subArr[j], subArr[j + 1]}; // [����ʱ�̣� ȡ������ʱ��]
        }

        // ���շ���ʱ�����򣺷���������
        Arrays.sort(publisher, (a, b) -> a[0] - b[0]);

        // Ϊÿһ�������߹���һ���Ķ������ݼ���
        ArrayList<ArrayList<Integer>> subContent = new ArrayList<>();
        for (int j = 0; j < subscriber.length; j++) subContent.add(new ArrayList<>());

        // ����������
        for (int[] pub : publisher) {
            int pubTime = pub[0]; // ����ʱ��
            int pubContent = pub[1]; // ��������

            // ������������ߣ���Ϊ����Ķ��������ȼ����ߣ���˵������ʵ�ָ����ȼ��Ķ�������ƥ�䵽������
            for (int j = subscriber.length - 1; j >= 0; j--) {
                int subTime = subscriber[j][0]; // ����ʱ��
                int unSubTime = subscriber[j][1]; // ȡ������ʱ��

                // ��� ����ʱ�� <= ����ʱ�� < ȡ������ʱ��
                if (pubTime >= subTime && pubTime < unSubTime) {
                    // ��ô�ö����߾Ϳ����յ�����������
                    subContent.get(j).add(pubContent);
                    // ��Ŀ˵����������ֻ�ᱻ������ȼ��Ķ������յ�
                    break;
                }
            }
        }

        // ��ӡ
        for (ArrayList<Integer> contents : subContent) {
            if (contents.size() == 0) {
                System.out.println("-1");
            } else {
                StringJoiner sj = new StringJoiner(" ");
                for (Integer content : contents) sj.add(content + "");
                System.out.println(sj.toString());
            }
        }
    }

}
