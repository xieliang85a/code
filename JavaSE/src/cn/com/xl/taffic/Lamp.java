package cn.com.xl.taffic;

public enum Lamp {

	/*每个枚举元素各表示一个方向的控制灯*/	
	S2N("N2S","S2W",false),S2W("N2E","E2W",false),E2W("W2E","E2S",false),E2S("W2N","S2N",false),
	/*下面元素表示与上面的元素的相反方向的灯，它们的“相反方向灯”和“下一个灯”应忽略不计！*/
	N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
	/*由南向东和由西向北等右拐弯的灯不受红绿灯的控制，所以，可以假想它们总是绿灯*/
	S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
	
	private Lamp(String oppoiste,String nextLamp,boolean light){
		this.oppoiste = oppoiste;
		this.nextLamp = nextLamp;
		this.light = light;
	}
	
	private String oppoiste;//对面灯(同下)
	private String nextLamp;//下一个灯的字符串格式(由于用实例对象的话,会存在先声明才能使用的问题)
	private boolean light;	//当前灯的状态
	
	public boolean isLight(){
		return light;
	}
	
	//当某个方向的灯变绿时,则其对面的灯也应变绿
	public void green(){
		if(oppoiste!=null){
			Lamp.valueOf(oppoiste).green();
		}
		this.light = true;
	}
	
	//当某个方向的灯变红灯时,则其下一个方向的灯变绿(返回)
	public Lamp red(){
		if(oppoiste!=null){
			Lamp.valueOf(oppoiste).red();
		}
		this.light = false;
		
		Lamp _lamp = null;
		if(nextLamp!=null){
			_lamp = Lamp.valueOf(nextLamp);
			System.out.println("绿灯从"+name()+"切换到"+nextLamp);
			_lamp.green();
		}
		return _lamp;
	}
	
	
	
	
	
	
}
