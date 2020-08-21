package com.masterdevskills.cha3.ext3;

import com.masterdevskills.cha3.ex2.ThreadPool;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class ThreadPoolTest {
	@Test
	public void testTasksAreStopped() throws InterruptedException {
		var pool = new com.masterdevskills.cha3.ex2.ThreadPool(1);
		var latch = new CountDownLatch(1);
		pool.submit(() -> {
			try {
				sleep(10_000);
			} finally {
				latch.countDown();
			}
		});
		Thread.sleep(1000);
		pool.shutdown();
		boolean noTimeout = latch.await(100, TimeUnit.MILLISECONDS);
		assertTrue("timeout occurred - did not shutdown the threads in time?", noTimeout);
	}

	@Test
	public void testThreadPoolFunctionality() throws InterruptedException {
		runThreadPoolFunctionality();
	}

	private void runThreadPoolFunctionality() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(20);
		com.masterdevskills.cha3.ex2.ThreadPool threadPool = new com.masterdevskills.cha3.ex2.ThreadPool(10);
		var start = System.currentTimeMillis();
		for (int i = 0; i < 20; i++) {
			threadPool.submit(() -> {
				sleep(1000);
				countDownLatch.countDown();
			});
		}

		var noTimeout = countDownLatch.await(3, TimeUnit.SECONDS);
		var time = System.currentTimeMillis() - start;
		assertTrue("timeout occurred - did not shutdown threads on time", noTimeout);
		threadPool.shutdown();

		assertThat(threadPool.getRunQueueLength(), is(0));
		assertTrue("Total time exceed limit", time < 2400);
		assertFalse("executed faster than expected", time < 2000);
	}

	@Test
	public void testIfBlockingQueueUsed() {
		boolean foundBlockingQueueField = false;
		for (var field : com.masterdevskills.cha3.ex2.ThreadPool.class.getDeclaredFields()) {
			if (BlockingQueue.class.isAssignableFrom(field.getType())) {
				foundBlockingQueueField = true;
			}
		}
		assertTrue("Expected BlockingQueue to be used in ThreadPool", foundBlockingQueueField);
	}

	@Test
	public void testSpuriousWakeUpHandledCorrectly() throws InterruptedException, IllegalAccessException {
		var pool = new com.masterdevskills.cha3.ex2.ThreadPool(10);
		sleep(100);
		var list = findFieldValue(pool, List.class);
		for (int i = 0; i < 20; i++) {
			synchronized (list) {
				list.notifyAll();
			}
		}
		runThreadPoolFunctionality();
	}

	private <E> E findFieldValue(ThreadPool pool, Class<E> fieldType) throws IllegalAccessException {
		for (var field : pool.getClass().getDeclaredFields()) {
			if (fieldType.isAssignableFrom(field.getType())) {
				field.setAccessible(true);
				return fieldType.cast(field.get(pool));
			}
		}
		throw new IllegalArgumentException("Field of type " + fieldType + " not found");
	}

	private void sleep(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException ignored) {
		}
	}

}