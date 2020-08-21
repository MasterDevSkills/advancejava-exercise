package com.masterdevskills.cha3.ext4;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.masterdevskills.cha3.SleepUtil.quietlySleep;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.junit.Assert.*;

class JdbcConnectionPoolTest {

	private JdbcConnectionPool jdbcConnectionPool;

	@BeforeEach
	void setUp() {
		jdbcConnectionPool = new JdbcConnectionPool(new JdbcConfig("jdbc:h2:mem:testdb", "sa", "password"));
	}

	@Test
	void testConnection() throws InterruptedException {
		final int ITERATIONS = 100;
		final int threadCount = 10;
		final int workTimeMs = 3;
		final int restTimeMs = 1;
		final ExecutorService threadPool = newFixedThreadPool(threadCount);
		final CountDownLatch allThreadsDone = new CountDownLatch(ITERATIONS);
		final AtomicReference<Exception> ref = new AtomicReference<>(null);

		final AtomicInteger count = new AtomicInteger();
		for (int i = 0; i < ITERATIONS; i++) {
			threadPool.submit(() -> {
				if (ref.get() == null) {
					quietlySleep(restTimeMs);
					try (Connection conn = jdbcConnectionPool.getConnectionFromPool()) {
						quietlySleep(workTimeMs);
						count.incrementAndGet();
					} catch (Exception e) {
						ref.set(e);
					}
				}
				allThreadsDone.countDown();
			});
		}

		allThreadsDone.await();
		threadPool.shutdown();

		System.out.println(count.get());
		Assert.assertTrue((ref.get() == null));
		Assert.assertTrue((count.get() == ITERATIONS));
	}

	@Test
	public void testConcurrentClose() throws Exception {
		try (var connection = jdbcConnectionPool.getConnectionFromPool()) {
			ExecutorService executorService = Executors.newFixedThreadPool(10);

			List<Future<?>> futures = new ArrayList<>();

			for (int i = 0; i < 500; i++) {
				final PreparedStatement preparedStatement =
								connection.prepareStatement("");

				futures.add(executorService.submit((Callable<Void>) () -> {
					preparedStatement.close();
					return null;
				}));
			}

			executorService.shutdown();

			for (Future<?> future : futures) {
				future.get();
			}
		}
	}

	@Test
	public void testStatementClose() throws SQLException {

		assertTrue("Total connections not as expected", jdbcConnectionPool.getTotalConnection() >= 1);

		try (Connection connection = jdbcConnectionPool.getConnectionFromPool()) {
			assertNotNull(connection);

			assertTrue("Total connections not as expected", jdbcConnectionPool.getTotalConnection() >= 1);

			Statement statement = connection.createStatement();
			assertNotNull(statement);

			connection.close();

			assertFalse(statement.isClosed());
		}
	}
}