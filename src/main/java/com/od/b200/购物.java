package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/131605179
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ���� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] nums = new int[n];
        for (int j = 0; j < n; j++) {
            nums[j] = sc.nextInt();
        }

        getResult(n, k, nums);
    }

    static class CombineModel {
        int curSum; // ��ǰ���֮��
        int nextIdx; // ��Ҫ�����뵱ǰ��ϵ���Ԫ������λ��

        public CombineModel(int curSum, int nextIdx) {
            this.curSum = curSum;
            this.nextIdx = nextIdx;
        }
    }

    public static void getResult(int n, int m, int[] nums) {
        Arrays.sort(nums);

        // ����һ�����ģ�ͣ��䡱��Ҫ����������ϡ�֮��ԽС�������ȼ�Խ��
        // curSum + nums[nextIdx] Ϊ ����Ҫ����������ϡ�֮��
        PriorityQueue<CombineModel> pq =
                new PriorityQueue<>((a, b) -> a.curSum + nums[a.nextIdx] - (b.curSum + nums[b.nextIdx]));

        // ����ϵĺ�Ϊ0, ��Ҫ�������Ԫ����nums[0], ������0��Ԫ�أ��佫Ҫ�����������֮��Ϊ 0 + nums[0]
        CombineModel c = new CombineModel(0, 0);

        for (int i = 1; i <= m; i++) {
            // ��ӡ�� i С���
            System.out.println(c.curSum + nums[c.nextIdx]);

            // c�ǵ�ǰ��С���ģ�ͣ���С�����ģ��ָ���ǽ�Ҫ�����������֮���ڶ�Ӧ�ִ�����С
            // �����ǰ���ģ��c���пɺ������һ��Ԫ�أ���c.nextIdx + 1 < n, ��˵�����Ի��ڵ�ǰ���ģ�Ͳ���һ�������
            if (c.nextIdx + 1 < n) {
                // ���ڵ�ǰ���ģ�Ͳ���������ϣ�Ҳ�Ǳ�����С����ϣ����� i С���
                pq.offer(new CombineModel(c.curSum + nums[c.nextIdx], c.nextIdx + 1));

                // ��ǰ�����Ҫ����nextIdx�����¼������ȶ���
                c.nextIdx += 1;
                pq.offer(c);
            }

            // ȡ�����ȶ�������С��ϣ�ע���������С��ָ���ǻ��ڵ�ǰ��ϣ���Ҫ�����������֮����С��
            c = pq.poll();
        }
    }
}