package com.app.escapistandroid;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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
    
    String[][] items = new String[][]{
    	//Strings contain show name to be displayed in the menu
    	//and the activity name that the user will be sent to upon selection
    	{"Zero Punctuation", "ZeroPunctuation"},
    	{"Jimquisition", "Jimquisition"},
    	{"Escapist News Now", "NewsNowShow"},
    	{"Escapist Podcast", "EscapistPodcastShow"},
    	{"Escapist Podcast - Tabletop", "EscapistPodcastTShow"},
    	{"Escapist Podcast - Movies and TV", "EscapistPodcastMVShow"},
    	{"The Big Picture", "BigPictureShow"},
    	{"The Escapist Presents", "EscapistPresentsShow"},
    	{"EXP", "EXPShow"},
    	{"Feed Dump", "FeedDumpShow"},
    	{"GameFront Plays", "GameFrontPlays"},
    	{"Game Theory", "GameTheoryShow"},
    	{"Game of Thrones Abridged", "GOTAbridgedShow"},
    	{"Loading Ready Run", "LoadingReadyRunShow"},
    	{"Miracle of Sound", "MiracleOfSoundShow"},
    	{"Movie Defense Force", "MovieDefenseShow"},
    	{"Movie Trailers", "MovieTrailersShow"},
    	{"Escapist News Now", "NewsNowShow"},
    	{"No Right Answer", "NoRightAnswerShow"},
    	{"Shut up and Sit Down", "ShutUpShow"},
    	{"Trailers", "TrailersShow"},
    	{"Uncivil War", "UncivilWarShow"},
    	{"Unskippable", "UnskippableShow"},
    	{"Top 5 with Lisa Foiles", "Top5Show"}
    	
    	
    };

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
		
		//ProgressDialog dialog = new ProgressDialog(this);
		//dialog.show();
		
		super.onListItemClick(listView,view,position,id);
		String openClass = getClasses()[position];
		
		try{
			
			Class selected = Class.forName("com.app.escapistandroid."+openClass);
			Intent selectedIntent = new Intent(this,selected);
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
private String[] getClasses(){
	
	String[] className = new String[items.length];
	
	for(int i = 0;i<items.length;i++){
		
		className[i] = items[i][1];
		
		
	}
	return className;
}


}

