package com.protodx.masterdex;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AboutActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        RelativeLayout root = (RelativeLayout)findViewById(R.id.root);
        applyFontRecursively(root,tf);
        EditText et = (EditText) findViewById(R.id.editText1);
        tf = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
        et.setTypeface(tf);
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
