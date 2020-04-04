package com.itranswarp.service;

import com.itranswarp.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ViewService {

	static final String KEY_VIEWS = "__views__";

	@Autowired
	RedisService redisService;

	@Autowired
	ViewDbService viewDbService;

	ExecutorService executor = Executors.newSingleThreadExecutor();

	public long increaseArticleViews(Long id) {
		long value = this.redisService.hincrby(KEY_VIEWS, id);
		if (value % 1000 == 0) {
			executor.submit(() -> {
				this.viewDbService.updateArticleViews(id, value);
			});
		}
		return value;
	}

	public long increaseWikiViews(Long id) {
		long value = this.redisService.hincrby(KEY_VIEWS, id);
		if (value % 1000 == 0) {
			executor.submit(() -> {
				this.viewDbService.updateWikiViews(id, value);
			});
		}
		return value;
	}

	public long increaseWikiPageViews(Long id) {
		long value = this.redisService.hincrby(KEY_VIEWS, id);
		if (value % 1000 == 0) {
			executor.submit(() -> {
				this.viewDbService.updateWikiPageViews(id, value);
			});
		}
		return value;
	}

	public long[] getViews(Object... ids) {
		var kvs = this.redisService.hmget(KEY_VIEWS, ids);
		if (kvs == null) {
			return new long[ids.length];
		}
		return kvs.stream().mapToLong(kv -> {
			if (kv.hasValue()) {
				String v = kv.getValue();
				return Long.parseLong(v);
			}
			return 0;
		}).toArray();
	}

	public long getArticleViews(Long id) {
		String value = this.redisService.hget(KEY_VIEWS, id);
		return value == null ? 0 : Long.parseLong(value);
	}

	public long getWikiViews(Long id) {
		String value = this.redisService.hget(KEY_VIEWS, id);
		return value == null ? 0 : Long.parseLong(value);
	}

	public long getWikiPageViews(Long id) {
		String value = this.redisService.hget(KEY_VIEWS, id);
		return value == null ? 0 : Long.parseLong(value);
	}

}
