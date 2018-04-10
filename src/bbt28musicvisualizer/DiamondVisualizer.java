/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28musicvisualizer;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Brad
 */
public class DiamondVisualizer implements Visualizer
{
    private final String name = "Diamond Visualizer";
    private Integer numBands;
    private AnchorPane animationPane;
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    
    private Polygon[] diamonds;

    @Override
    public void start(Integer numBands, AnchorPane animationPane) {
        end();
        this.numBands = numBands;
        this.animationPane = animationPane;
        
        height = animationPane.getHeight();
        width = animationPane.getWidth();
        diamonds = new Polygon[numBands];
        
        for(int i=0;i<numBands;i++)
        {
            Polygon diamond = new Polygon();
            diamond.getPoints().addAll(new Double[]{
                10.0,20.0,
                10.0,40.0,
                40.0,10.0,
                20.0,10.0
            });
            animationPane.getChildren().add(diamond);
            diamond = diamonds[i];
        }
    }

    @Override
    public void end() {
       if(diamonds != null)
       {
           for(Polygon diamond: diamonds)
           {
              animationPane.getChildren().remove(diamond); 
           }
           diamonds = null;
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
        if(diamonds == null)
        {
            return;
        }
        
    }

    
    
}
