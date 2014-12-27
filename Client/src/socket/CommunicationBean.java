package socket;

import java.io.Serializable;
import java.util.HashMap;

public class CommunicationBean implements Serializable {
	private String command;
	private HashMap data;
	
	public CommunicationBean()
	{
		this.data = new HashMap();
	}
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Object getData(String key) {
		Object myReturn = this.data.get(key);
		return myReturn;
	}
	
	public void setData(HashMap data)
	{
		this.data = data;
	}
	
	public void addData(String key, Object value)
	{
		this.data.put(key, value);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommunicationBean other = (CommunicationBean) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CommunicationBean [command=" + command + ", data=" + data + "]";
	}
	
}
