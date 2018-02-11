package com.facebook.view;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.facebook.controller.UserCollection;
import com.facebook.model.post.ImagePost;
import com.facebook.model.post.Post;
import com.facebook.model.post.TextPost;
import com.facebook.model.post.VideoPost;
import com.facebook.model.user.User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.TextAttribute;
import java.util.Stack;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import java.awt.GridLayout;

public class ProfilePage extends JFrame {
	private static final int USER_OWN_MODE = 0;
	private static final int USER_VISITER_MODE = 1;
	private static final int USER_POST = 3;
	private static final int FRIEND_POST = 4;

	JFrame frame;
	JPanel contentPane;
	JPanel information;
	JPanel posts;
	JPanel friends;
	int mode;
	User user;
	private JButton btnBlockUser;
	private JLabel lblDateOfBirthSpec;
	private JLabel lblDateOfBirth;
	private JRadioButton radioBtnNormal;
	private JRadioButton radioBtnBlocked;
	private JTextField txtNameSurname;
	private JLabel lblSchoolSpe;
	private JLabel lblSchoolGraduated;
	private JLabel lblRelationSpe;
	private JComboBox comboBoxRela;
	private JButton btnUpdate;
	private JPanel panel;
	private JLabel lblFriends;
	private JList lstFriends;
	JList<String> lstSearch;
	private JButton btnRemoveSelectedUser;
	private JLabel lblNameSurname;
	private JPanel panelFriendPost;
	private JPanel panelSearch;
	private JLabel lblSearchFriends;
	private JTextField txtSearch;
	private JButton btnLogout;
	private JButton btnCreatePost;

	private static Stack<User> frames = new Stack<>();
	public static ProfilePage currentUser;
	private JPanel panelSearchFriends;
	private JScrollPane scrollPaneOwnPost;
	private JTextField txtDateOfBirth;
	private JTextField txtSchool;

