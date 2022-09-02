package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doktor;
	private DefaultTableModel doktorModel;
	private Object[] doktorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoktorID =0;
	private String selectDoktorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		
		doktorModel = new DefaultTableModel();
		Object[] colDoktor = new Object[2];
		colDoktor[0] = "ID";
		colDoktor[1] = "Ad Soyad";
		doktorModel.setColumnIdentifiers(colDoktor);
		doktorData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i=0; i< appoint.getHastaList(hasta.getId()).size();i++) {
			appointData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoktorName();
			appointData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
		
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 506);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_ = new JLabel("Hoşgeldiniz, Sayın "+ hasta.getName());
		lbl_.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_.setBounds(10, 11, 447, 38);
		w_pane.add(lbl_);
		
		JButton btn_Exit = new JButton("Çıkış Yap");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_Exit.setBounds(592, 13, 135, 38);
		w_pane.add(btn_Exit);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setFont(new Font("Tahoma", Font.PLAIN, 13));
		w_tab.setBounds(10, 60, 717, 396);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 40, 280, 288);
		w_appointment.add(w_scrollDoktor);
		
		table_doktor = new JTable(doktorModel);
		w_scrollDoktor.setViewportView(table_doktor);
		table_doktor.getColumnModel().getColumn(0).setPreferredWidth(15);
		
		JLabel lblNewLabel = new JLabel("Doktor Listesi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 97, 28);
		w_appointment.add(lblNewLabel);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(300, 40, 182, 33);
		select_clinic.addItem("--Poliklinik Seç");
		for (int i=0; i< clinic.getClinicList().size();i++) {
			select_clinic.addItem(new Item(clinic.getClinicList().get(i).getId(), clinic.getClinicList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i<clinic.getClinicDoktorList(item.getKey()).size(); i++) {
							doktorData[0] = clinic.getClinicDoktorList(item.getKey()).get(i).getId();
							doktorData[1] = clinic.getClinicDoktorList(item.getKey()).get(i).getName();
							doktorModel.addRow(doktorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_clinic);
		
		JButton btn_selDoktor = new JButton("Doktoru Seç");
		btn_selDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doktor.getSelectedRow();
				if (row >= 0) {
					String value = table_doktor.getModel().getValueAt(row, 0).toString();
					int doktor_id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i<whour.getWhourList(doktor_id).size(); i++) {
							 whourData[0] = whour.getWhourList(doktor_id).get(i).getId();
							 whourData[1] = whour.getWhourList(doktor_id).get(i).getWdate();
							 whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					selectDoktorID = doktor_id;
					selectDoktorName = table_doktor.getModel().getValueAt(row, 1).toString();
					
				} else {
					Helper.showMsg("Lütfen Doktor Seçiniz !");
				}
			}
		});
		btn_selDoktor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_selDoktor.setBounds(10, 329, 280, 37);
		w_appointment.add(btn_selDoktor);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(492, 40, 220, 288);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(52);
		
		JLabel lblSaat = new JLabel("Tarih ve Saat");
		lblSaat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSaat.setBounds(492, 11, 97, 28);
		w_appointment.add(lblSaat);
		
		JButton btn_addAppoint = new JButton("Randevu al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >=0 ) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString(); 
					try {
						boolean control = hasta.addAppointment(selectDoktorID, selectDoktorName, hasta.getId(), hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoktorID, date);
							updateWhourModel(selectDoktorID);
							updateAppointModel(hasta.getId());
						} 					
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen randevu saati geçiniz !");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_addAppoint.setBounds(492, 329, 220, 37);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 11, 692, 355);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
	}
	
	public void updateWhourModel(int doktor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<whour.getWhourList(doktor_id).size(); i++) {
			 whourData[0] = whour.getWhourList(doktor_id).get(i).getId();
			 whourData[1] = whour.getWhourList(doktor_id).get(i).getWdate();
			 whourModel.addRow(whourData);
		}
	}
	
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i< appoint.getHastaList(hasta_id).size();i++) {
			appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoktorName();
			appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
