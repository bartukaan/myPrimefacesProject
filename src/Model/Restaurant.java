package Model;

import java.sql.Time;

public class Restaurant {
    int RestaurantId;
	String RestaurantName;
	Time OpeningHour;
	Time ClosingHour;
	
	
	public int getRestaurantId() {
		return RestaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		RestaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return RestaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		RestaurantName = restaurantName;
	}
	public Time getOpeningHour() {
		return OpeningHour;
	}
	public void setOpeningHour(Time openingHour) {
		OpeningHour = openingHour;
	}
	public Time getClosingHour() {
		return ClosingHour;
	}
	public void setClosingHour(Time closingHour) {
		ClosingHour = closingHour;
	}
	
	
	@Override
	public String toString() {
		return "Restaurant [RestaurantId=" + RestaurantId + ", RestaurantName=" + RestaurantName + ", OpeningHour="
				+ OpeningHour + ", ClosingHour=" + ClosingHour + "]";
	}
	public Restaurant (){
		
	}
	
	
}
