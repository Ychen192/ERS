package dataTier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ReimbType;

public class ReimbTypeDAO {
		
	private Connection conn;
	
	public ReimbTypeDAO(Connection conn) {
		this.conn = conn;
	}

	public List<ReimbType> getAllReimbType() throws SQLException {
		String sql = "select * from ERS_REIMBURSEMENT_TYPE";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.executeQuery();
		List<ReimbType> typeList = new ArrayList<ReimbType>();

		while (rs.next()) {
			ReimbType rType = new ReimbType( rs.getInt(1), rs.getString(2) );
			typeList.add( rType );
		}
		conn.close();
		return typeList;
	}
	
}
