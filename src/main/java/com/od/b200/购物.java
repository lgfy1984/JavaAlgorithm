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

public class 购物 {
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
        int curSum; // 当前组合之和
        int nextIdx; // 将要被加入当前组合的新元素索引位置

        public CombineModel(int curSum, int nextIdx) {
            this.curSum = curSum;
            this.nextIdx = nextIdx;
        }
    }

    public static void getResult(int n, int m, int[] nums) {
        Arrays.sort(nums);

        // 对于一个组合模型，其”将要产生的新组合“之和越小，则优先级越高
        // curSum + nums[nextIdx] 为 ”将要产生的新组合“之和
        PriorityQueue<CombineModel> pq =
                new PriorityQueue<>((a, b) -> a.curSum + nums[a.nextIdx] - (b.curSum + nums[b.nextIdx]));

        // 空组合的和为0, 将要加入的新元素是nums[0], 即索引0的元素，其将要产生的新组合之和为 0 + nums[0]
        CombineModel c = new CombineModel(0, 0);

        for (int i = 1; i <= m; i++) {
            // 打印第 i 小组合
            System.out.println(c.curSum + nums[c.nextIdx]);

            // c是当前最小组合模型，最小的组合模型指的是将要产生的新组合之和在对应轮次中最小
            // 如果当前组合模型c还有可合入的下一个元素，即c.nextIdx + 1 < n, 则说明可以基于当前组合模型产生一个新组合
            if (c.nextIdx + 1 < n) {
                // 基于当前组合模型产生的新组合，也是本轮最小的组合，即第 i 小组合
                pq.offer(new CombineModel(c.curSum + nums[c.nextIdx], c.nextIdx + 1));

                // 当前组合需要更新nextIdx后，重新加入优先队列
                c.nextIdx += 1;
                pq.offer(c);
            }

            // 取出优先队列中最小组合（注意这里的最小，指的是基于当前组合，将要产生的新组合之和最小）
            c = pq.poll();
        }
    }
}