package com.app.escapistandroid;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
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
 





public class ViewActivity extends ListActivity {
	ListView listView1;
    ListView listView2;
    String[] items = { "ZeroPunctuation", "item 2", "item 3", "item 4", "item 5" };
    String[] items_list_2 = { "items_list_2", "items_list_2", "items_list_2",
            "items_list_2", "items_list_2", "items_list_2", "items_list_2",
            "items_list_2", "items_list_2", "items_list_2", "items_list_2" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1,
				items));
    }
	
	
	protected void onListItemClick(ListView listView, View view, int position, long id){
		super.onListItemClick(listView,view,position,id);
		String openClass = items[position];
		
		try{
			
			Class selected = Class.forName("com.app.escapistandroid."+openClass);
			Intent selectedIntent = new Intent(this,selected);
			startActivity(selectedIntent);
			
		}catch(ClassNotFoundException e){
			
			
			
		}
		
		
	}
	
	
		
}


