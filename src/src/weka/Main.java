package weka;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class Main {

    public  static void main(String [] args) throws Exception {
        System.out.println("Aplicação de ID3 ou C4.5 para a predição de bancarrota em companhias polacas\n");


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


        System.out.println(dataSetTrain.toSummaryString());

        J48 tree= new J48();
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




        // display classifier
        final javax.swing.JFrame jf = new javax.swing.JFrame("Aplicação de ID3 ou C4.5 para a predição de bancarrota em companhias polacas");
        jf.setSize(500,400);
        jf.getContentPane().setLayout(new BorderLayout());
        TreeVisualizer tv = new TreeVisualizer(null,tree.graph(), new PlaceNode2());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });

        jf.setVisible(true);
        tv.fitToScreen();



    }
}
