
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author inftel03
 */
public class TestRunner {

    public static void main(String[] args) {

        Result nombreTest = JUnitCore.runClasses(TableroTest.class);
        if (nombreTest.wasSuccessful()) {
            System.out.println("ÈXITO DE LOS TESTS: "+nombreTest.wasSuccessful());
            System.out.print("Cantidad de tests probados: " + nombreTest.getRunCount()+"\n");

        } else {
            System.out.print("Errores encontrados: "+nombreTest.getFailures());
        }

    }

}


