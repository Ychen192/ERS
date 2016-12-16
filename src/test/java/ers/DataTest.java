package ers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.ReimbType;
import beans.Reimbursement;
import beans.Users;
import dataTier.ReimbTypeDAO;
import dataTier.ReimbursementDAO;
import dataTier.ServiceLocator;
import dataTier.UsersDAO;

public class DataTest {
	public static void main(String[] args) throws SQLException, Exception {
		
		
		// getAllReimbTypeTest();
		// getReimbursementByUserIdTest(2);
		// getUsersByNameTest("user");
		// getAllUsersTest();
		
			String[] idList = {"1", "2"};
			updateReimbursementStatusTest( idList,1 );
		getAllReimbursementViewTest();
	}
	
	public static void getAllReimbTypeTest() throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<ReimbType> reimbTypeList = new ReimbTypeDAO(conn).getAllReimbType();
		for (ReimbType type : reimbTypeList) {
			System.out.println(type);
		}
		System.out.println("done");
	}
	
	
	public static void updateReimbursementStatusTest(String[] idList, int authorID) throws SQLException, Exception{
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		new ReimbursementDAO(conn).changeReimbStatusToAccept(idList, authorID);
		System.out.println("done");
	}
	

	public static void getReimbursementByUserIdTest(int id) throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<Reimbursement> reimbList = new ReimbursementDAO(conn).getReimbursementsByUserId(id);
		for (Reimbursement reimb : reimbList) {
			System.out.println(reimb);
		}
		System.out.println("done");
	}

	public static void getAllReimbursementViewTest() throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<Reimbursement> reimbList = new ReimbursementDAO(conn).getAllReimbursements();
		for (Reimbursement reimb : reimbList) {
			System.out.println(reimb);
		}
		System.out.println("done");
	}

	public static void getAllUsersTest() throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		List<Users> userList = new UsersDAO(conn).getAllUsers();
		for (Users u : userList) {
			System.out.println(u);
		}
		System.out.println("done");
	}

	public static void getUsersByNameTest(String username) throws SQLException, Exception {
		Connection conn = ServiceLocator.getErsDatabase().getConnection();
		Users user = new UsersDAO(conn).getUserByName(username);
		System.out.println(user);
		System.out.println("done");
	}
	
}
