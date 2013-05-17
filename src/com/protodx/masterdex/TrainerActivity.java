package com.protodx.masterdex;

import java.sql.SQLException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TrainerActivity extends Activity {
	private ArrayList<TextView> tvList;
	private ArrayList<TextView> tvTags;
	private static LayoutInflater inflater = null;
	private ArrayList<TrainedPokemon> trainedPokemon;
	private Typeface tf;
	private static final String TYPEFACE = "fonts/Roboto-Light.ttf"; 
	private PokemonDAO dao;
	private ImageDownloader DL;
	 public void onCreate(Bundle savedInstanceState) {
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.trainerdex);
	        String[] name = new String[1];
	        name[0] = getIntent().getExtras().getString("com.protodex.masterdex.trainer");
	        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        tf = Typeface.createFromAsset(getAssets(),TYPEFACE);
	        
	        tvList = new ArrayList<TextView>();
	        tvList.add((TextView)findViewById(R.id.trainer));
	        tvList.add((TextView)findViewById(R.id.age));
	        tvList.add((TextView)findViewById(R.id.hometown));
	        tvList.add((TextView)findViewById(R.id.region));
	        tvList.add((TextView)findViewById(R.id.badge));
	        tvList.add((TextView)findViewById(R.id.pType));
	        
	        tvTags = new ArrayList<TextView>();
	        tvTags.add((TextView)findViewById(R.id.ageTag));
	        tvTags.add((TextView)findViewById(R.id.hometownTag));
	        tvTags.add((TextView)findViewById(R.id.regionTag));
	        tvTags.add((TextView)findViewById(R.id.badgeTag));
	        tvTags.add((TextView)findViewById(R.id.pTypeTag));
	        tvTags.add((TextView)findViewById(R.id.generalInfo));
	        
	        ImageView image = (ImageView)findViewById(R.id.trainer_);
	        
	        dao = new PokemonDAO(this);
	        Trainer trainer = new Trainer();
	        trainedPokemon = new ArrayList<TrainedPokemon>();
	        try {
				dao.open();
				trainer = dao.getTrainer(name);
				trainedPokemon = dao.getTrainedPokemonByTrainer(name);
			} catch (SQLException e) {
				e.printStackTrace();
			}dao.close();
			
			String trainerName = trainer.getName();
	        
	        if(trainerName.contains("Lt. Surge"))
	        	trainerName = "LtSurge";
	        if(trainerName.contains("Tate & Liza"))
	        	trainerName = "TateLiza";
	        if(trainerName.contains("Crasher Wake"))
	        	trainerName = "CrasherWake";
	        
			if(!trainer.getName().isEmpty()){
				setLayout(trainer);
				setTrainerInfo(trainer);
				inflateGameHeader();
				downloadImage("http://protodx.com/trainers/trainer_"+trainerName+".png",image);
			}
	 }
	 
	 private void setLayout(Trainer trainer){
		 RelativeLayout layout = (RelativeLayout)findViewById(R.id.topBarLayout);
		 
		 if(!trainer.getPreferredType().contains("???")){
			 String type = trainer.getPreferredType().substring(0, 3).toLowerCase();
			 int resId = getResources().getIdentifier("bar_"+type, "drawable", "com.protodx.masterdex");
			 layout.setBackgroundResource(resId);
		 }else{
			 int resId = getResources().getIdentifier("bar_dra", "drawable", "com.protodx.masterdex");
			 layout.setBackgroundResource(resId);
		 }
	 }
	 private void setTrainerInfo(Trainer trainer){
		 tvList.get(0).setText(trainer.getName());
		 tvList.get(1).setText(String.valueOf(trainer.getAge()));
		 tvList.get(2).setText(trainer.getHometown());
		 tvList.get(3).setText(trainer.getRegion());
		 tvList.get(4).setText(trainer.getBadge());
		 tvList.get(5).setText(trainer.getPreferredType());
		 
		 ImageView badgeIcon = (ImageView)findViewById(R.id.badgeIcon);
		 int badgeResource = getResources().getIdentifier(trainer.getBadge().replace(" ", "").toLowerCase(), "drawable", "com.protodx.masterdex");
		 badgeIcon.setImageResource(badgeResource);
		 
		 for(TextView text: tvList){
			 text.setTypeface(tf);
		 }
		 for(TextView text: tvTags){
			 text.setTypeface(tf);
		 }
		 
	 }
	 private void inflateGameHeader(){
		 LinearLayout midSection = (LinearLayout)findViewById(R.id.midSection);
		 
		 View rbChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout rFill = (LinearLayout)rbChild.findViewById(R.id.fill);
		 
		 View yChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout yFill = (LinearLayout)yChild.findViewById(R.id.fill);
		 
		 View gscChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout gscFill = (LinearLayout)gscChild.findViewById(R.id.fill);
		 
		 View frlgChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout frlgFill = (LinearLayout)frlgChild.findViewById(R.id.fill);
		 
		 View hgssChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout hgssFill = (LinearLayout)hgssChild.findViewById(R.id.fill);
		 
		 View rehgssChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout rehgssFill = (LinearLayout)rehgssChild.findViewById(R.id.fill);
		 
		 View eChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout eFill = (LinearLayout)eChild.findViewById(R.id.fill);
		 
		 View dpChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout dpFill = (LinearLayout)dpChild.findViewById(R.id.fill);
		 
		 View ptChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout ptFill = (LinearLayout)ptChild.findViewById(R.id.fill);
		 
		 View rsChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout rsFill = (LinearLayout)rsChild.findViewById(R.id.fill);
		 
		 View bwChild = inflater.inflate(R.layout.gameheader, null);
		 LinearLayout bwFill = (LinearLayout)bwChild.findViewById(R.id.fill);
		 
		 int rbFlag = 0;
		 int yFlag = 0;
		 int gscFlag = 0;
		 int frlgFlag = 0;
		 int hgssFlag = 0;
		 int rehgssFlag = 0;
		 int dpFlag = 0;
		 int eFlag = 0;
		 int ptFlag = 0;
		 int rsFlag = 0;
		 int bwFlag = 0;
		 for(TrainedPokemon tp: trainedPokemon){
			 if(tp.getGeneration().contains("R/B")){
				 TextView game = (TextView)rbChild.findViewById(R.id.pokeGame);
				 game.setText("Red/Blue");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 rFill.addView(child);
				 rbFlag = 1;
			 }
			 if(tp.getGeneration().contains("Y")){
				 TextView game = (TextView)yChild.findViewById(R.id.pokeGame);
				 game.setText("Yellow");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 yFill.addView(child);
				 yFlag = 1;
			 }
			 if(tp.getGeneration().contains("G/S/C")){
				 TextView game = (TextView)gscChild.findViewById(R.id.pokeGame);
				 game.setText("Gold/Silver/Crystal");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 gscFill.addView(child);
				 gscFlag = 1;
			 }
			 if(tp.getGeneration().contains("R/S")){
				 TextView game = (TextView)rsChild.findViewById(R.id.pokeGame);
				 game.setText("Ruby/Sapphire");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 rsFill.addView(child);
				 rsFlag = 1;
			 }
			 if(tp.getGeneration().contains("BW") || tp.getGeneration().contains("B") || tp.getGeneration().contains("W")){
				 TextView game = (TextView)bwChild.findViewById(R.id.pokeGame);
				 game.setText("Black/White");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 bwFill.addView(child);
				 bwFlag = 1;
			 }
			 if(tp.getGeneration().contains("E")){
				 TextView game = (TextView)eChild.findViewById(R.id.pokeGame);
				 game.setText("Emerald");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 eFill.addView(child);
				 eFlag = 1;
			 }
			 if(tp.getGeneration().contains("D/P")){
				 TextView game = (TextView)dpChild.findViewById(R.id.pokeGame);
				 game.setText("Diamond/Perl");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 dpFill.addView(child);
				 dpFlag = 1;
			 }
			 if(tp.getGeneration().contains("Pt")){
				 TextView game = (TextView)ptChild.findViewById(R.id.pokeGame);
				 game.setText("Platinium");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 ptFill.addView(child);
				 ptFlag = 1;
			 }
			 if(tp.getGeneration().contains("Fr/Lg")){
				 TextView game = (TextView)frlgChild.findViewById(R.id.pokeGame);
				 game.setText("Fire Red/ Leaf Green");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 frlgFill.addView(child);
				 frlgFlag = 1;
			 }
			 if(tp.getGeneration().contains("Hg/Ss") && tp.getMatch() == 0){
				 TextView game = (TextView)hgssChild.findViewById(R.id.pokeGame);
				 game.setText("Heart Gold/Soul Silver");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 hgssFill.addView(child);
				 hgssFlag = 1;
			 }
			 if(tp.getGeneration().contains("Hg/Ss") && tp.getMatch() == 1){
				 TextView game = (TextView)rehgssChild.findViewById(R.id.pokeGame);
				 game.setText("Heart Gold/Soul Silver (Rematch)");
				 game.setTypeface(tf);
				 View child = inflatePokemonEntry(tp);
				 rehgssFill.addView(child);
				 rehgssFlag = 1;
			 }
		 }
		 if(rbFlag != 0){
			 //midSection.addView(rbChild);
			 midSection.addView(rbChild);
		 }
		 if(yFlag != 0){
			 midSection.addView(yChild);
		 }
		 if(gscFlag != 0){
			 midSection.addView(gscChild);
		 }
		 if(rsFlag != 0){
			 midSection.addView(rsChild);
		 }
		 if(eFlag != 0){
			 midSection.addView(eChild);
		 }
		 if(ptFlag != 0){
			 midSection.addView(ptChild);
		 }
		 if(dpFlag != 0){
			 midSection.addView(dpChild);
		 }
		 if(frlgFlag != 0){
			 midSection.addView(frlgChild);
		 }
		 if(hgssFlag != 0){
			 midSection.addView(hgssChild);
		 }
		 if(rehgssFlag != 0){
			 midSection.addView(rehgssChild);
		 }
		 if(bwFlag != 0){
			 midSection.addView(bwChild);
		 }
	 }
	 private View inflatePokemonEntry(TrainedPokemon tp){
		 View child = inflater.inflate(R.layout.trainerpokemon, null);
		 TextView level = (TextView)child.findViewById(R.id.level);
		// TextView move = (TextView)child.findViewById(R.id.move);
		 TextView pkName = (TextView)child.findViewById(R.id.pkName);
		 ImageView pk = (ImageView)child.findViewById(R.id.trainerPok);
		 level.setText(String.valueOf(tp.getLevel()));
		 level.setTypeface(tf);
		 
		 //String replaceString = tp.getMoves().replaceAll("/", "\n");
		 //String[] attacks = new String[]{"-","-","-","-"};
		 String[] tempattacks = tp.getMoves().split("/");
		 ArrayList<String> attacks = new ArrayList<String>();
		 for(String atk: tempattacks){
			 attacks.add(atk);
		 }
		  //= tp.getMoves().split("/");
		 while(attacks.size() < 4){
			 attacks.add("-");
		 }
		 TextView atk1 = (TextView)child.findViewById(R.id.attack1);
		 TextView atk2 = (TextView)child.findViewById(R.id.attack2);
		 TextView atk3 = (TextView)child.findViewById(R.id.attack3);
		 TextView atk4 = (TextView)child.findViewById(R.id.attack4);
		 atk1.setText(attacks.get(0));
		 atk2.setText(attacks.get(1));
		 atk3.setText(attacks.get(2));
		 atk4.setText(attacks.get(3));
		 atk1.setTypeface(tf);
		 atk2.setTypeface(tf);
		 atk3.setTypeface(tf);
		 atk4.setTypeface(tf);
		 
		 atk1.setBackgroundResource(android.R.drawable.menuitem_background);
		 atk1.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				TextView text = (TextView) v.findViewById(R.id.attack1);
				final Intent intent = new Intent(TrainerActivity.this,MoveActivity.class);
				intent.putExtra("com.protodex.masterdex.move", text.getText());
				startActivity(intent);
				
			}
			 
		 });
		 atk2.setBackgroundResource(android.R.drawable.menuitem_background);
		 atk2.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					TextView text = (TextView) v.findViewById(R.id.attack2);
					final Intent intent = new Intent(TrainerActivity.this,MoveActivity.class);
					intent.putExtra("com.protodex.masterdex.move", text.getText());
					startActivity(intent);
					
				}
				 
			 });
		 atk3.setBackgroundResource(android.R.drawable.menuitem_background);
		 atk3.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					TextView text = (TextView) v.findViewById(R.id.attack3);
					final Intent intent = new Intent(TrainerActivity.this,MoveActivity.class);
					intent.putExtra("com.protodex.masterdex.move", text.getText());
					startActivity(intent);
					
				}
				 
			 });
		 atk4.setBackgroundResource(android.R.drawable.menuitem_background);
		 atk4.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					TextView text = (TextView) v.findViewById(R.id.attack4);
					final Intent intent = new Intent(TrainerActivity.this,MoveActivity.class);
					intent.putExtra("com.protodex.masterdex.move", text.getText());
					startActivity(intent);
					
				}
				 
			 });
		 
		 
