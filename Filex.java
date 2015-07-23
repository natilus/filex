/*
 * @author Nathaniel Swan
 * Filex
 * File explorer framework for my COS226 class project.
 *
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.ColumnConstraints;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class Filex extends Application
{

    public Text title;
    public TextField searchBox;
    public ProgressBar progressBar;
    public ListView<String> listPane;
    public List<String> resultsList;
    public ObservableList<String> observableList;


    /*
     * Invoked by the JavaFX Application.launch from main()
     */
    @Override
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

        //listPane.setPrefHeight(470);
        //listPane.setPrefWidth(500);
        listPane = new ListView<String>();
        resultsList = new ArrayList<String>();
        observableList = FXCollections.observableList(resultsList);
        observableList.add("Negative Item");
        observableList.add("Zeroith Item");
        observableList.add("First Item");
        observableList.add("Second Item");
        System.out.println(resultsList);
        listPane.setItems(observableList);
        borderpane.setCenter(listPane);

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
 * Build a grid pane that will be inserted into each row in the list view.
 * The reason for using a grid pane is to customize the row such that it 
 * includes a button for previewing the file that the row corresponds to.
 */
 //   public void getSearchResults()
 //   {
 //       setListItem("my/path/to/a/file", 1);
 //   }

 //   public void setListItem(String path, int type)
 //   {
 //       GridPane gp = new GridPane();
 //       gp.getColumnConstraints().add(new ColumnConstraints(50));
 //       gp.getColumnConstraints().add(new ColumnConstraints(350));
 //       gp.getColumnConstraints().add(new ColumnConstraints(100));
 //       
 //       Label fileType = new Label("TYPE");    
 //       Label fileLocation = new Label(path);    
 //       Button previewButton = getButton(path);
 //       GridPane.setConstraints(fileType, 1, 1);
 //       GridPane.setConstraints(fileLocation, 2, 1);
 //       GridPane.setConstraints(previewButton, 3, 1);
 //       searchResults.add(gp); 
 //   }

/*
 * Return a button with a specified name
 * @param name      string that will be the name of the button
 * @return          JavaFX Button with a custom name
 */  
    public Button getButton(String name)
    {
        Button btn = new Button(name);
        return btn;
    }

    /*
     * Main function used to instantiate the Filex system
     */ 
    public static void main(final String[] arguments)
    {
        Application.launch(arguments);
    }

    public void handleSearchPressed()
    {

    }
}
