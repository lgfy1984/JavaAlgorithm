package com.usual.ch14Tree;
/*
 * 平衡二叉树节点
 */
public class Node {
	// 数据项（键值）
	public long data;
	// 数据项（字符串值）
	public String sData;
	// 左子节点
	public Node leftChild;
	// 右子节点
	public Node rightChild;
	// 节点高度（用于平衡因子计算）
	public int height;

	/**
	 * 构造方法
	 * @param data
	 * @param sData
	 */
	public Node(long data, String sData) {
		this.data = data;
		this.sData = sData;
		this.height = 1;
	}
}