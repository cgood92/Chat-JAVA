package com.example.chat;

import socket.CommunicationBean;
import socket.SocketSession;
import Login.LoginSingleton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChatContainer extends ActionBarActivity {
	private SocketSession socketSession = new SocketSession();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_container);
		getAllChats();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat_container, menu);
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
	
	public void getAllChats()
	{
		TextView textView = (TextView) findViewById(R.id.chat_container_textView);
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("GETALLCHATS");
		commBean.addData("meeting", 9292);
		
		CommunicationBean dataFromServer = this.socketSession.connect(commBean);
		textView.setText(dataFromServer.getData("msg").toString());
	}
	
	public void sendMessage(View view)
	{
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("SENDCHAT");
		commBean.addData("msg", ((EditText) findViewById(R.id.newText)).getText().toString());
		commBean.addData("username", LoginSingleton.getInstance().getUserName());
		commBean.addData("meeting", 9292);
		CommunicationBean dataFromServer = this.socketSession.connect(commBean);
		
		TextView textView = (TextView) findViewById(R.id.chat_container_textView);
		textView.append("\n" + dataFromServer.getData("username") + ": " + dataFromServer.getData("msg"));
		((EditText) findViewById(R.id.newText)).setText("");
	}
}
