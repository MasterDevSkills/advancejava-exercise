package com.masterdevskills.cha3.ex1;


//TODO: Create a thread group field
// Create a LinkedList field containing Runnable
// Hint: Since LinkedList is not thread-safe, we need to synchronize it
public class ThreadPool {
	public ThreadPool(int poolSize) {
	}

	private Runnable take() throws InterruptedException {
		//TODO if the LinkedList is empty, we wait
		// remove the first job from the LinkedList and return it
		throw new UnsupportedOperationException("not implemented");
	}

	public void submit(Runnable job) {
		//TODO Add the job to the LinkedList and notifyAll
	}

	public int getRunQueueLength() {
		//TODO return the length of the LinkedList
		// remember to also synchronize!
		throw new UnsupportedOperationException("not implemented");
	}

	public void shutdown() {
		//TODO this should call stop() on the ThreadGroup.
	}

	private class Worker extends Thread {
		public Worker(ThreadGroup group, String name) {
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


