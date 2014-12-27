package com.example.chat;

import socket.SocketSession;
import Login.LoginBean;
import Login.LoginSingleton;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class CHAT extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void login (View view)
    {
    	EditText editText = (EditText) findViewById(R.id.userName);
    	String userName = editText.getText().toString();
    	editText = (EditText) findViewById(R.id.meetingID);
    	String meetingID = editText.getText().toString();
    	
    	LoginSingleton.getInstance().setUserName(userName);
    	LoginSingleton.getInstance().setMeetingId(meetingID);
    	LoginSingleton.getInstance().authenticate();
    	if(LoginSingleton.getInstance().getIsValid())
    	{
    		Intent intent = new Intent(this, ChatContainer.class);
        	startActivity(intent);
    	}
    	else
    	{
    		TextView textView = (TextView) findViewById(R.id.loginError);
    		textView.setText("Login was invalid.  Please try again.");
    	}
    }
}
