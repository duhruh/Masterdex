package com.protodx.masterdex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NatureActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naturepage);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.natures, null);
        RelativeLayout natureSecion = (RelativeLayout)findViewById(R.id.naturesection);
        natureSecion.addView(child);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        RelativeLayout root = (RelativeLayout)findViewById(R.id.root);
        applyFontRecursively(root,tf);
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
	void applyFontRecursively(ViewGroup parent, Typeface font) 
	{    
	    for(int i = 0; i < parent.getChildCount(); i++)
	    {
	        View child = parent.getChildAt(i);            
	        if(child instanceof ViewGroup) 
	        {
	            applyFontRecursively((ViewGroup)child, font);
	        }
	        else if(child != null)
	        {
	            Log.d("menfis", child.toString());
	            if(child.getClass() == TextView.class)
	            {
	                ((TextView) child).setTypeface(font);
	            }
	        }                
	    }
	}
}
