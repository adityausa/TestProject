package test.dhw;

public class Enums {

	FrequencyEnum test;
	
	public static enum FrequencyEnum {
		WEEKLY("W"), MONTHLY("M");

		private String value;

		FrequencyEnum(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		public static String getEnumName(String value) {
			for(FrequencyEnum frequencyEnum : FrequencyEnum.values()) {
				if(value.equalsIgnoreCase(frequencyEnum.value)){
					return frequencyEnum.name();
				}
			}
			return null;
		}
			
	}
	
	public FrequencyEnum getTest() {
		return test;
	}

	public void setTest(FrequencyEnum test) {
		this.test = test;
	}

	public static void main(String args[]) {
		Enums enums = new Enums();
		enums.setTest(FrequencyEnum.MONTHLY);
		int x = 10;
		int y = 25;
		int z = x + y;

		System.out.println("Sum of x+y = "+ FrequencyEnum.getEnumName("M"));
	}



}