/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nikolaev
 */
public class getParamOSPmasrerDb
{
    private static final getParamOSPmasrerDb PMASRER_DB;
    private static final Logger LOG = Logger.getLogger(getParamOSPmasrerDb.class.getName());
    private static List<?> results;
    private static Query query;
    static {
        PMASRER_DB = new getParamOSPmasrerDb();
    }
    
    public static  Object[] getParam(String SQL,Session session)
    {
        Object[]  obj = new Object[10];  
        session.beginTransaction();
        query  = session.createSQLQuery(SQL);                  
        query.getQueryString();                                
        results = query.list();
        for(ListIterator<?> iter = results.listIterator(); iter.hasNext(); ) 
            {                   
                    Object[] row = (Object[]) iter.next();
                    for(int i = 0; i< row.length;i ++)
                    {
                        LOG.log(Level.INFO, row[i].toString());
                        obj[i] = row[i];
                     } 
                    
            }
             
       session.close();
       return obj; 
    }

    public getParamOSPmasrerDb() {
    }
}
