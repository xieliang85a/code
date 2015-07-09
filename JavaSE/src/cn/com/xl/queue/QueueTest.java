package cn.com.xl.queue;

import java.util.Random;
import java.util.UUID;

public class QueueTest {
	private static Object[] objs;
	private static int front;// 队头
	private static int rear = -1;// 队尾
	private static int items;// 队列中的实际数据长度

	public static void main(String[] args) {
		objs = new Object[5];
		int i = 1;
		while (!isFull()) {
			insert("第" + i++ + "<<<" + UUID.randomUUID());
		}
		while (!isEmpty()) {
			romve();
		}

	}

	// 进队列
	public static void insert(Object obj) {
		int temp = rear;
		if (++temp >= objs.length) {
			rear = -1;
		}
		objs[++rear] = obj;
		items++;
	}

	// 出队列
	public static void romve() {
		Object obj = objs[front++];
		if (front == objs.length) {
			front = 0;
		}
		System.out.println(obj);
		items--;
	}

	public static boolean isEmpty() {
		return items == 0;
	}

	public static boolean isFull() {
		return items == objs.length;
	}
}
