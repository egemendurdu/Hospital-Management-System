package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doktor extends User {
	public Doktor() {
	}

	public Doktor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
	}
	
	public boolean addWhour(int doktor_id, String doktor_name, String wdate) throws SQLException {
		boolean key = false;
		int count = 0;
		String query = "INSERT INTO whour" + "(doktor_id,doktor_name,wdate) VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM whour WHERE status='a' AND doktor_id=" + doktor_id + " AND wdate ='" + wdate + "'");
			while(rs.next()) {
				count++;
				break;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doktor_id);
				preparedStatement.setString(2, doktor_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public ArrayList<Whour> getWhourList(int doktor_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doktor_id="+doktor_id);
			while(rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoktor_id(rs.getInt("doktor_id"));
				obj.setDoktor_name(rs.getString("doktor_name"));
				obj.setWdate(rs.getString("wdate"));
				obj.setStatus(rs.getString("status"));
				list.add(obj);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} 
		return list;
		
	}
	
	public boolean deleteWhour(int id) {
		
		String query = "Delete From whour Where id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return key;
	}
	
}














