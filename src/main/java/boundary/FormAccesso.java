package boundary;

import control.ControllerUtenti;
import entity.ClassificazioneUtente;
import entity.Cliente;
import entity.Gestore;
import entity.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormAccesso {
    private JPanel accessoPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton accediButton;
    private JComboBox classificazioneBox;
    private JLabel EsitoLabel;

    //frame per la chiamata ad altre schermate
    private JFrame frameStabilimento;
    private JFrame frameUtenti;


    public FormAccesso() {
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = emailField.getText();
                String password = passwordField.getText();
                ClassificazioneUtente classificazione = (ClassificazioneUtente) classificazioneBox.getSelectedItem();

                Utente utente = ControllerUtenti.controllaAccesso(email, password, classificazione);

                //Controlla se l'utente esiste ed è un Gestore
                if (utente != null && utente.getClassificazione() == ClassificazioneUtente.GESTORE) {

                    Gestore gestore = (Gestore) utente;
                    if (frameStabilimento == null ||
                        !frameStabilimento.isDisplayable()) {

                    //nuovo oggetto schermataStabilimento
                    SchermataStabilimento schermataStabilimento =
                            new SchermataStabilimento(gestore);


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

                    Cliente cliente = (Cliente) utente;
                    if (frameUtenti == null ||
                            !frameUtenti.isDisplayable()) {

                        //nuovo oggetto schermataUtente
                        SchermataUtente schermataUtente =
                                new SchermataUtente(cliente);


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
            }
    });
    }

    public JFrame apriFormAccesso() {

        JFrame frame = new JFrame("Accesso Utente");

        frame.setContentPane(accessoPanel);

        //Inizializzo i valori ENUM nella comboBox
        classificazioneBox.setModel(new DefaultComboBoxModel(ClassificazioneUtente.values()));

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }
}
