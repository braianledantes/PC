package TP2_Concurrente.ejercicio5;

public class Vehiculo extends Thread {
    public static final String ANSI_BLUE = "\u001B[34m";
    private EstacionDeServicio servicio;
    private double conbustible, kmPorLitro, capcidadTanque;

    public Vehiculo(String patente, double conbustible, double kmPorLitro, double capcidadTanque, EstacionDeServicio servicio) {
        super(patente);
        this.conbustible = conbustible;
        this.kmPorLitro = kmPorLitro;
        this.capcidadTanque = capcidadTanque;
        this.servicio = servicio;
    }

    public double getConbustible() {
        return conbustible;
    }

    public void setConbustible(double conbustible) {
        this.conbustible = conbustible;
    }

    public double getKmPorLitro() {
        return kmPorLitro;
    }

    public void setKmPorLitro(double kmPorLitro) {
        this.kmPorLitro = kmPorLitro;
    }

    public void setCapcidadTanque(double capcidadTanque) {
        this.capcidadTanque = capcidadTanque;
    }

    public double getCapcidadTanque() {
        return capcidadTanque;
    }

    public void mover(int km) {
        try {
            Thread.sleep(1000);
            conbustible = conbustible - (km * kmPorLitro);
            conbustible = (conbustible < 0) ? 0 : conbustible;
            System.out.println(ANSI_BLUE + getName() + " moviendo " + km + "km(" + conbustible + ")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            if (conbustible <= 0) {
                servicio.surtir(this);
            }
            this.mover(10);
        }
    }

}
