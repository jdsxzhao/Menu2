package nju.edu.menu2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ShowDetail extends ActionBarActivity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		String[] itemsStrings = getResources().getStringArray(R.array.type_array);
		ArrayAdapter<String> _Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,itemsStrings);
		spinner.setAdapter(_Adapter);
	
	}
}
