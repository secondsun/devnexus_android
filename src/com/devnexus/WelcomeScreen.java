package com.devnexus;

import java.util.List;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.pipeline.Pipe;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class WelcomeScreen extends ListActivity {

	private static final String TAG = WelcomeScreen.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		DevnexusApplication application = ((DevnexusApplication) getApplication());
		Pipe<Speaker> speakers = application.pipeline.get("speaker");

		speakers.read(new Callback<List<Speaker>>() {

			@Override
			public void onSuccess(List<Speaker> data) {
				setListAdapter(new ArrayAdapter<Speaker>(
						WelcomeScreen.this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, data));
			}

			@Override
			public void onFailure(Exception e) {
				Log.e(TAG, e.getMessage(), e);
				Toast.makeText(WelcomeScreen.this, e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_welcome_screen, menu);
		return true;
	}

}
