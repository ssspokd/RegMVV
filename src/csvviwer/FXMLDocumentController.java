/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvviwer;


import com.lowagie.text.DocumentException;
import csvreader.CsvReader;
import hash.hash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author nikolaev
 */
public class FXMLDocumentController    extends Application
{       
    private static final Logger LOG = Logger.getLogger(FXMLDocumentController.class.getName());

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
    }

    private final FileChooser fileChooser = new FileChooser();
    private TableClassImpl tableClass;
    private File file;
    private int curentRow;
    private  CsvReader reader; 
    private boolean  isOpenFiledialod = false;
    private ObservableList<SimpleStringProperty>  data;
    private ObservableList<SimpleStringProperty>  KeyTag;
    private Stage stage;
    private Scene scene;
    
    
    
    
    
    @FXML
    private    TableView<ObservableList<SimpleStringProperty>>  table ;      
    @FXML
    private Label textSelectRows;
    @FXML 
    private Label countRowslbl;
    @FXML 
    private Label countColumnslbl;
    @FXML 
    private Label fisrtSplitSymvolChkBox;  
    @FXML
    private Menu actionMenu;
    @FXML
    private MenuItem EditMenu;
    @FXML
    private MenuItem CloseMenu;
    @FXML
    private MenuItem OpenMenu;
     @FXML
    private MenuItem ActionMenu;
    
    
    
    
    public FXMLDocumentController() {
        this.stage = null;
        configureFileChooser(fileChooser);
    } 
    
    public void initialize(URL url, ResourceBundle rb) throws IOException
    {
        this.OpenMenu.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        data =  FXCollections.observableArrayList();
        KeyTag = FXCollections.observableArrayList();
        
    }  
    
    @FXML
    public void ExitMenu(ActionEvent event) throws DocumentException, FileNotFoundException
    {
        //stage.close();       
    }
    
    
    @FXML
    public void EditMenu() throws IOException
    {     
       OpenEditMenu();
    }
    
    @FXML
    private void  CloseFile()
    {     
        CloseOpenFile();
        this.CloseMenu.setDisable(true);
    }
    
    @FXML
    public void ActionMenu(ActionEvent event) throws DocumentException, FileNotFoundException, Exception
    { 
        table.getSelectionModel().selectFirst();             
        String [] str = hash.get("csv_key").toString().split(",");
        int count = hash.getInt("setcountRowslbl", 0);
       
            //Создание потока
        Thread myThready = new Thread(() -> //Этот метод будет выполняться в побочном потоке
        {
            int count1 = hash.getInt("setcountRowslbl", 0);
            while (count1 != 0) {
                String SQL = "Select " + hash.get("csv_key").toString() + " from table.... where  ";
                for(int i = 0; i < hash.getInt("columnCount",0);i++)
                {
                    SQL += str[i] + " = '" + table.getSelectionModel().getSelectedItem().get(i).getValue() +  "', ";
                }
                LOG.log(Level.INFO, Integer.toString(table.getSelectionModel().getSelectedIndex()));
                count1--;
                table.getSelectionModel().selectNext();
            }
        });
            myThready.start();	//Запуск потока
            
          
        this.table.setEditable(false);
        this.actionMenu.setDisable(true);
        this.EditMenu.setDisable(true);
        stage.setTitle(file.getName() + ": OK");
        
    }
    
    @FXML
    private void OpenFileAction() throws IOException 
    {    
       if(!isOpenFiledialod)
       {    
        isOpenFiledialod = true;        
        file = fileChooser.showOpenDialog(stage);
        stage.setTitle(file.getName());
        hash.put("fileName",file.toString());       
        if (file != null) 
           {          
                    
                    tableClass = new TableClassImpl(table); 
                    CloseOpenFile();
                    reader = new csvreader.CsvReader(new  FileReader(file.toString()), hash.getChar("firstSplit", '|'));      
                  
                    tableClass.addDataToTableValidateTagWhithoutTag(reader,setColumTag(), hash.get("secondSplit").toString());                 
                    this.actionMenu.setDisable(false);
                    this.EditMenu.setDisable(false);
                    this.CloseMenu.setDisable(false);
                    getContextMenu();
                    isOpenFiledialod = false;                  
                    
            }
                    
        }     
    }
    
    @FXML
    public void MouseClick(MouseEvent t) throws FileNotFoundException, IOException
    {
        
        table.setOnMousePressed((MouseEvent event) -> {
         if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
             try {
                 LOG.log(Level.INFO, "Select row {0}", getcurentRow());
                 LOG.info(table.getSelectionModel().getSelectedItem().toString());
                 OpenEditMenu();
             } catch (IOException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         if(event.isPrimaryButtonDown() && event.getClickCount() == 1)
         {
             LOG.log(Level.INFO, "size columns {0}", table.getColumns().toString());            
         }
     });
    }   
    
   
   ////
   /// function
   //// 
 
    
   public void CloseOpenFile() 
        {       
        table.getColumns().clear();
        if(reader != null)
        {
            reader.close();
        }               
        }
   
   //функция открытие мену редактирования выбранной  строки
    private void OpenEditMenu() throws IOException
    {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Rows");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(stage);
        curentRow = table.getSelectionModel().getSelectedIndex();
        EditSelectColumnTableController  obj =  new EditSelectColumnTableController(); 
        //final int currentPos,
        hash.put("curentPosRow",curentRow);
        obj.start(dialogStage, file, reader, table);
    }
    ////
    ///гетеры и сетеры
    ///
    public File getFile()
    {
        return this.file;
    }
    public int getcurentRow()
    {
        return  this.curentRow;
    }
    
    public void settextSelectRows(String textSelectRows)
    {
        this.textSelectRows.setText(textSelectRows);
    }
    

    public void setcountRowslbl(String countRowslbl)
    {
        this.countRowslbl.setText(countRowslbl);
    }
    
    public  void setcountColumnslbl(String countColumnslbl)
    {
        this.countColumnslbl.setText(countColumnslbl);
    }
    
    public  void setfisrtSplitSymvolChkBox(String fisrtSplitSymvolChkBox)
    {
        this.fisrtSplitSymvolChkBox.setText(fisrtSplitSymvolChkBox);
    }
    
    public  void getContextMenu()
    {
        ContextMenu contextMenu=new ContextMenu();  
        contextMenu.setStyle("-fx-text-fill:#808000;-fx-font:bold italic 14pt Georgia;");  
        MenuItem menuItemEdit = new MenuItem("Просмотр");
        menuItemEdit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        menuItemEdit.setOnAction((ActionEvent e) -> {
            try {
                OpenEditMenu();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        contextMenu.getItems().addAll(menuItemEdit);   
        table.setContextMenu(contextMenu);
         
    }

    private String setColumTag()
    {
 
        String[]string = hash.get("csv_key").toString().split(",");
        String str = string[0];
        hash.put("columnCount", string.length);
        for(int i = 1;i < string.length;i++)
        {
            str +=  "," + string[i];
        }
        return str;
    }
    
    @Override
    public void start(Stage stage2) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        AnchorPane page = new AnchorPane();
        page = loader.load();
        stage = new Stage();
        stage.setTitle("");
        stage.initModality(Modality.APPLICATION_MODAL);       
        stage.initOwner(stage2);      
        scene = new Scene(page);
        stage.setScene(scene);
        // Set the person into the controller.
        FXMLDocumentController controller = loader.getController();
        controller.setDialogStage(stage);
        stage.show();
        
    }
    
    @Override
    public void stop()
    {
        this.stage.close();
    }
    
     public void setDialogStage(Stage dialogStage) {
        this.stage = dialogStage;
    }
}


