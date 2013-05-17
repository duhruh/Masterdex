package com.protodx.masterdex;

import java.sql.SQLException;
import java.util.Vector;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

public class ListActivity extends Activity   {
	ListView list;
    LazyAdapter adapter;
    private PokemonDAO dao;
    private EditText filterText;
    LazyAdapter filterAdapter;
    String[] pokemon;
    String[] pokemonIcon;
    String[] searchPokemon;
    String[] searchPokemonIcon;
    private Vector<Pokemon> AllPokemon = new Vector<Pokemon>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        dao = new PokemonDAO(this);
        
        try{
        	dao.open();
        	AllPokemon = dao.getPokemonList();
        } catch (Exception e) {
			e.printStackTrace();
		}dao.close();
//		pokemon = new String[AllPokemon.size()];
//		pokemonIcon = new String[AllPokemon.size()];
//		for(int i = 0; i < AllPokemon.size(); i++){
//			pokemon[i] = AllPokemon.get(i).getName();
//			pokemonIcon[i] = AllPokemon.get(i).getIcon();
//		}
        Resources resource = getResources();
        list=(ListView)findViewById(R.id.list);
        adapter=new LazyAdapter(this, AllPokemon);
        adapter.setResource(resource);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
        
        filterText = (EditText) findViewById(R.id.searchbox);
        filterText.addTextChangedListener(filterTextWatcher);

    }
    
    private TextWatcher filterTextWatcher  = new TextWatcher(){

		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			adapter.getFilter().filter(s);
			
		}};
    
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
	    filterText.removeTextChangedListener(filterTextWatcher);
	}

}
