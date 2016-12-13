/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingHost;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author nikolaev
 */
public class pingHost {
    private static final pingHost P_HOST;
    
    static 
    {
        P_HOST = new pingHost();
    }
    
    public pingHost(){}
    
    public static  boolean getPingHost(String ipAddress) throws IOException
    {
        
        InetAddress inet;
        try {
            inet = InetAddress.getByName(ipAddress);
             if(inet.isReachable(5000))
                {   
                     return true;
                }
        } 
        catch (UnknownHostException ex) {
            Logger.getLogger(pingHost.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sending Ping Request to " + ipAddress);
       
        return false;
    }
    
    public static  void getPingHost2(String ipAddress,String title) throws IOException
    {
        try {
             
             InetAddress inet = InetAddress.getByName(ipAddress);
             if(inet.isReachable(5000))
                {   
                    JOptionPane.showMessageDialog(null, "Доступ до ОСП: " + title +", есть",  "ОСП: " + title, JOptionPane.DEFAULT_OPTION );
                }
              else
                {
                  JOptionPane.showMessageDialog(null,  "Доступ до ОСП: " + title +", отсутсвует "  + ipAddress, "ОСП: " + "Доступа нет", JOptionPane.DEFAULT_OPTION );
                }
            } 
            catch (UnknownHostException ex)
            {
                 Logger.getLogger(pingHost.class.getName()).log(Level.SEVERE, null, ex);
                 JOptionPane.showMessageDialog(null, "error"  , ex.toString()  , JOptionPane.DEFAULT_OPTION );              
            }
    }
    
}
