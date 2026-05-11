package com.usual.ch14Tree;
/*
 * 平衡二叉树（AVL树）
 */
public class AVLTree {
	// 根节点
	public Node root;

	/**
	 * 获取节点高度
	 */
	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	/**
	 * 更新节点高度
	 */
	private void updateHeight(Node node) {
		node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
	}

	/**
	 * 计算平衡因子（左子树高度 - 右子树高度）
	 */
	private int balanceFactor(Node node) {
		if (node == null) {
			return 0;
		}
		return height(node.leftChild) - height(node.rightChild);
	}

	/**
	 * 右旋（LL旋转）
	 *         y               x
	 *        / \             / \
	 *       x  T3   -->    T1  y
	 *      / \                 / \
	 *     T1 T2              T2 T3
	 */
	private Node rightRotate(Node y) {
		Node x = y.leftChild;
		Node T2 = x.rightChild;

		x.rightChild = y;
		y.leftChild = T2;

		updateHeight(y);
		updateHeight(x);

		return x;
	}

	/**
	 * 左旋（RR旋转）
	 *       x                 y
	 *      / \               / \
	 *     T1  y     -->     x  T3
	 *        / \           / \
	 *       T2 T3         T1 T2
	 */
	private Node leftRotate(Node x) {
		Node y = x.rightChild;
		Node T2 = y.leftChild;

		y.leftChild = x;
		x.rightChild = T2;

		updateHeight(x);
		updateHeight(y);

		return y;
	}

	/**
	 * 平衡节点（检查并执行对应的旋转操作）
	 */
	private Node balance(Node node) {
		updateHeight(node);
		int bf = balanceFactor(node);

		// LL情况：左子树高，且新节点插入在左子树的左侧
		if (bf > 1 && balanceFactor(node.leftChild) >= 0) {
			return rightRotate(node);
		}

		// RR情况：右子树高，且新节点插入在右子树的右侧
		if (bf < -1 && balanceFactor(node.rightChild) <= 0) {
			return leftRotate(node);
		}

		// LR情况：左子树高，但新节点插入在左子树的右侧
		if (bf > 1 && balanceFactor(node.leftChild) < 0) {
			node.leftChild = leftRotate(node.leftChild);
			return rightRotate(node);
		}

		// RL情况：右子树高，但新节点插入在右子树的左侧
		if (bf < -1 && balanceFactor(node.rightChild) > 0) {
			node.rightChild = rightRotate(node.rightChild);
			return leftRotate(node);
		}

		return node;
	}

	/**
	 * 插入节点
	 */
	public void insert(long value, String sValue) {
		root = insertNode(root, value, sValue);
	}

	private Node insertNode(Node node, long value, String sValue) {
		if (node == null) {
			return new Node(value, sValue);
		}

		if (value < node.data) {
			node.leftChild = insertNode(node.leftChild, value, sValue);
		} else if (value > node.data) {
			node.rightChild = insertNode(node.rightChild, value, sValue);
		} else {
			// 相等值，更新字符串数据
			node.sData = sValue;
			return node;
		}

		return balance(node);
	}

	/**
	 * 查找节点
	 */
	public Node find(long value) {
		Node current = root;
		while (current != null && current.data != value) {
			if (current.data > value) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
		}
		return current;
	}

	/**
	 * 删除节点
	 */
	public boolean delete(long value) {
		if (find(value) == null) {
			return false;
		}
		root = deleteNode(root, value);
		return true;
	}

	private Node deleteNode(Node node, long value) {
		if (node == null) {
			return null;
		}

		if (value < node.data) {
			node.leftChild = deleteNode(node.leftChild, value);
		} else if (value > node.data) {
			node.rightChild = deleteNode(node.rightChild, value);
		} else {
			// 找到要删除的节点
			// 情况1：叶子节点，或只有左子节点/右子节点
			if (node.leftChild == null) {
				return node.rightChild;
			} else if (node.rightChild == null) {
				return node.leftChild;
			}

			// 情况2：有两个子节点，找后继节点（右子树的最小节点）
			Node successor = findMin(node.rightChild);
			node.data = successor.data;
			node.sData = successor.sData;
			node.rightChild = deleteNode(node.rightChild, successor.data);
		}

		return balance(node);
	}

	/**
	 * 找右子树中的最小节点（后继节点）
	 */
	private Node findMin(Node node) {
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return node;
	}

	/**
	 * 前序遍历（根 -> 左 -> 右）
	 */
	public void frontOrder(Node localNode) {
		if (localNode != null) {
			System.out.println(localNode.data + ", " + localNode.sData);
			frontOrder(localNode.leftChild);
			frontOrder(localNode.rightChild);
		}
	}

	/**
	 * 中序遍历（左 -> 根 -> 右）
	 */
	public void inOrder(Node localNode) {
		if (localNode != null) {
			inOrder(localNode.leftChild);
			System.out.println(localNode.data + ", " + localNode.sData);
			inOrder(localNode.rightChild);
		}
	}

	/**
	 * 后序遍历（左 -> 右 -> 根）
	 */
	public void afterOrder(Node localNode) {
		if (localNode != null) {
			afterOrder(localNode.leftChild);
			afterOrder(localNode.rightChild);
			System.out.println(localNode.data + ", " + localNode.sData);
		}
	}
}