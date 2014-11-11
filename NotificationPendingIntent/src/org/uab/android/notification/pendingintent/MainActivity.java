package org.uab.android.notification.pendingintent;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	// Notification ID for future updates
	private static final int RANDOM_NOTIFICATION_ID = 1;
	
	// Notification fields
	private static final String tickerText = "This text appears when first receiving the Notification.";
	private static final String notificationTitle = "Notification Title";
	private static final String notificationContent = "You have received a new notification!";
	
	// Vibration pattern to make the device vibrate when the Notification is issued
	private static final long[] VIBRATE_PATTERN = { 0, 200, 200, 300 };
	
	// Count the notifications that have been issued from this Activity
	private int notificationCount = 0;
	
	private NotificationManager notificationManager;

	Button	newNotificationButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		newNotificationButton = (Button) findViewById(R.id.button1);
		
		// Obtain a reference to the NotificationManager
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		// Set the action to be performed when the user clicks on the notification
		Intent notificationIntent = new Intent(this, HandleNotificationActivity.class);
		final PendingIntent notificationPendingIntent = PendingIntent
				.getActivity(
						this, 							// Context
						0, 								// Request code
						notificationIntent, 			// Intent to be launched
						Intent.FLAG_ACTIVITY_NEW_TASK	// Flags
						);
		
		// Set the Button's OnClickListener
		newNotificationButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Build the notification
				NotificationCompat.Builder notificationBuilder = 
						new NotificationCompat.Builder(MainActivity.this)
					.setAutoCancel(true)
					.setTicker(tickerText)
					.setContentTitle(notificationTitle)
					.setContentText(notificationContent)
					.setNumber(++notificationCount)
					.setSmallIcon(android.R.drawable.stat_sys_warning)
					.setContentIntent(notificationPendingIntent)
					.setVibrate(VIBRATE_PATTERN);
				
				// Pass the notification to the NotificationManager
				notificationManager.notify(
						RANDOM_NOTIFICATION_ID, 		// Notification ID
						notificationBuilder.build()		// Notification build previously
						);
			}
		});
		
	}
}
