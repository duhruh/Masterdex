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

public class TrainerListActivity extends Activity {
	private PokemonDAO dao;
	private Vector<Trainer> TrainerList = new Vector<Trainer>();
	private TrainerListAdapter ListAdapter;
	private ListView list;
	private EditText filterText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list);
	    
	    dao = new PokemonDAO(this);
	    try {
			dao.open();
			TrainerList = dao.getTrainerList();
		} catch (SQLException e) {
			e.printStackTrace();
		}dao.close();
		
		list = (ListView)findViewById(R.id.list);
		Resources resource = getResources();
		ListAdapter = new TrainerListAdapter(this, TrainerList);
		ListAdapter.setResource(resource);
		list.setAdapter(ListAdapter);
		list.setItemsCanFocus(true);
		list.setBackgroundResource(android.R.drawable.menuitem_background);
		
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
			ListAdapter.getFilter().filter(s);
			
		}};
		
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
