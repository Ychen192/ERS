package dataTier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.ReimbRecord;

public class ReimbRecordDAO {

	Connection conn;

	public ReimbRecordDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void insert(ReimbRecord reimb) throws SQLException {
		try {
			String sql = "INSERT INTO ERS_REIMBURSEMENT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			
			stmt.setInt(1, reimb.getId());
			stmt.setFloat(2, reimb.getAmount());
			stmt.setTimestamp(3, reimb.getTimeSubmitted());
			stmt.setTimestamp(4, reimb.getTimeResolved());
			stmt.setString(5, reimb.getDescription());
			stmt.setInt(6, reimb.getAuthorID());
			stmt.setInt(7, reimb.getResolverID());
			stmt.setInt(8, reimb.getStatusID());
			stmt.setInt(9, reimb.getTypeID());
			stmt.executeUpdate();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
