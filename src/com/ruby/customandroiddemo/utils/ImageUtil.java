/**
 * @Description: TODO
 * @author: Administrator
 * @version: 1.0
 * @see 
 */

package com.ruby.customandroiddemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * 图片处理工具类
 * 
 * @author Bob
 * 
 */
public class ImageUtil {

	public static void saveBitmap2File(Bitmap mBitmap, String filePath)
			throws IOException {
		File f = new File(filePath);
		f.createNewFile();
		FileOutputStream fOut = null;
		fOut = new FileOutputStream(f);
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		fOut.flush();
		fOut.close();
	}

	/**
	 * 从资源获取默认图片
	 * 
	 * @param res
	 * @param resid
	 * @return
	 */
	public static Bitmap getDefaultBitmap(Resources res, int resid) {
		return BitmapFactory.decodeResource(res, resid);
	}

	/**
	 * 从资源获取圆角默认图片
	 * 
	 * @param res
	 * @param resid
	 * @return
	 */
	public static Bitmap getDefaultBitmapRoundedCorner(Resources res, int resid) {
		Bitmap bitmap = BitmapFactory.decodeResource(res, resid);
		return bitmap;
	}

	/**
	 * drawable到Bitmap的转换方法
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public static Bitmap byteBuffer2Bitmap(ByteBuffer data, int maxSize) {
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		Bitmap bm = BitmapFactory.decodeByteArray(data.array(), 0,
				data.limit(), o);
		if (bm != null) {
			bm.recycle();
			bm = null;
		}
		int sampleSize = 1;
		if (o.outHeight > maxSize || o.outWidth > maxSize) {
			sampleSize = (int) Math.pow(
					2,
					(int) Math.round(Math.log(maxSize
							/ (double) Math.max(o.outHeight, o.outWidth))
							/ Math.log(0.5)));
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = sampleSize;
		return BitmapFactory.decodeByteArray(data.array(), 0, data.limit(),
				opts);
	}

	/**
	 * 根据图片路径得到图片的缩率图，不致于加载过大图片出现内存溢出
	 * 
	 * @param bytes
	 * @return
	 */
	public static Bitmap getBitmapByBytes(String imagePath){  
		File file = new File(imagePath);
		if(!file.exists())
			return null;
    	TiffDecoder.nativeTiffOpen(imagePath);
		int pixels[] = TiffDecoder.nativeTiffGetBytes();
		int tif_width = TiffDecoder.nativeTiffGetWidth();
		int tif_height= TiffDecoder.nativeTiffGetHeight();
		Bitmap bitmap = Bitmap.createBitmap(pixels,
				tif_width,
				tif_height,
				Bitmap.Config.ARGB_8888);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte bytes[] = baos.toByteArray(); 
		try {
			baos.close();
			bitmap.recycle();
			bitmap = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		int sampleSize = 4; //默认缩放为1  
        BitmapFactory.Options options = new BitmapFactory.Options();  
        //不再只加载图片实际边缘  
        options.inJustDecodeBounds = false;  
        //并且制定缩放比例  
        options.inSampleSize = sampleSize;  
        
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);  
    }
	
}
