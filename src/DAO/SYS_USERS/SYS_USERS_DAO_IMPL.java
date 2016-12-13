/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.SYS_USERS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logik.SYSUSERS.SYS_USERS;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author nikolaev
 */
public class SYS_USERS_DAO_IMPL implements SYS_USERS_DAO
{
    private static final Logger LOG = Logger.getLogger(SYS_USERS_DAO_IMPL.class.getName());

    @Override
    public void addSYS_USERS(SYS_USERS sys_users) throws SQLException {
                Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(sys_users);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {

        session.close();
      }
    }
    }

    @Override
    public void updateSYS_USERS(Long SYS_USERS_ID, SYS_USERS sys_users) throws SQLException {
            Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.update(sys_users);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    }

    @Override
    public void deleteSYS_USERS(SYS_USERS sys_users) throws SQLException {
        Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(sys_users);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    }

    @Override
    public SYS_USERS getSYS_USERSid(Long SYS_USERS_ID) throws SQLException {
            Session session = null;
    SYS_USERS sys_users = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      sys_users = (SYS_USERS) session.load(SYS_USERS.class, SYS_USERS_ID);
    } catch (HibernateException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка findById", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return sys_users;
    }

    @Override
    public Collection getAllSYS_USERS() throws SQLException {
        Session session = null;
    List sys_users = new ArrayList<>();
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      sys_users = session.createCriteria(SYS_USERS.class).list();
    } catch (HibernateException e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
         return sys_users;
    }
    
}
