package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
	List <String> vehicle = new ArrayList<String>();
	public Road(final String name){
		ExecutorService pool = Executors.newFixedThreadPool(1);
		pool.execute(new Runnable(){
			public void run(){
				for(int i=1;i<=50;i++){
					try{
						Thread.currentThread().sleep(1000);
						vehicle.add(name+i+"车");
					}catch(Exception e){
						
					}
				}
			}
		});
		
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(
				new Runnable(){
					public void run(){
						if(Lamp.valueOf(name).isLight()){
							if(vehicle.size()!=0)
								System.out.println(vehicle.remove(0) + "过去了");
						}
					}
				}
				, 2
				, 2
				, TimeUnit.SECONDS);
	}
}
