/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.SYS_USERS;
import java.sql.SQLException;
import java.util.Collection;
import  logik.SYSUSERS.SYS_USERS;

/**
 *
 * @author nikolaev
 */
public interface SYS_USERS_DAO {
    public void addSYS_USERS(SYS_USERS sys_users) throws SQLException;
    public void updateSYS_USERS(Long SYS_USERS_ID,SYS_USERS sys_users) throws SQLException; 
    public void deleteSYS_USERS(SYS_USERS sys_users) throws SQLException;
    public SYS_USERS getSYS_USERSid(Long SYS_USERS_ID) throws SQLException;
    public Collection getAllSYS_USERS() throws SQLException;
}
