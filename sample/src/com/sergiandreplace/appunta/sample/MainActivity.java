package com.sergiandreplace.appunta.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
    private View btnRadar;
	private View btnAR;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        setListeners();
        
    }
    
    private void findViews() {
    	btnRadar=findViewById(R.id.btnRadar);
    	btnAR=findViewById(R.id.btnAR);
    }
    
    private void setListeners() {
    	btnRadar.setOnClickListener(this);
    	btnAR.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if (v.getId()==btnRadar.getId()) {
			Intent i=new Intent(this, RadarActivity.class);
			startActivity(i);
		}
		if (v.getId()==btnAR.getId()) {
			Intent i=new Intent(this, AugmentedRealityActivity.class);
			startActivity(i);
		}
	}
}