/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

/**
 *
 * @author inftel02
 */
public class Coordenada {
    
    private int x;
    private int y;
    
    public Coordenada(){
        x=0; y=0;
    }
    
    public Coordenada(Coordenada cord){
        x=cord.x();
        y=cord.y();
    }
    
    public Coordenada(int x1, int y1){
        x=x1; y=y1;
    }
    
    public int x(){
        return x;
    }
    
    public int y(){
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public String toString(){
        return "("+ x + ", " + y + ")";
    }
    
}
