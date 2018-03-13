
package jp.co.cyberagent.android.gpuimage.sample.activity;

import java.io.File;






import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.GPUImageView.OnPictureSavedListener;
import jp.co.cyberagent.android.gpuimage.sample.GPUImageFilterTools;
import jp.co.cyberagent.android.gpuimage.sample.GPUImageFilterTools.FilterAdjuster;
import jp.co.cyberagent.android.gpuimage.sample.GPUImageFilterTools.OnGpuImageFilterChosenListener;
import jp.co.cyberagent.android.gpuimage.sample.R;
import jp.co.cyberagent.android.gpuimage.sample.utils.constant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class ActivityGallery extends Activity implements OnSeekBarChangeListener,
OnClickListener, OnPictureSavedListener {

	private static final int REQUEST_PICK_IMAGE = 1;
	private GPUImageFilter mFilter;
	private FilterAdjuster mFilterAdjuster;
	private GPUImageView mGPUImageView;
	static Uri uri;

	boolean isPresent;
	File destination;
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(this);
		findViewById(R.id.button_choose_filter).setOnClickListener(this);
		findViewById(R.id.button_save).setOnClickListener(this);

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

		mGPUImageView = (GPUImageView) findViewById(R.id.gpuimage);

		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		switch (requestCode) {
		case REQUEST_PICK_IMAGE:
			if (resultCode == RESULT_OK) {
				handleImage(data.getData());
			} else {
				finish();
			}
			break;

		default:
			super.onActivityResult(requestCode, resultCode, data);
			break;
		}
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.button_choose_filter:
			GPUImageFilterTools.showDialog(this, new OnGpuImageFilterChosenListener() {

				@Override
				public void onGpuImageFilterChosenListener(final GPUImageFilter filter) {
					switchFilterTo(filter);
					mGPUImageView.requestRender();
				}

			});
			break;
		case R.id.button_save:
			saveImage();
			break;

		default:
			break;
		}

	}

	@Override
	public void onPictureSaved(final Uri uri) {
		Toast.makeText(this, "Saved: " + uri.toString(), Toast.LENGTH_SHORT).show();
		Intent i= new Intent(ActivityGallery.this,Share_Activity.class);

		startActivity(i);
	}

	
	private void saveImage() 
	{
		String fileName = System.currentTimeMillis() + ".jpg";
		constant.savedPath = destination.getAbsolutePath() + "/" + fileName;
		
		mGPUImageView.saveToPictures(destination.getAbsolutePath(), fileName, this);
		//mGPUImageView.saveToPictures("GPUImage", fileName, 1600, 1600, this);
	}


	private void switchFilterTo(final GPUImageFilter filter) {
		if (mFilter == null
				|| (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
			mFilter = filter;
			mGPUImageView.setFilter(mFilter);
			mFilterAdjuster = new FilterAdjuster(mFilter);

			findViewById(R.id.seekBar).setVisibility(
					mFilterAdjuster.canAdjust() ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
		if (mFilterAdjuster != null) {
			mFilterAdjuster.adjust(progress);
		}
		mGPUImageView.requestRender();
	}

	@Override
	public void onStartTrackingTouch(final SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(final SeekBar seekBar) {
	}

	private void handleImage(final Uri selectedImage) {
		mGPUImageView.setImage(selectedImage);
	}
}
