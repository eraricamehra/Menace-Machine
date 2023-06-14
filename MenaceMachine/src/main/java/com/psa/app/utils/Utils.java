package com.psa.app.utils;


import java.util.Random;


public class Utils {

	// TODO create method to generate a random integer number

	// TODO create method to write the result to file

	public static final Random generator = new Random();

	public static int getRandomNumber() {
		return generator.nextInt(9) + 1;
	}

	public static int getRandomArrLen() {
		return generator.nextInt(ApplicationConstants.ALPHA) + 1;
	}


//	int ar[3][3] = {{1,2,3},{4,5,6},{7,8,9}};



	
	

}
