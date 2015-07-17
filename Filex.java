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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class Filex extends Application
{

    public void start(final Stage stage) throws Exception
    {
        stage.setTitle("Filex");
        stage.setScene(buildComponents());
        stage.show();
    }

    public Scene buildComponents()
    {
        final BorderPane borderpane = new BorderPane();

        VBox leftPane = new VBox();
        leftPane.setPadding(new Insets(10));
        leftPane.setSpacing(8);

        Text title = getSearchTitle();
        TextField searchBox = getSearchBox();
        ProgressBar progressBar = getProgressBar(); 

        leftPane.getChildren().addAll(title, searchBox, progressBar);
        borderpane.setLeft(leftPane);


        final Scene scene = new Scene(borderpane, 720, 480);
        return scene;        
    }


    public Text getSearchTitle()
    {
        Text title = new Text("Search");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        return title;
    }

    public TextField getSearchBox()
    {
        TextField query = new TextField();
        return query;
    }

    public ProgressBar getProgressBar()
    {
        ProgressBar pb = new ProgressBar();
        pb.setProgress(-1);
        return pb;
    }

    /*
     * Main function used to run Filex
     */ 
    public static void main(final String[] arguments)
    {
        Application.launch(arguments);
    }

    public void handleSearchPressed()
    {

    }
}
