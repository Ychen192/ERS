package webTier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ReimbType;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// overriding init() to use ServletContext
		try {
			List<ReimbType> listOfTypeObject = new MainController().getListOfReimbType();
			this.getServletContext().setAttribute("listOfType", listOfTypeObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		System.out.println(requestURI);

		LoginController loginCtrl = new LoginController();

		switch (requestURI) {

		case "/ers/login.do": {
			try {
				loginCtrl.login(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/ers/employee.do": {
			loginCtrl.validateSession(request, response);
			request.getRequestDispatcher("/secure/eReimbursement.jsp").forward(request, response);
			break;
		}
		case "/ers/manager.do": {
			loginCtrl.validateSession(request, response);
			request.getRequestDispatcher("/secure/mReimbursement.jsp").forward(request, response);
			break;
		}
		case "/ers/updateStatus.do": {
			try {
				new MainController().udpateReimbursementStatus(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/ers/submitReimbursement.do": {
			try {
				new MainController().submitNewReimbursement(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case "/ers/logOut.do": {
			request.getSession().invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		}
		default: {
			response.setStatus(404);
		}
		}
	}
}
