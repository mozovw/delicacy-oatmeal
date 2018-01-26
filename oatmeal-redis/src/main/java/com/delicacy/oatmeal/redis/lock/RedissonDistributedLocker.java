package com.delicacy.oatmeal.redis.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class RedissonDistributedLocker implements DistributedLocker {
	
	private RedissonClient redissonClient;

	@Override
	public void lock(String entityId) {
		RLock lock = redissonClient.getLock(entityId);
		lock.lock();
	}

	@Override
	public void unlock(String entityId) {
		RLock lock = redissonClient.getLock(entityId);
		lock.unlock();
	}

	@Override
	public void lock(String entityId, int leaseTime) {
		RLock lock = redissonClient.getLock(entityId);
		lock.lock(leaseTime, TimeUnit.SECONDS);
	}
	
	@Override
	public void lock(String entityId, TimeUnit unit ,int timeout) {
		RLock lock = redissonClient.getLock(entityId);
		lock.lock(timeout, unit);
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}
}
