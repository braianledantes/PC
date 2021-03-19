package Saludo_Jefe_Empleado;

import colores.ColoresString;

import java.util.concurrent.CountDownLatch;

public class SaludoCountDownLatch implements Saludo {
    CountDownLatch saludoJefe;
    CountDownLatch saludoEmpleados;

    public SaludoCountDownLatch(int cantEmpleados) {
        this.saludoJefe = new CountDownLatch(cantEmpleados);
        this.saludoEmpleados = new CountDownLatch(1);
    }

    @Override
    public void esperarJefe(String nombreEmpleado) {
        try {
            saludoJefe.countDown();
            saludoEmpleados.await();
            System.out.println(ColoresString.ANSI_RESET + nombreEmpleado + ": Hola jefecito");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saludoJefe() {
        try {
            saludoJefe.await();
            saludoEmpleados.countDown();
            System.out.println(ColoresString.ANSI_RED + "Jefe: Buen día");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
