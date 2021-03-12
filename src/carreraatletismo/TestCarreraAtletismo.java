package carreraatletismo;

public class TestCarreraAtletismo {

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        Corredor corredor1 = new Corredor("corredor1", carrera);
        Corredor corredor2 = new Corredor("corredor2", carrera);
        Corredor corredor3 = new Corredor("corredor3", carrera);
        Corredor corredor4 = new Corredor("corredor4", carrera);

        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
    }
}
