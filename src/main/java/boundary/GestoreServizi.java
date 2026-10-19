package boundary;

import control.ControlStabilimento;
import control.ServizioDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestoreServizi {
    private JTextField nomeField;
    private JTextArea descrizioneArea;
    private JButton aggiungiButton;
    private JComboBox comboBox1;
    private JButton rimuoviButton;
    private JButton modificaButton;
    private JPanel serviziPanel;
    private JScrollPane listPane;
    private JLabel esitoLabel;
    private JTextField prezzoField;
    private JPanel listPanel;

    private Long idGestore;
    private Long servizioSelezionato;

    public GestoreServizi(Long idGestore) {

        this.idGestore = idGestore;

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlStabilimento cs = new ControlStabilimento();

                String nome = nomeField.getText();
                String descrizione = descrizioneArea.getText();
                String prezzo = prezzoField.getText();
                int quantita = Integer.parseInt(comboBox1.getSelectedItem().toString());

                boolean aggiungi = cs.salvaServizio(nome, quantita, prezzo, descrizione);

                if (aggiungi) {
                    esitoLabel.setText("Servizio aggiunto");
                } else  {
                    esitoLabel.setText("Impossibile aggiungere servizio");
                }

                aggiornaLista();
            }
        });


        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlStabilimento cs = new ControlStabilimento();

                String nome = nomeField.getText();
                String descrizione = descrizioneArea.getText();
                String prezzo = prezzoField.getText();
                int quantita = Integer.parseInt(comboBox1.getSelectedItem().toString());

                boolean modifica = cs.aggiornaServizio(servizioSelezionato, nome, prezzo, quantita, descrizione);

                if(modifica){
                    esitoLabel.setText("Servizio modificato");
                } else {
                    esitoLabel.setText("Impossibile modificare servizio");
                }

                aggiornaLista();
            }
        });


        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlStabilimento cs = new ControlStabilimento();

                boolean rimuovi = cs.rimuoviServizio(servizioSelezionato);

                if(rimuovi){
                    esitoLabel.setText("Servizio rimosso");
                } else {
                    esitoLabel.setText("Impossibile rimuovere servizio");
                }

                aggiornaLista();
            }
        });


    }

    public JFrame apriFormServizi() {

        JFrame frame = new JFrame("Gestione Servizi");
        frame.setContentPane(serviziPanel);

        aggiornaLista();

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }

    private void aggiornaLista() {

        //Ripulisce la schermata ogni chiamata
        listPanel.removeAll();
        listPanel.setLayout(new GridLayout(0, 1));

        ControlStabilimento control = new ControlStabilimento();
        List<ServizioDTO> servizi = control.listaServizi();

        //gruppo per la mutua esclusivita dei pulsanti
        ButtonGroup buttonGroup = new ButtonGroup();

        if (servizi == null || servizi.isEmpty()) {
            listPanel.add(new JLabel("Non sono presenti servizi"));
        } else {
            for (ServizioDTO servizio : servizi) {

                JRadioButton radioButton = new JRadioButton(servizio.getNome());
                radioButton.putClientProperty("idServizio", servizio.getId());

                radioButton.addActionListener(e -> {
                    nomeField.setText(servizio.getNome());
                    descrizioneArea.setText(servizio.getDescrizione());
                    prezzoField.setText(String.valueOf(servizio.getPrezzo()));
                    comboBox1.setSelectedIndex(servizio.getQuantita() - 1);

                    servizioSelezionato = servizio.getId();
                });

                buttonGroup.add(radioButton);
                listPanel.add(radioButton);
            }
        }

        listPanel.revalidate();
        listPanel.repaint();

        servizioSelezionato = null;
        nomeField.setText("");
        descrizioneArea.setText("");
        prezzoField.setText("");
        comboBox1.setSelectedIndex(0);
    }



}
