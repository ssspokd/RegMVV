/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.CONTRAGENT;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logik.CONTRAGENT.CONTRAGENT;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;
/**
 *
 * @author nikolaev
 */
public class CONTRAGENT_DAO_IMPL implements CONTRAGENT_DAO
{
    private static final Logger LOG = Logger.getLogger(CONTRAGENT_DAO_IMPL.class.getName());

    @Override
    public void addCONTRAGENT(CONTRAGENT contragent) throws SQLException {
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(contragent);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }  }

    @Override
    public void updateCONTRAGENT(CONTRAGENT contragent) throws SQLException {
            Session session = null; 
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.update(contragent);
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
    public CONTRAGENT getCONTRAGENTById(Long id) throws SQLException {
            Session session = null;
            CONTRAGENT contragent = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                contragent = (CONTRAGENT) session.load(CONTRAGENT.class, id);
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            return contragent;   }

    @Override
    public Collection getAllCONTRAGENT() throws SQLException {
            Session session = null;
            List contragent = new ArrayList<>();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                contragent = session.createCriteria(CONTRAGENT.class).list();
            } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
           return contragent;       
    }

    @Override
    public void deleteCONTRAGENT(CONTRAGENT contragent) throws SQLException {
    Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(contragent);
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
    public CONTRAGENT getContragentCONTRAGENT(CONTRAGENT contragent) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
