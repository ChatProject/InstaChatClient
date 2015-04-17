/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Saurin
 */
public class InstanceChatWindow {
    static HashMap<String, Object> map = new HashMap<>();
    
    public InstanceChatWindow(){
        
    }
    
    public InstanceChatWindow(String user, String friend, String message){
        checkMap(user, friend, message);
        //buildFrame(user, friend);
    }
    
    public static void buildFrame(String user, String friend){
        System.out.println("new frame being built!");
        ChatGui cGui = new ChatGui(user, friend);
        map.put(friend, cGui );
        //chatWindows.add(user);
    }
    
    public static void emptyCheckMap(String user, String friend){
        boolean val = map.isEmpty();
        
        if(val == true){
            System.out.println(" hash map is Empty: " + val);
            buildFrame(user,friend);
            emptyCheckMap(user, friend);
        }else{
            for(Map.Entry<String, Object> entry: map.entrySet()){
                System.out.println("Inside of for loop");
                String key = entry.getKey();
                Object value = entry.getValue();

                if(key.equals(friend)){
                    ((ChatGui) value).setVisible(true);
                    //((ChatGui) value).append(friend, message);
                    System.out.println("Message Appended");
                }
                else{
                    System.out.println("went in side of else");
                    buildFrame(user, friend);
                    emptyCheckMap(user, friend);
                }
            }
        }
    }
    
    public static void checkMap(String user, String friend, String message){
        System.out.println("printing from chat window user: " + user + " friend: " + friend + " message: " + message);
        
        boolean val = map.isEmpty();
        
        if(val == true){
            System.out.println(" hash map is Empty: " + val);
            buildFrame(user,friend);
            checkMap(user, friend, message);
        }else{
            for(Map.Entry<String, Object> entry: map.entrySet()){
                System.out.println("Inside of for loop");
                String key = entry.getKey();
                Object value = entry.getValue();

                if(key.equals(friend)){
                    ((ChatGui) value).setVisible(true);
                    ((ChatGui) value).append(friend, message);
                    System.out.println("Message Appended");
                }
                else{
                    System.out.println("went in side of else");
                    buildFrame(user, friend);
                    checkMap(user, friend, message);
                }
            }
        }
    }
    
}
