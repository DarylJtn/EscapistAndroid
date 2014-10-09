package com.app.escapistandroid;

import com.google.android.gms.drive.internal.v;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
/*
 * Main menu 
 * 
 * Contains a list of shows that can be viewed. 
 * When a user selects a show from the list the application switches to that activity
 * 
 * 
 */




public class ViewActivity extends ListActivity  {
	ListView listView1;
    ListView listView2;
    Link[] links;
    private ProgressDialog pDialog;

    String[][] items = new String[][]{
    	//Strings contain show name to be displayed in the menu
    	//and the activity name that the user will be sent to upon selection
        {"Escape to the Movies", "http://www.escapistmagazine.com/rss/videos/list/101.xml"},
    	{"Escapist News Now", "http://www.escapistmagazine.com/rss/videos/list/197.xml"},
    	{"Escapist Podcast", "http://www.escapistmagazine.com/rss/videos/list/167.xml"},
    	{"Escapist Podcast - Comics and Cosplay", "http://www.escapistmagazine.com/rss/videos/list/208.xml"},
    	{"Escapist Podcast - Movies and TV", "http://www.escapistmagazine.com/rss/videos/list/207.xml"},
    	{"Escapist Podcast - Science and Tech", "http://www.escapistmagazine.com/rss/videos/list/210.xml"},
    	{"Escapist Podcast - Tabletop", "http://www.escapistmagazine.com/rss/videos/list/206.xml"},
    	{"EXP", "http://www.escapistmagazine.com/rss/videos/list/198.xml"},
    	{"Feed Dump", "http://www.escapistmagazine.com/rss/videos/list/171.xml"},
    	{"Game of Thrones Abridged", "http://www.escapistmagazine.com/rss/videos/list/203.xml"},
    	{"Game Theory", "http://www.escapistmagazine.com/rss/videos/list/205.xml"},
    	{"GameFront Plays", "http://www.escapistmagazine.com/rss/videos/list/209.xml"},
    	{"Jim & Yahtzee's Rhymedown Spectacular", "http://www.escapistmagazine.com/rss/videos/list/196.xml"},
    	{"Jimquisition", "http://www.escapistmagazine.com/rss/videos/list/166.xml"},
    	{"LoadingReadyRun", "http://www.escapistmagazine.com/rss/videos/list/123.xml"},
    	{"Miracle of Sound", "http://www.escapistmagazine.com/rss/videos/list/165.xml"},
    	{"Movie Defense Force", "http://www.escapistmagazine.com/rss/videos/list/190.xml"},
    	{"Movie Trailers", "http://www.escapistmagazine.com/rss/videos/list/186.xml"},
    	{"No Right Answer", "http://www.escapistmagazine.com/rss/videos/list/172.xml"},
    	{"Previews", "http://www.escapistmagazine.com/rss/videos/list/212.xml"},
    	{"Reviews", "http://www.escapistmagazine.com/rss/videos/list/73.xml"},
    	{"Shut up and Sit Down", "http://www.escapistmagazine.com/rss/videos/list/204.xml"},
    	{"The Big Picture", "http://www.escapistmagazine.com/rss/videos/list/156.xml"},
    	{"The Escapist On The Road", "http://www.escapistmagazine.com/rss/videos/list/195.xml"},
    	{"The Escapist Presents", "http://www.escapistmagazine.com/rss/videos/list/7.xml"},
    	{"Top 5 with Lisa Foiles", "http://www.escapistmagazine.com/rss/videos/list/159.xml"},
    	{"Trailers", "http://www.escapistmagazine.com/rss/videos/list/150.xml"},
    	{"Uncivil War", "http://www.escapistmagazine.com/rss/videos/list/213.xml"},
    	{"Unskippable", "http://www.escapistmagazine.com/rss/videos/list/82.xml"},
    	{"Zero Punctuation", "http://www.escapistmagazine.com/rss/videos/list/1.xml"}
    		
 
    };
    
    
	protected Handler handler;
	private ProgressDialog progressDialog;

    /*
     * on create set the title of the activity 
     * set the name of shows to the user interface from the array 
     * 
     */
	
	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {



		super.onCreate(savedInstanceState);
		this.setTitle(String.format("Select a Show", "aa"));
		setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1,
				getTitles()));
    }
	
	
/*
 * On click of item get the position of clicked item and get the class name of the selection then switch to that activity
 * 
 */
	
	protected void onListItemClick(ListView listView, View view, int position, long id){
 		
		
//Check if the user is connected to the internet 
		//notify the user if they are not connected
if(!isNetworkAvailable()){
	
	
	Context context = getApplicationContext();
	CharSequence text = "Network Error. Check internet connection";
	int duration = Toast.LENGTH_SHORT;

	Toast toast = Toast.makeText(context, text, duration);
	toast.show();
	return;
}

		super.onListItemClick(listView,view,position,id);
		String openClass = getUrls()[position];
		
		try{
			//TODO add loading dialog
			
			
			rssThread thread = new rssThread(getUrls()[position]);
			thread.start();
			
			
			
			
		int numAttempts = 0; 
			while(!thread.finished()){
				
			
				
				try {
					Thread.sleep(100);
					numAttempts++;
					if (numAttempts == 40){
						
						Context context = getApplicationContext();
						CharSequence text = "A network error occurred. Please try again";
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
						return;
						
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	             
			}
			System.out.println("got links");

			links = thread.returnLinks();
			
			String[] episodes = new String[links.length-2];
			String[] rssUrls = new String[links.length-2];

			for(int i=0;i<=links.length-3;i++){
			System.out.println("count "+i);

			System.out.println("length "+links.length);
			System.out.println(links[i].getTitle());	
			episodes[i] = links[i].getTitle();
			rssUrls[i] = links[i].getURL();
				
			
			}
			
			

			String[] eps = new String[20];
			Class selected = Class.forName("com.app.escapistandroid.EpisodeList");

			Intent selectedIntent = new Intent(this,selected);

			selectedIntent.putExtra("episodes", episodes);
			selectedIntent.putExtra("rssUrls",rssUrls);
			selectedIntent.putExtra("showName",items[position][0]);


						startActivity(selectedIntent);
			
		}catch(ClassNotFoundException e){
			
			
			
		}
		
		
	}
	
	
//Returns a 1D array of titles from the items 2D array
private String[] getTitles(){
	
	String[] title = new String[items.length];
	
	for(int i = 0;i<items.length;i++){
		
		title[i] = items[i][0];
		
		
	}
	return title;
} 



//Returns a 1D array of classnames from the items 2D array
private String[] getUrls(){
	
	String[] className = new String[items.length];
	
	for(int i = 0;i<items.length;i++){
		
		className[i] = items[i][1];
		
		
	}
	return className;
}
private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager 
          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
}



}

