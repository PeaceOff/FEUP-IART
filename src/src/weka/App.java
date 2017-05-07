package weka;

import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JFrame frame;
    private Container oldPanel;
    private JPanel panel;
    private JTextArea log;
    private JButton year1Button;
    private JButton year4Button;
    private JButton year3Button;
    private JButton year2Button;
    private JButton year5Button;
    private JCheckBox unprunedCheckBox;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JButton check_btn;

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

        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createFrame();

            }
        });
    }

    public void createFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame("IART@FEUP");
                Query query = new Query(frame,handler);
                query.init();
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
                String[] options = new String[1];


                if(unprunedCheckBox.isSelected())
                    options[0] = "-U";
                else
                    options[0] = "";

                handler.loadTree(index, options);

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

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private void init(){
        handler = new TreeHandler("dataset/");
    }

    public  static void main(String [] args) throws Exception {

        JFrame frame = new JFrame("IART@FEUP");
        App app = new App();

        app.setFrame(frame);
        frame.setContentPane(app.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


}
