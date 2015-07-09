package temp;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LampController {
	private Lamp currentLamp;
	public LampController(){
		currentLamp = currentLamp.S2N;
		currentLamp.green();
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable(){
					public void run(){
						System.out.println("置换前"+currentLamp);
						currentLamp = currentLamp.red();
						System.out.println("置换后"+currentLamp);
					}
				}
				, 10
				, 10, TimeUnit.SECONDS);
	}
}
