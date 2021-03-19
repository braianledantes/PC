package Donantes.locks;

import colores.ColoresString;

public interface Sala {

    boolean intentarEntrar();

    boolean intentarEntrarASalsa();

    boolean intentarLeerRevista();

    void mirarTV();

    void donarSangre();

    void salir();
}
