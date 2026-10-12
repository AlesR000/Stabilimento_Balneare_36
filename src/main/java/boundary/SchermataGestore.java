package boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataGestore {
    private JPanel stabilimentoPanel;
    private JButton ombrelloniButton;
    private JButton tariffeButton;
    private JButton serviziButton;
    private JButton prenotazioniButton;
    private JButton logoutButton;
    private JLabel benvenutoLabel;

    private Long idGestore;

    private JFrame frame;
    private JFrame ombrelloniFrame;
    private JFrame serviziFrame;
    private JFrame tariffeFrame;
    private JFrame prenotazioniFrame;

    public JFrame apriFormStabilimento() {

        frame = new JFrame("Stabilimento Balneare");

        frame.setContentPane(stabilimentoPanel);

        //Chiude la singola finestra in uscita
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        return frame;
    }


    public SchermataGestore(Long idGestore) {
        this.idGestore = idGestore;
        benvenutoLabel.setText("Benvenuto!");
        ombrelloniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ombrelloniFrame == null ||
                        !ombrelloniFrame.isDisplayable()) {

                    //nuovo oggetto formOmbrelloni
                    GestoreOmbrelloni formOmbrelloni =
                            new GestoreOmbrelloni(idGestore);


                    ombrelloniFrame = formOmbrelloni.apriFormOmbrelloni();

                    ombrelloniFrame.setLocationRelativeTo(null);
                    ombrelloniFrame.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    ombrelloniFrame.toFront();
                    ombrelloniFrame.requestFocus();
                }
            }
        });
        /*
        serviziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serviziFrame == null ||
                        !serviziFrame.isDisplayable()) {

                    //nuovo oggetto formServizio
                    FormServizi formServizio =
                            new FormServizi();


                    serviziFrame = formServizio.apriFormServizi();

                    serviziFrame.setLocationRelativeTo(null);
                    serviziFrame.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    serviziFrame.toFront();
                    serviziFrame.requestFocus();
                }
            }
        });
        tariffeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tariffeFrame == null ||
                        !tariffeFrame.isDisplayable()) {

                    //nuovo oggetto formTariffa
                    FormTariffe formTariffa =
                            new FormTariffe();


                    tariffeFrame = formTariffa.apriFormTariffe();

                    tariffeFrame.setLocationRelativeTo(null);
                    tariffeFrame.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    tariffeFrame.toFront();
                    tariffeFrame.requestFocus();
                }
            }
        });
        prenotazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prenotazioniFrame == null ||
                        !prenotazioniFrame.isDisplayable()) {

                    //nuovo oggetto schermata
                    SchermataPrenotazioniGestore schermataPrenotazioniGestore =
                            new SchermataPrenotazioniGestore();

                    prenotazioniFrame = schermataPrenotazioniGestore.apriFormGestorePrenotazioni();

                    prenotazioniFrame.setLocationRelativeTo(null);
                    prenotazioniFrame.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    prenotazioniFrame.toFront();
                    prenotazioniFrame.requestFocus();
                }
            }
        });

         */
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
