package boundary;

import control.ControlStabilimento;
import control.ServizioDTO;
import control.TariffaDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class FormTariffe {
    private JPanel tariffePanel;
    private JComboBox serviziBox;
    private JTextField prezzoField;
    private JButton aggiungiButton;
    private JButton rimuoviButton;
    private JButton modificaButton;
    private JSpinner daData;
    private JSpinner aData;
    private JLabel esitoLabel;
    private JScrollPane tariffeScroll;
    private JPanel listaTariffe;

    private Long tariffaSelezionata;
    private long idGestore;


    public FormTariffe(Long idGestore) {

        //this.idGestore = idGestore;

        SpinnerDateModel modelDa = new SpinnerDateModel();
        SpinnerDateModel modelA = new SpinnerDateModel();

        daData.setModel(modelDa);
        aData.setModel(modelA);

        JSpinner.DateEditor editorDa = new JSpinner.DateEditor(daData, "dd/MM/yyyy");
        JSpinner.DateEditor editorA = new JSpinner.DateEditor(aData, "dd/MM/yyyy");

        daData.setEditor(editorDa);
        aData.setEditor(editorA);

        /*
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float prezzo = Float.parseFloat(prezzoField.getText());

                Date daTemp = (Date) daData.getValue();
                Date aTemp = (Date) aData.getValue();
                LocalDate da = daTemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate a = aTemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if(serviziBox.getSelectedItem() instanceof Fila) {
                    Fila fila = (Fila) serviziBox.getSelectedItem();
                    boolean esito = ControllerTariffe.saveTariffaOmbrellone(prezzo, da, a, fila);

                    if(esito) {
                        esitoLabel.setText("Tariffa aggiunta");
                    } else {
                        esitoLabel.setText("Impossibile aggiungere tariffa");
                    }
                    aggiornaTariffe();
                }
                else if(serviziBox.getSelectedItem() instanceof ServizioAggiuntivo) {
                    ServizioAggiuntivo servizio = (ServizioAggiuntivo) serviziBox.getSelectedItem();
                    boolean esito = ControllerTariffe.saveTariffaServizio(prezzo, da, a, servizio);

                    if(esito) {
                        esitoLabel.setText("Tariffa aggiunta");
                    } else {
                        esitoLabel.setText("Impossibile aggiungere tariffa");
                    }
                    aggiornaTariffe();
                }
                //Il caso non viene mai chiamato in circostanze normali
                else {
                    esitoLabel.setText("Impossibile aggiungere tariffa");
                    aggiornaTariffe();
                }
            }

        });

        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean esito = ControllerTariffe.deleteTariffa(tariffaSelezionata);
                if(esito) {
                    esitoLabel.setText("Tariffa rimossa");
                }
                else {
                    esitoLabel.setText("Impossibile rimuovere tariffa");
                }
                aggiornaTariffe();
            }

        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float prezzo = Float.parseFloat(prezzoField.getText());

                Date daTemp = (Date) daData.getValue();
                Date aTemp = (Date) aData.getValue();
                LocalDate da = daTemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate a = aTemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if(serviziBox.getSelectedItem() instanceof Fila) {
                    Fila fila = (Fila) serviziBox.getSelectedItem();
                    boolean esito = ControllerTariffe.updateTariffaOmbrellone(prezzo, da, a, fila, tariffaSelezionata);

                    if(esito) {
                        esitoLabel.setText("Tariffa modificata");
                    } else {
                        esitoLabel.setText("Impossibile modificare tariffa");
                    }
                    aggiornaTariffe();
                }
                else if(serviziBox.getSelectedItem() instanceof ServizioAggiuntivo) {
                    ServizioAggiuntivo servizio = (ServizioAggiuntivo) serviziBox.getSelectedItem();
                    boolean esito = ControllerTariffe.updateTariffaServizio(prezzo, da, a, servizio, tariffaSelezionata);

                    if(esito) {
                        esitoLabel.setText("Tariffa modificata");
                    } else {
                        esitoLabel.setText("Impossibile modificare tariffa");
                    }
                    aggiornaTariffe();
                }
                //Il caso non viene mai chiamato in circostanze normali
                else {
                    esitoLabel.setText("Impossibile aggiungere tariffa");
                    aggiornaTariffe();
                }
            }

        });

         */
    }

    public JFrame apriFormTariffe() {

        JFrame frame = new JFrame("Gestione Tariffe");

        frame.setContentPane(tariffePanel);

        //Inserisce gli elementi nel comboBox
        aggiornaComboBox();

        //Crea la vista tariffe
        aggiornaTariffe();

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

    //Per aggiornare la vista qualvolta venga inserita o rimossa una tariffa (ed anche quando viene aperta la schermata)
    private void aggiornaTariffe() {

        //Pulisce la lista
        listaTariffe.removeAll();

        //Prende le tariffe
        List<TariffaDTO> tariffe = ControlStabilimento.listaTariffe();

        listaTariffe.setLayout(new BoxLayout(listaTariffe, BoxLayout.Y_AXIS));

        ButtonGroup gruppoTariffe = new ButtonGroup();

        if (tariffe == null || tariffe.isEmpty()) {
            listaTariffe.add(new JLabel("Non sono presenti tariffe"));
        }
        else{
            for (TariffaDTO tariffa : tariffe) {

                JRadioButton radioButton = new JRadioButton(
                        String.format(
                                "Prezzo: %.2f €    %s   Dal: %s    Al: %s",
                                tariffa.getPrezzo(),
                                tariffa.getAssociazione(),
                                tariffa.getDataInizio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                tariffa.getDataFine().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        )
                );

                gruppoTariffe.add(radioButton);
                // Store the Tariffa inside the radio button
                radioButton.putClientProperty("tariffaId", tariffa.getId());

                radioButton.addActionListener(e -> {

                    tariffaSelezionata = tariffa.getId();
                    prezzoField.setText(Float.toString(tariffa.getPrezzo()));

                /*
                //Converti da LocalDate a Date per JSpinner
                Date dataDa = Date.from(
                        tariffa.getDataInizio()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()
                );
                daData.setValue(dataDa);

                //Converti da LocalDate a Date per JSpinner
                Date dataA = Date.from(
                        tariffa.getDataFine()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()
                );

                aData.setValue(dataA);

                System.out.println("Tariffa selezionata");

                 */

                });

                listaTariffe.add(radioButton);
            }
        }


        listaTariffe.revalidate();
        listaTariffe.repaint();

        prezzoField.setText("");

    }

    //Crea un ComboBox con elementi di Fila e i servizi presenti
    private void aggiornaComboBox() {

        ControlStabilimento control = new ControlStabilimento();
        List<ServizioDTO> servizi = control.listaServizi();

        for (ServizioDTO servizio : servizi) {
            serviziBox.addItem(servizio.getNome());
        }
        /*
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        List<ServizioAggiuntivo> servizi = ControllerServizi.getServiziAggiuntivi();

        for (Fila fila : Fila.values()) {
            model.addElement(fila);
        }

        for (ServizioAggiuntivo servizio : servizi) {
            model.addElement(servizio);
        }

        serviziBox.setModel(model);

         */
    }

    public static void main(String[] args) {
        FormTariffe formTariffe = new FormTariffe(null);
        formTariffe.apriFormTariffe();
    }
}
