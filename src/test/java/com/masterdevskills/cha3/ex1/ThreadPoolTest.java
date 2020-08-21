package com.masterdevskills.cha3.ex1;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
		runThreadPoolFunciotnaltiy();
	}

	private void runThreadPoolFunciotnaltiy() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(20);
		ThreadPool threadPool = new ThreadPool(10);
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
	public void testSynchronizationOnList() throws IllegalAccessException, InterruptedException {
		var pool = new ThreadPool(10);
		var list = findFieldValue(pool, List.class);

		synchronized (list) {
			var thread = new Thread(() -> {
				pool.submit(() -> System.out.println("submit worked"));
			});
			thread.start();
			thread.join(100);
			assertTrue("In submit(), we expected the pool to be synchronizing list access using the list object as a monitor lock", thread.isAlive());
		}

		synchronized (list) {
			var thread = new Thread(() -> {
				System.out.println("pool.getRunQueueLength() = " + pool.getRunQueueLength());
			});
			thread.start();
			thread.join(100);
			assertTrue("In getRunQueueLength(), we expected the pool to be synchronizing list access using the list object as a monitor lock", thread.isAlive());
		}
		pool.shutdown();
	}

	@Test
	public void testSpuriousWakeUpHandledCorrectly() throws InterruptedException, IllegalAccessException {
		var pool = new ThreadPool(10);
		sleep(100);
		var list = findFieldValue(pool, List.class);
		for (int i = 0; i < 20; i++) {
			synchronized (list) {
				list.notifyAll();
			}
		}
		runThreadPoolFunciotnaltiy();
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