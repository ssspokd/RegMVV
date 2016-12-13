/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regmvv;


import DAO.Factory;
import csvviwer.FXMLDocumentController;
import hash.hash;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import logik.CONTRAGENT.CONTRAGENT;
import logik.OSP.OSP;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xml.sax.SAXException;
import pingHost.pingHost;
import util.HibernateUtil;
import util.NewHibernateUtil;
import util.ShowMessageToWindow;
import util.edidConnect_db_cfg;
import util.getParamOSPmasrerDb;


/**
 *
 * @author nikolaev
 */
public class MainForm extends Application implements Initializable 
{       
    private static final Logger LOG = Logger.getLogger(MainForm.class.getName());
    /**
     * Stage по умолчанию
     */
    private static Stage stage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    private Collection<OSP> osp;
    private OSP objOSP  = null;
    private final CONTRAGENT objContragent  = null;
    
    @FXML
    private Menu actionMenu;
    @FXML
    private MenuItem EditMenu;
    @FXML
    private MenuItem CloseMenu;
    @FXML
    private MenuItem OpenMenu;
    @FXML
    private ComboBox<String>  CmbOSP;
    @FXML
    private ComboBox<String>  CmbContragent;
    @FXML
    private ComboBox<String>  CmbTypeAction;
    @FXML
    private ComboBox<String> CmbAction;
    
