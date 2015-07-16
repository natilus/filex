/*
 * @author Nathaniel Swan
 * File explorer framework for my COS226 class project.
 *
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class Filex extends Application
{
    @Override
    public void start(final Stage stage) throws Exception
    {
        final BorderPane borderpane = new BorderPane();
        VBox searchPane = buildSearchPane();
        borderpane.setLeft(searchPane);
        final Scene scene = new Scene(borderpane, 720, 480);
        
        stage.setTitle("Filex");
        stage.setScene(scene);
        stage.show();
    }


    public VBox buildSearchPane()
    {
        VBox searchPane = new VBox();
        searchPane.setPadding(new Insets(10));
        searchPane.setSpacing(8);

        Text title = new Text("Search");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField query = new TextField();
        searchPane.getChildren().addAll(title, query);
        return searchPane;
    }
    /*
     * Main function used to run Filex
     */ 
    public static void main(final String[] arguments)
    {
        Application.launch(arguments);
    }
}
