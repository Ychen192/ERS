package dataTier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Reimbursement;

public class ReimbursementDAO {

	private Connection conn;

	public ReimbursementDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Reimbursement> getAllReimbursements() throws SQLException {
		String sql = "SELECT R.REIMB_ID, " + "R.REIMB_AMOUNT, " + "R.REIMB_SUBMITTED, " + "R.REIMB_RESOLVED, "
				+ "R.REIMB_DESCRIPTION, " + "CONCAT(CONCAT(Aut.USER_FIRST_NAME, ' '), Aut.USER_LAST_NAME), "
				+ "CONCAT(CONCAT(Res.USER_FIRST_NAME, ' '), Res.USER_LAST_NAME), " + "S.REIMB_STATUS, "
				+ "T.REIMB_TYPE " + "FROM ERS_REIMBURSEMENT R "
				+ "JOIN ERS_REIMBURSEMENT_STATUS S ON R.REIMB_STATUS_ID = S.REIMB_STATUS_ID "
				+ "JOIN ERS_REIMBURSEMENT_TYPE   T ON R.REIMB_TYPE_ID   = T.REIMB_TYPE_ID "
				+ "JOIN ERS_USERS Aut              ON R.REIMB_AUTHOR    = Aut.ERS_USERS_ID "
				+ "JOIN ERS_USERS Res              ON R.REIMB_RESOLVER  = Res.ERS_USERS_ID "
				+ "ORDER BY R.REIMB_SUBMITTED ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.executeQuery();

		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();

		while (rs.next()) {
			Reimbursement reImburse = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3),
					rs.getTimestamp(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9));
			reimbList.add(reImburse);
		}
		return reimbList;
	}

	public List<Reimbursement> getReimbursementsByUserId(int userId) throws SQLException {
		String sql = "SELECT R.REIMB_ID, " + "R.REIMB_AMOUNT, " + "R.REIMB_SUBMITTED, " + "R.REIMB_RESOLVED, "
				+ "R.REIMB_DESCRIPTION, " + "CONCAT(CONCAT(Aut.USER_FIRST_NAME, ' '), Aut.USER_LAST_NAME), "
				+ "CONCAT(CONCAT(Res.USER_FIRST_NAME, ' '), Res.USER_LAST_NAME), " + "S.REIMB_STATUS, "
				+ "T.REIMB_TYPE " + "FROM ERS_REIMBURSEMENT R "
				+ "JOIN ERS_REIMBURSEMENT_STATUS S ON R.REIMB_STATUS_ID = S.REIMB_STATUS_ID "
				+ "JOIN ERS_REIMBURSEMENT_TYPE   T ON R.REIMB_TYPE_ID   = T.REIMB_TYPE_ID "
				+ "JOIN ERS_USERS Aut              ON R.REIMB_AUTHOR    = Aut.ERS_USERS_ID "
				+ "JOIN ERS_USERS Res              ON R.REIMB_RESOLVER  = Res.ERS_USERS_ID "
				+ "WHERE Aut.ERS_USERS_ID = ? " + "ORDER BY R.REIMB_SUBMITTED ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		stmt.executeUpdate();
		ResultSet rs = stmt.executeQuery();

		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();

		while (rs.next()) {
			Reimbursement reImburse = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3),
					rs.getTimestamp(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9));
			reimbList.add(reImburse);
		}
		return reimbList;
	}

	public void changeReimbStatusToAccept(String[] idList) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);

			for (String id : idList) {

				String sql = "UPDATE ERS_REIMBURSEMENT " + "SET REIMB_STATUS_ID = 2 " + "Where REIMB_ID = "
						+ Integer.parseInt(id);
				stmt.addBatch(sql);
			}

			stmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeReimbStatusToDenied(String[] idList) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);

			for (String id : idList) {

				String sql = "UPDATE ERS_REIMBURSEMENT " + "SET REIMB_STATUS_ID = 3 " + "Where REIMB_ID = "
						+ Integer.parseInt(id);
				stmt.addBatch(sql);
			}

			stmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void insert(Reimbursement reimb) throws SQLException { try {
	 * String sql =
	 * "INSERT INTO ERS_REIMBURSEMENT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 * PreparedStatement stmt = conn.prepareStatement(sql);
	 * 
	 * stmt.setInt(1, reimb.getId()); stmt.setInt(2, reimb.getAmount());
	 * stmt.setTimestamp(3, reimb.getTimeSubmitted()); stmt.setTimestamp(4,
	 * reimb.getTimeResolved()); stmt.setString(5, reimb.getDescription());
	 * stmt.setInt(6, reimb.getAuthorID()); stmt.setInt(7,
	 * reimb.getResolverID()); stmt.setInt(8, reimb.getStatusID());
	 * stmt.setInt(9, reimb.getTypeID()); stmt.executeUpdate(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 */
}
