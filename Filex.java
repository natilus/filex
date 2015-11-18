/*
 * Project Filex
 * File explorer software for my COS226 project.
 * @author Nathaniel Swan
*/

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Filex extends Application
{
    public String search;
    public TextField searchBox;
    public Boolean searchError;
    public CheckBox innerFileSearchFlag;
    public ListView<GridPane> listPane;
    public Text title;
    public Alert inputErr;
    public ProgressBar progressBar;
    public Button searchButton;
    public Button cancelButton;
    public CheckBox regexFlag;
    public ObservableList<GridPane> observableList;
    public FXComponent component;
    public Stage primaryStage;
    public List<GridPane> resultsList;
    public Search mySearch;

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
        this.primaryStage = stage;
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

        /*
         * Defines the function of the search button
         * such that if no errors occur, start the search!
         */
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(checkForSearchError()){
                    noSearchCriteria();
                }
                else{
                    long x=0;
                    buildSearch();
                    isSearching(true);
                    // pretend the search is actually doing something complicated
                    isSearching(false);
                    updateSearchResults();
                }
            }
        }); 

        // Build the left pane with components
        leftPane.getChildren().add(title);
        leftPane.getChildren().add(searchBox);
        leftPane.getChildren().add(innerFileSearchFlag);
        leftPane.getChildren().add(regexFlag);
        leftPane.getChildren().add(progressBar);
        hideProgressBar(); // progress bar is hidden to start
        leftPane.getChildren().add(searchButton);
        borderpane.setLeft(leftPane);

        listPane = new ListView<GridPane>();
        listPane.setPrefHeight(500);
        listPane.setPrefWidth(550);
        resultsList = new ArrayList<GridPane>();
        observableList = FXCollections.observableList(resultsList);
        listPane.setItems(observableList);
        borderpane.setCenter(listPane);

        return scene;        
    }

    /*
     * Iterate over the results from a search and display them
     */
    public void updateSearchResults(){ 
        List<SearchResult> list = mySearch.getSearchResults(); 
        for (SearchResult result : list){
            observableList.add(result.getResult());
        }
    }

    /*
     * Convert the entered text characters to a string
     * @ return  search query
     */
    public String getSearchQuery(){
        return this.searchBox.getCharacters().toString();
    }

    /*
     * Return whether or not some search text has been entered into the 
     * searchBox
     * @return  false if empty, true otherwise
     */
    public Boolean checkForSearchError(){
        if( this.searchBox.getCharacters().length() == 0 )
            return true;
        return false;
    }

    /* 
     * This function prompts the user to actually enter some search text into
     * the searchBox label.
     */
    public void noSearchCriteria(){
        Alert inputErr = new Alert(AlertType.INFORMATION);
        inputErr.setTitle("Error Searching");
        inputErr.setHeaderText("Please enter valid search criteria.");
        inputErr.show();
    }

    /* 
     * Prompt user for selection of a directory
     */
    public File openDirectory(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose a Directory");
        File defaultDirectory = new File("/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(this.primaryStage);
        return selectedDirectory;
    }

    //TODO
    /*
     * This is the mechanism in control of displaying the in-Progress
     * bar as a search is executing, and setting the visibility off 
     * otherwise.<br>
     * post:    toggle progressBar visibility
     */
    public void isSearching(Boolean searching) {
        if (searching){
            showProgressBar();
            //hideSearchButton();
        }
        else{
            hideProgressBar();
            //showSearchButton();
        }
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
     * This will return the current state of the regex checkbox as a
     * boolean. If the box is checked, return true, otherwise false
     */
    public Boolean getRegexFlag(){
        if(regexFlag.isSelected())
           return true;
        else
           return false; 
    }

    /*
     * This will return the current state of the inner search checkbox as a
     * boolean. If the box is checked, return true, otherwise false
     */
    public Boolean getInnerSearchFlag(){
        if(this.innerFileSearchFlag.isSelected())
           return true;
        else
           return false; 
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
        progressBar.setVisible(false);
    }

    /*
     * This will show the progress bar. This is primarily used when
     * there is a search in progess.<br>
     * post:     shows the progress bar in the left borderpane
     */
    public void showProgressBar(){
        progressBar.setVisible(true);
    }

    /*
     * Invoke a search based on query, directory, regex compatability, and inner searching
     */ 
    public void buildSearch(){
        mySearch = new Search(getSearchQuery(), openDirectory(), getRegexFlag(), getInnerSearchFlag());
    }
}
