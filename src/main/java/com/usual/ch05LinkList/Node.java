package com.usual.ch05LinkList;
/*
 * ����㣬�൱���ǳ���
 */
public class Node {
	//������
	public long data;
	//ָ����
	public Node next;
	public Node previous;
	
	public Node(long value) {
		this.data = value;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display() {
		System.out.print(data + " ");
	}
}