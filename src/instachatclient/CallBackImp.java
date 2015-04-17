/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import instachatrmi.CallBack;
import instachatrmi.InstaChatInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author Saurin
 */
public class CallBackImp extends UnicastRemoteObject implements CallBack, Runnable{
    private InstaChatInterface client;
    private String name = null;
    InstanceChatWindow cWindow = new InstanceChatWindow();
    
    public CallBackImp(String name, InstaChatInterface client) throws RemoteException{
        this.client = client;
        this.name = name;
        client.connect(this, name);
        //client.connect((CallBack) client);
    }

    @Override
    public String sendMsg(String to, String from, String message) throws RemoteException {
        System.out.println("Message received From: "+ from + " Message: "+ message);
        
        //InstanceChatWindow cWindow = new InstanceChatWindow();
        cWindow.checkMap(to, from, message);
        return message;
    }

    @Override
    public void run() {
        while(true){
            
        }
    }
}// END of CallBackImp
