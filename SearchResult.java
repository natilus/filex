/* 
 * This class will provide a SearchResult which results 
 * from running a Search. 
 *
 * Build a grid pane that will be inserted into each row in the list view.
 * The reason for using a grid pane is to customize the row such that it 
 * includes a button for previewing the file that the row corresponds to.
 *
 * @author     Nathaniel Swan
 */

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.geometry.HPos;
import javafx.geometry.Insets;

public class SearchResult{
    
    private FXComponent component = new FXComponent(); 
    private ColumnConstraints col1 = new ColumnConstraints();
    private ColumnConstraints col2 = new ColumnConstraints();
    private ColumnConstraints col3 = new ColumnConstraints();
    private GridPane gp = new GridPane();
    private Button previewButton = new Button();
    private Label typeLabel = new Label();
    private Label pathLabel = new Label();
 

    public SearchResult(String type, String path){ 
        formatColumns();
        makeLabels(type, path);
        setPreviewButton();
        formatSearchResultLayout();
    }

    public void setPreviewButton(){
        this.previewButton = this.component.getButton("Preview");
    }

    public void formatColumns(){
        this.col1.setPercentWidth(5);
        this.col1.setHalignment(HPos.CENTER);

        this.col2.setPercentWidth(70);
        this.col2.setHalignment(HPos.LEFT);

        this.col3.setPercentWidth(25);
        this.col3.setHalignment(HPos.CENTER);

        addColumnToGridPane(this.col1); 
        addColumnToGridPane(this.col2); 
        addColumnToGridPane(this.col3); 
    }

    public GridPane getSearchResult(){
        return this.gp; 
    }

    public void addColumnToGridPane(ColumnConstraints col){
        this.gp.getColumnConstraints().add(col);
    }

    public  void makeLabels(String type, String path){
        this.typeLabel.setText(type);
        this.pathLabel.setText(path);
    }

    public void setPreviewButtonFunction(){
    }

    public void formatSearchResultLayout(){
        this.gp.setConstraints(this.typeLabel, 0, 0);
        this.gp.setConstraints(this.pathLabel, 1, 0);
        this.gp.setConstraints(this.previewButton, 2, 0);

        // Insets ( top, right, bottom, left ) 
        this.gp.setMargin(this.pathLabel, new Insets(0,15,0,15));
        this.gp.setMargin(this.previewButton, new Insets(5,5,5,5));
        this.gp.setGridLinesVisible(true); //njs
        this.gp.getStyleClass().add("filetype");
        this.gp.getChildren().addAll(typeLabel, pathLabel, previewButton);
    }
}
