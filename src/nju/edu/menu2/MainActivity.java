package nju.edu.menu2;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView tv;
	private Button button1;
	private Button button2;

	
	int count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		ButtonListener btnButtonListener=new ButtonListener();	
		ButtonListener2 btnButtonListener2=new ButtonListener2();	
		button1.setOnClickListener(btnButtonListener);
		button2.setOnClickListener(btnButtonListener2);
		
		tv=(TextView)findViewById(R.id.textView1);
		TextViewListener tvListener=new TextViewListener();
		tv.setOnClickListener(tvListener);
//		tv.setText("Hello asdfasd");

//		tv.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_launcher), getResources().getDrawable(R.drawable.ic_launcher), getResources().getDrawable(R.drawable.ic_launcher), getResources().getDrawable(R.drawable.ic_launcher));
		
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, AddMenu.class);
			
			Bundle bundle=new Bundle();  
            bundle.putString("name", "");  
            bundle.putString("material","");  
            intent.putExtras(bundle); 
			startActivity(intent);
//			MainActivity.this.finish();
		}
		
	}
	class ButtonListener2 implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, BeginWork.class);
			startActivity(intent);
//			MainActivity.this.finish();
		}
		
	}
	class TextViewListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, ShowDetail.class);
			startActivity(intent);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
/*
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
*/
}
