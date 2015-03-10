package com.example.instagram;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class GridViewItemLayout extends ImageView {
	
	// The position of the view cell
	private int mPosition;

	// Public constructor
	public GridViewItemLayout(Context context) {
		super(context);
	}

	// Public constructor
	public GridViewItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Set the position of the view cell
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		mPosition = position;
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// Get the size of current cell
		int size = (Integer) getTag();
		Log.d("SIZE", size + "---");
		// Get the measured height for this layout
		int measuredHeight = getMeasuredHeight();
		// Update the dimensions of the layout to reflect the max height
		setMeasuredDimension(getMeasuredWidth(), size);
	}
}
