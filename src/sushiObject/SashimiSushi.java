package sushiObject;

public class SashimiSushi extends Sushi {
	private String typeOfSashimi;
	public static int sashimiCount = 0;
	
	public SashimiSushi(String typeOfSashimi) {
		super("Sashimi");
		this.typeOfSashimi = typeOfSashimi;
		sashimiCount++;
	}
	
	public String getTypeOfSashimi() {
		return typeOfSashimi;
	}

}
