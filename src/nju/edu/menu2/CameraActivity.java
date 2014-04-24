package nju.edu.menu2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

public class CameraActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_main);

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {

			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
				Log.v("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}

			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ
			FileOutputStream b = null;
			File file = new File("/sdcard/myImage/");
			file.mkdirs();// �����ļ���
			String fileName = "/sdcard/myImage/111.jpg";

			try {
				b = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
			} catch (FileNotFoundException e) {
				Log.e("����", "û�ҵ�");
				e.printStackTrace();
			} finally {
				try {
					b.flush();
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// ��ͼƬ��ʾ��ImageView��
			Button button = (Button) findViewById(R.id.button);
			Button button2 = (Button) findViewById(R.id.button1);
			Button button3 = (Button) findViewById(R.id.button2);

			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(CameraActivity.this, MainActivity.class);
					startActivity(intent);
					// ������رյ�ǰ�Ļ���ֺö��ҳ��
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
					// ������رյ�ǰ�Ļ���ֺö��ҳ��
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