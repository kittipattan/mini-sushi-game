package sushiObject;

public class Sushi {
	private String typeOfSushi;
	public static int sushiCount = 0;
	
	public Sushi(String typeOfSushi) {
		this.typeOfSushi = typeOfSushi;
		sushiCount++;
	}
	
	public String getTypeOfSushi() {
		return typeOfSushi;
	}
	
}
