package com.ruby.customandroiddemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 可设置最大高度的ListView
 * 
 * @author ruby
 * 
 */
public class AutoHeightListView extends ListView {

	private int maxHeight = Integer.MAX_VALUE/2 ;

	public AutoHeightListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置ListView的最大高度
	 * 
	 * @param maxHeight
	 */
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(maxHeight,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
