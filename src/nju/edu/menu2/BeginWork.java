package nju.edu.menu2;

import java.util.ArrayList;
import java.util.List;

import nju.edu.menu2.Lay1Activity.CheckBoxListener;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class BeginWork extends ActionBarActivity{
	 
	private ViewPager mPager;//ҳ������
	private List<View> listViews; // Tabҳ���б�
	private ImageView cursor;// ����ͼƬ
	private TextView t1, t2, t3;// ҳ��ͷ��
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	 private int bmpW;// ����ͼƬ���
	 private TextView tv;
	 
		private CheckBox checkBox1;
		private CheckBox checkBox2;
		private CheckBox checkBox3;
	    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.begin_work);
		
		InitImageView(); 
        InitTextView(); 
        InitViewPager(); 
        
        
	
	
	}
	
	private void InitTextView() {
		 t1 = (TextView) findViewById(R.id.text1);
		 t2 = (TextView) findViewById(R.id.text2);
		 t3 = (TextView) findViewById(R.id.text3);

		 t1.setOnClickListener(new MyOnClickListener(0));
		 t2.setOnClickListener(new MyOnClickListener(1));
		 t3.setOnClickListener(new MyOnClickListener(2));	
		 
		
	}
	 public class MyOnClickListener implements View.OnClickListener {
		 private int index = 0;
		 public MyOnClickListener(int i) {
			 index = i;
		 }
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(index);
		}
	 }
	 private void InitViewPager() {
		 mPager = (ViewPager) findViewById(R.id.vPager);
		 listViews = new ArrayList<View>();
		 LayoutInflater mInflater = getLayoutInflater();
		 

		 
		 View layout1 = mInflater.inflate(R.layout.lay1,null);
		 View layout2 = mInflater.inflate(R.layout.lay2,null);
		 View layout3 = mInflater.inflate(R.layout.lay3,null);
		 listViews.add(layout1);	
		 listViews.add(layout2);
		 listViews.add(layout3);
		 mPager.setAdapter(new MyPagerAdapter(listViews));
		 mPager.setCurrentItem(0);
		 mPager.setOnPageChangeListener(new MyOnPageChangeListener());

//			intent.setClass(BeginWork.this, Lay1Activity.class);
//			startActivity(intent);
//		 
		 	checkBox1=(CheckBox)layout1.findViewById(R.id.checkBox1);
			checkBox2=(CheckBox)layout1.findViewById(R.id.checkBox2);
			checkBox3=(CheckBox)layout1.findViewById(R.id.checkBox3);
			
			CheckBoxListener listener=new CheckBoxListener();
			
			checkBox1.setOnCheckedChangeListener(listener);
			checkBox2.setOnCheckedChangeListener(listener);
			checkBox3.setOnCheckedChangeListener(listener);
			
			tv = (TextView) layout1.findViewById(R.id.chosenView2);
		 
		
	 }
	 
	 public class MyPagerAdapter extends PagerAdapter {
		 public List<View> mListViews;
		 public MyPagerAdapter(List<View> mListViews) {		
		   this.mListViews = mListViews;
		 }
		 @Override		 
		 public void destroyItem(View arg0, int arg1, Object arg2) {		 
		      ((ViewPager) arg0).removeView(mListViews.get(arg1));
		 }
		         
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mListViews.size();
		}

		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}
		
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==(arg1);
		}
	 }
	 
	 private void InitImageView(){
		 cursor = (ImageView) findViewById(R.id.cursor);
		 bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)	
				                  .getWidth();// ��ȡͼƬ���
		 DisplayMetrics dm = new DisplayMetrics();
		 getWindowManager().getDefaultDisplay().getMetrics(dm);
		 int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
		 offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
		 Matrix matrix = new Matrix();
		 matrix.postTranslate(offset, 0);
		 cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
		 
	 }
	 
	 public class MyOnPageChangeListener implements OnPageChangeListener { 
		 
	        int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ���� 
	        int two = one * 2;// ҳ��1 -> ҳ��3 ƫ���� 
	 
	        @Override 
	        public void onPageSelected(int arg0) { 
	            Animation animation = null; 
	            switch (arg0) { 
	            case 0: 
	                if (currIndex == 1) { 
	                    animation = new TranslateAnimation(one, 0, 0, 0); 
	                } else if (currIndex == 2) { 
	                    animation = new TranslateAnimation(two, 0, 0, 0); 
	                } 
	                break; 
	            case 1: 
	                if (currIndex == 0) { 
	                    animation = new TranslateAnimation(offset, one, 0, 0); 
	                } else if (currIndex == 2) { 
	                    animation = new TranslateAnimation(two, one, 0, 0); 
	                } 
	                break; 
	            case 2: 
	                if (currIndex == 0) { 
	                    animation = new TranslateAnimation(offset, two, 0, 0); 
	                } else if (currIndex == 1) { 
	                    animation = new TranslateAnimation(one, two, 0, 0); 
	                } 
	                break; 
	            } 
	            currIndex = arg0; 
	            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ�� 
	            animation.setDuration(300); 
	            cursor.startAnimation(animation); 
	        } 
	 
	        @Override 
	        public void onPageScrolled(int arg0, float arg1, int arg2) { 
	        } 
	 
	        @Override 
	        public void onPageScrollStateChanged(int arg0) { 
	        } 
	    } 
	 
	 
	 private int count = 0;
	 
	 class CheckBoxListener implements OnCheckedChangeListener{

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					count++;
					tv.setText(count+"���Ȳ�");
				}else{
					count--;
					tv.setText(count+"���Ȳ�");
				}
			}

			
			
		}
}
