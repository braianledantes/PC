package TP1_Excepciones;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class PruebaExcep {
    public static void main(String[] args) {

        try {

            jugarRuleta();

        }finally {
            System.out.println("estoy en finally");
        }
        //leerEdad();
    }

    static int leerEdad() throws PersonaMenorDeEdad {
        Scanner scanner = new Scanner(System.in);
        int edad = scanner.nextInt();

        if (edad < 18) {
            throw new PersonaMenorDeEdad("Persona menor de edad: " + edad + " anyos");
        }
        return edad;
    }

    static boolean jugarRuleta() {
        String input = JOptionPane.showInputDialog("Juegue un número: ...");
        int num = Integer.parseInt(input);
        int numRuleta = (new Random()).nextInt(9) + 1;

        if (num != numRuleta) throw new RuntimeException("mala jugada, num: " + num + ", numRuleta: " + numRuleta);
        JOptionPane.showMessageDialog(null, "Ganaste!!!");
        return true;
    }

    static void ingresarNumeros() throws RuntimeException {

    }
}
