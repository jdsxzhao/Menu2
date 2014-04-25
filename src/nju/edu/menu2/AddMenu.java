package nju.edu.menu2;

import java.util.ArrayList;




import nju.edu.menu2.MainActivity.ButtonListener;
import nju.edu.menu2.MainActivity.TextViewListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class AddMenu extends ActionBarActivity{


	LinearLayout rlLayout;
	private TextView confirmTV;
	private ImageView cameraImageView;
	Bitmap bitmap;
	ImageView imageView;
//	public static String name = "";
//	public static String material = "";
//	
//	
//	public ImageView pic;
	
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_main2);
		rlLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.add_main2, null);  
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String material = intent.getStringExtra("material");
		  bitmap=intent.getParcelableExtra("bitmap");
		  imageView = (ImageView) findViewById(R.id.pic);
          imageView.setImageBitmap(bitmap);
		
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		editText1.setText(name);
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		editText2	.setText(material);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		String[] itemsStrings = getResources().getStringArray(R.array.type_array);
		ArrayAdapter<String> _Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,itemsStrings);
		spinner.setAdapter(_Adapter);

		confirmTV=(TextView)findViewById(R.id.textView5);
		TVOnClickListener confirmListener=new TVOnClickListener();
		confirmTV.setOnClickListener(confirmListener);
		
		
		cameraImageView = (ImageView)findViewById(R.id.camera);
		cameraImageView.setOnClickListener(new CameraListener());
		

		
	}
	
	class TVOnClickListener implements OnClickListener{

		
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			
			new AlertDialog.Builder(AddMenu.this)
			.setTitle("确认" )
			.setMessage("确定吗？" )
			.setPositiveButton("yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					new AlertDialog.Builder(AddMenu.this)
					.setTitle("确认" )
					.setMessage("确定分享吗？" )
					.setPositiveButton("分享", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
							}})
					.setNegativeButton("no", null)
					.show();
					}})
			.setNegativeButton("no", null)
			.show();
			
			
		}
	
		
	}
	
	class CameraListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
			String material = ((EditText) findViewById(R.id.editText2)).getText().toString();	
			Intent intent = new Intent();
			intent.setClass(AddMenu.this, CameraActivity.class);
			Bundle bundle=new Bundle();  
            bundle.putString("name", name);  
            bundle.putString("material",material);  
            intent.putExtras(bundle);  
			
            startActivity(intent);
		}
		
	}
	
	
//	public void setPic(Bitmap bitmap){
//		pic = (ImageView) findViewById(R.id.pic);
//		pic.setImageBitmap(bitmap);
//	}
	
	
	ArrayList<RelativeLayout.LayoutParams> lps = new ArrayList<>();
	int i = 0 ;

//	public void addEditText(){
//		
//        //定义一个RelativeLayout组件
//        
//		EditText editText = new EditText(this);
//		LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//		
//        
//        //与父组件顶部对齐
//       // lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        if (i == 0) {
//        	
//		}else {
//			//lp1.addRule(RelativeLayout.);
//		}
//
//        rlLayout.addView(editText, lp1 );
//        i++;
// 
//        setContentView(rlLayout);
//        
//		
//	}
//	class TVListener implements OnClickListener{
//
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			addEditText();
//		}
//		
//	}
}
