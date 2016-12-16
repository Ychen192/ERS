package middleTier;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import beans.ReimbRecord;
import beans.ReimbType;
import beans.Reimbursement;
import beans.Users;
import dataTier.Facade;

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

	public void approveReimbursement(String[] idList, int resolverID) throws SQLException {
		new Facade().approveReimbursements(idList, resolverID);
	}

	public void denyReimbursement(String[] idList, int resolverID) throws SQLException {
		new Facade().denyReimbursements(idList, resolverID);
	}

	public List<ReimbType> retrieveAllReimbType() throws SQLException {
		return new Facade().retrieveAllReimbType();
	}
	
	public void createNewReimbursement( ReimbRecord reimb ) throws SQLException {
		new Facade().insert(reimb);
	}
	
	
}
