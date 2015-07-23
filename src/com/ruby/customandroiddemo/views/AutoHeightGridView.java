package com.ruby.customandroiddemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 可设置最大高度的GridView
 * 
 * @author ruby
 * 
 */
public class AutoHeightGridView extends GridView {
	
	private int maxHeight = Integer.MAX_VALUE/2;

	public AutoHeightGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
