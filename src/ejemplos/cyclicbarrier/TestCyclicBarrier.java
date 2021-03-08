/*
 *
 */
package ejemplos.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sebastian Santalla
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        int cantAlumnos = 5;
        int cantEjercicios = 5;
        final CyclicBarrier barrera = new CyclicBarrier(cantAlumnos + 1);
        Alumno[] alumnos = new Alumno[cantAlumnos];

        //System.out.println("*Profesora espera a que alumnos terminen el ejercicio*");

        for (int i = 0; i < cantAlumnos; i++) {
            alumnos[i] = new Alumno((i+1), cantEjercicios, barrera);
        }
        for (int i = 0; i < cantAlumnos; i++) {
            alumnos[i].start();
        }

        try {
            Thread.sleep((int)(Math.floor(Math.random() * (5000 - 1000 + 1)) + 1000));
            System.out.println("*Profesora entra*");
            barrera.await();
            System.out.println("*** empieza el examen ***");
            do {
                barrera.await(); // espero a que terminen
                System.out.println("*Profesora corrige el ejercicio en el pizarron. Alumnos esperan*");
                Thread.sleep((int)(Math.floor(Math.random() * (8000 - 1000 + 1)) + 1000));
                System.out.println("**Ejercicio corregido - Siguiente ejercicio**\n");
                cantEjercicios--;
                barrera.await(); // pueden continuar al sig ejercicio
            } while (cantEjercicios > 0);
        } catch (Exception ex) {
            Logger.getLogger(TestCyclicBarrier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
