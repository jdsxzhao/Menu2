package nju.edu.menu2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

@SuppressLint("SdCardPath")
public class CameraActivity extends Activity {
	String name;
	String material;
	Bitmap bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_main);
		Intent intent1 = getIntent();
		name = intent1.getStringExtra("name");
		material = intent1.getStringExtra("material");
		
		Log.i("name", name);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1);
	}

	@SuppressLint("SdCardPath")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {

			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				Log.v("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}

			Bundle bundle = data.getExtras();
			bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
			FileOutputStream b = null;
			File file = new File("/sdcard/myImage/");
			boolean i = file.mkdirs();// 创建文件夹
			String fileName = "/sdcard/myImage/111.jpg";

			try {
				b = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
			} catch (FileNotFoundException e) {
				Log.e("错误", i+"没找到");
				e.printStackTrace();
			} finally {
				try {
					b.flush();
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// 将图片显示在ImageView里
			Button button = (Button) findViewById(R.id.button);
			Button button2 = (Button) findViewById(R.id.button1);
			Button button3 = (Button) findViewById(R.id.button2);

			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					
					intent.setClass(CameraActivity.this, AddMenu.class);
					
					Bundle bundle=new Bundle();  
		            bundle.putString("name", name);  
		            Log.i("name", name);
		            bundle.putString("material",material); 
		            intent.putExtra("bitmap", bitmap);
		            intent.putExtras(bundle);  
		            
					
					
					
					startActivity(intent);
					// 如果不关闭当前的会出现好多个页面
					
					CameraActivity.this.finish();
				}
			});

			button2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent intent = new Intent();
					
					intent.setClass(CameraActivity.this, MainActivity.class);
					startActivity(intent);
					// 如果不关闭当前的会出现好多个页面
					CameraActivity.this.finish();
				}
			});

			button3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, 1);
					CameraActivity.this.finish();
				}
			});
		}
	}
}