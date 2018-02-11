package com.facebook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * When logged in user wants to add post, s/he can create three types of post 
 * @authors Serhat
 *
 */
public class PostView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int TEXT_POST_MODE = 0;
	private static final int IMAGE_POST_MODE = 1;
	private static final int VIDEO_POST_MODE = 2;

	private JTextField txtText;
	private JTextField txtLatitude;
	private JTextField txtLongtitude;
	private JComboBox<String> comboBoxPostType;
	private JTextField txtFileName;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtDuration;
	private JFrame frame;

	/**
	 * Initial mode is text post mode.
	 */
	public PostView() {
		this(0);
	}

	public PostView(int mode) {
		frame = this;
		frame.setTitle("Add Post");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(20, 30, 647, 350);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

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
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				new ProfilePage(ProfilePage.currentUser.user, ProfilePage.USER_OWN_MODE);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lblPostType = new JLabel("Post Type");
		lblPostType.setBounds(34, 50, 77, 24);
		panel.add(lblPostType);

		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(34, 85, 77, 24);
		panel.add(lblText);

		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(34, 120, 77, 24);
		panel.add(lblLatitude);

		JLabel lblLongtitude = new JLabel("Longtitude:");
		lblLongtitude.setBounds(265, 120, 77, 24);
		panel.add(lblLongtitude);

		JButton btnAddPost = new JButton("Add Post");
		btnAddPost.setBounds(507, 50, 94, 26);
		panel.add(btnAddPost);

		btnAddPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (mode == TEXT_POST_MODE) {
						ProfilePage.currentUser.user.addTextPost(txtText.getText(), txtLongtitude.getText(),
								txtLatitude.getText(), "");
					} else if (mode == IMAGE_POST_MODE) {
						ProfilePage.currentUser.user.addImagePost(txtText.getText(), txtLongtitude.getText(),
								txtLatitude.getText(), "", txtFileName.getText(),
								txtWidth.getText() + "<x>" + txtHeight.getText());
					} else if (mode == VIDEO_POST_MODE) {
						ProfilePage.currentUser.user.addVideoPost(txtText.getText(), txtLongtitude.getText(),
								txtLatitude.getText(), "", txtFileName.getText(), txtDuration.getText());
					}
					new ProfilePage(ProfilePage.currentUser.user, ProfilePage.USER_OWN_MODE);
					frame.dispose();
				} catch (Exception ef) {
					JOptionPane.showMessageDialog(new JFrame(), "Please fill all fields with true format", "Failure", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		comboBoxPostType = new JComboBox<String>();
		comboBoxPostType
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Text Post", "Image Post", "Video Post" }));
		comboBoxPostType.setBounds(105, 50, 160, 22);
		panel.add(comboBoxPostType);

		if (mode == TEXT_POST_MODE) {
			comboBoxPostType.setSelectedItem("Text Post");
		} else if (mode == IMAGE_POST_MODE) {
			comboBoxPostType.setSelectedItem("Image Post");
		} else if (mode == VIDEO_POST_MODE) {
			comboBoxPostType.setSelectedItem("Video Post");
		}

		comboBoxPostType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPostType.getSelectedItem().toString().equals("Text Post")) {
					frame.dispose();
					new PostView(TEXT_POST_MODE);
				} else if (comboBoxPostType.getSelectedItem().toString().equals("Image Post")) {
					frame.dispose();
					new PostView(IMAGE_POST_MODE);
				} else if (comboBoxPostType.getSelectedItem().toString().equals("Video Post")) {
					frame.dispose();
					new PostView(VIDEO_POST_MODE);
				}

			}
		});

		txtText = new JTextField();
		txtText.setBounds(105, 85, 495, 22);
		panel.add(txtText);
		txtText.setColumns(10);

		txtLatitude = new JTextField();
		txtLatitude.setBounds(105, 120, 150, 22);
		panel.add(txtLatitude);
		txtLatitude.setColumns(10);

		txtLongtitude = new JTextField();
		txtLongtitude.setColumns(10);
		txtLongtitude.setBounds(340, 120, 150, 22);
		panel.add(txtLongtitude);

		if (mode == IMAGE_POST_MODE) {
			JLabel lblFileName = new JLabel("File Name:");
			lblFileName.setBounds(34, 175, 77, 24);
			panel.add(lblFileName);

			txtFileName = new JTextField();
			txtFileName.setColumns(10);
			txtFileName.setBounds(105, 177, 237, 22);
			panel.add(txtFileName);

			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setBounds(34, 210, 77, 24);
			panel.add(lblWidth);

			JLabel lblHeight = new JLabel("Height:");
			lblHeight.setBounds(206, 210, 77, 24);
			panel.add(lblHeight);

			txtWidth = new JTextField();
			txtWidth.setColumns(10);
			txtWidth.setBounds(105, 210, 82, 22);
			panel.add(txtWidth);

			txtHeight = new JTextField();
			txtHeight.setColumns(10);
			txtHeight.setBounds(260, 211, 82, 22);
			panel.add(txtHeight);

		}

		if (mode == VIDEO_POST_MODE) {
			JLabel lblFileName = new JLabel("File Name:");
			lblFileName.setBounds(34, 175, 77, 24);
			panel.add(lblFileName);

			txtFileName = new JTextField();
			txtFileName.setColumns(10);
			txtFileName.setBounds(105, 177, 237, 22);
			panel.add(txtFileName);

			JLabel lblDuration = new JLabel("Duration:");
			lblDuration.setBounds(34, 210, 77, 24);
			panel.add(lblDuration);

			txtDuration = new JTextField();
			txtDuration.setColumns(10);
			txtDuration.setBounds(105, 210, 237, 22);
			panel.add(txtDuration);
		}
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
