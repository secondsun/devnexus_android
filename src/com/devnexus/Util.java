package com.devnexus;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.pipeline.Pipe;

import android.util.Log;

public final class Util {

	private static final String TAG = Util.class.getSimpleName();
	
	private Util(){}
	
	public static <T> List<T> syncReadAll(Pipe<T> pipe) {
		final CountDownLatch latch = new CountDownLatch(1);
		final AtomicReference<Exception> exceptionRef = new AtomicReference<Exception>();
		final AtomicReference<List<T>> listRef = new AtomicReference<List<T>>();
		pipe.read(new Callback<List<T>>() {

			@Override
			public void onSuccess(List<T> data) {
				listRef.set(data);
				latch.countDown();
			}

			@Override
			public void onFailure(Exception e) {
				exceptionRef.set(e);
				latch.countDown();
			}
		});
		try {
			latch.await(150, TimeUnit.SECONDS);
			
			if (exceptionRef.get() != null) {
				Exception e = exceptionRef.get();
				Log.e(TAG, e.getMessage(), e);
				throw new RuntimeException(e);
			}
			
			return listRef.get();
			
		} catch (InterruptedException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
}
