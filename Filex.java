/*
 * @author Nathaniel Swan
 * Filex
 * File explorer framework for my COS226 class project.
 *
*/

import javafx.application.Application;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.HPos;

public class Filex extends Application
{

    public Text title;
    public TextField searchBox;
    public ProgressBar progressBar;
    public ListView<GridPane> listPane;
    public List<GridPane> resultsList;
    public ObservableList<GridPane> observableList;


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
        listPane = new ListView<GridPane>();
        resultsList = new ArrayList<GridPane>();
        observableList = FXCollections.observableList(resultsList);
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "F"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "F"));
        observableList.add(buildListItem("path/to/my/file", "F"));
        observableList.add(buildListItem("path/to/my/file", "D"));
        observableList.add(buildListItem("path/to/my/file", "F"));
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

 //   public void getSearchResults()
 //   {
 //       setListItem("my/path/to/a/file", 1);
 //   }

/*
 * Build a grid pane that will be inserted into each row in the list view.
 * The reason for using a grid pane is to customize the row such that it 
 * includes a button for previewing the file that the row corresponds to.
 */
    public GridPane buildListItem(String path, String type)
    {

        GridPane gp = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints(25);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints(400);
        col2.setHalignment(HPos.LEFT);
        ColumnConstraints col3 = new ColumnConstraints(100);
        col3.setHalignment(HPos.CENTER);

        gp.getColumnConstraints().add(col1);
        gp.getColumnConstraints().add(col2);
        gp.getColumnConstraints().add(col3);

        Label fileType = new Label(type);    
        Label fileLocation = new Label(path);    
        gp.setMargin(fileLocation, new Insets(0,15,0,15));
        Button previewButton = getButton("Preview");
        gp.setMargin(previewButton, new Insets(5,5,5,5));

        GridPane.setConstraints(fileType, 0, 0);
        GridPane.setConstraints(fileLocation, 1, 0);
        GridPane.setConstraints(previewButton, 2, 0);
        gp.setGridLinesVisible(true);
        gp.getChildren().addAll(fileType, fileLocation, previewButton);

        return gp;

    }

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
