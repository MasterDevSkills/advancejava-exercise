package com.masterdevskills.cha3.ex2;

import com.masterdevskills.cha3.ReflectionUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.masterdevskills.cha3.SleepUtil.quietlySleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class ThreadPoolTest {
	@Test
	public void testTasksAreStopped() throws InterruptedException {
		var pool = new ThreadPool(1);
		var latch = new CountDownLatch(1);
		pool.submit(() -> {
			try {
				quietlySleep(10_000);
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
		ThreadPool threadPool = new ThreadPool(10);
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
		var usedBlockingQueue = Stream.of(ThreadPool.class.getDeclaredFields())
						.anyMatch(field -> ThreadPool.class.isAssignableFrom(field.getType()));
		assertTrue("Expected BlockingQueue to be used in ThreadPool", usedBlockingQueue);
	}

	@Test
	public void testSpuriousWakeUpHandledCorrectly() throws InterruptedException {
		var pool = new ThreadPool(10);
		quietlySleep(100);
		var list = ReflectionUtil.findFieldValue(pool, List.class);
		for (int i = 0; i < 20; i++) {
			synchronized (list) {
				list.notifyAll();
			}
		}
		runThreadPoolFunctionality();
	}
}