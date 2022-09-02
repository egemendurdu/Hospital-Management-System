package View;

import java.awt.BorderLayout;
import Helper.*;
import Model.BasHekim;
import Model.Doktor;
import Model.Hasta;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField txt_hastaTc;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doktorPassword;
	private JPasswordField txt_hastaŞifre;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 503);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lbl_logo.setBounds(103, 53, 338, 39);
		lbl_logo.setFont(new Font("Tahoma", Font.BOLD, 16));
		w_pane.add(lbl_logo);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(39, 132, 457, 280);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblHastaTc = new JLabel("T.C Numaranız :");
		lblHastaTc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHastaTc.setBounds(10, 11, 131, 43);
		w_hastaLogin.add(lblHastaTc);

		JLabel lblHastaŞifre = new JLabel("Şifre :");
		lblHastaŞifre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHastaŞifre.setBounds(10, 83, 131, 43);
		w_hastaLogin.add(lblHastaŞifre);

		txt_hastaTc = new JTextField();
		txt_hastaTc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_hastaTc.setBounds(151, 11, 274, 43);
		w_hastaLogin.add(txt_hastaTc);
		txt_hastaTc.setColumns(10);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_register.setBounds(10, 175, 178, 51);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_hastaTc.getText().length() == 0 || txt_hastaŞifre.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = false;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("Select * From user");
						while (rs.next()) {
							if (txt_hastaTc.getText().equals(rs.getString("tcno"))
									&& txt_hastaŞifre.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta(rs.getInt("id"), rs.getString("tcno"),
											rs.getString("password"), rs.getString("name"), rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = true;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (!key) {
						Helper.showMsg("Hatalı T.C veya şifre");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_hastaLogin.setBounds(247, 175, 178, 51);
		w_hastaLogin.add(btn_hastaLogin);

		txt_hastaŞifre = new JPasswordField();
		txt_hastaŞifre.setBounds(154, 83, 271, 43);
		w_hastaLogin.add(txt_hastaŞifre);

		JPanel w_doktorLogin = new JPanel();
		w_tabpane.addTab("Doktor Girişi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lbl_doktorTc = new JLabel("T.C Numaranız :");
		lbl_doktorTc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_doktorTc.setBounds(10, 11, 131, 43);
		w_doktorLogin.add(lbl_doktorTc);

		fld_doktorTc = new JTextField();
		fld_doktorTc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(151, 11, 274, 43);
		w_doktorLogin.add(fld_doktorTc);

		JLabel lbl_DoktorPassword = new JLabel("Şifre :");
		lbl_DoktorPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_DoktorPassword.setBounds(10, 83, 131, 43);
		w_doktorLogin.add(lbl_DoktorPassword);

		JButton btn_doktorLogin = new JButton("Giriş Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorTc.getText().length() == 0 || fld_doktorPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("Select * From user");
						while (rs.next()) {
							if (fld_doktorTc.getText().equals(rs.getString("tcno"))
									&& fld_doktorPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									BasHekim bhekim = new BasHekim(rs.getInt("id"), rs.getString("tcno"),
											rs.getString("password"), rs.getString("name"), rs.getString("type"));
									BasHekimGUI bGUI = new BasHekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("doktor")) {
									Doktor doktor = new Doktor(rs.getInt("id"), rs.getString("tcno"),
											rs.getString("password"), rs.getString("name"), rs.getString("type"));
									DoktorGUI dGUI = new DoktorGUI(doktor);
									dGUI.setVisible(true);
									dispose();
								}
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_doktorLogin.setBounds(10, 175, 415, 52);
		w_doktorLogin.add(btn_doktorLogin);

		fld_doktorPassword = new JPasswordField();
		fld_doktorPassword.setBounds(151, 83, 274, 43);
		w_doktorLogin.add(fld_doktorPassword);
	}
}
