package nju.edu.menu2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

public class MyEditText extends EditText{
	public MyEditText(Context context , AttributeSet attrs){
		super(context, attrs);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
		return true;
	}
}
