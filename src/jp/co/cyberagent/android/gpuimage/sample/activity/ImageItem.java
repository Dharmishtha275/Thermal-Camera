package jp.co.cyberagent.android.gpuimage.sample.activity;

public class ImageItem
{
	private String imagePath;
	private String title;

	public ImageItem(String paramString1, String paramString2)
	{
		this.title = paramString1;
		this.imagePath = paramString2;
	}

	public String getImagePath()
	{
		return this.imagePath;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setImagePath(String paramString)
	{
		this.imagePath = paramString;
	}

	public void setTitle(String paramString)
	{
		this.title = paramString;
	}
}
