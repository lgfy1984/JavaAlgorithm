package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/132194543
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ������ߵĵ��� {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("[\n,]");

        // ��������
        int n = sc.nextInt();
        // ��������
        int m = sc.nextInt();

        // shop_costs��һ���ֵ䣬keyΪ����id��valueΪ�õ������µ�����
        HashMap<Integer, ArrayList<Integer>> shop_costs = new HashMap<>();

        // ��¼�������������ĸ�ѡ���ѣ����ں��湹��Ȩֵ�߶���
        int max_cost = 0;
        // ��¼�Լ�����ʤѡ����Ҫ�Ļ���
        long ans = 0;

        // ��ȡn������
        for (int i = 0; i < n; i++) {
            // ����id, ��ѡ�Լ����̵Ļ���
            int shop = sc.nextInt();
            int cost = sc.nextInt();

            // �����ѡ����Ϊ0����˵��ѡ�ľ����Լ�����
            if (cost == 0 || shop == 1) continue;

            // ��¼��ѡ�Լ����̵����������ĸ�ѡ����
            max_cost = Math.max(max_cost, cost);
            // ans��¼�����Լ�����ʤѡ����Ҫ�Ļ��ѣ���ʼʱ�����Ǽ��������в�ѡ�Լ����̵��˶���ѡ�Լ����̣���ô�Լ����̾ͻ������ѡƱ����ʱ�϶�ʤѡ
            ans += cost;

            // shop_costs��һ���ֵ䣬keyΪ����id��valueΪ�õ������µ�����
            shop_costs.putIfAbsent(shop, new ArrayList<>());
            shop_costs.get(shop).add(cost);
        }

        // scan��ɨ���߾���
        ArrayList<ArrayList<Integer>> scan = new ArrayList<>();
        for (ArrayList<Integer> costs : shop_costs.values()) {
            costs.sort((a, b) -> b - a);

            for (int i = 0; i < costs.size(); i++) {
                if (scan.size() <= i) scan.add(new ArrayList<>());
                scan.get(i).add(costs.get(i));
            }
        }

        // �����е�Ʊ����ʼʱ������������������Ʊ
        int my_ticket_count = n;
        // �������Ʊ�Ļ���
        long my_ticket_cost = ans;

        // ����Ȩֵ�߶���
        WSegmentTree tree = new WSegmentTree(max_cost);

        // ��ʼɨ��
        for (int i = 0; i < scan.size(); i++) {
            //  ��ɨ����ɨ���Ķ���ÿ���������¸�ѡ�����������񣬶����ⲿ���������Ƿ���
            ArrayList<Integer> line = scan.get(i);

            for (Integer cost : line) {
                // ��������������ĸ�ѡ���ü���Ȩֵ�߶�������������Լ�����Ʊ������������Ҫ����һЩ����
                tree.add(1, 1, max_cost, cost);
                // ���ڷ������ⲿ��������˿���ȥ�����ǵĸ�ѡ����
                my_ticket_cost -= cost;
                // ���ڷ������ⲿ�����������ӦƱ��ҲҪȥ��
                my_ticket_count -= 1;
            }

            int extra_ticket_cost = 0;
            // ��ʱ�������̵�ѡƱ��Ϊi+1�����������ǵ�ѡƱ��my_ticket_count <= i+1�����޷�ȡʤ
            if (my_ticket_count <= i + 1) {
                // ����������Ʊ��ֻ�дﵽ i+2 �ţ�����սʤ�������̣�����ע�� i+2 ���ܳ�����Ʊ��n,
                // ������ǻ���Ҫ��ȷ����� min(i+2, n) - my_ticket_count��Ʊ
                int extra_ticket_count = Math.min(i + 2, n) - my_ticket_count;
                // ��Ч�����һ�����е�ǰxС��֮�ͣ����Ի���Ȩֵ�߶�����⣬�൱�����ǰextra_ticket_countС֮��
                extra_ticket_cost = tree.query(1, 1, max_cost, extra_ticket_count);
            }

            // ÿ��ɨ����ɨ��󣬼����仨�ѣ�������С������Ϊ���
            ans = Math.min(ans, my_ticket_cost + extra_ticket_cost);
        }

        System.out.println(ans);
    }
}

