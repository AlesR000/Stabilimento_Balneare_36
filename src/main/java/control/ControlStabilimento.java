package control;

import entity.RegistroStabilimento;
import entity.ServizioAggiuntivo;
import entity.Tariffa;

import java.util.ArrayList;
import java.util.List;

public class ControlStabilimento {

    //Funzioni Salva
    public boolean salvaOmbrellone(String fila, long prezzo){

        RegistroStabilimento registro = new RegistroStabilimento();

        int filaInt = filaToInt(fila);
        if(filaInt == 0){
            System.out.print("Seleziona una fila esistente");
            return false;
        }

        int posto =  registro.ultimoPostoPerFila(filaInt) + 1;
        return registro.registraOmbrellone(prezzo, filaInt, posto);

    }

    public boolean salvaServizio(String nome, int quantita, String prezzo, String descrizione){
        RegistroStabilimento registro = new RegistroStabilimento();

        long prezzoLong = prezzoToLong(prezzo);
        if (prezzoLong == 0){
            System.out.print("Seleziona una prezzo valido");
            return false;
        }

        return registro.registraServizio(nome, prezzoLong, quantita, descrizione);
    }

    //Funzioni di rimozione
    public boolean rimuoviOmbrellone(String fila){

        RegistroStabilimento registro = new RegistroStabilimento();

        int filaInt = filaToInt(fila);
        if(filaInt == 0){
            System.out.print("Seleziona una fila esistente");
            return false;
        }

        return registro.eliminaOmbrellone(filaInt);

    }

    public boolean rimuoviServizio(Long id){

        RegistroStabilimento registro = new RegistroStabilimento();
        return registro.eliminaServizio(id);

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

    public List<ServizioDTO> listaServizi(){

        RegistroStabilimento registro = new RegistroStabilimento();
        List<ServizioAggiuntivo> serviziAggiuntivi = registro.ricercaServizi();

        if(serviziAggiuntivi.isEmpty()){
            return null;
        }
        else {
            List<ServizioDTO> listaServiziDTO = new ArrayList<>();

            for (int i = 0; i < serviziAggiuntivi.size(); i++) {
                ServizioAggiuntivo sAgg = serviziAggiuntivi.get(i);
                ServizioDTO servizioDTO = new ServizioDTO(
                        sAgg.getId(),
                        sAgg.getNome(),
                        sAgg.getPrezzoStandard(),
                        sAgg.getQuantita(),
                        sAgg.getDescrizione()
                );

                listaServiziDTO.add(servizioDTO);
            }

            return listaServiziDTO;
        }
    }

    public static List<TariffaDTO> listaTariffe(){

        RegistroStabilimento registro = new RegistroStabilimento();
        List<Tariffa> listaTariffe = registro.ricercaTariffe();

        if(listaTariffe.isEmpty()){
            return null;
        }
        else{
            List<TariffaDTO> listaTariffeDTO = new ArrayList<>();

            for(int i = 0; i < listaTariffe.size(); i++){
                Tariffa tariffa = listaTariffe.get(i);
                String associazione;
                if(!tariffa.getOmbrelloni().isEmpty()){
                    int fila = tariffa.getFila();
                    associazione = filaToString(fila);
                }
                else{
                    associazione = tariffa.getServizio().getNome();
                }

                TariffaDTO tarDTO = new TariffaDTO(
                        tariffa.getId(),
                        tariffa.getPrezzo(),
                        tariffa.getDataInizio(),
                        tariffa.getDataFine(),
                        associazione
                );

                listaTariffeDTO.add(tarDTO);
            }
            return listaTariffeDTO;
        }


    }


    //Funzioni Aggiorna
    public boolean aggiornaServizio(long id, String nome, String prezzoStandard, int quantita, String descrizione){

        RegistroStabilimento registro = new RegistroStabilimento();

        long prezzoLong = prezzoToLong(prezzoStandard);
        if (prezzoLong == 0){
            System.out.print("Seleziona una prezzo valido");
            return false;
        }

        ServizioAggiuntivo servizioAggiornato = registro.aggiornaServizio(id,nome,prezzoLong,quantita,descrizione);

        if(servizioAggiornato == null){
            return false;
        }

        return true;
    }

    //Funzioni ausiliarie
    private static int filaToInt (String fila){

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

    private static String filaToString (int fila){
        switch (fila){
            case 1:
                return "prima fila";
                case 2:
                    return "seconda fila";
                    case 3:
                        return "terza fila";
        }

        return "";
    }

    private long prezzoToLong (String prezzo){

        try {
            long prezzoInt = Long.parseLong(prezzo);
            return prezzoInt;
        }
        catch (NumberFormatException e){
            System.out.println("Prezzo invalido");
            return 0;
        }
    }

}

