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

public class TrainerListAdapter extends BaseAdapter implements Filterable {
	private Vector<Trainer> TrainerList;
	private static Vector<Trainer> sFilteredTrainerList;
	private Activity activity;
	private static LayoutInflater inflater = null;
	private Resources resource;
	private Filter filter;
	
	@SuppressWarnings("static-access")
	public TrainerListAdapter(Activity activity, Vector<Trainer> TrainerList){
		this.TrainerList = new Vector<Trainer>();
		this.TrainerList.addAll(TrainerList);
		this.sFilteredTrainerList = new Vector<Trainer>();
		sFilteredTrainerList.addAll(this.TrainerList);
		this.activity = activity;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		getFilter();
	}
	
	public void setResource(Resources resource){
    	this.resource = resource;
    }

	public int getCount() {
		return sFilteredTrainerList.size();
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
//        ImageView wheel = (ImageView)view.findViewById(R.id.wheel);
//        wheel.setBackgroundResource(R.drawable.wheelanimation);
//		final AnimationDrawable frameAnimation = (AnimationDrawable) wheel.getBackground();
//        wheel.post(frameAnimation);
//        ImageDownloader dl = new ImageDownloader();
        String name = sFilteredTrainerList.get(position).getIcon();
//        String extension = ".gif";
//        if(name.contains("Lt. Surge"))
//        	name = "LtSurge";
//        if(name.contains("Tate & Liza"))
//        	name = "TateLiza";
//        if(name.contains("Crasher Wake"))
//        	name = "CrasherWake";
//        if(name.contains("Brawly")||name.contains("Brycen")||name.contains("Burgh")||name.contains("Chili")||name.contains("Cilan")||name.contains("Clay")||name.contains("Cress")||name.contains("Drayden")||name.contains("Elesa")||name.contains("Flannery")||name.contains("Iris")||name.contains("Juan")||name.contains("Lenora")||name.contains("Norman")||name.contains("Roxanne")||name.contains("Skyla")||name.contains("Tate")||name.contains("Wallace")||name.contains("Wattson")||name.contains("Winona"))
//        	extension = ".png";
//        dl.download("http://protodx.com/trainers/icon_"+name+extension, image, wheel);
        //image.setImageBitmap(getBitmap());
       // image.setBackgroundResource(R.drawable.misty);
       // image.setImageDrawable(null);
        //final AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
        //image.post(frameAnimation);
        int picID = resource.getIdentifier(name, "drawable", "com.protodx.masterdex");
        image.setImageResource(picID);
        
        TextView text=(TextView)view.findViewById(R.id.text);
        text.setText(sFilteredTrainerList.get(position).getName());
        Typeface tf = Typeface.createFromAsset(text.getContext().getAssets(),"fonts/Roboto-Light.ttf");
        text.setTypeface(tf);
        
//        TextView number = (TextView)view.findViewById(R.id.number);
//        int pkId = sFilteredTrainerList.get(position).getId();
//        if(pkId < 10)
//        	number.setText("#00"+String.valueOf(pkId));
//        else if(pkId > 9 && pkId < 100)
//        	number.setText("#0"+String.valueOf(pkId));
//        else
//        	number.setText("#"+String.valueOf(pkId));
//        number.setTypeface(tf);
        
        view.setClickable(true);
        view.setFocusable(true);
        view.setBackgroundResource(android.R.drawable.menuitem_background);
        
        view.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				TextView text = (TextView) v.findViewById(R.id.text);
				final Intent intent = new Intent(activity,TrainerActivity.class);
				intent.putExtra("com.protodex.masterdex.trainer", text.getText());
		        activity.startActivity(intent);
			}});
        
        return view;
	}

	public Filter getFilter() {
		if(filter == null)
			filter = new TrainerFilter();
		return filter;
	}
	
	private class TrainerFilter extends Filter{

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();

			if(constraint != null && constraint.length() > 0){
				constraint = constraint.toString().toUpperCase();
				Vector<Trainer> filt = new Vector<Trainer>();
				Vector<Trainer> tempList = new Vector<Trainer>();
				tempList.addAll(TrainerList);
				int size = tempList.size();
				for(int i = 0; i < size; i++){
					Trainer trainer = tempList.get(i);
					if(trainer.getName().toUpperCase().contains(constraint))
						filt.add(trainer);
				}
				results.values = filt;
				results.count = filt.size();
			}else{
				synchronized(this){
					results.values = TrainerList;
					results.count = TrainerList.size();
				}
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			sFilteredTrainerList = ((Vector<Trainer>) results.values);
			notifyDataSetChanged();
			
		}
		
	}
}
