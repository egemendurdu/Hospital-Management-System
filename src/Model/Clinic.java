package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {
	private int id;
	private String name;

	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Clinic() {

	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<Clinic> getClinicList() {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;

	}
	
	public ArrayList<User> getClinicDoktorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id, u.tcno, u.password, u.name, u.type FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id ="+ clinic_id);
			while(rs.next()) {
				obj = new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.password"),rs.getString("u.name"),rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} 
		return list;
		
	}
	
	public boolean addClinic(String name) {
		
		String query = "INSERT INTO clinic (name) VALUES (?)";
		boolean key = false;
				
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);			
			preparedStatement.executeUpdate();
			key = true;			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return key;
	}
	
	public boolean deleteClinic(int id) {
		
		String query = "Delete From clinic Where id = ?";
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
	
	public boolean updateClinic(int id, String name) {
		
		String query = "Update clinic Set name = ? Where id = ?";
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);			
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();			
			key = true;			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return key;
	}
	
	public Clinic getFetch(int id) {
		Clinic clinic = new Clinic();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("Select * From clinic Where id ="+id);
			while (rs.next()) {
				clinic.setId(rs.getInt("id"));
				clinic.setName(rs.getString("name"));
				break;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return clinic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
