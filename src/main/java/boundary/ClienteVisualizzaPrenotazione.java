package boundary;



import control.ControlPrenotazioni;
import control.PrenotazioneDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteVisualizzaPrenotazione {
    private JPanel panel1;
    private JButton annullaPrenotazioneButton;
    private JLabel esitoLabel;
    private JScrollPane prenotazioniPane;
    private JPanel prenotazioniPanel;

    private Long idCliente;
    private List<PrenotazioneDTO> prenotazioni = new ArrayList<PrenotazioneDTO>();

    private Long prenotazioneSelezionata;
    private LocalDate dataPrenotazione;


    public ClienteVisualizzaPrenotazione(Long idCliente) {

        this.idCliente = idCliente;


        annullaPrenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlPrenotazioni cp = new ControlPrenotazioni();

                boolean esito = cp.eliminaPrenotazione(dataPrenotazione, prenotazioneSelezionata);
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

        ControlPrenotazioni cp = new ControlPrenotazioni();


        prenotazioni = cp.prenotazioniCliente(idCliente);

        prenotazioniPanel.removeAll();
        prenotazioniPanel.setLayout(new BoxLayout(prenotazioniPanel, BoxLayout.Y_AXIS));

        ButtonGroup gruppoPrenotazioni = new ButtonGroup();

        if (prenotazioni == null) {
            JLabel prenLabel = new JLabel("Non ci sono prenotazioni");
        }
        else{
            for (PrenotazioneDTO prenotazione : prenotazioni) {

                JRadioButton radioButton = new JRadioButton(
                        "Data: " + prenotazione.getData() +
                                " | Fila: " + prenotazione.getFila() +
                                " | Posto: " + prenotazione.getPosto());

                //Associa la Prenotazione al RadioButton
                radioButton.putClientProperty("prenotazione", prenotazione);

                //Salva la Prenotazione selezionata localmente
                radioButton.addActionListener(e -> {
                    prenotazioneSelezionata = prenotazione.getIdPrenotazione();
                    dataPrenotazione = prenotazione.getData();
                });

                gruppoPrenotazioni.add(radioButton);
                prenotazioniPanel.add(radioButton);
            }
        }

        prenotazioniPanel.revalidate();
        prenotazioniPanel.repaint();
    }


}
