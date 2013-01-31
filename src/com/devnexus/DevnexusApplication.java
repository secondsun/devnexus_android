package com.devnexus;

import java.net.MalformedURLException;
import java.net.URL;

 
import org.jboss.aerogear.android.Pipeline;
import org.jboss.aerogear.android.impl.pipeline.PipeConfig;

import android.app.Application;

public class DevnexusApplication extends Application {

	Pipeline pipeline;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		URL devnexusURL;
		try {
			devnexusURL = new URL(getString(R.string.devnexus_url));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		pipeline = new Pipeline(devnexusURL);
		
		PipeConfig config = new PipeConfig(devnexusURL, Speaker.class);
		config.setEndpoint("speakers.json");
		config.setName("speaker");
		config.setDataRoot("speakerList.speaker");
		
		pipeline.pipe(Speaker.class, config);
		
	}
	
}
