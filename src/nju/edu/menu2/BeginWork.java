package nju.edu.menu2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nju.edu.menu2.Lay1Activity.CheckBoxListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class BeginWork extends ActionBarActivity {

	private ViewPager mPager;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t1, t2, t3;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	ArrayList<Dish> dishs;
	ArrayList<Dish> orderDishs = new ArrayList<>();

	// private CheckBox checkBox1;
	// private CheckBox checkBox2;
	// private CheckBox checkBox3;
	CheckBoxListener1 listener1;
	CheckBoxListener2 listener2;
	CheckBoxListener3 listener3;

	@SuppressLint("SdCardPath")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 需要添加checkbox的layout

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

		LinearLayout layout1 = (LinearLayout) mInflater.inflate(R.layout.lay1,
				null);
		LinearLayout layout2 = (LinearLayout) mInflater.inflate(R.layout.lay2,
				null);
		LinearLayout layout3 = (LinearLayout) mInflater.inflate(R.layout.lay3,
				null);

		// intent.setClass(BeginWork.this, Lay1Activity.class);
		// startActivity(intent);
		//
		// checkBox1=new CheckBox(layout2.getContext());
		// checkBox2=(CheckBox)layout1.findViewById(R.id.checkBox2);
		// checkBox3=new CheckBox(layout2.getContext());

		listener1 = new CheckBoxListener1();
		listener2 = new CheckBoxListener2();
		listener3 = new CheckBoxListener3();
		//
		// checkBox1.setOnCheckedChangeListener(listener);
		// checkBox2.setOnCheckedChangeListener(listener);
		// checkBox3.setOnCheckedChangeListener(listener);

		// LinearLayout linearLayout = (LinearLayout)
		// getLayoutInflater().inflate(R.layout.lay1, null);
		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		ArrayList<CheckBox> checkBoxs = new ArrayList<>();
		// checkBoxs.add(checkBox1);
		// checkBoxs.add(checkBox2);
		// checkBoxs.add(checkBox3);
		dishs = AddMenu.read("b.txt"); // 取出数据
		for (Dish dish : dishs) {

			CheckBox checkBox = new CheckBox(this);
			checkBox.setText(dish.getName());
			if (dish.getType().equals("炒菜")) {
				layout1.addView(checkBox);
				checkBox.setOnCheckedChangeListener(listener1);
			} else if (dish.getType().equals("凉菜")) {
				layout2.addView(checkBox);
				checkBox.setOnCheckedChangeListener(listener2);
			} else {
				layout3.addView(checkBox);
				checkBox.setOnCheckedChangeListener(listener3);
			}
			Log.i("add", checkBox.getText() + "");

		}

		RelativeLayout relativeLayout1 = (RelativeLayout) mInflater.inflate(
				R.layout.count, null);
		RelativeLayout relativeLayout2 = (RelativeLayout) mInflater.inflate(
				R.layout.count, null);
		RelativeLayout relativeLayout3 = (RelativeLayout) mInflater.inflate(
				R.layout.count, null);
		layout1.addView(relativeLayout1);
		layout2.addView(relativeLayout2);
		layout3.addView(relativeLayout3);

		tv1 = (TextView) relativeLayout1.findViewById(R.id.chosenView2);
		tv2 = (TextView) relativeLayout2.findViewById(R.id.chosenView2);
		tv3 = (TextView) relativeLayout3.findViewById(R.id.chosenView2);

		listViews.add(layout1);
		listViews.add(layout2);
		listViews.add(layout3);
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

		Button confirmButton = (Button) findViewById(R.id.button1);
		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText etEditText = new EditText(BeginWork.this);
				new AlertDialog.Builder(BeginWork.this).setTitle("请输入菜谱名称")
						.setView(etEditText)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent();
								intent.setClass(BeginWork.this, Result.class);
								Bundle bundle = new Bundle();
								bundle.putString("title", etEditText.getText()+"");
								intent.putExtras(bundle);
								intent.putExtra("list", orderDishs);
								startActivity(intent);
								BeginWork.this.finish();
								
							}
						})
						.setNegativeButton("取消", null).show();
			}
		});

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
			return arg0 == (arg1);
		}
	}

	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

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
			animation.setFillAfter(true);// True:图片停在动画结束位置
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

	private int count1 = 0;
	private int count2 = 0;
	private int count3 = 0;

	class CheckBoxListener1 implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			Log.i("button", buttonView.getText() + "");
			if (isChecked) {
				for (int i = 0; i < dishs.size(); i++) {
					if (dishs.get(i).getName().equals(buttonView.getText())) {
						orderDishs.add(dishs.get(i));
						break;
					}
				}
				count1++;
				tv1.setText(count1 + "个热菜");
			} else {
				for (int i = 0; i < orderDishs.size(); i++) {
					if (orderDishs.get(i).getName()
							.equals(buttonView.getText())) {
						orderDishs.remove(i);
						break;
					}
				}
				count1--;
				tv1.setText(count1 + "个热菜");
			}
		}

	}

	class CheckBoxListener2 implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked) {
				for (int i = 0; i < dishs.size(); i++) {
					if (dishs.get(i).getName().equals(buttonView.getText())) {
						orderDishs.add(dishs.get(i));
						break;
					}
				}
				count2++;
				tv2.setText(count2 + "个凉菜");
			} else {
				for (int i = 0; i < orderDishs.size(); i++) {
					if (orderDishs.get(i).getName()
							.equals(buttonView.getText())) {
						orderDishs.remove(i);
						break;
					}
				}
				count2--;
				tv2.setText(count2 + "个凉菜");
			}
		}

	}

	class CheckBoxListener3 implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked) {

				for (int i = 0; i < dishs.size(); i++) {
					if (dishs.get(i).getName().equals(buttonView.getText())) {
						orderDishs.add(dishs.get(i));
						break;
					}
				}
				count3++;
				tv3.setText(count3 + "个汤");
			} else {
				for (int i = 0; i < orderDishs.size(); i++) {
					if (orderDishs.get(i).getName()
							.equals(buttonView.getText())) {
						orderDishs.remove(i);
						break;
					}
				}
				count3--;
				tv3.setText(count3 + "个汤");
			}
		}

	}
}
