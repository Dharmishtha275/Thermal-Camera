package jp.co.cyberagent.android.gpuimage.sample.activity;



import java.io.File;





import jp.co.cyberagent.android.gpuimage.sample.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Delete_activity extends Activity
{
	private static final String TAG = "KM";
	String imagePath;
	private ImageView imageView;
	static Bitmap bmp;
	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_image_display_screen);
		//this.imageView = (ImageView) ((ImageView)findViewById(R.id.imageview_showimage));
		Bundle bdl=getIntent().getExtras();
		int index=bdl.getInt("Index");      
		ImageView mImage = (ImageView) findViewById(R.id.imageview_showimage);
		imagePath=Myphotos_Activity.FilePathStrings[index];

		mImage.setImageBitmap(BitmapFactory.decodeFile(Myphotos_Activity.FilePathStrings[index]));
	}

	public void onDelete(View paramView)
	{
		final Dialog localDialog = new Dialog(this);
		localDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		localDialog.setContentView(R.layout.custom_dialog);

		Button btyes = (Button) localDialog.findViewById(R.id.btyes);
		Button btno = (Button) localDialog.findViewById(R.id.btno);

		TextView txtmsg = (TextView) localDialog.findViewById(R.id.txtmsg);
		txtmsg.setText("Would you like to delete this image?");

		btyes.setOnClickListener(new View.OnClickListener()
		{
			//localDialog
			public void onClick(View paramView)
			{
				localDialog.dismiss();
				new File(Delete_activity.this.imagePath).delete();
				RefreshGallery(Delete_activity.this.imagePath);
				Delete_activity.this.finish();
				Intent i= new Intent(Delete_activity.this,Myphotos_Activity.class);
				startActivity(i);
			}
		});

		btno.setOnClickListener(new View.OnClickListener()
		{
			//localDialog
			public void onClick(View paramView)
			{
				localDialog.dismiss();
				Intent i= new Intent(Delete_activity.this,Myphotos_Activity.class);
				startActivity(i);
			}
		});
		Window localWindow = localDialog.getWindow();
		localWindow.setLayout(-2, -2);
		localWindow.setGravity(17);
		localDialog.show();
	}

	@SuppressLint("NewApi")
	public void RefreshGallery(String path)
	{
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) 
		{
			Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			File f1 = new File(path);
			Uri contentUri = Uri.fromFile(f1);
			mediaScanIntent.setData(contentUri);
			sendBroadcast(mediaScanIntent);
		}
		else
		{
			File f1 = new File(path);
			sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(f1)));
			//sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
		}
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
	{
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	public void onShare(View paramView)
	{
		try
		{
			Intent localIntent = new Intent("android.intent.action.SEND");
			localIntent.setType("image/png");
			localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(this.imagePath)));
			startActivity(Intent.createChooser(localIntent, "My Photo"));
			return;
		}
		catch (Exception localException)
		{
			Log.v("KM", "Error sharing photo");
		}
	}
}

