/*
 *@version 1.0 
 *@author Daryl Johnston<daryl874@gmail.com
*/
package com.app.escapistandroid;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
 
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


import com.app.escapistandroid.util.SystemUiHider;
import com.app.escapistandroid.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
	Boolean share;
	VideoView mVideoView;
	public FullscreenActivity() {
		super();
		}
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	private static final int SCREEN_ORIENTATION_LANDSCAPE = 0;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;
	String sharedText;
	// The following line should be changed to include the correct property id.
	private static final String PROPERTY_ID = "UX-XXXXXXXX-X";
	 
	//Logging TAG
	private static final String TAG = "MyApp";
	 
	public static int GENERAL_TRACKER = 0;
	protected void onStop() {
	    super.onStop();
	}
	
	View contentView;
	//on load code
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		Intent intent = getIntent();
	    String action = intent.getAction();
	    String type = intent.getType();
		
	    super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);

		contentView = (VideoView)findViewById(R.id.videoView1);

		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							// If the ViewPropertyAnimator API is available
							// (Honeycomb MR2 and later), use it to animate the
							// in-layout UI controls at the bottom of the
							// screen.
							if (mControlsHeight == 0) {
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
		
						} else {
							// If the ViewPropertyAnimator APIs aren't
							// available, simply show or hide the in-layout UI
							// controls.
						
						}

						if (visible && AUTO_HIDE) {
							// Schedule a hide().
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		// Set up the user interaction to manually show or hide the system UI.
		
		
		Bundle extras = getIntent().getExtras();
		System.out.println("Size  "+ extras.size());
		if (extras != null&&extras.size()==1) {//check if extras is null or if url has been shared with app
			System.out.println("Extra  "+extras);
		    String value = extras.getString("new_variable_name");
		    System.out.println(value);
		    try {
		    	sharedText = value;
				playVideo(getURL(value));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/*
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					
					mSystemUiHider.toggle();
				mVideoView.show(1000);
				} else {
					//mSystemUiHider.show();
				}
			}
		});
*/
		//set the orientation of the device to landscape
		this.setRequestedOrientation(FullscreenActivity.SCREEN_ORIENTATION_LANDSCAPE);

/*

*/

	//check if the application has been opened via a share 
		if (Intent.ACTION_SEND.equals(action) && type != null) {
	        if ("text/plain".equals(type)) {
	        	System.out.println("Intent "+intent);
	        	//call the method to compile the shared URL and 
	            handleSendText(intent); // Handle text being sent
	        }else{
	        	//Show help

	        Intent launchactivity= new Intent(FullscreenActivity.this,popup.class);                               
	     startActivity(launchactivity);    
          }
	         
		}else{
			
		//	startActivity(new Intent(FullscreenActivity.this,ViewActivity.class));
			System.out.println("No message here");

			
		}
		
		
	}
	

	  
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */


	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
			
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	
	public void playVideo(String URL){
		
		Uri uri = Uri.parse(URL); //Declare your url here.
		final ProgressDialog progDailog;
		mVideoView  = (VideoView)findViewById(R.id.videoView1);
		mVideoView.setVisibility(1);
		mVideoView.setMediaController(new MediaController(this));       
		mVideoView.setVideoURI(uri);
		mVideoView.requestFocus();
		mVideoView.start();
	      progDailog = ProgressDialog.show(this, "Please wait ...", "Retrieving data ...", true);

	     
	      
			contentView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (TOGGLE_ON_CLICK) {
						
						mSystemUiHider.toggle();
					} else {
						//mSystemUiHider.show();
					}
				}
			});
	      
	      
	      
	      
	      
	      mVideoView.setOnPreparedListener(new OnPreparedListener() {


	  
	    	  
	            public void onPrepared(MediaPlayer mp) {
	                // TODO Auto-generated method stub
	                progDailog.dismiss();
	                
	            }

	
	        });
	    }
		
		
		
	
	
	//get the URL
	void handleSendText(Intent intent) {
	    sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		System.out.println("loading: "+sharedText);

	    	try {
				playVideo(getURL(sharedText));
	    		
	    	
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	        // Update UI to reflect text being shared
	    }
	
	//find the temp MP4 link in the source of escapist
	public String getURL(String source) throws IllegalStateException, IOException{
	
		String a;
		
		
readThread read = new readThread(sharedText);
read.start();



while(!read.getDone()){
	System.out.println("Waiting...");
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

String s = read.returnMp4URL();
read = null;
return s;

	
	
	

		
	
	}
	
	class readThread extends Thread {
      	HttpURLConnection urlConnection = null;
      URL url = null;
      String mp4URL = null;
      String MP4URL = "http://video."; 
      Boolean done = false;
	
      
      readThread(String page){
			
			mp4URL = "a";
			try {
				url = new URL(page);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		 public void run() {
			 String jsURL = null;
		    			    	try {
		    		
				    urlConnection = (HttpURLConnection) url.openConnection();
				   
				     InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				     FilterInputStream filter = new BufferedInputStream(in);
				     
				     
				     //System.out.println(in.available());
				     //System.out.println(getUrlCode(in));
				     jsURL = getUrlCode(in);
				   
	  					try {
	  						url = new URL(jsURL);
	  					} catch (MalformedURLException e) {
	  						// TODO Auto-generated catch block
	  						e.printStackTrace();
	  					}
	  					    urlConnection = (HttpURLConnection) url.openConnection();
	  					   
	  					      in = new BufferedInputStream(urlConnection.getInputStream());
	  					      filter = new BufferedInputStream(in);
				     
				     
				    System.out.println(getMP4URL(in));
				    
				     
				     
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
				   //  urlConnection.disconnect();
				   }

		    			    	
		    			    	
		    			    	
		    			    	
		    }
		 
		 public String getMP4URL(InputStream URL) throws IOException{
			 	
			 
			
			Reader reader = new InputStreamReader(URL);
			String find = "'url':'http://video";
			int found = 0;
			
			 int data = reader.read();
			 while(data != -1){
				
				 char dataChar = (char) data;
			        data = reader.read();
			        
			   if(dataChar == find.charAt(found)){
			 
				   found++;
				   if(found == 19){
				   // found start of url
				   //System.out.println("found start of URL");
				   found = 0; 
				   
				   
				   while(dataChar != "'".charAt(0)){
				  data = reader.read();
					  dataChar = (char) data;
				    MP4URL = MP4URL + dataChar;    
				        System.out.println("datachar " + MP4URL);
				        
				   }
				   MP4URL = MP4URL.replaceAll("'","");
				   done = true;
				   return MP4URL;
				   
				   }
			 }else{
				 
			 found = 0;
			 }
			 }
			 
			 //System.out.println(URL.toString());
			 return URL.toString();
		
		 }
		    	
		   public String getUrlCode(InputStream code) throws IOException{
			   
			byte[] b = null;
			int found = 0;
			String jsString = "";
			String findText = "video_player_object";
			String findjs = ".js";
			boolean complete = false;
			Boolean match = false;
			Reader reader = new InputStreamReader(code);
		    
			int data = reader.read();
			while(data != -1|| !complete){
		        char dataChar = (char) data;
		        data = reader.read();
		        if(dataChar == findText.charAt(found)){

		        	
		        	
		        	if(found == 18){
		        		
		        		found = 0;
		        		while(match ==false){
		        			dataChar = (char) data;
			 		        data = reader.read();	
			 		        
			 		        
			 		       if(dataChar == "config=".charAt(found)){
			        			 found ++;
			        			 if(found == 7){
					 		    	   System.out.println("Found Config");

		        			 match = true;
			        			 }
		        		}else{
		        			found = 0;
		        			
		        		}
			 		       
		        		
		        		}
		        		
		        		match = false;
		        		found = 0;
		        	 while(match==false){
		        		 
		        		 dataChar = (char) data;
		 		        data = reader.read();
	        			 jsString = jsString+dataChar;
		        		 if(dataChar == findjs.charAt(found)){
		        			 found ++;
		        		 if(found == 3){
		        			 
		        			 complete = true;
		        			match = true;		        		 
		        			
		        		 
		        		 }
		        		 }
		        	 }

		        	}
		        	
		        	found++;
		        }else {
		        	found = 0;
		        }
		    }
			

			   return jsString;
					  
			   
			   
		   }
		   
		    	
		    	private String getString(final InputStream is) {
				 
				BufferedReader br = null;
				StringBuilder sb = new StringBuilder();
		 
				String line;
				try {
		 
					br = new BufferedReader(new InputStreamReader(is));
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
		 
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		 
				return sb.toString();
		 
			}
		
	
	
	public String returnMp4URL(){
		return MP4URL;
	}
	 
	public boolean getDone(){
		return done;
		
	}

	
	
	}

	
	
	
	
}
