package com.protodx.masterdex;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyPagerAdapter extends PagerAdapter  {
	private ArrayList<Pokemon> PokemonList = new ArrayList<Pokemon>();
	private Activity context;
	private static LayoutInflater inflater = null;
	private Typeface tf;
	private final String TYPEFACE = "fonts/Roboto-Light.ttf";
	private Resources resource;
	
	private TableRow tr;
	
	private TextView pokemonName;
	private TextView bio;
	private TextView type;
	private TextView height;
	private TextView weight;
	private TextView hp;
	private TextView defense;
	private TextView attack;
	private TextView specialAtk;
	private TextView specialDef;
	private TextView speed;
	private TextView species;
	private TextView weakness;
	private TextView statTotal;
	private TextView abilities;
	private TextView pronunce;
	
	private LinearLayout bioSection;
	private LinearLayout levelSection;
	private LinearLayout evolutionSection;
	private LinearLayout effortsvalues;
	private LinearLayout catchLocations;
	
	private ImageView evolvesFrom;
	private ImageView evolvesInto;
	private ImageView topBar;
	private RelativeLayout topBarLayout;
	private ImageView thumb;
	private View view;
	public ImageDownloader DL;
	
	private TextToSpeech myTTs;
	private Dexter dex;
	public MyPagerAdapter(Activity context, ArrayList<Pokemon> PokemonList){
		this.PokemonList.addAll(PokemonList);
		this.context = context;
		myTTs = new TextToSpeech(context, null);
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return PokemonList.size();
	}
	
	@Override
    public void destroyItem(View collection, int position, Object view) {
            ((ViewPager) collection).removeView((View) view);
            unbindDrawables((View)view);
		    System.gc();
    }

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 ==((View)arg1);
	}
	
	@Override
    public Object instantiateItem(View collection, final int position) {
            //View view;
//            if(PokemonList.get(position).getName().contains("Eevee"))
//            	view = inflater.inflate(R.layout.eevee, null);
//            else
            	view  = inflater.inflate(R.layout.pokedex, null);
            
            pokemonName = (TextView) view.findViewById(R.id.pokemon);
	        bio = (TextView) view.findViewById(R.id.bio);
	        type = (TextView) view.findViewById(R.id.type);
	        height = (TextView) view.findViewById(R.id.height);
	        weight = (TextView) view.findViewById(R.id.weight);
	    	hp = (TextView) view.findViewById(R.id.hp);
	    	defense = (TextView) view.findViewById(R.id.defense);
	    	attack = (TextView) view.findViewById(R.id.attack);
	    	specialAtk = (TextView) view.findViewById(R.id.specialatk);
	    	specialDef = (TextView) view.findViewById(R.id.specialdef);
	    	speed = (TextView) view.findViewById(R.id.speed);
	    	species = (TextView) view.findViewById(R.id.species);
	    	weakness = (TextView) view.findViewById(R.id.weakness);
	    	statTotal = (TextView) view.findViewById(R.id.stattotal);
	    	abilities = (TextView) view.findViewById(R.id.ability);
	    	pronunce = (TextView) view.findViewById(R.id.pronunce);
	    	evolvesFrom = (ImageView) view.findViewById(R.id.ImageView01);
	        evolvesInto = (ImageView) view.findViewById(R.id.imageView2);
	       // topBar = (ImageView) view.findViewById(R.id.image);
	        topBarLayout = (RelativeLayout)view.findViewById(R.id.topBarLayout);
	        thumb = (ImageView) view.findViewById(R.id.thumb);
	        thumb.setClickable(true);
	        
	        
	        bioSection = (LinearLayout) view.findViewById(R.id.bioSection);
	        levelSection = (LinearLayout) view.findViewById(R.id.leveling);
	        evolutionSection = (LinearLayout) view.findViewById(R.id.evolutionSet);
	        effortsvalues = (LinearLayout) view.findViewById(R.id.effortsvalues);
	        catchLocations = (LinearLayout) view.findViewById(R.id.catchlocations);
	        tr = (TableRow) view.findViewById(R.id.iconRow_0);
	        
	        RelativeLayout topBar = (RelativeLayout) view.findViewById(R.id.topBarLayout);
	        topBar.setBackgroundColor(Color.TRANSPARENT);
	        
	        resource = context.getResources();
	        tf = Typeface.createFromAsset(resource.getAssets(),TYPEFACE);
	        setPokedexEntry(PokemonList.get(position),view);
            
            ((ViewPager) collection).addView(view,0);
            dex = new Dexter(context,PokemonList.get(position).getBio(),myTTs);
            thumb.setOnClickListener(dex);
            return view;
    }
	public void shutdownTTS(){
		if(dex.myTTS != null){
			dex.shutdownTTS();
		}
	}
	private void setPokedexEntry(Pokemon pokemon, View view){
		setLayout(pokemon);
		if(!pokemon.getName().contains("Eevee"))
			setIcons(pokemon);
		setText(pokemon);
		setImage(pokemon,view);
		setTypeFace();
		setTags(view);
		if(pokemon.getEffortsValue() != null)
			setEV(pokemon);
		setEvolutionSection(pokemon);
		setLocation(pokemon);
	}
	private void setLocation(Pokemon pokemon){
		View parent = inflater.inflate(R.layout.locations, null);
		LinearLayout catchlocation = (LinearLayout)parent.findViewById(R.id.catchlocations); 
		
		if(pokemon.getLocation().getRedBlue().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getRedBlue());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Red");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("Blue");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getYellow().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getYellow());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Yellow");
			red.setTypeface(tf);
			
			red.setTextColor(Color.BLACK);
			game.addView(red);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getGoldSilver().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getGoldSilver());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Gold");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("Silver");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getCrystal().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getCrystal());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Crystal");
			red.setTypeface(tf);
			
			red.setTextColor(Color.BLACK);
			game.addView(red);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getRubySapphire().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getRubySapphire());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Ruby");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("Sapphire");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getFireRedLeafGreen().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getFireRedLeafGreen());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Fire Red");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("Leaf Green");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getEmerald().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getEmerald());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Emerald");
			red.setTypeface(tf);
			
			red.setTextColor(Color.BLACK);
			game.addView(red);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getDiamondPearl().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getDiamondPearl());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Diamond");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("Pearl");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getPlatinum().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getPlatinum());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Platinum");
			red.setTypeface(tf);
			
			red.setTextColor(Color.BLACK);
			game.addView(red);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getHeartGoldSoulSilver().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getHeartGoldSoulSilver());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Heart Gold");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("Soul Silver");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		if(pokemon.getLocation().getBlackWhite().length() > 1){
			View child = inflater.inflate(R.layout.location, null);
			LinearLayout game = (LinearLayout)child.findViewById(R.id.games);
			TextView local = (TextView)child.findViewById(R.id.loco);
			local.setText(pokemon.getLocation().getBlackWhite());
			local.setTypeface(tf);
			TextView red = new TextView(context);
			red.setText("Black");
			red.setTypeface(tf);
			TextView blue = new TextView(context);
			blue.setText("White");
			blue.setTypeface(tf);
			blue.setTextColor(Color.BLACK);
			red.setTextColor(Color.BLACK);
			game.addView(red);
			game.addView(blue);
			catchlocation.addView(child);
		}
		catchLocations.addView(parent);
	}
	private void setEvolutionSection(Pokemon pokemon){
		View child = null;
		if(pokemon.getEvolutionLayout().contentEquals("stand") || pokemon.getEvolutionLayout().contentEquals("single2")){
			if(pokemon.getEvolutionLayout().contentEquals("stand"))
				child = inflater.inflate(R.layout.standevolution, null);
			else if(pokemon.getEvolutionLayout().contentEquals("single2"))
				child = inflater.inflate(R.layout.single2evolution, null);
			
			ImageView evo0 = (ImageView)child.findViewById(R.id.evo0);
			ImageView evo1 = (ImageView)child.findViewById(R.id.evo1);
			ImageView evo2 = (ImageView)child.findViewById(R.id.evo2);
			TextView evo0name = (TextView)child.findViewById(R.id.evo0name);
			TextView evo1name = (TextView)child.findViewById(R.id.evo1name);
			TextView evo2name = (TextView)child.findViewById(R.id.evo2name);
			TextView method0 = (TextView)child.findViewById(R.id.method0);
			TextView method1 = (TextView)child.findViewById(R.id.method1);
			
			String[] evSet = pokemon.getEvolutionSet().split("/",3);
			String[] method = pokemon.getMethod().split("/",2);
			int evo0Id;
			int evo1Id;
			int evo2Id;
			if(evSet.length == PokemonList.size()){
				evo0Id =  resource.getIdentifier(PokemonList.get(0).getIcon().replace(".png", ""), "drawable", "com.protodx.masterdex");
				evo1Id =  resource.getIdentifier(PokemonList.get(1).getIcon().replace(".png", ""), "drawable", "com.protodx.masterdex");
				evo2Id =  resource.getIdentifier(PokemonList.get(2).getIcon().replace(".png", ""), "drawable", "com.protodx.masterdex");
			}else{
				evo0Id =  resource.getIdentifier("icon_"+evSet[0].toLowerCase(), "drawable", "com.protodx.masterdex");
				evo1Id =  resource.getIdentifier("icon_"+evSet[1].toLowerCase(), "drawable", "com.protodx.masterdex");
				evo2Id =  resource.getIdentifier("icon_"+evSet[2].toLowerCase(), "drawable", "com.protodx.masterdex");
			}
			evo0.setImageResource(evo0Id);
			evo1.setImageResource(evo1Id);
			evo2.setImageResource(evo2Id);
			evo0name.setText(evSet[0]);
			evo1name.setText(evSet[1]);
			evo2name.setText(evSet[2]);
			method0.setText(method[0]);
			method1.setText(method[1]);
			evo0name.setTypeface(tf);
			evo1name.setTypeface(tf);
			evo2name.setTypeface(tf);
			method0.setTypeface(tf);
			method1.setTypeface(tf);
			evolutionSection.addView(child);
		}
		else if(pokemon.getEvolutionLayout().contentEquals("single")){
			child = inflater.inflate(R.layout.singleevolution, null);
			ImageView evo0 = (ImageView)child.findViewById(R.id.evo0);
			ImageView evo1 = (ImageView)child.findViewById(R.id.evo1);
			TextView evo0name = (TextView)child.findViewById(R.id.evo0name);
			TextView evo1name = (TextView)child.findViewById(R.id.evo1name);
			TextView method0 = (TextView)child.findViewById(R.id.method0);
			int evo0Id =  resource.getIdentifier(PokemonList.get(0).getIcon().replace(".png", ""), "drawable", "com.protodx.masterdex");
			int evo1Id =  resource.getIdentifier(PokemonList.get(1).getIcon().replace(".png", ""), "drawable", "com.protodx.masterdex");
			evo0.setImageResource(evo0Id);
			evo1.setImageResource(evo1Id);
			evo0name.setText(PokemonList.get(0).getName());
			evo1name.setText(PokemonList.get(1).getName());
			method0.setText(pokemon.getMethod());
			evo0name.setTypeface(tf);
			evo1name.setTypeface(tf);
			method0.setTypeface(tf);
			evolutionSection.addView(child);
		}
		else if(pokemon.getEvolutionLayout().contentEquals("stand2") || pokemon.getEvolutionLayout().contentEquals("single3")){
			if(pokemon.getEvolutionLayout().contentEquals("stand2"))
				child = inflater.inflate(R.layout.stand2evolution, null);
			else if(pokemon.getEvolutionLayout().contentEquals("single3"))
				child = inflater.inflate(R.layout.single3evolution, null);
			ImageView evo0 = (ImageView)child.findViewById(R.id.evo0);
			ImageView evo1 = (ImageView)child.findViewById(R.id.evo1);
			ImageView evo2 = (ImageView)child.findViewById(R.id.evo2);
			ImageView evo3 = (ImageView)child.findViewById(R.id.evo3);
			TextView evo0name = (TextView)child.findViewById(R.id.evo0name);
			TextView evo1name = (TextView)child.findViewById(R.id.evo1name);
			TextView evo2name = (TextView)child.findViewById(R.id.evo2name);
			TextView evo3name = (TextView)child.findViewById(R.id.evo3name);
			TextView method0 = (TextView)child.findViewById(R.id.method0);
			TextView method1 = (TextView)child.findViewById(R.id.method1);
			TextView method2 = (TextView)child.findViewById(R.id.method2);
			
			String[] evSet = pokemon.getEvolutionSet().split("/", 4);
			String[] method = pokemon.getMethod().split("/", 3);
			
			int evo0Id =  resource.getIdentifier("icon_"+evSet[0].toLowerCase(), "drawable", "com.protodx.masterdex");
			int evo1Id =  resource.getIdentifier("icon_"+evSet[1].toLowerCase(), "drawable", "com.protodx.masterdex");
			int evo2Id =  resource.getIdentifier("icon_"+evSet[2].toLowerCase(), "drawable", "com.protodx.masterdex");
			int evo3Id =  resource.getIdentifier("icon_"+evSet[3].toLowerCase(), "drawable", "com.protodx.masterdex");
			evo0.setImageResource(evo0Id);
			evo1.setImageResource(evo1Id);
			evo2.setImageResource(evo2Id);
			evo3.setImageResource(evo3Id);
			evo0name.setText(evSet[0]);
			evo1name.setText(evSet[1]);
			evo2name.setText(evSet[2]);
			evo3name.setText(evSet[3]);
			method0.setText(method[0]);
			method1.setText(method[1]);
			method2.setText(method[2]);
			evo0name.setTypeface(tf);
			evo1name.setTypeface(tf);
			evo2name.setTypeface(tf);
			evo3name.setTypeface(tf);
			method0.setTypeface(tf);
			method1.setTypeface(tf);
			method2.setTypeface(tf);
			evolutionSection.addView(child);
		}
//		else if(pokemon.getEvolutionLayout().contentEquals("single2")){
//			child = inflater.inflate(R.layout.single2evolution, null);
//		}
//		else if(pokemon.getEvolutionLayout().contentEquals("single3"))
//			child = inflater.inflate(R.layout.single3evolution, null);
		else if(pokemon.getEvolutionLayout().contentEquals("single4")){
			child = inflater.inflate(R.layout.single4evolution, null);
			evolutionSection.addView(child);
		}
		else if(pokemon.getEvolutionLayout().contentEquals("eevee")){
			child = inflater.inflate(R.layout.eeveevoultion, null);
			evolutionSection.addView(child);
		}
		else if(pokemon.getEvolutionLayout().contentEquals("standbranch")){
			child = inflater.inflate(R.layout.standbranchevoultion, null);
			evolutionSection.addView(child);
		}
		else if(pokemon.getEvolutionLayout().contentEquals("singleplus")){
			child = inflater.inflate(R.layout.singleplus, null);
			evolutionSection.addView(child);
		}
	}
	private void setTags(View view){
		TextView evolvesFrom = (TextView)view.findViewById(R.id.evolvesFromTag);
		TextView evolvesTo = (TextView)view.findViewById(R.id.evolvesToTag);
		TextView type = (TextView)view.findViewById(R.id.typetag);
		TextView species = (TextView)view.findViewById(R.id.speciestag);
		TextView height = (TextView)view.findViewById(R.id.heighttag);
		TextView weight = (TextView)view.findViewById(R.id.weighttag);
		TextView abilities = (TextView)view.findViewById(R.id.abiltag);
		TextView weakness = (TextView)view.findViewById(R.id.weaktag);
		TextView base = (TextView)view.findViewById(R.id.basestatstext);
		TextView hp = (TextView)view.findViewById(R.id.hptag);
		TextView attack = (TextView)view.findViewById(R.id.attacktag);
		TextView defense = (TextView)view.findViewById(R.id.defensetag);
		TextView stat = (TextView)view.findViewById(R.id.stattotaltag);
		TextView specialatk = (TextView)view.findViewById(R.id.specialatktag);
		TextView specialdef = (TextView)view.findViewById(R.id.specialdeftag);
		TextView speed = (TextView)view.findViewById(R.id.speedtag);
		type.setTypeface(tf);
    	height.setTypeface(tf);
        weight.setTypeface(tf);
    	hp.setTypeface(tf);
    	defense.setTypeface(tf);
    	attack.setTypeface(tf);
    	specialatk.setTypeface(tf);
    	specialdef.setTypeface(tf);
    	speed.setTypeface(tf);
    	species.setTypeface(tf);
    	weakness.setTypeface(tf);
    	type.setTypeface(tf);
    	abilities.setTypeface(tf);
    	stat.setTypeface(tf);
    	base.setTypeface(tf);
    	evolvesFrom.setTypeface(tf);
    	evolvesTo.setTypeface(tf);
	}
	private void setLevelChart(Pokemon pokemon){
		View child = inflater.inflate(R.layout.levelingtable, null);
		TextView rbyTag = (TextView)child.findViewById(R.id.rby);
		TextView gscTag = (TextView) child.findViewById(R.id.gsc);
		TextView rseTag = (TextView) child.findViewById(R.id.rse);
		TextView frlgTag = (TextView) child.findViewById(R.id.frlg);
		TextView dpptTag = (TextView) child.findViewById(R.id.dppt);
		TextView hgssTag = (TextView) child.findViewById(R.id.hgss);
		TextView bwTag = (TextView) child.findViewById(R.id.bw);
		TextView moveTag = (TextView) child.findViewById(R.id.move);
		rbyTag.setTypeface(tf);
		gscTag.setTypeface(tf);
		rseTag.setTypeface(tf);
		frlgTag.setTypeface(tf);
		dpptTag.setTypeface(tf);
		hgssTag.setTypeface(tf);
		bwTag.setTypeface(tf);
		moveTag.setTypeface(tf);
				
		TableLayout table = (TableLayout) child.findViewById(R.id.level_secion);
		ArrayList<Learnset> list = pokemon.getLearnset();
		for(Learnset move: list){
			View child_child = inflater.inflate(R.layout.levelingrow, null);
			TextView rby = (TextView) child_child.findViewById(R.id.rbycol);
			TextView gsc = (TextView) child_child.findViewById(R.id.gsccol);
			TextView rse = (TextView) child_child.findViewById(R.id.rsecol);
			TextView frlg = (TextView) child_child.findViewById(R.id.frlgcol);
			TextView dppt = (TextView) child_child.findViewById(R.id.dpptcol);
			TextView hgss = (TextView) child_child.findViewById(R.id.hgsscol);
			TextView bw = (TextView) child_child.findViewById(R.id.bwcol);
			TextView attack = (TextView) child_child.findViewById(R.id.move);
			
			if(move.getRBY() != 0)
				rby.setText(String.valueOf(move.getRBY()));
			else
				rby.setText("-");
			if(move.getGSC() != 0)
				gsc.setText(String.valueOf(move.getGSC()));
			else
				gsc.setText("-");
			if(move.getRSE() != 0)
				rse.setText(String.valueOf(move.getRSE()));
			else
				rse.setText("-");
			if(move.getFRLG() != 0)
				frlg.setText(String.valueOf(move.getFRLG()));
			else
				frlg.setText("-");
			if(move.getDPPT() != 0)
				dppt.setText(String.valueOf(move.getDPPT()));
			else
				dppt.setText("-");
			if(move.getHGSS() != 0)
				hgss.setText(String.valueOf(move.getHGSS()));
			else
				hgss.setText("-");
			if(move.getBW() != 0)
				bw.setText(String.valueOf(move.getBW()));
			else
				bw.setText("-");
			rby.setTypeface(tf);
			gsc.setTypeface(tf);
			rse.setTypeface(tf);
			frlg.setTypeface(tf);
			dppt.setTypeface(tf);
			hgss.setTypeface(tf);
			bw.setTypeface(tf);
			attack.setTypeface(tf);
			
			attack.setText(move.getMove());
			attack.setClickable(true);
			attack.setBackgroundResource(android.R.drawable.menuitem_background);
			attack.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					TextView text = (TextView) v.findViewById(R.id.move);
					final Intent intent = new Intent(context,MoveActivity.class);
					intent.putExtra("com.protodex.masterdex.move", text.getText());
					context.startActivity(intent);
				}});
			table.addView(child_child);
		}
		levelSection.addView(child);
	}
	private void setText(Pokemon pokemon){
		if(pokemon.getName().isEmpty()){
			pokemonName.setText("NO POKEMON FOUND!");
        	bio.setText("BIO NOT FOUND!");
        	
        }else{
        	setLevelChart(pokemon);
        	pokemonName.setText(pokemon.getName());
        	View child = inflater.inflate(R.layout.bio,null);
        	TextView childBio = (TextView)child.findViewById(R.id.bio);
        	childBio.setText(pokemon.getBio());
        	childBio.setTypeface(tf);
        	bioSection.addView(child);
        	//bio.setText(pokemon.getBio());
        	type.setText(pokemon.getType());
        	height.setText(pokemon.getHeight());
	        weight.setText(pokemon.getWeight());
	    	hp.setText(String.valueOf(pokemon.getHp()));
	    	defense.setText(String.valueOf(pokemon.getDefence()));
	    	attack.setText(String.valueOf(pokemon.getAttack()));
	    	specialAtk.setText(String.valueOf(pokemon.getSpAtk()));
	    	specialDef.setText(String.valueOf(pokemon.getSpDef()));
	    	speed.setText(String.valueOf(pokemon.getSpeed()));
	    	species.setText(pokemon.getSpecies());
	    	weakness.setText(pokemon.getWeakness());
	    	
	    	String[] weak = pokemon.getWeakness().split("/");
	    	
	    	for(String type : weak){
	    		ImageView icon = new ImageView(context);
	    		//float GESTURE_THRESHOLD_DP = 3.4f;
	    		//final float scale = context.getResources().getDisplayMetrics().density;
	    		//int size = (int) (GESTURE_THRESHOLD_DP * scale + 0.5f);
	    		icon.setImageResource(resource.getIdentifier("type_"+type, "drawable", "com.protodx.masterdex"));
	    		icon.setLayoutParams(new LayoutParams(20,20));
	    		tr.addView(icon); 
	    	}
	    	
	    	type.setText(pokemon.getType());
        	abilities.setText(pokemon.getAbilities().replace("/", "\n"));
        	statTotal.setText(String.valueOf(pokemon.getStatTotal()));
        	pronunce.setText("("+pokemon.getPronunce()+")");
        }
	}
	public void setTypeFace(){
		pokemonName.setTypeface(tf);
    	//bio.setTypeface(tf);
    	type.setTypeface(tf);
    	height.setTypeface(tf);
        weight.setTypeface(tf);
    	hp.setTypeface(tf);
    	defense.setTypeface(tf);
    	attack.setTypeface(tf);
    	specialAtk.setTypeface(tf);
    	specialDef.setTypeface(tf);
    	speed.setTypeface(tf);
    	species.setTypeface(tf);
    	weakness.setTypeface(tf);
    	type.setTypeface(tf);
    	abilities.setTypeface(tf);
    	statTotal.setTypeface(tf);
    	pronunce.setTypeface(tf);
	}
	private void setLayout(Pokemon pokemon){
		int layoutId = resource.getIdentifier(pokemon.getLayout().replace(".png", ""), "drawable", "com.protodx.masterdex");
		//topBar.setImageResource(layoutId);
		topBarLayout.setBackgroundResource(layoutId);
	}
	private void setIcons(Pokemon pokemon){
		int smallIconFId;
        int smallIconIId;
        Pokemon pokemonEFIcon = new Pokemon();
        Pokemon pokemonETIcon = new Pokemon();
        PokemonDAO dao = new PokemonDAO(context);

        if(pokemon.getEvolvesFrom().equals("None"))
        	smallIconFId = resource.getIdentifier("small_na", "drawable", "com.protodx.masterdex");
        else{
        	String[] evolvesFromName = new String[]{pokemon.getEvolvesFrom()};
        	
        	try {
        		dao.open();
				pokemonEFIcon = dao.getPokemonIcon(evolvesFromName);
			} catch (Exception e) {e.printStackTrace();}
        	dao.close();
        	
        	String temp = pokemonEFIcon.getIcon().replace("icon_", "small_");
        	temp = temp.replace(".png", "");
        	smallIconFId = resource.getIdentifier(temp, "drawable", "com.protodx.masterdex");
        }
        if(pokemon.getEvolvesInto().equals("None"))
        	smallIconIId = resource.getIdentifier("small_na", "drawable", "com.protodx.masterdex");
        else{
        	String[] evolvesIntoName = new String[]{pokemon.getEvolvesInto()};
        	
        	try {
        		dao.open();
				pokemonETIcon = dao.getPokemonIcon(evolvesIntoName);
			} catch (Exception e) {e.printStackTrace();}
        	dao.close();
        	String temp = pokemonETIcon.getIcon().replace("icon_", "small_");
        	temp = temp.replace(".png", "");
        	smallIconIId = resource.getIdentifier(temp, "drawable", "com.protodx.masterdex");
        }
        
        evolvesFrom.setImageResource(smallIconFId);
    	evolvesInto.setImageResource(smallIconIId);
	}
	private void setImage(final Pokemon pokemon, View view){
        String url = "http://protodx.com/pokemon/"+pokemon.getPic();
        downloadImage(url, view);
	}
	private void downloadImage(String url,View view){
		ImageView wheel = (ImageView) view.findViewById(R.id.wheel);
		wheel.setBackgroundResource(R.drawable.wheelanimation);
		final AnimationDrawable frameAnimation = (AnimationDrawable) wheel.getBackground();
        wheel.post(frameAnimation);
        
        DL = new ImageDownloader();
        DL.download(url, thumb, wheel);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	unbindDrawables(view.findViewById(R.id.pager));
		    System.gc();
	    	//shutdownTTS();
	    }
	    return context.onKeyDown(keyCode, event);
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
        
	}
	private void setEV(Pokemon pokemon){
		View child = inflater.inflate(R.layout.ev, null);
		TextView hptag = (TextView)child.findViewById(R.id.hptag);
		TextView hp = (TextView)child.findViewById(R.id.hp);
		TextView attacktag = (TextView)child.findViewById(R.id.attacktag);
		TextView attack = (TextView)child.findViewById(R.id.attack);
		TextView defensetag = (TextView)child.findViewById(R.id.defensetag);
		TextView defense = (TextView)child.findViewById(R.id.defense);
		TextView specialatktag = (TextView)child.findViewById(R.id.specialatktag);
		TextView specialatk = (TextView)child.findViewById(R.id.specialatk);
		TextView specialdeftag = (TextView)child.findViewById(R.id.specialdeftag);
		TextView specialdef = (TextView)child.findViewById(R.id.specialdef);
		TextView speedtag = (TextView)child.findViewById(R.id.speedtag);
		TextView speed = (TextView)child.findViewById(R.id.speed);
//		TextView ev = (TextView)child.findViewById(R.id.ev);
		
		hp.setText(String.valueOf(pokemon.getEffortsValue().getHP()));
		attack.setText(String.valueOf(pokemon.getEffortsValue().getAttack()));
		defense.setText(String.valueOf(pokemon.getEffortsValue().getDefense()));
		specialatk.setText(String.valueOf(pokemon.getEffortsValue().getSpecialAtk()));
		specialdef.setText(String.valueOf(pokemon.getEffortsValue().getSpecialDef()));
		speed.setText(String.valueOf(pokemon.getEffortsValue().getSpeed()));
		
//		ev.setTypeface(tf);
		hptag.setTypeface(tf);
		attacktag.setTypeface(tf);
		defensetag.setTypeface(tf);
		specialatktag.setTypeface(tf);
		specialdeftag.setTypeface(tf);
		speedtag.setTypeface(tf);
		hp.setTypeface(tf);
		attack.setTypeface(tf);
		defense.setTypeface(tf);
		specialatk.setTypeface(tf);
		specialdef.setTypeface(tf);
		speed.setTypeface(tf);
		effortsvalues.addView(child);
		
	}
}
