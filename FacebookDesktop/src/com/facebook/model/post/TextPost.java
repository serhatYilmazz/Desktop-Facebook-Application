package com.facebook.model.post;

import com.facebook.model.user.User;

/**
 * TextPost class that extends {@link Post} class. Excluding properties, just super class' methods.
 *
 */
public class TextPost extends Post{
	/**
	 * TextPost constructor that contains all of features.
	 * @param text of the TextPost.
	 * @param postedDate of TextPost.
	 * @param longitude of posted place.
	 * @param latitude of posted place.
	 * @param taggedUsers that tagged in the post.
	 */
	public TextPost(String text, String postedDate, String longitude, String latitude, String taggedUsers) {
		super(text, postedDate, longitude, latitude, taggedUsers);
	}

	@Override
	public String showTaggedFriends() {
		System.out.print("Friends tagged in this post: ");
		StringBuilder sb = new StringBuilder();
		for(User u : getTaggedUsers()){
			System.out.print(u.getName() + (getTaggedUsers().getLast().equals(u) ? "." : ", "));
			sb.append(u.getName() + (getTaggedUsers().getLast().equals(u) ? "." : ", "));
		}	
		System.out.println("");
		return sb.toString();
	}
	

	@Override
	public String showPostLocation() {
		StringBuilder sb = new StringBuilder();
		sb.append("Latitude: " + getLocation().getLatitude() + "Longitude: " + getLocation().getLongitude());
		System.out.println("Latitude: " + getLocation().getLatitude() + "Longitude: " + getLocation().getLongitude());
		return sb.toString();
	}
}
