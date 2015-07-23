package com.ruby.customandroiddemo.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ruby.customandroiddemo.R;
import com.ruby.customandroiddemo.utils.ActivityHelper;

/**
 * 
 * 一个自定义的Dialog
 * 
 * @author lingfei.qi 2015/6/1
 *
 */
public class CommonDialog extends Dialog{

	private Context context;
	
	public static String JUST_ONE_BUTTON = "oneButton";
	
	public static String TWO_BUTTONS = "twoButtons";
	
	public CommonDialog(Context context) {
		super(context);
		this.context = context;
		this.setCanceledOnTouchOutside(false);
	}
	
	public CommonDialog(Context context, int theme) {
	    super(context, theme);
	    this.context = context;
	    this.setCanceledOnTouchOutside(false);
	}
	
	/**
	 *  初始化弹窗的相关内容与位置
	 *  
	 * @param title 弹窗的标题。
	 * @param message  弹窗中显示的内容。
	 * @param buttonValue 弹窗右边红色按钮上显示的内容。
	 * @param flag 标志显示的button个数： (TWO_BUTTONS: 2个; JUST_ONE_BUTTON: 1个。)
	 */
	public void initViews(String title,String message,String buttonValue,String flag){
		View viewRoot = getLayoutInflater().inflate(R.layout.update_dialog, null);  
		TextView tv_title = (TextView)viewRoot.findViewById(R.id.dialog_title);
		TextView tv_x_title = (TextView)viewRoot.findViewById(R.id.with_x_title);
		TextView tv_message = (TextView)viewRoot.findViewById(R.id.dialog_message);
		LinearLayout llBtn = (LinearLayout)viewRoot.findViewById(R.id.ll_btn);
		TextView tv_cancel = (TextView)viewRoot.findViewById(R.id.dialog_cancel);
		TextView tv_sure = (TextView)viewRoot.findViewById(R.id.dialog_sure);
		int height = 0;
		if(flag == null){
			tv_x_title.setVisibility(View.VISIBLE);
			llBtn.setVisibility(View.GONE);
			tv_title.setVisibility(View.GONE);
			tv_x_title.setText(title);
			height = 2;
		}else {
			height = 3;
			tv_x_title.setVisibility(View.GONE);
			llBtn.setVisibility(View.VISIBLE);
			tv_title.setVisibility(View.VISIBLE);
			tv_title.setText(title);
			tv_sure.setText(buttonValue);
			if(flag.equals(JUST_ONE_BUTTON)){
				tv_cancel.setVisibility(View.GONE);
			}else{
				tv_cancel.setVisibility(View.VISIBLE);
			}
		}
		tv_message.setText(message);
        setContentView(viewRoot);  
        
        //在中间显示
        getWindow().setGravity(Gravity.CENTER);  
        int screenHeight = ActivityHelper.screenHeight(context);
        int screenWidth = ActivityHelper.screenWidth(context);
        getWindow().setLayout(screenWidth*3/4, screenHeight*height/11);
        tv_cancel.setOnClickListener((android.view.View.OnClickListener)context);
        tv_sure.setOnClickListener((android.view.View.OnClickListener)context);
        tv_x_title.setOnClickListener((android.view.View.OnClickListener)context);
	}

}
