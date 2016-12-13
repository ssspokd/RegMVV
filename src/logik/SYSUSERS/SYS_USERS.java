/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logik.SYSUSERS;

import java.util.logging.Logger;

/**
 *
 * @author nikolaev
 */
public class SYS_USERS {
    private static final Logger LOG = Logger.getLogger(SYS_USERS.class.getName());

    public static Logger getLOG() {
        return LOG;
    }
    private long  ID;
    private String LOGIN_USER;
    private String PASSWORD_USER;
    private long SUSERS_ID;


    public long getID() {
        return ID;
    }

    public String getLOGIN_USER() {
        return LOGIN_USER;
    }

    public String getPASSWORD_USER() {
        return PASSWORD_USER;
    }

    public long getSUSERS_ID() {
        return SUSERS_ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setLOGIN_USER(String LOGIN_USER) {
        this.LOGIN_USER = LOGIN_USER;
    }

    public void setPASSWORD_USER(String PASSWORD_USER) {
        this.PASSWORD_USER = PASSWORD_USER;
    }

    public void setSUSERS_ID(long SUSERS_ID) {
        this.SUSERS_ID = SUSERS_ID;
    }

    
    
    
}
