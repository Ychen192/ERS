package webTier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Reimbursement;
import middleTier.BusinessDelegate;
import middleTier.UserService;

public class MainController {

	public void udpateReimbursementStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String[] listOfIdToUpdate = request.getParameterValues("status");

		if (request.getParameter("accept") != null) {
			new UserService().approveReimbursement(listOfIdToUpdate);
		} else if (request.getParameter("deny") != null) {
			new UserService().denyReimbursement(listOfIdToUpdate);
		} else {
			return;
		}
		List<Reimbursement> list = new BusinessDelegate().getAllReimbursement();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/secure/mReimbursement.jsp").forward(request, response);

	}
}