// Ȩֵ�߶���
class WSegmentTree {
    int[] count; // Ȩֵ��
    int[] sum; // ����

    public WSegmentTree(int n) {
        // �߶�����Ҫ��4n�Ŀռ�
        this.count = new int[n << 2];
        this.sum = new int[n << 2];
    }

    /**
     * ��Ȩֵ�߶����м���һ����
     *
     * @param k ��ǰ���ڵ��߶����ڵ�����
     * @param l ��ǰ���ڵ��߶����ڵ� ��Ӧ�����䷶Χ����߽�
     * @param r ��ǰ���ڵ��߶����ڵ� ��Ӧ�����䷶Χ���ұ߽�
     * @param val Ҫ����Ȩֵ�߶�������
     */
    public void add(int k, int l, int r, int val) {
        // ����Ҷ�ӽڵ�
        if (l == r) {
            this.count[k] += 1;
            this.sum[k] += val;
            return;
        }

        // �ڵ�k�����ӽڵ����
        int lson = k << 1; // �൱�� 2 * k
        // �ڵ�k�����ӽڵ����
        int rson = k << 1 | 1; // �൱�� 2 * k + 1

        // ���ӽڵ��Ӧ���䷶Χ[l, mid]
        // ���ӽڵ��Ӧ���䷶Χ[mid+1, r]
        int mid = (l + r) >> 1;

        if (val <= mid) {
            // Ҫ�������val�������ӽڵ����䷶Χ��
            this.add(lson, l, mid, val);
        } else {
            // Ҫ�������val�������ӽڵ����䷶Χ��
            this.add(rson, mid + 1, r, val);
        }

        // ����ͳ��
        this.count[k] = this.count[lson] + this.count[rson];
        this.sum[k] = this.sum[lson] + this.sum[rson];
    }

    /**
     * ���ǰrankС��֮��
     *
     * @param k ��ǰ���ڵ��߶����ڵ�����
     * @param l ��ǰ���ڵ��߶����ڵ� ��Ӧ�����䷶Χ����߽�
     * @param r ��ǰ���ڵ��߶����ڵ� ��Ӧ�����䷶Χ���ұ߽�
     * @param rank ���ǰrankС��֮�͵�rankֵ
     * @return ǰrankС��֮��
     */
    public int query(int k, int l, int r, int rank) {
        // ����Ҷ�ӽڵ�
        if (l == r) {
            return rank * l;
        }

        // �ڵ�k�����ӽڵ����
        int lson = k << 1;
        // �ڵ�k�����ӽڵ����
        int rson = k << 1 | 1;

        // ���ӽڵ��Ӧ���䷶Χ[l, mid]
        // ���ӽڵ��Ӧ���䷶Χ[mid+1, r]
        int mid = (l + r) >> 1;

        // this.countͳ�Ƶ���ĳ���䷶Χ��Ԫ�ص���������Щ�����ۼ��������Ƕ�ӦԪ�ص�����
        if (this.count[lson] < rank) {
            // ������ӽڵ��Ԫ������ < rank����ô˵��ǰrank���������в��������ӽڵ���
            return this.sum[lson] + this.query(rson, mid + 1, r, rank - this.count[lson]);
        } else if (this.count[lson] > rank) {
            // ������ӽڵ��Ԫ������ > rank����ô˵��ǰrank�����������ӽڵ��У���Ҫ�����ֽ�
            return this.query(lson, l, mid, rank);
        } else {
            // ������ӽڵ�Ԫ������ == rank����ô˵��ǰrank�����������ӽڵ��ڵ�Ԫ�أ���ʱҪ��ǰrankС��֮�ͣ���ʵ����this.sum[lson]
            return this.sum[lson];
        }
    }
}