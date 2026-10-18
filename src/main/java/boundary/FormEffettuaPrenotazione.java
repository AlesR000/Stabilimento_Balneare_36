package boundary;

import control.ControlStabilimento;
import control.ServizioDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class FormEffettuaPrenotazione {
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

    private int prezzoCorrente;
    private Date dataSelezionata;
    private Long ombrelloneSelezionato;
    private List<Long> serviziSelezionati;

    List<Long> idPrimaFila;
    List<Long> idSecondaFila;
    List<Long> idTerzaFila;


    public FormEffettuaPrenotazione(Long idCliente) {

        SpinnerDateModel model = new SpinnerDateModel();
        dataSpinner.setModel(model);

        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        dataSpinner.setEditor(editor);

        this.idCliente = idCliente;
        /*
        prenotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ombrelloneSelezionato == null) {
                    esitoLabel.setText("Seleziona un ombrellone.");
                    return;
                }

                // Recupera tutti i servizi selezionati
                serviziSelezionati = new HashMap<>();

                Component[] componenti = ((JPanel) serviziPane.getViewport().getView()).getComponents();

                for (Component componente : componenti) {
                    JPanel rigaServizio = (JPanel) componente;

                    ServizioAggiuntivo servizio = (ServizioAggiuntivo) rigaServizio.getClientProperty("servizio");
                    JComboBox<Integer> comboBox = (JComboBox<Integer>) rigaServizio.getClientProperty("comboBox");

                    int quantita = (Integer) comboBox.getSelectedItem();
                    if (quantita > 0) {
                        serviziSelezionati.put(servizio, quantita);
                    }
                }

                //Calcola il prezzo prima di salvare
                aggiornaPrezzo();

                boolean risultato = ControllerPrenotazioni.salvaPrenotazione(
                        cliente,
                        dataSelezionata.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate(),
                        prezzoCorrente,
                        ombrelloneSelezionato,
                        serviziSelezionati
                );

                if (risultato) {
                    esitoLabel.setText("Prenotazione effettuata con successo.");
                } else {
                    esitoLabel.setText(
                            "Impossibile effettuare la prenotazione."
                    );
                }

                //Refresh della pagina
                aggiornaMappa(dataSelezionata);
                aggiornaServizi(dataSelezionata);
            }
        });

        dataSpinner.addChangeListener(e -> {
            dataSelezionata = (Date) dataSpinner.getValue();

            ombrelloneSelezionato = null;
            serviziSelezionati = new HashMap<>();
            prezzoCorrente = 0;

            aggiornaMappa(dataSelezionata);
            aggiornaServizi(dataSelezionata);
            aggiornaPrezzo();
        });

         */
    }

    public JFrame apriFormEffPrenotazione() {

        JFrame frame = new JFrame("Effettua Prenotazione");

        frame.setContentPane(effPrenotazionePanel);

        dataSelezionata = (Date) dataSpinner.getValue();

        aggiornaMappa();
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
    /*
    private void aggiornaOmbrelloni(Date data) {

        Map<Fila, ArrayList<Ombrellone>> mappa =
                ControllerOmbrelloni.mappaOmbrelloni();

        List<Long> prenotati =
                ControllerOmbrelloni.getOmbrelloniPrenotati(data);

        mappaPanel.removeAll();
        mappaPanel.setLayout(new GridLayout(3, 1));

        ButtonGroup gruppoOmbrelloni = new ButtonGroup();

        for (Fila fila : Fila.values()) {

            JPanel pannelloFila = new JPanel();
            pannelloFila.setLayout(new GridLayout(1, 0));

            for (Ombrellone ombrellone : mappa.get(fila)) {

                JRadioButton radioButton =
                        new JRadioButton(String.valueOf(ombrellone.getNumero_posto()));

                radioButton.putClientProperty("ombrellone", ombrellone);

                radioButton.addActionListener(e -> {
                    ombrelloneSelezionato =
                            (Ombrellone) radioButton.getClientProperty("ombrellone");

                    aggiornaPrezzo();
                });

                if (prenotati.contains(Long.valueOf(ombrellone.getId()))) {
                    radioButton.setEnabled(false);
                }

                gruppoOmbrelloni.add(radioButton);
                pannelloFila.add(radioButton);
            }

            mappaPanel.add(pannelloFila);
        }

        mappaPanel.revalidate();
        mappaPanel.repaint();
    }

    private void aggiornaServizi(Date data) {

        List<ServizioAggiuntivo> servizi = ControllerServizi.getServiziAggiuntivi();
        Map<Long, Integer> quantitaPrenotate = ControllerServizi.getQuantitaServiziPrenotati(data);

        JPanel serviziPanel = new JPanel();
        serviziPanel.setLayout(new BoxLayout(serviziPanel, BoxLayout.Y_AXIS));

        for (ServizioAggiuntivo servizio : servizi) {

            JPanel rigaServizio = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nomeServizio = new JLabel(servizio.getNome());
            JLabel disponibilitaLabel = new JLabel("Quantità:");

            JComboBox<Integer> comboBox = new JComboBox<>();

            int quantitaPrenotata = quantitaPrenotate.getOrDefault(servizio.getId(), 0);
            int disponibilitaResidua = Math.max(0, servizio.getDisponibilita() - quantitaPrenotata);

            for (int i = 0; i <= disponibilitaResidua; i++) {
                comboBox.addItem(i);
            }

            //Ricalcola il prezzo ogni volta che cambia la quantità
            comboBox.addActionListener(e -> aggiornaPrezzo());

            rigaServizio.add(nomeServizio);
            rigaServizio.add(disponibilitaLabel);
            rigaServizio.add(comboBox);

            rigaServizio.putClientProperty("servizio", servizio);
            rigaServizio.putClientProperty("comboBox", comboBox);

            serviziPanel.add(rigaServizio);
        }

        serviziPane.setViewportView(serviziPanel);

        serviziPane.revalidate();
        serviziPane.repaint();

        aggiornaPrezzo();
    }

    private void aggiornaPrezzo() {

        if (dataSelezionata == null) {
            prezzoCorrente = 0;
            prezzoLabel.setText("Prezzo: 0 €");
            return;
        }

        LocalDate data = dataSelezionata.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long prezzoTotale = 0;

        //Controllo se è selezionato un ombrellone
        if (ombrelloneSelezionato != null) {
            prezzoTotale += ControllerTariffe.getPrezzoOmbrellone(
                    ombrelloneSelezionato, data);
        }

        //Controllo se è selezionato un servizio aggiuntivo
        if (serviziPane.getViewport().getView() instanceof JPanel serviziPanel) {

            for (Component componente : serviziPanel.getComponents()) {

                JPanel rigaServizio = (JPanel) componente;

                ServizioAggiuntivo servizio = (ServizioAggiuntivo) rigaServizio.getClientProperty("servizio");
                JComboBox<Integer> comboBox = (JComboBox<Integer>) rigaServizio.getClientProperty("comboBox");

                int quantita = (Integer) comboBox.getSelectedItem();
                if (quantita > 0) {
                    prezzoTotale += ControllerTariffe.getPrezzoServizio(
                            servizio, data) * quantita;
                }
            }
        }

        prezzoCorrente = (int) prezzoTotale;
        prezzoLabel.setText("Prezzo: " + prezzoCorrente + " €");
    }

     */

    private void aggiornaServizi() {

        //Ripulisce la schermata ogni chiamata
        serviziPanel.removeAll();
        serviziPanel.setLayout(new GridLayout(0, 1));

        ControlStabilimento control = new ControlStabilimento();
        List<ServizioDTO> servizi = control.listaServizi();

        //gruppo per la mutua esclusivita dei pulsanti
        ButtonGroup buttonGroup = new ButtonGroup();

        if (servizi == null || servizi.isEmpty()) {
            serviziPanel.add(new JLabel("Non sono presenti servizi"));
        } else {
            for (ServizioDTO servizio : servizi) {

                JRadioButton radioButton = new JRadioButton(servizio.getNome());
                radioButton.putClientProperty("idServizio", servizio.getId());

                radioButton.addActionListener(e -> {

                    servizioSelezionato = servizio.getId();
                });

                buttonGroup.add(radioButton);
                serviziPanel.add(radioButton);
            }
        }

        serviziPanel.revalidate();
        serviziPanel.repaint();

        servizioSelezionato = null;
        nomeField.setText("");
        descrizioneArea.setText("");
        prezzoField.setText("");
        comboBox1.setSelectedIndex(0);
    }

    private void aggiornaMappa(){

        idPrimaFila = ControlStabilimento.idOmbrelloniFila(1);
        idSecondaFila = ControlStabilimento.idOmbrelloniFila(2);
        idTerzaFila = ControlStabilimento.idOmbrelloniFila(3);

        aggiungiFila(unoFilaPanel, idPrimaFila);
        aggiungiFila(dueFilaPanel, idSecondaFila);
        aggiungiFila(treFilaPanel, idTerzaFila);

    }

    private void aggiungiFila(JPanel filaPanel, List<Long> idOmbrelloni) {

        //Ripulisce la fila ogni volta che viene chiamata la funzione
        filaPanel.removeAll();
        filaPanel.setLayout(new GridLayout(1, idOmbrelloni.size(), 10, 10));


        if (idOmbrelloni.size() == 0) {
            JLabel label = new JLabel();

            label.setText("Non sono presenti ombrelloni all'interno di questa fila");
            filaPanel.add(label);
        }
        else {
            for (int i = 0; i < idOmbrelloni.size(); i++) {
                Long idOmbrellone = idOmbrelloni.get(i);

                JButton button = new JButton(String.valueOf(i + 1));

                button.putClientProperty("idOmbrellone", idOmbrellone);
                filaPanel.add(button);
            }
        }

        filaPanel.revalidate();
        filaPanel.repaint();
    }

    public static void main(String[] args) {
        FormEffettuaPrenotazione formEffettuaPrenotazioni = new FormEffettuaPrenotazione(null);
        formEffettuaPrenotazioni.apriFormEffPrenotazione();
    }

}