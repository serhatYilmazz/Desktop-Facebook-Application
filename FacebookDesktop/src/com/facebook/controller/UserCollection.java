package com.facebook.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.facebook.model.post.Post;
import com.facebook.model.user.User;


/**
 * Class that can do things that a user can't control. For creating structures, holding 
 * dataset and managing them according to coming commands.
 * @author Serhat
 *
 */
public class UserCollection {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // Used format for all program's date.
	public static LinkedList<User> users = new LinkedList<User>(); // Users that exist on the system.
	public static LinkedList<Post> posts = new LinkedList<Post>(); // Posts that created by users. Each user also has one of that list.
	public static Stack<User> onlineUser = new Stack<User>(); // Evaluation is on last signed in user.
	
	/**
	 * 
	 * @param name that will be added user's.
	 * @param userName of user.
	 * @param password of user
	 * @param dateOfBirth of user
	 * @param school where user has been going to.
	 */
	public static boolean addNewUser(String name, String userName, String password, String dateOfBirth, String school, String relationshipStatus){
		try {
			users.addLast(new User(name, userName, password, sdf.parse(dateOfBirth), school, relationshipStatus));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Birthday Format is should be \"mm/dd/yyyy\".");
			return false;
		}
		return true;
	}
	
	/**
	 * Removing a user from the system with ID.
	 * @param ID whose will be removed user's.
	 */
	public static void removeUser(String ID){
		try{
			User userDeleted = users.remove(Integer.parseInt(ID) - 1); // User ID starts from 1, index starts from 0.
			for(User u : users){
				if(u.getCollectionOfFriends().contains(userDeleted))
					u.getCollectionOfFriends().remove(userDeleted);
				if(u.getCollectionBlockedUsers().contains(userDeleted))
					u.getCollectionBlockedUsers().remove(userDeleted);
			}
			
			
			System.out.println("User has been successfully removed.");
		}catch(Exception e){
			System.out.println("ID should be integer value that define a user.");
		}
	}
	
	/**
	 * Controlling a user's attempting to sign in to the system.
	 * @param userName of the user
	 * @param password of the user.
	 * @return <code>true</code> if attempt is succeded.
	 */
	public static boolean userSignIn(String userName, String password){
		User userProcess = getUserByUserName(userName);
		if(userProcess == null){
			return false;
		}
		//User name can see from everyone but password could not.
		if(userProcess.signIn(password)){
			onlineUser.push(userProcess);
			onlineUser.peek().setLastLogIn(new Date());
			return true;
		}
		return false;
	}
	
	
	
	
	/**
	 * Helper method to reach a user information without code repetition.
	 * @param userName whose information wanted.
	 * @return a {@link User} object if the userName exists. 
	 */
	public static User getUserByUserName(String userName){
		for(User u : users){
			if(u.getUserName().equals(userName)){
				return u;
			}
		}
		System.out.println("No such user!");
		return null;
	}
	
	public static User getUserByName(String name){
		for(User u : users){
			if(u.getName().equals(name)){
				return u;
			}
		}
		System.out.println("No such user!");
		return null;
	}
}
