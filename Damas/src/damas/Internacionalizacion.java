/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * Clase que gestiona la internacionalización
 * 
 * @author Alecia Franco, Alvaro García-Faure, Amir Haddouch, Rafael Hidalgo
 */
public class Internacionalizacion extends JuegoDamas {

    private final static String BUNDLE_PATH = Internacionalizacion.class.getPackage().getName() + ".mensajes";

    private static ResourceBundle messages_file = ResourceBundle.getBundle(BUNDLE_PATH, new Locale("es"));

    public static void setMessagesFile(Locale locale) {
        //System.out.println(messages_file.getLocale().getCountry());
        messages_file = ResourceBundle.getBundle(BUNDLE_PATH, locale);
    }

    public static String getString(String var) {
        return messages_file.getString(var);
    }
    /*public static void main(String args[]){
         
        
      static  
        /*Locale localizacion = Locale.getDefault();
        ResourceBundle fichero_textos = ResourceBundle.getBundle(path,localizacion);
         
         //System.out.println(fichero_textos.getString("nombre"));
         //System.out.println(fichero_textos.getString("mesa"));
        
         localizacion = new Locale("en","US");
         fichero_textos = ResourceBundle.getBundle(path,localizacion);
         System.out.println(fichero_textos.getString("nombre"));
         System.out.println(fichero_textos.getString("mesa"));
    
    }*/

}
