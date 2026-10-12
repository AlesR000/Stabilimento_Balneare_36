package control;

import entity.RegistroStabilimento;

import java.util.List;

public class ControlStabilimento {



    //Funzioni Salva
    public boolean salvaOmbrellone(String fila, long prezzo){

        RegistroStabilimento registro = new RegistroStabilimento();

        int filaInt = filaToInt(fila);
        if(filaInt == 0 || registro.ultimoPostoPerFila(filaInt) == 0){
            return false;
        }

        int posto =  registro.ultimoPostoPerFila(filaInt) + 1;
        return registro.RegistraOmbrellone(prezzo, filaInt, posto);

    }

    //Funzioni di Ricerca
    public static List<Long> idOmbrelloniFila(int fila){

        if(fila < 1 && fila > 3) {
            System.out.println("fila inesistente");
            return null;
        }

            RegistroStabilimento registro = new RegistroStabilimento();
            return registro.idOmbrelloniFila(fila);

    }

    //Funzioni ausiliarie
    private int filaToInt (String fila){

        fila = fila.toLowerCase();

        switch (fila){
            case "prima fila":
                return 1;
            case "seconda fila":
                return 2;
            case "terza fila":
                return 3;
        }

        return 0;
    }

}
