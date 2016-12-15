package middleTier;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import beans.Reimbursement;
import beans.Users;



public class BusinessDelegate {

	public Users login(String user, String pass) throws AuthenticationException, SQLException {
		return new UserService().authenticate(user, pass);
	}

	public List<Reimbursement> getAllReimbursement() throws SQLException {
		return new UserService().retrieveAllReimbursement();
	}
	
	public List<Reimbursement> getReimbursementByUserId(int userId) throws SQLException {
		return new UserService().retrieveReimbursementsById(userId);
	}
}
