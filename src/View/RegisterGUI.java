package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_password;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 316);
		w_pane = new JPanel();
		w_pane.setForeground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_doktorFullName = new JLabel("Ad Soyad");
		lbl_doktorFullName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_doktorFullName.setBounds(10, 11, 228, 20);
		w_pane.add(lbl_doktorFullName);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 31, 228, 20);
		w_pane.add(fld_name);
		
		JLabel lbl_doktorFullName_1 = new JLabel("T.C. Numarası");
		lbl_doktorFullName_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_doktorFullName_1.setBounds(10, 64, 228, 20);
		w_pane.add(lbl_doktorFullName_1);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 83, 228, 20);
		w_pane.add(fld_tcno);
		
		JLabel lbl_doktorFullName_2 = new JLabel("Şifre");
		lbl_doktorFullName_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_doktorFullName_2.setBounds(10, 114, 228, 20);
		w_pane.add(lbl_doktorFullName_2);
		
		fld_password = new JPasswordField();
		fld_password.setBounds(10, 135, 228, 20);
		w_pane.add(fld_password);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tcno.getText().length() == 0 || fld_password.getText().length() == 0 || fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_password.getText(), fld_name.getText());
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setBounds(10, 185, 228, 37);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setBounds(10, 233, 228, 37);
		w_pane.add(btn_backto);
	}
}
