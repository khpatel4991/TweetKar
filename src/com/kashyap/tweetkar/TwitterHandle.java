// Application Object

package com.kashyap.tweetkar;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class TwitterHandle extends Application implements OnSharedPreferenceChangeListener
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
		prefs.registerOnSharedPreferenceChangeListener(this);
		Log.d(TAG, "onCreate()");
	}
	
	public Twitter getTwitter()
	{
		if(twitter == null)
		{
			String username = prefs.getString(getString(R.string.userName), "");
			String password = prefs.getString(getString(R.string.password), "");
			String server = prefs.getString(getString(R.string.serverName), "");
			twitter = new Twitter(username, password);
			twitter.setAPIRootUrl(server);
		}
		return twitter;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1)
	{
		twitter = null;
	}

}
