package com.facebook.model.post;
import java.util.Date;
import java.util.UUID;
/**
 * Interface of Post class and all post types. Includes abstract methods that should be included by all methods.
 * 
 *
 */
public interface IPost {
	
	public void setText(String text);
	public String getText();
	public UUID getID();
	public Date getDate();
}
