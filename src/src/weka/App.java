package weka;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class App {
    private JButton treeBtn;
    private JPanel panel;
    private JTextArea log;
    private J48 tree;

    public App() {
        treeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    // display classifier
                    final javax.swing.JFrame jf = new javax.swing.JFrame("Aplicação de ID3 ou C4.5 para a predição de bancarrota em companhias polacas");
                    jf.setSize(1920,1080);
                    jf.getContentPane().setLayout(new BorderLayout());
                    TreeVisualizer tv = null;
                    tv = new TreeVisualizer(null,tree.graph(), new PlaceNode2());

                    jf.getContentPane().add(tv, BorderLayout.CENTER);
                    jf.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            jf.dispose();
                        }
                    });

                    jf.setVisible(true);
                    tv.fitToScreen();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void init(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader("dataset/1year.arff"));
            Instances dataSetTrain = new Instances(reader);
            //set the attribute for classification in this case it's 65
            dataSetTrain.setClassIndex(dataSetTrain.numAttributes() - 1);
            reader.close();


            reader = new BufferedReader(new FileReader("dataset/2year.arff"));
            Instances dataSetTest = new Instances(reader);
            //set the attribute for classification in this case it's 65
            dataSetTest.setClassIndex(dataSetTest.numAttributes() - 1);

            reader.close();

            log.setText(dataSetTrain.toSummaryString());



            tree= new J48();
            //do the training
            tree.buildClassifier(dataSetTrain);

            Instances labeled= new Instances(dataSetTest);

            //label the test instances
            for (int i = 0 ; i < dataSetTest.numInstances() ; i++){
            double clsLabel = tree.classifyInstance(dataSetTest.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
            }

            //save the new instance

            BufferedWriter writer = new BufferedWriter(new FileWriter("test/labeled.arff"));
            writer.write(labeled.toString());

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public  static void main(String [] args) throws Exception {

        JFrame frame = new JFrame("App");
        App app = new App();
        app.init();
        frame.setContentPane(app.panel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


}
