// Application Object

package com.kashyap.tweetkar;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
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
		
		/*//Preferences Stuff
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String username = prefs.getString("username", "");
		String password = prefs.getString("password", "");
		String server = prefs.getString("serverName", "");
*/
		
		//Twitter Stuff
		twitter = new Twitter("student", "password");
		twitter.setAPIRootUrl("http://yamba.marakana.com/api");
		
		Log.d(TAG, "onCreate()");
	}

}
