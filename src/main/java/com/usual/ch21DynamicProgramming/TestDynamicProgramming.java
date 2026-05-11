package com.usual.ch21DynamicProgramming;

public class TestDynamicProgramming {
	public static void main(String[] args) {

		// ===== 1. 斐波那契数列 =====
		System.out.println("===== 斐波那契数列 =====");
		System.out.println("fibMemo(10) = " + DynamicProgramming.fibMemo(10)
				+ " (预期: 34)");
		System.out.println("fibTab(10)  = " + DynamicProgramming.fibTab(10)
				+ " (预期: 34)");
		System.out.println("fibMemo(20) = " + DynamicProgramming.fibMemo(20)
				+ " (预期: 4181)");

		// ===== 2. 0-1 背包问题 =====
		System.out.println("\n===== 0-1 背包问题 =====");
		int[] w = {2, 3, 4, 5, 9};
		int[] v = {3, 4, 5, 8, 10};
		int capacity = 20;
		System.out.println("最大价值(二维dp): " + DynamicProgramming.knapsack(w, v, capacity)
				+ " (预期: 30)");
		System.out.println("最大价值(一维dp): " + DynamicProgramming.knapsackOptimized(w, v, capacity)
				+ " (预期: 30)");

		capacity = 10;
		System.out.println("\n容量为10时:");
		System.out.println("最大价值: " + DynamicProgramming.knapsackOptimized(w, v, capacity)
				+ " (预期: 20)");

		// ===== 3. 最长公共子序列 =====
		System.out.println("\n===== 最长公共子序列 =====");
		String s1 = "ABCBDAB";
		String s2 = "BDCABA";
		System.out.println("LCS长度: " + DynamicProgramming.lcsLength(s1, s2)
				+ " (预期: 4)");
		System.out.println("LCS内容: " + DynamicProgramming.lcsString(s1, s2)
				+ " (预期: BCBA 或 BDAB)");

		// ===== 4. 零钱兑换 =====
		System.out.println("\n===== 零钱兑换 =====");
		int[] coins = {1, 2, 5, 11};
		int amount = 11;
		System.out.println("凑成 " + amount + " 的最少硬币数: "
				+ DynamicProgramming.coinChange(coins, amount) + " (预期: 1)");

		amount = 20;
		System.out.println("凑成 " + amount + " 的最少硬币数: "
				+ DynamicProgramming.coinChange(coins, amount) + " (预期: 3)");

		System.out.println("凑成 " + amount + " 的组合数: "
				+ DynamicProgramming.coinChangeWays(coins, amount) + " (预期: 42)");

		// ===== 5. 最长递增子序列 =====
		System.out.println("\n===== 最长递增子序列 =====");
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println("LIS长度: " + DynamicProgramming.lisLength(nums)
				+ " (预期: 4，如 [2,5,7,101] 或 [2,3,7,18])");

		nums = new int[]{7, 7, 7, 7};
		System.out.println("全相等数组 LIS长度: "
				+ DynamicProgramming.lisLength(nums) + " (预期: 1)");

		// ===== 6. 编辑距离 =====
		System.out.println("\n===== 编辑距离 =====");
		System.out.println("\"horse\" -> \"ros\": "
				+ DynamicProgramming.editDistance("horse", "ros") + " (预期: 3)");
		System.out.println("\"intention\" -> \"execution\": "
				+ DynamicProgramming.editDistance("intention", "execution") + " (预期: 5)");

		// ===== 7. 三角形最小路径和 =====
		System.out.println("\n===== 三角形最小路径和 =====");
		int[][] triangle = {
				{2, 0, 0, 0},
				{3, 4, 0, 0},
				{6, 5, 7, 0},
				{4, 1, 8, 3}
		};
		// 路径: 2 -> 3 -> 5 -> 1 = 11
		System.out.println("最小路径和: " + DynamicProgramming.triangleMinPath(triangle)
				+ " (预期: 11)");
	}
}