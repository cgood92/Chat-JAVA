package Login;

import java.util.HashMap;

import socket.CommunicationBean;
import socket.SocketSession;

public class LoginSingleton {
	private String userName;
	private String meetingId;
	private static LoginSingleton instance = new LoginSingleton();
	private SocketSession socketSession;
	private boolean isValid = false;

	public LoginSingleton() {
		this.socketSession = new SocketSession();
	}
	
	public static LoginSingleton getInstance()
	{
		return instance;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	public boolean getIsValid()
	{
		return this.isValid;
	}

	public void authenticate()
	{
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("LOGIN");
		commBean.addData("userName", this.userName);
		commBean.addData("meetingID", this.meetingId);
		CommunicationBean dataFromServer = this.socketSession.connect(commBean);
		if (dataFromServer.getCommand().equals("LOGIN") && dataFromServer.getData("sessionID") != null && !dataFromServer.getData("sessionID").equals(null))
		{
			System.out.println("Successfully logged in");
			this.isValid = true;
		}
		else
		{
			System.out.println(dataFromServer.getData("msg"));
		}
	}
}
