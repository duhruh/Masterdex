package com.protodx.masterdex;

import java.sql.SQLException;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


public class PokedexActivity extends Activity {
	private PokemonDAO dao;
	private Pokemon pokemon = new Pokemon();
	
	private ViewPager viewPager;
	private MyPagerAdapter adapter;
	private ArrayList<Pokemon> PokemonList = new ArrayList<Pokemon>();
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.viewpager);	 
	        
            
	        dao = new PokemonDAO(this);
	        String[] name = new String[1];
	        int next = 0;
	        name [0] = getIntent().getExtras().getString("com.protodex.masterdex.pokemon");
	        next = getIntent().getExtras().getInt("com.example.helloandroid.pokemonID");
	        
//	        if(next > 0){
//	        	try {
//		        	dao.open();
//		        	String[] _id = new String[]{String.valueOf(next)};
//					pokemon = dao.getPokemonById(_id);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}dao.close();
//	        }else{
	        	try {
		        	dao.open();
					pokemon = dao.getPokemon(name);
					pokemon = dao.getLearnset(pokemon);
					pokemon = dao.getEffortsValue(pokemon);
					pokemon = dao.getLocations(pokemon);
				} catch (Exception e) {
					e.printStackTrace();
					Log.d("Pokemon Name", name[0]);
				}dao.close();
//	        }
	        //EF None ET None
	        int position = 0;
	        String EV = pokemon.getName();
	        if(!EV.contains("Eevee") && !EV.contains("Jolteon") && !EV.contains("Flareon") && !EV.contains("Vaporeon") && !EV.contains("Espeon") && !EV.contains("Umbreon") && !EV.contains("Leafeon") && !EV.contains("Glaceon")){
		        if(pokemon.getEvolvesFrom().contains("None")){
		        	position = 0;
		        	PokemonList.add(pokemon);
		        	while(!pokemon.getEvolvesInto().contains("None")){
		        		String[] tmpPkName = new String[]{pokemon.getEvolvesInto()};
		        		try {
		        			dao.open();
		        			pokemon = dao.getPokemon(tmpPkName);
		        			pokemon = dao.getLearnset(pokemon);
		        			pokemon = dao.getEffortsValue(pokemon);
		        			pokemon = dao.getLocations(pokemon);
						} catch (Exception e) {
							e.printStackTrace();
							Log.d("Evolves From Line:71", tmpPkName[0]);
						}dao.close();
		        		PokemonList.add(pokemon);
		        	}
		        }else if(!pokemon.getEvolvesInto().contains("None")){
		        	position = 0;
		        	Pokemon chosenPokemon = new Pokemon();
		        	PokemonList.add(pokemon);
		        	chosenPokemon = pokemon;
		        	while(!pokemon.getEvolvesFrom().contains("None")){
		        		String[] tmpPkName = new String[]{pokemon.getEvolvesFrom()};
		        		try {
		        			dao.open();
		        			pokemon = dao.getPokemon(tmpPkName);
		        			pokemon = dao.getLearnset(pokemon);
		        			pokemon = dao.getEffortsValue(pokemon);
		        			pokemon = dao.getLocations(pokemon);
						} catch (Exception e) {
							e.printStackTrace();
							Log.d("Evolves From Line:89", tmpPkName[0]);
						}dao.close();
		        		PokemonList.add(0, pokemon);
		        		position++;
		        	}
		        	while(!chosenPokemon.getEvolvesInto().contains("None")){
		        		String[] tmpPkName = new String[]{chosenPokemon.getEvolvesInto()};
		        		try {
		        			dao.open();
		        			chosenPokemon = dao.getPokemon(tmpPkName);
		        			chosenPokemon = dao.getLearnset(chosenPokemon);
		        			chosenPokemon = dao.getEffortsValue(chosenPokemon);
		        			chosenPokemon = dao.getLocations(chosenPokemon);
						} catch (Exception e) {
							e.printStackTrace();
							Log.d("Evolves Into Line:103", tmpPkName[0]);
						}dao.close();
		        		PokemonList.add(PokemonList.size(), chosenPokemon);
		        	}
		        }else{
		        	position = 0;
		        	PokemonList.add(pokemon);
		        	while(!pokemon.getEvolvesFrom().contains("None")){
		        		String[] tmpPkName = new String[]{pokemon.getEvolvesFrom()};
		        		try {
		        			dao.open();
		        			pokemon = dao.getPokemon(tmpPkName);
		        			pokemon = dao.getLearnset(pokemon);
		        			pokemon = dao.getEffortsValue(pokemon);
		        			pokemon = dao.getLocations(pokemon);
						} catch (Exception e) {
							e.printStackTrace();
						}dao.close();
		        		PokemonList.add(0,pokemon);
		        		position++;
		        	}
		        }
	        }else{//THIS IS EEVEE'S LIST
	        	String[] eevee = new String[]{"Eevee","Vaporeon","Jolteon","Flareon","Espeon","Umbreon","Leafeon","Glaceon"};
	        	ArrayList<Pokemon> EeveeList = new ArrayList<Pokemon>();
	        	int numEevee = 8;
	        	for(int i = 0; i < numEevee; i++){
		        	try {
		        		dao.open();
						EeveeList.add(dao.getPokemon(new String[]{eevee[i]}));
					} catch (Exception e) {e.printStackTrace();}dao.close();
	        	}
	        	for(int i = 0; i < eevee.length; i++){
	        		try {dao.open();
						EeveeList.set(i, dao.getLearnset(EeveeList.get(i)));
						EeveeList.set(i, dao.getEffortsValue(EeveeList.get(i)));
						EeveeList.set(i, dao.getLocations(EeveeList.get(i)));
					} catch (Exception e) {
						e.printStackTrace();
					}dao.close();
	        		if(eevee[i].contains(EV))
	        			position = i;
	        	}
	        	//PokemonList.addAll(EeveeList);
	        	PokemonList = EeveeList;
	        	//Collections.copy(PokemonList, EeveeList);
	        }
	        viewPager = (ViewPager) findViewById(R.id.pager);
	        adapter = new MyPagerAdapter(this,PokemonList);
	        viewPager.setAdapter(adapter);
	        viewPager.setCurrentItem(position);
	 }
	@Override
	protected void onResume() {
		try {
			dao.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.onResume();
	}
	@Override
	protected void onPause() {
		dao.close();
		//adapter.DL.clearCache();
	    //adapter.DL.resetPurgeTimer();
	   // adapter.shutdownTTS();
		super.onPause();
	}
	@Override
	protected void onDestroy() {
	    
	    adapter.DL.clearCache();
	    adapter.DL.resetPurgeTimer();
	    if (dao != null) {
	    	dao.close();
	    }
	    adapter.shutdownTTS();
	    
	    unbindDrawables(findViewById(R.id.root));
	    System.gc();
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
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	adapter.DL.clearCache();
	    	adapter.DL.resetPurgeTimer();
	    	//adapter.shutdownTTS();
	    	unbindDrawables(findViewById(R.id.root));
		    System.gc();
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
