package control;

import entity.Cliente;
import entity.Gestore;
import entity.RegistroUtente;
import entity.Utente;

public class loginControl {

    public static final int UTENTE_NON_SALVATO = 0;
    public static final int CLIENTE_SALVATO = 1;
    public static final int GESTORE_SALVATO = 2;

    private static final PasswordProtezione passwordHasher =
            new PasswordProtezione();


    //Operazioni di salvataggio
    public static int salvaUtente(String nome, String cognome, String email,
                                  String password, String recapito, String classificazione) {

        boolean validitaDati = validaNome(nome)
                && validaNome(cognome)
                && validaRecapito(recapito)
                && validaEmail(email)
                && validaPassword(password);

        if(!validitaDati){

            return UTENTE_NON_SALVATO;

        }
        else if(classificazione == "Cliente"){

            RegistroUtente registro = new RegistroUtente();

            String passwordHash = passwordHasher.hash(password);
            int recapitoInt = recapitoToInt(recapito);

            Cliente cliente = new Cliente(nome, cognome, email, passwordHash, recapitoInt);

            registro.registroCliente(cliente);

            return CLIENTE_SALVATO;
        }
        else if(classificazione == "Gestore"){

            RegistroUtente registro = new RegistroUtente();

            String passwordHash = passwordHasher.hash(password);
            int recapitoInt = recapitoToInt(recapito);

            Gestore gestore = new Gestore(nome, cognome, email, passwordHash, recapitoInt);

            registro.registroGestore(gestore);

            return GESTORE_SALVATO;
        }

            return 0;
    }

    //Operazioni di login
    public static boolean controllaAccesso(String email, String password, String classificazione){
        return true;
    }

    public static Utente restituisciUtente(String email, String password, String classificazione){

        RegistroUtente registro = new RegistroUtente();
        return registro.cercaUtente(email, password, classificazione);
    }

    //Controlli sulla validità dei dati inseriti dall'utente
    public static boolean validaNome(String nome) {
        return nome != null && nome.matches("[a-zA-ZÀ-ÿ ]+");
    }

    public static boolean validaRecapito(String recapito) {

        if(recapito.length()!=10){
            return false;
        }

        int numero = 0;

        try {
            numero = Integer.parseInt(recapito);

            System.out.println(numero);

        } catch (NumberFormatException e) {
            // Il recapito non è un numero valido
            System.out.println("Inserisci un numero valido");
            return false;
        }

        return true;
    }
    public static int recapitoToInt(String recapito) {
        int numero = 0;
        try {
            numero = Integer.parseInt(recapito);

            System.out.println(numero);
        } catch (NumberFormatException e) {
            // Il recapito non è un numero valido
            System.out.println("Inserisci un numero valido");
        }
        return numero;
    }

    public static boolean validaEmail(String email) {
        //Check basilare per controllare la formattazione della mail
        return email != null &&
                email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    }

    public static boolean validaPassword(String password) {

        if(password.length()<8 && password.length()>15){
            return false;
        }

        if(password != null && password.matches(".*[A-Za-z0-9].*") && password.matches(".*[^A-Za-z0-9].*")){
            return true;
        }

        return false;
    }


}
