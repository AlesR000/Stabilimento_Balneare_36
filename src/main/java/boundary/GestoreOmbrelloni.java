package boundary;

import control.ControlStabilimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GestoreOmbrelloni {

    private JPanel ombrelloniPanel;
    private JComboBox filaBox;
    private JButton aggiungiButton;
    private JButton rimuoviButton;
    private JLabel esitoLabel;
    private JScrollPane scrollMap;
    private JPanel mappaPanel;
    private JPanel dueFilaPanel;
    private JPanel treFilaPanel;
    private JPanel unoFilaPanel;

    private Long idGestore;
    List<Long> idPrimaFila;
    List<Long> idSecondaFila;
    List<Long> idTerzaFila;

    public GestoreOmbrelloni(Long idGestore) {

        aggiornaMappa();

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fila = filaBox.getSelectedItem().toString();
                ControlStabilimento control = new ControlStabilimento();
                boolean add = control.salvaOmbrellone(fila, 5);
                if (add) {
                    esitoLabel.setText("Ombrellone aggiunto.");
                    aggiornaMappa();
                } else  {
                    esitoLabel.setText("Operazione fallita.");
                }
            }
        });

        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fila = filaBox.getSelectedItem().toString();
                ControlStabilimento control = new ControlStabilimento();
                boolean rmv = control.rimuoviOmbrellone(fila);
                if (rmv) {
                    esitoLabel.setText("Ombrellone rimosso.");
                    aggiornaMappa();
                } else  {
                    esitoLabel.setText("Operazione fallita.");
                }
            }
        });
    }

    public JFrame apriFormOmbrelloni() {

        JFrame frame = new JFrame("Gestione ombrelloni");

        frame.setContentPane(ombrelloniPanel);

        /*
        scrollMap.setViewportView(mappaPanel);
        Map<Fila, ArrayList<Ombrellone>> mappa = ControllerOmbrelloni.mappaOmbrelloni();
        //Crea 3 file per il display degli ombrelloni
        mappaPanel.setLayout(new GridLayout(3, 1));

        for (Fila fila : Fila.values()) {

            JPanel filaPanel = new JPanel();
            filaPanel.setLayout(new BoxLayout(filaPanel, BoxLayout.Y_AXIS));

            JPanel ombrelloniPanel = new JPanel();
            ombrelloniPanel.setLayout(new BoxLayout(ombrelloniPanel, BoxLayout.X_AXIS));

            for (Ombrellone o : mappa.get(fila)) {

                ombrelloniPanel.add(
                        new JLabel("[" + o.getNumero_posto() + "]")
                );

                ombrelloniPanel.add(Box.createHorizontalStrut(10));
            }

            filaPanel.add(ombrelloniPanel);
            mappaPanel.add(filaPanel);
        }

        scrollMap.setViewportView(mappaPanel);
        */

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;

    }


    private void aggiornaMappa(){

        this.idGestore = idGestore;

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
        GestoreOmbrelloni gestoreOmbrelloni = new GestoreOmbrelloni(null);
        gestoreOmbrelloni.apriFormOmbrelloni();
    }

}
