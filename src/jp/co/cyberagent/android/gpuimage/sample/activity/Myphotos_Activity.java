

package jp.co.cyberagent.android.gpuimage.sample.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;










import jp.co.cyberagent.android.gpuimage.sample.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Myphotos_Activity extends Activity {
	static Bitmap bm;
    private Context mContext;
    private Bitmap[]mis_fotos;
	static String[] FilePathStrings;
	private String[] FileNameStrings;
	private File[] listFile;
	GridView grid;
	GridviewAdapter adapter;
   
    
    GridView gv;
    Button share,delete;
	boolean isPresent;
	File destination;
    
	ArrayList<String> f1 = new ArrayList<String>();
  
	protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myphotos);
		
		
		isPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		
		if(isPresent)
		{
			//make folder in sd card
			
			destination = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.account_name) + "/" + getString(R.string.folder_name));
			////to make directory
			if(!destination.exists())
			{
				destination.mkdirs();
			}
		}
		else
		{
			//Toast.makeText(getApplicationContext(), "No SD Card Found!! Please insert SD Card.", Toast.LENGTH_LONG).show();
			destination = getDir(getString(R.string.account_name) + "/" + getString(R.string.folder_name), Context.MODE_PRIVATE);
		}
		
		if (destination.isDirectory()) {
			listFile = destination.listFiles();
			// Create a String array for FilePathStrings
			FilePathStrings = new String[listFile.length];
			// Create a String array for FileNameStrings
			FileNameStrings = new String[listFile.length];
 
			for (int i = 0; i < listFile.length; i++) {
				// Get the path of the image file
				FilePathStrings[i] = listFile[i].getAbsolutePath();
				// Get the name image file
				FileNameStrings[i] = listFile[i].getName();
			}
		}
		

		// Locate the GridView in gridview_main.xml
		grid = (GridView) findViewById(R.id.PhoneImageGrid);
		// Pass String arrays to LazyAdapter Class
		adapter = new GridviewAdapter(this, FilePathStrings, FileNameStrings);
	
		grid.setAdapter(adapter);
		
	     grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// TODO Auto-generated method stub
				Intent i= new Intent(Myphotos_Activity.this,Delete_activity.class);
				i.putExtra("Index", position);
				startActivity(i);
				finish();
			}
		});
	
 
}

}