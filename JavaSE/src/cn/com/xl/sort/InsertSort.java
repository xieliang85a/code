package cn.com.xl.sort;

import java.util.Random;
/**
 * 插入排序
 * @author admin
 *
 */
public class InsertSort {
	public static void insertSort(Integer[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i;
			while (j > 0 && arr[j - 1] >= temp) {
				arr[j] = arr[j - 1];
				--j;
			}
			arr[j] = temp;
		}

	}

	public static void main(String[] args) {
		Integer arr[] = new Integer[3];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Random().nextInt(50);
		}
		sop(arr);
		System.out.println("\n-----------------");
		insertSort(arr);
		sop(arr);
	}
	

	public static void sop(Object...objects){
		for (int i = 0; i < objects.length; i++) {
			System.out.print(objects[i] + ",");
		}
		
	}
}
