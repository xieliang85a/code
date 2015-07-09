package temp;

public enum Lamp {
	S2N("N2S","S2W",false),S2W("N2E","E2W",false),E2W("W2E","E2S",false),E2S("W2N","S2N",false),
	/*下面元素表示与上面的元素的相反方向的灯，它们的“相反方向灯”和“下一个灯”应忽略不计！*/
	N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
	/*由南向东和由西向北等右拐弯的灯不受红绿灯的控制，所以，可以假想它们总是绿灯*/
	S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
	
	private Lamp(String opposite, String next, boolean light){
		this.opposite = opposite;
		this.next = next;
		this.light = light;
		
	}
	private boolean light;
	private String opposite;
	private String next;
	
	public boolean isLight(){
		return light;
	}
	
	public void green(){
		if(opposite!=null){
			Lamp.valueOf(opposite).green();
		}
		this.light = true;
	}
	
	public Lamp red(){
		if(opposite!=null){
			Lamp.valueOf(opposite).red();
		}
		this.light = false;
		//以上将绿灯置红(正在)
		//以下将红灯置绿(下一)
		Lamp nextLamp = null;
		if(next!=null){
			nextLamp = Lamp.valueOf(next);
			nextLamp.green();
		}
		return nextLamp;
	}
}
