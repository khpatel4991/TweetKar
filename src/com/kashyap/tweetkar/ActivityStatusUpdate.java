package com.kashyap.tweetkar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityStatusUpdate extends Activity implements OnClickListener
{
	static final String TAG = "ActivityStatusUpdate";
	EditText EditTextTweetBox;
	Button buttonTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.status);
		
		EditTextTweetBox = (EditText) findViewById(R.id.EditTextTweetBox);
		buttonTweet = (Button) findViewById(R.id.buttonTweet);
		
		buttonTweet.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0)
	{
		final String status = EditTextTweetBox.getText().toString();
		Log.d("TweetButtonLog", "onClick() with status '" + status + "'");
		new PostToTwitter().execute(status);
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
	}


	class PostToTwitter extends AsyncTask<String, Void, String>
	{
		/* NewThread */
		@Override
		protected String doInBackground(final String... params)
		{
			((TwitterHandle)getApplication()).getTwitter().setStatus(params[0]);
			return "Posted '" + params[0] + "'";
		}

		@Override
		protected void onPostExecute(String result)
		{
			/* UI Thread */
			super.onPostExecute(result);
			Toast.makeText(ActivityStatusUpdate.this, result, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = new Intent(this, ServiceUpdater.class);
		Intent intentRefresh = new Intent(this, ServiceRefresh.class);
		Intent intentPrefs = new Intent(this, ActivityPrefs.class);
		
		switch(item.getItemId())
		{
		case R.id.itemStartService:
			startService(intent);
			return true;
			
		case R.id.itemStopService:
			stopService(intent);
			return true;
		
		case R.id.itemRefresh:
			startService(intentRefresh);
			return true;
			
		case R.id.itemPreferences:
			startActivity(intentPrefs);
			return true;
		
		default:
			return false;
		}
	}

}