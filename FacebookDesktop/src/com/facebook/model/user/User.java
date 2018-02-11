package com.facebook.model.user;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;

import com.facebook.controller.UserCollection;
import com.facebook.model.post.ImagePost;
import com.facebook.model.post.Post;
import com.facebook.model.post.TextPost;
import com.facebook.model.post.VideoPost;

/**
 * Class that holds all information about a user.
 * 
 *
 */
public class User {
	private final int userID; // User's ID.
	private String name; // Name of user.
	private String userName; // User name of user.
	private String password; // password of user
	private Date dateOfBirth; // Birth day of user.
	private String school; // Current school of user.
	private Date lastLogIn; // Last seen of user
	private String relationship_status;

	private LinkedList<User> collectionOfFriends; // LIFO, Since first friend
													// will be last person
													// according to friendship
													// date.
	private LinkedList<User> collectionOfBlockedUser; // Blocked users who the
														// user ignores.
	private LinkedList<Post> collectionOfPosts; // All post belongs to user.

	private static int ID = 1; // static variable that assign to user each
								// creation.

	/**
	 * Constructor of User's class, needed information to create a user.
	 * Otherwise user can't be created.
	 * 
	 * @param name
	 *            that will be added user's.
	 * @param userName
	 *            of user.
	 * @param password
	 *            of user
	 * @param dateOfBirth
	 *            of user
	 * @param school
	 *            where user has been going to.
	 */
	public User(String name, String userName, String password, Date dateOfBirth, String school,
			String relationshipStatus) {
		userID = ID;
		ID++;
		collectionOfBlockedUser = new LinkedList<>();
		collectionOfFriends = new LinkedList<>();
		collectionOfPosts = new LinkedList<>();
		setName(name);
		setUserName(userName);
		setPassword(password);
		setDateOfBirth(dateOfBirth);
		setSchool(school);
		setRelationship_status(relationshipStatus);
	}

	/**
	 * Comparing user's password with entered password. Authentication is
	 * protected.
	 * 
	 * @param password
	 *            entered for authentication.
	 * @return <code>true</code> signing-in successful.
	 */
	public boolean signIn(String password) {
		if (this.password.equals(password)) {
			System.out.println("You have successfully signed in.");
			return true;
		} else {
			System.out.println("Invalid username or password! Please try again.");
			return false;
		}
	}

	/**
	 * Session will be ended.
	 * 
	 * @return <code>true</code> if sign out is successful.
	 */
	public boolean signOut() {

		/*
		 * *********************************************************************
		 * * There should be some conditions to log out in terms of network
		 * here.
		 * *********************************************************************
		 * **
		 */

		System.out.println("You have successfully signed out.");
		return true;
	}

	/**
	 * Changing existing features of user with new features.
	 * 
	 * @param name
	 *            will be replaced user's name. (not userName).
	 * @param dateOfBirth
	 *            that will be new birthday of user.
	 * @param schoolGraduated
	 *            that will be new school or last school of user.
	 */
	public void updateProfile(String name, String dateOfBirth, String schoolGraduated, String relationshipProcess) {
		this.name = name;
		try {
			this.dateOfBirth = UserCollection.sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			System.out.println("Date format is given wrong.");
		}
		this.school = schoolGraduated;
		this.relationship_status = relationshipProcess;
	}

	/**
	 * Listing all posts that belongs to user.
	 * 
	 * @param userName
	 *            whose posts that will be shown.
	 */
	public void showPosts() {
		if (getCollectionOfPosts().isEmpty()) { // If user doesn't have any
												// posts.
			System.out.println(userName + " does not have any posts yet.");
		} else {
			System.out.println("*********************\n" + userName + "'s Posts\n*********************");
			int i = 1;
			for (Post p : getCollectionOfPosts()) {
				System.out.println(
						"This is " + userName + "'s " + i++ + "th " + p.getClass().getSimpleName() + " post\n" + p);
				p.showTaggedFriends();
				System.out.println("********************************");
			}
		}
	}

