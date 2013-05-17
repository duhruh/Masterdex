package com.protodx.masterdex;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private SQLengine engine;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.splash);
	    Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.logo);
        tv.setTypeface(tf);
        
	    // thread for displaying the SplashScreen
	    Thread splashTread = new Thread() {
	        @Override
	        public void run() {
	            engine = new SQLengine(SplashActivity.this);
                try{
                	boolean dbExist = engine.createDataBase();
                	// If the db doesn't exist just look at the pretty screen for a while
                	if(dbExist == false){
                		sleep(1000);
                	}
                }catch(IOException ioe){
                	throw new Error("Unable to create database!");
                	} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            finally{
	            	finish();
	            	Intent intent = new Intent(SplashActivity.this,MasterdexActivity.class);
	                startActivity(intent);
	            }
	        }
	    };
	    splashTread.start();
	}

	@Override
	protected void onPause() {
		engine.close();
		super.onPause();
	}
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    if (engine != null) {
	    	engine.close();
	    }
	}
}
