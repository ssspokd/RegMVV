/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvviwer;

//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import hash.hash;
import csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;


/**
 *
 * @author nikolaev
 */
class TableClassImpl implements TableClass
{
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(TableClassImpl.class.getName());
    private TableView<ObservableList<SimpleStringProperty>> table = new TableView<>();  
    private TableColumn<ObservableList<SimpleStringProperty>, String> column;
    private static final TableClass tableClass;
    static 
    {
        tableClass = new TableClassImpl();
    }
    
    public TableClassImpl()
    {
        
    }
    
    
    
    TableClassImpl(TableView<ObservableList<SimpleStringProperty>> table) throws IOException
    {
        table.getItems().clear();     
        this.table = table;  
    }   

    
    
    @Override
    public void addDataToTableValidateTagWhithoutTag(CsvReader reader,String strValTag,String secondSplit) throws FileNotFoundException, IOException
    {           
        
        table.setEditable(true); 
        table.getColumns().clear();         
        int curentColumn = 0;
        //create column
       
       for(int columnIndex = table.getColumns().size();  columnIndex < strValTag.split(",").length;  columnIndex++ )
            {
                table.getColumns().add(createColumn(columnIndex,strValTag.split(",")[columnIndex]));
            }     
        while (reader.readRecord()) 
        {   
            curentColumn ++;
            final String[] dataValues = reader.getValues();
            Platform.runLater(() -> {
                ObservableList<SimpleStringProperty> data = FXCollections.observableArrayList();
                if(dataValues[0].length() != 0)
                {
                    if(table.getColumns().size() <= dataValues.length)
                    {
                        int j = 0;
                        for(String value : dataValues)
                        {
                            if(value.split(secondSplit)[0] == null ? table.getColumns().get(j).getText() == null : value.split(secondSplit)[0].equals(table.getColumns().get(j).getText()))
                            {
                                if(value.split(secondSplit).length == 2)
                                {
                                    data.add(new SimpleStringProperty(value.split(secondSplit)[1]));
                                }
                                else
                                {
                                    data.add(new SimpleStringProperty(""));
                                }
                            }
                            else
                            {
                                data.clear();
                                return;
                            }
                            j++;
                        }
                    }
                    else
                    {
                        data.clear();
                        return;
                    }
                }
                else
                {
                    if(table.getColumns().size() <= dataValues.length-1)
                    {
                        for(int i = 1; i < dataValues.length;i++)
                        {
                            if(dataValues[i].split(secondSplit)[0] == null ? table.getColumns().get(i-1).getText() == null : dataValues[i].split(secondSplit)[0].equals(table.getColumns().get(i-1).getText()))
                            {
                                if(dataValues[i].split(secondSplit).length == 2)
                                {
                                    data.add(new SimpleStringProperty(dataValues[i].split(secondSplit)[1]));
                                }
                                else
                                {
                                    data.add(new SimpleStringProperty(""));
                                }
                            }
                            else
                            {
                                data.clear();
                                return;
                            }
                        }
                    }
                    else
                    {
                        data.clear();
                        return;
                    }
                }
                table.getItems().add(data);
            });                 
        }
        
        hash.put("setcountRowslbl",  Integer.toString(curentColumn));
        hash.put("countColumnslbl",  Integer.toString(curentColumn));
  
    }
    
   
    @Override
    public TableColumn<ObservableList<SimpleStringProperty>, String> createColumn(final int columnIndex, String columnTitle)
    {
    this.column = new TableColumn<>();
    String title;
    if (columnTitle == null || columnTitle.trim().length() == 0) 
    {    
        title = Integer.toString(columnIndex + 1);
    } else 
    {
      title = columnTitle;
    }
    this.column.setText(title);
    this.column.setCellValueFactory((CellDataFeatures<ObservableList<SimpleStringProperty>, String> cellDataFeatures) -> {
        ObservableList<SimpleStringProperty> values = cellDataFeatures.getValue();
        if (columnIndex >= values.size())
        {
            return new SimpleStringProperty("");
        } else
        {
            return cellDataFeatures.getValue().get(columnIndex);
        }
    });
    this.column.setResizable(true);
    this.column.setId(title);
    return this.column;
    }   
}
