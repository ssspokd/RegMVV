/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logik.OSP;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import logik.CONTRAGENT.CONTRAGENT;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.mapping.OneToMany;

/**
 *
 * @author nikolaev
 */
@Entity
@Table
public class OSP implements Serializable
{
    private static final Logger LOG = Logger.getLogger(OSP.class.getName());
    //private static final long serialVersionUID = 1L;
    private static final long serialVersionUID = -5170875020617735653L;
    public static Logger getLOG() {
        return LOG;
    }
    /**
    ID               SMALLINT NOT NULL,
    NAME_OSP         VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    IP_OSP           VARCHAR(16) CHARACTER SET WIN1251 NOT NULL,
    PASSWORD_OSP     VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    LOGIN_OSP        VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    ALIASES_OSP      VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    VKSP_OSP         VARCHAR(30) CHARACTER SET WIN1251 NOT NULL,
    DATE_CREATE_OSP  DATE NOT NULL,
    DATE_OF_CHANGE   DATE NOT NULL
     **/
    private  Long ID ;//              SMALLINT NOT NULL,
    private String NAME_OSP        ;// VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    private String IP_OSP          ;// VARCHAR(16) CHARACTER SET WIN1251 NOT NULL,
    private String PASSWORD_OSP    ;// VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    private String LOGIN_OSP       ;// VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    private String ALIASES_OSP     ;// VARCHAR(25) CHARACTER SET WIN1251 NOT NULL,
    private String VKSP_OSP        ;// VARCHAR(30) CHARACTER SET WIN1251 NOT NULL,
    private Date DATE_CREATE_OSP ;// DATE NOT NULL,
    private Date DATE_OF_CHANGE  ;// DATE NOT NULL
    @OneToMany(mappedBy = "osp", fetch = FetchType.LAZY)
    private Set<CONTRAGENT> contragent;
    
    @Column(name = "ALIASES_OSP")   
    public String getALIASES_OSP() {
        return ALIASES_OSP;
    }

    @Column(name = "DATE_OF_CHANGE", nullable = false, updatable = false, insertable = false)
    public Date getDATE_CREATE_OSP() {
        return DATE_CREATE_OSP;
    }

    
    @Column(name = "DATE_OF_CHANGE",nullable = false, updatable = false, insertable = false)
    public Date getDATE_OF_CHANGE() {
        return DATE_OF_CHANGE;
    }
    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")  
    public Long getID() {
        return ID;
    }

    @Column(name = "IP_OSP")
    public String getIP_OSP() {
        return IP_OSP;
    }
    
    @Column(name = "LOGIN_OSP")
    public String getLOGIN_OSP() {
        return LOGIN_OSP;
    }
    
    @Column(name = "NAME_OSP")
    public String getNAME_OSP() {
        return NAME_OSP;
    }
   
    @Column(name = "PASSWORD_OSP")
    public String getPASSWORD_OSP() {
        return PASSWORD_OSP;
    }

    @Column(name = "VKSP_OSP")
    public String getVKSP_OSP() {
        return VKSP_OSP;
    }

    public void setALIASES_OSP(String ALIASES_OSP) {
        this.ALIASES_OSP = ALIASES_OSP;
    }

    public void setDATE_CREATE_OSP(Date DATE_CREATE_OSP) {
        this.DATE_CREATE_OSP = DATE_CREATE_OSP;
    }

    public void setDATE_OF_CHANGE(Date DATE_OF_CHANGE) {
        this.DATE_OF_CHANGE = DATE_OF_CHANGE;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setIP_OSP(String IP_OSP) {
        this.IP_OSP = IP_OSP;
    }

    public void setLOGIN_OSP(String LOGIN_OSP) {
        this.LOGIN_OSP = LOGIN_OSP;
    }

    public void setNAME_OSP(String NAME_OSP) {
        this.NAME_OSP = NAME_OSP;
    }

    public void setPASSWORD_OSP(String PASSWORD_OSP) {
        this.PASSWORD_OSP = PASSWORD_OSP;
    }

    public void setVKSP_OSP(String VKSP_OSP) {
        this.VKSP_OSP = VKSP_OSP;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OSP other = (OSP) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }
    
    
    
}
