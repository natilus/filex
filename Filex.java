/*
 * @author Nathaniel Swan
 * Filex
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

    public Text title;
    public TextField searchBox;
    public ProgressBar progressBar;

    /*
     * Invoked by the JavaFX Application.launch from main()
     */
    public void start(final Stage stage) throws Exception
    {
        stage.setTitle("Filex");
        stage.setScene(buildComponents());
        stage.show();
    }

    /*
     * Construct the major components in a layout for the GUI
     */
    public Scene buildComponents()
    {
        final BorderPane borderpane = new BorderPane();

        VBox leftPane = new VBox();
        leftPane.setPadding(new Insets(10));
        leftPane.setSpacing(8);

        title = getText("Search");
        searchBox = getTextField();
        progressBar = getProgressBar(); 

        leftPane.getChildren().addAll(title, searchBox, progressBar);
        borderpane.setLeft(leftPane);


        final Scene scene = new Scene(borderpane, 720, 480);
        return scene;        
    }

    /*
     * Get a formatted string
     * @param t     string to be formatted
     * @return      formatted string
     */
    public Text getText(String t)
    {
        Text title = new Text(t);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        return title;
    }

    /*
     * Get a TextField object --used for user input
     * @return      generic TextField object with no modifications 
     */
    public TextField getTextField()
    {
        TextField query = new TextField();
        return query;
    }

    /*
     * Get a ProgressBar that will oscillate signifying some execution
     * @return      progress bar that oscillates
     */
    public ProgressBar getProgressBar()
    {
        ProgressBar pb = new ProgressBar();
        pb.setProgress(-1); // -1 for oscillation (in progress)
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
