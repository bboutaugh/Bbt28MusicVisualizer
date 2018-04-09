/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28musicvisualizer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

/**
 *
 * @author Brad
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private MenuBar menuBar;
    
    @FXML 
    private Menu fileMenu;
    
    @FXML
    private Menu songMenu;
    
    @FXML
    private Menu vizMenu;
    
    @FXML
    private Menu colorMenu;
    
    @FXML
    private VBox leftVBox;
    
    @FXML
    private Button playButton;
    
    @FXML
    private Button pauseButton;
    
    @FXML
    private Button stopButton;
    
    @FXML
    private SplitPane splitPane;
    
    @FXML
    private AnchorPane animationPane;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private VBox rightVBox;
    
    @FXML
    private Label songLabel;
    
    @FXML
    private Label bandLabel;
    
    @FXML
    private Label tempoLabel;
    
    @FXML
    private HBox bottomHBox;
    
    @FXML
    private Label vizTypeLabel;
    
    @FXML
    private Label depthLabel;
    
    @FXML
    private Label nameLabel;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
