package com.masterdevskills.cha3.ex2;


//TODO: Implement this thread pool using BlockingQueue
public class ThreadPool {

	public ThreadPool(int poolSize) {
	}

	private Runnable take() throws InterruptedException {
		throw new UnsupportedOperationException("not implemented");
	}

	public void submit(Runnable job) {

	}

	public int getRunQueueLength() {

		throw new UnsupportedOperationException("not implemented");
	}

	public void shutdown() {

	}

	private class Job extends Thread {
		public Job(ThreadGroup group, String name) {
			super(group, name);
		}

		public void run() {
			//TODO
			// we run in an infinite loop:
			// remove the next job from the linked list using take()
			// we then call the run() method on the job
		}
	}
}


