package Model;

public class Meal {
	int MealId;
	String MealName;
	double MealPrice;
	double MealPortion;
	public int getMealId() {
		return MealId;
	}
	public String getMealName() {
		return MealName;
	}
	public void setMealName(String mealName) {
		MealName = mealName;
	}
	public double getMealPrice() {
		return MealPrice;
	}
	public void setMealPrice(double mealPrice) {
		MealPrice = mealPrice;
	}
	public double getMealPortion() {
		return MealPortion;
	}
	public void setMealPortion(double mealPortion) {
		MealPortion = mealPortion;
	}
	
	@Override
	public String toString() {
		return "Meal [MealId=" + MealId + ", MealName=" + MealName + ", MealPrice=" + MealPrice + ", MealPortion="
				+ MealPortion + "]";
	}
	public Meal () {
		
	}

}
