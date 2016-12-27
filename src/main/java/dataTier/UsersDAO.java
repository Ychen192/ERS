package dataTier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import beans.Users;

public class UsersDAO {

	private Connection conn;

	public UsersDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public static String jbEncrpyt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public void insert(Users user) throws SQLException {
		try {
			String sql = "INSERT INTO ERS_USERS VALUES(?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getPassWord());
			stmt.setString(4, user.getFirstName());
			stmt.setString(5, user.getLastName());
			stmt.setString(6, user.getEmail());
			stmt.setInt(7, user.getRoleID());

			stmt.executeUpdate();
			System.out.println("executed");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Users> getAllUsers() throws SQLException {
		String sql = "select * from ERS_USERS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.executeQuery();
		List<Users> userList = new ArrayList<Users>();

		while (rs.next()) {
			Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			userList.add(user);
		}
		conn.close();
		return userList;
	}

	public Users getUserByName(String username) throws SQLException {

		String sql = "select * from ERS_USERS WHERE ERS_USERNAME = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, username);
		stmt.executeUpdate();
		ResultSet rs = stmt.executeQuery();

		Users user = null;
		while (rs.next()) {
			user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
		}
		conn.close();
		return user;
	}

}
