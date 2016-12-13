
package xmlviewer;

import groovy.lang.GroovyShell;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codehaus.groovy.control.CompilationFailedException;



public class FXMLXmlViwerController extends Application
{
    
    private static final Logger LOG = Logger.getLogger(FXMLXmlViwerController.class.getName());

    private static void runGroovy() throws CompilationFailedException, IOException {
        GroovyShell shell = new GroovyShell();
        Object result = shell.evaluate(new File("c:\\test.groovy"));
        LOG.log(Level.INFO, "result={0}", result);
    }

    public static void main(String[] args) throws CompilationFailedException, IOException {
        FXMLXmlViwerController.runGroovy();
        launch(args);
    }
    private  Stage stage;    
     
    @Override
    public  void start(Stage stage1) throws Exception
    {
        this.stage = stage1;
        Parent root = FXMLLoader.load(this.getClass().getResource("FXMLXmlViwer.fxml"));
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        root.autosize();
        this.stage.show();              
    }
    
    
    
    @Override
    public void stop() {
        this.stage.close();
    }
   
    private void ssdf(){
        
    }
   
}
