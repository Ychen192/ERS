package ers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Reimbursement;
import beans.Users;
import dataTier.ReimbursementDAO;
import dataTier.ServiceLocator;
import dataTier.UsersDAO;

public class DataTest {
	public static void main(String[] args) throws SQLException, Exception {

		//getReimbursementByUserIdTest(2);
		
		// getUsersByNameTest("user");
		// getAllUsersTest();
		String[] idList = {"92","710","669","945","387"};
		updateReimbursementStatusTest(idList);
		getAllReimbursementViewTest();
	}
	
	
	public static void updateReimbursementStatusTest(String[] idList) throws SQLException, Exception{
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		new ReimbursementDAO(conn).changeReimbStatusToAccept(idList);
	}
	

	public static void getReimbursementByUserIdTest(int id) throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<Reimbursement> reimbList = new ReimbursementDAO(conn).getReimbursementsByUserId(id);
		for (Reimbursement reimb : reimbList) {
			System.out.println(reimb);
		}
	}

	public static void getAllReimbursementViewTest() throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<Reimbursement> reimbList = new ReimbursementDAO(conn).getAllReimbursements();
		for (Reimbursement reimb : reimbList) {
			System.out.println(reimb);
		}
	}

	public static void getAllUsersTest() throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<Users> userList = new UsersDAO(conn).getAllUsers();
		for (Users u : userList) {
			System.out.println(u);
		}
	}

	public static void getUsersByNameTest(String username) throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		Users user = new UsersDAO(conn).getUserByName(username);
		System.out.println(user);
	}
}
