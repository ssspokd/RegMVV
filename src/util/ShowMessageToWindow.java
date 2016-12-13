/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.org.apache.xerces.internal.xs.StringList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nikolaev
 */
public class ShowMessageToWindow {
      public static void ShowMessageBox(String SQL,Object[] param)
    {
       String result = "";
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       Query query = session.createSQLQuery(SQL);
       List results = query.list();
       for(ListIterator iter = results.listIterator(); iter.hasNext(); ) 
            {                   
                    Object[] row = (Object[]) iter.next();
                    for(int i = 0; i < row.length;i ++)
                    {
                        System.out.println(row[i]); 
                        result  +=  param[i].toString() + "  "+row[i] +"\n";// + +  row[i] +  "\n";
                    }  
           }
       JOptionPane.showMessageDialog(null, result ,null, JOptionPane.DEFAULT_OPTION );
       session.close();                   
    }
}
