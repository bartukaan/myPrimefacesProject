package Model;

public class Drink {
	int DrinkId;
	String DrinkName;
	double DrinkPrice;
    String DrinkBrand;
	public int getDrinkId() {
		return DrinkId;
	}
	public void setDrinkId(int drinkId) {
		DrinkId = drinkId;
	}
	public String getDrinkName() {
		return DrinkName;
	}
	public void setDrinkName(String drinkName) {
		DrinkName = drinkName;
	}
	public double getDrinkPrice() {
		return DrinkPrice;
	}
	public void setDrinkPrice(double drinkPrice) {
		DrinkPrice = drinkPrice;
	}
	public String getDrinkBrand() {
		return DrinkBrand;
	}
	public void setDrinkBrand(String drinkBrand) {
		DrinkBrand = drinkBrand;
	}
		
	@Override
	public String toString() {
		return "Drink [DrinkId=" + DrinkId + ", DrinkName=" + DrinkName + ", DrinkPrice=" + DrinkPrice + ", DrinkBrand="
				+ DrinkBrand + "]";
	}
	public Drink(){
		
	}
}
