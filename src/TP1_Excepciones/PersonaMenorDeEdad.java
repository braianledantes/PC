package TP1_Excepciones;

public class PersonaMenorDeEdad extends RuntimeException {

    public PersonaMenorDeEdad(String message) {
        super(message);
    }
}
