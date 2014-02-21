package com.kashyap.tweetkar;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ServiceRefresh extends IntentService
{
	static final String TAG = "RefreshService";
	Twitter twitter;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		twitter = new Twitter("student", "password");
		twitter.setAPIRootUrl("http://yamba.marakana.com/api");
		Log.d(TAG, "onCreate()");
	}
	
	public ServiceRefresh()
	{
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		List<Status> timeline = twitter.getPublicTimeline();
		for(Status i:timeline)
		{
			Log.d(TAG, String.format("%s: %s", i.user.name, i.text));
		}
		Log.d(TAG, "onRefresh()");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}
	
}
