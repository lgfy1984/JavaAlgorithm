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

public class 人气最高的店铺 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("[\n,]");

        // 市民数量
        int n = sc.nextInt();
        // 店铺数量
        int m = sc.nextInt();

        // shop_costs是一个字典，key为店铺id，value为该店铺名下的市民
        HashMap<Integer, ArrayList<Integer>> shop_costs = new HashMap<>();

        // 记录所有市民中最大的改选花费，用于后面构造权值线段树
        int max_cost = 0;
        // 记录自己店铺胜选所需要的花费
        long ans = 0;

        // 获取n行输入
        for (int i = 0; i < n; i++) {
            // 店铺id, 改选自己店铺的花费
            int shop = sc.nextInt();
            int cost = sc.nextInt();

            // 如果改选花费为0，则说明选的就是自己店铺
            if (cost == 0 || shop == 1) continue;

            // 记录不选自己店铺的市民中最大的改选花费
            max_cost = Math.max(max_cost, cost);
            // ans记录的是自己店铺胜选所需要的花费，初始时，我们假设让所有不选自己店铺的人都改选自己店铺，那么自己店铺就获得所有选票，此时肯定胜选
            ans += cost;

            // shop_costs是一个字典，key为店铺id，value为该店铺名下的市民
            shop_costs.putIfAbsent(shop, new ArrayList<>());
            shop_costs.get(shop).add(cost);
        }

        // scan是扫描线矩阵
        ArrayList<ArrayList<Integer>> scan = new ArrayList<>();
        for (ArrayList<Integer> costs : shop_costs.values()) {
            costs.sort((a, b) -> b - a);

            for (int i = 0; i < costs.size(); i++) {
                if (scan.size() <= i) scan.add(new ArrayList<>());
                scan.get(i).add(costs.get(i));
            }
        }

        // 我手中的票，初始时，假设我们想获得所有票
        int my_ticket_count = n;
        // 获得所有票的花费
        long my_ticket_cost = ans;

        // 构建权值线段树
        WSegmentTree tree = new WSegmentTree(max_cost);

        // 开始扫描
        for (int i = 0; i < scan.size(); i++) {
            //  被扫描线扫到的都是每个店铺名下改选花费最大的市民，对于这部分市民，我们放弃
            ArrayList<Integer> line = scan.get(i);

            for (Integer cost : line) {
                // 将被放弃的市民的改选费用加入权值线段树，后面如果自己店铺票数不够，还需要复活一些市民
                tree.add(1, 1, max_cost, cost);
                // 由于放弃了这部分市民，因此可以去除他们的改选花费
                my_ticket_cost -= cost;
                // 由于放弃了这部分市民，因此相应票数也要去除
                my_ticket_count -= 1;
            }

            int extra_ticket_cost = 0;
            // 此时其他店铺的选票数为i+1，因此如果我们的选票数my_ticket_count <= i+1，则无法取胜
            if (my_ticket_count <= i + 1) {
                // 我们手中总票数只有达到 i+2 张，才能战胜其他店铺，但是注意 i+2 不能超过总票数n,
                // 因此我们还需要正确额外的 min(i+2, n) - my_ticket_count张票
                int extra_ticket_count = Math.min(i + 2, n) - my_ticket_count;
                // 高效的求解一组数中的前x小数之和，可以基于权值线段树求解，相当于求解前extra_ticket_count小之和
                extra_ticket_cost = tree.query(1, 1, max_cost, extra_ticket_count);
            }

            // 每轮扫描线扫描后，计算其花费，保留最小花费作为题解
            ans = Math.min(ans, my_ticket_cost + extra_ticket_cost);
        }

        System.out.println(ans);
    }
}

// 权值线段树
class WSegmentTree {
    int[] count; // 权值树
    int[] sum; // 和树

    public WSegmentTree(int n) {
        // 线段树都要开4n的空间
        this.count = new int[n << 2];
        this.sum = new int[n << 2];
    }

    /**
     * 向权值线段树中加入一个数
     *
     * @param k 当前所在的线段树节点的序号
     * @param l 当前所在的线段树节点 对应的区间范围的左边界
     * @param r 当前所在的线段树节点 对应的区间范围的右边界
     * @param val 要加入权值线段数的数
     */
    public void add(int k, int l, int r, int val) {
        // 到达叶子节点
        if (l == r) {
            this.count[k] += 1;
            this.sum[k] += val;
            return;
        }

        // 节点k的左子节点序号
        int lson = k << 1; // 相当于 2 * k
        // 节点k的右子节点序号
        int rson = k << 1 | 1; // 相当于 2 * k + 1

        // 左子节点对应区间范围[l, mid]
        // 右子节点对应区间范围[mid+1, r]
        int mid = (l + r) >> 1;

        if (val <= mid) {
            // 要加入的数val，在左子节点区间范围内
            this.add(lson, l, mid, val);
        } else {
            // 要加入的数val，在右子节点区间范围内
            this.add(rson, mid + 1, r, val);
        }

        // 回溯统计
        this.count[k] = this.count[lson] + this.count[rson];
        this.sum[k] = this.sum[lson] + this.sum[rson];
    }

    /**
     * 求解前rank小数之和
     *
     * @param k 当前所在的线段树节点的序号
     * @param l 当前所在的线段树节点 对应的区间范围的左边界
     * @param r 当前所在的线段树节点 对应的区间范围的右边界
     * @param rank 求解前rank小数之和的rank值
     * @return 前rank小数之和
     */
    public int query(int k, int l, int r, int rank) {
        // 到达叶子节点
        if (l == r) {
            return rank * l;
        }

        // 节点k的左子节点序号
        int lson = k << 1;
        // 节点k的右子节点序号
        int rson = k << 1 | 1;

        // 左子节点对应区间范围[l, mid]
        // 右子节点对应区间范围[mid+1, r]
        int mid = (l + r) >> 1;

        // this.count统计的是某区间范围内元素的数量，这些数量累加起来就是对应元素的排名
        if (this.count[lson] < rank) {
            // 如果左子节点的元素数量 < rank，那么说明前rank个数，还有部分在右子节点中
            return this.sum[lson] + this.query(rson, mid + 1, r, rank - this.count[lson]);
        } else if (this.count[lson] > rank) {
            // 如果左子节点的元素数量 > rank，那么说明前rank个数都在左子节点中，需要继续分解
            return this.query(lson, l, mid, rank);
        } else {
            // 如果左子节点元素数量 == rank，那么说明前rank个数就是左子节点内的元素，此时要求前rank小数之和，其实就是this.sum[lson]
            return this.sum[lson];
        }
    }
}