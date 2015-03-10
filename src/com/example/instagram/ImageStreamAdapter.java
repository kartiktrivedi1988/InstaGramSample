package com.example.instagram;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.instagramgallery.network.BitmapDownloaderTask;

public class ImageStreamAdapter extends BaseAdapter {
	private HashMap<ImageView, BitmapDownloaderTask> competitors;

	JSONObject imageData;
	Context c;
	int size = 3;

	public ImageStreamAdapter(Context c, JSONObject imageData, int number) {
		Log.i("crb", "image data downloaded");
		this.c = c;
		this.imageData = imageData;
		competitors = new HashMap<ImageView, BitmapDownloaderTask>();
		//size = number;

	}

	@Override
	public int getCount() {
		try {
			return imageData.getJSONArray("data").length();
		} catch (JSONException e) {
			// simply return 0
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout container = new LinearLayout(c);

		ImageView imageView = new ImageView(c);
		container.setLayoutParams(new GridView.LayoutParams(220, 220));
		if (position % size == 0) {
			//size++;
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
		} else  {
			//size++;
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
		} 

		imageView.setImageBitmap(null);

		try {
			BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);

			BitmapDownloaderTask other = competitors.put(imageView, task);

			if (other != null)
				other.cancel(false);

			String url = imageData.getJSONArray("data").getJSONObject(position)
					.getJSONObject("images").getJSONObject("thumbnail")
					.getString("url");

			if (!task.searchCache(url))
				task.execute(url);

		} catch (JSONException e) {

		}
		container.setOrientation(LinearLayout.HORIZONTAL);
		container.setGravity(Gravity.CENTER_HORIZONTAL);
		container.addView(imageView);
		return container;
	}

}
