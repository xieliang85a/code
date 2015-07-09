package cn.com.xl.other;

public abstract class WeekDayEnum {
	private WeekDayEnum(){}
	
	public final static WeekDayEnum MON= new WeekDayEnum(){
		public WeekDayEnum nextDay(){
			return SUN;
		}
	};
	public final static WeekDayEnum SUN= new WeekDayEnum(){
		public WeekDayEnum nextDay(){
			return MON;
		}
	};
	
	
	public abstract WeekDayEnum nextDay();
	
	public String toString(){
		if(this == MON){
			return "星期一";
		}
		return "星期日";
	}
}

