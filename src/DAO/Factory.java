package DAO;

import DAO.OSP.OSP_DAO;
import DAO.OSP.OSP_DAO_IMPL;
import DAO.SYS_USERS.SYS_USERS_DAO;
import DAO.SYS_USERS.SYS_USERS_DAO_IMPL;
import DAO.CONTRAGENT.CONTRAGENT_DAO;
import DAO.CONTRAGENT.CONTRAGENT_DAO_IMPL;
import java.util.logging.Logger;



public class Factory 
{
   private static  SYS_USERS_DAO  sys_users_dao = null;
   private static  OSP_DAO osp_dao = null;
   private static  Factory instance = null;
   private static  CONTRAGENT_DAO contragent_dao = null;
   private static  final Logger LOG = Logger.getLogger(Factory.class.getName());
   
   
    public static synchronized Factory getInstance() {
        if(instance == null)
        {
            instance = new Factory();
        }
        return instance;
    }

    public SYS_USERS_DAO geSys_users_dao() {
        if(sys_users_dao == null)
        {
            sys_users_dao  =  new SYS_USERS_DAO_IMPL();
        }
        return sys_users_dao;
    }
    
    public OSP_DAO getOsp_dao() {
        if(osp_dao == null)
        {
            osp_dao  =  new OSP_DAO_IMPL();
        }
        return osp_dao;
    }
    
    public CONTRAGENT_DAO getContragent_dao(){
        if(contragent_dao == null)
        {
            contragent_dao = new CONTRAGENT_DAO_IMPL();
        }
        return contragent_dao;
    }
}
