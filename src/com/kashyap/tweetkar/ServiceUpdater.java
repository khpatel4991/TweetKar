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
				List<Status> timeline = ((TwitterHandle)getApplication()).twitter.getPublicTimeline();
				for (Status i : timeline)
				{
					Log.d(TAG, String.format("%s: %s", i.user.name, i.text));
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
		Log.d(TAG, "onDestroy()");
	}

	@Override
	public IBinder onBind(Intent intent)
	{

		return null;
	}
}
