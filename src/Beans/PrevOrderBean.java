package Beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DateBaseConnection.DBConnection;
import Model.Order;

@ManagedBean(name = "prevOrderBean")
@SessionScoped
public class PrevOrderBean {

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	private DBConnection dbConn;
	private List<Order> orders;
	private Order order;

	@SuppressWarnings("static-access")
	public PrevOrderBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		conn = dbConn.getConnection();
	}

	public void getAllOrdersViaView()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		LoginBean log=new LoginBean();
		String userMail="ILHAN.KESKIN35@GMAIL.COM";
		orders = new ArrayList<Order>();
		ps = this.conn.prepareStatement(
				"SELECT ORDERID, RESTAURANTNAME ,ORDERNOTE, ORDERTIME, MEALNAME, DRINKNAME,DEBIT FROM VIEWALLORDERSBYUSERMAIL WHERE USERMAIL=?");
		ps.setString(1,userMail);
		rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setRestaurantName(rs.getString(2));
				order.setOrderNote(rs.getString(3));
				order.setOrderTime(rs.getTime(4));
				order.setMealName(rs.getString(5));
				order.setDrinkName(rs.getString(6));
				order.setDebit(rs.getDouble(7));
				orders.add(order);
			}

		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
