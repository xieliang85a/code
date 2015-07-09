package cn.com.xl.soul;

public class PrintMDemo {

	/**
	 * 将传递进来的数,格成M型
	 * 效果如下:
	 *  3   7
	 * 2 4 6 8
	 *1   5   9
	 * @param args
	 */
	public static int[][] primtM(int number) {
		int height = number / 4 + 1;
		int width = number;
		int[][] arr = new int[height][width];
		boolean flag = false;
		int y = 0;
		int x = height - 1;
		for (int i = 1; i <= number; i++) {
			arr[x][y] = i;
			y++;
			if (flag) {
				x++;
				if (x == (height - 1)) {
					flag = false;
				}
			} else {
				x--;
				if (x == 0) {
					flag = true;
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		//int[][] arr = new int[3][30];
		// param1:几行(y);param2:几列(x);param3:从指定的数值开始
		// controller(arr,3,30,1);
		// print(arr);

		print(primtM(13));

	}

	static void print(int[][] arr) {
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0) {
					if(j>9){
						flag = true;
					}else{
						flag = false;
					}
					if(flag){
						System.out.print(" ");
					}else{
						System.out.print(" ");
					}
					// System.out.printf("%1$2s", "");
				} else {
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param arr
	 * @param strat要打印的开始数
	 * @param end要打印的结束数
	 * @param outlen控制二维数组中外层长度
	 *            (也就是打印几行的格式)纵y
	 */
	public static void controller(int[][] arr, int outlen, int end, int start) {
		/*
		 * int arr_out_len = arr.length;//二维数组中的外层数组长度 int arr_in_len = end;
		 * //二维数组中的内层数组长度
		 */
		int y = outlen - 1;
		/*
		 * 注意y的最大值不能超过二维数组中的内层长度 (其实是由end控制着) end应和二维数组中的内层长度一致
		 */
		int x = 0;
		boolean flag = false;
		while (true) {
			arr[y][x] = start;
			x++;

			if (flag) {
				y++;
				if (y == (outlen - 1)) {
					flag = false;
				}
			} else {

				y--;// 当为0时,则加操作
				if (y == 0) {
					flag = true;
				}
			}
			start++;
			if (start > end) {
				break;
			}
		}
	}
}
