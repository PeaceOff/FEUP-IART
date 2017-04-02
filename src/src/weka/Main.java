package weka;

import weka.classifiers.trees.J48;
import weka.core.Instances;

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



    }
}
