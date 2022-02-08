package com.buenoezandro.app.ws.shared;

import java.util.Random;

public final class Utils {
	private static final int BOUND = 100;
	private static Random random = new Random();
	
	private Utils() {
	}
	
	public static int generateUserId() {
		return random.nextInt(BOUND);
	}
}
