package boundary;

import control.ControlPrenotazioni;
import control.OmbrelloneDTO;
import control.ServizioDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class ClientePrenotazione {
    private JPanel effPrenotazionePanel;
    private JScrollPane mappaPane;
    private JScrollPane serviziPane;
    private JButton prenotaButton;
    private JLabel prezzoLabel;
    private JLabel esitoLabel;
    private JSpinner dataSpinner;
    private JPanel mappaPanel;
    private JPanel unoFilaPanel;
    private JPanel dueFilaPanel;
    private JPanel treFilaPanel;
    private JPanel serviziPanel;

    private Long idCliente;

    private long prezzoTotale = 0;
    private LocalDate dataLocale = LocalDate.now();

    List<OmbrelloneDTO> ombrelloni = new ArrayList<>();
    List<ServizioDTO> servizi = new ArrayList<>();

    private OmbrelloneDTO ombrelloneSelezionato;
    Map<Long, Integer> serviziSelezionati = new HashMap<>();

    private final List<JComboBox<Integer>> comboBoxes = new ArrayList<>();
    private final List<ServizioDTO> serviziAssociati = new ArrayList<>();


    public ClientePrenotazione(Long idCliente) {

        SpinnerDateModel model = new SpinnerDateModel();
        dataSpinner.setModel(model);

        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        dataSpinner.setEditor(editor);

        this.idCliente = idCliente;

        prenotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlPrenotazioni cp = new ControlPrenotazioni();

                if (ombrelloneSelezionato == null) {
                    esitoLabel.setText("Seleziona un ombrellone.");
                    return;
                }

                boolean esito = cp.salvaPrenotazione(dataLocale, prezzoTotale, idCliente, ombrelloneSelezionato.getId(), serviziSelezionati);

                if (esito) {
                    esitoLabel.setText("Prenotazione effettuata con successo.");
                } else {
                    esitoLabel.setText(
                            "Impossibile effettuare la prenotazione."
                    );
                }

                //Refresh della pagina
                aggiornaMappa(dataLocale);
                aggiornaServizi(dataLocale);
            }
        });

        //reset quando cambia data
        dataSpinner.addChangeListener(e -> {
            Date data = (Date) dataSpinner.getValue();
            this.dataLocale = data.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            ombrelloneSelezionato = null;
            serviziSelezionati.clear();
            prezzoTotale = 0;

            aggiornaMappa(dataLocale);
            aggiornaServizi(dataLocale);
            aggiornaPrezzo();
        });




    }

    public JFrame apriFormEffPrenotazione() {

        JFrame frame = new JFrame("Effettua Prenotazione");
        frame.setContentPane(effPrenotazionePanel);

        aggiornaMappa(dataLocale);
        aggiornaServizi(dataLocale);
        /*
        aggiornaOmbrelloni(dataSelezionata);
        aggiornaServizi(dataSelezionata);
        aggiornaPrezzo();

         */

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

    private void aggiornaPrezzo() {
        prezzoTotale = 0;

        // Add the selected umbrella price.
        if (ombrelloneSelezionato != null) {
            prezzoTotale += ombrelloneSelezionato.getPrezzo();
        }

        // Add the price of each selected service, multiplied by quantity.
        for (ServizioDTO servizio : serviziAssociati) {
            int quantita = serviziSelezionati.getOrDefault(
                    servizio.getId(), 0
            );

            prezzoTotale += servizio.getPrezzo() * quantita;
        }

        prezzoLabel.setText("Prezzo Totale: " + prezzoTotale + " €");
    }

    private void aggiornaServizi(LocalDate data) {

        serviziPanel.removeAll();
        serviziPanel.setLayout(new GridLayout(0, 1));

        comboBoxes.clear();
        serviziAssociati.clear();

        ControlPrenotazioni cp = new ControlPrenotazioni();
        servizi = cp.creaServiziDisponibili(data);

        for (ServizioDTO servizioDTO : servizi) {
            System.out.println(servizioDTO.getNome());
        }

        if (servizi == null || servizi.isEmpty()) {
            serviziPanel.add(new JLabel("Non sono presenti servizi"));
        } else {
            for (ServizioDTO servizio : servizi) {

                JPanel riga = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));

                JLabel nomeLabel = new JLabel(servizio.getNome());
                JLabel prezzoLabel = new JLabel(servizio.getPrezzo() + " €");

                JComboBox<Integer> combo = new JComboBox<>();
                for (int i = 0; i <= servizio.getRimanenti(); i++) {
                    combo.addItem(i);
                }

                // Initialize the map with the default selected quantity.
                serviziSelezionati.put(servizio.getId(), (Integer) combo.getSelectedItem());

                // Update the map whenever the selection changes.
                combo.addActionListener(e -> {
                    Integer quantita = (Integer) combo.getSelectedItem();
                    serviziSelezionati.put(servizio.getId(), quantita);
                    aggiornaPrezzo();
                });

                riga.add(nomeLabel);
                riga.add(prezzoLabel);
                riga.add(combo);

                serviziPanel.add(riga);

                comboBoxes.add(combo);
                serviziAssociati.add(servizio);
            }
        }

        serviziPanel.revalidate();
        serviziPanel.repaint();
    }

    private void aggiornaMappa(LocalDate data) {

        ControlPrenotazioni cp = new ControlPrenotazioni();

        ombrelloni = cp.creaOmbrelloniDisponibili(data);

        List<OmbrelloneDTO> idPrimaFila = new ArrayList<>();
        List<OmbrelloneDTO> idSecondaFila = new ArrayList<>();
        List<OmbrelloneDTO> idTerzaFila = new ArrayList<>();

        for(OmbrelloneDTO ombrellone : ombrelloni) {

            switch (ombrellone.getFila()) {
                case 1:
                    idPrimaFila.add(ombrellone);
                    break;
                case 2:
                    idSecondaFila.add(ombrellone);
                    break;
                case 3:
                    idTerzaFila.add(ombrellone);
                    break;
            }
        }

        aggiungiFila(unoFilaPanel, idPrimaFila);
        aggiungiFila(dueFilaPanel, idSecondaFila);
        aggiungiFila(treFilaPanel, idTerzaFila);

    }

    private void aggiungiFila(JPanel filaPanel, List<OmbrelloneDTO> idOmbrelloni) {

        //Ripulisce la fila ogni volta che viene chiamata la funzione
        filaPanel.removeAll();
        filaPanel.setLayout(new GridLayout(1, idOmbrelloni.size(), 10, 10));


        if (idOmbrelloni.size() == 0) {
            JLabel label = new JLabel();

            label.setText("Non sono presenti ombrelloni all'interno di questa fila");
            filaPanel.add(label);
        }
        else {
            for (OmbrelloneDTO ombrellone : idOmbrelloni) {
                Long idOmbrellone = ombrellone.getId();
                int posto = ombrellone.getPosto();

                JButton button = new JButton(Integer.toString(posto));
                button.addActionListener(e -> {
                    ombrelloneSelezionato = ombrellone;
                    aggiornaPrezzo();
                });

                button.putClientProperty("idOmbrellone", idOmbrellone);
                filaPanel.add(button);
            }

        }

        filaPanel.revalidate();
        filaPanel.repaint();
    }


}