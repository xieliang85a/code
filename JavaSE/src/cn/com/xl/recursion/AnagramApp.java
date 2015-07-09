package cn.com.xl.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AnagramApp{
		static int size;
		static int count;
		static char[] arrChar = new char[100];
		public static void main(String[] args) {
			String input = getString();
			count = 0;
			size = input.length();
			for(int j = 0; j<size; j++){
				arrChar[j] = input.charAt(j);
			}
			doAnagram(size);
			
		}
		public static void doAnagram(int newSize){
			if(newSize == 1){
				return;
			}
			for(int j = 0; j<newSize; j++){
				doAnagram(newSize - 1);
				if(newSize == 2){
					displayWord();
				}
				rotate(newSize);
			}
		}
		private static void displayWord() {
			if(count < 99)
				System.out.print(" ");
			if(count <9)
				System.out.print(" ");
			System.out.print(++count + "");
			for(int j =0; j<size; j++){
				System.out.print(arrChar[j]);
			}
			System.out.print(" ");
			System.out.flush();
			if(count%6 == 0){
				System.out.println(" ");
			}
			
		}

		private static void rotate(int newSize) {
			int j;
			int position = size - newSize;
			char temp = arrChar[position];
			for(j=position+1; j<size; j++){
				arrChar[j-1] = arrChar[j];
				arrChar[j] = temp;
			}
			
		}
		public static String getString(){
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
		}
	}