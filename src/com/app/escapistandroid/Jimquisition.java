package com.app.escapistandroid;

import com.app.escapistandroid.Class.LoadingThread;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Jimquisition extends ListActivity {

	Link[] links; //= new Link[52];
	
    String[] items; //= new String[52];

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	LoadingThread loadingThread = new LoadingThread(this);		
	loadingThread.showDialog();	
		
		super.onCreate(savedInstanceState);
	
		rssThread thread = new rssThread("http://www.escapistmagazine.com/rss/videos/list/166.xml");
		thread.start();
		
		while(!thread.finished()){
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
			
			
		}
		//loadingThread.hideDialog();
		links = thread.returnLinks();

items = new String[links.length-2];	

		for(int i = 0;i<links.length;i++){
			System.out.println(i);

		
			try{

			items[i]=links[i].getTitle();
			}catch(NullPointerException e){
				
				System.out.println(i);
				
			}catch (ArrayIndexOutOfBoundsException e) {
				
				System.out.println("Caught");

			}
		}
		System.out.println(items);
		
		
		setListAdapter(new ArrayAdapter<String>(this, 
		android.R.layout.simple_list_item_1,
		items));
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zero_punctuation, menu);
		return true;
	}

	protected void onListItemClick(ListView listView, View view, int position, long id){
		super.onListItemClick(listView,view,position,id);
		String vidURL = links[position].getURL();
		
		try{
			
			Class selected = Class.forName("com.app.escapistandroid.FullscreenActivity");
			Intent selectedIntent = new Intent(this,selected);
			selectedIntent.putExtra("new_variable_name", vidURL);

			startActivity(selectedIntent);
			
		}catch(ClassNotFoundException e){
			
			
			
		}
		
		
	}
	

	
}





