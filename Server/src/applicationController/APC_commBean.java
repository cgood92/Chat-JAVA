/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author cgood92
 */
public class APC_commBean implements Serializable {
    private String command;
    private HashMap data;
    
    public int convertLong(Object longInput)
    {
        return (int)(((Long) longInput).floatValue());
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.command);
        hash = 13 * hash + Objects.hashCode(this.data);
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
        final APC_commBean other = (APC_commBean) obj;
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "APC_commBean{" + "command=" + command + ", data=" + data + '}';
    }
    
}
