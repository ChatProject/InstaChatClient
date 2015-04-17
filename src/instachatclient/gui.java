/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Saurin
 */
public final class gui extends JFrame{
    
    JPanel pnl = new JPanel();
    JLabel lblUser = new JLabel("Username");
    JLabel lblPass = new JLabel("Password");
    JTextField txtUser = new JTextField();
    JTextField txtPass = new JTextField();
    JButton btnLogin = new JButton("Login");
    JButton btnRegister = new JButton("Register");
    
    String user;

    public gui() {
        txtUser.requestFocus(true);
        pnl.setLayout(new GridLayout(3,2));
        pnl.add(lblUser);
        pnl.add(txtUser);
        pnl.add(lblPass);
        pnl.add(txtPass);
        pnl.add(btnRegister);
        pnl.add(btnLogin);
        add(pnl);
        btnListener();
    }
    
    public void closeGui(){
        this.setVisible(false);
    }
    
    public void btnListener(){
        
    btnLogin.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                user  = txtUser.getText();
                //friendsGui fGui = new friendsGui(user);
                
                GuiActionHandler guiAction = new GuiActionHandler(txtUser.getText());
                try {
                    boolean flag = guiAction.login(txtUser.getText(), txtPass.getText());
                    if(flag == true){
                        closeGui();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    btnRegister.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                user = txtUser.getText();
                GuiActionHandler guiAction = new GuiActionHandler(user);
                try {
                    guiAction.register(txtUser.getText(), txtPass.getText());
                } catch (RemoteException ex) {
                    Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}// END of gui
