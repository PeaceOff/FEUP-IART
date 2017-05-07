package weka;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by PeaceOff on 07-05-2017.
 */
public class Query {

    private JFrame frame;
    public JPanel panel;
    private JPanel panel_1;

    public Query(JFrame frame) {

        this.frame = frame;
        panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(64, 2, 0, 1));
        JScrollPane jsPane = new JScrollPane(panel_1);




        JButton button_1 = new JButton("Year 1");
        JButton button_2 = new JButton("Year 2");
        JButton button_3 = new JButton("Year 3");
        JButton button_4 = new JButton("Year 4");
        JButton button_5 = new JButton("Year 5");

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
        this.frame.setSize(1920, 1080);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        //JOptionPane.showMessageDialog(this.frame,"Eggs are not supposed to be green.");
    }

    private void add_text(String label_text, String value){

        JLabel label = new JLabel(label_text);
        label.setToolTipText(value);
        JTextField text_field = new JTextField();

        label.setVisible(true);
        text_field.setVisible(true);
        panel_1.add(label);
        panel_1.add(text_field);

        panel_1.revalidate();
        panel_1.repaint();

    }

    public void init(){


        HashMap<String,String> hmap = AttributeMapper.get_attributes();
        Iterator it = hmap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<String,String> pair = (HashMap.Entry<String,String>)it.next();
            add_text(pair.getKey(),pair.getValue());
        }
    }
}
