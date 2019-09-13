import java.io.*;
import java.util.ArrayList;

public class Main {

    /**
     * <h1>Consultas</h1>
     * <p>martes 16hs a 18hs ----> Agustin</p>
     */
    public static void main(String[] args) {
        long endTime, iniTime = System.currentTimeMillis();
        File file = new File("/home/braian/Documentos/dictionaries-master/es/es_ANY.dic");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String text = "";
        StringBuilder builder = new StringBuilder();
        ArrayList<String> palabras = new ArrayList<>();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String palabra;
            while ((palabra = bufferedReader.readLine()) != null) {
                //palabras.add(palabra);
                //text += palabra + "\n";
                builder.append(palabra).append("\n");
            }
            text = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            endTime = System.currentTimeMillis();
            System.out.println("longitud: " + palabras.size());
            System.out.println("tiempo de ejecucion: " + ((endTime - iniTime)) + " milisegundos");
        }
    }
}
