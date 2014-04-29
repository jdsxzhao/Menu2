package nju.edu.menu2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;

import nju.edu.menu2.MainActivity.ButtonListener;
import nju.edu.menu2.MainActivity.TextViewListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
import android.widget.Toast;

public class AddMenu extends ActionBarActivity{


	LinearLayout rlLayout;
	private TextView confirmTV;
	private TextView cancelTV;
	private ImageView cameraImageView;
	Bitmap bitmap;
	ImageView imageView;
	Spinner spinner;
	EditText editText1;
	EditText editText2;
//	public static String name = "";
//	public static String material = "";
//	
//	
//	public ImageView pic;
	
	public static boolean isloadPic = false ;
	
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_main2);
		rlLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.add_main2, null);  
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String material = intent.getStringExtra("material");
		imageView = (ImageView) findViewById(R.id.pic);
		if (isloadPic) {
			bitmap=intent.getParcelableExtra("bitmap");
			imageView.setImageBitmap(bitmap);
			
		}
		
		editText1 = (EditText) findViewById(R.id.editText1);
		editText1.setText(name);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText2	.setText(material);
		spinner = (Spinner) findViewById(R.id.spinner1);
		String[] itemsStrings = getResources().getStringArray(R.array.type_array);
		ArrayAdapter<String> _Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,itemsStrings);
		spinner.setAdapter(_Adapter);
		spinner.setSelection(intent.getIntExtra("type", 0), true);
		

		confirmTV=(TextView)findViewById(R.id.textView5);
		TVOnClickListener confirmListener=new TVOnClickListener();
		confirmTV.setOnClickListener(confirmListener);
		
		
		cancelTV = (TextView)findViewById(R.id.textView4);
		cancelTV.setOnClickListener(new CancelListener());
		
		
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
			
			
			save("b.txt");
			//ArrayList<Dish> dishs = read("b.txt");
			// Toast.makeText(AddMenu.this,dishs.get(0).getName(),Toast.LENGTH_LONG).show();
			//AddMenu.this.finish();
			
			
		}
	
		
	}
	
	
	class CancelListener implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent();
			intent.setClass(AddMenu.this, MainActivity.class);
			startActivity(intent);
			AddMenu.this.finish();
			
			
		}
	}
	
	class CameraListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
			String material = ((EditText) findViewById(R.id.editText2)).getText().toString();	
			int pos = spinner.getSelectedItemPosition();
			Intent intent = new Intent();
			intent.setClass(AddMenu.this, CameraActivity.class);
			Bundle bundle=new Bundle();  
            bundle.putString("name", name);  
            bundle.putString("material",material); 
            bundle.putInt("type", pos);
            intent.putExtras(bundle);  
			
            startActivity(intent);
            AddMenu.this.finish();
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
	

	@SuppressLint("SdCardPath")
	public void save(String path){
		  try {
			  if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

			         File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录

			         File saveFile = new File("/data/data/nju.edu.menu2/files/"+path);

			       FileOutputStream out= new FileOutputStream(saveFile,true);
			       Writer outStream = new OutputStreamWriter(out, "UTF-8");
			       
			   
			       
			       String string = new String(editText1.getText().toString().getBytes(), "UTF-8");
			       String string2 = new String(";".getBytes(), "UTF-8");
			       String string3 = new String(editText2.getText().toString().getBytes(), "UTF-8");
			       String string4 = new String(spinner.getSelectedItem().toString().getBytes(), "UTF-8");
	            
	            outStream.write(string);
	            outStream.write(string2);
	            outStream.write(string3);
	            outStream.write(string2);
	            outStream.write(string4);
	            outStream.write('\n');
	            
	            outStream.close();
	            Toast.makeText(AddMenu.this,"保存完成",Toast.LENGTH_LONG).show();
			  }else {
				  Toast.makeText(AddMenu.this,"SD卡发生错误",Toast.LENGTH_LONG).show();
			}
	        } catch (FileNotFoundException e) {
	            return;
	        }
	        catch (IOException e){
	            return ;
	        }
	}
	
	@SuppressLint("SdCardPath")
	public static ArrayList<Dish> read(String path){
		ArrayList<Dish> dishs = new ArrayList<>();
		//if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String strFileName = path;  
			FileInputStream fis;
			String res = ""; 
			try {
				//File file = new File(Environment.getExternalStorageDirectory(),path);
				//FileInputStream fin = new FileInputStream(file);
//				 FileInputStream fin = openFileInput(path); 
//		            int length = fin.available(); 
//		            byte[] buffer = new byte[length]; 
//		            fin.read(buffer); 
//		            res = EncodingUtils.getString(buffer, "GBK"); 
//		            Log.i("read", res);
//		            fin.close(); 
				fis = new FileInputStream("/data/data/nju.edu.menu2/files/"+strFileName);
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				
				
				BufferedReader dataIO = new BufferedReader(isr);  
				String strLine = null;  
				while((strLine =  dataIO.readLine()) != null){ 
					
					String[] dishStrings = strLine.split(";"); 
					Log.i("dish", strLine);
					Dish dish = new Dish();
					dish.setName(dishStrings[0]);
					dish.setMaterial(dishStrings[1]);
					dish.setType(dishStrings[2]);
					dishs.add(dish);					
				}  
				dataIO.close();  
				fis.close();  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		//}
	return dishs;	
	}
}
