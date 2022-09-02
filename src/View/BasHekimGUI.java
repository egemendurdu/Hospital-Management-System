package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import Helper.*;
import javax.swing.JComboBox;

public class BasHekimGUI extends JFrame {
	
	static BasHekim bashekim = new BasHekim();
	Clinic clinic = new Clinic();
	
	private JPanel w_pane;
	private JTextField fld_DoktorName;
	private JTextField fld_DoktorTc;
	private JTextField fld_DoktorID;
	private JTable table_doktor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JPasswordField fld_doktorPassword;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(bashekim);
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
	public BasHekimGUI(BasHekim bashekim) throws SQLException {
		
		// Doktor Model 
		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4];
		colDoktorName[0] = "ID";
		colDoktorName[1] = "TC NO";
		colDoktorName[2] = "Şifre";
		colDoktorName[3] = "Ad Soyad";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];
		for (int i =0; i< bashekim.getDoktorList().size();i++) {
			doktorData[0] = bashekim.getDoktorList().get(i).getId();
			doktorData[1] = bashekim.getDoktorList().get(i).getTcno();
			doktorData[2] = bashekim.getDoktorList().get(i).getPassword();
			doktorData[3] = bashekim.getDoktorList().get(i).getName();
			
			doktorModel.addRow(doktorData);
		}
		
		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i=0; i< clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 477);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_ = new JLabel("Hoşgeldiniz, Sayın"+ bashekim.getName());
		lbl_.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_.setBounds(10, 11, 397, 38);
		w_pane.add(lbl_);
		
		JButton btn_Exit = new JButton("Çıkış Yap");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btn_Exit.setBounds(542, 11, 135, 38);
		w_pane.add(btn_Exit);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 73, 671, 354);
		w_pane.add(w_tab);
		
		JPanel w_doktor = new JPanel();
		w_tab.addTab("Doktor Yönetimi", null, w_doktor, null);
		w_doktor.setLayout(null);
		
		JLabel lbl_doktorFullName = new JLabel("Ad Soyad");
		lbl_doktorFullName.setBounds(428, 11, 228, 20);
		w_doktor.add(lbl_doktorFullName);
		
		fld_DoktorName = new JTextField();
		fld_DoktorName.setBounds(428, 30, 228, 20);
		w_doktor.add(fld_DoktorName);
		fld_DoktorName.setColumns(10);
		
		JLabel lbl_doktorTc = new JLabel("T.C No");
		lbl_doktorTc.setBounds(428, 61, 228, 20);
		w_doktor.add(lbl_doktorTc);
		
		fld_DoktorTc = new JTextField();
		fld_DoktorTc.setBounds(428, 81, 228, 20);
		w_doktor.add(fld_DoktorTc);
		fld_DoktorTc.setColumns(10);
		
		fld_doktorPassword = new JPasswordField();
		fld_doktorPassword.setBounds(428, 133, 228, 20);
		w_doktor.add(fld_doktorPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Şifre");
		lblNewLabel_2.setBounds(428, 112, 228, 20);
		w_doktor.add(lblNewLabel_2);
		
		JButton btn_addDoktor = new JButton("Ekle");
		btn_addDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_DoktorName.getText().length() == 0 || fld_doktorPassword.getText().length() == 0 || fld_DoktorTc.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoktor(fld_DoktorTc.getText(), fld_doktorPassword.getText(), fld_DoktorName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_DoktorTc.setText(null);
							fld_doktorPassword.setText(null);
							fld_DoktorName.setText(null);
							updateDoktorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_addDoktor.setBounds(428, 164, 228, 37);
		w_doktor.add(btn_addDoktor);
		
		JLabel lbl_kullanıcıID = new JLabel("Kullanıcı ID");
		lbl_kullanıcıID.setBounds(428, 222, 228, 28);
		w_doktor.add(lbl_kullanıcıID);
		
		fld_DoktorID = new JTextField();
		fld_DoktorID.setBounds(428, 244, 228, 20);
		w_doktor.add(fld_DoktorID);
		fld_DoktorID.setColumns(10);
		
		JButton btn_removeDoktor = new JButton("Sil");
		btn_removeDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_DoktorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz !");
				} else {
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_DoktorID.getText());
						try {
							boolean control = bashekim.deleteDoktor(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_DoktorID.setText(null);
								updateDoktorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		btn_removeDoktor.setBounds(428, 270, 228, 34);
		w_doktor.add(btn_removeDoktor);
		
		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 11, 408, 293);
		w_doktor.add(w_scrollDoktor);
		
		table_doktor = new JTable(doktorModel);
		w_scrollDoktor.setViewportView(table_doktor);
				
		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_DoktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					
				}
				
			}
			
		});
			
		table_doktor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());					
					String selectTcno = table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString();
					String selectPassword = table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString();
					String selectName = table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString();
					
					bashekim.updateDoktor(selectID, selectTcno, selectPassword, selectName);
					
				
					
				}
				
			}
		});
		
		JPanel w_clinic = new JPanel();
		w_tab.addTab("Poliklinikler", null, w_clinic, null);			
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 225, 304);
		w_clinic.add(w_scrollClinic);
		
		
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectedClinic = clinic.getFetch(selectedID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectedClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			
		});
		
		deleteMenu.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selectedID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selectedID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
				
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {			
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);	
			}
		});
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lbl_doktorFullName_1 = new JLabel("Poliklinik Adı");
		lbl_doktorFullName_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_doktorFullName_1.setBounds(245, 9, 182, 20);
		w_clinic.add(lbl_doktorFullName_1);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(245, 29, 182, 20);
		w_clinic.add(fld_clinicName);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		btn_addClinic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_addClinic.setBounds(245, 53, 182, 37);
		w_clinic.add(btn_addClinic);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(437, 11, 219, 304);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doktor = new JComboBox();
		select_doktor.setBounds(245, 224, 182, 37);
		for (int i = 0; i< bashekim.getDoktorList().size(); i++) {
			select_doktor.addItem(new Item(bashekim.getDoktorList().get(i).getId(), bashekim.getDoktorList().get(i).getName()));
		}
		select_doktor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey()+" : "+ item.getValue());
		});
		w_clinic.add(select_doktor);
		
		JButton btn_addWorker = new JButton("Doktor Ata");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_clinic.getSelectedRow();
				if (selectedRow >= 0) {
					String selectedClinic = table_clinic.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					Item doktorItem = (Item) select_doktor.getSelectedItem();
					boolean control = bashekim.addWorker(doktorItem.getKey(), selectedClinicID);
					if (control) {
						Helper.showMsg("success");						
					} else {
						Helper.showMsg("error");
					}
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btn_addWorker.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_addWorker.setBounds(245, 272, 182, 37);
		w_clinic.add(btn_addWorker);
		
		JLabel lbl_doktorFullName_1_1 = new JLabel("Poliklinik Adı");
		lbl_doktorFullName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_doktorFullName_1_1.setBounds(245, 116, 182, 20);
		w_clinic.add(lbl_doktorFullName_1_1);
		
		JButton btn_addClinic_1 = new JButton("Seç");
		btn_addClinic_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >=0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i=0; i < bashekim.getClinicDoktorList(selClinicID).size();i++) {
							workerData[0] = bashekim.getClinicDoktorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoktorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btn_addClinic_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_addClinic_1.setBounds(245, 147, 182, 37);
		w_clinic.add(btn_addClinic_1);
				
	}
	
	public void updateDoktorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();	
		clearModel.setRowCount(0);
		for (int i =0; i< bashekim.getDoktorList().size();i++) {
			doktorData[0] = bashekim.getDoktorList().get(i).getId();
			doktorData[1] = bashekim.getDoktorList().get(i).getTcno();
			doktorData[2] = bashekim.getDoktorList().get(i).getPassword();
			doktorData[3] = bashekim.getDoktorList().get(i).getName();
			
			doktorModel.addRow(doktorData);
		}
	}
	
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i=0; i< clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