	/**
	 * Authentication method to check old password, then setting new password to
	 * user.
	 * 
	 * @param oldPassword
	 *            of user to check.
	 * @param newPassword
	 *            of user.
	 * @return <code>true</code> if password will be changed successfully.
	 */
	public boolean changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(password)) {
			password = newPassword;
			System.out.println("Password has been changed successfully.");
			return true;
		} else {
			System.out.println("Password mismatch! Please, try again.");
			return false;
		}
	}

	/**
	 * Adding new friend by a user. Like twitter, it is one sided. <br>
	 * When a user wants to add a user, automatically he/she adds the user their
	 * {@link #collectionOfFriends}.
	 * 
	 * @param userName
	 *            that the user's to be added.
	 * @return {@link User} to be added.
	 */
	public User addFriend(String userName) {
		User u = UserCollection.getUserByUserName(userName);
		if (u == null) { // If the user will be added not exist.
			return null;
		} else {
			if (!collectionOfFriends.contains(u)) {
				collectionOfFriends.push(u);
				System.out.println(u.getName() + " has been successfully added to your friend list.");
				return u;
			} else {
				System.out.println("This user is already in your friend list!");
				return null;
			}
		}
	}

	/**
	 * Removing a friend from {@link #collectionOfFriends}.
	 * 
	 * @param userName
	 *            that wants to be removed.
	 * @return <code>true</code> if user removed from
	 *         {@link #collectionOfFriends}.
	 */
	public boolean removeFriend(String userName) {
		User u = UserCollection.getUserByUserName(userName);
		if(u != null){
			collectionOfFriends.remove(u);
			removeTagOf(u);
			System.out.println(userName + " has been successfully removed from your friend list.");
			return true;
		}

		return false;
	}


	/**
	 * Adding a text post to user's {@link #collectionOfPosts}.
	 * 
	 * @param text
	 *            that appears on the post.
	 * @param longitude
	 *            of post shared, relevant to {@link Location}.
	 * @param latitude
	 *            of post shared. relevant to {@link Location}.
	 * @param taggedUsers
	 *            whose friends tagged in the post.
	 */
	public void addTextPost(String text, String longitude, String latitude, String taggedUsers) {
		String[] friends = taggedUsers.split(":");
		String listWillBeTagged = "";
		for (String userName : friends) {
			User u = UserCollection.getUserByUserName(userName); // Getting
																	// named
																	// user
																	// informations.
			if (u != null) { // If user exists.
				// Controlling user who wants to tag a friend has this friend in
				// his/her friend list.
				if (isFriendWith(u)) {
					listWillBeTagged += u.name + ":";
				} else {
					System.out.println(u.userName + " is not your friend, and will not be tagged!");
				}
			} else {
				System.out.println("Therefore couldn't be tagged.");
			}
		}
		collectionOfPosts.push(new TextPost(text, UserCollection.sdf.format(new Date()), longitude, latitude, listWillBeTagged));
	}

	/**
	 * Adding a image post to user's {@link #collectionOfPosts}.
	 * 
	 * @param text
	 *            that appears on the post.
	 * @param longitude
	 *            of post shared, relevant to {@link Location}.
	 * @param latitude
	 *            of post shared. relevant to {@link Location}.
	 * @param taggedUsers
	 *            whose friends tagged in the post.
	 * @param fileName
	 *            that name of the file with its extension.
	 * @param resolution
	 *            of image with height and width.
	 */
	public void addImagePost(String text, String longitude, String latitude, String taggedUsers, String fileName,
			String resolution) {
		String[] friends = taggedUsers.split(":");
		String listWillBeTagged = "";
		for (String userName : friends) {
			User u = UserCollection.getUserByUserName(userName); // Getting
																	// named
																	// user
																	// informations.
			if (u != null) { // If user exists.
				// Controlling user who wants to tag a friend has this friend in
				// his/her friend list.
				if (isFriendWith(u)) {
					listWillBeTagged += u.name + ":";
				} else {
					System.out.println(u.userName + " is not your friend, and will not be tagged!");
				}
			} else {
				System.out.println("Therefore couldn't be tagged.");
			}
		}
		collectionOfPosts.push(new ImagePost(text, UserCollection.sdf.format(new Date()), longitude, latitude,
				listWillBeTagged, fileName, resolution));
	}

	/**
	 * 
	 * Adding a video post to user's {@link #collectionOfPosts}.
	 * 
	 * @param text
	 *            that appears on the post.
	 * @param longitude
	 *            of post shared, relevant to {@link Location}.
	 * @param latitude
	 *            of post shared. relevant to {@link Location}.
	 * @param taggedUsers
	 *            whose friends tagged in the post.
	 * @param fileName
	 *            that name of the file.
	 * @param videoDuration
	 *            that duration of the video.
	 * 
	 */
	public void addVideoPost(String text, String longitude, String latitude, String taggedUsers, String fileName,
			String videoDuration) {
		String[] friends = taggedUsers.split(":");
		String listWillBeTagged = "";
		for (String userName : friends) {
			User u = UserCollection.getUserByUserName(userName); // Getting
																	// named
																	// user
																	// informations.
			if (u != null) { // If user exists.
				// Controlling user who wants to tag a friend has this friend in
				// his/her friend list.
				if (isFriendWith(u)) {
					listWillBeTagged += u.name + ":";
				} else {
					System.out.println(u.userName + " is not your friend, and will not be tagged!");
				}
			} else {
				System.out.println("Therefore couldn't be tagged.");
			}
		}
		collectionOfPosts.push(new VideoPost(text, UserCollection.sdf.format(new Date()), longitude, latitude,
				listWillBeTagged, fileName, videoDuration));
	}

	/**
	 * Removing last post from {@link #collectionOfPosts}.
	 */
	public void removeLastPost() {
		if (collectionOfPosts.isEmpty()) {
			System.out.println("Error: You don't have any posts.");
		} else {
			collectionOfPosts.pop();
			System.out.println("Your last post has been successfully removed.");
		}
	}

	/**
	 * Blocking a user that given with {@link #userName}.
	 * 
	 * @param userName
	 *            that will be blocked user and added to
	 *            {@link #showBlockedUsers()}.
	 * @return <code>true</code> if user is blocked.
	 */
	public boolean blockUser(String userName) {
		User u = UserCollection.getUserByUserName(userName);
		if (u != null) {// If user exists.
			if (!collectionOfBlockedUser.contains(u)) {
				collectionOfFriends.remove(u); // If a user blocked, it means it
												// is not wanted anymore. So it
												// should be removed from friend
												// list.
				collectionOfBlockedUser.push(u);
				System.out.println(userName + " has been successfully blocked.");
				return true;
			} else {
				System.out.println("User is already in your blocked list");
			}
		}
		return false;
	}

	/**
	 * Unblocking a user, remove the user from {@link #collectionOfBlockedUser}.
	 * 
	 * @param userName
	 *            that will be unblocked.
	 * @return <code>true</code> if unblocking process is successful.
	 */
	public boolean unBlockUser(String userName) {
		User u = UserCollection.getUserByUserName(userName);
		if (collectionOfBlockedUser.contains(u)) {// If user exists.
			collectionOfBlockedUser.remove(u);
			System.out.println(userName + " has been successfully unblocked.");
			return true;
		}
		System.out.println("No such user in your blocked users list!");
		return false;
	}

	/**
	 * Listing all friends in {@link #collectionOfFriends}.
	 */
	public String[] ListFriends() {
		LinkedList<String> friends = new LinkedList<>();
		if (!collectionOfFriends.isEmpty()) {
			for (User u : collectionOfFriends) {
				System.out.println(u);
				friends.add(u.name);
				System.out.println("----------------------------");
			}
		} else {
			System.out.println("You have not added any friends yet!");
		}
		
		return friends.toArray(new String[friends.size()]);
	}

	/**
	 * Listing all users in the system, including offlines.
	 */
	public void listAllUsers() {
		for (User u : UserCollection.users) {
			System.out.println(u);
			System.out.println("-----------------------------");
		}
	}

	/**
	 * Listing blocked users that also friend of the user in
	 * {@link #showBlockedFriends()}.
	 */
	public void showBlockedFriends() {
		boolean bFriendBlocked = false;
		if (!collectionOfBlockedUser.isEmpty()) {
			for (User u : collectionOfBlockedUser) {
				if (collectionOfFriends.contains(u)) {
					System.out.println(u);
					System.out.println("-----------------------------");
					bFriendBlocked = true; // If never come this place, means
											// that there is no friend in
											// blocked.
				}
			} // If there is no friend block
			if (!bFriendBlocked) {
				System.out.println("You have not blocked any friends yet!");
			}
		} else {
			System.out.println("You have not blocked any users yet!");
		}
	}

	/**
	 * Listing all blocked user's of user in {@link #blockUser(String)}.
	 */
	public void showBlockedUsers() {
		if (!collectionOfBlockedUser.isEmpty()) {
			for (User u : collectionOfBlockedUser) {
				System.out.println(u);
				System.out.println("-----------------------------");
			}
		} else {
			System.out.println("You have not blocked any users yet!");
		}
	}

	/**
	 * Helper function to check if the users are friends or not.
	 * 
	 * @param u
	 *            {@link User} that will be checked friend of the user.
	 * @return <code>true</code> if two users are friend.
	 */
	public boolean isFriendWith(User u) {
		if (collectionOfFriends.contains(u)) {
			return true;
		}
		return false;
	}
	
	public void removeTagOf(User user) {
		for(Post p : collectionOfPosts){
			StringBuilder newTaggingSituation = new StringBuilder();
			if(p.getTaggedUsers().contains(user)){
				for(User s : p.getTaggedUsers()){
					if(s != null && s.equals(user))	continue;
						newTaggingSituation.append(s.getName() + ":");
				}
				if(newTaggingSituation.length() > 0)
					newTaggingSituation.deleteCharAt(newTaggingSituation.length() - 1);
				p.resetTaggedUsers(newTaggingSituation.toString());
			}
			
		}
		
	}

	/*
	 * *************************************************************************
	 * ******** GETTERS- SETTERS*********************************
	 ***********************************************************************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getLastLogIn() {
		return lastLogIn;
	}

	public void setLastLogIn(Date lastLogIn) {
		this.lastLogIn = lastLogIn;
	}

	public LinkedList<User> getCollectionOfFriends() {
		return collectionOfFriends;
	}

	public void setCollectionOfFriends(LinkedList<User> collectionOfFriends) {
		this.collectionOfFriends = collectionOfFriends;
	}

	public LinkedList<User> getCollectionBlockedUsers() {
		return collectionOfBlockedUser;
	}

	public void setCollectionBlockedUsers(LinkedList<User> collectionBlockedUsers) {
		this.collectionOfBlockedUser = collectionBlockedUsers;
	}

	public LinkedList<Post> getCollectionOfPosts() {
		return collectionOfPosts;
	}

	public void setCollectionOfPosts(LinkedList<Post> collectionOfPosts) {
		this.collectionOfPosts = collectionOfPosts;
	}

	public int getUserID() {
		return userID;
	}

	public String getRelationship_status() {
		return relationship_status;
	}

	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}

	/*
	 * *************************************************************************
	 * ********
	 ***********************************************************************************
	 ***********************************************************************************/

	public String toString() {
		String s = String.format("ID: %d, Name: %s, UserName: %s, \nDate Of Birth: %s, School: ", userID, name,
				userName, UserCollection.sdf.format(dateOfBirth));
		s += school;
		if (lastLogIn == null) {
			System.out.println("User " + userName + " not log-in yet.");
		} else {
			s += "\nLast Login: " + lastLogIn + "\n";
		}
		/*
		 * if(collectionOfFriends.isEmpty()){ s += "No one\n"; }
		 * 
		 * for(User u : collectionOfFriends){ s += u.getName() + " - "; } s +=
		 * "\n"; for(User u : collectionOfBlockedUser){ s += u.getName() +
		 * " - "; } s += "\n"; for(Post p : collectionOfPosts){ s += p + "\n"; }
		 */
		return s;
	}

	
}
