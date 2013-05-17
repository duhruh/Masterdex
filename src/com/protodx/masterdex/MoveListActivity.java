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

public class MoveListActivity extends Activity {
	private PokemonDAO dao;
	private Vector<Move> MoveList = new Vector<Move>();
	private MoveListAdapter ListAdapter;
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
			MoveList = dao.getMoveList();
		} catch (SQLException e) {
			e.printStackTrace();
		}dao.close();
		
		list = (ListView)findViewById(R.id.list);
		Resources resource = getResources();
		ListAdapter = new MoveListAdapter(this, MoveList);
		ListAdapter.setResource(resource);
		list.setAdapter(ListAdapter);
		list.setItemsCanFocus(true);
		
		filterText = (EditText) findViewById(R.id.searchbox);
        filterText.addTextChangedListener(filterTextWatcher);
        
	}
	private TextWatcher filterTextWatcher  = new TextWatcher(){

		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			ListAdapter.getFilter().filter(arg0);
			
		}
		
	};
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
