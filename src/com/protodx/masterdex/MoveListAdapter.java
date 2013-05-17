package com.protodx.masterdex;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class MoveListAdapter extends BaseAdapter implements Filterable {
	private Vector<Move> MoveList;
	private static Vector<Move> sMoveList;
	private Activity activity;
	private static LayoutInflater inflater = null;
	private Resources resource;
	private Filter filter;
	
	@SuppressWarnings("static-access")
	public MoveListAdapter(Activity activity, Vector<Move> MoveList){
		this.MoveList = new Vector<Move>();
		this.MoveList.addAll(MoveList);
		this.sMoveList = new Vector<Move>();
		sMoveList.addAll(this.MoveList);
		this.activity = activity;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		getFilter();
	}
	
	public int getCount() {
		return sMoveList.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
	public void setResource(Resources resource){
    	this.resource = resource;
    }
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.item, null);
        ImageView image=(ImageView)view.findViewById(R.id.image);
        
        String type = sMoveList.get(position).getType().toLowerCase().substring(0, 3);
        int picID = resource.getIdentifier("type_"+type, "drawable", "com.protodx.masterdex");
        image.setImageResource(picID);
        
        TextView text=(TextView)view.findViewById(R.id.text);
        text.setText(sMoveList.get(position).getName());
        Typeface tf = Typeface.createFromAsset(text.getContext().getAssets(),"fonts/Roboto-Light.ttf");
        text.setTypeface(tf);
		
        view.setClickable(true);
        view.setFocusable(true);
        view.setBackgroundResource(android.R.drawable.menuitem_background);
        view.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				TextView text = (TextView) v.findViewById(R.id.text);
				final Intent intent = new Intent(activity,MoveActivity.class);
				intent.putExtra("com.protodex.masterdex.move", text.getText());
		        activity.startActivity(intent);
			}});
        
        return view;
	}

	public Filter getFilter() {
		if(filter == null)
			filter = new MoveFilter();
		return filter;
	}
	private class MoveFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();

			if(constraint != null && constraint.length() > 0){
				constraint = constraint.toString().toUpperCase();
				Vector<Move> filt = new Vector<Move>();
				Vector<Move> tempList = new Vector<Move>();
				tempList.addAll(MoveList);
				int size = tempList.size();
				for(int i = 0; i < size; i++){
					Move move = tempList.get(i);
					if(move.getName().toUpperCase().contains(constraint) || move.getType().contains(constraint))
						filt.add(move);
				}
				results.values = filt;
				results.count = filt.size();
			}else{
				synchronized(this){
					results.values = MoveList;
					results.count = MoveList.size();
				}
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			sMoveList = ((Vector<Move>) results.values);
			notifyDataSetChanged();
			
		}
		
	}
}
