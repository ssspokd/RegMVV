/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.OSP;


import java.sql.SQLException;
import java.util.Collection;
import logik.OSP.OSP;

/**
 *
 * @author nikolaev
 */
public interface OSP_DAO {
    public void addOSP(OSP osp) throws SQLException;   //добавить 
    public void updateOSP(OSP osp) throws SQLException;//обновить 
    public OSP getOSPById(Long id) throws SQLException;    //получить стедента по id
    public Collection getAllOSP() throws SQLException;              //получить всех 
    public void deleteOSP(OSP osp) throws SQLException;//удалить 
    public OSP getContragentOSP(OSP osp) throws SQLException;
}
