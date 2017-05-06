package weka;

import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel panel;
    private JTextArea log;
    private JButton year1Button;
    private JButton year4Button;
    private JButton year3Button;
    private JButton year2Button;
    private JButton year5Button;
    private JCheckBox unprunedCheckBox;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;

    private TreeHandler handler;


    public App() {

        init();

        try {

            year1Button.addActionListener(new buttonHandler(handler.determineTree(1) ,handler.getTree(1)));
            year2Button.addActionListener(new buttonHandler(handler.determineTree(2) ,handler.getTree(2)));
            year3Button.addActionListener(new buttonHandler(handler.determineTree(3) ,handler.getTree(3)));
            year4Button.addActionListener(new buttonHandler(handler.determineTree(4) ,handler.getTree(4)));
            year5Button.addActionListener(new buttonHandler(handler.determineTree(5) ,handler.getTree(5)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class buttonHandler implements ActionListener {

        String msg;
        J48 tree;

        public buttonHandler(String msg, J48 tree) {
            this.msg = msg;
            this.tree = tree;
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
                String[] options = new String[1];


                if(unprunedCheckBox.isSelected())
                    options[0] = "-U";
                else
                    options[0] = "";

                tree.setOptions(options);

                tv = new TreeVisualizer(null,tree.graph(), new PlaceNode2());

                jf.getContentPane().add(tv, BorderLayout.CENTER);
                jf.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        jf.dispose();
                    }
                });

                jf.setVisible(true);
                tv.fitToScreen();

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
