package com.od.b200;

/**
 * https://blog.csdn.net/qfc_128220/article/details/131476575
 *
 * @author l84309057
 * @since 2023/9/12
 */
import java.util.Arrays;
import java.util.Scanner;

public class MELON������ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(n, nums));
    }

    public static int getResult(int n, int[] nums) {
        // �����껨ʯ����֮��
        int sum = Arrays.stream(nums).sum();

        // �������֮�Ͳ�������2�����Ȼ�޷�ƽ��
        if (sum % 2 != 0) return -1;

        // ��������
        int bag = sum / 2;

        // ��ά����
        int[][] dp = new int[n + 1][bag + 1];

        // ��ʼ����һ�У�n��һ�������ܵ�װ����������Ʒ����
        for (int i = 0; i <= bag; i++) {
            dp[0][i] = n;
        }

        // 2023.07.16 �޸ĳ�ʼ������
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 1; j <= bag; j++) {
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - num] + 1);
                }
            }
        }

        // ���װ��������������Ʒ��Ϊn, ��˵��û��ƽ�ַ�������Ϊn���껨ʯ������֮��ΪsumV���������ĳ�����bag = sumV // 2
        if (dp[n][bag] == n) {
            return -1;
        } else {
            return dp[n][bag];
        }
    }
}