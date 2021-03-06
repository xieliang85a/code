package cn.com.xl.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureCallable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Future<String> f = pool.submit(new Callable<String>(){

			
			public String call() throws Exception {
				System.out.println("开始存放数据!");
				Thread.sleep(1000);
				return "hello";
			}
			
		});
		
		
		try{
			System.out.println("等待结果:"+f.get(1,TimeUnit.SECONDS));
			System.out.println();
		}catch(Exception e){
			
		}
		
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(pool);
		for(int i = 0;i<10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>(){
				public Integer call() throws Exception{
					Thread.sleep(new Random().nextInt(10)*500);
					return seq;
				}
			});
		}
		
		for(int i = 0;i<10;i++){
			try{
				System.out.println(completionService.take().get());
			}catch(Exception e){
				
			}
		}

	}

}
