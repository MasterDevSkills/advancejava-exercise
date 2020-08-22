package com.masterdevskills.cha3.ext3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.masterdevskills.cha3.ReflectionUtil.findFieldValue;
import static com.masterdevskills.cha3.SleepUtil.quietlySleep;
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
				quietlySleep(10_000);
			} finally {
				latch.countDown();
			}
		});
		quietlySleep(1000);
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
				quietlySleep(1000);
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
		quietlySleep(100);
		var list = findFieldValue(pool, List.class);
		for (int i = 0; i < 20; i++) {
			synchronized (list) {
				list.notifyAll();
			}
		}
		runThreadPoolFunctionality();
	}


}