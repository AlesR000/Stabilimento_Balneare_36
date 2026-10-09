package boundary;

import entity.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataCliente {


    private JPanel utentePanel;
    private JButton prenotaButton;
    private JButton visualizzaButton;
    private JLabel benvenutoLabel;
    private JButton logoutButton;

    private Cliente cliente;

    private JFrame frame;
    //Frame per la chiamata ad una nuova finestra
    private JFrame prenotaFrame;
    private JFrame visualizzaFrame;

    public JFrame apriFormUtente() {

        frame = new JFrame("Homepage Utente");

        frame.setContentPane(utentePanel);

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

    public SchermataCliente(Cliente cliente) {
        this.cliente = cliente;
        benvenutoLabel.setText("Benvenuto, " + cliente.getNome() + "!");
        prenotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prenotaFrame == null ||
                        !prenotaFrame.isDisplayable()) {

                    //nuovo oggetto formEffPrenotazione
                    FormEffettuaPrenotazione formEffettuaPrenotazione =
                            new FormEffettuaPrenotazione(cliente);


                    prenotaFrame = formEffettuaPrenotazione.apriFormEffPrenotazione();

                    prenotaFrame.setLocationRelativeTo(null);
                    prenotaFrame.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    prenotaFrame.toFront();
                    prenotaFrame.requestFocus();
                }
            }
        });
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visualizzaFrame == null ||
                        !visualizzaFrame.isDisplayable()) {

                    //nuovo oggetto visualizza Prenotazione
                    SchermataPrenotazioniCliente schermataPrenotazioneCliente =
                            new SchermataPrenotazioniCliente(cliente);


                    visualizzaFrame = schermataPrenotazioneCliente.apriPrenotazioniCliente();
                    visualizzaFrame.setLocationRelativeTo(null);
                    visualizzaFrame.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    visualizzaFrame.toFront();
                    visualizzaFrame.requestFocus();
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame != null) {
                    frame.dispose();
                }
            }
        });
    }
}
