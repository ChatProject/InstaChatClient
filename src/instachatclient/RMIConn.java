/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import instachatrmi.InstaChatInterface;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Saurin
 */
public class RMIConn {
    String name = "10.0.0.29";
    InstaChatInterface stub;
    public RMIConn(){
        //String strName = "rmi://instachatrmi.InstaChatInterface/Login";
        //System.setProperty("java.security.policy","file:C:\\Users\\Saurin\\Documents\\NetBeansProjects\\InstaChatClient\\Client.policy");
        //System.setSecurityManager(new RMISecurityManager());
        
        try{
            stub = (InstaChatInterface) LocateRegistry.getRegistry(name).lookup("Login");
        }catch(Exception e){
            System.out.println("Client: Exception thrown looking up " + e);
        }
    }
}
