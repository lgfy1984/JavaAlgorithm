package com.od.b100;

/**
 * https://fcqian.blog.csdn.net/article/details/130766482
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ����Ͱ��һƽ���3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] boxes = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int len = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(boxes, len));
    }

    public static int getResult(int[] boxes, int len) {
        // ͳ�Ƹ�������һ�����ӵı��
        HashMap<Integer, Integer> lastIdx = new HashMap<>();
        // ��Ӧ���ֵ������Ѿ��ҵ��ˣ���������Ҫ������Ӷ�
        HashSet<Integer> find = new HashSet<>();

        int ans = -1;

        for (int i = 0; i < boxes.length; i++) {
            // ��������������
            int num = boxes[i];

            // �������Ƿ��Ѿ��ҵ���������Ҫ������Ӷԣ�����ҵ��ˣ�����Ҫ�ٿ�����ģ�ֻ�ҵ�һ�Լ���
            if (find.contains(num)) continue;

            // ������Ӷ��Ƿ��������Ҫ��
            if (lastIdx.containsKey(num) && i - lastIdx.get(num) <= len) {
                find.add(num);
                ans = ans == -1 ? lastIdx.get(num) : Math.min(ans, lastIdx.get(num));
            } else {
                lastIdx.put(num, i);
            }
        }

        return ans;
    }
}