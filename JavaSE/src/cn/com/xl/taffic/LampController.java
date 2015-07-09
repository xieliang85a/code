package cn.com.xl.taffic;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class LampController {

	private Lamp currentLamp;
	public LampController(){
		currentLamp = Lamp.S2N;//初始化第一个灯
		currentLamp.green();//将第一个灯置为绿灯
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(
				new Runnable(){
					public void run(){
						System.out.println("LapmController置换"+currentLamp.name());
						currentLamp = currentLamp.red();
					}
				}
				, 10
				, 10
				, TimeUnit.SECONDS);
	}
}
