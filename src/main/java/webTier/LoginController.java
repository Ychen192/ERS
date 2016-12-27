package webTier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Reimbursement;
import beans.Users;
import middleTier.BusinessDelegate;

public class LoginController {

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userid");
		String pass = request.getParameter("pass");

		try {
			Users session = new BusinessDelegate().login(user, pass);
			request.getSession().setAttribute("user", session);
			request.setAttribute("name", session.getFirstName() + " " + session.getLastName());
			request.getSession().setMaxInactiveInterval(15*60);
			
			if (session.getRoleID() == 2) {
				List<Reimbursement> list = new BusinessDelegate().getAllReimbursement();
				request.setAttribute("list", list);
				request.getRequestDispatcher("manager.do").forward(request, response);
			} else {
				int userId = session.getUserID();
				List<Reimbursement> list = new BusinessDelegate().getReimbursementByUserId(userId);
				request.setAttribute("list", list);
				request.getRequestDispatcher("employee.do").forward(request, response);
			}
		} catch (AuthenticationException e) {
			request.setAttribute("authFailed", "Incorrect password! ");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("authFailed", "Connection error!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	public Users validateSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( request.getSession().getAttribute("user") != null ){
			return (Users) request.getSession().getAttribute("user");
		}else{
			request.getRequestDispatcher("/error/403.html").forward(request, response);
			return null;
		}
	}

}