    public MainForm() {
        this.osp = null;
    } 
    
    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb)
    {       
        this.OpenMenu.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        
        this.CmbAction.getItems().add("Load");
        this.CmbAction.getItems().add("UnLoad");
        this.CmbAction.getSelectionModel().selectFirst();
        try
        {         
            this.osp = Factory.getInstance().getOsp_dao().getAllOSP();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator iterator   = this.osp.iterator();
        while(iterator.hasNext())
        {  
            this.objOSP = (OSP)iterator.next();
            this.CmbOSP.getItems().add(this.objOSP.getNAME_OSP());
        }   
        this.CmbOSP.getSelectionModel().selectFirst();
        
        this.setContextMenuOspCmb();
        this.setContextMenuContragentCmb();
        
    }  
    
    /**
     * 
     * @param event 
     * Выход из программы по нажатию кнопки выхода
     */
    
    @FXML
    protected void clickExit(ActionEvent event)
    {
        stage.close();
    }
    
    @FXML
    @SuppressWarnings({"unchecked", "unchecked"})
    protected void  onActionOspCmb(ActionEvent event) throws SQLException
    {


        this.CmbContragent.getItems().clear();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<String>  list = session.createSQLQuery("select c.name_contragent  from osp o join contragent c  on c.id_osp = o.id  where o.name_osp = :param").setString("param", this.CmbOSP.getSelectionModel().getSelectedItem()).list();
        if(list.size() > 0)
        {
            
            list.stream().forEach((Object list1) -> {
                this.CmbContragent.getItems().add(list1.toString());
            });
            this.CmbContragent.getSelectionModel().selectFirst();
           
        }
        session.close();
        this.getParamOsp();
    }
    
    @FXML
    @SuppressWarnings("unchecked")
    protected void onActionContragentCmb(ActionEvent  event)
    {
        this.CmbTypeAction.getItems().clear();
        if(this.CmbContragent.getSelectionModel().getSelectedIndex()  != -1)
        {
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
           
            List<String>  list = session.createSQLQuery("SELECT NAME_ACTION  FROM CONTRAGENT_ACTION WHERE ID_CONRTAGENT = (SELECT ID FROM CONTRAGENT WHERE NAME_CONTRAGENT = :p2 AND ID_OSP = (SELECT ID FROM OSP WHERE NAME_OSP = :p1))").setString("p1", this.CmbOSP.getSelectionModel().getSelectedItem()).setString("p2", this.CmbContragent.getSelectionModel().getSelectedItem()).list();
            if(list.size() > 0)
            {
           
                list.stream().forEach((Object list1) -> {
                    this.CmbTypeAction.getItems().add(list1.toString());
                });
                this.CmbTypeAction.getSelectionModel().selectFirst();
                this.getParamContragent();
            }        
             session.close();    
             
        }
    }    
    
  
    
    @FXML
    protected void ButtonAction(ActionEvent event) throws Exception
    {
        if("Load".equals(this.CmbAction.getSelectionModel().getSelectedItem()) && "Постановление".equals(this.CmbTypeAction.getSelectionModel().getSelectedItem()))
        {
            Stage dialogStage = new Stage();
            FXMLDocumentController ob  = new FXMLDocumentController();
            ob.setDialogStage(dialogStage);
            ob.start(dialogStage);
        }                   
    }
    
    
    public  void setContextMenuOspCmb()
    {
        ContextMenu contextMenu=new ContextMenu();  
        contextMenu.setStyle("-fx-text-fill:#808000;-fx-font:bold italic 14pt Georgia;");  
        MenuItem menuItemEdit = new MenuItem("Показать параметры ОСП");
        menuItemEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        menuItemEdit.setOnAction((ActionEvent e) -> {
            String SQL = "select o.name_osp,o.ip_osp,o.aliases_osp,o.vksp_osp from osp o where o.name_osp = '" + this.CmbOSP.getSelectionModel().getSelectedItem()  + "' ";
            Object[] param = { "Имя осп", "Ip ОСП", "Альяс до БД", "Код ВКСП ОСП"};
            ShowMessageToWindow.ShowMessageBox(SQL,param);
        });
        
        MenuItem menuItemEdit2 = new MenuItem("Проверить доступ до ОСП");
        menuItemEdit2.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        menuItemEdit2.setOnAction((ActionEvent e) -> {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction beginTransaction = session.beginTransaction();
            List<String>  list = session.createSQLQuery("select IP_OSP  from osp   where name_osp = :param").setString("param", CmbOSP.getSelectionModel().getSelectedItem()).list();
            String ipAddress = null;
            for (Object list1 : list) {
                ipAddress = (String) list1;
            }
            try {
                pingHost.getPingHost2(ipAddress,this.CmbOSP.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        contextMenu.getItems().addAll(menuItemEdit,menuItemEdit2);   
        this.CmbOSP.setContextMenu(contextMenu);
         
    }
    
    public  void setContextMenuContragentCmb()
    {
        ContextMenu contextMenu=new ContextMenu();  
        contextMenu.setStyle("-fx-text-fill:#808000;-fx-font:bold italic 14pt Georgia;");  
        MenuItem menuItemEdit = new MenuItem("Показать параметры Контрагента");
        menuItemEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        menuItemEdit.setOnAction((ActionEvent e) -> {
            if(this.CmbContragent.getSelectionModel().getSelectedIndex() != -1){
                String SQL = "SELECT c.id, c.name_contragent, c.agent_code_contragent, c.agent_dept_code_contragent, c.agent_agreement_code_contragent from contragent c join OSP o on c.id_osp = o.id where c.name_contragent = '" + this.CmbContragent.getSelectionModel().getSelectedItem()  + "' ";
                Object[] param = {"id", "Имя котрагента", ".Код контрагента", "Код Подразделения", "Код Соглашения"};
                ShowMessageToWindow.ShowMessageBox(SQL,param);
            }
        });
        
        MenuItem menuItemEdit2 = new MenuItem("Проверить есть ли данны Контрагент в ОСП");
        menuItemEdit2.setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        menuItemEdit2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Object[]  obj = new Object[10];
                if(CmbContragent.getSelectionModel().getSelectedIndex() !=-1 )
                {
                    String SQL = "SELECT c.id, c.name_contragent, c.agent_code_contragent, c.agent_dept_code_contragent, c.agent_agreement_code_contragent, o.ip_osp,  o.password_osp, o.login_osp, o.aliases_osp from contragent c join OSP o on c.id_osp = o.id where c.name_contragent = '" + CmbContragent.getSelectionModel().getSelectedItem()  + "' ";
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    obj =  getParamOSPmasrerDb.getParam(SQL, session);
                    String url =  "jdbc:firebirdsql:" + obj[5].toString() +"/3050:" + obj[8].toString() +"?lc_ctype=WIN1251";
                    try {
                        edidConnect_db_cfg.EditXmlFile(url, obj[7].toString(), obj[6].toString());
                    }
                    catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session = null;
                    session = NewHibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    SQL = "Select count(*) from mvv_endpoint_settings d where d.agent_code = '" + obj[2].toString() +"' AND d.agent_dept_code = '" +obj[3].toString() +"' AND d.agreement_code ='"+ obj[4].toString() +"' AND d.agreement_caption='" + obj[1].toString() +"'";
                    List<?> list = session.createSQLQuery("select SUSER_ID from sys_users").list();
                    BigInteger bigInteger = new BigInteger("0");
                    if(list.get(0) == bigInteger)
                    {
                        JOptionPane.showMessageDialog(null, "no" ,"no", JOptionPane.DEFAULT_OPTION );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Данный контрагента отсутствует в данном ОСП", "всё плохо", JOptionPane.DEFAULT_OPTION );
                    }}
            }
        });
        contextMenu.getItems().addAll(menuItemEdit,menuItemEdit2);   
        this.CmbContragent.setContextMenu(contextMenu);
         
    }
    
    @SuppressWarnings("unchecked")
    private void getParamOsp()
    {              
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<OSP> result;
        result = session.createQuery("from OSP where  name_osp = :param1").setParameter("param1", this.CmbOSP.getSelectionModel().getSelectedItem()).list();
        session.getTransaction().commit();      
        session.close();
        hash.put("parameterOsp",result);        
    }
    
    private void getParamContragent()
    {              
        String SQL = "Select cd.name_action, cd.split_1, cd.split_2, cd.csv_key  from contragent c join contragent_action cd on c.id = cd.id_conrtagent where c.name_contragent   = " + "'" + this.CmbContragent.getSelectionModel().getSelectedItem() +"'";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createSQLQuery(SQL);
        Object[] obj =  q.list().toArray();
        Object[] h = (Object[]) obj[0];
        hash.put("firstSplit",h[1]);
        hash.put("secondSplit",h[2]);
        hash.put("csv_key",h[3]);
        session.getTransaction().commit();        
        
    }

    @Override
    public void start(Stage stage1) throws Exception {
        MainForm.stage = stage1;
        Parent root = FXMLLoader.load(this.getClass().getResource("MainController.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.autosize();
        stage.show();       
        
    }
    
}


