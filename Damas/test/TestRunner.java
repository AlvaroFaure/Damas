
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Clase principal de los tests
 * 
 * @author Alecia Franco, Alvaro García-Faure, Amir Haddouch, Rafael Hidalgo
 */
public class TestRunner {

    public static void main(String[] args) {

        Result nombreTest = JUnitCore.runClasses(TableroTest.class);
        if (nombreTest.wasSuccessful()) {
            System.out.println("Ha habido éxito ejecutando los tests");
            System.out.println("Se han ejecutado " + nombreTest.getRunCount()+" tests");

        } else {
            System.out.println("No ha habido éxito ejecutando los tests");
            System.out.println("Se han encontrado "+nombreTest.getFailures()+" errores");
        }

    }

}


