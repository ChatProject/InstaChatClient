/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import javax.swing.JFrame;

/**
 *
 * @author Saurin
 */
public class InstaChatClient {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gui gLogin = new gui();
        gLogin.setVisible(true);
        gLogin.setSize(300,200);
        gLogin.setLocationRelativeTo(null);
        gLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }// END of main
}// END of InstaChatClient
