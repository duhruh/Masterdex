package com.protodx.masterdex;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
public class LazyAdapter extends BaseAdapter implements Filterable {
    
    private Activity activity;
    private final Vector<Pokemon> data;
    private Vector<Pokemon> filteredData;
    private static LayoutInflater inflater=null;
    private Resources resource;
    private Filter filter;
    
    public LazyAdapter(Activity a, Vector<Pokemon> d) {
        activity = a;
        this.data = new Vector<Pokemon>();
        data.addAll(d);
        this.filteredData = new Vector<Pokemon>();
        filteredData.addAll(data);
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getFilter();
    }
    public void setResource(Resources resource){
    	this.resource = resource;
    }
    public int getCount() {
        return filteredData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.item, null);

        ImageView image=(ImageView)view.findViewById(R.id.image);
        int picID = resource.getIdentifier(filteredData.get(position).getIcon().replace(".png", ""), "drawable", "com.protodx.masterdex");
        image.setImageResource(picID);
        
        TextView text=(TextView)view.findViewById(R.id.text);
        text.setText(filteredData.get(position).getName());
        Typeface tf = Typeface.createFromAsset(text.getContext().getAssets(),"fonts/Roboto-Light.ttf");
        text.setTypeface(tf);
        
        TextView number = (TextView)view.findViewById(R.id.number);
        int pkId = filteredData.get(position).getId();
        if(pkId < 10)
        	number.setText("#00"+String.valueOf(pkId));
        else if(pkId > 9 && pkId < 100)
        	number.setText("#0"+String.valueOf(pkId));
        else
        	number.setText("#"+String.valueOf(pkId));
        number.setTypeface(tf);
        
        view.setClickable(true);
        view.setFocusable(true);
        view.setBackgroundResource(android.R.drawable.menuitem_background);
        
        view.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				TextView text = (TextView) v.findViewById(R.id.text);
				final Intent intent = new Intent(activity,PokedexActivity.class);
				intent.putExtra("com.protodex.masterdex.pokemon", text.getText());
		        activity.startActivity(intent);
			}});
        
        return view;
    }
    
	public Filter getFilter() {
		if(filter == null)
			filter = new PokemonFilter();
		return filter;
	}
	private class PokemonFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			
			if(constraint != null && constraint.length() > 0){
				constraint = constraint.toString().toUpperCase();
				Vector<Pokemon> filt = new Vector<Pokemon>();
				Vector<Pokemon> tempVect = new Vector<Pokemon>();
				tempVect.addAll(data);
				int size = tempVect.size();
				for(int i = 0; i < size; i++){
					Pokemon pk = tempVect.get(i);
					if(pk.getName().toUpperCase().contains(constraint) || String.valueOf(pk.getId()).contains(constraint))
						filt.add(pk);
				}
				results.values = filt;
				results.count = filt.size();
			}
			else{
				synchronized(this){
					results.values = data;
					results.count = data.size();
				}
			}
			
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			filteredData = ((Vector<Pokemon>) results.values);
			notifyDataSetChanged();
			
		}
		
	}
}
