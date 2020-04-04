package com.itranswarp.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AsyncCommandCallback<T> {

	CompletableFuture<T> doInConnection(RedisAsyncCommands<String, String> commands);

}
