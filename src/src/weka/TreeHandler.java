package weka;

import jdk.internal.util.xml.impl.Input;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joao on 5/6/17.
 */
public class TreeHandler {


    private J48 trees[] = new J48[]{null,null,null,null,null};
    private Double fiabilities[] = new Double[]{0.0,0.0,0.0,0.0,0.0};

    private ArrayList<String> d = new ArrayList<>();

    public TreeHandler(String folderPath){
        loadTrees(folderPath);
    }

    private double fiability(J48 tree, Instances test) throws Exception {
        int success = 0;
        for(int i = 0; i < test.numInstances(); i++){
            double classified = tree.classifyInstance(test.instance(i));
            if( classified == test.instance(i).classValue())
                success++;

        }
        return success/(double)test.numInstances();
    }

    public J48 getTree(int index){
        return trees[index];
    }
    private void loadTree(int i , String folderPath) {

        String file = folderPath + "/" + i + "year.arff";
        Instances train = null;
        Instances test = null;
        J48 res = null;
        double fiability = 0.0;
        try {

            BufferedReader bf = new BufferedReader(new FileReader(file));

            train = new Instances(bf);
            train.setClassIndex(train.numAttributes() - 1);

            test = new Instances(train, train.numInstances()/2);

            int k = 0;

            for(int o = 0; o < train.numInstances(); o++){
                if(k % 2 == 0) {
                    test.add(train.get(o));
                    train.remove(o--);
                }
                k++;
            }

            res = new J48();
            res.buildClassifier(train);
            fiability = fiability(res, test);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fiabilities[i-1] = fiability;
        trees[i-1] = res;

    }

    private void loadTrees(String folderPath){

        for(int i = 1 ; i <= trees.length; i++){
            loadTree(i,folderPath);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < trees.length;i++){
            sb.append("Fiability[");
            sb.append(i + 1);
            sb.append("]=");
            sb.append(fiabilities[i] * 100.0);
            sb.append("%");
            sb.append("\n");
        }
        for(String s : d){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String determineTree(int i){

        J48 tree = trees[i];

        StringBuilder sb = new StringBuilder();

        sb.append(tree.binarySplitsTipText());

        String treeStr = tree.toString();


        Matcher p = Pattern.compile("Attr\\d+").matcher(treeStr);

        List<String> matches = new ArrayList<String>();

        while(p.find()) {
            System.out.println(p.group());
            System.out.println(AttributeMapper.getAttributeName(p.group()));
            treeStr = treeStr.replace(p.group(), AttributeMapper.getAttributeName(p.group()));
        }

        sb.append(treeStr);

        return sb.toString();
    }

}
