package weka;

import jdk.internal.util.xml.impl.Input;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TreeHandler {


    private J48 trees[] = new J48[]{null,null,null,null,null};
    private Double reliabilities[] = new Double[]{0.0,0.0,0.0,0.0,0.0};
    private String lastFolderPath = "";
    private ArrayList<String> d = new ArrayList<>();
    public static Instance scoopy_potato;

    public TreeHandler(String folderPath){
        loadTrees(folderPath);
        lastFolderPath = folderPath;
    }

    private double reliability(J48 tree, Instances test) throws Exception {
        int success = 0;
        for(int i = 0; i < test.numInstances(); i++){
            double classified = tree.classifyInstance(test.instance(i));
            if( classified == test.instance(i).classValue())
                success++;
        }

        return success/(double)test.numInstances();
    }

    public J48 getTree(int index){
        return trees[index-1];
    }

    public void loadTree(int i, String[] options,int division){
    loadTree(i,lastFolderPath, options,division );
    }


    private void loadTree(int i , String folderPath, String[] options, int division) {

        String file = folderPath + "/" + i + "year.arff";
        Instances train = null;
        Instances test = null;
        J48 res = null;
        double reliability = 0.0;
        try {

            BufferedReader bf = new BufferedReader(new FileReader(file));

            train = new Instances(bf);
            train.setClassIndex(train.numAttributes() - 1);

            scoopy_potato = train.get(0);

            test = new Instances(train, train.numInstances()/2);

            int k = 0;

            for(int o = 0; o < train.numInstances(); o++){
                if(k % division == 0) {
                    test.add(train.get(o));
                    train.remove(o--);
                }
                k++;
            }


            res = new J48();
            res.setOptions(options);
            res.buildClassifier(train);
            reliability = reliability(res, test);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reliabilities[i-1] = reliability;
        trees[i-1] = res;

    }

    private void loadTrees(String folderPath){

        for(int i = 1 ; i <= trees.length; i++){
            loadTree(i,folderPath, null,2);
        }
    }


    public Double getReliability(int tree){
        return reliabilities[tree-1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < trees.length;i++){
            sb.append("Fiability[");
            sb.append(i + 1);
            sb.append("]=");
            sb.append(reliabilities[i] * 100.0);
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

        J48 tree = trees[i-1];

        StringBuilder sb = new StringBuilder();

        sb.append(tree.binarySplitsTipText());

        String treeStr = tree.toString();


        Matcher p = Pattern.compile("Attr\\d+").matcher(treeStr);

        while(p.find()) {
            treeStr = treeStr.replace(p.group(), AttributeMapper.getAttributeName(p.group()));
        }

        sb.append(treeStr);

        return sb.toString();
    }

}
