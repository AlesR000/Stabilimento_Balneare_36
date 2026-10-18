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

public class GestoreTariffe {
    private JPanel tariffePanel;
    private JComboBox serviziBox;
    private JTextField prezzoField;
    private JButton aggiungiButton;
    private JButton rimuoviButton;
    private JSpinner daData;
    private JSpinner aData;
    private JLabel esitoLabel;
    private JScrollPane tariffeScroll;
    private JPanel listaTariffe;

    private Long tariffaSelezionata;
    private long idGestore;


    public GestoreTariffe(Long idGestore) {

        //this.idGestore = idGestore;

        SpinnerDateModel modelDa = new SpinnerDateModel();
        SpinnerDateModel modelA = new SpinnerDateModel();

        daData.setModel(modelDa);
        aData.setModel(modelA);

        JSpinner.DateEditor editorDa = new JSpinner.DateEditor(daData, "dd/MM/yyyy");
        JSpinner.DateEditor editorA = new JSpinner.DateEditor(aData, "dd/MM/yyyy");

        daData.setEditor(editorDa);
        aData.setEditor(editorA);

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlStabilimento cs = new ControlStabilimento();

                String prezzo = (prezzoField.getText());

                Date daTemp = (Date) daData.getValue();
                Date aTemp = (Date) aData.getValue();
                LocalDate da = daTemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate a = aTemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                boolean esito = cs.salvaTariffa(serviziBox.getSelectedIndex(),serviziBox.getSelectedItem().toString(), prezzo, da, a);

                if(esito){
                    esitoLabel.setText("Tariffa aggiunta");
                }
                else {
                    esitoLabel.setText("Impossibile aggiungere tariffa");
                }

                aggiornaTariffe();
            }

        });

        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlStabilimento cs = new ControlStabilimento();
                boolean esito = cs.rimuoviTariffa(tariffaSelezionata);
                if(esito) {
                    esitoLabel.setText("Tariffa rimossa");
                }
                else {
                    esitoLabel.setText("Impossibile rimuovere tariffa");
                }
                aggiornaTariffe();
            }

        });


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
                                "Prezzo: %d €    %s   Dal: %s    Al: %s",
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

        if (servizi == null || servizi.isEmpty()) {

        }
        else {
            for (ServizioDTO servizio : servizi) {
                serviziBox.addItem(servizio.getNome());
            }
        }
    }

    public static void main(String[] args) {
        GestoreTariffe gestoreTariffe = new GestoreTariffe(null);
        gestoreTariffe.apriFormTariffe();
    }
}
