package csvviwer;

import csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 *
 * @author spok
 */


public interface TableClass 
{
    public void addDataToTableValidateTagWhithoutTag(CsvReader reader,String strValTag,String secondSplit) throws FileNotFoundException, IOException;
    TableColumn<ObservableList<SimpleStringProperty>, String> createColumn(final int columnIndex, String columnTitle);       
}
