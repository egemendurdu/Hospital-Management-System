package Model;

import java.sql.SQLException;

public class Hasta extends User {
	public Hasta() {
	}

	public Hasta(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
	}

	public boolean register(String tcno, String password, String name) throws SQLException {
		boolean key = false;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "' ");
			while (rs.next()) {
				duplicate = true;
				break;
			}
			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	public boolean addAppointment(int doktor_id, String doktor_name, int hasta_id, String hasta_name, String app_date) throws SQLException {
		boolean key = false;
		String query = "INSERT INTO appointment" + "(doktor_id,doktor_name,hasta_id,hasta_name,app_date) VALUES" + " (?,?,?,?,?)";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doktor_id);
			preparedStatement.setString(2, doktor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, app_date);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public boolean updateWhourStatus(int doktor_id, String wdate) throws SQLException {
		boolean key = false;
		String query = "UPDATE whour SET status = ? WHERE doktor_id= ? AND wdate= ?";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doktor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
}









