package jp.co.cyberagent.android.gpuimage.sample.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jp.co.cyberagent.android.gpuimage.sample.R;
import jp.co.cyberagent.android.gpuimage.sample.utils.constant;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Share_Activity  extends Activity{

	ImageView img1,img;
	Button home ,share,photo;
	Bitmap bitmap1,bitmapgallery;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);

		img=(ImageView)findViewById(R.id.img);
		
		
		
			 String path=constant.savedPath;
			bitmapgallery =decodeFile(new File(path)) ;
		 	   img.setImageBitmap(bitmapgallery);
		
			//bitmap1 =decodeFile(new File(ActivityCamera.savedPath1)) ;
	//	img1.setImageBitmap(ActivityCamera.bitmap);
		
		home=(Button)findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Activity_Start.class);
				startActivity(i);
			}
		});

		share=(Button)findViewById(R.id.share);
		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
				sharingIntent.setType("text/plain");
				String shareBody = "Here is the share content body";
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				startActivity(Intent.createChooser(sharingIntent, "Share via"));
			}
		});


		photo=(Button)findViewById(R.id.photo);
		photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Myphotos_Activity.class);
				startActivity(i);
			}
		});
	
	}
	
	 public Bitmap decodeFile(File f)
	 {
	  try 
	  {
	   //Decode image size
	   BitmapFactory.Options o = new BitmapFactory.Options();
	   o.inJustDecodeBounds = true;
	   BitmapFactory.decodeStream(new FileInputStream(f),null,o);

	   //The new size we want to scale to
	   final int REQUIRED_SIZE = 340;

	   //Find the correct scale value. It should be the power of 2.
	   int scale = 1;
	   while(o.outWidth/scale/2 >= REQUIRED_SIZE && o.outHeight/scale/2 >= REQUIRED_SIZE)
	   {
	    scale *= 2;
	   }

	   //Decode with inSampleSize
	   BitmapFactory.Options o2 = new BitmapFactory.Options();
	   o2.inSampleSize = scale;
	   return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	  }
	  catch (FileNotFoundException e)
	  {

	  }
	  return null;
	 }
	 




}
