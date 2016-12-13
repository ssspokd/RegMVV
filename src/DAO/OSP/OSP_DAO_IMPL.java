/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.OSP;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logik.OSP.OSP;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;


/**
 *
 * @author nikolaev
 */
public class OSP_DAO_IMPL implements DAO.OSP.OSP_DAO{
    private static final Logger LOG = Logger.getLogger(OSP_DAO_IMPL.class.getName());

    @Override
    public void addOSP(OSP osp) throws SQLException {
        Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(osp);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
    }

    
    @Override
    public void updateOSP(OSP osp) throws SQLException {
      Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.update(osp);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }  
    }

    @Override
    public OSP getOSPById(Long id) throws SQLException {
            Session session = null;
            OSP osp = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                osp = (OSP) session.load(OSP.class, id);
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            return osp; 
    }

    @Override
    public Collection getAllOSP() throws SQLException {
            Session session = null;
            List osp = new ArrayList<>();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                osp = session.createCriteria(OSP.class).list();
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
           return osp;            
      
    }

    @Override
    public void deleteOSP(OSP osp) throws SQLException {
       Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(osp);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }   
    }
     @Override
     public  OSP getContragentOSP(OSP osp)
     {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
