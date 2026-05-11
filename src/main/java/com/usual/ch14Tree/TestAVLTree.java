package com.usual.ch14Tree;

public class TestAVLTree {
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();

		// 测试插入（包含触发各种旋转的场景）
		System.out.println("===== 插入节点 =====");
		// LL旋转：依次插入3,2,1
		tree.insert(3, "three");
		tree.insert(2, "two");
		tree.insert(1, "one");
		System.out.println("插入3,2,1后（触发LL旋转），根节点应为2: " + tree.root.data);

		// RR旋转：依次插入1,2,3
		AVLTree tree2 = new AVLTree();
		tree2.insert(1, "one");
		tree2.insert(2, "two");
		tree2.insert(3, "three");
		System.out.println("插入1,2,3后（触发RR旋转），根节点应为2: " + tree2.root.data);

		// LR旋转：插入5,3,4
		AVLTree tree3 = new AVLTree();
		tree3.insert(5, "five");
		tree3.insert(3, "three");
		tree3.insert(4, "four");
		System.out.println("插入5,3,4后（触发LR旋转），根节点应为4: " + tree3.root.data);

		// RL旋转：插入3,5,4
		AVLTree tree4 = new AVLTree();
		tree4.insert(3, "three");
		tree4.insert(5, "five");
		tree4.insert(4, "four");
		System.out.println("插入3,5,4后（触发RL旋转），根节点应为4: " + tree4.root.data);

		// 构建一棵完整的AVL树进行后续测试
		AVLTree avl = new AVLTree();
		avl.insert(50, "fifty");
		avl.insert(30, "thirty");
		avl.insert(70, "seventy");
		avl.insert(20, "twenty");
		avl.insert(40, "forty");
		avl.insert(60, "sixty");
		avl.insert(80, "eighty");
		avl.insert(10, "ten");
		avl.insert(25, "twenty-five");

		System.out.println("\n===== 中序遍历（有序输出） =====");
		avl.inOrder(avl.root);

		System.out.println("\n===== 查找测试 =====");
		Node found = avl.find(40);
		System.out.println("查找40: " + (found != null ? "找到 " + found.data + ", " + found.sData : "未找到"));
		Node notFound = avl.find(100);
		System.out.println("查找100: " + (notFound != null ? "找到" : "未找到"));

		System.out.println("\n===== 删除测试 =====");
		System.out.println("删除叶子节点10...");
		avl.delete(10);
		System.out.println("中序遍历结果:");
		avl.inOrder(avl.root);

		System.out.println("\n删除有一个子节点的节点20...");
		avl.delete(20);
		System.out.println("中序遍历结果:");
		avl.inOrder(avl.root);

		System.out.println("\n删除有两个子节点的节点50（根节点）...");
		avl.delete(50);
		System.out.println("中序遍历结果:");
		avl.inOrder(avl.root);
		System.out.println("删除后的根节点: " + avl.root.data);

		System.out.println("\n===== 前序遍历 =====");
		avl.frontOrder(avl.root);

		System.out.println("\n===== 后序遍历 =====");
		avl.afterOrder(avl.root);
	}
}