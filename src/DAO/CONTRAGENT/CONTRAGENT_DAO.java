/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.CONTRAGENT;
import java.sql.SQLException;
import java.util.Collection;
import logik.CONTRAGENT.CONTRAGENT;

/**
 *
 * @author nikolaev
 */
public interface CONTRAGENT_DAO 
{
    public void addCONTRAGENT(CONTRAGENT contragent) throws SQLException;   //добавить 
    public void updateCONTRAGENT(CONTRAGENT contragent) throws SQLException;//обновить 
    public CONTRAGENT getCONTRAGENTById(Long id) throws SQLException;    //получить стедента по id
    public Collection getAllCONTRAGENT() throws SQLException;              //получить всех 
    public void deleteCONTRAGENT(CONTRAGENT contragent) throws SQLException;//удалить 
    public CONTRAGENT getContragentCONTRAGENT(CONTRAGENT contragent) throws SQLException;
}
