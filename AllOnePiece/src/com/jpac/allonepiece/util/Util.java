package com.jpac.allonepiece.util;

import java.util.Random;

import android.content.res.AssetManager;
import android.graphics.Typeface;

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
}
