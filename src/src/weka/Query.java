package weka;

import weka.classifiers.trees.J48;
import weka.core.*;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by PeaceOff on 07-05-2017.
 */
public class Query {

    private JFrame frame;
    private JPanel panel;
    private JPanel panel_1;
    GridBagConstraints constraints;
    private ArrayList<JTextField> attr_values = new ArrayList<JTextField>();
    private TreeHandler handler;

    public Query(JFrame frame,TreeHandler handler) {

        this.handler = handler;
        this.frame = frame;
        panel_1 = new JPanel();
        GridBagLayout gl = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;
        panel_1.setLayout(gl);

        //panel_1.setLayout(new GridLayout(64, 5, 0, 1));
        JScrollPane jsPane = new JScrollPane(panel_1);

        JButton button_1 = new JButton("Year 1");
        JButton button_2 = new JButton("Year 2");
        JButton button_3 = new JButton("Year 3");
        JButton button_4 = new JButton("Year 4");
        JButton button_5 = new JButton("Year 5");
        button_1.addActionListener(new Query.buttonHandler(1));
        button_2.addActionListener(new Query.buttonHandler(2));
        button_3.addActionListener(new Query.buttonHandler(3));
        button_4.addActionListener(new Query.buttonHandler(4));
        button_5.addActionListener(new Query.buttonHandler(5));

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(new BoxLayout(panel_2,BoxLayout.Y_AXIS));
        panel_2.add(button_1,BorderLayout.SOUTH);
        panel_2.add(button_2,BorderLayout.SOUTH);
        panel_2.add(button_3,BorderLayout.SOUTH);
        panel_2.add(button_4,BorderLayout.SOUTH);
        panel_2.add(button_5,BorderLayout.SOUTH);
        this.frame.add(panel_2,BorderLayout.EAST);

        this.frame.add(jsPane);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public class buttonHandler implements ActionListener {

        int year;

        public buttonHandler(int i){
            year = i;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Instance values = get_instance();
            Double res = null;
            try {
                 res = handler.getTree(year).classifyInstance(values);
            } catch (Exception e) {
                System.out.println("Could not fetch results!");
            }

            if(res != null){
                if(res == 1.0){
                    JOptionPane.showMessageDialog(frame,"Bankrupcy in " + (6 - year) + " years!");
                } else {
                    JOptionPane.showMessageDialog(frame,"No Bankrupcy in " + (6 - year) + " years!");
                }
            }
        }
    }

    private Instance get_instance(){

        ArrayList<Double> values = get_values();

        for(int i = 0; i < 64; i++){
            TreeHandler.scoopy_potato.setValue(i,values.get(i));
        }

        return TreeHandler.scoopy_potato;
    }

    private void add_text(int i, String label_text, String value){

        JLabel label = new JLabel(label_text);
        label.setToolTipText(value);
        NumberFormat f = NumberFormat.getNumberInstance();
        f.setMaximumFractionDigits(14);
        f.setMinimumFractionDigits(5);
        JFormattedTextField text_field = new JFormattedTextField(f);

        constraints.gridy = i;
        label.setVisible(true);
        text_field.setVisible(true);

        constraints.weightx = 0.1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        panel_1.add(label,constraints);

        constraints.weightx = 0.9;
        constraints.gridx = 1;
        constraints.gridwidth = 4;
        panel_1.add(text_field,constraints);

        attr_values.add(text_field);
    }

    private ArrayList<Double> get_values() {

        ArrayList<Double> res = new ArrayList<Double>();

        for (JTextField tf : attr_values){
            if(tf.getText().equals(""))
                res.add(0.0);
            else
                res.add(Double.parseDouble(tf.getText().replace(".","").replace(',','.')));
        }
        return res;
    }

    public void init(){


        HashMap<String,String> hmap = AttributeMapper.get_attributes();

        for(int i = 1; i < 65; i++){
            String search = "Attr" + i;
            add_text(i,search,hmap.get(search));
        }

        panel_1.revalidate();
        panel_1.repaint();
    }
}
