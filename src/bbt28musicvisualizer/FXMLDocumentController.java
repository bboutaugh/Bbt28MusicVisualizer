/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28musicvisualizer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Brad
 */
public class FXMLDocumentController implements Initializable 
{
    @FXML
    private AnchorPane root;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private MenuBar menuBar;
    
    @FXML 
    private Menu fileMenu;
    @FXML
    private MenuItem openFileMenuItem;
    @FXML
    private MenuItem addFileMenuItem;
    @FXML
    private MenuItem removeFileMenuItem;
    @FXML 
    private MenuItem closeFileMenuItem;
    
    //Menu UI Elements
    @FXML
    private Menu songsMenu;
    @FXML
    private MenuItem songMenuItem1;
    @FXML
    private MenuItem songMenuItem2;
    @FXML
    private MenuItem songMenuItem3;
    
    @FXML
    private Menu vizMenu;
    @FXML
    private MenuItem diamondVizMenuItem;
    @FXML
    private MenuItem starsVizMenuItem;
    
    @FXML
    private Menu colorsMenu;
    @FXML
    private MenuItem blueColorMenuItem;
    @FXML
    private MenuItem redColorMenuItem;
    @FXML
    private MenuItem greenColorMenuItem;
    
    @FXML
    private VBox leftVBox;
    
    //Buttons
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;
    
    @FXML
    private SplitPane splitPane;
    
    @FXML
    private Slider songSlider;
    
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
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private ImageView satelliteImage;
    
    private Media media;
    private MediaPlayer mediaPlayer;
    
    private Integer numBands = 40;
    private final Double updateInterval = 0.05;
    
    private ArrayList<Visualizer> visualizers;
    private Visualizer currentVisualizer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       visualizers = new ArrayList<>();
        //visualizers.add(new Bbt28DiamondVisualizer());
        visualizers.add(new Bbt28StarsVisualizer(satelliteImage));
        visualizers.add(new EllipseVisualizer());

