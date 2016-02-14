package Beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import DateBaseConnection.DBConnection;
import Model.Drink;
import Model.Meal;
import Model.Restaurant;

@ManagedBean(name = "orderBean")
@SessionScoped
public class OrderBean {
	private Restaurant restaurant;
	private List<Restaurant> restaurants;
	private List<String> restaurantsList;
	private String selectedRestaurants = null;
	private boolean mealView = false;

	private Meal meal;
	private List<Meal> meals;
	private List<String> mealsList;
	private String selectedMeals = null;

	private Drink drink;
	private List<Drink> drinks;
	private List<String> drinksList;
	private String selectedDrinks = null;

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	private DBConnection dbConn;

	@SuppressWarnings("static-access")
	public OrderBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		conn = dbConn.getConnection();
		getAllRestaurants();
		selectedDrinks = null;
		selectedRestaurants = null;
		selectedMeals = null;
	}

	public void getAllRestaurants() throws SQLException {
		restaurants = new ArrayList<Restaurant>();
		restaurantsList = new ArrayList<String>();
		ps = this.conn
				.prepareStatement("SELECT RESTAURANTNAME, OPENINGHOUR, CLOSINGHOUR,RESTAURANTID FROM  RESTAURANT ");
		rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				restaurant = new Restaurant();
				restaurant.setRestaurantName(rs.getString(1));
				restaurant.setOpeningHour(rs.getTime(2));
				restaurant.setClosingHour(rs.getTime(3));
				restaurant.setRestaurantId(rs.getInt(4));
				restaurants.add(restaurant);
				restaurantsList.add(restaurant.getRestaurantName());
			}

		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public void handleSelectRestaurant() throws SQLException {
		mealView = true;
		meals = new ArrayList<Meal>();
		mealsList = new ArrayList<String>();
		ps = this.conn.prepareStatement("SELECT MEALNAME,MEALPRICE FROM MENUVIEW WHERE RESTAURANTNAME=?");
		ps.setString(1, selectedRestaurants);
		rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				meal = new Meal();
				meal.setMealName(rs.getString(1));
				meal.setMealPrice(rs.getDouble(2));
				meals.add(meal);
				System.out.println(meal.toString());
				mealsList.add(meal.getMealName() + " " + meal.getMealPrice() + " TL");
			}

		} finally {
			if (rs != null)
				rs.close();
		}
		ExternalContext context1 = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context1.redirect(context1.getRequestContextPath() + "/faces/order.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drinks = new ArrayList<Drink>();
		drinksList = new ArrayList<String>();
		ps = this.conn.prepareStatement("SELECT DRINKNAME,DRINKPRICE FROM MENUVIEW WHERE RESTAURANTNAME=?");
		ps.setString(1, selectedRestaurants);
		rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				drink = new Drink();
				drink.setDrinkName(rs.getString(1));
				drink.setDrinkPrice(rs.getDouble(2));
				drinks.add(drink);
				System.out.println(drink.toString());
				drinksList.add(drink.getDrinkName() + " " + drink.getDrinkPrice() + "TL");
			}

		} finally {
			if (rs != null)
				rs.close();
		}

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void buttonAction(ActionEvent actionEvent)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		addMessage("Order receieved to " + selectedRestaurants + "!!");
		System.out
				.println("Restaurant: " + selectedRestaurants + " Meal: " + selectedMeals + "Drink: " + selectedDrinks);

		String selectedMeal = selectedMeals;
		int tempInt = selectedMeal.indexOf(' ');
		String mealWord = selectedMeal.substring(0, tempInt);

		String selectedDrink = selectedDrinks;
		int tempInt2 = selectedDrink.indexOf(' ');
		String drinkWord = selectedDrink.substring(0, tempInt2);
		ps = this.conn.prepareStatement(
				"INSERT INTO ORDERS VALUES((SELECT USERID FROM USERS WHERE USERMAIL='ILHAN.KESKIN35@GMAIL.COM' ),  "
						+ "1," + "(SELECT RESTAURANTID FROM RESTAURANT WHERE RESTAURANTNAME=?),"
						+ "(SELECT MEALID FROM MEAL WHERE MEALNAME = ?),"
						+ "(SELECT DRINKID FROM DRINK WHERE DRINKNAME=?))");
		ps.setString(1,selectedRestaurants);
		ps.setString(2,mealWord);
		ps.setString(3,drinkWord);
		ps.executeUpdate();
		System.out.println("denendi	");
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public List<String> getRestaurantsList() {
		return restaurantsList;
	}

	public void setRestaurantsList(List<String> restaurantsList) {
		this.restaurantsList = restaurantsList;
	}

	public String getSelectedRestaurants() {
		return selectedRestaurants;
	}

	public void setSelectedRestaurants(String selectedRestaurants) {
		this.selectedRestaurants = selectedRestaurants;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public List<String> getMealsList() {
		return mealsList;
	}

	public void setMealsList(List<String> mealsList) {
		this.mealsList = mealsList;
	}

	public String getSelectedMeals() {
		return selectedMeals;
	}

	public void setSelectedMeals(String selectedMeals) {
		this.selectedMeals = selectedMeals;
	}

	public boolean isMealView() {
		return mealView;
	}

	public void setMealView(boolean mealView) {
		this.mealView = mealView;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

	public List<String> getDrinksList() {
		return drinksList;
	}

	public void setDrinksList(List<String> drinksList) {
		this.drinksList = drinksList;
	}

	public String getSelectedDrinks() {
		return selectedDrinks;
	}

	public void setSelectedDrinks(String selectedDrinks) {
		this.selectedDrinks = selectedDrinks;
	}

}
