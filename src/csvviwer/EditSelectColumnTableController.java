/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvviwer;

import hash.hash;
import csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author nikolaev
 */
public class EditSelectColumnTableController extends Application
{
    private static final Logger LOG = Logger.getLogger(EditSelectColumnTableController.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private final               TableView<RowsTable> table = new TableView<>();;  
    private File                file;
    private final               ObservableList<RowsTable> data = FXCollections.observableArrayList();
    final                       HBox hb = new HBox();
    private                     TableView<?> parrentTable;
    private                     ObservableList<RowsTable>   rowsTable;
    private int curentPos;
    /////TEXTField
    private  TextField EditPositon;
    private  TextField EditValue;
    private  TextField EditTag;
    private  int TableNewSelectIndex;
    ///TableColumn
    private  TableColumn<RowsTable,String>  Value;
    private  TableColumn<RowsTable,String> Positon;
    private  TableColumn<RowsTable,String>  Tag;
    
       
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(RowsTable obj) {
    	if (obj != null) {
    		// Fill the labels with info from the person object.
    		EditPositon.setText(obj.getPositon());
    		EditTag.setText(obj.getTag());
    		EditValue.setText(obj.getValue());

    	} else {
    		// Person is null, remove all the text.
    		EditPositon.setText("");
    		EditTag.setText("");
    		EditValue.setText("");   		
    	}
    }

    
    
    public String getEditPositon() {
        return this.EditPositon.getText();
    }

    public String getEditTag() {
        return this.EditTag.getText();
    }

    public String getEditValue() {
        return this.EditValue.getText();
    }

    public void setEditPositon(String EditPositon) {
        this.EditPositon.setText(EditPositon);
    }

    public void setEditTag(String EditTag) {
        this.EditTag.setText(EditTag);
    }

    public void setEditValue(String EditValue) {
        this.EditValue.setText(EditValue);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //@Override
    /**
     *
     * @param primaryStage = primary stage
     * @param file = name file
     * @param reader = class reader cvs
     * @param parrentTable = parrentTable
     * @throws IOException = IOException
     */
    @SuppressWarnings({"empty-statement", "unchecked"})
    public void start(Stage primaryStage, final File file, final CsvReader reader, final TableView<?> parrentTable) throws IOException {
        
        Stage stage = new Stage();
        stage.setTitle("Edit Rows position = " + hash.get("curentPosRow"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(new Stage());      
        Scene scene = new Scene(new Group());
        stage.setWidth(450);
        stage.setHeight(500);    
        this.parrentTable = parrentTable;
        this.file = file;
        this.table.setEditable(true);
        this.LoadDataCVSToTable(hash.get("firstSplit").toString().toCharArray()[0], hash.getInt("curentPosRow",0), reader);       
        this.table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.showPersonDetails(newValue));       
        this.table.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override 
        public void handle(MouseEvent event)
        {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
            {                         
                LOG.log(Level.INFO,table.getColumns().toString());
            }
        }
        });
        /////
        // create table column
        /////
        this.Positon = new TableColumn<>("Positon");
        this.Positon.setMinWidth(100);
        this.Positon.setCellValueFactory(new PropertyValueFactory<>("Positon"));
 
        /////
        this.Tag = new TableColumn<>("Tag");
        if("true".equals(hash.get("isShowSecondSymDelimeter").toString())){
            this.Tag.setEditable(true);          
        }
        else
        {
            this.Tag.setEditable(false);         
        }
        this.Tag.setMinWidth(100);
        this.Tag.setCellValueFactory(new PropertyValueFactory<>("Tag"));       
        this.Tag.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Tag.setOnEditCommit
                ((CellEditEvent<RowsTable, String> t) -> 
        {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTag(t.getNewValue());
                    LOG.info("tag "  + t.getNewValue());
                    LOG.info("tag "  + t.getOldValue());
                    LOG.info("tag "  + t.getTablePosition());
                    setEditTag(t.getNewValue());
        });
        /////
        this.Value = new TableColumn<>("Value");
        this.Value.setMinWidth(200);
        this.Value.setCellValueFactory(new PropertyValueFactory<>("Value"));
        this.Value.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Value.setOnEditCommit((CellEditEvent<RowsTable, String> t) -> {
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setValue(t.getNewValue());
            LOG.info("new data: "  + this.data.get(TableNewSelectIndex).Value.getValue());
            LOG.info("tag "  + t.getNewValue());
            LOG.info("tag "  + t.getOldValue());
            LOG.info("tag "  + t.getTablePosition());
            setEditValue(t.getNewValue());
        });
        ///create EditText
        this.EditPositon = new TextField();
        this.EditPositon.setPromptText("EditPositon");
        this.EditPositon.setMaxWidth(this.Tag.getPrefWidth());
        this.EditPositon.setDisable(true);
        this.EditPositon.setVisible(true);     
        
        this.EditTag   = new TextField();
        this.EditTag.setMaxWidth(this.EditTag.getPrefWidth());
        this.EditTag.setPromptText("EditTag");         
        this.EditTag.setVisible(true);
        this.EditTag.setOnKeyPressed((KeyEvent event) ->
        {
            if("true".equals(hash.get("isShowSecondSymDelimeter").toString())){
                if(event.getCode() == KeyCode.ENTER)
                {
                    RowsTable obj = this.table.getSelectionModel().getSelectedItem();
                    obj.setTag(this.getEditTag());
                }
            }
        }); 
        
        this.EditValue = new TextField();
        this.EditValue.setMaxWidth(this.EditValue.getPrefWidth());
        this.EditValue.setPromptText("EditValue");
        this.EditValue.setDisable(false);
        this.EditValue.setVisible(true);
        this.EditValue.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER)
            {
                RowsTable obj = this.table.getSelectionModel().getSelectedItem();
                obj.setValue(this.getEditValue());
                for(int i  =0; i < this.curentPos; i++)
                {
                     for(int j = 0; j < 3; j++){
                         LOG.log(Level.INFO, "Rows index:{0} ColumName: {1} Value:  {2}", new Object[]{Integer.toString(i), this.table.getColumns().get(j).getText(), this.table.getColumns().get(j).getCellData(i).toString()});
                     }
                }
            }
        });
        this.hb.getChildren().addAll(this.EditPositon, this.EditTag,this.EditValue);
        this.hb.setSpacing(3);
        
        ///
        this.table.setItems(this.data);
        this.table.getColumns().addAll(this.Positon, this.Tag, this.Value);       
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(this.table, this.hb);
        
        this.getContextMenu();
        ((Group) scene.getRoot()).getChildren().addAll(vbox);     
        stage.setScene(scene);
        stage.show();
    }
    public void LoadDataCVSToTable(char sym, int position, CsvReader reader) throws FileNotFoundException, IOException {
        
        reader  =  new  csvreader.CsvReader(new  FileReader(this.file.toString()), sym);
        this.table.setEditable(true);
        this.table.getColumns().clear();     
        this.curentPos = 0;
        while (reader.readRecord())
        {              
            final String[] dataValues = reader.getValues();
            if(this.curentPos == position)
            {              
                int currentPositon = 0;
                for (String value : dataValues)
                {
                    if("true".equals(hash.get("isShowSecondSymDelimeter").toString()))
                    {
                        if(value.length() > 0 )
                        {
                            if(value.split((String) hash.get("secondSplit")).length == 1)
                            {
                                this.data.add(new RowsTable(Integer.toString(currentPositon), value.split(hash.get("secondSplit").toString())[0],""));
                            }
                            else
                            {
                                this.data.add(new RowsTable(Integer.toString(currentPositon), value.split(hash.get("secondSplit").toString())[0], value.split("=")[1]));
                            }
                        }
                        else
                        {
                            this.data.add(new RowsTable(Integer.toString(currentPositon), value.split(hash.get("secondSplit").toString())[0], value.split("=")[0]));
                        }
                    }
                    else
                    {
                        if(value.length() > 0 )
                        {
                            this.data.add(new RowsTable(Integer.toString(currentPositon), "", value));
                        }
                        else
                        {
                            this.data.add(new RowsTable(Integer.toString(currentPositon), "", value));
                        }
                    }
                    currentPositon ++;
                }
            }    
           this.curentPos ++;
        }
        
    }
    
    public  void getContextMenu()
    {
        
        
        /*ContextMenu contextMenu=new ContextMenu();
        contextMenu.setStyle("-fx-text-fill:#808000;-fx-font:bold italic 14pt Georgia;");
        MenuItem menuItemDel = new MenuItem("Удалить");
        menuItemDel.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        menuItemDel.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
        public void handle(ActionEvent e)
        {
        
        } 
        });
        MenuItem menuItemCopy = new MenuItem("Копировать"); 
        menuItemCopy.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        MenuItem menuItemPaste = new MenuItem("Вставить");
        menuItemPaste.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));  
        SeparatorMenuItem sep=new SeparatorMenuItem();
        contextMenu.getItems().addAll(menuItemDel, menuItemCopy, menuItemPaste, sep);   
        table.setContextMenu(contextMenu);
         */
    }

    protected class RowsTable {

        private final  SimpleStringProperty  Positon;
        private final  SimpleStringProperty  Tag;
        private final  SimpleStringProperty  Value;
        
        private RowsTable(String Positon, String Tag, String value) {
            this.Positon = new SimpleStringProperty(Positon);
            this.Tag = new SimpleStringProperty(Tag);
            this.Value = new SimpleStringProperty (value);
        }

        public String  getPositon() {
            return this.Positon.get();
        }
        
        public String  getTag() {
            return this.Tag.get();
        }

        public String  getValue() {
            return this.Value.get();
        }

        public void setPositon(String  Poz) {
            this.Positon.set(Poz);
        }

        public void setTag(String  tag) {
            this.Tag.set(tag);
        }

        public void setValue(String  value) {
            this.Value.set(value);
        }
    }
  
    
    
}
 
