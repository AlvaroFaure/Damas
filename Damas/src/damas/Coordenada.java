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
 * @author Alecia Franco, Alvaro García-Faure, Amir Haddouch, Rafael Hidalgo
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
    /**
     * crea una nueva instancia de Coordenada recibiendo una coordenada
     * @param cord contiene una coordenada que se guarda en x e y
     */
    public Coordenada(Coordenada cord){
        x=cord.x();
        y=cord.y();
    }
    /**
     * crea una nueva instancia de Coordenada recibiendo dos enteros
     * @param x1 contiene un entero que se guarda en el atributo x 
     * @param y1 contiene un entero que se guarda en el atributo y
     */
    public Coordenada(int x1, int y1){
        x=x1; y=y1;
    }
    /**
     * devuelve x
     *@return devuelve un entero de la coordenada x
     */
    public int x(){
        return x;
    }
    /**
     * devuelve y
     *@return devuelve un entero de la coordenada y
     */
    public int y(){
        return y;
    }

    /**
     * modifica el valor de la coordenada x
     * @param x contiene un entero con el valor a cambiar  
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * modifica el valor de la coordenada y 
     * @param y contiene un entero con el valor a cambiar 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * devuelve string de la coordenada
     *@return devuelve un string de la coordenada de la forma (x,y)
     */
    @Override
    public String toString(){
        return "("+ x + ", " + y + ")";
    }
    
    /**
     * @param o contiene un objeto
     * devuelve un booleano, es true si el objeto o es una instancia de coordenada y coincide con la coordenada x,y
     */
    @Override
    public boolean equals(Object o){
        return (o instanceof Coordenada) && ((Coordenada)o).x()==x && ((Coordenada)o).y()==y;
    }
    
}
