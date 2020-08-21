package com.masterdevskills.cha3.ext4;

public class JdbcConfig {
	private final String url;
	private final String username;
	private final String password;
	private final int maxPoolSize;
	private final int minPoolSize;

	public JdbcConfig(String url, String username, String password) {
		this(url, username, password, 2, 10);
	}

	public JdbcConfig(String url, String username, String password, int minPoolSize, int maxPoolSize) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.maxPoolSize = maxPoolSize;
		this.minPoolSize = minPoolSize;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}
}
