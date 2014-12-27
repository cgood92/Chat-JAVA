package socket;

import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;


public class SocketSession {
	private final class CallableImplementation implements Callable {
		private final CommunicationBean commBean;
		private final Handler myHandler;

		private CallableImplementation(CommunicationBean commBean,
				Handler myHandler) {
			this.commBean = commBean;
			this.myHandler = myHandler;
		}

		@Override
		public CommunicationBean call() throws Exception {
			//Communication Bean
			CommunicationBean dataFromServerBean = new CommunicationBean();
			try {
				//Create our socket
				toServer = new Socket("10.0.3.2", 9292);
				
				//JSON input/output streams
		        final JSONInputStream inFromServer = new JSONInputStream(toServer.getInputStream());
		        final JSONOutputStream outToServer = new JSONOutputStream(toServer.getOutputStream());
				
				//Try sending the bean
				try 
				{
					outToServer.writeObject(commBean);
					//Read from the server
					final HashMap dataFromServer = (HashMap)inFromServer.readObject();
					dataFromServerBean.setCommand(dataFromServer.get("command").toString());
					dataFromServerBean.setData((HashMap) dataFromServer.get("data"));
					myHandler.post(new Runnable(){
						@Override
						public void run(){
						}
					});
					toServer.close();
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return dataFromServerBean;
		}
	}

	//No longer in the private class
	private Socket toServer;
	HashMap dataFromServer = null;
	
	public CommunicationBean connect(final CommunicationBean commBean){		
		final Handler myHandler = new Handler();
		ExecutorService exec=Executors.newCachedThreadPool();
		Future<CommunicationBean> result = exec.submit(new CallableImplementation(commBean, myHandler));
		CommunicationBean fromServerCommBean = null;
		try {
			fromServerCommBean = result.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return fromServerCommBean;
	}
}
