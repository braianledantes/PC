package repaso;

public class Cuadrado extends Cuadrilatero {
    @Override
    public void queSoy() {
        //super.queSoy();
        System.out.println("Cuadrado");
    }

    public void metodoEspecial(){
        System.out.println("soy un metodo especial de Cuadrado");
    }
}
