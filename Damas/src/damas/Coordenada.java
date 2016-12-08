/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

/**
 *La clase Coordenada crea una coordenada a partir de dos valores x e y, establece los valores de la coordenada a partir de otra,
 *establece cada componente de la coordenada a partir de otra y devuelve el valor de x,y o de la coordenada 
 *
 * @author Alecia Franco, Alvaro Garcia, Amir Haddouch, Rafael Hidalgo
 */
public class Coordenada {
    /**coordenada x*/
    private int x;
    /**coordenada y*/
    private int y;
    /**crea una nueva instancia de Coordenada inicializando 'x' e 'y' a 0
     */
    public Coordenada(){
        x=0; y=0;
    }
    /**crea una nueva instancia de Coordenada recibiendo una coordenada
     * @param cord contiene los valores de x e y
     */
    public Coordenada(Coordenada cord){
        x=cord.x();
        y=cord.y();
    }
    /**crea una nueva instancia de Coordenada recibiendo dos enteros
     * @param x valor de la coordenada x
     * @param y valor de la coordenada y
     */
    public Coordenada(int x1, int y1){
        x=x1; y=y1;
    }
    /**
     *@return devuelve coordenada x
     */
    public int x(){
        return x;
    }
    /**
     *@return devuelve coordenada y
     */
    public int y(){
        return y;
    }

    /**
     * @param x establece el valor de la coordenada x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y establece el valor de la coordenada y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     *@return devuelve un string de la coordenada de la forma (x,y)
     */
    public String toString(){
        return "("+ x + ", " + y + ")";
    }
    
}
