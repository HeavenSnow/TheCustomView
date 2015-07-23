package com.ruby.customandroiddemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * drawableLeft与文本一起居中显示
 * 
 * @author lingfei.qi
 * @see http://www.cnblogs.com/over140/p/3464348.html
 * 
 */
public class DrawableCenterTextView extends TextView{

	public DrawableCenterTextView(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterTextView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	 Drawable[] drawables = getCompoundDrawables();  
    	    if (drawables != null) {  
    	        Drawable drawableLeft = drawables[0];  
    	        if (drawableLeft != null) {  
    	            final float textWidth = getPaint().measureText(getText().toString());  
    	            final int drawablePadding = getCompoundDrawablePadding();  
    	            final int drawableWidth = drawableLeft.getIntrinsicWidth();  
    	            final float mPaddingLeft = (getWidth() - textWidth) / 2 - drawablePadding - drawableWidth;
    	            canvas.translate(mPaddingLeft, 0);  
    	        }  
    	    }  
    	    super.onDraw(canvas); 
    }

}
