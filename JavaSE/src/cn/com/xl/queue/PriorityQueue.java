package cn.com.xl.queue;

import java.util.LinkedList;
import java.util.Random;

public class PriorityQueue {

	/**
	 * 优先队列
	 * @param args
	 */
	public static void main(String[] args) {
		is = new Integer[5];
		while(!isFull()){
			insert(new Random().nextInt(100));
		}
		while(!isEmpty()){
			System.out.println("__"+remove());
		}
		

	}

	private static Integer [] is;
	private static int nItems;
	
	public static void insert(Integer i){
		int j;
		if(nItems==0){
			is[nItems++] = i;
		}else{
			for (j = nItems-1; j >=0; j--) {
				if(i > is[j]){
					is[j+1] = is[j];
					
				}else break;
			}
			is[j+1] = i;
			nItems++;
		}
	}
	public static Integer remove(){
		return is[--nItems];
	}
	public static boolean isEmpty(){
		return nItems==0;
	}
	public static boolean isFull(){
		return nItems==is.length;
	}
}
