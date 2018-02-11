package com.facebook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.facebook.controller.UserCollection;
import com.facebook.model.user.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

/**
 * <p>
 * Application that first phase of the project that contains 2 of them.
 * </p>
 * Dependencies of the application:
 * <ul>
 * <li>users.txt</li>
 * <li>commands.txt</li>
 * </ul>
 * 
 * <p>
 * <b> commands.txt </b> can have functionality:
 * </p>
 * <ul>
 * <li>ADDUSER</li>
 * <li>REMOVEUSER</li>
 * <li>SHOWPOSTS</li>
 * <li>SIGNIN</li>
 * <li>SIGNOUT</li>
 * <li>UPDATEPROFILE</li>
 * <li>CHPASS</li>
 * <li>ADDFRIEND</li>
 * <li>REMOVEFRIEND</li>
 * <li>ADDPOST-TEXT</li>
 * <li>ADDPOST-IMAGE</li>
 * <li>ADDPOST-VIDEO</li>
 * <li>REMOVELASTPOST</li>
 * <li>BLOCK</li>
 * <li>UNBLOCK</li>
 * <li>LISTFRIENDS</li>
 * <li>LISTUSERS</li>
 * <li>SHOWBLOCKEDFRIENDS</li>
 * <li>SHOWBLOCKEDUSERS</li>
 * </ul>
 * 
 * @author Serhat YILMAZ
 *
 */
