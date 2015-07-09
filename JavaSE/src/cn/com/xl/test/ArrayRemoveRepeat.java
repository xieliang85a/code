package cn.com.xl.test;
/**
 * 找出数组中唯一重复的元素
 * @author xieliang
 *
 */
public class ArrayRemoveRepeat {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 4 };
		int repeatNum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			repeatNum ^= i;
			repeatNum ^= arr[i];
		}
		System.out.println("重复数为："+repeatNum);
		
		/*int sum = 0;
		for(int i = 0; i< arr.length;i++){
			sum +=arr[i];
		}
		System.out.println(sum);
		int sum2 = 0;
		for(int i = 1; i< arr.length;i++){
			sum2 +=i;
		}
		System.out.println(sum2);*/
		
		int sum1 = arr[0];
		int sum2 = 0;
		for(int i = 1; i< arr.length;i++){
			sum1+=arr[i];
			sum2+=i;
		}
		System.out.println("重复数为:"+(sum1-sum2));
	}
}
