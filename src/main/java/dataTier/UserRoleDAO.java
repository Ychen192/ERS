package dataTier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDAO {
	private Connection conn;

	public UserRoleDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	// using objects instead of multiple fields is good practice
	public void insert(int userRoleID, String userRole) throws SQLException {
		String sql = "insert into ERS_USERS_ROLES values (?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userRoleID);
		stmt.setString(2, userRole);
		stmt.executeUpdate();
		conn.close();
	}

	public void getAll() throws SQLException {
		String sql = "select * from ERS_USERS_ROLES";
		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("ERS_USER_ROLE_ID");
			String name = rs.getString("USER_ROLE");
			System.out.println(id + " " + name);
		}
		conn.close();
	}

}
