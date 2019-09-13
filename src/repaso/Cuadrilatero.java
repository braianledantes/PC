package repaso;

public class Cuadrilatero extends Figura {
    @Override
    public void superficie() {
        System.out.println("B*A");
    }

    @Override
    public void queSoy() {
        System.out.println("Cuadrilatero");
    }

    public void metodoEspecial(){
        System.out.println("soy un metodo especial de Cuadrilatero");
    }
}