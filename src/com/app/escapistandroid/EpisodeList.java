package com.app.escapistandroid;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EpisodeList extends ListActivity {
	Link[] links;
	
    String[] items;
    String[] rssUrls;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		items = new String[2];
		items[0] = "a";
		items[1] = "b";
		Intent intent = getIntent();
	    Bundle extras = getIntent().getExtras();
	    String[] episodes = extras.getStringArray("episodes");
	    rssUrls = extras.getStringArray("rssUrls");
		this.setTitle(String.format(extras.getString("showName"), "aa"));

		
		setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1,
				episodes));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.episode_list, menu);
		return true;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    MenuItem item= menu.findItem(R.id.action_settings);
	    item.setVisible(false);
	    return true;
	}
	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id){
		
		
		try{
			
			Class selected = Class.forName("com.app.escapistandroid.FullscreenActivity");
			Intent selectedIntent = new Intent(this,selected);
			selectedIntent.putExtra("new_variable_name", rssUrls[position]);

			startActivity(selectedIntent);
			
		}catch(ClassNotFoundException e){
			
			
			
		}
		
	}
}
