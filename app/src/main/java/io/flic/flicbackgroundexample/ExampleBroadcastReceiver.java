package io.flic.flicbackgroundexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import io.flic.lib.FlicBroadcastReceiver;
import io.flic.lib.FlicButton;

/**
 * Created by Emil on 2015-11-30.
 */
public class ExampleBroadcastReceiver extends FlicBroadcastReceiver {
	@Override
	protected void onRequestAppCredentials(Context context) {
		Config.setFlicCredentials();
	}

	@Override
	public void onButtonUpOrDown(Context context, FlicButton button, boolean wasQueued, int timeDiff, boolean isUp, boolean isDown) {
		if (isDown) {
			Notification notification = new Notification.Builder(context)
					.setSmallIcon(R.mipmap.ic_launcher)
					.setContentTitle("Button was pressed")
					.setContentText("Pressed last time at " + new Date())
					.build();
			((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, notification);
		}
	}

	@Override
	public void onButtonRemoved(Context context, FlicButton button) {
		Log.d("yo", "removed");
		Toast.makeText(context, "Button was removed", Toast.LENGTH_SHORT).show();
	}
}
