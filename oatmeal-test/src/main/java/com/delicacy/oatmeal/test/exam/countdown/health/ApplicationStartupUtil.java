package com.delicacy.oatmeal.test.exam.countdown.health;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ApplicationStartupUtil {
	// List of service checkers
	private static List<BaseHealthChecker> _services;

	// This latch will be used to wait on
	private static CountDownLatch _latch;

	private ApplicationStartupUtil() {
	}

	private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

	public static ApplicationStartupUtil getInstance() {
		return INSTANCE;
	}

	public static boolean checkExternalServices() throws Exception {
		// Initialize the latch with number of service checkers
		_latch = new CountDownLatch(2);
		// All add checker in lists
		_services = new ArrayList<BaseHealthChecker>();
		_services.add(new NetworkHealthChecker(_latch));
		// _services.add(new CacheHealthChecker(_latch));
		 _services.add(new DatabaseHealthChecker(_latch));

		// Start service checkers using executor framework
		ExecutorService executor = Executors.newFixedThreadPool(_services.size());

		for (final BaseHealthChecker v : _services) {
			executor.execute(v);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		// Now wait till all service are checked
		_latch.await();

		// Services are file and now proceed startup
		for (final BaseHealthChecker v : _services) {
			if (!v.isServiceUp()) {
				return false;
			}
		}
		return true;
	}
}