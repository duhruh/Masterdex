package com.protodx.masterdex;

import java.sql.SQLException;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MoveActivity extends Activity{
	private ArrayList<TextView> tvList;
	private ArrayList<TextView> tvTags;
	private Typeface tf;
	private static final String TYPEFACE = "fonts/Roboto-Light.ttf"; 
	private PokemonDAO dao;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.move);
	    
	    String[] name = new String[1];
        name[0] = getIntent().getExtras().getString("com.protodex.masterdex.move");
        if(name[0].contentEquals("-")){
        	name[0] = "Struggle";
        }
        tf = Typeface.createFromAsset(getAssets(),TYPEFACE);
        
        tvList = new ArrayList<TextView>();
        tvList.add((TextView)findViewById(R.id.type));
        tvList.add((TextView)findViewById(R.id.tmhm));
        tvList.add((TextView)findViewById(R.id.cat));
        tvList.add((TextView)findViewById(R.id.acc));
        tvList.add((TextView)findViewById(R.id.power));
        tvList.add((TextView)findViewById(R.id.pp));
        tvList.add((TextView)findViewById(R.id.prob));
        tvList.add((TextView)findViewById(R.id.effect));
        tvList.add((TextView)findViewById(R.id.move));
        
        tvTags = new ArrayList<TextView>();
        tvTags.add((TextView)findViewById(R.id.typeTag));
        tvTags.add((TextView)findViewById(R.id.tmhmTag));
        tvTags.add((TextView)findViewById(R.id.catTag));
        tvTags.add((TextView)findViewById(R.id.accTag));
        tvTags.add((TextView)findViewById(R.id.powerTag));
        tvTags.add((TextView)findViewById(R.id.ppTag));
        tvTags.add((TextView)findViewById(R.id.probTag));
        tvTags.add((TextView)findViewById(R.id.effectTag));
        tvTags.add((TextView)findViewById(R.id.moveTag));
        
        
        
        dao = new PokemonDAO(this);
        Move move = new Move();
       
        try {
			dao.open();
			move = dao.getMove(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}dao.close();
		
		setMoveEntry(move);
	}
	private void setMoveEntry(Move move){
		setMoveLayout(move);
		setText(move);
		setTypeface();
	}
	private void setMoveLayout(Move move){
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.topBarLayout);
		ImageView image = (ImageView)findViewById(R.id.typePic);
		String icon = move.getType().toLowerCase().substring(0, 3);
		int resId = getResources().getIdentifier("bar_"+icon, "drawable", "com.protodx.masterdex");
		int resIdImage = getResources().getIdentifier("type_"+icon, "drawable", "com.protodx.masterdex");
		image.setImageResource(resIdImage);
		layout.setBackgroundResource(resId);
	}
	private void setText(Move move){
		tvList.get(0).setText(move.getType());
		tvList.get(0).setTypeface(tf);
		tvList.get(1).setText(move.getTmHm());
		tvList.get(1).setTypeface(tf);
		tvList.get(2).setText(move.getCategory());
		tvList.get(2).setTypeface(tf);
		tvList.get(3).setText(String.valueOf(move.getAccuracy()));
		tvList.get(3).setTypeface(tf);
		tvList.get(4).setText(String.valueOf(move.getPower()));
		tvList.get(4).setTypeface(tf);
		tvList.get(5).setText(String.valueOf(move.getPP()));
		tvList.get(5).setTypeface(tf);
		tvList.get(6).setText(String.valueOf(move.getProbability()));
		tvList.get(6).setTypeface(tf);
		tvList.get(7).setText(move.getEffect());
		tvList.get(7).setTypeface(tf);
		tvList.get(8).setText(move.getName());
	}
	private void setTypeface(){
		for(TextView tv:tvTags){
			tv.setTypeface(tf);
		}
	}
	 @Override
		protected void onResume() {
			try {
				dao.open();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onResume();
		}
		@Override
		protected void onPause() {
			dao.close();
			super.onPause();
		}
		@Override
		protected void onDestroy() {
		    super.onDestroy();
		    if (dao != null) {
		    	dao.close();
		    }
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
}
