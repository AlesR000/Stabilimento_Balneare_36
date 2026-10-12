package boundary;

import entity.Cliente;
import entity.Prenotazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SchermataPrenotazioniCliente {
    private JPanel panel1;
    private JButton annullaPrenotazioneButton;
    private JLabel esitoLabel;
    private JScrollPane prenotazioniPane;
    private JPanel prenotazioniPanel;

    private Cliente cliente;
    private Prenotazione prenotazioneSelezionata;


    public SchermataPrenotazioniCliente(Cliente cliente) {
        this.cliente = cliente;
    }
        /*
        annullaPrenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean esito = ControllerPrenotazioni.cancellaPrenotazione(prenotazioneSelezionata);
                if (esito) {
                    esitoLabel.setText("Prenotazione cancellata");
                }
                else {
                    esitoLabel.setText("Impossibile cancellare prenotazione");
                }
                aggiornaPrenotazioni();
            }
        });

    }

    public JFrame apriPrenotazioniCliente() {

        JFrame frame = new JFrame("Visualizza Prenotazioni");

        frame.setContentPane(panel1);

        aggiornaPrenotazioni();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;

    }
    public void aggiornaPrenotazioni() {

        List<Prenotazione> prenotazioni = ControllerPrenotazioni.getPrenotazioniCliente(cliente);

        prenotazioniPanel.removeAll();
        prenotazioniPanel.setLayout(new BoxLayout(prenotazioniPanel, BoxLayout.Y_AXIS));

        ButtonGroup gruppoPrenotazioni = new ButtonGroup();

        for (Prenotazione prenotazione : prenotazioni) {

            JRadioButton radioButton = new JRadioButton(
                    "Data: " + prenotazione.getDataPrenotazione() +
                            " | Ombrellone: " + prenotazione.getOmbrellone().getNumero_posto() +
                            " | Prezzo: " + prenotazione.getPrezzo() + " €"
            );

            //Associa la Prenotazione al RadioButton
            radioButton.putClientProperty("prenotazione", prenotazione);

            //Salva la Prenotazione selezionata localmente
            radioButton.addActionListener(e -> {
                prenotazioneSelezionata =
                        (Prenotazione) radioButton.getClientProperty("prenotazione");
            });

            gruppoPrenotazioni.add(radioButton);
            prenotazioniPanel.add(radioButton);
        }

        prenotazioniPanel.revalidate();
        prenotazioniPanel.repaint();
    }

         */
}
