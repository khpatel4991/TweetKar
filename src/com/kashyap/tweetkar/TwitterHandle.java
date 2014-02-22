// Application Object

package com.kashyap.tweetkar;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class TwitterHandle extends Application
{
	static final String TAG = "TwitterHandle";
	Twitter twitter;
	SharedPreferences prefs;
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		//Preferences Stuff
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String username = prefs.getString(getString(R.string.userName), "");
		String password = prefs.getString(getString(R.string.password), "");
		String server = prefs.getString(getString(R.string.serverName), "");
		
		Log.d(TAG, username);
		Log.d(TAG, password);
		Log.d(TAG, server);
		
		//Twitter Stuff
		twitter = new Twitter(username, password);
		twitter.setAPIRootUrl(server);
		
		Log.d(TAG, "onCreate()");
	}

}
