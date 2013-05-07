package com.jpac.allonepiece.util;

import java.util.Random;

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
}
