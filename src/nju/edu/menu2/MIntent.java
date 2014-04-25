package nju.edu.menu2;

import android.content.Context;
import android.content.Intent;

public class MIntent extends Intent{
	
	Intent intent;

	public Intent setClass(Context context , Class<?> cls , AddMenu a){
		intent.setClass(context, cls);
		return intent;
	}
	
	
}
