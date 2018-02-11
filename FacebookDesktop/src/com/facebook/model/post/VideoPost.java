package com.facebook.model.post;

import com.facebook.model.user.User;

/**
 * Video class that extends {@link Post} class. Excluding properties:
 * <ol>
 * 		<li> {@link #videoFileName} </li>
 * 		<li> {@link #videoDuration} </li>
 * </ol>
 *
 *
 */
public class VideoPost extends Post{
	public static final int MAX_VIDEO_DURATION = 10;
	
	private String videoFileName; // File name of video.
	private int videoDuration; // Duration of video.
	
	/**
	 * Constructor of {@link VideoPost} class with features of the class.
	 * @param text of the post. 
	 * @param postedDate of the post.
	 * @param longitude of shared place.
	 * @param latitude of shared place.
	 * @param taggedUsers who tagged in the {@link VideoPost}.
	 * @param videoFileName Name of the file.
	 * @param videoDuration Duration of the file.
	 */
	public VideoPost(String text, String postedDate, String longitude, String latitude, String taggedUsers, String videoFileName, String videoDuration) {
		super(text, postedDate, longitude, latitude, taggedUsers);
		setVideoFileName(videoFileName);
		setVideoDuration(videoDuration);
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

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public int getVideoDuration() {
		return videoDuration;
	}

	public void setVideoDuration(String videoDuration) {
		this.videoDuration = Integer.parseInt(videoDuration);
	}
	
	public String toString(){
		String s = super.toString() + "Image File Name: " + videoFileName + ", Duration: " + videoDuration;
		return s;
		
	}
	
	
}
