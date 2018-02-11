package com.facebook.model.post;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import com.facebook.controller.UserCollection;
import com.facebook.model.location.Location;
import com.facebook.model.user.User;

/**
 * Class that includes all features of a post that user shared.
 * 
 *
 */
public abstract class Post implements IPost{
	private UUID postID; // Post's unique id.
	private String text;	// Text of post.
	private Date postedDate; // Date of post shared.
	private Location location; // Loction of post shared.
	private LinkedList<User> taggedUsers; // Users tagged in the post.
	
	/**
	 * Constructor of {@link Post} class with necessary arguments.
	 * @param text of post.
	 * @param postedDate Date of post.
	 * @param longitude of post shared
	 * @param latitude of post shared. 
	 * @param taggedUsers who are tagged in the post.
	 */
	public Post(String text, String postedDate, String longitude, String latitude, String taggedUsers) {
		setText(text);
		setPostedDate(postedDate);
		setLocation(longitude, latitude);
		this.taggedUsers = new LinkedList<>();
		setTaggedUsers(taggedUsers);
		setID();
		
	}

	
	
	public void setPostedDate(String postedDate) {
		try {
			this.postedDate = UserCollection.sdf.parse(postedDate);
		} catch (ParseException e) {
			System.out.println("Date format is given wrong.");
		}
	}

	@Override
	public Date getDate() {
		return postedDate;
	}
	
	@Override
	public UUID getID() {
		return postID;
	}
	
	public void setID() {
		postID = UUID.randomUUID();
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String getText() {
		return text;
	}
	/**
	 * Showing tagged friends in form of list. Using in {@link #toString()}.
	 */
	public abstract String showTaggedFriends();
	/**
	 * Showing post location in form of list. Using in {@link #toString()}.
	 */
	public abstract String showPostLocation();

	public Location getLocation() {
		return location;
	}

	public void setLocation(String longitude, String latitude) {
		location = new Location(Double.parseDouble(latitude), Double.parseDouble(longitude));
	}

	public LinkedList<User> getTaggedUsers() {
		return taggedUsers;
	}
	
	/**
	 * Setting tagged users one by one.
	 * @param taggedUsers with be parsed input. It will be parsed with regex.
	 */
	public void setTaggedUsers(String taggedUsers) {
		if(taggedUsers.matches("^[a-zA-Z]+.*")){
			String[] friends = taggedUsers.split(":");
			for(String name : friends){
				User u = UserCollection.getUserByName(name);
				if(!this.taggedUsers.contains(u))
					this.taggedUsers.add(u);
			}
		}
		System.out.println("The post has been successfully added.");
	}	
	
	public void resetTaggedUsers(String taggedUsers){
		this.taggedUsers = new LinkedList<>(); 
		if(taggedUsers.matches("^[a-zA-Z]+.*")){
			String[] friends = taggedUsers.split(":");
			for(String name : friends){
				this.taggedUsers.add(UserCollection.getUserByName(name));
			}
		}
		System.out.println("The post has been successfully added.");
	}
	
	public String toString(){
		String s = "Posted Date: " + UserCollection.sdf.format(postedDate) + "\nLocation: " + location + "\n";
		return s;
	}
}
