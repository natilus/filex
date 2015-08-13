/*
 * Project Filex
 * File explorer software for my COS226 project.
 * @author Nathaniel Swan
*/

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;

import java.util.List;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Filex extends Application
{

    public Text title;
    public TextField searchBox;
    public ProgressBar progressBar;
    public Button searchButton;
    public Button cancelButton;
    public CheckBox regexFlag;
    public CheckBox innerFileSearchFlag;
    public ListView<GridPane> listPane;
    public List<GridPane> resultsList;
    public ObservableList<GridPane> observableList;
    public FXComponent component;
    public Boolean isSearching = false;

    /*
     * Main function used to instantiate the Filex system
     */ 
    public static void main(final String[] arguments)
    {
        Application.launch(arguments);
    }

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
     * @return  scene
     */
    public Scene buildComponents()
    {
        final BorderPane borderpane = new BorderPane();
        final Scene scene = new Scene(borderpane, 720, 480);
        scene.getStylesheets().add("stylesheets/styles.css");

        // Build the vertical box that stacks all of the leftPane components
        VBox leftPane = new VBox();
        leftPane.setPrefWidth(200);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(10));
        leftPane.setSpacing(8);

        // Build the primary components for the leftPane 
        component = new FXComponent();
        title = component.getText("Enter Search Criteria");
        searchBox = component.getTextField();
        progressBar = component.getProgressBar(); 
        innerFileSearchFlag = component.getCheckbox("Search Within Files");
        regexFlag = component.getCheckbox("Use Regex");
        searchButton = component.getButton("Search");
        //searchButton.setAlignment(Pos.CENTER_RIGHT);

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                toggleSearchIndicators();
                isSearching = !isSearching;
            }
        }); 

        // Make this line shorter with .add() it works!
        leftPane.getChildren().addAll(title, searchBox, innerFileSearchFlag, regexFlag, progressBar);
        leftPane.getChildren().add(searchButton);
        borderpane.setLeft(leftPane);

        listPane = new ListView<GridPane>();
        listPane.setPrefHeight(500);
        listPane.setPrefWidth(550);
        resultsList = new ArrayList<GridPane>();
        observableList = FXCollections.observableList(resultsList);
        observableList.add(getListItem("D", "path/to/my/file"));
        listPane.setItems(observableList);
        borderpane.setCenter(listPane);

        return scene;        
    }

    /*
     * This is the mechanism in control of displaying the in-Progress
     * bar as a search is executing, and setting the visibility off 
     * otherwise.<br>
     * post:    toggle progressBar visibility
     */
    public void toggleSearchIndicators() {
        if (isSearching){
            showProgressBar();
            //hideSearchButton();
        }
        else{
            hideProgressBar();
            //showSearchButton();
        }
        //isSearching = !isSearching;
    }

    /*
    * Build a grid pane that will be inserted into each row in the list view.
    * The reason for using a grid pane is to customize the row such that it 
    * includes a button for previewing the file that the row corresponds to.<br>
    * @return   SearchResult<GridPane>
    */
    public GridPane getListItem(String type, String path)
    {
        SearchResult result = new SearchResult(type, path);
        return result.getSearchResult();
    }

    /*
     * This will hide the cancel button. This function is used to set 
     * the visibility of the cancel button such that it is not seen.<br>
     * post:    hides the cancel button from the left border pane 
     */
    public void hideCancelButton(){
        this.cancelButton.setVisible(false);
    }

    /*
     * This will show the search button.<br>
     * post:    shows the cancel button in the left borderpane
     */
    public void showCancelButton(){
        this.cancelButton.setVisible(true);
    }

    /*
     * This will create the cancel button. This function is used to show 
     * that the application has a search in progess.<br>
     * @return  cancelButton
     */
    public Button createCancelButton(){
        Button btn = this.component.getButton("Cancel");
        btn.setCancelButton(true);
        return btn;
    }

    /*
     * This will hide the search button. This function is used to show 
     * that the application has a search in progess.<br>
     * post:    hides the search button in the left borderpane
     */
    public void hideSearchButton(){
        this.searchButton.setVisible(false);
    }

    /*
     * This will show the search button. This is primarily used when
     * the application is opened and there is no search in progess.<br>
     * post:     shows the search button in the left borderpane
     */
    public void showSearchButton(){
        this.searchButton.setVisible(true);
    }

    /*
     * This will create the search button. We want to set this button
     * to be the default button so that when the user presses the 
     * "Enter" key, the application will pick it up<br>
     * @return  searchButton 
     */
    public Button createSearchButton(String name){
        Button btn = this.component.getButton(name);
        btn.setDefaultButton(true);
        return btn;
    }

    /*
     * This will hide the progress bar. This is used to show that 
     * the application has no search in progess.<br>
     * post:     hides the progress bar in the left borderpane
     */
    public void hideProgressBar(){
        this.progressBar.setVisible(false);
    }

    /*
     * This will show the progress bar. This is primarily used when
     * there is a search in progess.<br>
     * post:     shows the progress bar in the left borderpane
     */
    public void showProgressBar(){
        this.progressBar.setVisible(true);
    }

}
