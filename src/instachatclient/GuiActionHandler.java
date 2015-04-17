/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import instachatrmi.CallBack;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Saurin
 */
public class GuiActionHandler {
    
    boolean flag;
    Vector<String> friendsList = new Vector<String>();
    RMIConn rmiCon = new RMIConn();
    String user;
    
    public GuiActionHandler(String user){
        this.user = user;
        System.out.println("GuiActionHandler Constructor user : " + this.user);
    }
    
    public boolean login(String user, String pass) throws RemoteException{
        flag = rmiCon.stub.login(user, pass);
        System.out.println(" login flag : " + flag);
        
        if(flag == true){
            friendsGui fGui = new friendsGui(user);
            CallBackImp callBackImp = new CallBackImp(user, rmiCon.stub);
        }
        return flag;
    }
    
    public boolean getLoginFlag(){
        return flag;
    }
    
    public Vector<String> friendsList(String user) throws RemoteException{
        friendsList = rmiCon.stub.friends(user);
        return friendsList;
    }
    
    public boolean add (String user, String friend) throws RemoteException{
        System.out.println("User from add inside of GuiAction: " + this.user);
        boolean flag = rmiCon.stub.add(this.user, friend);
        return flag;
    }
    
    public boolean register(String user, String pass) throws RemoteException{
        boolean flag = rmiCon.stub.register(user, pass);
        return flag;
    }
    
    public boolean delete(String user, String enemy) throws RemoteException{
        System.out.println("Deleting this smuck from the friends List of: " + this. user + enemy);
        boolean flag = rmiCon.stub.delete(this.user, enemy);
        return flag;
    }
    
    public void sendChat(String to, String from, String message) throws RemoteException{
        System.out.println("GuiActionHandler Chat to: " + to + " from: " + from + " Message: " + message);
        rmiCon.stub.sendChat(to, from, message);
    }
}//END of GuiActionListener
