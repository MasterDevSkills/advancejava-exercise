package com.masterdevskills.cha3.ext0;

import com.masterdevskills.cha3.SleepUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ThreadSafeCounterTest {

	@Test
	public void testCount() throws InterruptedException {
		ThreadSafeCounter counter = new ThreadSafeCounter();
		var executorService = Executors.newFixedThreadPool(10);
		var iteration = 1000;
		CountDownLatch latch = new CountDownLatch(iteration);
		for (int i = 0; i < iteration; i++) {
			executorService.submit(() -> {
				counter.increment();
				SleepUtil.quietlySleep(1);
				latch.countDown();
			});
		}
		latch.await();
		executorService.shutdownNow();
		assertThat(counter.getCount(), equalTo(iteration));
	}
}