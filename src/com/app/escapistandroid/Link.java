package com.app.escapistandroid;

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