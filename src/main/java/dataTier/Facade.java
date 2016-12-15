package dataTier;

import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.List;

import beans.Reimbursement;
import beans.Users;

public class Facade {

	// JAR in classpath:
	// C:\Oracle\Middleware\Oracle_Home\wlserver\server\lib\weblogic.jar
	Connection conn;

	public Facade() {
		try {
			conn = ServiceLocator.getErsDatabase().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// insert one reimbursement object into DB
	/*
	 * public void insert(Reimbursement reimb) throws SQLException { //
	 * Timestamp submitted = new Timestamp( System.currentTimeMillis() ); //
	 * Timestamp resolved = new Timestamp( System.currentTimeMillis() ); //
	 * reimb = new Reimbursement(7, 200, submitted, resolved, "descriptions //
	 * of stuff", 1, 1, 1, 1); new ReimbursementDAO(conn).insert(reimb); }
	 */

	// insert one User object into DB
	public void insert(Users user) throws SQLException {
		// String hashed = UsersDAO.jbEncrpyt(user.getPassWord());
		// user.setPassWord(hashed);
		new UsersDAO(conn).insert(user);
	}

	// retrieve reimbursement data
	public List<Reimbursement> retrieveAllReimbursement() throws SQLException {
		return new ReimbursementDAO(conn).getAllReimbursements();
	}

	// retrieve all users data
	public List<Users> retrieveAllUsers() throws SQLException {
		return new UsersDAO(conn).getAllUsers();
	}

	// retrieve user data where by username
	public Users retrieveUserByUsername(String username) throws SQLException {
		return new UsersDAO(conn).getUserByName(username);
	}
	
	// retrieve reimbursements by user id
	public List<Reimbursement> retrieveReimbursementsByUserId(int userId) throws SQLException {
		return new ReimbursementDAO(conn).getReimbursementsByUserId( userId );
	}
	
	// approve the reimbursements status given list of reimbursement_id
	public void approveReimbursements(String[] idList) throws SQLException{
		new ReimbursementDAO(conn).changeReimbStatusToAccept(idList);
	}
	
	// deny the reimbursements status given list of reimbursement_id
	public void denyReimbursements(String[] idList) throws SQLException{
		new ReimbursementDAO(conn).changeReimbStatusToDenied(idList);
	}
	
	

}
