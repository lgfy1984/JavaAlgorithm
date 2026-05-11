package com.usual.ch21DynamicProgramming;

/*
 * 动态规划算法 —— 包含多个经典问题
 */
public class DynamicProgramming {

	// ==================== 1. 斐波那契数列 ====================

	/**
	 * 斐波那契数列 —— 自顶向下（记忆化搜索）
	 * 时间复杂度 O(n)，空间复杂度 O(n)
	 */
	public static long fibMemo(int n) {
		if (n <= 0) return 0;
		long[] memo = new long[n + 1];
		return fibMemoHelper(n, memo);
	}

	private static long fibMemoHelper(int n, long[] memo) {
		if (n <= 2) return n - 1;
		if (memo[n] != 0) return memo[n];
		memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
		return memo[n];
	}

	/**
	 * 斐波那契数列 —— 自底向上（递推 / 滚动数组）
	 * 时间复杂度 O(n)，空间复杂度 O(1)
	 */
	public static long fibTab(int n) {
		if (n <= 1) return 0;
		if (n == 2) return 1;
		long prev2 = 0; // F(1)
		long prev1 = 1; // F(2)
		long current = 0;
		for (int i = 3; i <= n; i++) {
			current = prev1 + prev2;
			prev2 = prev1;
			prev1 = current;
		}
		return current;
	}

	// ==================== 2. 0-1 背包问题 ====================

	/**
	 * 0-1 背包问题 —— 二维 DP
	 * 给定 n 件物品，重量 w[i]，价值 v[i]，背包容量 capacity，
	 * 求能装入的最大总价值（每件物品只能选一次）。
	 * 时间复杂度 O(n*capacity)，空间复杂度 O(n*capacity)
	 */
	public static int knapsack(int[] w, int[] v, int capacity) {
		int n = w.length;
		// dp[i][j] = 前 i 件物品，容量 j 时的最大价值
		int[][] dp = new int[n + 1][capacity + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= capacity; j++) {
				if (j >= w[i - 1]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][capacity];
	}

	/**
	 * 0-1 背包问题 —— 一维滚动数组优化
	 * 时间复杂度 O(n*capacity)，空间复杂度 O(capacity)
	 */
	public static int knapsackOptimized(int[] w, int[] v, int capacity) {
		int n = w.length;
		int[] dp = new int[capacity + 1];

		for (int i = 0; i < n; i++) {
			// 必须从大到小遍历，保证每件物品只选一次
			for (int j = capacity; j >= w[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
			}
		}
		return dp[capacity];
	}

	// ==================== 3. 最长公共子序列（LCS） ====================

	/**
	 * 最长公共子序列 —— 求长度
	 * 给定两个字符串 s1, s2，求最长公共子序列的长度。
	 * 时间复杂度 O(m*n)，空间复杂度 O(m*n)
	 */
	public static int lcsLength(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		// dp[i][j] = s1[0..i-1] 与 s2[0..j-1] 的 LCS 长度
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}

	/**
	 * 最长公共子序列 —— 回溯输出子序列
	 */
	public static String lcsString(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// 回溯构造 LCS
		StringBuilder sb = new StringBuilder();
		int i = m, j = n;
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				sb.append(s1.charAt(i - 1));
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		return sb.reverse().toString();
	}

	// ==================== 4. 零钱兑换（Coin Change） ====================

	/**
	 * 零钱兑换 —— 最少硬币数
	 * 给定面值数组 coins 和目标金额 amount，求凑成该金额的最少硬币数。
	 * 如果无法凑出，返回 -1。
	 * 时间复杂度 O(n*amount)，空间复杂度 O(amount)
	 */
	public static int coinChange(int[] coins, int amount) {
		// dp[i] = 凑成金额 i 所需的最少硬币数
		int[] dp = new int[amount + 1];
		// 初始化为一个不可能的大数
		int max = amount + 1;
		for (int i = 1; i <= amount; i++) {
			dp[i] = max;
		}
		dp[0] = 0;

		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (i >= coin) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	/**
	 * 零钱兑换 —— 组合方案数
	 * 求凑成目标金额的所有组合数（不考虑顺序）。
	 * 时间复杂度 O(n*amount)，空间复杂度 O(amount)
	 */
	public static int coinChangeWays(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;

		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}

	// ==================== 5. 最长递增子序列（LIS） ====================

	/**
	 * 最长递增子序列 —— O(n²) DP
	 * dp[i] = 以 nums[i] 结尾的最长递增子序列长度
	 */
	public static int lisLength(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;

		int[] dp = new int[n];
		int maxLen = 1;
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}
		return maxLen;
	}

	// ==================== 6. 编辑距离（Edit Distance） ====================

	/**
	 * 编辑距离 —— 最少操作次数
	 * 将 word1 转换为 word2 所需的最少操作数（插入/删除/替换）。
	 * dp[i][j] = word1[0..i-1] 转换为 word2[0..j-1] 的最小操作数
	 * 时间复杂度 O(m*n)，空间复杂度 O(m*n)
	 */
	public static int editDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m + 1][n + 1];

		// 边界：将空串转为另一字符串全是插入
		for (int i = 0; i <= m; i++) dp[i][0] = i;
		for (int j = 0; j <= n; j++) dp[0][j] = j;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(
							dp[i - 1][j],     // 删除
							dp[i][j - 1]),    // 插入
							dp[i - 1][j - 1]  // 替换
					) + 1;
				}
			}
		}
		return dp[m][n];
	}

	// ==================== 7. 三角形最小路径和 ====================

	/**
	 * 三角形最小路径和
	 * 给定三角形二维数组 triangle[n][n]，从顶部到底部的最小路径和，
	 * 每一步只能移动到下一行的相邻位置。
	 * 自底向上 DP，空间复杂度 O(n)
	 */
	public static int triangleMinPath(int[][] triangle) {
		int n = triangle.length;
		// dp[j] = 走到当前行第 j 列的最小路径和
		int[] dp = new int[n];

		// 初始化最后一行
		for (int j = 0; j < n; j++) {
			dp[j] = triangle[n - 1][j];
		}

		// 自底向上递推
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j];
			}
		}
		return dp[0];
	}
}