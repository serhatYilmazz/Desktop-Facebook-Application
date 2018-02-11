package com.facebook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class CreateUser extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JFrame frame;
	private JTextField txtUserName;
	private JTextField txtNameSurname;
	private JTextField txtSchoolGraduated;
	private JTextField txtBirthDate;
	private JPasswordField txtPass;
	private JPasswordField txtPassRe;

	public CreateUser() {
		this.setTitle("Facebook Create User");
		this.setBounds(250, 70, 500, 579);
		frame = this;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.addWindowListener(new closingCreateUser());

		JLabel lblFacebookLogo = new JLabel(Main.facebookIcon);
		lblFacebookLogo.setBounds(102, 49, 300, 64);
		contentPane.add(lblFacebookLogo);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(55, 250, 115, 23);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(55, 280, 115, 23);
		contentPane.add(lblPassword);

		JLabel lblPasswordRe = new JLabel("Password (re-type)");
		lblPasswordRe.setBounds(55, 310, 115, 23);
		contentPane.add(lblPasswordRe);

		JLabel lblNameSurname = new JLabel("Name Surname");
		lblNameSurname.setBounds(55, 340, 115, 23);
		contentPane.add(lblNameSurname);

		JLabel lblSchool = new JLabel("School Graduated");
		lblSchool.setBounds(55, 370, 115, 23);
		contentPane.add(lblSchool);

		JLabel lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(55, 400, 115, 23);
		contentPane.add(lblBirthDate);

		JLabel lblRelationship = new JLabel("Relationship Status");
		lblRelationship.setBounds(55, 430, 115, 23);
		contentPane.add(lblRelationship);

		txtUserName = new JTextField();
		txtUserName.setBounds(191, 250, 136, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		txtNameSurname = new JTextField();
		txtNameSurname.setColumns(10);
		txtNameSurname.setBounds(191, 340, 211, 20);
		contentPane.add(txtNameSurname);

		txtSchoolGraduated = new JTextField();
		txtSchoolGraduated.setColumns(10);
		txtSchoolGraduated.setBounds(191, 370, 211, 20);
		contentPane.add(txtSchoolGraduated);

		txtBirthDate = new JTextField();
		txtBirthDate.setColumns(10);
		txtBirthDate.setBounds(191, 400, 136, 20);
		contentPane.add(txtBirthDate);

		txtPass = new JPasswordField();
		txtPass.setBounds(191, 280, 136, 20);
		contentPane.add(txtPass);

		txtPassRe = new JPasswordField();
		txtPassRe.setBounds(191, 310, 136, 20);
		contentPane.add(txtPassRe);

		JLabel label = new JLabel("Create User");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(191, 152, 211, 55);
		contentPane.add(label);

		JComboBox<String> comboBoxRelationship = new JComboBox<String>();
		comboBoxRelationship.setBounds(191, 430, 136, 20);
		contentPane.add(comboBoxRelationship);
		StringBuilder sb = new StringBuilder();
		sb.append("In a relationship" + "\t" + "Divorced" + "\t" + "Complicated" + "\t" + "Single");
		DefaultComboBoxModel<String> relationShipModel = new DefaultComboBoxModel<>();
		String[] relationshipArray = sb.toString().split("\t");
		for (String r : relationshipArray) {
			relationShipModel.addElement(r);
		}
		comboBoxRelationship.setModel(relationShipModel);

		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnCreate.setBounds(191, 482, 142, 23);
		contentPane.add(btnCreate);

		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String userName = txtUserName.getText();
					@SuppressWarnings("deprecation")
					String password = txtPass.getText();
					@SuppressWarnings("deprecation")
					String passwordRe = txtPassRe.getText();
					String nameSurname = txtNameSurname.getText();
					String birthDate = txtBirthDate.getText();
					String schoolGraduated = txtSchoolGraduated.getText();
					String relationshipStatus = (String) comboBoxRelationship.getSelectedItem();
					if (userName.equals("") || password.equals("") || passwordRe.equals("") || nameSurname.equals("")
							|| birthDate.equals("") || schoolGraduated.equals("")) {
						throw new Exception();
					}
					if (!password.equals(passwordRe)) {
						JOptionPane.showMessageDialog(new JFrame(), "Passwords entered mismatch !!");
						txtPass.setText("");
						txtPassRe.setText("");
					} else {
						StringBuilder sb = new StringBuilder();
						sb.append("ADDUSER" + "\t" + nameSurname + "\t" + userName + "\t" + password + "\t" + birthDate
								+ "\t" + schoolGraduated + "\t" + relationshipStatus);
						if (Main.commandParser(sb.toString())) {
							Main.frame.setVisible(true);
							frame.dispose();
						}
					}
				} catch (Exception d) {
					System.out.println(d);
					JOptionPane.showMessageDialog(new JFrame(), "Please enter all fields");
				}

			}
		});
		this.setResizable(false);
		this.setVisible(true);
	}

	private class closingCreateUser implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure to exit from creating a user?", "Exiting",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				Main.frame.setVisible(true);
				frame.dispose();
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
