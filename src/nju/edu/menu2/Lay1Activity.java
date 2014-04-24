package nju.edu.menu2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Lay1Activity extends ActionBarActivity{

	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private CheckBox checkBox3;
	private TextView tv;
	int count=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay1);
		
		
		tv=(TextView)findViewById(R.id.chosenView1);
		 try{
			    tv.setText("Hello testing");
			    Log.i("Correct","No error");
			    }
			    catch(Exception e){

			        Log.i("Error","Error here");
			        e.printStackTrace();
			    }
	
	
		checkBox1=(CheckBox)findViewById(R.id.checkBox1);
		checkBox2=(CheckBox)findViewById(R.id.checkBox2);
		checkBox3=(CheckBox)findViewById(R.id.checkBox3);
		
		CheckBoxListener listener=new CheckBoxListener();
		
		checkBox1.setOnCheckedChangeListener(listener);
		checkBox2.setOnCheckedChangeListener(listener);
		checkBox3.setOnCheckedChangeListener(listener);
		
//		tv=new TextView(this);
		
		
		
		
	}

	class CheckBoxListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				count++;
				tv.setText(count);
			}else{
				count--;
				tv.setText(count);
			}
		}

		
		
	}
	
	public int getView(){
		return R.layout.lay1;
	}
}
