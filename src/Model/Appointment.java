package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id,doktorID,hastaID;
	private String doktorName,hastaName,appDate;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Appointment(int id, int doktorID, String doktorName, int hastaID, String hastaName, String appDate) {
		super();
		this.id = id;
		this.doktorID = doktorID;
		this.hastaID = hastaID;
		this.doktorName = doktorName;
		this.hastaName = hastaName;
		this.appDate = appDate;
	}
	
	public Appointment() {
	}
	
	public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("Select * From appointment WHERE hasta_id="+ hasta_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoktorID(rs.getInt("doktor_id"));
				obj.setDoktorName(rs.getString("doktor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;

	}
	
	public ArrayList<Appointment> getDoktorList(int doktor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("Select * From appointment WHERE hasta_id="+ doktor_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoktorID(rs.getInt("doktor_id"));
				obj.setDoktorName(rs.getString("doktor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoktorID() {
		return doktorID;
	}

	public void setDoktorID(int doktorID) {
		this.doktorID = doktorID;
	}

	public int getHastaID() {
		return hastaID;
	}

	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}

	public String getDoktorName() {
		return doktorName;
	}

	public void setDoktorName(String doktorName) {
		this.doktorName = doktorName;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
}
