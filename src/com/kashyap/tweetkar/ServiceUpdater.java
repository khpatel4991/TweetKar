package com.kashyap.tweetkar;

import java.util.List;

import winterwell.jtwitter.Twitter.Status;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceUpdater extends Service
{
	static final String TAG = "ServiceUpdater";
	public boolean running = false;

	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d(TAG, "onCreate()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		new Thread()
		{
			public void run()
			{
				running = true;
				while (running)
				{
					List<Status> timeline = ((TwitterHandle) getApplication()).getTwitter()
							.getPublicTimeline();
					for (Status i : timeline)
					{
						Log.d(TAG, String.format("%s: %s", i.user.name, i.text));
					}
					int sec = Integer.parseInt(((TwitterHandle)getApplication()).prefs.getString("refreshInterval", "10"));
					Log.d(TAG, "Refresh Interval is " + sec);
					int millis = sec * 1000;
					try
					{
						Thread.sleep(millis, 0);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
		Log.d(TAG, "onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		running = false;
		Log.d(TAG, "onDestroy()");
	}

	@Override
	public IBinder onBind(Intent intent)
	{

		return null;
	}
}
