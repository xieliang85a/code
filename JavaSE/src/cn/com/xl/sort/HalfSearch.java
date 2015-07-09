package cn.com.xl.sort;

public class HalfSearch {

	/**
	 * 折半查找
	 * @param args
	 */
	public static void main(String[] args) {
		int [] arr = {12, 23, 56, 66};
		int index = halfSearch_2(arr, 13);
		System.out.println(index);

	}
	
	/**
	 * 在一个有序数组中插入一个数，获取该插入数所在组中的位置
	 * @param arr
	 * @param key
	 * @return
	 */
	public static int halfSearch_2(int [] arr,int key){
		int min = 0;
		int max = arr.length - 1;
		int mid = 0;
		while(min <= max){
			mid = (max + min) >> 1;
			if(arr[mid] > key){
				max = mid - 1;
			}else if(arr[mid] < key){
				min = mid + 1;
			}else{
				return mid;
			}
		}
		return min;
		
	}
	
	/**
	 * 查找数组中对应key的下位置
	 * @param arr
	 * @param key
	 * @return
	 */
	public static int halfSearch(int [] arr,int key){
		int min = 0;
		int max = arr.length - 1;
		int mid = 0;
		while(min <= max){
			mid = (max + min) >> 1;
			if(arr[mid] > key){
				max = mid - 1;
			}else if(arr[mid] < key){
				min = mid + 1;
			}else{
				return mid;
			}
		}
		return -1;
		
	}
}
