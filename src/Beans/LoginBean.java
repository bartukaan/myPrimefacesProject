package Beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import DateBaseConnection.DBConnection;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {
	public static final String PROC_LOGINLOG = "{call PROCLOGINLOG(?,?,?)}";
	Connection conn = null;
	private DBConnection dbConn;
	private String userMail;
	private String password;
	private boolean Event = false;

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEvent() {
		return Event;
	}

	public void setEvent(boolean event) {
		Event = event;
	}

	public LoginBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		conn = dbConn.getConnection();
	}

	public void procCallForLogin() throws SQLException {
		CallableStatement cs = null;
		try {
			cs = this.conn.prepareCall(PROC_LOGINLOG);
			cs.setString(1, userMail.toUpperCase());
			cs.setString(2, password.toUpperCase());
			cs.registerOutParameter(3, java.sql.Types.BIT);
			cs.executeUpdate();

			Event = cs.getBoolean(3);
			System.out.println(Event);
			FacesContext context = FacesContext.getCurrentInstance();
			if (Event) {
				ExternalContext context1 = FacesContext.getCurrentInstance().getExternalContext();
				context1.redirect(context1.getRequestContextPath() + "/faces/order.xhtml");

			} else {
				FacesContext.getCurrentInstance().addMessage("MessageId",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Email or Password!"));
			}

		} catch (SQLException se) {
			System.out.println("SQL exception was handled.");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception was handled.");
			e.printStackTrace();
		} finally {
			if (cs != null)
				cs.close();
		}
	}

}
