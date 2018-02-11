package com.facebook.model.post;

import com.facebook.model.user.User;

/**
 * Image class that extends {@link Post} class. Excluding properties:
 * <ol>
 * 		<li> {@link #imageFileName} </li>
 * 		<li> {@link #resolutionHeight} </li>
 * 		<li> {@link #resolutionWidth} </li>
 * </ol>
 * 	
 *
 */
public class ImagePost extends Post{
	private String imageFileName;
	private double resolutionHeight;
	private double resolutionWidth;
	
	/**
	 * Constructor of {@link ImagePost} class.
	 * @param text of the post.
	 * @param postedDate of the post.
	 * @param longitude of the location.
	 * @param latitude of the location.
	 * @param taggedUsers who tagged in the post.
	 * @param imageFileName that name of the file with extensions.
	 * @param resolution of the file.
	 */
	public ImagePost(String text, String postedDate, String longitude, String latitude, String taggedUsers, String imageFileName, String resolution) {
		super(text, postedDate, longitude, latitude, taggedUsers);
		setImageFileName(imageFileName);
		setResolution(resolution);
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

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public void setResolution(String resolution){
		String[] res = resolution.split("<x>");
		resolutionWidth = Double.parseDouble(res[0]);
		resolutionHeight = Double.parseDouble(res[1]);
	}
	
	public String getResolution(){
		return String.format("%fx%f", resolutionWidth, resolutionHeight);
	}
	
	public String toString(){
		String s = super.toString() + "Image File Name: " + imageFileName + ", Resolution: " + (int)resolutionWidth + "x" + (int)resolutionHeight  ;
		return s;
		
	}
}