        for (Visualizer visualizer : visualizers) {
            MenuItem menuItem = new MenuItem(visualizer.getName());
            menuItem.setUserData(visualizer);
            menuItem.setOnAction((ActionEvent event) -> {
                selectVisualizer(event);
            });
            vizMenu.getItems().add(menuItem);
        }
        currentVisualizer = visualizers.get(0);
        vizTypeLabel.setText(currentVisualizer.getName());
        
    }
    
    @FXML
    private void openMedia(File file) 
    {
        errorLabel.setText("");
        
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        
        try 
        {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnReady(() -> {
                readyAction();
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                endOfMediaAction();
            });
            mediaPlayer.setAudioSpectrumNumBands(numBands);
            mediaPlayer.setAudioSpectrumInterval(updateInterval);
            mediaPlayer.setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
                updateAction(timestamp, duration, magnitudes, phases);
            });
            mediaPlayer.setAutoPlay(false);
        } 
        catch (Exception ex) 
        {
            errorLabel.setText(ex.toString());
        }
    }
    
    @FXML
        private void readyAction() 
        {
        Duration duration = mediaPlayer.getTotalDuration();
        Duration ct = mediaPlayer.getCurrentTime();
        currentVisualizer.start(numBands, animationPane);
        songSlider.setMin(0);
        songSlider.setMax(duration.toMillis());
    }
    
    @FXML
    private void endOfMediaAction() 
    {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        songSlider.setValue(0);
    }
    
    @FXML
    private void updateAction(double timestamp, double duration, float[] magnitudes, float[] phases) 
    {
        Duration ct = mediaPlayer.getCurrentTime();
        double ms = ct.toMillis();
        songSlider.setValue(ms);
        currentVisualizer.update(timestamp, duration, magnitudes, phases);
    }
    
    @FXML
    private void openAction(Event event) 
    {
        Stage primaryStage = (Stage)animationPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) 
        {
            openMedia(file);
        }
    }
    
    @FXML
    private void closeAction(Event event)
    {
        System.exit(0);
    }
    
    @FXML
    private void playSong1(Event event)
    {
        File songFile1 = new File("C:\\Users\\Brad\\Documents\\NetBeansProjects\\Bbt28MusicVisualizer\\src\\bbt28musicvisualizer\\bensound-jazzyfrenchy.mp3");
        if (songFile1 != null) 
        {
            openMedia(songFile1);
        }
    }
    
    @FXML
    private void playSong2(Event event)
    {
        File songFile1 = new File("C:\\Users\\Brad\\Documents\\NetBeansProjects\\Bbt28MusicVisualizer\\src\\bbt28musicvisualizer\\bensound-psychedelic_1.mp3");
        if (songFile1 != null) 
        {
            openMedia(songFile1);
        }
    }
    
    @FXML
    private void playSong3(Event event)
    {
        File songFile1 = new File("C:\\Users\\Brad\\Documents\\NetBeansProjects\\Bbt28MusicVisualizer\\src\\bbt28musicvisualizer\\bensound-ukulele.mp3");
        if (songFile1 != null) 
        {
            openMedia(songFile1);
        }
    }
    
    @FXML
    private void removeAction(Event event)
    {
        int songSelection = -1;
        ArrayList<Object> songSelections = new ArrayList<>();
        for(int i=0;i<songsMenu.getItems().size();i++)
        {
            songSelections.add(songsMenu.getItems().get(i).getText());
        }
       ChoiceDialog<Object> selectionRemoval 
               = new ChoiceDialog<>("Songs",songSelections);
       selectionRemoval.setTitle("Remove Song");
       selectionRemoval.setHeaderText("Update Playlist");
       selectionRemoval.showAndWait();
    }
    
    @FXML
    private void addSongAction(Event event)
    {
        Stage primaryStage = (Stage)animationPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) 
        {
            openMedia(file);
        }
        MenuItem menuItem = new MenuItem(file.getName());
        menuItem.setUserData(file);
        menuItem.setOnAction((ActionEvent selectEvent) -> {
                selectSong(selectEvent);
            });
        songsMenu.getItems().add(menuItem);
    }
    
    private void selectSong(ActionEvent event)
    {
        MenuItem menuItem = (MenuItem)event.getSource();
        File song = (File)menuItem.getUserData();
        mediaPlayer.play();
    }
    
        private void selectVisualizer(ActionEvent event) 
        {
        MenuItem menuItem = (MenuItem)event.getSource();
        Visualizer visualizer = (Visualizer)menuItem.getUserData();
        changeVisualizer(visualizer);
       
        Bbt28StarsVisualizer starsVisualizer = new Bbt28StarsVisualizer(satelliteImage);
        starsVisualizer.makeVisible(satelliteImage);
        
        }
        
         private void changeVisualizer(Visualizer visualizer) 
        {
        if (currentVisualizer != null) {
            currentVisualizer.end();
        }
        currentVisualizer = visualizer;
        currentVisualizer.start(numBands, animationPane);
        vizTypeLabel.setText(currentVisualizer.getName());
        }
         

   //Button Actions
 @FXML
 private void playAction(ActionEvent event)
 {
     if(mediaPlayer != null)
     {
         mediaPlayer.play();
     }
 }
 
 @FXML
 private void pauseAction(ActionEvent event)
 {
     if(mediaPlayer != null)
     {
         mediaPlayer.pause();
     }
 }
 
 @FXML
 private void stopAction(ActionEvent event)
 {
     if(mediaPlayer != null)
     {
         mediaPlayer.stop();
     }
 }
 
 //Slider Activity
    @FXML
    private void songSliderMousePressed(Event event) 
    {
        if (mediaPlayer != null) 
        {
           mediaPlayer.pause(); 
        }  
    }
    
    @FXML
    private void songSliderMouseReleased(Event event) 
    {
        if (mediaPlayer != null) 
        {
            mediaPlayer.seek(new Duration(songSlider.getValue()));
            System.out.println(songSlider.getValue());
            //currentVisualizer.start(numBands, animationPane);
            mediaPlayer.play();
        }  
    }
    
    public boolean isRunning(){
        if(mediaPlayer.getAudioSpectrumListener() != null){
            if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                return true; 
            }
        }
        return false; 
    }
    
}
