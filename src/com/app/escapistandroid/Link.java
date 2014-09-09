package com.app.escapistandroid;


/*
 * Link contains a url and title for a single episode of a show
 * Used for storing data for use in the User Interface
 * 
 * e.g URL = "HTTP://www.example.com/video123"
 * 	   title = "Hello world"
 * 
 * 
 */

class Link{
	
String URL;
String title;

Link(){
	
	
	
	
}

Link(String u, String t){
	
	URL = u;
	title = t;
	
}


void setData(String u, String t){
	
	
	URL = u;
	title = t;
}
	
	public String getURL(){
		return URL;
	}
	public String getTitle(){
		
	return title;	
		
	}
}