public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	public static ImageIcon facebookIcon = new ImageIcon("img/Facebooklogo1.png");
	private JTextField txtFieldUserName;
	private JPasswordField passFieldPassword;
	static JList<String> lstUsers;
	public static JFrame frame;

	public Main() {
		frame = this;
		this.setBounds(250, 70, 667, 449);
		this.setTitle("Facebook Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(355, 69, 84, 25);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(355, 94, 84, 25);
		contentPane.add(lblPassword);

		txtFieldUserName = new JTextField();
		txtFieldUserName.setBounds(434, 71, 179, 20);
		contentPane.add(txtFieldUserName);
		txtFieldUserName.setColumns(10);

		passFieldPassword = new JPasswordField();
		passFieldPassword.setBounds(434, 96, 179, 20);
		contentPane.add(passFieldPassword);

		JLabel faceBookLogo = new JLabel(facebookIcon);
		faceBookLogo.setBounds(26, 55, 300, 64);
		contentPane.add(faceBookLogo);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(26, 309, 587, 52);
		contentPane.add(scroll);
		
		JPanel lstUserPanel = new JPanel();
		scroll.setViewportView(lstUserPanel);
		lstUserPanel.setLayout(new BorderLayout());
		
		lstUsers = new JList<String>();
		lstUserPanel.add(lstUsers);
		lstUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstUsers.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		lstUsers.setVisibleRowCount(2);

		lstUsers.setBounds(26, 309, 587, 52);

		refreshSystemUsers();

		lstUsers.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String s = (String) lstUsers.getSelectedValue();
				txtFieldUserName.setText(s);

			}
		});

		JButton btnLogIn = new JButton("Login");
		btnLogIn.setBounds(434, 127, 179, 23);
		contentPane.add(btnLogIn);

		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = txtFieldUserName.getText();
				@SuppressWarnings("deprecation")
				String password = passFieldPassword.getText();
				if (password.equals("") || userName.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter user name and password",
							"User Name or Password", JOptionPane.ERROR_MESSAGE);
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("SIGNIN" + "\t" + userName + "\t" + password);
					Main.commandParser(sb.toString());
				}
			}
		});

		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setBounds(26, 372, 121, 23);
		contentPane.add(btnRemoveUser);

		btnRemoveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(new JFrame(),
						"Are you sure to remove this user from system?", "Remove User", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					StringBuilder sb = new StringBuilder();
					sb.append("REMOVEUSER" + "\t" + (lstUsers.getSelectedIndex() + 1));
					Main.commandParser(sb.toString());
					refreshSystemUsers();
				}
			}
		});

		JButton btnNewUser = new JButton("New User");
		btnNewUser.setBounds(492, 372, 121, 23);
		contentPane.add(btnNewUser);

		btnNewUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreateUser();
			}
		});

		this.setVisible(true);
		this.setResizable(false);

	}

	private static void refreshSystemUsers() {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (User u : UserCollection.users) {
			model.addElement(u.getUserName());
		}
		lstUsers.setModel(model);
	}

	public static void main(String[] args) {
		File fUser = new File(args[0]);
		File fCommands = new File(args[1]);

		try {
		/*	@SuppressWarnings("resource")
			BufferedReader userDataSet = new BufferedReader(new InputStreamReader(new FileInputStream(fUser), "UTF8"));
			String line = "";
			@SuppressWarnings("resource")
			BufferedReader commands = new BufferedReader(new InputStreamReader(new FileInputStream(fCommands), "UTF8")); */
			Scanner userDataSet = new Scanner(fUser);
			//String line = "";
			Scanner commands = new Scanner(fCommands);
			while (userDataSet.hasNextLine()) {
				userParser(userDataSet.nextLine());
			}

			while (commands.hasNextLine()) {
				Startup(commands.nextLine());
			}
			
			userDataSet.close();
			commands.close();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Main();
				
			}
		});
	}

	/**
	 * Parsing the users.txt as line by line.
	 * 
	 * @param line
	 *            that comes from users.txt.
	 * @throws ParseException
	 *             if date is parsed wrong.
	 */
	public static void userParser(String line) throws ParseException {
		if (line.matches("^[A-Za-z]+.*")) {
			String[] data = line.split("\t");
			// Since it will be a view class, parsing String to Date process
			// will be done in UserCollection (controller) class.
			UserCollection.addNewUser(data[0], data[1], data[2], data[3], data[4], data[5]);
		}
	}

	/**
	 * Commands parser line coming from commands.txt
	 * 
	 * @param line
	 *            that comes from commands.txt.
	 */
	public static boolean commandParser(String line) {
		boolean condition = false; // The condition will be used in HW 4 as a
									// feedback of action.
		String[] aLine = line.split("\t");
		switch (aLine[0]) {
		case "ADDUSER":
			System.out.println("Command: " + line);
			condition = UserCollection.addNewUser(aLine[1], aLine[2], aLine[3], aLine[4], aLine[5], aLine[6]);
			if (condition) {
				JOptionPane.showMessageDialog(new JFrame(), "New User Successfully Created", "Success !!!",
						JOptionPane.INFORMATION_MESSAGE);
				refreshSystemUsers();
			}
			System.out.println("--------------------------------------");
			break;
		case "REMOVEUSER":
			System.out.println("Command: " + line);
			UserCollection.removeUser(aLine[1]);
			System.out.println("--------------------------------------");
			break;
		case "SIGNIN":
			System.out.println("Command: " + line);
			condition = UserCollection.userSignIn(aLine[1], aLine[2]);
			if (condition) {
				JOptionPane.showMessageDialog(new JFrame(), "Login attempt successful !", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				User u = UserCollection.getUserByUserName(aLine[1]);
				ProfilePage.currentUser = new ProfilePage(u, 0); // Mode 0, user's own mod.
				
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Wrong user name or password", "Failure",
						JOptionPane.ERROR_MESSAGE);
			}
			System.out.println("--------------------------------------");
			break;
		case "SIGNOUT":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error : There is no user to log out.");
				return false;
			}
			UserCollection.onlineUser.pop();
			System.out.println("You have successfully signed out.");
			System.out.println("--------------------------------------");
			break;
		case "UPDATEPROFILE":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().updateProfile(aLine[1], aLine[2], aLine[3], aLine[4]);
			System.out.println("--------------------------------------");
			break;
		case "CHPASS":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			condition = UserCollection.onlineUser.peek().changePassword(aLine[1], aLine[2]);
			System.out.println("--------------------------------------");
			break;
		case "ADDFRIEND":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			System.out.println("--------------------------------------");
			break;
		case "REMOVEFRIEND":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			condition = UserCollection.onlineUser.peek().removeFriend(aLine[1]);
			if (!condition) {
				System.out.println("No such friend!");
			}
			System.out.println("--------------------------------------");
			break;
		case "ADDPOST-TEXT":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().addTextPost(aLine[1], aLine[2], aLine[3], aLine[4]);
			System.out.println("--------------------------------------");
			break;
		case "ADDPOST-IMAGE":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().addImagePost(aLine[1], aLine[2], aLine[3], aLine[4], aLine[5], aLine[6]);
			System.out.println("--------------------------------------");
			break;
		case "ADDPOST-VIDEO":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().addVideoPost(aLine[1], aLine[2], aLine[3], aLine[4], aLine[5], aLine[6]);
			System.out.println("--------------------------------------");
			break;
		case "REMOVELASTPOST":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().removeLastPost();
			System.out.println("--------------------------------------");
			break;
		case "BLOCK":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().blockUser(aLine[1]);
			System.out.println("--------------------------------------");
			break;
		case "UNBLOCK":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().unBlockUser(aLine[1]);
			System.out.println("--------------------------------------");
			break;
		case "LISTFRIENDS":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().ListFriends();
			System.out.println("--------------------------------------");
			break;
		case "LISTUSERS":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().listAllUsers();
			System.out.println("--------------------------------------");
			break;
		case "SHOWBLOCKEDFRIENDS":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().showBlockedFriends();
			System.out.println("--------------------------------------");
			break;
		case "SHOWBLOCKEDUSERS":
			System.out.println("Command: " + line);
			if (UserCollection.onlineUser.isEmpty()) {
				System.out.println("Error: Please sign in and try again.");
				return false;
			}
			UserCollection.onlineUser.peek().showBlockedUsers();
			System.out.println("--------------------------------------");
			break;
		default:
			System.out.println("Invalid Command !!!");
			break;
		}
		return condition;
	}
	
	private static void Startup(String line){
		String[] aLine = line.split("\t");
		//There is a problem in line 30 in commands.txt
		for(int i = 0; i < aLine.length; i++)
			aLine[i] = aLine[i].trim();
		
		switch (aLine[0]) {
		case "ADDUSER":
			System.out.println("Command: " + line);
			UserCollection.addNewUser(aLine[1], aLine[2], aLine[3], aLine[4], aLine[5], aLine[6]);
			System.out.println("--------------------------------------");
			break;
		case "ADDFRIEND":
			System.out.println("Command: " + line);
			// If it is a undirected path, also user u should add the user to
			// friend list. (Two way)
			UserCollection.getUserByUserName(aLine[1]).addFriend(aLine[2]); 
			UserCollection.getUserByUserName(aLine[2]).addFriend(aLine[1]); 
			System.out.println("--------------------------------------");
			break;
		case "ADDPOST-TEXT":
			System.out.println("Command: " + line);
			UserCollection.getUserByUserName(aLine[1]).addTextPost(aLine[2], aLine[3], aLine[4], aLine[5]);
			System.out.println("--------------------------------------");
			break;
		case "ADDPOST-IMAGE":
			System.out.println("Command: " + line);
			UserCollection.getUserByUserName(aLine[1]).addImagePost(aLine[2], aLine[3], aLine[4], aLine[5], aLine[6], aLine[7]);
			System.out.println("--------------------------------------");
			break;
		case "ADDPOST-VIDEO":
			System.out.println("Command: " + line);
			UserCollection.getUserByUserName(aLine[1]).addVideoPost(aLine[2], aLine[3], aLine[4], aLine[5], aLine[6], aLine[7]);
			System.out.println("--------------------------------------");
			break;
		case "BLOCKFRIEND":
			System.out.println("Command: " + line);
			UserCollection.getUserByUserName(aLine[1]).blockUser(aLine[2]);
			UserCollection.getUserByUserName(aLine[2]).blockUser(aLine[1]);
			System.out.println("--------------------------------------");
			break;
		default:
			System.out.println("Invalid Command !!!");
			break;
		}
	}

}