/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instachatclient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Saurin
 */
public class friendsGui extends JFrame{
    
    private String[] columnNames = {"Friends"};
    private Object[][] data = null;
    
    private DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    private JTable jTable1 = new JTable(tableModel);
    
    JPanel pnl = new JPanel();
    JButton btnAdd = new JButton("ADD");
    JButton btnDelete = new JButton("DELETE");
    JPanel btnPnl = new JPanel();
    
    gui g = new gui();
    String loginUser;
    
    InstanceChatWindow cWindow = new InstanceChatWindow();
    
    public friendsGui(String user) throws RemoteException{
        this.loginUser = user;
        System.out.println("User from friendsGui constructor: "+loginUser);
        buildFrame();
    }
    
    public friendsGui() throws RemoteException{
        buildFrame();
    }
    
    public void buildFrame() throws RemoteException{
        setLayout(new FlowLayout());
        add(pnl);
        pnl.add(btnPnl);
        btnPnl.add(btnAdd);
        btnPnl.add(btnDelete);
        add(new JScrollPane(jTable1));
        btnListener();
        generateFriends();
        jTable1.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
            try{
                int row = jTable1.getSelectedRow();
                String friendName = (jTable1.getModel().getValueAt(row, 0).toString());
                //ChatGui cGui;
                //                   need to block from opening new GUI each time if(cGui(loginUser,friendName).)
                //cGui = new ChatGui(loginUser, friendName);
                
                cWindow.emptyCheckMap(loginUser, friendName);
                //JOptionPane.showMessageDialog(null, friendName);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
        }            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        
        setVisible(true);
        setSize(200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void generateFriends() throws RemoteException{
        GuiActionHandler guiAction = new GuiActionHandler(loginUser);
        Vector<String> friendsList = guiAction.friendsList(loginUser);
        for (int i = 0; i < friendsList.size(); i++){
            String friend = friendsList.elementAt(i);
            tableModel.addRow(new Object[]
            {friend});
        }
        //jTable1.setEnabled(false);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new Font ("Serif", Font.PLAIN, 20));
//        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        TableColumn col1 = jTable1.getColumn("Friends");
//        col1.setWidth(25);
//        CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
//        col1.setCellRenderer(centerRenderer);
    }
    
    void reset(){
        tableModel.setRowCount(0);
    }
    
    public void btnListener(){
        
    btnAdd.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String s = JOptionPane.showInputDialog(null,"Enter friends name: ","Add Friend",JOptionPane.PLAIN_MESSAGE);
                try {
                    GuiActionHandler guiAction = new GuiActionHandler(loginUser);
                    boolean flag = guiAction.add(loginUser, s);
                    if (flag == true){
                        reset();
                        generateFriends();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    btnDelete.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String s = JOptionPane.showInputDialog(null,"Enter enemy name: ","Delete Enemy",JOptionPane.PLAIN_MESSAGE);
                GuiActionHandler guiAction = new GuiActionHandler(loginUser);
                try {
                    boolean flag = guiAction.delete(loginUser, s);
                    if (flag == true){
                        reset();
                        generateFriends();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//END of btnListener
}//END of friendsGui
