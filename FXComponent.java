/* This class is used to access custom objects that are offered
 * through the JavaFX API.
 * @author  Nathaniel Swan
 * @date    July 29, 2015
 */

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class FXComponent {

    /*
     * Get a ProgressBar that will oscillate signifying some execution
     * @return      ProgressBar
     */
    public ProgressBar getProgressBar()
    {
        ProgressBar pb = new ProgressBar();
        pb.setProgress(-1); // -1 for oscillation (in progress)
        pb.setPrefWidth(200);
        return pb;
    }

    /*
     * Get a formatted string
     * @param t     string to be formatted
     * @return      Text
     */
    public Text getText(String t)
    {
        Text title = new Text(t);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        return title;
    }

    /*
     * Get a TextField object --used for user input
     * @return      TextField
     */
    public TextField getTextField()
    {
        TextField tf = new TextField();
        return tf;
    }

    /*
    * Return a button with a specified name
    * @param name      string that will be the name of the button
    * @return          Button
    */  
    public Button getButton(String name)
    {
        Button btn = new Button(name);
        return btn;
    }

    /*
    * Return a checkbox with an associated name
    * @param name      string that will be the name associated to the checkbox
    * @return          JavaFX checkbox with a custom name
    */  
    public CheckBox getCheckbox(String name)
    {
        CheckBox cb = new CheckBox(name);
        return cb;
    }

}
