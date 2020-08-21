package com.masterdevskills.cha3.ext4;

import java.sql.Connection;

//TODO
// Hints: use BlockingQueue
// Use delegate pattern, create a new ProxyConnection
public class JdbcConnectionPool {
	private final JdbcConfig config;

	public JdbcConnectionPool(JdbcConfig config) {
		this.config = config;
		initializeConnectionPool();
	}

	//hints: initialize connection pool with min poolSize
	private void initializeConnectionPool() {
	}

	public int getTotalConnection() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public Connection getConnectionFromPool() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void returnConnectionToPool(/*ProxyConnection*/) {
		throw new UnsupportedOperationException("not yet implemented");
	}
}
