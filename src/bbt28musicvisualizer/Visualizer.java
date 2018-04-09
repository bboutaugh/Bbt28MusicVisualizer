/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt28musicvisualizer;

/**
 *
 * @author Brad
 */
public interface Visualizer {
    public void start();
    public void end();
    public String getName();
    public void update();
}
