package com.itranswarp.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisClientFactory implements FactoryBean<RedisClient> {

	@Value("${spring.redis.standalone.host:localhost}")
	String host = "localhost";

	@Value("${spring.redis.standalone.port:6379}")
	int port = 6379;

	/**
	 * Timeout in seconds
	 */
	@Value("${spring.redis.standalone.timeout:1}")
	int timeout = 1;

	@Value("${spring.redis.standalone.password:}")
	String password = "";

	@Value("${spring.redis.standalone.database:0}")
	int database = 0;

	@Override
	public RedisClient getObject() throws Exception {
		RedisURI uri = RedisURI.Builder.redis(this.host, this.port).withPassword(this.password)
				.withDatabase(this.database).withTimeout(Duration.ofSeconds(this.timeout)).build();
		return RedisClient.create(uri);
	}

	@Override
	public Class<?> getObjectType() {
		return RedisClient.class;
	}

}
