package cn.com.xl.taffic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
	private List<String> vehicle = new ArrayList<String>();

	public Road(final String name) {

		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i < 100; i++) {
					try {
						Thread.currentThread().sleep(
								(new Random().nextInt(10) + 1) * 1000);
						vehicle.add(name + i);
					} catch (Exception e) {

					}

				}
			}
		}).start();

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(
				new Runnable(){
					public void run(){
						Lamp lamp = Lamp.valueOf(name);//获取到当前路线的灯
						if(lamp.isLight()){//如果当前灯是绿的
							
							if(vehicle.size()!=0){
								System.out.println(vehicle.remove(0)+"穿过红绿灯了!");
							}
						}
					}
				}
				, 2
				, 2//每隔两秒移动一辆车
				, TimeUnit.SECONDS);
		
	}
}
