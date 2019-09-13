package repaso;

public class Repaso {

    public static void main(String[] args) {
        Repaso r = new Repaso();

        /*Figura[] figuras = new Cuadrilatero[3];
        figuras[0] = new Cuadrado();
        figuras[1] = new Cuadrilatero();
        figuras[2] = new Circulo();*/
       Figura[] figuras = {
                new Cuadrado(),
                new Cuadrilatero(),
                new Circulo()
        };

        for (Figura f : figuras) {
            f.queSoy();
            //f.superficie();

            if (f instanceof Cuadrilatero) {
                ((Cuadrilatero) f).metodoEspecial();
            }
        }

        String [] array_string = new String [25];
        //System.out.println (array_string [3].length());
        String aux = "hola";
        int aux2 = Integer.parseInt ("42");
/*
        Figura figura = new Cuadrado();
        figura.queSoy();
        Cuadrilatero f = (Cuadrilatero) figura;
        f.queSoy();
        f.metodoEspecial();*/
    }

    final String num = null;


}
