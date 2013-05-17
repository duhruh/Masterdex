package com.protodx.masterdex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MechanicsActivity extends Activity {
	private Vibrator myVib;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanics);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        TextView tv = (TextView) findViewById(R.id.masterdex);
        
        tv.setTypeface(tf);
        
        final Button movesButton = (Button) findViewById(R.id.moves_button);
        movesButton.setTypeface(tf);
        movesButton.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 				myVib.vibrate(50);
 				final Intent moveIntent = new Intent(MechanicsActivity.this,MoveListActivity.class);
 				startActivity(moveIntent);
 				
 			}
 		});
        
        final Button natureButton = (Button) findViewById(R.id.nature_button);
        natureButton.setTypeface(tf);
        natureButton.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 				myVib.vibrate(50);
 				final Intent natureIntent = new Intent(MechanicsActivity.this,NatureActivity.class);
 				startActivity(natureIntent);
 				
 			}
 		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}
}
