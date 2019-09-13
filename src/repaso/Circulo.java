package repaso;

public class Circulo extends Figura {

    @Override
    public void queSoy() {
        System.out.println("Circulo");
    }

    @Override
    public void superficie() {
        System.out.println("PI*r²");
    }
}