package boundary;

import control.loginControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormIscrizione {

    private JPanel iscrizionePanel;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JTextField recapitoField;
    private JPasswordField passwordField;
    private JComboBox<String> classificazioneBox;
    private JButton iscrivitiButton;
    private JLabel EsitoLabel;

    public FormIscrizione() {
        iscrivitiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //recupera i valori dal form
                String nome = nomeField.getText();
                String cognome = cognomeField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String recapito = recapitoField.getText();
                String classificazione = classificazioneBox.getSelectedItem().toString();

                //Check se è utente o cliente
                int esito = loginControl.salvaUtente(nome, cognome, email, password, recapito, classificazione);


                if (esito == loginControl.UTENTE_NON_SALVATO){
                    EsitoLabel.setText("Utente non registrato!");
                    System.out.println("Non è stato possibile registrare l'utente.");
                }
                else if (esito == loginControl.CLIENTE_SALVATO){
                    EsitoLabel.setText("Cliente registrato!");
                    System.out.println("Cliente registrato con successo.");
                }
                else if (esito == loginControl.GESTORE_SALVATO){
                    EsitoLabel.setText("Gestore registrato!");
                    System.out.println("Gestore registrato con successo.");
                }

            }
        });
    }

    public JFrame apriFormIscrizione(){

        JFrame frame = new JFrame("Iscrizione Utente");

        //FormInserimentoImbarcazione formImbarcazione = new FormInserimentoImbarcazione();

        frame.setContentPane(iscrizionePanel);

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

}
