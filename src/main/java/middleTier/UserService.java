package middleTier;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import beans.Reimbursement;
import beans.Users;
import dataTier.Facade;
import dataTier.ReimbursementDAO;

public class UserService {

	Users authenticate(String username, String password) throws AuthenticationException, SQLException {
		Facade dataTier = new Facade();
		Users user = dataTier.retrieveUserByUsername(username);
		if (user == null)
			throw new AuthenticationException();
		if (user.getPassWord().equals(password)) {
			return user;
		} else {
			throw new AuthenticationException();
		}
	}

	public List<Reimbursement> retrieveAllReimbursement() throws SQLException {
		return new Facade().retrieveAllReimbursement();
	}

	public List<Reimbursement> retrieveReimbursementsById(int userId) throws SQLException {
		return new Facade().retrieveReimbursementsByUserId(userId);
	}

	public void approveReimbursement(String[] idList) throws SQLException {
		new Facade().approveReimbursements(idList);
	}

	public void denyReimbursement(String[] idList) throws SQLException {
		new Facade().denyReimbursements(idList);
	}
}
