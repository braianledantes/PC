package Filosofos;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Mesa extends JFrame {
    private JPanel panelBotones, panelFilosofos;
    private JButton btnStart, btnStop;
    private JLabel[] labelF;
    private JLabel[] labelT;
    private JLabel marcaRegistrada;
    private JMenuBar menuBar;
    private JMenuItem miAcercade;

    private int cant;
    private Filosofo[] filosofos;
    private Tenedor[] tenedores;
    private Random random;

    public Mesa(int cant) {
        this.cant = cant;
        random = new Random(System.currentTimeMillis());
        tenedores = new Tenedor[cant];

        for (int i = 0; i < cant; i++) {
            tenedores[i] = new Tenedor(1, true, "" + i);
        }
    }

    public Mesa(Filosofo[] filosofos) {
        this(filosofos.length);
        this.filosofos = filosofos;

        initComponets();

        setTitle("Problema de los Filosofos");
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponets() {
        panelFilosofos = new JPanel(new GridLayout(4, 3));
        labelT = new JLabel[cant];
        labelF = new JLabel[cant];
        for (int i = 0; i < cant; i++) {
            labelF[i] = new JLabel(":)   ", SwingConstants.CENTER);
            labelT[i] = new JLabel("|", SwingConstants.CENTER);
        }

        panelFilosofos.add(labelF[0]);
        panelFilosofos.add(labelT[1]);
        panelFilosofos.add(labelF[1]);
        panelFilosofos.add(labelT[0]);
        panelFilosofos.add(new JLabel());
        panelFilosofos.add(labelT[2]);
        panelFilosofos.add(labelF[4]);
        panelFilosofos.add(new JLabel());
        panelFilosofos.add(labelF[2]);
        panelFilosofos.add(labelT[4]);
        panelFilosofos.add(labelF[3]);
        panelFilosofos.add(labelT[3]);


        panelBotones = new JPanel();

        marcaRegistrada = new JLabel("Braian Ledantes");
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        //btnStart.setBackground(Color.GREEN);
        btnStart.addActionListener(e -> {
            btnStart.setEnabled(false);
            for (int i = 0; i < filosofos.length; i++) {
                filosofos[i].start();
            }
        });

        //panelBotones.add(marcaRegistrada);
        panelBotones.add(btnStart);
        //panelBotones.add(btnStop);

        add(panelFilosofos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void comerVisual(Filosofo filosofo) {
        Tenedor tenedor1 = tenedores[filosofo.getPos()];
        Tenedor tenedor2 = tenedores[(filosofo.getPos() + 1) % cant];
        JLabel labelFilosofo = labelF[filosofo.getPos()];
        JLabel labelT1 = labelT[filosofo.getPos()];
        JLabel labelT2 = labelT[(filosofo.getPos() + 1) % cant];

        boolean tomo1 = tenedor1.tryAcquire();
        boolean tomo2 = tenedor2.tryAcquire();

        // si el filosofo tomo uno de los tenedores
        if ((tomo1 && !tomo2) || (!tomo1 && tomo2)) {
            if (tomo1) {
                labelT1.setText("");
                labelFilosofo.setText(":/ | ");
            } else {
                labelT2.setText("");
                labelFilosofo.setText(":/ | ");
            }
            // espera con el tenedor en la mano
            dormirHilo(2, 4);
            // intenta tomar el que le falta
            if (tomo1) {
                tomo2 = tenedor2.tryAcquire();
            } else {
                tomo1 = tenedor1.tryAcquire();
            }
        }

        // si el filosofo tomo los dos tenedores puede comer
        if (tomo1 && tomo2) {
            labelT1.setText("");
            labelT2.setText("");
            labelFilosofo.setText(":O ||");
            dormirHilo(3, 5);
        }
        // luego los libera
        if (tomo1) {
            labelT1.setText("|");
            tenedor1.release();
        }
        if (tomo2) {
            labelT2.setText("|");
            tenedor2.release();
        }
        // y espera tranquilo
        labelFilosofo.setText(":)   ");
    }

    private void dormirHilo(int min, int max) {
        try {
            Thread.sleep(1000 * (min + random.nextInt(max - min)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo que funciona pero los filosofos nunca se quedan con un tenedor en la mano, lo liberan instantaneamente
     *
     * @param filosofo
     */
    public void comerOld(Filosofo filosofo) {
        Tenedor tenedor1 = tenedores[filosofo.getPos()];
        Tenedor tenedor2 = tenedores[(filosofo.getPos() + 1) % cant];
        boolean tomo1 = tenedor1.tryAcquire();
        boolean tomo2 = tenedor2.tryAcquire();

        if (tomo1 && tomo2) {
            try {
                System.out.println("\u001B[31m" + filosofo.getName() + " estoy COMIENDO <----");
                Thread.sleep(1000 * (1 + random.nextInt(3)));
                System.out.println("\u001B[0m" + filosofo.getName() + " DEJÉ DE COMER  ---->");
                tenedor1.release();
                tenedor2.release();
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if (tomo1) {
                tenedor1.release();
            }
            if (tomo2) {
                tenedor2.release();
            }
        }
    }
}
