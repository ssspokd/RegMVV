/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logik.CONTRAGENT;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import logik.OSP.OSP;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author nikolaev
 */
@Entity
@Table
public class CONTRAGENT implements Serializable 
{
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CONTRAGENT.class.getName());

    public static Logger getLOG() {
        return LOG;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private List<OSP> OSP;
    /**
     ID                               SMALLINT NOT NULL,
    NAME_CONTRAGENT                  "VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    AGENT_CODE_CONTRAGENT            "VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    AGENT_DEPT_CODE_CONTRAGENT       "VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    AGENT_AGREEMENT_CODE_CONTRAGENT  "VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    DATE_CREATE_CONTRAGENT           DATE NOT NULL,
    DATE_OF_CHANGE                   DATE NOT NULL,
    ID_OSP                           SMALLINT NOT NULL
     */
    private int ID                               ;//SMALLINT NOT NULL,
    private String NAME_CONTRAGENT                  ;//;//"VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    private String AGENT_CODE_CONTRAGENT            ;//;//"VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    private String AGENT_DEPT_CODE_CONTRAGENT       ;//;//"VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    private String AGENT_AGREEMENT_CODE_CONTRAGENT  ;//;//"VARCHAR(50)" NOT NULL /* "VARCHAR(50)" = VARCHAR(50) NOT NULL ,
    private Date DATE_CREATE_CONTRAGENT           ;//;//DATE NOT NULL,
    private Date DATE_OF_CHANGE                   ;//;//DATE NOT NULL,
    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinTable(name = "OSP_CONTRAGENT", joinColumns = @JoinColumn(name = "ID_CONTRAGENT"), inverseJoinColumns = @JoinColumn(name = "ID_OSP"))  
    @Column(name = "ID_OSP")
    private int ID_OSP;
    private OSP osp;
   // @ManyToOne(fetch = FetchType.LAZY,optional=true)
    //@JoinTable(name = "OSP_CONTRAGENT", joinColumns = @JoinColumn(name = "ID_CONTRAGENT"), inverseJoinColumns = @JoinColumn(name = "ID_OSP"))  
   
    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="tid")
    public int getID() {
        return ID;
    }

    @Column(name = "NAME_CONTRAGENT")
    public String getNAME_CONTRAGENT() {
        return NAME_CONTRAGENT;
    }

    @Column(name = "AGENT_AGREEMENT_CODE_CONTRAGENT")
    public String getAGENT_AGREEMENT_CODE_CONTRAGENT() {
        return AGENT_AGREEMENT_CODE_CONTRAGENT;
    }

    @Column(name = "AGENT_CODE_CONTRAGENT")
    public String getAGENT_CODE_CONTRAGENT() {
        return AGENT_CODE_CONTRAGENT;
    }

    @Column(name = "AGENT_DEPT_CODE_CONTRAGENT")
    public String getAGENT_DEPT_CODE_CONTRAGENT() {
        return AGENT_DEPT_CODE_CONTRAGENT;
    }

    @Column(name = "DATE_CREATE_CONTRAGENT")
    public Date getDATE_CREATE_CONTRAGENT() {
        return DATE_CREATE_CONTRAGENT;
    }

    @Column(name = "DATE_OF_CHANGE")
    public Date getDATE_OF_CHANGE() {
        return DATE_OF_CHANGE;
    }

    public int getID_OSP() {
        return ID_OSP;
    }

    public void setID_OSP(int ID_OSP) {
        this.ID_OSP = ID_OSP;
    }
    
    
   
    public void setAGENT_AGREEMENT_CODE_CONTRAGENT(String AGENT_AGREEMENT_CODE_CONTRAGENT) {
        this.AGENT_AGREEMENT_CODE_CONTRAGENT = AGENT_AGREEMENT_CODE_CONTRAGENT;
    }

    public void setAGENT_CODE_CONTRAGENT(String AGENT_CODE_CONTRAGENT) {
        this.AGENT_CODE_CONTRAGENT = AGENT_CODE_CONTRAGENT;
    }

    public void setAGENT_DEPT_CODE_CONTRAGENT(String AGENT_DEPT_CODE_CONTRAGENT) {
        this.AGENT_DEPT_CODE_CONTRAGENT = AGENT_DEPT_CODE_CONTRAGENT;
    }

    public void setDATE_CREATE_CONTRAGENT(Date DATE_CREATE_CONTRAGENT) {
        this.DATE_CREATE_CONTRAGENT = DATE_CREATE_CONTRAGENT;
    }

    public void setDATE_OF_CHANGE(Date DATE_OF_CHANGE) {
        this.DATE_OF_CHANGE = DATE_OF_CHANGE;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNAME_CONTRAGENT(String NAME_CONTRAGENT) {
        this.NAME_CONTRAGENT = NAME_CONTRAGENT;
    }
    
   
    
}
