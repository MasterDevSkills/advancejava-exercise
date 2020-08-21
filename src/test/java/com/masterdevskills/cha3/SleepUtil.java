package com.masterdevskills.cha3;

import static java.lang.Thread.currentThread;

public class SleepUtil {
	public static void quietlySleep(final long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// I said be quiet!
			currentThread().interrupt();
		}
	}
}
