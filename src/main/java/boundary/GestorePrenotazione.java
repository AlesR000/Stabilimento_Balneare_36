package boundary;

import control.ControlPrenotazioni;
import control.PrenotazioneDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GestorePrenotazione {
    private JPanel prenotazioniPanel;
    private JScrollPane scrollPane;

    List<PrenotazioneDTO> prenotazioni = new ArrayList<PrenotazioneDTO>();


    public GestorePrenotazione() {

        ControlPrenotazioni c = new ControlPrenotazioni();

        JPanel listaPrenotazioni = new JPanel();
        listaPrenotazioni.setLayout(new BoxLayout(listaPrenotazioni, BoxLayout.Y_AXIS));

        prenotazioni = c.ricercaPrenotazioni();

        for (PrenotazioneDTO prenotazione : prenotazioni) {

            JPanel riga = new JPanel(new FlowLayout(FlowLayout.LEFT));

            riga.add(new JLabel("Data: " + prenotazione.getData()));
            riga.add(new JLabel(" | Fila: " + prenotazione.getFila()));
            riga.add(new JLabel(" | Posto: " + prenotazione.getPosto()));
            riga.add(new JLabel(" | Cliente: " + prenotazione.getCliente()));
            riga.add(new JLabel(" | Recapito: " + prenotazione.getRecapito()));

            listaPrenotazioni.add(riga);
        }

        scrollPane.setViewportView(listaPrenotazioni);
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public JFrame apriFormGestorePrenotazioni() {

        JFrame frame = new JFrame("Visualizza Prenotazioni");

        frame.setContentPane(prenotazioniPanel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

}