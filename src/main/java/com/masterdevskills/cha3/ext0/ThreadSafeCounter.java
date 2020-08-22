package com.masterdevskills.cha3.ext0;



//TODO make it threadsafe
public class ThreadSafeCounter {
	private int count;

	public void increment() {
		count = count + 1;
	}

	public int getCount() {
		return count;
	}
}
