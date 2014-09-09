package com.app.escapistandroid.Class;

import android.app.ProgressDialog;
import android.content.Context;

/*
 * 
 * Class for displaying user feedback on loading while transitioning between activities 
 * 
 */


public class LoadingThread {

	ProgressDialog dialog;
	
	public LoadingThread(Context context){
		
	dialog = new ProgressDialog(context);
		
		
	}
	
public void showDialog(){
	
dialog.show();
	
}	
	
public void hideDialog(){
	
	dialog.dismiss();
	
	
}
	
	
	
	
	
	
}
