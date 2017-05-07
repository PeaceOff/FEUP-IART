package weka;

import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {
    private JPanel panel;
    private JTextArea log;
    private JButton year1Button;
    private JButton year4Button;
    private JButton year3Button;
    private JButton year2Button;
    private JButton year5Button;
    private JCheckBox unprunedCheckBox;
    private JCheckBox reducedErrorPruningCheckBox;
    private JCheckBox subTreeRaisingCheckBox;
    private JTextField pruningConfidence;
    private JTextField minimumNumberOfInstances;

    private TreeHandler handler;


    public App() {

        init();

        try {

            year1Button.addActionListener(new buttonHandler(1));
            year2Button.addActionListener(new buttonHandler(2));
            year3Button.addActionListener(new buttonHandler(3));
            year4Button.addActionListener(new buttonHandler(4));
            year5Button.addActionListener(new buttonHandler(5));

        } catch (Exception e) {
            e.printStackTrace();
        }

        unprunedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                boolean val = !unprunedCheckBox.isSelected();


                reducedErrorPruningCheckBox.setEnabled(val);
                subTreeRaisingCheckBox.setEnabled(val);
                pruningConfidence.setEnabled(val);
                minimumNumberOfInstances.setEnabled(val);
            }
        });

        reducedErrorPruningCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean val = !reducedErrorPruningCheckBox.isSelected();

                unprunedCheckBox.setEnabled(val);
                subTreeRaisingCheckBox.setEnabled(val);
                pruningConfidence.setEnabled(val);
                minimumNumberOfInstances.setEnabled(val);
            }
        });
    }

    public class buttonHandler implements ActionListener {

        int index = 0;
        public buttonHandler(int i) {
            index = i;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
            // display classifier
            final javax.swing.JFrame jf = new javax.swing.JFrame("Tree view");
                jf.setSize(1920,1080);
                jf.getContentPane().setLayout(new BorderLayout());
                TreeVisualizer tv = null;

                //set option
                ArrayList<String> options= new ArrayList();


                if(unprunedCheckBox.isSelected())
                    options.add("-U");
                else if (reducedErrorPruningCheckBox.isSelected())
                    options.add("-R");
                else{
                    if (!subTreeRaisingCheckBox.isSelected())
                        options.add("-S");

                        options.add("-C");
                        options.add(pruningConfidence.getText());

                        options.add("-M");
                        options.add(minimumNumberOfInstances.getText());
                }



                System.out.println(options.toArray(new String[0]));

                handler.loadTree(index, options.toArray(new String[0]));

                J48 tree = handler.getTree(index);

                tv = new TreeVisualizer(null,tree.graph(), new PlaceNode2());

                jf.getContentPane().add(tv, BorderLayout.CENTER);
                jf.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        jf.dispose();
                    }
                });

                jf.setVisible(true);
                tv.fitToScreen();

                String msg = handler.determineTree(index);

                log.setText(null);
                log.append(msg);
                log.append("Fiability of all years");
                log.append(handler.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init(){
        handler = new TreeHandler("dataset/");
    }

    public  static void main(String [] args) throws Exception {

        JFrame frame = new JFrame("IART@FEUP");
        App app = new App();

        frame.setContentPane(app.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


}
