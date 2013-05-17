package com.protodx.masterdex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MasterdexActivity extends Activity{
    /** Called when the activity is first created. */
	//private SQLengine engine;
	private Vibrator myVib;
	
	 
    // This code can be any value you want, its just a checksum.
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        
//        GifWebView view = new GifWebView(this, "file:///android_asset/birds.gif");
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.thing);
//        layout.addView(view);

        
//        engine = new SQLengine(this);
//       try{
//       	engine.createDataBase();
//       }catch(IOException ioe){throw new Error("Unable to create database!");}
       

       //setContentView(R.layout.main);
       Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
       TextView tv = (TextView) findViewById(R.id.masterdex);
       
       tv.setTypeface(tf);
       
       final Button pokedexButton = (Button) findViewById(R.id.custom_button);
       pokedexButton.setTypeface(tf);
       pokedexButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myVib.vibrate(50);
				final Intent pokedexIntent = new Intent(MasterdexActivity.this,ListActivity.class);
				startActivity(pokedexIntent);
				
			}
		});
       
       final Button trainerButton = (Button) findViewById(R.id.trainer_button);
       trainerButton.setTypeface(tf);
       trainerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myVib.vibrate(50);
				final Intent trainerIntent = new Intent(MasterdexActivity.this,TrainerListActivity.class);
				startActivity(trainerIntent);
				
			}
		});
       
       final Button mechanicsButton = (Button) findViewById(R.id.mechanics_button);
       mechanicsButton.setTypeface(tf);
       mechanicsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myVib.vibrate(50);
				final Intent mechanicsIntent = new Intent(MasterdexActivity.this,MechanicsActivity.class);
				startActivity(mechanicsIntent);
				
			}
		});
       
       final Button aboutButton = (Button) findViewById(R.id.about_button);
       aboutButton.setTypeface(tf);
       aboutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myVib.vibrate(50);
				final Intent aboutIntent = new Intent(MasterdexActivity.this,AboutActivity.class);
				startActivity(aboutIntent);
				
			}
		});
       final Button twitterButton = (Button) findViewById(R.id.twitter);
       twitterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myVib.vibrate(50);
				final Intent twitterIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.twitter.com/protodx"));
				startActivity(twitterIntent);
				
			}
		});
    }
  
}