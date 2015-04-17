/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Saurin
 */
public class ChatGui extends JFrame{
    JLabel lblname = new JLabel("Name: ");
    JTextArea rcdMessage = new JTextArea();
    JTextArea newMessage = new JTextArea();
    JButton btnSend = new JButton("Send");
    JPanel pnl = new JPanel();
    JPanel topPnl = new JPanel();
    JPanel middlePnl = new JPanel();
    JPanel bottomPnl = new JPanel();
    JScrollPane sPane = new JScrollPane(rcdMessage);
    
    String name = "Friend Name: ";
    String userName;
    String friendName;
    
    public ChatGui(){
        buildFrame();
    }
    
    public ChatGui(String userName, String friendName){
        this.userName = userName;
        this.friendName = friendName;
        buildFrame();
    }

    private void buildFrame() {
        add(pnl, BorderLayout.CENTER);
        pnl.add(topPnl, BorderLayout.NORTH);
        pnl.add(middlePnl, BorderLayout.CENTER);
        pnl.add(bottomPnl, BorderLayout.SOUTH);
        topPnl.add(lblname, BorderLayout.NORTH);
        middlePnl.add(sPane, BorderLayout.CENTER);
        bottomPnl.add(newMessage, BorderLayout.WEST);
        bottomPnl.add(btnSend, BorderLayout.EAST);
        rcdMessage.setEditable(false);
        rcdMessage.setLineWrap(true);
        rcdMessage.setWrapStyleWord(true);
        rcdMessage.setPreferredSize(new Dimension(300, 250));
        newMessage.setLineWrap(true);
        newMessage.setWrapStyleWord(true);
        newMessage.setPreferredSize(new Dimension(300,100));
        setTitle(name);
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        btnListener();
    }
    
//    public void setVisible(Object chatPanel){
//        chatGui.setVisible(true);
//    }
    
    public void btnListener(){
        
        btnSend.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    rcdMessage.append(newMessage.getText());//////////////////////////////////////////////////////////////////////
                    GuiActionHandler guiAction = new GuiActionHandler(userName);
                    guiAction.sendChat(friendName, userName, newMessage.getText());
                    newMessage.setText("");
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void append(String user, String message){
        rcdMessage.append(user + ": " + message);
    }
    
}
