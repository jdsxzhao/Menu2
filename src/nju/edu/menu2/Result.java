package nju.edu.menu2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends ActionBarActivity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String title = getIntent().getStringExtra("title");
		@SuppressWarnings("unchecked")
		ArrayList<Dish> dishs = (ArrayList<Dish>)getIntent().getSerializableExtra("list");
		
		
		LinearLayout ly = (LinearLayout)this.getLayoutInflater().inflate(R.layout.result, null);
		
		TextView tv1 = new TextView(this);
		tv1.setText(title);
		ly.addView(tv1);
		
		for(Dish dish:dishs){
			TextView tView = new TextView(this);
			tv1.setText(dish.getName());
			ly.addView(tView);
		}
		
		setContentView(ly);
	}

}
