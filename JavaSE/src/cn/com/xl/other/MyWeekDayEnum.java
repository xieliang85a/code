package cn.com.xl.other;


public class MyWeekDayEnum {
	public static void main(String[] args) throws Exception{
//		WeekDayEnum w = WeekDayEnum.SUN;
//		System.out.println(w.nextDay());
		WeekDay ww = WeekDay.TUE;
		System.out.println(ww.nextDay().name());
		/*ClassLoader loader = Class.forName("cn.com.xl.other.GenericTest").getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader= loader.getParent();
		}
		System.out.println(loader);*/
	}
	public enum WeekDay{
		MON{
			@Override
			public WeekDay nextDay(){
				return TUE;
			}
		},TUE{
			public WeekDay nextDay(){
				return MON;
			}
		};
		abstract public WeekDay nextDay();
		@Override
		public String toString(){
			switch(this){
				case MON : return "星期一";
				case TUE : return "星期二";
			}
			return this.toString();
		}
	}
}
