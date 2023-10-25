package sushiObject;

public class NigiriSushi extends Sushi {
    public static int nigiriCount = 0;
    private String topping;

    public NigiriSushi(String topping) {
        super("Nigiri");
        this.topping = topping;
        nigiriCount++;
    }

    public String getTopping() {
        return topping;
    }

}
