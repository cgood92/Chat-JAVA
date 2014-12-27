/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChatServer;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class CommunicationBean implements Serializable{
	
    private String status;
    private ArrayList data;
    
    CommunicationBean(String done, ArrayList data) {
        this.setData(data);
        this.setStatus(status);
    }    
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public ArrayList getData() {
    	return data;
    }
    
    public void setData(ArrayList data) {
    	this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommunicationBean other = (CommunicationBean) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
    

}