	public ProfilePage(User visitedUser, int mode) {
		this.mode = mode;
		this.user = visitedUser;
		frame = this;
		this.setTitle("ProfilePage");
		this.setBounds(100, 10, 900, 737);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 100, 100));
		setContentPane(contentPane);
		this.addWindowListener(new closingProfile());

		ImageIcon profileIcon = new ImageIcon("img/personicon8.png");
		contentPane.setLayout(null);
		JLabel lblProfileIcon = new JLabel(profileIcon);
		lblProfileIcon.setBounds(10, 57, 150, 152);
		contentPane.add(lblProfileIcon);

		information = new JPanel();
		information.setBounds(10, 228, 202, 230);
		information.setBorder(BorderFactory.createTitledBorder("Information"));
		contentPane.add(information);
		information.setLayout(null);

		lblDateOfBirthSpec = new JLabel("Date of Birth");
		lblDateOfBirthSpec.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateOfBirthSpec.setBounds(22, 30, 122, 15);
		information.add(lblDateOfBirthSpec);
		
		lblSchoolSpe = new JLabel("School Graduated");
		lblSchoolSpe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSchoolSpe.setBounds(22, 80, 122, 15);
		information.add(lblSchoolSpe);
		
		lblRelationSpe = new JLabel("Relationship Status");
		lblRelationSpe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelationSpe.setBounds(22, 130, 148, 15);
		information.add(lblRelationSpe);
		
		comboBoxRela = new JComboBox();
		comboBoxRela.setBounds(22, 155, 122, 20);
		information.add(comboBoxRela);
		
		if(mode == USER_VISITER_MODE){
			lblDateOfBirth = new JLabel(UserCollection.sdf.format(visitedUser.getDateOfBirth()));
			lblDateOfBirth.setBounds(22, 55, 132, 15);
			information.add(lblDateOfBirth);

			

			lblSchoolGraduated = new JLabel(visitedUser.getSchool());
			lblSchoolGraduated.setBounds(22, 105, 170, 15);
			information.add(lblSchoolGraduated);

			lblNameSurname = new JLabel(visitedUser.getName());
			lblNameSurname.setBounds(131, 173, 228, 56);
			lblNameSurname.setHorizontalAlignment(SwingConstants.CENTER);
			lblNameSurname.setFont(new Font("Times New Roman", Font.BOLD, 27));
			contentPane.add(lblNameSurname);

			
			comboBoxRela.setEnabled(false);
		}
		else if(mode == USER_OWN_MODE){
			btnUpdate = new JButton("Update");
			btnUpdate.setBounds(22, 180, 81, 23);
			information.add(btnUpdate);
			
			btnUpdate.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					StringBuilder sb = new StringBuilder();
					
					
				}
			});
		
			txtNameSurname = new JTextField();
			txtNameSurname.setBorder(BorderFactory.createEmptyBorder());
			txtNameSurname.setBounds(151, 153, 228, 56);
			txtNameSurname.setBackground(this.getBackground());
			txtNameSurname.setText(user.getName());
			txtNameSurname.setHorizontalAlignment(SwingConstants.CENTER);
			txtNameSurname.setFont(new Font("Times New Roman", Font.BOLD, 27));
			contentPane.add(txtNameSurname);
			
			txtDateOfBirth = new JTextField();
			txtDateOfBirth.setBorder(BorderFactory.createEmptyBorder());
			txtDateOfBirth.setText(UserCollection.sdf.format(visitedUser.getDateOfBirth()));
			txtDateOfBirth.setBounds(22, 49, 148, 20);
			txtDateOfBirth.setBackground(this.getBackground());
			information.add(txtDateOfBirth);
			txtDateOfBirth.setColumns(10);
			
			txtSchool = new JTextField();
			txtSchool.setBorder(BorderFactory.createEmptyBorder());
			txtSchool.setText(visitedUser.getSchool());
			txtSchool.setColumns(10);
			txtSchool.setBackground(this.getBackground());
			txtSchool.setBounds(22, 99, 148, 20);
			information.add(txtSchool);
		}
		
		
		/* ************************************************************
		 * 	********Information panel, relationship status process	******
		 * ************************************************************
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("In a relationship" + "\t" + "Divorced" + "\t" + "Complicated" + "\t" + "Single");
		DefaultComboBoxModel<String> relationShipModel = new DefaultComboBoxModel<>();
		String[] relationshipArray = sb.toString().split("\t");
		for (String r : relationshipArray) {
			relationShipModel.addElement(r);
		}
		comboBoxRela.setModel(relationShipModel);
		comboBoxRela.setSelectedItem(visitedUser.getRelationship_status());
		
		
		/* ************************************************************
		 * 	************										******
		 * ************************************************************
		 */

		friends = new JPanel();
		friends.setBounds(10, 491, 202, 203);
		friends.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(friends);
		friends.setLayout(null);

		lstFriends = new JList();
		lstFriends.setBounds(10, 11, 182, 151);
		friends.add(lstFriends);
		lstFriends.addMouseListener(new MouseListener() {

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
				if (e.getClickCount() == 2) {
					if (lstFriends.getSelectedValue() != null) {
						User u = UserCollection.getUserByUserName((String) lstFriends.getSelectedValue());
						frames.push(user);
						frame.dispose();

						if (u == ProfilePage.currentUser.user) // If chosen
																// profile is
																// own profile,
																// then it
																// should be
																// opened by
																// owner mode.
							new ProfilePage(u, USER_OWN_MODE);
						else
							new ProfilePage(u, USER_VISITER_MODE);
					}
				}

			}
		});

		btnRemoveSelectedUser = new JButton("Remove Selected User");
		btnRemoveSelectedUser.setBounds(10, 169, 182, 23);
		friends.add(btnRemoveSelectedUser);

		lblFriends = new JLabel("Friends");
		lblFriends.setBounds(10, 469, 46, 15);
		contentPane.add(lblFriends);

		ButtonGroup group = new ButtonGroup();

		radioBtnNormal = new JRadioButton("Normal");
		radioBtnNormal.setBounds(59, 469, 73, 15);
		contentPane.add(radioBtnNormal);
		group.add(radioBtnNormal);
		// When click on normal friends button:
		radioBtnNormal.addMouseListener(new MouseListener() {

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
				DefaultListModel<String> friend = new DefaultListModel<>();
				for (User u : visitedUser.getCollectionOfFriends()) {
					if (!visitedUser.getCollectionBlockedUsers().contains(u))
						friend.addElement(u.getUserName());
				}
				lstFriends.setModel(friend);

			}
		});

		radioBtnBlocked = new JRadioButton("Blocked");
		radioBtnBlocked.setBounds(131, 469, 85, 15);
		contentPane.add(radioBtnBlocked);
		// When click on blocked friends button:
		group.add(radioBtnBlocked);
		radioBtnBlocked.addMouseListener(new MouseListener() {

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
				DefaultListModel<String> blocked = new DefaultListModel<>();
				for (User u : visitedUser.getCollectionBlockedUsers()) {
					blocked.addElement(u.getUserName());
				}
				lstFriends.setModel(blocked);

			}
		});

		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(222, 222, 652, 465);
		contentPane.add(tabbedPane);

		JPanel panelOwnPost = new JPanel();
		tabbedPane.addTab("Posts", null, panelOwnPost, null);
		panelOwnPost.setLayout(null);

		/**
		 * If owner user visits a profile that:
		 * 1-> Friends' profile: Owner user can see the user's posts.
		 * 2-> Blocked Friends' profile: Owner user can't see the user's posts.
		 * 3-> Non-Friend profile: Owner user can't see the user post.
		 */
		if (mode == USER_OWN_MODE){
			createPosts(USER_POST, panelOwnPost);
		} else if (mode == USER_VISITER_MODE && !ProfilePage.currentUser.user.getCollectionBlockedUsers().contains(user)) {
			if(ProfilePage.currentUser.user.getCollectionOfFriends().contains(user)){
				createPosts(USER_POST, panelOwnPost);
			}
		}
		// When a user visits the other profiles, the user can't see their friends' posts.
		if (mode != USER_VISITER_MODE) {
			panelFriendPost = new JPanel();
			tabbedPane.addTab("Friends' Post", null, panelFriendPost, null);
			createFriendPosts(FRIEND_POST, panelFriendPost);
		}

		panelSearch = new JPanel();
		panelSearch.setBackground(new Color(0, 51, 204));
		panelSearch.setBounds(10, 11, 864, 35);
		contentPane.add(panelSearch);
		panelSearch.setLayout(null);

		lblSearchFriends = new JLabel("Search Friends");
		lblSearchFriends.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearchFriends.setForeground(Color.WHITE);
		lblSearchFriends.setBounds(157, 11, 98, 14);
		panelSearch.add(lblSearchFriends);

		txtSearch = new JTextField();
		txtSearch.setBounds(251, 8, 166, 20);
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);
		StringBuilder builderOfSearchTextField = new StringBuilder();
		txtSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				

			}

			@Override
			public void keyReleased(KeyEvent e) {
				

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				EventQueue.invokeLater(new Runnable() {
					/**
					 * When a key is entered, then a thread control it, if a result found, thread lists them.
					 */
					@Override
					public void run() {
						String searched = txtSearch.getText();
						DefaultListModel<String> list = new DefaultListModel<>();
						for (User u : UserCollection.users) {
							if (u.getName().toLowerCase().startsWith(searched.toLowerCase())) {
								list.addElement(u.getName());
							}
						}
						if (txtSearch.getText().equals("")) {
							panelSearchFriends.setVisible(false);
						} else {
							lstSearch.setModel(list);
							panelSearchFriends.setVisible(true);
						}

					}
				});
			}
		});
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(765, 7, 89, 23);
		panelSearch.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure to logout from the your session?",
						"Exiting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					UserCollection.onlineUser.pop(); //Tell the system no online user remains.
					visitedUser.signOut();
					ProfilePage.currentUser = null; //No current user exists.
					frames.clear(); // Stack of frames is emptied.
					JOptionPane.showMessageDialog(new JFrame(), "You are signed out ..");
					new Main(); // First window.
					frame.dispose(); // Window is closed.
				}
			}
		});

		btnCreatePost = new JButton("Create a Post");
		btnCreatePost.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreatePost.setBounds(598, 7, 157, 23);
		panelSearch.add(btnCreatePost);

		panelSearchFriends = new JPanel();
		panelSearchFriends.setBounds(190, 34, 268, 128);
		contentPane.add(panelSearchFriends);
		panelSearchFriends.setVisible(false);

		JScrollPane pane = new JScrollPane();
		panelSearchFriends.add(pane);
		lstSearch = new JList<String>();
		lstSearch.setVisibleRowCount(2);
		pane.setViewportView(lstSearch);
		panelSearchFriends.add(pane);
		lstSearch.setLayoutOrientation(JList.VERTICAL);
		lstSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSearch.addListSelectionListener(new ListSelectionListener() {

			@Override //When a user is searhed and when clicked, the profile should open.
			public void valueChanged(ListSelectionEvent e) {
				User u = UserCollection.getUserByName(lstSearch.getSelectedValue());
				frames.push(user);
				frame.dispose();
				if (u == ProfilePage.currentUser.user) { // If user opens own profile, then the profile should be seen as the user profile
					new ProfilePage(u, USER_OWN_MODE);
				} else {
					new ProfilePage(u, USER_VISITER_MODE);
				}
			}
		});

		if (mode == USER_VISITER_MODE) {
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					
					btnBlockUser = new JButton();
					//If the visited user none of a normal or blocked friends, then the button should be
					// seen as 'AddFriend'.
					if (!ProfilePage.currentUser.user.getCollectionOfFriends().contains(user)
							&& !ProfilePage.currentUser.user.getCollectionBlockedUsers().contains(user)) {
						btnBlockUser.setText("Add Friend");
						btnBlockUser.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								ProfilePage.currentUser.user.addFriend(user.getUserName());
								user.addFriend(ProfilePage.currentUser.user.getUserName());
								JOptionPane.showMessageDialog(new JFrame(),
										"You added " + user.getName() + " as a friend", "Success!",
										JOptionPane.INFORMATION_MESSAGE);
								new ProfilePage(user, USER_VISITER_MODE);
								frame.dispose();
							}
						});
						//If a user is blocked, then the block can be removable.
					} else if (ProfilePage.currentUser.user.getCollectionBlockedUsers().contains(user)) {
						btnBlockUser.setText("Unblock this person");
						btnBlockUser.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								ProfilePage.currentUser.user.unBlockUser(user.getUserName());
								user.unBlockUser(ProfilePage.currentUser.user.getUserName());
								JOptionPane.showMessageDialog(new JFrame(), "You unblocked " + user.getName(),
										"Success!", JOptionPane.INFORMATION_MESSAGE);
								new ProfilePage(user, USER_VISITER_MODE);
								frame.dispose();
							}
						});
						//If the visited user is friend of owner user, then the owner user can block the visited.
					} else {
						btnBlockUser.setText("Block this person");
						btnBlockUser.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								ProfilePage.currentUser.user.blockUser(user.getUserName());
								user.blockUser(ProfilePage.currentUser.user.getUserName());
								JOptionPane.showMessageDialog(new JFrame(), "You blocked " + user.getName(), "Success!",
										JOptionPane.INFORMATION_MESSAGE);
								new ProfilePage(user, USER_VISITER_MODE);
								frame.dispose();
							}
						});
					}

					btnBlockUser.setBounds(724, 205, 150, 23);
					contentPane.add(btnBlockUser);
				}
			});
			JButton btnHome = new JButton("Home");
			btnHome.setBounds(10, 7, 89, 23);
			panelSearch.add(btnHome);

			btnHome.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frames.clear();
					new ProfilePage(ProfilePage.currentUser.user, 0);
					frame.dispose();
				}
			});

			
		}
		this.setVisible(true);
	}

	private void createFriendPosts(int mode, JPanel panelPost) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 640, 500);
		panelPost.add(scrollPane);

		JPanel outer = new JPanel();
		scrollPane.setViewportView(outer);
		outer.setLayout(new BorderLayout());

		JPanel column = new JPanel();
		outer.add(column);
		column.setBounds(0, 0, 652, 465);
		column.setLayout(new GridLayout(0, 1, 0, 1));
		column.setBackground(panelPost.getBackground());

		for (User u : user.getCollectionOfFriends()) {
			if (!user.getCollectionBlockedUsers().contains(u)) {
				for (Post p : u.getCollectionOfPosts()) {
					JPanel row = new JPanel();
					row = createPost(FRIEND_POST, u, p);
					row.setPreferredSize(new Dimension(600, 65));
					column.add(row);
					row.setLayout(null);
				}
			}
		}
	}

	private void createPosts(int mode, JPanel panelPost) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 640, 500);
		panelPost.add(scrollPane);

		JPanel outer = new JPanel();
		scrollPane.setViewportView(outer);
		outer.setLayout(new BorderLayout());

		JPanel column = new JPanel();
		outer.add(column);
		column.setBounds(0, 0, 652, 465);
		column.setLayout(new GridLayout(0, 1, 0, 1));
		column.setBackground(panelPost.getBackground());

		for (Post p : user.getCollectionOfPosts()) {
			JPanel row = new JPanel();
			row = createPost(mode, user, p);
			row.setPreferredSize(new Dimension(600, 65));
			column.add(row);
			row.setLayout(null);
		}

	}

	public static JPanel createPost(int mode, User user, Post post) {
		/*
		 * While a post is creating, it should be determined which of the procedure can be seen and shared by whom.
		 */
		JPanel postPanel = new JPanel();
		postPanel.setBounds(0, 547, 597, 65);
		postPanel.setLayout(null);
		JLabel lblType = new JLabel();
		if (post instanceof ImagePost) {
			lblType.setText("I");
		} else if (post instanceof TextPost) {
			lblType.setText("T");
		} else {
			lblType.setText("V");
		}
		lblType.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setBounds(0, 11, 22, 44);
		postPanel.add(lblType);

		JTextArea postText = new JTextArea(post.getText());
		postText.setForeground(Color.BLUE);
		postText.setLineWrap(true);
		postText.setBackground(postPanel.getBackground());
		postText.setWrapStyleWord(true);
		postPanel.add(postText);
		//Just owner can tag friends to posts.
		if (mode == USER_POST) {
			postText.setBounds(27, 0, 426, 50);

			JButton btnTagFriends = new JButton("Tag Friends");
			btnTagFriends.setBounds(460, 11, 125, 44);
			postPanel.add(btnTagFriends);
		// When a user visits another person's profile, the user can't see visited profile's friends' posts.
		} else if (mode == FRIEND_POST) {
			postText.setBounds(27, 22, 426, 29);

			JLabel lblFriendName = new JLabel(user.getName() + " has shared:");
			lblFriendName.setBounds(0, 0, 416, 14);
			postPanel.add(lblFriendName);
		}

		StringBuilder sb = new StringBuilder();
		for (String s : post.getTaggedUsers()) {
			sb.append(s + (post.getTaggedUsers().getLast().equals(s) ? "." : ", "));
		}

		JLabel labelTaggedUsers = new JLabel("Tagged Users: " + sb.toString());
		labelTaggedUsers.setBounds(0, 51, 587, 14);
		postPanel.add(labelTaggedUsers);

		return postPanel;

	}

	private class closingProfile implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {

			if (mode == 0) {
				if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure to logout from the your session?",
						"Exiting", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					UserCollection.onlineUser.pop();
					JOptionPane.showMessageDialog(new JFrame(), "You are signed out ..");
					new Main();
					frames.clear();
					frame.dispose();
				}
			} else {
				User u = frames.pop();
				if (u == ProfilePage.currentUser.user) {
					new ProfilePage(u, 0);
				} else {
					new ProfilePage(u, 1);
				}
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
