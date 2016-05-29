package com.blackoon.lr.jdk.collection;

/**
 * @author zhangy
 * @E-mail:blackoon88@gmail.com
 * @qq:846579287
 * @version 创建时间：2016年4月27日 下午5:56:34 一个动态添加对象的容器，底层使用链表模拟。
 */
public class LinkedList {
	Node head = null; // 链表头
	Node tail = null; // 链表尾
	int size = 0; // 链表中元素个数

	/**
	 * 链表中添加元素
	 * 
	 * @param o
	 *            要添加的元素
	 */
	public void add(Object o) {
		Node n = new Node(o, null); // 构造要添加的节点
		// 若链表为空，则将链表头和尾都指向新节点
		if (head == null) {
			head = n;
			tail = n;
		}
		tail.setNext(n); // 原链表尾指向新节点
		tail = n; // 链表尾重定向到新节点
		size++; // 链表中元素数量累加
	}

	/**
	 * 链表中元素的个数
	 * 
	 * @return 返回链表中元素的个数
	 */
	public int size() {
		return size;
	}
}

class Node {
	public Node(Object o, Node next) {
		super();
		this.data = o;
		this.next = next;
	}

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	private Node next;
}