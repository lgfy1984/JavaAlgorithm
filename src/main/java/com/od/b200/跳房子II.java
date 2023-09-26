package com.od.b200;

/**
 * https://fcqian.blog.csdn.net/article/details/130771324
 *
 * @author l84309057
 * @since 2023/9/19
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class ������II {
    static class Step {
        int val;
        int idx;

        public Step(int val, int idx) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String tmp = sc.nextLine();
        int[] steps =
                Arrays.stream(tmp.substring(1, tmp.length() - 1).split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        int count = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(steps, count));
    }

    public static String getResult(int[] steps, int count) {
        int n = steps.length;

        Step[] newSteps = new Step[n];
        for (int i = 0; i < n; i++) {
            newSteps[i] = new Step(steps[i], i);
        }

        Arrays.sort(newSteps, (a, b) -> a.val != b.val ? a.val - b.val : a.idx - b.idx);

        int minStepIdxSum = Integer.MAX_VALUE;
        String ans = "";

        for (int i = 0; i < n; i++) {
            // ��֦�Ż�
            if (newSteps[i].val > count && newSteps[i].val > 0 && count > 0) break;

            // ��֦�Ż�
            if (i > 0 && newSteps[i].val == newSteps[i - 1].val) continue;

            int l = i + 1;
            int r = n - 1;

            while (l < r) {
                // ��֦�Ż���L,Rָ��ָ��ֵ��Ŀ���Ϊcount - iָ��ָ���ֵ����Lָ��ָ���ֵ ��ȻС�ڵ��� Rָ��ָ���ֵ��
                // ���Lָ��ָ���ֵ��Ȼ <= Ŀ���/2����Rָ��ָ���ֵ��Ȼ >= Ŀ���/2
                int threshold = (count - newSteps[i].val) / 2;
                if (newSteps[l].val > threshold || newSteps[r].val < threshold) break;

                int stepValSum = newSteps[i].val + newSteps[l].val + newSteps[r].val;

                if (stepValSum < count) {
                    l++;
                } else if (stepValSum > count) {
                    r--;
                } else {
                    // ��֦�Ż�
                    while (l < r - 1 && newSteps[r].val == newSteps[r - 1].val) {
                        r--;
                    }

                    int stepIdxSum = newSteps[i].idx + newSteps[l].idx + newSteps[r].idx;
                    if (stepIdxSum < minStepIdxSum) {
                        minStepIdxSum = stepIdxSum;

                        Step[] arr = {newSteps[i], newSteps[l], newSteps[r]};
                        Arrays.sort(arr, (a, b) -> a.idx - b.idx);

                        StringJoiner sj = new StringJoiner(",", "[", "]");
                        for (Step step : arr) sj.add(step.val + "");

                        ans = sj.toString();
                    }

                    // ��֦�Ż�
                    while (l + 1 < r && newSteps[l].val == newSteps[l + 1].val) {
                        l++;
                    }

                    l++;
                    r--;
                }
            }
        }
        return ans;
    }
}