package io.flic.flicbackgroundexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import junit.framework.Assert;

import io.flic.lib.FlicBroadcastReceiverFlags;
import io.flic.lib.FlicButton;
import io.flic.lib.FlicButtonCallback;
import io.flic.lib.FlicButtonCallbackFlags;
import io.flic.lib.FlicManager;
import io.flic.lib.FlicManagerInitializedCallback;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Config.setFlicCredentials();
	}

	public void grabButton(View v) {
		try {
			FlicManager.getInstance(this, new FlicManagerInitializedCallback() {
				@Override
				public void onInitialized(FlicManager manager) {
					manager.initiateGrabButton(MainActivity.this);
				}
			});
		} catch (AssertionError err) {
			Toast.makeText(this, "Flic App is not installed", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		FlicManager.getInstance(this, new FlicManagerInitializedCallback() {
			@Override
			public void onInitialized(FlicManager manager) {
				FlicButton button = manager.completeGrabButton(requestCode, resultCode, data);
				if (button != null) {
					button.registerListenForBroadcast(FlicBroadcastReceiverFlags.UP_OR_DOWN | FlicBroadcastReceiverFlags.REMOVED);
					Toast.makeText(MainActivity.this, "Grabbed a button", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Did not grab any button", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