//		 move.setText(replaceString);
//		 move.setTypeface(tf);
		 pkName.setText(tp.getPokemon());
		 pkName.setTypeface(tf);
		 //String pokemonIcon = "icon_"+(tp.getPokemon().toLowerCase());
		 String pokemonIcon = tp.getIcon().replace(".png", "");
		 int pkID = getResources().getIdentifier(pokemonIcon, "drawable", "com.protodx.masterdex");
		 pk.setImageResource(pkID);
		 final LinearLayout pkSection = (LinearLayout)child.findViewById(R.id.pkSection);
		 pkSection.setClickable(true);
		 pkSection.setFocusable(true);
		 pkSection.setBackgroundResource(android.R.drawable.menuitem_background);
		 final String name = tp.getPokemon();
		 pkSection.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				
				final Intent intent = new Intent(TrainerActivity.this,PokedexActivity.class);
				intent.putExtra("com.protodex.masterdex.pokemon",name);
		        startActivity(intent);
				
			}});
		
		 return child;
	 }
	 private void downloadImage(String URI, ImageView image){
		 DL = new ImageDownloader();
		 ImageView wheel = (ImageView) findViewById(R.id.wheel);
		 wheel.setBackgroundResource(R.drawable.wheelanimation);
		 final AnimationDrawable frameAnimation = (AnimationDrawable) wheel.getBackground();
         wheel.post(frameAnimation);
		 DL.download(URI, image, wheel);
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
			DL.clearCache();
	    	DL.resetPurgeTimer();
			super.onPause();
		}
		@Override
		protected void onDestroy() {
		    super.onDestroy();
		    if (dao != null) {
		    	dao.close();
		    }
		    DL.clearCache();
	    	DL.resetPurgeTimer();
		    unbindDrawables(findViewById(R.id.root));
		    System.gc();
		}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event)
		{
		    if ((keyCode == KeyEvent.KEYCODE_BACK))
		    {
		    	DL.clearCache();
		    	DL.resetPurgeTimer();
		    	//unbindDrawables(findViewById(R.id.root));
			    //System.gc();
		    	finish();
		    }
		    return super.onKeyDown(keyCode, event);
		}
		 private void unbindDrawables(View view) {
		        if (view.getBackground() != null) {
		        view.getBackground().setCallback(null);
		        }
		        if (view instanceof ViewGroup) {
		            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
		            unbindDrawables(((ViewGroup) view).getChildAt(i));
		            }
		        ((ViewGroup) view).removeAllViews();
		        }
		        super.onDestroy();
			}
}