package boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale extends JFrame{
    private JPanel contentPanel;
    private JButton iscriviti;
    private JButton accedi;

    //frame per la chiamata a registrazione e login
    private JFrame frameIscrizione;
    private JFrame frameAccesso;

    public SchermataPrincipale() {

        //Chiama il form corrispondente
        iscriviti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frameIscrizione == null ||
                        !frameIscrizione.isDisplayable()) {

                    //nuovo oggetto formIscrizione
                    FormIscrizione formInserimento =
                            new FormIscrizione();


                    frameIscrizione = formInserimento.apriFormIscrizione();

                    frameIscrizione.setLocationRelativeTo(null);
                    frameIscrizione.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    frameIscrizione.toFront();
                    frameIscrizione.requestFocus();
                }
            }
        });

        //Chiama il form corrispondente
        accedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frameAccesso == null ||
                        !frameAccesso.isDisplayable()) {

                    //nuovo oggetto formAccesso
                    FormAccesso formAccesso =
                            new FormAccesso();

                    frameAccesso = formAccesso.apriFormAccesso();

                    frameAccesso.setLocationRelativeTo(null);
                    frameAccesso.setVisible(true);

                } else {

                    //Se la finestra esiste, viene spostata in avanti
                    frameAccesso.toFront();
                    frameAccesso.requestFocus();
                }
            }
        });

    }

    //Funzione chiamata dal main
    public JFrame apriSchermataPrincipale() {

        JFrame frame = new JFrame("Homepage");

        frame.setContentPane(contentPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1200);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    //MAIN!!
    public static void main(String[] args) {

        SchermataPrincipale schermataPrincipale = new SchermataPrincipale();
        schermataPrincipale.apriSchermataPrincipale();

    }

}
