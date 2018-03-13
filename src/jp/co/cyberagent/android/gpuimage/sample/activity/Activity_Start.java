package jp.co.cyberagent.android.gpuimage.sample.activity;

import jp.co.cyberagent.android.gpuimage.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Activity_Start extends Activity implements OnClickListener {
	
	 public void onCreate(final Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_start);
	        
	        findViewById(R.id.start).setOnClickListener(this);
	        findViewById(R.id.rate_us).setOnClickListener(this);
	        findViewById(R.id.photos).setOnClickListener(this);
	 }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		 switch (v.getId()) {
		 case R.id.start:
           startActivity(ActivityMain.class);
          break;
          case R.id.rate_us:
           startActivity(new Intent(Intent.ACTION_VIEW,
                   Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
            break;
          case R.id.photos:
              startActivity(Myphotos_Activity.class);
               break;
    default:
        break;
		 }
}


private void startActivity(final Class<?> activityClass) {
startActivity(new Intent(this, activityClass));
}
	}

	


