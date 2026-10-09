package boundary;

import control.ControlLogin;
import entity.Cliente;
import entity.Gestore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormAccesso {
    private JPanel accessoPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton accediButton;
    private JLabel EsitoLabel;
    private JComboBox classificazioneBox;

    //frame per la chiamata ad altre schermate
    private JFrame frameStabilimento;
    private JFrame frameUtenti;


    public FormAccesso() {
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = emailField.getText();
                String password = passwordField.getText();
                String classificazione = classificazioneBox.getSelectedItem().toString();

                boolean validitaAccesso = ControlLogin.controllaAccesso(email, password, classificazione);

                //ACCESSO SCHERMATA CLIENTE
                if(validitaAccesso && classificazione == "Cliente"){

                    EsitoLabel.setText("Accesso consentito");
                    Long idCliente = ControlLogin.restituisciIdUtente(email, password, classificazione);

                    if (frameUtenti == null ||
                            !frameUtenti.isDisplayable()) {

                        //nuovo oggetto schermataUtente
                        SchermataCliente schermataUtente =
                                new SchermataCliente(idCliente);


                        frameUtenti = schermataUtente.apriFormUtente();

                        frameUtenti.setLocationRelativeTo(null);
                        frameUtenti.setVisible(true);

                    } else {

                        //Se la finestra esiste, viene spostata in avanti
                        frameUtenti.toFront();
                        frameUtenti.requestFocus();
                    }

                }
                //ACCESSO SCHERMATA GESTORE
                else if(validitaAccesso && classificazione == "Gestore"){

                    EsitoLabel.setText("Accesso consentito");
                    Long idGestore = ControlLogin.restituisciIdUtente(email, password, classificazione);

                    if (frameStabilimento == null ||
                            !frameStabilimento.isDisplayable()) {

                        //nuovo oggetto schermataStabilimento
                        SchermataGestore schermataStabilimento =
                                new SchermataGestore(idGestore);


                        frameStabilimento = schermataStabilimento.apriFormStabilimento();

                        frameStabilimento.setLocationRelativeTo(null);
                        frameStabilimento.setVisible(true);

                    } else {

                        //Se la finestra esiste, viene spostata in avanti
                        frameStabilimento.toFront();
                        frameStabilimento.requestFocus();
                    }

                }
                else{
                    EsitoLabel.setText("Credenziali non valide");
                }

                emailField.setText("");
                passwordField.setText("");
/*

                loginControl.controllaAccesso(email,password,classificazione)
                Utente utente = loginControl.controllaAccesso(email, password, classificazione);

                //Controlla se l'utente esiste ed è un Gestore
                if (utente != null && utente.getClassificazione() == ClassificazioneUtente.GESTORE) {

                    Gestore idGestore = (Gestore) utente;
                    if (frameStabilimento == null ||
                        !frameStabilimento.isDisplayable()) {

                    //nuovo oggetto schermataStabilimento
                    SchermataStabilimento schermataStabilimento =
                            new SchermataStabilimento(idGestore);


                    frameStabilimento = schermataStabilimento.apriFormStabilimento();

                    frameStabilimento.setLocationRelativeTo(null);
                    frameStabilimento.setVisible(true);

                    } else {

                    //Se la finestra esiste, viene spostata in avanti
                    frameStabilimento.toFront();
                    frameStabilimento.requestFocus();
                    }

                }

                //Controlla se l'utente esiste ed è un Cliente
                else if(utente != null && utente.getClassificazione().equals(ClassificazioneUtente.CLIENTE)){

                    Cliente idCliente = (Cliente) utente;
                    if (frameUtenti == null ||
                            !frameUtenti.isDisplayable()) {

                        //nuovo oggetto schermataUtente
                        SchermataUtente schermataUtente =
                                new SchermataUtente(idCliente);


                        frameUtenti = schermataUtente.apriFormUtente();

                        frameUtenti.setLocationRelativeTo(null);
                        frameUtenti.setVisible(true);

                    } else {

                        //Se la finestra esiste, viene spostata in avanti
                        frameUtenti.toFront();
                        frameUtenti.requestFocus();
                    }

                }

                //Controlla se l'utente non esiste
                else {

                    EsitoLabel.setText("Utente non trovato!");

                }

                emailField.setText("");
                passwordField.setText("");
                */
            }
        });
    }

    public JFrame apriFormAccesso() {

        JFrame frame = new JFrame("Accesso Utente");

        frame.setContentPane(accessoPanel);

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }
}
