package webTier;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ReimbRecord;
import beans.ReimbType;
import beans.Reimbursement;
import beans.Users;
import middleTier.BusinessDelegate;
import middleTier.UserService;

public class MainController {

	/**
	 * Accept or Deny all the reimbursements that are checked
	 */
	public void udpateReimbursementStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String[] listOfIdToUpdate = request.getParameterValues("status");
		Users session = (Users) request.getSession().getAttribute("user");
		int resolverID = session.getUserID();
		// Accept all the checked reimbursements if accept button is click
		if (request.getParameter("accept") != null) {
			new UserService().approveReimbursement(listOfIdToUpdate, resolverID);
		}
		// Accept all the checked reimbursements if deny button is click
		else if (request.getParameter("deny") != null) {
			new UserService().denyReimbursement(listOfIdToUpdate, resolverID);
		} else {
			return;
		}

		// Re-fetch the data, then refresh the page
		List<Reimbursement> list = new BusinessDelegate().getAllReimbursement();
		request.setAttribute("name", session.getFirstName() + " " + session.getLastName());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/secure/mReimbursement.jsp").forward(request, response);
	}

	// returns a list of all reimbursement types
	public List<ReimbType> getListOfReimbType() throws SQLException {
		return new UserService().retrieveAllReimbType();
	}

	// create a new reimbursement record
	public void submitNewReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		Users session = (Users) request.getSession().getAttribute("user");
		
		//int reimbID = Integer.parseInt( request.getParameter("rID") );
		int reimbID = 1; //sequence created in db to auto assign id
		float reimbAmount = Float.parseFloat(request.getParameter("rAmount"));
		Timestamp timeSubmitted = new Timestamp( System.currentTimeMillis() );
		String reimbDescription = request.getParameter("rDescription");
		int reimbAuthorID = session.getUserID();
		int reimbStatusID = 1;	// all new records has status pending (which has id = 1)
		int reimbTypeID = Integer.parseInt(request.getParameter("rType"));
		
		ReimbRecord newRec = new ReimbRecord(reimbID, reimbAmount, timeSubmitted, reimbDescription, reimbAuthorID, reimbStatusID, reimbTypeID);
		new UserService().createNewReimbursement(newRec);
		
		// Re-fetch the data, then refresh the page
		List<Reimbursement> list = new BusinessDelegate().getReimbursementByUserId(session.getUserID());
		request.setAttribute("name", session.getFirstName() + " " + session.getLastName());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/secure/eReimbursement.jsp").forward(request, response);
	}

}
