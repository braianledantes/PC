/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Sebastian
 */
public class Alumno extends Thread {
    private int numeroAlumno;
    private int cantEjrecicios;
    private int ejercicioActual;
    private CyclicBarrier barrera;

    public Alumno(int numeroAlumno, int cantEjercicios, CyclicBarrier barrera) {
        this.numeroAlumno = numeroAlumno;
        this.cantEjrecicios = cantEjercicios;
        this.barrera = barrera;
        this.ejercicioActual = 1;
    }

    public void run() {
        try {
            Thread.sleep((int)(Math.floor(Math.random() * (5000 - 1000 + 1)) + 1000));
            System.out.println("Alumno N°" + numeroAlumno + " entro");
            barrera.await();

            do {
                System.out.println("Alumno N°" + numeroAlumno + " realiza ejercicio N°" + ejercicioActual);
                Thread.sleep((int)(Math.floor(Math.random() * (10000 - 1000 + 1)) + 1000));
                System.out.println("Alumno N°" + numeroAlumno + " finaliza ejercicio N°" + ejercicioActual + ". Espera al resto");
                ejercicioActual++;
                barrera.await(); // le digo a la profesora que termine
                barrera.await(); // espera a la profesora a que corrija
            } while (ejercicioActual <= cantEjrecicios);
        } catch (Exception ignore) {

        }
    }
}
