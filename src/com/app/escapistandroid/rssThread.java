package com.app.escapistandroid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

class rssThread extends Thread{
	
	String loc;
	Boolean finished = false;
	int numLinks = 0;
	Link[] links;
	rssThread(String url){
		loc = url;
		links = new Link[55];
			}
	
	
	 public void run() {
		 InputStream is = null;
  String title ="none";
			try {
			

				//We will put the data into a StringBuilder
				StringBuilder builder=new StringBuilder();
				 
				URL url = new URL(loc);
				 
				XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp=factory.newPullParser();
				 
				xpp.setInput(getInputStream(url), "UTF_8");
				 
				int eventType=xpp.getEventType();
	
				while(eventType!=XmlPullParser.END_DOCUMENT){
				  // Looking for a start tag
				  if(eventType==XmlPullParser.START_TAG){
				    //We look for "title" tag in XML response
				    if(xpp.getName().equalsIgnoreCase("title")){
				    	title = xpp.nextText();
		      //Once we found the "title" tag, add the text it contains to our builder
				     // builder.append(xpp.nextText()+"\n");
				    }else if(xpp.getName().equalsIgnoreCase("link")){
				    	System.out.println("numLinks = "+ numLinks);
				    	links[numLinks] = new Link(xpp.nextText(),title);
				    	//links[numLinks].setData(title,xpp.getName());
				    	title = "";
				    	numLinks++;
				    	// builder.append(xpp.nextText()+"\n");
				    	//System.out.println(numLinks);
				    	
				    }
				  }
				 
				  eventType=xpp.next();
				}
				
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
		 
			
		 finished = true;
	 }
	 
	 public InputStream getInputStream(URL url) {
		  try {
		    return url.openConnection().getInputStream();
		  } catch (IOException e) {
		    return null;
		  }
		}
		public Link[] returnLinks(){
			System.out.println("ReturnLinks");
			Link[] returnLinks = new Link[numLinks];
			for(int i = 0;i<numLinks;i++){
				returnLinks[i] =  links[i+2];
				
	
				
			}
			
			
			System.out.println("returned");

			return returnLinks;

		}
		
		public Boolean finished(){
			
			return finished;
			
		}
	
}

