package com.jpac.glm.util;

import java.io.IOException;
import java.util.Random;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Util {

	public static Random globalRand = new Random(System.currentTimeMillis());
	
	public static char[] shuffleContent(char[] original) {
		int n = original.length;
		
		for(int i=0; i<n; i++) {
			int x = globalRand.nextInt(n);
			
			char temp = original[i];
			original[i] = original[x];
			original[x] = temp;
		}
		
		return original;
	}
	
	public static Typeface getFont(AssetManager mgr, String font) {
		return Typeface.createFromAsset(mgr, font);
	}
	
	public static Drawable getBitmapDrawable(AssetManager mgr, String image) throws IOException {
		return BitmapDrawable.createFromStream(mgr.open(image), null);
	}
	
	public static Bitmap getBitmap(AssetManager mgr, String image) throws IOException {
		return BitmapFactory.decodeStream(mgr.open(image));
	}
	
	public static Bitmap resize(Bitmap bitmap, double newSize) {
		return Bitmap.createScaledBitmap(bitmap, ((int) (bitmap.getWidth()*newSize)), ((int) (bitmap.getHeight()*newSize)), false);
	}
}
