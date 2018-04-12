/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28musicvisualizer;

import static java.lang.Integer.min;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Brad
 */
public class Bbt28StarsVisualizer implements Visualizer
{

    public Bbt28StarsVisualizer(ImageView imageView) 
    {
        //imageView.setVisible(true);
    }

   
   private final String name = "Bbt28 Stars Visualizer";
   
    private Integer numBands;
    private AnchorPane animationPane;
    
    private final Double bandHeightPercentage = 1.3;
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    private final Double circleRadiusMin = 10.0;
    
    private final Double initHue = 25.0;
    
    private Circle[] stars;
    
    @Override
    public void start(Integer numBands, AnchorPane animationPane) 
    {
        
      
        end();
        this.numBands = numBands;
        this.animationPane = animationPane;
        
        animationPane.setStyle("-fx-background-color: BLACK");
        //animationPane.getChildren().add(new ImageView(satelliteImage));
        height = animationPane.getHeight();
        width = animationPane.getWidth();
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        stars = new Circle[numBands];
        
        for(int i=0;i<numBands;i++)
        {
            Circle star = new Circle();
            star.setRadius(2.0f);
            star.setFill(Color.WHITE);
            star.setCenterX(Math.random()*600);
            star.setCenterY(Math.random()*600);
            animationPane.getChildren().add(star);
            stars[i] = star;
        }
    }

    @Override
    public void end() {
       if(stars != null)
       {
           for(Circle star: stars)
           {
              animationPane.getChildren().remove(star); 
           }
           stars = null;
       }
    }

    @Override
    public String getName() 
    {
        return name;
    }

    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) 
    {
        if(stars == null)
        {
            return;
        }
        
       Integer num = min(stars.length,magnitudes.length);
        
       
        for(int i = 0;i<num;i++)
        {
            
             if(magnitudes == null)
            {
                continue;
            }
      // stars[i].setFill(Color.BLUE); 
       //stars[i].setRadius((60.0 + magnitudes[i]/60)*circleRadiusMin);  
       System.out.println(((40.0 + magnitudes[i])/40.0) * (halfBandHeight + circleRadiusMin));
       stars[i].setRadius( Math.abs((60.0 + magnitudes[i])/5.0));
       
        }   
    }
void makeVisible(ImageView imageView)
{
    imageView.setVisible(true);
}
}
