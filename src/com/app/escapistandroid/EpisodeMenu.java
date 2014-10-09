package com.app.escapistandroid;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class EpisodeMenu extends ListActivity {
	Link[] links;
	
    String[] items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		/*
		Intent intent = getIntent();
	    String action = intent.getAction();
	    String type = intent.getType();
	    Bundle extras = getIntent().getExtras();
	    String[] episodes = extras.getStringArray("episodes");
	    String[] rssUrls = extras.getStringArray("rssUrls");

	    String rssUrl = extras.getString("rssUrl");
		
	    
	    */

		
		/*for(int i = 0;i<episodes.length;i++){
			System.out.println(i);

			
			try{
			items[i]=links[i].getTitle();
			}catch(NullPointerException e){
				
				System.out.println(i);
				
			}catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		
		
		setListAdapter(new ArrayAdapter<String>(this, 
		android.R.layout.simple_list_item_1,
		episodes));
*/
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_episode_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.episode_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
