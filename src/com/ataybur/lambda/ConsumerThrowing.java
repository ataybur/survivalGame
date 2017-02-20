package com.ataybur.lambda;

import java.util.function.Consumer;

@FunctionalInterface
public interface ConsumerThrowing<T> extends Consumer<T> {
	@Override
	default void accept(T t) {
		try {
			acceptThrowing(t);
		} catch (Exception e) {
			throwAsUnchecked(e);
		}
	}

	void acceptThrowing(T t) throws Exception;

	@SuppressWarnings("unchecked")
	static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
		throw (E) exception;
	}
}
