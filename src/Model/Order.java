package Model;

import java.sql.Time;

public class Order {

	
	public Order() {
	}
	int orderId;
	String restaurantName;
	String orderNote;
	Time orderTime;
	String mealName;
	String drinkName;
	double debit;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public Time getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", restaurandName=" + restaurantName + ", orderNote=" + orderNote
				+ ", orderTime=" + orderTime + ", mealName=" + mealName + ", drinkName=" + drinkName + "]";
	}
	
	
	
	
}
